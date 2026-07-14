package learn.encounters.data;

import learn.encounters.models.Encounter;
import learn.encounters.models.EncounterType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EncounterFileRepository implements EncounterRepository {

    private final String filePath;
    private static final String DELIMITER = ",";
    private static final int FIELD_COUNT = 5;

    public EncounterFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Encounter> findAll() throws DataException {
        ArrayList<Encounter> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                Encounter encounter = lineToEncounter(line);
                if (encounter != null) {
                    result.add(encounter);
                }
            }
        } catch (FileNotFoundException ex) {
            // No file yet just means no encounters yet. It is created on the first add.
        } catch (IOException ex) {
            throw new DataException("Could not open the file path: " + filePath, ex);
        }
        return result;
    }

    @Override
    public Encounter findById(int encounterId) throws DataException {
        for (Encounter encounter : findAll()) {
            if (encounter.getEncounterId() == encounterId) {
                return encounter;
            }
        }
        return null;
    }

    @Override
    public List<Encounter> findByType(EncounterType type) throws DataException {

        // This list will store every encounter that matches the requested type.
        List<Encounter> result = new ArrayList<>();

        // Check each encounter currently stored in the file.
        for (Encounter encounter : findAll()) {

            if (encounter.getType() == type) {
                result.add(encounter);
            }
        }

        return result;
    }

    @Override
    public Encounter add(Encounter encounter) throws DataException {
        List<Encounter> all = findAll();
        int nextId = getNextId(all);
        encounter.setEncounterId(nextId);
        all.add(encounter);
        writeToFile(all);
        return encounter;
    }

    @Override
    public boolean update(Encounter encounter) throws DataException {

        List<Encounter> all = findAll();

        for (int i = 0; i < all.size(); i++) {

            // Check whether the encounter at this index has the matching ID.
            if (all.get(i).getEncounterId() == encounter.getEncounterId()) {

                // Replace the old encounter with the updated one.
                all.set(i, encounter);

                // Save the updated list back to the file.
                writeToFile(all);

                return true;
            }
        }

        // No encounter with the matching ID was found.
        return false;
    }

    @Override
    public boolean deleteById(int encounterId) throws DataException {
        List<Encounter> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getEncounterId() == encounterId) {
                all.remove(i);
                writeToFile(all);
                return true;
            }
        }
        return false;
    }

    private int getNextId(List<Encounter> encounters) {
        int maxId = 0;
        for (Encounter encounter : encounters) {
            if (maxId < encounter.getEncounterId()) {
                maxId = encounter.getEncounterId();
            }
        }
        return maxId + 1;
    }

    private void writeToFile(List<Encounter> encounters) throws DataException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (Encounter encounter : encounters) {
                writer.println(encounterToLine(encounter));
            }
        } catch (IOException ex) {
            throw new DataException("Could not write to file path: " + filePath, ex);
        }
    }

    private Encounter lineToEncounter(String line) {
        String[] fields = line.split(DELIMITER);
        if (fields.length != FIELD_COUNT) {
            return null;
        }
        return new Encounter(
                Integer.parseInt(fields[0]),
                EncounterType.valueOf(fields[1]),
                LocalDate.parse(fields[2]),
                fields[3],
                Integer.parseInt(fields[4])
        );
    }

    private String encounterToLine(Encounter encounter) {
        StringBuilder buffer = new StringBuilder(100);
        buffer.append(encounter.getEncounterId()).append(DELIMITER);
        buffer.append(encounter.getType()).append(DELIMITER);
        buffer.append(encounter.getWhen()).append(DELIMITER);
        buffer.append(cleanField(encounter.getDescription())).append(DELIMITER);
        buffer.append(encounter.getOccurrences());
        return buffer.toString();
    }

    private String cleanField(String field) {
        if (field == null) {
            return "";
        }
        // The comma is our delimiter, and carriage returns / newlines would break a row,
        // so we scrub them from any field before it is written to the file.
        return field.replace(DELIMITER, " ")
                .replace("\r", "")
                .replace("\n", "");
    }
}
