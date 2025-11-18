
package model;

// ============================
//  STANDARD TABLE
// ============================
public class StandardTable extends Table {

    public StandardTable(String number, int capacity) {
        super(number, capacity);
        this.type = "STANDARD";
        this.surcharge = 0;
    }

    @Override
    public String toString() {
        return String.format("Table[%s] #%d | STANDARD | Capacity: %d | Status: %s",
                id, number, capacity, status);
    }
}