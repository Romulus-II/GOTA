/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gota;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author G-sta
 */
public class GOTA extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        final int width = 200, height = 400;
        
        GOTADisplay display = new GOTADisplay(width, height);
        display.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
