package Exception;

public class TableAlreadyBookedException extends RuntimeException {
    public TableAlreadyBookedException(String message) {
        super(message);
    }
}
