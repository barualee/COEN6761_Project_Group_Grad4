package com.coen6761Tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.coen6761.ProgramActions;

public class ProgramActionsTests {
	
    private ProgramActions programActions;

    @BeforeEach
    public void setUp() {
        // Initialize the services directly for testing purposes
    	programActions = new ProgramActions();
    }
	

    @ParameterizedTest
    @CsvSource({
       "I 10,Robot initialized",
       "I,Floor Dimension is missing",
       "I aa,Floor Dimension is invalid: ",
       "I -9,Floor Dimension should be greater than 0",
       "XXX - default,Wrong Input please give correct input",
	   "U,Please initialize Robot first",
	   "D,Please initialize Robot first",
	   "R,Please initialize Robot first",
	   "L,Please initialize Robot first",
	   "C,Please initialize Robot first",
	   "H,Please initialize Robot first",
	   "P,Please initialize Robot first",
	   
    })	
	public void TestProgramActions(String input, String output) {
        
        // Capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        programActions.actions(input);

        // Check if events are printed
        assertTrue(outContent.toString().contains(output));

        // Restore original System.out
        System.setOut(System.out);
	}
    
    @ParameterizedTest
    @CsvSource({
	   "U,Robot Pen Set to Up",
	   "D,Robot Pen Set to Down",
	   "R,Robot Turned Right",
	   "L,Robot Turned Left",
       "M,Movement StepCount is missing",
       "M aa,Movement StepCount is invalid: ",
       "M -9,Movement StepCount should be greater than 0",
       
    })	
	public void TestProgramActionsDirectionsAndPenStatus(String input, String output) {
        
    	programActions.actions("I 10");
    	
    	// Capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        programActions.actions(input);

        // Check if events are printed
        assertTrue(outContent.toString().contains(output));

        // Restore original System.out
        System.setOut(System.out);
	}
    
    @ParameterizedTest
    @CsvSource({
	   "C,Current Bot Position:",
	   "P,_",
	   "H,MovementEvent:",
    })
    public void testOtherActions(String input, String output) {
    	programActions.actions("I 10");
    	
    	// Capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        programActions.actions(input);

        // Check if events are printed
        assertTrue(outContent.toString().contains(output));

        // Restore original System.out
        System.setOut(System.out);
    }
    
    @Test
    public void missedTestCase() {
    	programActions.actions("I 10");
    	programActions.actions("D");
    	
    	// Capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        programActions.actions("M 5");

        // Check if events are printed
        assertTrue(outContent.toString().contains("Movement recorded"));

        // Restore original System.out
        System.setOut(System.out);
    }
    
    @ParameterizedTest
    @CsvSource({
	   "M 5;Movement recorded:R;Robot Turned Right:M 5;Movement recorded:R;Robot Turned Right:M 5;Movement recorded:R;Robot Turned Right:M 5;Movement recorded"
    })
    public void testMovementActionsInAllDirections(String input) {
    	programActions.actions("I 10");
    	
    	for(String cur: input.split(":")) {
    		String[] curs = cur.split(";");
    		String curInput = curs[0];
    		String curOutput = curs[1];
    		
        	// Capture output
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            
            programActions.actions(curInput);

            // Check if events are printed
            assertTrue(outContent.toString().contains(curOutput));

            // Restore original System.out
            System.setOut(System.out);
    	}

    }
}
