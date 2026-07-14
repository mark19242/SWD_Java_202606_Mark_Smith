package learn.encounters;

import learn.encounters.data.EncounterFileRepository;
import learn.encounters.data.EncounterRepository;
import learn.encounters.domain.EncounterService;
import learn.encounters.ui.Controller;
import learn.encounters.ui.View;

public class App {

    public static void main(String[] args) {

        // Build the application's dependency chain.
        EncounterRepository repository =
                new EncounterFileRepository("./data/encounters.csv");

        EncounterService service = new EncounterService(repository);

        View view = new View();

        Controller controller = new Controller(view, service);

        // Start the application.
        controller.run();
    }
}