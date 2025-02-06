package com.coen6761.HelperFunctionsTest;

import org.junit.jupiter.api.Test;

import com.coen6761.HelperFunctions.HelperFunctions;

import static org.junit.jupiter.api.Assertions.*;

public class HelperFunctionsTests {

    @Test
    public void testIntegerExist() {
        String[] validCommands = {"command1", "command2"};
        String[] invalidCommands = {"command1"};

        assertTrue(HelperFunctions.IntegerExist(validCommands));
        assertFalse(HelperFunctions.IntegerExist(invalidCommands));
    }

    @Test
    public void testIsValidInteger() {
        assertEquals(123, HelperFunctions.isValidInteger("123"));

        assertEquals(-1, HelperFunctions.isValidInteger("abc"));
        assertEquals(-123, HelperFunctions.isValidInteger("-123"));
        assertEquals(-1, HelperFunctions.isValidInteger(""));
    }

    @Test
    public void testIsIntegerGreaterThanZero() {

        assertTrue(HelperFunctions.isIntegergreaterThanZero(5));
        assertFalse(HelperFunctions.isIntegergreaterThanZero(0));
        assertFalse(HelperFunctions.isIntegergreaterThanZero(-5));
    }
}

