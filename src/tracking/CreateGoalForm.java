/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tracking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uicomponents.GoalContainer;
import utilities.TimePicker;

/**
 *
 * @author G-sta
 */
public class CreateGoalForm extends Stage {
    
    private final GoalContainer goalContainer;
    
    private final TextField goalObjTextField;
    private final ChoiceBox goalTypeChoiceBox;
    private final VBox subgoalArea;
    private final TimePicker goalTimePicker;
    
    @SuppressWarnings("Convert2Lambda")
    public CreateGoalForm(GoalContainer goalContainer) {
        this.goalContainer = goalContainer;
        
        int WIDTH = 300;
        int HEIGHT = 500;
        
        VBox root = new VBox();
        root.setPrefSize(WIDTH, HEIGHT);
        
        Label header = new Label("New Goal");
        
        VBox formSection1 = new VBox();
        formSection1.setPrefWidth(WIDTH);
        Label goalObjLabel = new Label("Goal Objective");
        Text goalObjDesc = new Text("A brief description of your goal"); 
        goalObjTextField = new TextField();
        formSection1.getChildren().addAll(goalObjLabel, goalObjDesc,goalObjTextField);
        
        VBox formSection2 = new VBox();
        formSection2.setPrefWidth(WIDTH);
        Label goalTypeLabel = new Label("Goal Type");
        goalTypeChoiceBox = new ChoiceBox();
        
        subgoalArea = new VBox();
        subgoalArea.setPrefWidth(WIDTH);
        final ArrayList<TextField> subgoalTextFields = new ArrayList<>();
            
        goalTimePicker = new TimePicker();
        
        // Fetch descriptions for goal from static file
        Text goalTypeDesc = new Text("");
        goalTypeDesc.setWrappingWidth(WIDTH);
        ArrayList<String> goalDescriptions = new ArrayList<>();
        try {
            File file = new File("data/goal-descriptions.txt");
            BufferedReader reader;
            String text = "";
            reader = new BufferedReader(new FileReader(file));
            while((text = reader.readLine()) != null) {
                if (!text.contains("::") && !text.equals("")) goalDescriptions.add(text);
                else if (text.contains("::")) goalTypeChoiceBox.getItems().add(text.substring(0,text.length()-2));
            }
            reader.close();
            System.out.println("Read in goals from description file: " + goalDescriptions.size());
        } catch (FileNotFoundException ex) {
            System.out.println("Goal description file missing: \n" + ex.toString());
        } catch (IOException ex) {
            System.out.println("Unable to read goal descriptions from data file: \n" + ex.toString());
            Logger.getLogger(CreateGoalForm.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        goalTypeChoiceBox.getSelectionModel().select(0);
        goalTypeChoiceBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (formSection2.getChildren().contains(subgoalArea)) {
                    formSection2.getChildren().remove(subgoalArea);
                } else if (formSection2.getChildren().contains(goalTimePicker)) {
                    formSection2.getChildren().remove(goalTimePicker);
                }
                int selectionIndex = goalTypeChoiceBox.getSelectionModel().getSelectedIndex();
                switch (selectionIndex) {
                    case 0 -> goalTypeDesc.setText(goalDescriptions.get(0));
                    case 1 -> {
                        if (subgoalArea.getChildren().size() < 2) {
                            goalTypeDesc.setText(goalDescriptions.get(1));

                            HBox subgoalContent = new HBox();
                            TextField subgoalObjTextField = new TextField();
                            subgoalTextFields.add(subgoalObjTextField);
                            Button subgoalDeleteBtn = new Button("X");
                            subgoalContent.getChildren().addAll(subgoalObjTextField, subgoalDeleteBtn);
                            subgoalDeleteBtn.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent t) {
                                    subgoalTextFields.remove(subgoalObjTextField);
                                    subgoalArea.getChildren().remove(subgoalContent);
                                }

                            });

                            Button createSubgoalBtn = new Button("Add Subgoal");
                            createSubgoalBtn.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent t) {
                                    HBox subgoalContent = new HBox();
                                    TextField subgoalObjTextField = new TextField();
                                    subgoalTextFields.add(subgoalObjTextField);
                                    Button subgoalDeleteBtn = new Button("X");
                                    subgoalContent.getChildren().addAll(subgoalObjTextField, subgoalDeleteBtn);
                                    subgoalDeleteBtn.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent t) {
                                            subgoalTextFields.remove(subgoalObjTextField);
                                            subgoalArea.getChildren().remove(subgoalContent);
                                        }
                                    });
                                    subgoalArea.getChildren().add(subgoalTextFields.size()-1,subgoalContent);
                                }
                            });
                            subgoalArea.getChildren().addAll(subgoalContent, createSubgoalBtn);
                        }
                        formSection2.getChildren().add(3,subgoalArea);
                    }
                    case 2 -> {
                        formSection2.getChildren().add(3,goalTimePicker);
                        goalTypeDesc.setText(goalDescriptions.get(2));
                    }
                    case 3 -> goalTypeDesc.setText(goalDescriptions.get(3));
                    default -> System.out.println("Unable to set goal description");
                }
            }
        });
        
        formSection2.getChildren().addAll(goalTypeLabel, goalTypeChoiceBox, goalTypeDesc);
        
        
        HBox formSection3 = new HBox();
        formSection3.setPrefWidth(WIDTH);
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction((ActionEvent event) -> {
            this.close();
        });
        Button submitButton = new Button("Create");
        submitButton.setOnAction((ActionEvent event) -> {
            Goal goal = generateGoal();
            goalContainer.addGoal(goal);
        });
        formSection3.getChildren().addAll(cancelButton, submitButton);
        
        root.getChildren().addAll(header,formSection1,formSection2, formSection3);
        Scene scene = new Scene(root);
        
        this.setScene(scene);
        this.setAlwaysOnTop(true);
        this.initModality(Modality.APPLICATION_MODAL);
        this.show();
    }
    
    private Goal generateGoal() {
        String objective = goalObjTextField.getText();
        String type = goalTypeChoiceBox.getValue().toString().toLowerCase();
        if (type.equals("multi-step")) {
            ArrayList<SubGoal> subgoals = new ArrayList<>();
            for (int i = 0; i < subgoalArea.getChildren().size()-1; i++) {
                HBox item = (HBox) subgoalArea.getChildren().get(i);
                TextField subgoalObjTextField = (TextField) item.getChildren().get(0);
                String subgoalObj = subgoalObjTextField.getText().toString();
                if (subgoalObj.equals("")) subgoals.add(new SubGoal(subgoalObj));
            }
            if (subgoals.size() == 0) return new Goal(objective, "completion");
            return new Goal(objective, type, subgoals);
        } else if (type.equals("time-reliant")) {
            String time = goalTimePicker.getValue();
            return new Goal(objective, type, time);
        } else if (type.equals("progressional")) {
            return new Goal(objective, type, true);
        } else {
            return new Goal(objective, type);
        }
    }
}
