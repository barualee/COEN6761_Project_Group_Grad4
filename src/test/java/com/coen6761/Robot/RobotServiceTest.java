package com.coen6761.Robot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

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

        assertNotNull(robot);
        assertEquals(9, robot.getRow());
        assertEquals(0, robot.getCol());
        assertTrue(robot.getPenUpStatus());
        assertEquals(Directions.NORTH, robot.getDirection());
    }

    @ParameterizedTest
    @CsvSource({
            "5, -1, 6", // negative steps
            "5, 0, 5",  // 0 step
            "5, 1, 4",  // 1 step
            "5, 3, 2",  // normal step
            "5, 4, 1",  // edge - 1 step
            "5, 5, 0",  // reach edge
            "5, 6, 0"   // exceed edge
    })
    void testMoveWest(int initCol, int steps, int expectedCol) {
        this.robotService.getRobot().setCol(initCol);
        this.robotService.moveWest(steps);
        assertEquals(expectedCol, this.robotService.getRobot().getCol());
    }

    @ParameterizedTest
    @CsvSource({
            "5, -1, 4",
            "5, 0, 5",
            "5, 1, 6",
            "5, 2, 7",
            "5, 3, 8",
            "5, 4, 9",
            "5, 5, 9"
    })
    void testMoveEast(int initCol, int steps, int expectedCol) {
        this.robotService.getRobot().setCol(initCol);
        this.robotService.moveEast(steps);
        assertEquals(expectedCol, this.robotService.getRobot().getCol());
    }

    @ParameterizedTest
    @CsvSource({
            "5, -1, 6",
            "5, 0, 5",
            "5, 1, 4",
            "5, 3, 2",
            "5, 4, 1",
            "5, 5, 0",
            "5, 6, 0"
    })
    void testMoveNorth(int initRow, int steps, int expectedRow) {
        this.robotService.getRobot().setRow(initRow);
        this.robotService.moveNorth(steps);
        assertEquals(expectedRow, this.robotService.getRobot().getRow());
    }

    @ParameterizedTest
    @CsvSource({
            "5, -1, 4",
            "5, 0, 5",
            "5, 1, 6",
            "5, 2, 7",
            "5, 3, 8",
            "5, 4, 9",
            "5, 5, 9"
    })
    void testMoveSouth(int initRow, int steps, int expectedRow) {
        this.robotService.getRobot().setRow(initRow);
        this.robotService.moveSouth(steps);
        assertEquals(expectedRow, this.robotService.getRobot().getRow());
    }

    @ParameterizedTest
    @CsvSource({
            "NORTH, WEST",
            "WEST, SOUTH",
            "SOUTH, EAST",
            "EAST, NORTH"
    })
    void testTurnLeft(Directions initDir, Directions expectedDir) {
        this.robotService.getRobot().setDirection(initDir);
        this.robotService.turnLeft();
        assertEquals(expectedDir, this.robotService.getRobot().getDirection());
    }

    @ParameterizedTest
    @CsvSource({
            "NORTH, EAST",
            "WEST, NORTH",
            "SOUTH, WEST",
            "EAST, SOUTH"
    })
    void testTurnRight(Directions initDir, Directions expectedDir) {
        this.robotService.getRobot().setDirection(initDir);
        this.robotService.turnRight();
        assertEquals(expectedDir, this.robotService.getRobot().getDirection());
    }


}
