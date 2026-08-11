package learn.pets;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

import learn.pets.dao.JdbcPetDao;
import learn.pets.dao.PetDao;
import learn.pets.models.Pet;
import org.apache.commons.dbcp2.BasicDataSource;

public class App {

    public static void main(String[] args) {

        DataSource dataSource = initDataSource();
        PetDao petDao = new JdbcPetDao(dataSource);

        // 1. FIND ALL
        System.out.println("\n*** FIND ALL ***");

        for (Pet pet : petDao.findAll()) {
            System.out.println(pet);
        }


        // 2. FIND BY ID
        System.out.println("\n*** FIND PET BY ID ***");

        Pet foundPet = petDao.findPetById(1);
        System.out.println(foundPet);


        // 3. ADD
        System.out.println("\n*** ADD PET ***");

        Pet newPet = new Pet();
        newPet.setName("Buddy");
        newPet.setType("Dog");

        Pet addedPet = petDao.add(newPet);
        System.out.println("Added: " + addedPet);


        // 4. UPDATE
        System.out.println("\n*** UPDATE PET ***");

        addedPet.setName("Buddy Updated");
        addedPet.setType("Golden Retriever");

        Boolean updated = petDao.update(addedPet);

        System.out.println("Updated: " + updated);
        System.out.println("Pet after update: "
                + petDao.findPetById(addedPet.getId()));


        // 5. DELETE
        System.out.println("\n*** DELETE PET ***");

        Boolean deleted = petDao.deleteById(addedPet.getId());

        System.out.println("Deleted: " + deleted);


        // Final list
        System.out.println("\n*** FINAL PET LIST ***");

        for (Pet pet : petDao.findAll()) {
            System.out.println(pet);
        }
    }


    private static DataSource initDataSource(){

        Properties props = new Properties();

        try(InputStream in =
                    App.class.getClassLoader().getResourceAsStream("application.properties")){

            props.load(in);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(props.getProperty("db.driver"));
        bds.setUrl(props.getProperty("db.url"));
        bds.setUsername( props.getProperty("db.username"));
        bds.setPassword( props.getProperty("db.password"));

        return bds;
    }
}
