package model;

public class VipTable extends Table {
    public VipTable(int capacity) { super(capacity); }

    @Override
    public double getSurcharge() {
        return 200000; // phụ phí cố định 200k
    }
}
