package ru.app.summerlabsday6part3.Variant14;

import java.util.ArrayList;

public class Dachshund extends Dog{
    private String color;

    public Dachshund(String name, ArrayList<String> accessories, String color) {
        super(name, accessories);
        this.color = color;
    }
}
