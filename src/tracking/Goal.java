/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tracking;

import java.util.ArrayList;

/**
 *
 * @author G-sta
 */
public class Goal {
    private String objective;
    private String type;
    private boolean completed;
    private String time;
    private String timeToComplete;
    private ArrayList<SubGoal> subgoals;
    private int progress;
    
    public Goal(String objective, String type) {
        this.objective = objective;
        this.type = type;
        completed = false;
        time = "0:00";
        timeToComplete = "0:00";
        subgoals = new ArrayList<>();
        progress = 0;
    }
    
    public Goal(String objective, String type, String timeToComplete) {
        this.objective = objective;
        this.type = type;
        completed = false;
        time = "0:00";
        this.timeToComplete = timeToComplete;
        subgoals = new ArrayList<>();
        progress = 0;
    }
    
    public Goal(String objective, String type, ArrayList<SubGoal> subgoals) {
        this.objective = objective;
        this.type = type;
        completed = false;
        time = "0:00";
        timeToComplete = "0:00";
        this.subgoals = subgoals;
        progress = 0;
    }
    
    public Goal(String objective, String type, boolean progressional) {
        this.objective = objective;
        this.type = type;
        completed = false;
        time = "0:00";
        timeToComplete = "0:00";
        subgoals = new ArrayList<>();
        progress = 0;
    }
    
    public String getObjective() {
        return objective;
    }
    
    public String getType() {
        return type;
    }
    
    public boolean isCompleted() {
        return completed;
    }
    
    public String getTime() {
        return time;
    }
    
    public String getTimeToComplete() {
        return timeToComplete;
    }
}
