package model;

import java.io.Serializable;

public abstract class Person implements Serializable {
    protected String name;
    protected String phone;

    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Name: " + name + " | Phone: " + phone;
    }


    public abstract void setId(String id);
    public abstract String getId();
    public abstract String getEmail();
    public abstract boolean isVip();
}