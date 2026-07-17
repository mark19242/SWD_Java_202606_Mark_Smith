package learn.encounters;

import learn.encounters.data.EncounterFileRepository;
import learn.encounters.data.EncounterRepository;
import learn.encounters.domain.EncounterService;
import learn.encounters.ui.Controller;
import learn.encounters.ui.View;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class App {

    public static void main(String[] args) {
        // The app wires itself by hand. This time, let Spring do the wiring using
        // ANNOTATIONS (no XML).
        //
        // TODO:
        //   1. Annotate the components with Spring stereotypes:
        //      - EncounterFileRepository -> @Repository, and annotate its constructor
        //        parameter with @Value("${dataFilePath}")
        //      - EncounterService        -> @Service
        //      - View                    -> @Component
        //      - Controller              -> @Component
        //   2. Annotate THIS class with @ComponentScan and
        //      @PropertySource("classpath:application.properties").
        //   3. Fill in src/main/resources/application.properties with:
        //        dataFilePath=./data/encounters.csv
        //   4. Replace the manual wiring below with:
        //        ApplicationContext context =
        //            new AnnotationConfigApplicationContext(App.class);
        //        Controller controller = context.getBean(Controller.class);
        //        controller.run();

        EncounterRepository repository = new EncounterFileRepository("./data/encounters.csv");
        EncounterService service = new EncounterService(repository);
        View view = new View();
        Controller controller = new Controller(view, service);
        controller.run();
    }
}
