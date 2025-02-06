package com.coen6761.RobotFloor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FloorTest {
    // test constructor and getFloorArray
    @ParameterizedTest
    @CsvSource({"1", "10"})
    void testConstAndSetToBlanks(int floorSize) {
        int[][] expectedArray = new int[floorSize][floorSize];
        Floor floor = new Floor(floorSize);

        int[][] actualArray = floor.getFloorArray();

        assertNotNull(actualArray);
        assertArrayEquals(expectedArray, actualArray);
        assertEquals(floorSize, actualArray.length);
        assertEquals(floorSize, actualArray[0].length);
        for (int[] ints : actualArray) {
            for (int anInt : ints) {
                assertEquals(0, anInt);
            }
        }
    }

    @Test
    void testSetFloorArray() {
        int[][] expectedArray = {{0, 1, 0}, {1, 0, 0}, {0, 0, 0}};
        Floor floor = new Floor(3);

        floor.setFloorArray(expectedArray);
        int[][] actualArray = floor.getFloorArray();

        assertNotNull(actualArray);
        assertEquals(expectedArray.length, actualArray.length);
        for (int i = 0; i < actualArray.length; i++) {
            assertEquals(expectedArray[0].length, actualArray[i].length);
            for (int j = 0; j < actualArray[i].length; j++) {
                assertEquals(expectedArray[i][j], actualArray[i][j]);
            }
        }
    }
}
