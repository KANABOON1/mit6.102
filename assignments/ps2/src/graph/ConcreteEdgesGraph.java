/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.*;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //   AF(vertices, edges) = directed graph
    // Representation invariant:
    //   1. vertices stores all the vertices in the graph(no repeats, guaranteed by Set)
    //   2. edges stores all the edges in the graph,
    //      all the edges in the graph should be valid(positive and source != target)
    //      no repeats
    //   3. vertices and edges should be consistent,
    //      that is to say all the vertices contained by edges are in the vertices
    // Safety from rep exposure:
    //   1. all the fields are private
    //   2. vertices are mutable Set,
    //      some functions make defensive copies to avoid sharing the rep's vertices object with clients
    //   3. edges are mutable List,
    //      some functions make defensive copies to avoid sharing the rep's vertices object with clients

    public ConcreteEdgesGraph() {
    }

    private void checkRep() {
        // check edges no repeats
        Set<Edge<L>> edgeSet = new HashSet<>(edges);
        assert edgeSet.size() == edges.size();

        // check all the vertices in the edges are in the vertices
        Set<L> verticesSetByEdge = new HashSet<>();
        for (final Edge<L> e: edges) {
            verticesSetByEdge.add(e.getSource());
            verticesSetByEdge.add(e.getTarget());
        }
        assert vertices.containsAll(verticesSetByEdge);
    }
    
    @Override public boolean add(L vertex) {
        if (vertices.contains(vertex)) {
            return false;
        }
        vertices.add(vertex);
        checkRep();
        return true;
    }
    
    @Override public int set(L source, L target, int weight) {
        // fail fast
        if (weight < 0) {
            throw new IllegalArgumentException("weight must be non-negative value");
        }

        // remove the old edge and remember the weight
        int lastWeight = 0;
        for (final Edge<L> e: edges) {
            if (e.getSource().equals(source) && e.getTarget().equals(target)) {
                edges.remove(e);
                lastWeight = e.getWeight();
                break;
            }
        }

        // add or modify a new edge
        if (weight != 0) {
            vertices.add(source);
            vertices.add(target);
            Edge<L> edge = new Edge<L>(source, target, weight);
            edges.add(edge);
        }

        checkRep();
        return lastWeight;
    }
    
    @Override public boolean remove(L vertex) {
        // delete the vertex from the graph
        boolean contains = this.vertices.remove(vertex);
        if (!contains) {
            return false;
        }

        // any edges to or from the vertex are also removed
        this.edges.removeIf(e -> e.getTarget().equals(vertex) || e.getSource().equals(vertex));
        this.checkRep();
        return true;
    }
    
    @Override public Set<L> vertices() {
        return new HashSet<>(this.vertices);   // defensive copy
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L, Integer> sourceMap = new HashMap<>();
        for (final Edge<L> e: this.edges) {
            if (e.getTarget().equals(target)) {
                sourceMap.put(e.getSource(), e.getWeight());
            }
        }
        checkRep();
        return sourceMap;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        Map<L, Integer> targetMap = new HashMap<>();
        for (final Edge<L> e: this.edges) {
            if (e.getSource().equals(source)) {
                targetMap.put(e.getTarget(), e.getWeight());
            }
        }
        checkRep();
        return targetMap;
    }

    /**
     * format the String representation of the edgeGraph
     * @return the String representation of the edgeGraph
     *         e.g.
     *         a ==> b: 3
     *         a ==> c: 2
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (final Edge<L> edge : edges) {
            output.append(edge.toString());
            output.append("\n");
        }
        return output.toString();
    }
    
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    // TODO fields
    private final L   source;
    private final L   target;
    private final int weight;

    // Abstraction function:
    //   AF(source, target, weight) = an edge starting from source and pointing to target with a fixed weight
    // Representation invariant:
    //   source != target
    //   weight > 0
    // Safety from rep exposure:
    //   all fields are private
    //   all fields are immutable

    /**
     * @param source the start point of the directed edge
     * @param target the end point of the directed edge
     * @param weight the weight of the directed edge, requires to be positive
     */
    public Edge(L source, L target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        checkRep();
    }

    private void checkRep() {
        assert !this.source.equals(this.target) && this.weight > 0;
    }

    public L getSource() {
        return this.source;
    }

    public L getTarget() {
        return this.target;
    }

    public int getWeight() {
        return this.weight;
    }

    /**
     * format the String representation of the edge
     * @return the String representation of the edge
     *         e.g.
     *         a ==> b: 3
     */
    @Override
    public String toString() {

        return  this.source +
                " ==> " +
                this.target +
                ": " +
                this.weight;
    }
    
}
