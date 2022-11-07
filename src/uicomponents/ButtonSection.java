/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uicomponents;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import tracking.CreateGoalForm;

/**
 *
 * @author G-sta
 */
public class ButtonSection extends HBox {
    
    private final GoalContainer container;
    
    private final double width = 200, height = 50;
    
    public ButtonSection(GoalContainer container) {
        super();
        this.setAlignment(Pos.CENTER);
        this.setMinWidth(width);
        this.setWidth(width);
        this.setHeight(height);
        this.container = container;
        System.out.println("HBox height: " + this.getHeight());
        
        Button createGoalBtn = new Button("Create Goal");
        createGoalBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) { createGoal(); }
        });
        this.getChildren().add(createGoalBtn);
    }
    
    private void createGoal() {
        CreateGoalForm goalForm = new CreateGoalForm(container);
    }
}
