package lv.tsi.student.model;

/**
 * Custom exception class for handling duplicate records.
 */
public class DuplicateRecordException extends Exception {
    public DuplicateRecordException(String message) {
        super(message);
    }
}
