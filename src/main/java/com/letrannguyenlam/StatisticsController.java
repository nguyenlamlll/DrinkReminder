package com.letrannguyenlam;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {
    @FXML
    private BarChart<String, Double> waterBarChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series<String, Double> series1 = new XYChart.Series<String, Double>();
        series1.setName("Liters Taken");
        series1.getData().add(new XYChart.Data<String, Double>("01/06/2019", 3.4));
        series1.getData().add(new XYChart.Data<String, Double>("02/06/2019", 3.0));
        series1.getData().add(new XYChart.Data<String, Double>("03/06/2019", 5.0));
        series1.getData().add(new XYChart.Data<String, Double>("04/06/2019", 2.75));
        series1.getData().add(new XYChart.Data<String, Double>("05/06/2019", 0.75));
        series1.getData().add(new XYChart.Data<String, Double>("06/06/2019", 4.));
        waterBarChart.getData().add(series1);
    }
}
