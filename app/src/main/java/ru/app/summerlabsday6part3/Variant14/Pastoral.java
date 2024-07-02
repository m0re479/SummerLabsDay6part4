package ru.app.summerlabsday6part3.Variant14;

import java.util.ArrayList;

public class Pastoral extends Dog{
    private String name_of_the_herd;//название стада

    public Pastoral(String name, ArrayList<String> accessories, String name_of_the_herd) {
        super(name, accessories);
        this.name_of_the_herd = name_of_the_herd;
    }
}
