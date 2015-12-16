package com.guigarage.model;

import com.canoo.dolphin.mapping.Property;

public class ChartData {

    private Property<String> category;

    private Property<Integer> value;

    public Property<String> categoryProperty() {
        return category;
    }

    public String getCategory() {
        return categoryProperty().get();
    }

    public void setCategory(String category) {
        categoryProperty().set(category);
    }

    public Property<Integer> valueProperty() {
        return value;
    }

    public int getValue() {
        if(valueProperty().get() == null) {
            return 0;
        }
        return valueProperty().get();
    }

    public void setValue(int value) {
        valueProperty().set(value);
    }
}
