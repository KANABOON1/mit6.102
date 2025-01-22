/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //

    @Test
    public void testToString() {
        Graph<String> G = emptyInstance();
        G.add("aa");
        G.add("bb");
        G.add("cc");
        G.set("aa", "bb", 3);
        G.set("aa", "cc", 2);
        assertEquals("aa ==> bb: 3\naa ==> cc: 2\n", G.toString());
    }
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    // getSource()
    // getTarget()
    // getWeight()
    @Test
    public void testEdgeObservers() {
        Edge<String> edge = new Edge<String>("aa", "bb", 3);
        assertEquals(3, edge.getWeight());
        assertEquals("aa", edge.getSource());
        assertEquals("bb", edge.getTarget());
    }


    @Test
    public void testEdgeToString() {
        Edge<String> edge = new Edge<String>("aa", "bb", 20);
        assertEquals("aa ==> bb: 20", edge.toString());
    }
    
}
