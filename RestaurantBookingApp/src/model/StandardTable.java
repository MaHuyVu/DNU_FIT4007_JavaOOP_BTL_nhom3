package model;

public class StandardTable extends Table {

    public StandardTable(int i, int seats) {
        super(p[0], "STANDARD", seats, 0); // bàn thường không có phụ phí
    }

    @Override
    public String toString() {
        return super.toString() + " | Surcharge: 0₫";
    }
}
