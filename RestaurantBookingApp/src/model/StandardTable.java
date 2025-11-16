package model;

public class StandardTable extends Table {

    public StandardTable(int i, int seats) {
        super(seats, 0);  // ✅ Sửa: truyền đúng tham số
    }

    @Override
    public String toString() {
        return super.toString() + " | Surcharge: 0đ";
    }
}