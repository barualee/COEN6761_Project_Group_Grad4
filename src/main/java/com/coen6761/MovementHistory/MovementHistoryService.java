package com.coen6761.MovementHistory;

import java.util.LinkedList;

public class MovementHistoryService {
    private LinkedList<MovementEvent> movementList;

    public MovementHistoryService() {
        this.movementList = new LinkedList<>();
    }

    public LinkedList<MovementEvent> getMovementList() {
        return movementList;
    }

    private void addMovement(MovementEvent event){
        movementList.add(event);
    }

    public void addLeftTurnEvent(){
        MovementEvent event = new MovementEvent(MovementType.LEFT, -1);
        addMovement(event);
    }

    public void addRightTurnEvent(){
        MovementEvent event = new MovementEvent(MovementType.RIGHT, -1);
        addMovement(event);
    }

    public void addMoveEvent(int stepCount){
        MovementEvent event = new MovementEvent(MovementType.MOVE, stepCount);
        addMovement(event);
    }

    public void display(){
        if(movementList.size()>0){
            for(MovementEvent event: movementList){
                System.out.println(event.toString());
            }
        } else {
            System.out.println("No events recorded yet.");
        }
    }
}
