/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

import java.beans.BeanProperty;
import java.util.HashMap;
import java.util.Map;

/**
 * Tests for the static methods of Commands.
 */
public class CommandsTest {

    // Testing strategy
    //   Commands.differentiate():
    //      partition on expressions: simple expr, composite expr
    //   Commands.simplify():
    //      partition on simplified: can be fully simplified, cannot be fully simplified
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    // TODO tests for Commands.differentiate() and Commands.simplify()
    @Test
    public void testDiffWrongExpr() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            String expStr = "((1 + x)";
            Commands.differentiate(expStr, "x");
        });
    }

    @Test
    public void testDiff() {
        String exp = "x*x*x";
        String res = Commands.differentiate(exp, "x");
        assertEquals("Test differentiate", "(1 * (x * x)) + (x * ((1 * x) + (x * 1)))", res);
    }

    @Test
    public void testSimplifyFully() {
        String exp = "(1 + x) + (x * x)";
        Map<String, Double> env = new HashMap<>();
        env.put("a", 1.0);
        env.put("x", 0.5);
        String res = Commands.simplify(exp, env);

        assertEquals("test fully simplified. ", "1.75", res);
    }

    @Test
    public void testSimplifyPart() {
        String exp = "(1 + x) + (y * y)";
        Map<String, Double> env = new HashMap<>();
        env.put("a", 1.0);
        env.put("x", 0.5);
        String res = Commands.simplify(exp, env);

        assertEquals("test partly simplified. ", "1.5 + (y * y)", res);
    }
}
