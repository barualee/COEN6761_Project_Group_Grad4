package com.coen6761.RobotFloorTest;

import com.coen6761.Robot.Directions;
import com.coen6761.RobotFloor.Floor;
import com.coen6761.RobotFloor.FloorMarkingService;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FloorMarkingServiceTest {
    @ParameterizedTest
    @CsvSource({"1", "10"})
    void testConstructor(int floorDim) {
        FloorMarkingService floorMarkingService = new FloorMarkingService(floorDim);

        assertNotNull(floorMarkingService.getFloor());
        assertEquals(floorDim, floorMarkingService.getFloor().getFloorArray().length);
        assertEquals(floorDim, floorMarkingService.getFloor().getFloorArray()[0].length);
    }

    // direction: NORTH, EAST, SOUTH, WEST
    // step: <0, =0, =1, normal value, edge-1, reach edge, exceed edge
    @ParameterizedTest
    @CsvSource({
            "NORTH, 5, 5, -1",
            "NORTH, 5, 5, 0",
            "NORTH, 5, 5, 1",
            "NORTH, 5, 5, 2",
            "NORTH, 5, 5, 4",
            "NORTH, 5, 5, 5",
            "NORTH, 5, 5, 6",

            "SOUTH, 5, 5, -1",
            "SOUTH, 5, 5, 0",
            "SOUTH, 5, 5, 1",
            "SOUTH, 5, 5, 2",
            "SOUTH, 5, 5, 3",
            "SOUTH, 5, 5, 4",
            "SOUTH, 5, 5, 5",

            "EAST, 5, 5, -1",
            "EAST, 5, 5, 0",
            "EAST, 5, 5, 1",
            "EAST, 5, 5, 2",
            "EAST, 5, 5, 3",
            "EAST, 5, 5, 4",
            "EAST, 5, 5, 5",

            "SOUTH, 5, 5, -1",
            "SOUTH, 5, 5, 0",
            "SOUTH, 5, 5, 1",
            "SOUTH, 5, 5, 2",
            "SOUTH, 5, 5, 4",
            "SOUTH, 5, 5, 5",
            "SOUTH, 5, 5, 6",
    })
    void testMarkFloor(String dir, int startRow, int startCol, int steps) {
        int floorDim = 10;
        FloorMarkingService floorMarkingService = new FloorMarkingService(floorDim);
        Directions direction = Directions.valueOf(dir);

        Floor floor = floorMarkingService.markFloor(direction, new int[]{startRow, startCol}, steps);

        int[][] floorArray = floor.getFloorArray();
        assertEquals(floorDim, floorArray.length);
        assertEquals(floorDim, floorArray[0].length);

        if (direction == Directions.NORTH) {
            int endRow = Math.max(0, startRow - steps);
            for (int row = 0; row < floorDim; row++) {
                for (int col = 0; col < floorDim; col++) {
                    // if cell should be marked
                    if (col == startCol && row <= startRow && row >= endRow) {
                        assertEquals(1, floorArray[row][col], String.format("[%d][%d] should be marked as 1, but not", row, col));
                    } else {
                        assertEquals(0, floorArray[row][col],
                                String.format("[%d][%d] should be left as 0, but marked to 1", row, col));
                    }
                }
            }
        } else if (direction == Directions.SOUTH) {
            int endRow = Math.min(floorDim - 1, startRow + steps);
            for (int row = 0; row < floorDim; row++) {
                for (int col = 0; col < floorDim; col++) {
                    if (col == startCol && row >= startRow && row <= endRow) {
                        assertEquals(1, floorArray[row][col],
                                String.format("[%d][%d] should be marked as 1, but not", row, col));
                    } else {
                        assertEquals(0, floorArray[row][col],
                                String.format("[%d][%d] should be left as 0, but marked to 1", row, col));
                    }
                }
            }
        } else if (direction == Directions.EAST) {
            int endCol = Math.min(floorDim - 1, startCol + steps);
            for (int row = 0; row < floorDim; row++) {
                for (int col = 0; col < floorDim; col++) {
                    if (row == startRow && col >= startCol && col <= endCol) {
                        assertEquals(1, floorArray[row][col],
                                String.format("[%d][%d] should be marked as 1, but not", row, col));
                    } else {
                        assertEquals(0, floorArray[row][col],
                                String.format("[%d][%d] should be left as 0, but marked to 1", row, col));
                    }
                }
            }
        } else if (direction == Directions.WEST) {
            int endCol = Math.max(0, startCol - steps);
            for (int row = 0; row < floorDim; row++) {
                for (int col = 0; col < floorDim; col++) {
                    if (row == startRow && col <= startCol && col >= endCol) {
                        assertEquals(1, floorArray[row][col],
                                String.format("[%d][%d] should be marked as 1, but not", row, col));
                    } else {
                        assertEquals(0, floorArray[row][col],
                                String.format("[%d][%d] should be left as 0, but marked to 1", row, col));
                    }
                }
            }
        }
    }
}