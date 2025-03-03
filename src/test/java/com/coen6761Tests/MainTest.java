package com.coen6761Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.coen6761.Main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


    @ParameterizedTest
    @CsvFileSource(resources = "", numLinesToSkip = 1)
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
