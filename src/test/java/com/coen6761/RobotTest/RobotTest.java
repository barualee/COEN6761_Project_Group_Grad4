package com.coen6761.RobotTest;

import com.coen6761.Robot.Directions;
import com.coen6761.Robot.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
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
    // floorDim = 10, 3, 1
    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/RobotTestCases/testConstructor.csv")
    void testConstructor(int floorDim, int expectedRow, int expectedCol) {
        Robot robot = new Robot(floorDim);
        assertEquals(expectedRow, robot.getRow());
        assertEquals(expectedCol, robot.getCol());
        assertTrue(robot.getPenUpStatus());
        assertEquals(Directions.NORTH, robot.getDirection());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/RobotTestCases/testSetRow.csv")
    void testSetRow(int row) {
        this.robot.setRow(row);
        assertEquals(row, this.robot.getRow());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/RobotTestCases/testSetCol.csv")
    void testSetCol(int col) {
        this.robot.setCol(col);
        assertEquals(col, this.robot.getCol());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/RobotTestCases/testSetDirection.csv")
    void testSetDirection(Directions direction) {
        this.robot.setDirection(direction);
        assertEquals(direction, this.robot.getDirection());
    }

    // penUp => penDown, penDown => penDown
    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/RobotTestCases/testSetPenDown.csv")
    void testSetPenDown(boolean initialPenUp, boolean expectedPenUp) {
        if (!initialPenUp) this.robot.setPenDown();
        this.robot.setPenDown();
        assertEquals(expectedPenUp, this.robot.getPenUpStatus());
    }

    // penUp => penUp, penDown => penUp
    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/RobotTestCases/testSetPenUp.csv")
    void testSetPenUp(boolean initialPenUp, boolean expectedPenUp) {
        if (!initialPenUp) this.robot.setPenDown();
        this.robot.setpenUp();
        assertEquals(expectedPenUp, this.robot.getPenUpStatus());
    }

    @ParameterizedTest
    @CsvSource({
            "true",
            "false"
    })
    void testPrintRobotStatus(boolean isPenUp){
        if (!isPenUp) this.robot.setPenDown();

        String resultTxt = this.robot.printRobotStatus();

        assertTrue(resultTxt.contains("Current Bot Position: [00]"));
        assertTrue(resultTxt.contains(String.format("Current Pen Position: %s", this.robot.getPenUpStatus() ? "Pen Up" : "Pen Down")));
        assertTrue(resultTxt.contains("Current Bot Direction: NORTH"));
    }
}
