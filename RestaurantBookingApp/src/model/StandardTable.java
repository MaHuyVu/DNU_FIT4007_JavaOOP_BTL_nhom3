package model;
public class StandardTable extends Table {
    public StandardTable(int capacity) { super(capacity); }
    @Override
    public double getSurcharge() {
        return 0; // không phụ phí
    }
}
