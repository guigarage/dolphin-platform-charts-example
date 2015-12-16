package com.guigarage.model;

import com.canoo.dolphin.collections.ObservableList;
import com.canoo.dolphin.mapping.DolphinBean;
import com.canoo.dolphin.mapping.Property;

@DolphinBean
public class MyModel {

    private ObservableList<String> countries;

    private Property<String> selectedCountry;

    private ObservableList<ChartData> chartData;

    public ObservableList<String> getCountries() {
        return countries;
    }

    public Property<String> selectedCountryProperty() {
        return selectedCountry;
    }

    public String getSelectedCountry() {
        return selectedCountryProperty().get();
    }

    public void setSelectedCountry(String selectedCountry) {
        selectedCountryProperty().set(selectedCountry);
    }

    public ObservableList<ChartData> getChartData() {
        return chartData;
    }
}
