package com.coen6761.MovementHistory;

public class MovementEvent {
    private MovementType movement;
    private int stepCount;
    
    public MovementEvent(MovementType movement, int stepCount) {
        this.movement = movement;
        this.stepCount = stepCount;
    }
    public MovementType getMovement() {
        return movement;
    }
    public int getStepCount() {
        return stepCount;
    }
    @Override
    public String toString() {
        if(stepCount != -1){
            return "MovementEvent: [movement=" + movement + ", stepCount=" + stepCount + "]";
        }
        return "MovementEvent: [Turn=" + movement + "]";
    }

    
}
