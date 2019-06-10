package com.letrannguyenlam.menuitems;

import com.jfoenix.controls.JFXComboBox;
import com.letrannguyenlam.Main;
import com.letrannguyenlam.logic.DrinkRecordLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {
    @FXML
    private BarChart<String, Double> waterBarChart;

    @FXML
    private JFXComboBox<Label> filterComboBox;

    private DrinkRecordLogic drinkRecordLogic;
    public StatisticsController() {
        drinkRecordLogic = new DrinkRecordLogic();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        prepareByDaysChart();

        filterComboBox.getItems().add(new Label("By Days"));
        filterComboBox.getItems().add(new Label("By Weeks"));
        filterComboBox.getItems().add(new Label("By Months"));


    }

    private void prepareByDaysChart() {
        var map = drinkRecordLogic.getStatisticsByDays(Main.currentSignedInUser);


        XYChart.Series<String, Double> series1 = new XYChart.Series<String, Double>();
        series1.setName("Liters Taken");

        for (Map.Entry<String,Double> entry: map.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();

            series1.getData().add(new XYChart.Data<String, Double>(key, value));
        }

        waterBarChart.getData().add(series1);
    }

    private void prepareByWeeksChart() {

    }

    private void prepareByMonthsChart() {

    }

    public void onFilterComboBoxMouseClicked(MouseEvent mouseEvent) {
        String fitlerType = filterComboBox.getValue().getText();
        switch (fitlerType) {
            case "By Days":
                prepareByDaysChart();
                break;
            case "By Weeks":
                prepareByWeeksChart();
                break;
            case "By Months":
                prepareByMonthsChart();
                break;
        }
    }
}
