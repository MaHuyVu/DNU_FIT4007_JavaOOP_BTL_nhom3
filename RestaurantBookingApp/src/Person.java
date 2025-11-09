package model;

import java.io.Serializable;
import java.util.UUID;

public abstract class Person implements Serializable {
    protected String id;
    protected String name;
    protected String phone;

    public Person(String name, String phone) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s", id, name, phone);
    }
}