package com.coen6761.MovementHistoryTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.coen6761.MovementHistory.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

public class MovementHistoryServiceTest {

    private MovementHistoryService movementHistoryService;

    @BeforeEach
    public void setUp() {
        // Initialize the services directly for testing purposes
        movementHistoryService = new MovementHistoryService();
    }

    @Test
    public void testAddLeftTurnEvent() {
        boolean penUpStatus = true;
        

        movementHistoryService.addLeftTurnEvent(penUpStatus);

        // Verify that the event was added to the movement list
        LinkedList<MovementEvent> movementList = movementHistoryService.getMovementList();
        assertEquals(1, movementList.size());
        
        MovementEvent event = movementList.get(0);
        assertEquals(MovementType.LEFT, event.getMovement());
        assertEquals(penUpStatus, event.getPenUpStatus());
    }
    
    @Test
    public void testAddTurnRightEvent() {
        boolean penUpStatus = true;

        movementHistoryService.addRightTurnEvent(penUpStatus);

        // Verify that the event was added to the movement list
        LinkedList<MovementEvent> movementList = movementHistoryService.getMovementList();
        assertEquals(1, movementList.size());
        
        MovementEvent event = movementList.get(0);
        assertEquals(MovementType.RIGHT, event.getMovement());
        assertEquals(penUpStatus, event.getPenUpStatus());
    }
    
    @Test
    public void testAddMoveEvent() {
        boolean penUpStatus = true;
        int stepCount = 5;

        movementHistoryService.addMoveEvent(stepCount, penUpStatus);

        // Verify that the event was added to the movement list
        LinkedList<MovementEvent> movementList = movementHistoryService.getMovementList();
        assertEquals(1, movementList.size());
        MovementEvent event = movementList.get(0);
        assertEquals(MovementType.MOVE, event.getMovement());
        assertEquals(stepCount, event.getStepCount());
        assertEquals(penUpStatus, event.getPenUpStatus());
    }

    @Test
    public void testAddPenUpEvent() {
        movementHistoryService.addPenUpEvent();

        // Verify that the event was added to the movement list
        LinkedList<MovementEvent> movementList = movementHistoryService.getMovementList();
        assertEquals(1, movementList.size());
        MovementEvent event = movementList.get(0);
        assertEquals(MovementType.PENUP, event.getMovement());
        assertTrue(event.getPenUpStatus());
    }
    
    @Test
    public void testAddPenDownEvent() {
        movementHistoryService.addPenDownEvent();

        // Verify that the event was added to the movement list
        LinkedList<MovementEvent> movementList = movementHistoryService.getMovementList();
        assertEquals(1, movementList.size());
        
        MovementEvent event = movementList.get(0);
        assertEquals(MovementType.PENDOWN, event.getMovement());
        assertFalse(event.getPenUpStatus());
    }

    @Test
    public void testAddInitializeEvent() {
        int floorDim = 10;
        movementHistoryService.addInitializeEvent(floorDim);

        // Verify that the event was added to the movement list
        LinkedList<MovementEvent> movementList = movementHistoryService.getMovementList();
        assertEquals(1, movementList.size());
        MovementEvent event = movementList.get(0);
        assertEquals(MovementType.INITIALIZE, event.getMovement());
        assertEquals(floorDim, event.getFloorDim());
    }

    @Test
    public void testDisplayWhenNoEvents() {
        // Capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        movementHistoryService.display();

        assertTrue(outContent.toString().contains("No events recorded yet."));

        // Restore original System.out
        System.setOut(System.out);
    }

    @Test
    public void testDisplayWhenEventsPresent() {
        int floorDim = 10;
        movementHistoryService.addInitializeEvent(floorDim);
        movementHistoryService.addMoveEvent(5, false);
        
        // Capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        movementHistoryService.display();

        // Check if events are printed
        assertTrue(outContent.toString().contains("MovementEvent: [ Action = INITIALIZE, floorDim = 10 ]"));
        assertTrue(outContent.toString().contains("MovementEvent: [ Movement = MOVE, stepCount = 5 ]"));

        // Restore original System.out
        System.setOut(System.out);
    }
    
    @Test
    public void testDisplayEventsTypes() {
        int floorDim = 10;
        movementHistoryService.addInitializeEvent(floorDim);
        movementHistoryService.addLeftTurnEvent(false);
        movementHistoryService.addRightTurnEvent(false);
        movementHistoryService.addPenDownEvent();
        movementHistoryService.addPenUpEvent();
        
        // Capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        movementHistoryService.display();

        // Check if events are printed
        assertTrue(outContent.toString().contains("MovementEvent: [ Action = INITIALIZE, floorDim = 10 ]"));
        assertTrue(outContent.toString().contains("MovementEvent: [ Action = RIGHT ]"));
        assertTrue(outContent.toString().contains("MovementEvent: [ Action = LEFT ]"));
        assertTrue(outContent.toString().contains("MovementEvent: [ Action = PENDOWN ]"));
        assertTrue(outContent.toString().contains("MovementEvent: [ Action = PENUP ]"));

        // Restore original System.out
        System.setOut(System.out);
    }
    
    @Test
    public void testDisplayEventsMovementsInAllDirections() {
        int floorDim = 10;
        movementHistoryService.addInitializeEvent(floorDim);
        //move west
        movementHistoryService.addPenDownEvent();
        movementHistoryService.addLeftTurnEvent(false);
        movementHistoryService.addMoveEvent(5, false);
        
        //move south
        movementHistoryService.addLeftTurnEvent(false);
        movementHistoryService.addMoveEvent(5, false);
        
        //move easr
        movementHistoryService.addLeftTurnEvent(false);
        movementHistoryService.addMoveEvent(5, false);
        
        // Capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        movementHistoryService.display();

        // Check if events are printed
        assertTrue(outContent.toString().contains("MovementEvent: [ Action = INITIALIZE, floorDim = 10 ]"));
        assertTrue(outContent.toString().contains("MovementEvent: [ Action = LEFT ]"));
        assertTrue(outContent.toString().contains("MovementEvent: [ Movement = MOVE, stepCount = 5 ]"));

        // Restore original System.out
        System.setOut(System.out);
    }
}

