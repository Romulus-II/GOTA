/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uicomponents;

import java.util.ArrayList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import tracking.Goal;
import tracking.GoalDisplay;

/**
 *
 * @author G-sta
 */
public class GoalContainer extends ScrollPane {
    
    private final VBox root;
    private final ArrayList<Goal> GOALS;
    
    public GoalContainer(double appWidth, double appHeight) {
        super();
        root = new VBox();
        this.setContent(root);
        root.setPrefWidth(appWidth-15);
        root.setMinHeight(appHeight-115);
        this.setWidth(appWidth);
        this.setHeight(appHeight-100);
        root.prefWidthProperty().bind(this.widthProperty().subtract(15));
        root.minHeightProperty().bind(this.prefHeightProperty().subtract(15));
        
        GOALS = new ArrayList<>();
        System.out.println("VBox Dimensions (" + root.getPrefWidth() + "," + root.getMinHeight() + ")");
    }
    
    public void addGoal(Goal goal) {
        GOALS.add(goal);
        GoalDisplay goalDisplay = new GoalDisplay(goal);
        goalDisplay.setPrefWidth(root.getPrefWidth());
        goalDisplay.prefWidthProperty().bind(root.prefWidthProperty());
        root.getChildren().add(goalDisplay);
    }
}
