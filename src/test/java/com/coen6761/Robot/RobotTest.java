package com.coen6761.Robot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RobotTest {
    private Robot robot;

    @BeforeEach
    void setUp() {
        robot = new Robot(10);
    }

    // test constructor, getRow, getCol, getPenUpStatus, getDirection
    // use local robot
    @ParameterizedTest
    @CsvSource({
            "10, 9, 0",
            "3, 2, 0",
            "1, 0, 0"
    })
    void testConstructor(int floorDim, int expectedRow, int expectedCol) {
        Robot robot = new Robot(floorDim);
        assertEquals(expectedRow, robot.getRow());
        assertEquals(expectedCol, robot.getCol());
        assertTrue(robot.getPenUpStatus());
        assertEquals(Directions.NORTH, robot.getDirection());
    }

    @ParameterizedTest
    @CsvSource({"0", "5", "9"})
    void testSetRow(int row) {
        this.robot.setRow(row);
        assertEquals(row, this.robot.getRow());
    }

    @ParameterizedTest
    @CsvSource({"0", "5", "9"})
    void testSetCol(int col) {
        this.robot.setCol(col);
        assertEquals(col, this.robot.getCol());
    }

    @ParameterizedTest
    @CsvSource({
            "NORTH",
            "EAST",
            "SOUTH",
            "WEST"
    })
    void testSetDirection(Directions direction) {
        this.robot.setDirection(direction);
        assertEquals(direction, this.robot.getDirection());
    }

    @ParameterizedTest
    @CsvSource({
            "true, false",
            "false, false"
    })
    void testSetPenDown(boolean initialPenUp, boolean expectedPenUp) {
        if (!initialPenUp) this.robot.setpenUp();
        this.robot.setPenDown();
        assertEquals(expectedPenUp, this.robot.getPenUpStatus());
    }

    @ParameterizedTest
    @CsvSource({
            "true, true",
            "false, true"
    })
    void testSetPenUp(boolean initialPenUp, boolean expectedPenUp) {
        if (!initialPenUp) this.robot.setPenDown();
        this.robot.setpenUp();
        assertEquals(expectedPenUp, this.robot.getPenUpStatus());
    }
}
