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
        if(movementHistoryService.getMovementList()!=null){
            movementHistoryService.display();
        } else {
            System.out.println("Please initialise robot first");
        }
    }
    
    private void printFloorFunction(){        
        if(floorMarkingService.getFloor()!=null){
            int[] robotCoordinates = new int[] {robotService.getRobot().getRow(), robotService.getRobot().getCol()};
            floorMarkingService.printFloorFunction(robotCoordinates, robotService.getRobot().getDirection());
        } else {
            System.out.println("Please initiliase the Robot first");
        }
    }
    
    private void callPenUpFunction(){
        if(robotService.getRobot()!=null){
            robotService.getRobot().setpenUp();
        } else {
            System.out.println("Please initialize Robot first");
        }
    }
    
    private void callPenDownFunction(){
        if(robotService.getRobot()!=null){
            robotService.getRobot().setPenDown();
        } else {
            System.out.println("Please initialize Robot first");
        }
    }
    
    private void callRobotTurnRightFunction(){
        if(robotService.getRobot()!=null){
            robotService.turnEast();
            movementHistoryService.addRightTurnEvent();
        } else {
            System.out.println("Please initialize Robot first");
        }
    }
    
    private void callRobotTurnLeftFunction(){
        if(robotService.getRobot()!=null){
            robotService.turnWest();
            movementHistoryService.addLeftTurnEvent();
        } else {
            System.out.println("Please initialize Robot first");
        }
    }
    
    private void callInitialiseFunction(String[] commands){
        if(HelperFunctions.IntegerExist(commands)){
            int floorDim = HelperFunctions.isValidInteger(commands[1]);
            if(floorDim != -1){
                if(HelperFunctions.isIntegergreaterThanZero(floorDim)){
                    robotService = new RobotService(floorDim);
                    floorMarkingService = new FloorMarkingService(floorDim);
                    movementHistoryService = new MovementHistoryService();

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
        if(robotService.getRobot()!=null){
            robotService.getRobot().printRobotStatus();
        } else {
            System.out.println("Please initialize Robot first");
        }
    }

    private void moveRobotFunction(String[] commands){
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
                    movementHistoryService.addMoveEvent(moveMentSteps);
                    // movementHistoryService.addMoveEvent(validatedSteps(moveMentSteps, startPos, robot.getDirection()));
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

    // private int validatedSteps(int moveMentSteps, int[] startPos, Directions direction){
    //     int floorDim = floorMarkingService.getFloor().getFloorArray().length - 1;
    //     if(direction == Directions.NORTH){
    //         int row = startPos[0];
    //         if(row - moveMentSteps < 0){
    //             return row;
    //         }
    //     } else if(direction == Directions.SOUTH){
    //         int row = startPos[0];
    //         if(row + moveMentSteps > floorDim - 1){
    //             return floorDim - row;
    //         }
    //     } else if(direction == Directions.EAST){
    //         int row = startPos[1];
    //         if(row + moveMentSteps > floorDim - 1){
    //             return floorDim - row;
    //         }
    //     } else if(direction == Directions.WEST){
    //         int row = startPos[1];
    //         if(row - moveMentSteps < 0){
    //             return row;
    //         }
    //     }
    //     return moveMentSteps;
    // }

    private void markingTheFloor(int[] startPos, int  moveMentSteps, Directions direction){
        if(robotService.getRobot().getPenUpStatus()==false){
            floorMarkingService.markFloor(direction, startPos, moveMentSteps);
        }
    }
}
