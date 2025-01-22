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
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   AF(vertices) = a graph composed of all the vertices
    // Representation invariant:
    //   1. completeness: all the vertices in the graph are in the vertices
    //   2. no repeated vertex

    // Safety from rep exposure:
    //   1. vertices are private
    //   2. vertices are mutable List, some functions make defensive copy to avoid sharing the rep's vertices to the clients

    public ConcreteVerticesGraph() {
    }

    /*
     * check that rep invariant is true
     * TODO: high complexity checking strategy
     */
    private void checkRep() {

        // no repeated vertex
        Set<L> verticesSet = new HashSet<>();
        for (final Vertex<L> v: this.vertices) {
            verticesSet.add(v.getLabel());
        }
        assert verticesSet.size() == this.vertices.size();

        // check completeness, but with high time complexity
        for (final Vertex<L> v: this.vertices) {
            Set<L> neighbours = v.getNeighbours().keySet();
            assert verticesSet.containsAll(neighbours);
        }
    }
    
    @Override public boolean add(L vertex) {
        for (final Vertex<L> v: this.vertices) {
            if (v.getLabel().equals(vertex)) {
                return false;
            }
        }
        this.vertices.add(new Vertex<L>(vertex));
        checkRep();
        return true;
    }
    
    @Override public int set(L source, L target, int weight) {

        // check existence and add vertices
        Vertex<L> sourceVertex = null, targetVertex = null;
        for (final Vertex<L> v: this.vertices) {
            if (v.getLabel().equals(source)) {
                sourceVertex = v;
            }
            if (v.getLabel().equals(target)) {
                targetVertex = v;
            }
        }
        if (sourceVertex == null) {
            sourceVertex = new Vertex<L>(source);
            this.vertices.add(sourceVertex);
        }
        if (targetVertex == null) {
            targetVertex = new Vertex<L>(target);
            this.vertices.add(targetVertex);
        }

        // update the vertex
        int res = sourceVertex.set(target, weight);
        checkRep();
        return res;
    }
    
    @Override public boolean remove(L vertex) {
        // check whether the vertex is included in the graph
        // remove the vertex if exists
        // remove the edges between vertex and others
        boolean contains = false;
        for (final Vertex<L> v: this.vertices) {
            v.set(vertex, 0);
            if (v.getLabel().equals(vertex)) {
                this.vertices.remove(v);
                contains = true;
            }
        }
        checkRep();
        return contains;
    }
    
    @Override public Set<L> vertices() {
        Set<L> verticesSet = new HashSet<>();
        for (final Vertex<L> v: this.vertices) {
            verticesSet.add(v.getLabel());
        }
        return verticesSet;
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L, Integer> map = new HashMap<>();
        for (final Vertex<L> v: this.vertices) {
            if (v.hasEdgeTo(target)) {
                map.put(v.getLabel(), v.weightTo(target));
            }
        }
        return map;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        Map<L, Integer> map = new HashMap<>();
        for (final Vertex<L> v: this.vertices) {
            if (v.getLabel().equals(source)) {
                map = new HashMap<>(v.getNeighbours());
            }
        }
        return map;
    }

    @Override public String toString() {
        StringBuilder out = new StringBuilder();
        for (final Vertex<L> v : vertices) {
            out.append(v.toString());
        }
        return out.toString();
    }
}

/**
 * A vertex with all its neighbours recorded.
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {
    
    private final L label;
    private final Map<L, Integer> neighbours = new HashMap<>();
    
    // Abstraction function:
    //   AF(label, neighbours) = a vertex with its neighbours of some distances
    // Representation invariant:
    //   label: True
    //   neighbours: the distance must be positive( and not includes itself )
    // Safety from rep exposure:
    //   1. all fields are private
    //   2. the neighbours are mutable map,
    //      some functions make defensive copy to avoid sharing the rep's neighbours object to the clients

    public Vertex(L label) {
        this.label = label;
    }

    private void checkRep() {
        for (final Map.Entry<L, Integer> e: neighbours.entrySet()) {
//            assert !e.getKey().equals(label) && e.getValue() > 0;
            assert e.getValue() > 0;
        }
    }

    /**
     * @return the label of the vertex
     */
    public L getLabel() {
        return label;
    }

    /**
     * check whether this vertex points to the target vertex
     * @param target the target vertex
     * @return true if this vertex points to the target vertex, false otherwise
     */
    public boolean hasEdgeTo(L target) {
        for (final L t: this.neighbours.keySet()) {
            if (t.equals(target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * get the weight of the edge from this vertex to target vertex if edge exists
     * @param target the target vertex
     * @return the weight of the edge, 0 otherwise
     */
    public int weightTo(L target) {
        Integer dist = neighbours.get(target);
        return Objects.requireNonNullElse(dist, 0);
    }

    /**
     * @return the neighbours of the vertex
     */
    public Map<L, Integer> getNeighbours() {
        return new HashMap<>(this.neighbours);  // defensive copy
    }

    /**
     * Add, change, or remove a weighted directed edge starting from the vertex
     * if weight is nonzero, add an edge or update the weight of the edge starting from this to target
     * if weight is zero,    drop the edge from this to target if it exists
     * @param target the end point of the edge
     * @param weight the weight of the edge, requires >= 0 (0 means drop the edge)
     * @return the previous edge weight or zero if there was no such edge
     */
    public int set(L target, int weight) {
        if (weight < 0) {
            throw new RuntimeException("weight should be non-negative");
        }
        // remove the old value and record the previous weight
        int lastWeight = 0;
        for (final L t: this.neighbours.keySet()) {
            if (t.equals(target)) {
                lastWeight = this.neighbours.get(t);
                this.neighbours.remove(t);
            }
        }

        // update the weight if weight > 0
        if (weight > 0) {
            this.neighbours.put(target, weight);
        }
        checkRep();
        return lastWeight;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (Map.Entry<L, Integer> entry : neighbours.entrySet()) {
            out.append(label);
            out.append(" ==> ");
            out.append(entry.getKey());
            out.append(": ");
            out.append(entry.getValue());
            out.append("\n");
        }
        return out.toString();
    }
}
