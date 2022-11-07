/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tracking;

/**
 *
 * @author G-sta
 */
public class SubGoal {
    private String objective;
    private boolean completed;
    
    public SubGoal(String objective) {
        this.objective = objective;
        completed = false;
    }
    
    public String getObjective() {
        return objective;
    }
    
    public boolean isCompleted() {
        return completed;
    }
    
    public void markCompleted() {
        completed = true;
    }
}
