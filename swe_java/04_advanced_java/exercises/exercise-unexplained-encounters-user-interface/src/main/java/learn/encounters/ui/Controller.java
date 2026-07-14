package learn.encounters.ui;

import learn.encounters.data.DataException;
import learn.encounters.domain.EncounterResult;
import learn.encounters.domain.EncounterService;
import learn.encounters.models.Encounter;
import learn.encounters.models.EncounterType;

import java.util.List;

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

        // Ask the user which type of encounter they want to view.
        EncounterType type = view.chooseEncounterType();

        // Retrieve encounters matching the selected type.
        List<Encounter> encounters = service.findByType(type);

        // The View handles displaying the encounters or the empty-list message.
        view.displayEncounters(encounters);
    }

    private void updateEncounter() throws DataException {

        // Narrow the choices by encounter type.
        EncounterType type = view.chooseEncounterType();
        List<Encounter> encounters = service.findByType(type);

        // Allow the user to select an encounter from the matching list.
        Encounter encounter = view.chooseEncounter(encounters);

        // Nothing can be updated if the list was empty or the id was invalid.
        if (encounter == null) {
            return;
        }

        // Collect the updated values from the user.
        Encounter updatedEncounter = view.editEncounter(encounter);

        // Let the service validate and update the encounter.
        EncounterResult result = service.update(updatedEncounter);

        if (result.isSuccess()) {
            view.displayMessage(
                    "Encounter #" + result.getEncounter().getEncounterId()
                            + " updated.");
        } else {
            view.displayErrors(result.getErrorMessages());
        }
    }

    private void deleteEncounter() throws DataException {

        // Narrow the choices by encounter type.
        EncounterType type = view.chooseEncounterType();
        List<Encounter> encounters = service.findByType(type);

        // Allow the user to select an encounter from the matching list.
        Encounter encounter = view.chooseEncounter(encounters);

        // Nothing can be deleted if the list was empty or the id was invalid.
        if (encounter == null) {
            return;
        }

        // Delete the selected encounter through the service.
        EncounterResult result =
                service.deleteById(encounter.getEncounterId());

        if (result.isSuccess()) {
            view.displayMessage(
                    "Encounter #" + encounter.getEncounterId() + " deleted.");
        } else {
            view.displayErrors(result.getErrorMessages());
        }
    }
}
