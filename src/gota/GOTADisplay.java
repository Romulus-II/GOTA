/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gota;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uicomponents.ButtonSection;
import uicomponents.GOTAMenuBar;
import uicomponents.GoalContainer;

/**
 *
 * @author G-sta
 */
public class GOTADisplay extends Stage {
    
    private final int HORIZ_OFFSET = 15, VERT_OFFSET = 65;
    private int width, height;
    
    private GraphicsDevice[] monitorDisplays;
    private int activeDisplay;
    
    private final Stage minimizedStage;
    private boolean minimized;
    
    private VBox root;
    private GOTAMenuBar menuBar;
    private GoalContainer goalContainer;
    private ButtonSection buttonSection;
    
    public GOTADisplay(int width, int height) {
        activeDisplay = 0;
        this.width = width;
        this.height = height;
        this.setMinWidth(width);
        this.setWidth(width);
        this.setMinHeight(height);
        this.setHeight(height);
        this.setTitle("GOTA");
        this.setAlwaysOnTop(true);
        
        menuBar = new GOTAMenuBar();
        menuBar.prefWidthProperty().bind(this.widthProperty());
        
        goalContainer = new GoalContainer(width, height);
        goalContainer.prefWidthProperty().bind(this.widthProperty());
        goalContainer.prefHeightProperty().bind((this.heightProperty()).subtract(100));
        
        buttonSection = new ButtonSection(goalContainer);
        buttonSection.prefWidthProperty().bind(this.widthProperty());
       
        root = new VBox();
        root.getChildren().addAll(menuBar, goalContainer, buttonSection);
        Scene scene = new Scene(root);
        this.setScene(scene);
        
        minimized = false;
        minimizedStage = new Stage(StageStyle.TRANSPARENT);
        minimizedStage.setWidth(this.getWidth()/4);
        minimizedStage.setHeight(minimizedStage.getWidth());
        
        Group rootGroup = new Group();
        Image image = new Image("images/logo.png");
        ImageView imageView = new ImageView(image);
        imageView.setOnMouseClicked(event -> {
            showApplication();
        });
        rootGroup.getChildren().add(imageView);
        
        Scene minimizedStageScene = new Scene(rootGroup, 50, 50, Color.TRANSPARENT);
        minimizedStage.setScene(minimizedStageScene);
        minimizedStage.setWidth(50);
        minimizedStage.setHeight(50);
        minimizedStage.setAlwaysOnTop(true);
        
        updateMonitors();
    }
    
    private void hideApplication() {
        System.out.println("Hiding Application");
        this.hide();
        minimizedStage.show();
    }
    
    private void showApplication() {
        System.out.println("Showing Application");
        minimizedStage.hide();
        this.show();
    }
            
    protected void updateMonitors() {
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        monitorDisplays = graphicsEnvironment.getScreenDevices();
        if (activeDisplay >= monitorDisplays.length) {
            activeDisplay = monitorDisplays.length - 1;
        }
        reposition();
    }
    
    protected void changeActiveDisplay(int shiftValue) {
        if (shiftValue == 0) return;
        activeDisplay += shiftValue;
        while (activeDisplay >= monitorDisplays.length) {
            activeDisplay -= monitorDisplays.length;
        }
        while (activeDisplay < 0) {
            activeDisplay += monitorDisplays.length;
        }
    }
    
    protected void reposition() {
        this.setX(monitorDisplays[activeDisplay].getDisplayMode().getWidth() - 
                (this.getWidth() + HORIZ_OFFSET));
        this.setY(monitorDisplays[activeDisplay].getDisplayMode().getHeight() - 
                (this.getHeight() + VERT_OFFSET));
        
        minimizedStage.setX(monitorDisplays[activeDisplay].getDisplayMode().getWidth() -
                HORIZ_OFFSET - (minimizedStage.getWidth()*(3/2)));
        minimizedStage.setY(monitorDisplays[activeDisplay].getDisplayMode().getHeight() - 
                VERT_OFFSET - (minimizedStage.getHeight()*(3/2)));
    }
    
}
