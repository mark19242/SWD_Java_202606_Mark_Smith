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
        return null;
    }

    /**
     * @param pet
     * @return
     */
    @Override
    public Pet add(Pet pet) {
        return null;
    }

    /**
     * @param pet
     * @return
     */
    @Override
    public Boolean update(Pet pet) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean deleteById(int id) {
        return null;
    }


private Pet mapRowToPet(ResultSet row) throws SQLException {

    Pet pet = new Pet();

    pet.setId(row.getInt("pet_id"));
    pet.setName(row.getString("name"));
    pet.setType(row.getString("type"));

    return pet;
}

}