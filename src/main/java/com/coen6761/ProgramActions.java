package com.coen6761;

import com.coen6761.HelperFunctions.HelperFunctions;
import com.coen6761.MovementHistory.MovementHistoryService;
import com.coen6761.Robot.Directions;
import com.coen6761.Robot.Robot;
import com.coen6761.Robot.RobotService;
import com.coen6761.RobotFloor.FloorMarkingService;

public class ProgramActions {

    private RobotService robotService;
    private FloorMarkingService floorMarkingService;
    private MovementHistoryService movementHistoryService;
    
    public void actions(String command) {
        String[] commands = command.split(" ");
        commands[0] = commands[0].toUpperCase();

        switch (commands[0]) {
            case "I":
                callInitialiseFunction(commands);
                break;
            
            case "U":
                callPenUpFunction();
                break;
            
            case "D":
                callPenDownFunction();
                break;

            case "C":
                callCurrentPositionFunction(); 
                break;
            
            case "R":
                callRobotTurnRightFunction();
                break;

            case "L":
                callRobotTurnLeftFunction();
                break;
            
            case "P":
                printFloorFunction();
                break;
            
            case "M":
                moveRobotFunction(commands);
                break;
            case "H":
                displayMovementHistory();
                break;
            default:
                System.out.println("Wrong Input please give correct input");
                break;
        }
    }

    private void displayMovementHistory(){
        if(movementHistoryService == null){
            System.out.println("Please initialise robot first");
            return;
        }
        movementHistoryService.display();
    }
    
    private void printFloorFunction(){        
        if(floorMarkingService == null || robotService == null){
            System.out.println("Please initiliase the Robot first");
            return;
        }
        int[] robotCoordinates = new int[] {robotService.getRobot().getRow(), robotService.getRobot().getCol()};
        floorMarkingService.printFloorFunction(robotCoordinates, robotService.getRobot().getDirection());
    }
    
    private void callPenUpFunction(){
        if(robotService == null){
            System.out.println("Please initialize Robot first");
            return;
        }
        robotService.getRobot().setpenUp();
        movementHistoryService.addPenUpEvent();
    }
    
    private void callPenDownFunction(){
        if(robotService == null){
            System.out.println("Please initialize Robot first");
            return;
        }
        robotService.getRobot().setPenDown();
        movementHistoryService.addPenDownEvent();
    }
    
    private void callRobotTurnRightFunction(){
        if(robotService == null){
            System.out.println("Please initialize Robot first");
            return;
        }
        robotService.turnRight();
        movementHistoryService.addRightTurnEvent(robotService.getRobot().getPenUpStatus());
    }
    
    private void callRobotTurnLeftFunction(){
        if(robotService == null){
            System.out.println("Please initialize Robot first");
            return;
        }
        robotService.turnLeft();
        movementHistoryService.addLeftTurnEvent(robotService.getRobot().getPenUpStatus());
    }
    
    private void callInitialiseFunction(String[] commands){
        if(HelperFunctions.IntegerExist(commands)){
            int floorDim = HelperFunctions.isValidInteger(commands[1]);
            if(floorDim != -1){
                if(HelperFunctions.isIntegergreaterThanZero(floorDim)){
                    robotService = new RobotService(floorDim);
                    floorMarkingService = new FloorMarkingService(floorDim);
                    movementHistoryService = new MovementHistoryService();
                    movementHistoryService.addInitializeEvent(floorDim);

                    System.out.println("Robot initialized");
                } else {
                    System.out.println("Floor Dimension should be greater than 0");
                }
            } else {
                System.out.println("Floor Dimension is invalid: ");
            }
        } else {
            System.out.println("Floor Dimension is missing");
        }
    }

    private void callCurrentPositionFunction(){
        if(robotService == null){
            System.out.println("Please initialize Robot first");
            return;
        }
        String curPosStr = robotService.getRobot().printRobotStatus();
        System.out.println(curPosStr);
    }

    private void moveRobotFunction(String[] commands){
        if(robotService == null){
            System.out.println("Please initialize Robot first");
            return;
        }
        if(HelperFunctions.IntegerExist(commands)){
            int moveMentSteps = HelperFunctions.isValidInteger(commands[1]);
            if(moveMentSteps != -1){
                if(HelperFunctions.isIntegergreaterThanZero(moveMentSteps)){
                    Robot robot = robotService.getRobot();
                    int[] startPos = new int[]{robot.getRow(), robot.getCol()};
                    switch (robot.getDirection()) {
                        case NORTH:
                            robotService.moveNorth(moveMentSteps);
                            markingTheFloor(startPos, moveMentSteps, Directions.NORTH);
                            break;
                        case SOUTH:
                            robotService.moveSouth(moveMentSteps);
                            markingTheFloor(startPos, moveMentSteps, Directions.SOUTH);
                            break;
                        case EAST:
                            robotService.moveEast(moveMentSteps);
                            markingTheFloor(startPos, moveMentSteps, Directions.EAST);
                            break;
                        case WEST:
                            robotService.moveWest(moveMentSteps);
                            markingTheFloor(startPos, moveMentSteps, Directions.WEST);
                            break;
                    }
                    movementHistoryService.addMoveEvent(moveMentSteps, robotService.getRobot().getPenUpStatus());
                } else {
                    System.out.println("Movement StepCount should be greater than 0");
                }
            } else {
                System.out.println("Movement StepCount is invalid: ");
            }
        } else {
            System.out.println("Movement StepCount is missing");
        }
    }

    private void markingTheFloor(int[] startPos, int  moveMentSteps, Directions direction){
        if(robotService.getRobot().getPenUpStatus()==false){
            floorMarkingService.markFloor(direction, startPos, moveMentSteps);
        }
    }
}
