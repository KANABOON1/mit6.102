/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Map;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //

    @Test
    public void testGraphToString() {
        Graph<String> G = emptyInstance();
        G.set("aa", "bb", 3);
        G.set("aa", "cc", 2);
        assertEquals("aa ==> bb: 3\naa ==> cc: 2\n", G.toString());
    }
    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    // getLabel()
    //
    // getNeighbours()
    //   partition on num of neighbours: 0, >0
    // set()
    // partition on result: 0, >0
    // partition on function: add, change, drop
    
    // TODO tests for operations of Vertex
    @Test
    public void testGetLabel() {
        Vertex vertex = new Vertex("abcd");
        assertEquals("the label should be: abcd", "abcd", vertex.getLabel());
    }

    @Test
    public void testGetNeighboursWithoutNei() {
        Vertex vertex = new Vertex("a");
        assertTrue("the neighbours should be empty", vertex.getNeighbours().isEmpty());
    }

    @Test
    public void testGetNeighboursWithNeis() {
        Vertex vertex = new Vertex("a");
        vertex.set("b", 100);   // integration tests
        vertex.set("c", 101);

        Map<String, Integer> map = vertex.getNeighbours();
        assertEquals("the vertex must has two neighbours", 2, map.size());

        int weightB = map.get("b");
        assertEquals("the edge from a to b must has 100 distance", 100, weightB);

        int weightC = map.get("c");
        assertEquals("the edge from a to b must has 100 distance", 101, weightC);
    }

    @Test
    public void testSetDropEdgeWithoutExist() {
        Vertex vertex = new Vertex("a");
        vertex.set("c", 100);
        int res = vertex.set("b", 0);

        assertEquals("the result must be 0", 0, res);
    }

    @Test
    public void testSetDropEdgeWithExist() {
        Vertex vertex = new Vertex("a");
        vertex.set("c", 100);
        int res = vertex.set("c", 0);

        assertEquals("the result must be 100", 100, res);
    }

    @Test
    public void testSetUpdateEdge() {
        Vertex vertex = new Vertex("a");
        vertex.set("b", 1);
        int res = vertex.set("b", 1000);
        Map<String, Integer> neighbours = vertex.getNeighbours();

        assertEquals("the old weight must be 1", 1, res);
        assertTrue("the neighbour must in the vertex's neighbours", neighbours.containsKey("b"));

        int weight = neighbours.get("b");
        assertEquals("the weight must be 1000", 1000, weight);
    }

    @Test
    public void testToString() {
        Vertex vertex = new Vertex("aa");
        vertex.set("bb", 1);
        vertex.set("cc", 2);
        assertEquals("aa ==> bb: 1\naa ==> cc: 2\n", vertex.toString());
    }
}
