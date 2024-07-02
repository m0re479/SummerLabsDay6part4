package ru.app.summerlabsday6part3.Variant14;

import java.util.ArrayList;

public class Hound extends Dog{
    private int number_of_hunts;//количество охот, в которых участвовала гончая

    public Hound(String name, ArrayList<String> accessories, int number_of_hunts) {
        super(name, accessories);
        this.number_of_hunts = number_of_hunts;
    }
}
