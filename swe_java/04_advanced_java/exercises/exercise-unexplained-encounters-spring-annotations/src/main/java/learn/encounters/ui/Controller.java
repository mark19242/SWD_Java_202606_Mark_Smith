package learn.encounters.ui;

import learn.encounters.data.DataException;
import learn.encounters.domain.EncounterResult;
import learn.encounters.domain.EncounterService;
import learn.encounters.models.Encounter;
import learn.encounters.models.EncounterType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Controller {

    private final View view;
    private final EncounterService service;

    public Controller(View view, EncounterService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        view.displayHeader("Welcome to Unexplained Encounters");
        try {
            runAppLoop();
        } catch (DataException ex) {
            view.displayErrors(List.of(ex.getMessage()));
        }
        view.displayMessage("Goodbye.");
    }

    private void runAppLoop() throws DataException {
        MenuOption option;
        do {
            option = view.chooseMenuOption();
            switch (option) {
                case VIEW_BY_TYPE:
                    viewByType();
                    break;
                case ADD:
                    addEncounter();
                    break;
                case UPDATE:
                    updateEncounter();
                    break;
                case DELETE:
                    deleteEncounter();
                    break;
                case EXIT:
                    break;
            }
        } while (option != MenuOption.EXIT);
    }

    private void addEncounter() throws DataException {
        Encounter encounter = view.makeEncounter();
        EncounterResult result = service.add(encounter);
        if (result.isSuccess()) {
            view.displayMessage("Encounter #" + result.getEncounter().getEncounterId() + " added.");
        } else {
            view.displayErrors(result.getErrorMessages());
        }
    }

    private void viewByType() throws DataException {
        EncounterType type = view.chooseEncounterType();
        List<Encounter> encounters = service.findByType(type);
        view.displayEncounters(encounters);
    }

    private void updateEncounter() throws DataException {
        EncounterType type = view.chooseEncounterType();
        List<Encounter> encounters = service.findByType(type);
        Encounter encounter = view.chooseEncounter(encounters);
        if (encounter == null) {
            return;
        }
        encounter = view.editEncounter(encounter);
        EncounterResult result = service.update(encounter);
        if (result.isSuccess()) {
            view.displayMessage("Encounter #" + result.getEncounter().getEncounterId() + " updated.");
        } else {
            view.displayErrors(result.getErrorMessages());
        }
    }

    private void deleteEncounter() throws DataException {
        EncounterType type = view.chooseEncounterType();
        List<Encounter> encounters = service.findByType(type);
        Encounter encounter = view.chooseEncounter(encounters);
        if (encounter == null) {
            return;
        }
        EncounterResult result = service.deleteById(encounter.getEncounterId());
        if (result.isSuccess()) {
            view.displayMessage("Encounter #" + encounter.getEncounterId() + " deleted.");
        } else {
            view.displayErrors(result.getErrorMessages());
        }
    }
}
