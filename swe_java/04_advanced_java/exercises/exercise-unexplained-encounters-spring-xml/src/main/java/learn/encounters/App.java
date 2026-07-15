package learn.encounters;

;
import learn.encounters.ui.Controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        // The app currently wires itself by hand. Your task is to move this wiring
        // into Spring's XML DI container.
        //
        // Load the Spring container using the XML configuration file.
        ApplicationContext container =
                new ClassPathXmlApplicationContext("di-config.xml");

        // Spring returns a Controller with all dependencies already injected.
        Controller controller = container.getBean(Controller.class);

        controller.run();

//        EncounterRepository repository = new EncounterFileRepository("./data/encounters.csv");
//        EncounterService service = new EncounterService(repository);
//        View view = new View();
//        Controller controller = new Controller(view, service);
//        controller.run();
    }
}
