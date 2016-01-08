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

/**
 * The Controller class
 */
@DolphinController(Constants.CONTROLLER_NAME)
public class MyController {

    /**
     * Some dummy data
     */
    private static final String[] CATEGORIES = {"Banana", "Apple", "Cherry", "Raspberry"};

    /**
     * Some dummy data
     */
    private static final String[] COUNTRIES = {"Germany", "England", "Spain", "Sweden"};

    /**
     * The Model
     */
    @DolphinModel
    private MyModel model;

    /**
     * The DOlphin Platform bean manager to manage the model
     */
    @Inject
    private BeanManager beanManager;

    /**
     * This method is called automatically whenever a new controller instance has been created
     */
    @PostConstruct
    public void init() {

        //Create initial data
        for (String category : CATEGORIES) {
            ChartData data = beanManager.create(ChartData.class);
            data.setCategory(category);
            model.getChartData().add(data);
        }
        for(String country : COUNTRIES) {
            model.getCountries().add(country);
        }

        //Add selection lister and define initial selection
        model.selectedCountryProperty().onChanged(e -> update());
        model.setSelectedCountry(COUNTRIES[0]);
    }

    /**
     * Updates the data
     */
    private void update() {

        //Update the data
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
