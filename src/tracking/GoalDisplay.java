/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tracking;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author G-sta
 */
public class GoalDisplay extends VBox {
    
    private Goal goal;
    
    public GoalDisplay(Goal goal) {
        this.goal = goal;
        
        Label objective = new Label(goal.getObjective());
        
        if (goal.getType().equals("completion")) {
            HBox content = new HBox();
            content.setSpacing(5);
            CheckBox completionBtn = new CheckBox();
            content.getChildren().addAll(completionBtn, objective);
            this.getChildren().add(content);
        }
        //---Remove this in final product---------------------------------------
        this.setBorder(Border.stroke(Color.BLACK));
        //----------------------------------------------------------------------
        this.setPadding(new Insets(10,10,10,10));
        this.setStyle("border: 5px solid black");
    }
}
