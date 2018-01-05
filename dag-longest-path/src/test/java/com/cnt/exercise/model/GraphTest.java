package com.cnt.exercise.model;

import org.junit.Test;

public class GraphTest {

    @Test
    public void testAddEdge() {
        Graph graph = new Graph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        System.out.println(graph.toString());
    }

    @Test
    public void testAddEdge_None() {
        Graph graph = new Graph(0);
        System.out.println(graph.toString());
    }

    @Test
    public void testAddEdge_Disjoint() {
        Graph graph = new Graph(3);
        graph.addEdge(0, 2);
        System.out.println(graph.toString());
    }

    @Test
    public void testAddEdge_Complex() {
        // Almost certainly not acyclic
        Graph graph = new Graph(10);
        graph.addEdge(0, 3);
        graph.addEdge(0, 6);
        graph.addEdge(0, 9);

        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(1, 5);
        graph.addEdge(1, 9);

        graph.addEdge(2, 6);
        graph.addEdge(2, 8);

        graph.addEdge(3, 6);
        graph.addEdge(3, 7);
        graph.addEdge(3, 2);
        graph.addEdge(3, 9);
        graph.addEdge(3, 4);

        graph.addEdge(4, 5);

        graph.addEdge(5, 8);
        graph.addEdge(5, 3);

        graph.addEdge(6, 9);
        graph.addEdge(6, 1);

        graph.addEdge(7, 4);

        graph.addEdge(8, 7);
        graph.addEdge(8, 1);

        System.out.println(graph.toString());
    }
}
