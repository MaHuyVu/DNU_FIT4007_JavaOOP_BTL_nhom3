package model;

public enum TableStatus {
    AVAILABLE("Trống"),
    BOOKED("Đã đặt"),
    OCCUPIED("Đang dùng"),
    RESERVED("Đã đặt trước");

    private final String displayName;

    TableStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}