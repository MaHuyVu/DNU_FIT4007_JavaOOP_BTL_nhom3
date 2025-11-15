package Exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(String bookingId) {
        super("Không tìm thấy đặt bàn với ID: " + bookingId);
    }
}
