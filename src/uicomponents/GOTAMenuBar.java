/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uicomponents;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 *
 * @author G-sta
 */
public class GOTAMenuBar extends MenuBar {
    
    private final double width = 200, height = 50;
    
    public GOTAMenuBar() {
        super();
        this.setWidth(width);
        this.setHeight(height);
        
        Menu file = new Menu("File");
        MenuItem openFile = new MenuItem("Open");
        file.getItems().add(openFile);
        
        Menu fill = new Menu();
        fill.setDisable(true);
        
        this.getMenus().addAll(file);
        System.out.println("Menu height: " + this.getHeight());
    }
    
}
