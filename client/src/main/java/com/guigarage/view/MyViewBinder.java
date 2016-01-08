package com.guigarage.view;

import com.canoo.dolphin.client.ClientContext;
import com.canoo.dolphin.client.javafx.AbstractViewBinder;
import com.canoo.dolphin.client.javafx.Converter;
import com.canoo.dolphin.client.javafx.FXBinder;
import com.guigarage.Constants;
import com.guigarage.model.ChartData;
import com.guigarage.model.MyModel;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;

public class MyViewBinder extends AbstractViewBinder<MyModel> {

    @FXML
    private ChoiceBox<String> countrySelection;

    @FXML
    private BarChart<String, Integer> chart;

    public MyViewBinder(ClientContext clientContext) {
        super(clientContext, Constants.CONTROLLER_NAME);
    }

    @Override
    protected void init() {
        try {

            //Configure UI
            XYChart.Series<String, Integer> townCountSeries = new XYChart.Series<>();
            chart.dataProperty().get().add(townCountSeries);
            chart.setLegendVisible(false);

            //Bind UI to Dolphin Platform Model
            FXBinder.bind(townCountSeries.getData()).to(getModel().getChartData(), new DataConverter());
            FXBinder.bind(countrySelection.valueProperty()).bidirectionalTo(getModel().selectedCountryProperty());
            FXBinder.bind(countrySelection.getItems()).to(getModel().getCountries());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class DataConverter implements Converter<ChartData, XYChart.Data<String, Integer>> {

        @Override
        public XYChart.Data<String, Integer> convert(ChartData value) {
            XYChart.Data<String, Integer> data = new XYChart.Data(value.getCategory(), value.getValue());
            value.categoryProperty().onChanged(e -> data.setXValue(value.getCategory()));
            value.valueProperty().onChanged(e -> data.setYValue(value.getValue()));
            return data;
        }
    }

}
