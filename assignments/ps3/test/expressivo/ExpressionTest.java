/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    // Testing strategy
    //   toString():
    //      partition on operations: no operation, one operation, multi operations
    //   equals():
    //      partition on expressions: simple expr, composite expr
    //   hashcode():
    //      partition on expressions: simple expr, composite expr
    //   parse():
    //      partition on expressions: simple expr, composite expr
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    

    @Test
    public void testToString() {
        // partition1: no operation
        Expression ec = new Constant(1);
        assertEquals("test toString with constant", "1", ec.toString());

        Expression ev = new Variable("x");
        assertEquals("test toString with variable", "x", ev.toString());

        // partition2: one operation
        Expression ePlus = new Plus(ec, ev);
        assertEquals("test toString with plus", "1 + x", ePlus.toString());

        Expression eTimes = new Times(ec, ev);
        assertEquals("test toString with times", "1 * x", eTimes.toString());

        // partition3: multi operation
        Expression mulPlusOneParenthesis = new Plus(ec, eTimes);
        assertEquals("test toString with plus with one parenthesis",
                "1 + (1 * x)", mulPlusOneParenthesis.toString());

        Expression mulPlusTwoParenthesis = new Times(ePlus, eTimes);
        assertEquals("test toString with times with two parenthesis",
                "(1 + x) * (1 * x)", mulPlusTwoParenthesis.toString());
    }

    @Test
    public void testEquals() {
        Expression xExpr = new Variable("x");

        // partition1: simple expr
        assertEquals("the same constant", new Constant(100), new Constant(100.000));
        assertNotEquals("different constant", new Constant(100), new Constant(99));
        assertEquals("the same variable", xExpr, new Variable("x"));
        assertNotEquals("different variable", xExpr, new Variable("y"));

        // partition2: composite expr
        Expression plusExpr = new Plus(new Constant(1), xExpr);
        Expression plusExprCopy = new Plus(new Constant(1), xExpr);
        Expression plusExprReverse = new Plus(xExpr, new Constant(1));
        assertEquals("the same plus expr", plusExpr, plusExprCopy);
        assertNotEquals("the different expr", plusExpr, plusExprReverse);

        Expression plusTimesExpr = new Times(new Plus(new Constant(1), xExpr), xExpr);
        Expression timesPlusExpr = new Plus(new Constant(1), new Times(xExpr, xExpr));
        assertNotEquals("the different expr", plusTimesExpr, timesPlusExpr);
    }

    @Test
    public void testHashCode() {
        Expression xExpr = new Variable("x");

        // partition1: simple expr
        assertEquals("the same constant", new Constant(100), new Constant(100.000));
        assertNotEquals("different constant", new Constant(100), new Constant(99));
        assertEquals("the same variable", xExpr.hashCode(), new Variable("x").hashCode());
        assertNotEquals("different variable", xExpr.hashCode(), new Variable("y").hashCode());

        // partition2: composite expr
        Expression plusExpr = new Plus(new Constant(1), xExpr);
        Expression plusExprCopy = new Plus(new Constant(1), xExpr);
        Expression plusExprReverse = new Plus(xExpr, new Constant(1));
        assertEquals("the same plus expr", plusExpr.hashCode(), plusExprCopy.hashCode());
        assertNotEquals("the different expr", plusExpr.hashCode(), plusExprReverse.hashCode());

        Expression plusTimesExpr = new Times(new Plus(new Constant(1), xExpr), xExpr);
        Expression timesPlusExpr = new Plus(new Constant(1), new Times(xExpr, xExpr));
        assertNotEquals("the different expr", plusTimesExpr.hashCode(), timesPlusExpr.hashCode());
    }

    @Test
    public void testParse() {

        // partition1: simple expr
        String simpleStr = "(((a)))";
        Expression simpleExpr = new Variable("a");
        assertEquals("Simple expression parse.", simpleExpr, Expression.parse(simpleStr));

        // partition2: composite expr
        String compositeStr1 = "((1 + x) * (y + 2.8))";
        String compositeStr2 = "(1 * x) + (((y + 2.8)))";

        Expression plusPartExpr1 = new Plus(new Constant(1), new Variable("x"));
        Expression plusPartExpr2 = new Plus(new Variable("y"), new Constant(2.8));
        Expression timesExpr1 = new Times(plusPartExpr1, plusPartExpr2);
        assertEquals("Composite expression parse test1.", timesExpr1, Expression.parse(compositeStr1));

        Expression timesPartExpr1 = new Times(new Constant(1), new Variable("x"));
        Expression plusExpr2 = new Plus(timesPartExpr1, plusPartExpr2);
        assertEquals("Composite expression parse test2.", plusExpr2, Expression.parse(compositeStr2));

        String compositeStr3 = "x + x + x + x * x";
        System.out.println(Expression.parse(compositeStr3));
    }

}
