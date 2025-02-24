package com.coen6761;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ProgramActionsTest {
    private ProgramActions programActions;
    private ByteArrayOutputStream outContent;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        programActions = new ProgramActions();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/ProgramActionsTestCases/initializationTests.csv", numLinesToSkip = 1)
    void testInitialization(String command, String expectedOutput, String testDescription) {
        programActions.actions(command);
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/ProgramActionsTestCases/movementTests.csv", numLinesToSkip = 1)
    void testMovement(String initCommand, String command, String expectedOutput, String testDescription) {
        if (!initCommand.isEmpty()) {
            programActions.actions(initCommand);
            outContent.reset();
        }
        programActions.actions(command);
        if (!expectedOutput.isEmpty()) {
            assertTrue(outContent.toString().contains(expectedOutput));
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/ProgramActionsTestCases/penStateTests.csv", numLinesToSkip = 1)
    void testPenState(String initCommand, String commands, String expectedOutput, boolean expectedMark, String testDescription) {
        if (!initCommand.isEmpty()) {
            programActions.actions(initCommand);
            outContent.reset();
        }
        
        String[] commandList = commands.split("\\|");
        for (String command : commandList) {
            programActions.actions(command);
        }
        
        if (!expectedOutput.isEmpty()) {
            assertTrue(outContent.toString().contains(expectedOutput));
        }
        
        if (initCommand.isEmpty()) {
            return;
        }
        
        String output = outContent.toString();
        assertEquals(expectedMark, output.contains("*"), 
            expectedMark ? "Should have marked path" : "Should not have marked path");
    }
} 