package com.coen6761.MovementHistoryTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.coen6761.MovementHistory.MovementEvent;
import com.coen6761.MovementHistory.MovementType;

public class MovementEventTest {

    @Test
    public void testMoveEventConstructor() {
        MovementEvent event = new MovementEvent(MovementType.MOVE, 5, true);

        assertEquals(MovementType.MOVE, event.getMovement());
        assertEquals(5, event.getStepCount());
        assertTrue(event.getPenUpStatus());
        assertEquals(0, event.getFloorDim());
    }

    @Test
    public void testInitializeEventConstructor() {
        MovementEvent event = new MovementEvent(MovementType.INITIALIZE, 10);

        assertEquals(MovementType.INITIALIZE, event.getMovement());
        assertEquals(0, event.getStepCount()); 
        assertFalse(event.getPenUpStatus()); 
        assertEquals(10, event.getFloorDim());
    }

    @Test
    public void testMoveEventToString() {
        MovementEvent event = new MovementEvent(MovementType.MOVE, 5, true);

        String expectedString = "MovementEvent: [ Movement = MOVE, stepCount = 5 ]";
        assertEquals(expectedString, event.toString());
    }

    @Test
    public void testInitializeEventToString() {
        MovementEvent event = new MovementEvent(MovementType.INITIALIZE, 10);

        String expectedString = "MovementEvent: [ Action = INITIALIZE, floorDim = 10 ]";
        assertEquals(expectedString, event.toString());
    }


    @Test
    public void testActionWithoutStepCountToString() {
        MovementEvent event = new MovementEvent(MovementType.MOVE, -1, false);

        String expectedString = "MovementEvent: [ Action = MOVE ]";
        assertEquals(expectedString, event.toString());
    }

    @Test
    public void testGetPenUpStatus() {
        MovementEvent event = new MovementEvent(MovementType.MOVE, 3, true);

        assertTrue(event.getPenUpStatus());
    }

    @Test
    public void testGetPenDownStatus() {
        MovementEvent event = new MovementEvent(MovementType.MOVE, 3, false);

        assertFalse(event.getPenUpStatus());
    }
}

