package lv.tsi.student.repository;

import lv.tsi.student.model.DuplicateRecordException;
import lv.tsi.student.model.Identity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Generic repository for managing records and saving/loading them to/from a file.
 *
 * @param <T> The type of records to be stored.
 */
public abstract class Repository<T extends Identity> {

    private static final Logger log = LogManager.getLogger(Repository.class);

    private List<T> recordList = new ArrayList<>();

    /**
     * Return a copy of all records.
     *
     * @return A list containing all records.
     */
    public List<T> list() {
        return new ArrayList<>(recordList);
    }

    public Optional<T> get(String id) {
        return recordList.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    public abstract List<T> findBy(String text);

    public void add(T record) throws DuplicateRecordException {
        Optional<T> r = get(record.getId());
        if (r.isPresent()) {
            throw new DuplicateRecordException("Record already exists: " + record);
        }

        recordList.add(record);
    }

    public void delete(T record) {
        String id = record.getId();
        Optional<T> r = get(id);
        r.ifPresent(t -> recordList.remove(t));
    }

    /**
     * Saves the list of records to a file.
     *
     * @param fileName The name of the file to save the records.
     */
    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(recordList);
            log.info("Record list saved to file: {}", fileName);
        } catch (IOException e) {
            log.error("Error saving record list to file: {}", e.getMessage());
        }
    }

    /**
     * Loads records from a file into the list.
     *
     * @param fileName The name of the file to load records from.
     */
    public void loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            recordList = (List<T>) ois.readObject();
            log.info("Record list loaded from file: {}", fileName);
        } catch (IOException | ClassNotFoundException e) {
            log.error("Error loading record list from file: {}", e.getMessage());
        }
    }


}
