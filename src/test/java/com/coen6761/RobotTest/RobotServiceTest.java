package com.coen6761.RobotTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.coen6761.Robot.Directions;
import com.coen6761.Robot.Robot;
import com.coen6761.Robot.RobotService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RobotServiceTest {
    private RobotService robotService;

    @BeforeEach
    void setUp() {
        robotService = new RobotService(10);
    }

    // test constructor and getRobot
    @Test
    void testConstructor() {
        RobotService robotService = new RobotService(10);
        Robot robot = robotService.getRobot();

        assertEquals(9, robot.getRow());
        assertEquals(0, robot.getCol());
        assertTrue(robot.getPenUpStatus());
        assertEquals(Directions.NORTH, robot.getDirection());
    }

    // step: <0, =0, =1, normal value, edge-1, reach edge, exceed edge
    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/RobotTestCases/testMoveWest.csv")
    void testMoveWest(int initCol, int steps, int expectedCol) {
        this.robotService.getRobot().setCol(initCol);
        this.robotService.moveWest(steps);
        assertEquals(expectedCol, this.robotService.getRobot().getCol());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/RobotTestCases/testMoveEast.csv")
    void testMoveEast(int initCol, int steps, int expectedCol) {
        this.robotService.getRobot().setCol(initCol);
        this.robotService.moveEast(steps);
        assertEquals(expectedCol, this.robotService.getRobot().getCol());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/RobotTestCases/testMoveNorth.csv")
    void testMoveNorth(int initRow, int steps, int expectedRow) {
        this.robotService.getRobot().setRow(initRow);
        this.robotService.moveNorth(steps);
        assertEquals(expectedRow, this.robotService.getRobot().getRow());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/RobotTestCases/testMoveSouth.csv")
    void testMoveSouth(int initRow, int steps, int expectedRow) {
        this.robotService.getRobot().setRow(initRow);
        this.robotService.moveSouth(steps);
        assertEquals(expectedRow, this.robotService.getRobot().getRow());
    }

    // N=>W, W=>S, S=>E, E=>N
    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/RobotTestCases/testTurnLeft.csv")
    void testTurnLeft(Directions initDir, Directions expectedDir) {
        this.robotService.getRobot().setDirection(initDir);
        this.robotService.turnLeft();
        assertEquals(expectedDir, this.robotService.getRobot().getDirection());
    }

    // N=>E, E=>S, S=>W, W=>N
    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/RobotTestCases/testTurnRight.csv")
    void testTurnRight(Directions initDir, Directions expectedDir) {
        this.robotService.getRobot().setDirection(initDir);
        this.robotService.turnRight();
        assertEquals(expectedDir, this.robotService.getRobot().getDirection());
    }


}
