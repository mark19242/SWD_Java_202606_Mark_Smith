package learn.pets.dao;

import learn.pets.models.Pet;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPetDao implements PetDao{

    private final String BASE_PET_SELECT = "SELECT pet_id, name, type FROM pet ";

    private final DataSource dataSource;

    //CTOR Injection (for now)
    public JdbcPetDao(DataSource dataSource){

        this.dataSource = dataSource;
    }

    /**
     * @return
     */
    @Override
    public List<Pet> findAll() {

        List<Pet> pets = new ArrayList<>();

        final String sql = BASE_PET_SELECT + ";";

        try(
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery(sql);
        ){

            while(results.next()){

                Pet p = mapRowToPet(results);
                pets.add(p);
            }


        }catch( SQLException sqlex){
            throw new RuntimeException("Error Communication with Database: "
                    + sqlex.getErrorCode());
        }


        return pets;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Pet findPetById(int id) {

        final String sql = BASE_PET_SELECT + "WHERE pet_id = ?;";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            try (ResultSet results = stmt.executeQuery()) {

                if (results.next()) {
                    return mapRowToPet(results);
                }
            }

        } catch (SQLException sqlex) {
            throw new RuntimeException(
                    "Error communicating with Database: "
                            + sqlex.getErrorCode()
            );
        }

        return null;
    }

    /**
     * @param pet
     * @return
     */
    @Override
    public Pet add(Pet pet) {

        final String sql =
                "INSERT INTO pet (name, type) VALUES (?, ?);";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        sql,
                        Statement.RETURN_GENERATED_KEYS)
        ) {

            stmt.setString(1, pet.getName());
            stmt.setString(2, pet.getType());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected <= 0) {
                return null;
            }

            try (ResultSet keys = stmt.getGeneratedKeys()) {

                if (keys.next()) {
                    pet.setId(keys.getInt(1));
                }
            }

            return pet;

        } catch (SQLException sqlex) {
            throw new RuntimeException(
                    "Error Communication with Database: "
                            + sqlex.getErrorCode()
            );
        }
    }

    /**
     * @param pet
     * @return
     */
    @Override
    public Boolean update(Pet pet) {

        final String sql =
                "UPDATE pet SET name = ?, type = ? WHERE pet_id = ?;";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, pet.getName());
            stmt.setString(2, pet.getType());
            stmt.setInt(3, pet.getId());

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException sqlex) {
            throw new RuntimeException(
                    "Error Communication with Database: "
                            + sqlex.getErrorCode()
            );
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean deleteById(int id) {

        final String sql =
                "DELETE FROM pet WHERE pet_id = ?;";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException sqlex) {
            throw new RuntimeException(
                    "Error Communication with Database: "
                            + sqlex.getErrorCode()
            );
        }
    }


private Pet mapRowToPet(ResultSet row) throws SQLException {

    Pet pet = new Pet();

    pet.setId(row.getInt("pet_id"));
    pet.setName(row.getString("name"));
    pet.setType(row.getString("type"));

    return pet;
}

}