package ru.app.summerlabsday6part3.Variant14;

import java.util.ArrayList;

public class Dog {
    private String name;
    ArrayList<String> accessories;

    public Dog(String name, ArrayList<String> accessories) {
        this.name = name;
        this.accessories = accessories;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getAccessories() {
        return accessories;
    }
}
