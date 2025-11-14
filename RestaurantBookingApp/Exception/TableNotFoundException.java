package Exception;

public class TableNotFoundException extends RuntimeException {
    public TableNotFoundException(String tableId) {
        super("Không tìm thấy bàn với ID: " + tableId);
    }
}