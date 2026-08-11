package learn.pets;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

import learn.pets.dao.JdbcPetDao;
import learn.pets.dao.PetDao;
import learn.pets.models.Pet;
import org.apache.commons.dbcp2.BasicDataSource;

public class App {

    static void main(String[] args) {

        DataSource dataSource = initDataSource();
        PetDao petDao = new JdbcPetDao(dataSource);

        System.out.println("================");
        System.out.println("    PET LIST");
        System.out.println("================");

        for(Pet p : petDao.findAll()){

            System.out.println(p.toString());

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
