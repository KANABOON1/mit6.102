/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {

    /*
     * Testing strategy
     * add():
     *     partition on the current size(): 0, >0
     *     partition on existence of the added vertex: already in the graph, not in the graph
     * set():
     *     partition on the weight: 0, positive
     *     partition on the existence: exist in the current graph, not exist
     * remove():
     *     partition on the existence: the vertex exists in the graph, not exists
     * sources():
     *     partition on the sources: has sources, doesn't have sources
     * target():
     *     partition on the targets: has targets, doesn't have targets
     */
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // TODO other tests for instance methods of Graph
    @Test
    public void testAddEmptyGraph() {
        Graph<String> graph = emptyInstance();
        String addContent = "What can I say?";
        boolean res = graph.add(addContent);

        assertTrue("the return value of the add must be true", res);
        assertEquals("the size of the graph should be 1", 1, graph.vertices().size());
        assertTrue("the content of the only vertice should be: " + addContent, graph.vertices().contains(addContent));
    }

    @Test
    public void testAddNonEmptyGraph() {
        Graph<String> graph = emptyInstance();
        String addContent = "What can I say?";
        graph.add(addContent);
        boolean res = graph.add(addContent);

        assertFalse("the return value of the add must be false", res);
        assertEquals("the size of the graph should be 1", 1, graph.vertices().size());
        assertTrue("the content of the only vertice should be: " + addContent, graph.vertices().contains(addContent));
    }

    @Test
    public void testSetPosWeightWithOutExist() {
        final Graph<String> graph = emptyInstance();
        final String node1 = "A";
        final String node2 = "B";
        final int originWeight = 100;
        graph.add(node1);
        graph.add(node2);
        final int weight = graph.set(node1, node2, originWeight);

        assertEquals("the current edge previously not exist", 0, weight);
    }

    @Test
    public void testSetPosWeightWithExist() {
        final Graph<String> graph = emptyInstance();
        final String node1 = "A";
        final String node2 = "B";
        final int originWeight = 100;
        graph.add(node1);
        graph.add(node2);
        graph.set(node1, node2, originWeight);

        final int weight = graph.set(node1, node2, 2 * originWeight);

        assertEquals("the previous edge should be: " + originWeight, originWeight, weight);
    }

    @Test
    public void testSetZeroWeightWithExist() {
        final Graph<String> graph = emptyInstance();
        final String node1 = "A";
        final String node2 = "B";
        final int originWeight = 100;
        graph.add(node1);
        graph.add(node2);
        graph.set(node1, node2, 100);

        final int weight = graph.set(node1, node2, 0);
        assertEquals("the previous weight of the edge must be: " + originWeight, originWeight, weight);
    }

    @Test
    public void testSetZeroWeightWithoutExist() {
        final Graph<String> graph = emptyInstance();
        final String node1 = "A";
        final String node2 = "B";
        final int originWeight = 100;
        graph.add(node1);
        graph.add(node2);
        graph.set(node1, node2, originWeight);

        final int weight = graph.set(node2, node1, 0);
        assertEquals("cannot find the given edge", 0, weight);
    }

    @Test
    public void testRemoveWithoutExist() {
        final Graph<String> graph = emptyInstance();
        final String node1 = "A";
        final String node2 = "B";
        final int originWeight = 100;
        graph.add(node1);
        graph.add(node2);
        graph.set(node1, node2, originWeight);

        final String node3 = "c";
        boolean res = graph.remove(node3);
        assertFalse("the result should be false", res);
    }

    @Test
    public void testRemoveWithExist() {
        final Graph<String> graph = emptyInstance();
        final String node1 = "A";
        final String node2 = "B";
        final int originWeight = 100;
        graph.add(node1);
        graph.add(node2);
        graph.set(node1, node2, originWeight);

        boolean res = graph.remove(node1);
        assertTrue("the result should be true", res);
        assertFalse("should not contains the node1", graph.vertices().contains(node1));
        // TODO: 检查边是否被删除
    }

    @Test
    public void testSourceWithoutSources() {
        final Graph<String> graph = emptyInstance();
        final String node1 = "A";
        final String node2 = "B";
        final int originWeight = 100;
        graph.set(node1, node2, originWeight);

        Map<String, Integer> map = graph.sources(node1);
        assertEquals("the node doesn't have sources", 0, map.size());
    }

    @Test
    public void testSourceWithSources() {
        final Graph<String> graph = emptyInstance();
        final String node1 = "A";
        final String node2 = "B";
        final String node3 = "C";
        final int originWeightAC = 100;
        final int originWeightBC = 101;

        graph.set(node1, node3, originWeightAC);
        graph.set(node2, node3, originWeightBC);

        Map<String, Integer> map = graph.sources(node3);
        assertEquals("the node does have 2 sources", 2, map.size());
        assertEquals("node1 -> node3 should be: " + originWeightAC, originWeightAC, map.get(node1).intValue());
        assertEquals("node2 -> node3 should be: " + originWeightBC, originWeightBC, map.get(node2).intValue());
    }

    @Test
    public void testSourceWithoutTargets() {
        final Graph<String> graph = emptyInstance();
        final String node1 = "A";
        final String node2 = "B";
        final int originWeight = 100;
        graph.set(node1, node2, originWeight);

        Map<String, Integer> map = graph.targets(node2);
        assertEquals("the node doesn't have targets", 0, map.size());
    }

    @Test
    public void testSourceWithTargets() {
        final Graph<String> graph = emptyInstance();
        final String node1 = "A";
        final String node2 = "B";
        final String node3 = "C";
        final int originWeightAB = 100;
        final int originWeightAC = 101;

        graph.set(node1, node3, originWeightAC);
        graph.set(node1, node2, originWeightAB);

        Map<String, Integer> map = graph.targets(node1);
        assertEquals("the node does have 2 targets", 2, map.size());
        assertEquals("node1 -> node2 should be: " + originWeightAB, originWeightAB, map.get(node2).intValue());
        assertEquals("node1 -> node3 should be: " + originWeightAC, originWeightAC, map.get(node3).intValue());
    }
}
