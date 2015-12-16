package com.guigarage.data;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Country {

    private List<String> towns;

    public Country() {
        this.towns = new CopyOnWriteArrayList<>();
    }

    public List<String> getTowns() {
        return towns;
    }
}
