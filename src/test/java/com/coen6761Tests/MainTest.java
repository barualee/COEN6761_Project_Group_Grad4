package com.coen6761Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.coen6761.Main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final ByteArrayInputStream originalIn = null;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        if (originalIn != null) {
            System.setIn(System.in);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_data/MainTestCases/basicCommands.csv", numLinesToSkip = 1)
    public void testGameScenarios(String input, String expectedOutput, String testDescription) {
        String processedInput = input.replace("\\n", "\n");
        ByteArrayInputStream in = new ByteArrayInputStream(processedInput.getBytes());
        System.setIn(in);

        Main.main(new String[]{});

        String output = outContent.toString();
        String[] expectedOutputs = expectedOutput.split("\\|");
        for (String expected : expectedOutputs) {
            assert(output.contains(expected));
        }
    }
}