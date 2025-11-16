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

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Name: " + name + " | Phone: " + phone;
    }

    public abstract void setId(String generate);

    public abstract Object getId();

    public abstract String getEmail() ;


    public abstract char[] isVip() ;
}
