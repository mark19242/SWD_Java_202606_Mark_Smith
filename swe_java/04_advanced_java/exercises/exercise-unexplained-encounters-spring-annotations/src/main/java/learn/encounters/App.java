package learn.encounters;

import learn.encounters.ui.Controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import learn.encounters.ui.Controller;
import learn.encounters.ui.View;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class App {

    public static void main(String[] args) {
        // The app wires itself by hand. This time, let Spring do the wiring using
        // ANNOTATIONS (no XML).
        //
        // Spring scans the project and manages the application's dependencies.
        ApplicationContext context =
                new AnnotationConfigApplicationContext(App.class);

        Controller controller = context.getBean(Controller.class);
        controller.run();

//        EncounterRepository repository = new EncounterFileRepository("./data/encounters.csv");
//        EncounterService service = new EncounterService(repository);
//        View view = new View();
//        Controller controller = new Controller(view, service);
//        controller.run();
    }
}
