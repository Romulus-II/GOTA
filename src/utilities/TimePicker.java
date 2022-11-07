/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author G-sta
 */
public class TimePicker extends HBox {
    
    private ChoiceBox hourInput, minuteInput;
    
    public TimePicker() {
        final int CB_WIDTH = 50;
        hourInput = new ChoiceBox();
        for (int i = 0; i <= 10; i++) {
            hourInput.getItems().add(i);
        }
        minuteInput = new ChoiceBox();
        for (int i = 0; i < 60; i++) {
            minuteInput.getItems().add(i);
        }
        
        Label separator = new Label(":");
        
        this.getChildren().addAll(hourInput, separator, minuteInput);
        this.setWidth(hourInput.getWidth() + separator.getWidth() + minuteInput.getWidth());
    }
    
    public String getValue() {
        String hours = hourInput.getValue().toString();
        String minutes = minuteInput.getValue().toString();
        if (minutes.length() < 2) minutes = "0" + minutes;
        return hours + ":" + minutes;
    }
    
}
