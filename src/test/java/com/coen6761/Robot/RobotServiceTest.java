package com.coen6761.Robot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.*;
=======
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
>>>>>>> 0fccf6796d2971443a374eedbe16e4b67de4362f

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
<<<<<<< HEAD

        Robot robot = robotService.getRobot();

        assertNotNull(robot);
=======
        Robot robot = robotService.getRobot();

>>>>>>> 0fccf6796d2971443a374eedbe16e4b67de4362f
        assertEquals(9, robot.getRow());
        assertEquals(0, robot.getCol());
        assertTrue(robot.getPenUpStatus());
        assertEquals(Directions.NORTH, robot.getDirection());
    }

    @ParameterizedTest
    @CsvSource({
<<<<<<< HEAD
            "5, -1, 6", // negative steps
            "5, 0, 5",  // 0 step
            "5, 1, 4",  // 1 step
            "5, 3, 2",  // normal step
            "5, 4, 1",  // edge - 1 step
            "5, 5, 0",  // reach edge
            "5, 6, 0"   // exceed edge
=======
            "5, -3, 8", // negative steps
            "5, 0, 5",  // 0 step
            "5, 1, 4",  // 1 step
            "5, 3, 2",  // normal step
            "5, 5, 0",  // reach edge
            "5, 8, 0"   // exceed edge
>>>>>>> 0fccf6796d2971443a374eedbe16e4b67de4362f
    })
    void testMoveWest(int initCol, int steps, int expectedCol) {
        this.robotService.getRobot().setCol(initCol);
        this.robotService.moveWest(steps);
        assertEquals(expectedCol, this.robotService.getRobot().getCol());
    }

    @ParameterizedTest
    @CsvSource({
<<<<<<< HEAD
            "5, -1, 4",
            "5, 0, 5",
            "5, 1, 6",
            "5, 2, 7",
            "5, 3, 8",
            "5, 4, 9",
            "5, 5, 9"
=======
            "5, -3, 2",
            "5, 0, 5",
            "5, 1, 6",
            "5, 3, 8",
            "5, 5, 9",
            "5, 8, 9"
>>>>>>> 0fccf6796d2971443a374eedbe16e4b67de4362f
    })
    void testMoveEast(int initCol, int steps, int expectedCol) {
        this.robotService.getRobot().setCol(initCol);
        this.robotService.moveEast(steps);
        assertEquals(expectedCol, this.robotService.getRobot().getCol());
    }

    @ParameterizedTest
    @CsvSource({
<<<<<<< HEAD
            "5, -1, 6",
            "5, 0, 5",
            "5, 1, 4",
            "5, 3, 2",
            "5, 4, 1",
            "5, 5, 0",
            "5, 6, 0"
=======
            "5, -3, 8",
            "5, 0, 5",
            "5, 1, 4",
            "5, 3, 2",
            "5, 5, 0",
            "5, 8, 0"
>>>>>>> 0fccf6796d2971443a374eedbe16e4b67de4362f
    })
    void testMoveNorth(int initRow, int steps, int expectedRow) {
        this.robotService.getRobot().setRow(initRow);
        this.robotService.moveNorth(steps);
        assertEquals(expectedRow, this.robotService.getRobot().getRow());
    }

    @ParameterizedTest
    @CsvSource({
<<<<<<< HEAD
            "5, -1, 4",
            "5, 0, 5",
            "5, 1, 6",
            "5, 2, 7",
            "5, 3, 8",
            "5, 4, 9",
            "5, 5, 9"
=======
            "5, -3, 2",
            "5, 0, 5",
            "5, 1, 6",
            "5, 3, 8",
            "5, 4, 9",
            "5, 8, 9"
>>>>>>> 0fccf6796d2971443a374eedbe16e4b67de4362f
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
<<<<<<< HEAD
        this.robotService.turnLeft();
=======
        this.robotService.turnWest();
>>>>>>> 0fccf6796d2971443a374eedbe16e4b67de4362f
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
<<<<<<< HEAD
        this.robotService.turnRight();
=======
        this.robotService.turnEast();
>>>>>>> 0fccf6796d2971443a374eedbe16e4b67de4362f
        assertEquals(expectedDir, this.robotService.getRobot().getDirection());
    }


}
