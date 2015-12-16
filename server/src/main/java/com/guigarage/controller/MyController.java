package com.guigarage.controller;

import com.canoo.dolphin.BeanManager;
import com.canoo.dolphin.server.DolphinController;
import com.canoo.dolphin.server.DolphinModel;
import com.guigarage.Constants;
import com.guigarage.model.ChartData;
import com.guigarage.model.MyModel;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Random;

@DolphinController(Constants.CONTROLLER_NAME)
public class MyController {

    private static final String[] CATEGORIES = {"Banana", "Apple", "Cherry", "Raspberry"};

    private static final String[] COUNTRIES = {"Germany", "England", "Spain", "Sweden"};

    @DolphinModel
    private MyModel model;

    @Inject
    private BeanManager beanManager;

    @PostConstruct
    public void init() {
        model.selectedCountryProperty().onChanged(e -> update());
        for (String category : CATEGORIES) {
            ChartData data = beanManager.create(ChartData.class);
            data.setCategory(category);
            model.getChartData().add(data);
        }
        for(String country : COUNTRIES) {
            model.getCountries().add(country);
        }
        model.setSelectedCountry(COUNTRIES[0]);
    }

    private void update() {
        Random random = new Random(System.currentTimeMillis());
        for (String category : CATEGORIES) {
            for (ChartData data : model.getChartData()) {
                if (data.getCategory().equals(category)) {
                    data.setValue(random.nextInt(1000));
                }
            }
        }
    }
}
