package Exception;

public class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super("Đặt bàn không hợp lệ: " + message);
    }
}
