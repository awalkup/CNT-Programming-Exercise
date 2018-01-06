package com.cnt.exercise.model;

import java.util.List;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class GraphTest {

    @Test
    public void testAddEdge() {
        Graph graph = new Graph();
        graph.addVertex(new Vertex(0));
        graph.addVertex(new Vertex(1));
        graph.addVertex(new Vertex(2));

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        System.out.println(graph.toString());
    }

    @Test
    public void testAddEdge_None() {
        Graph graph = new Graph();
        System.out.println(graph.toString());
    }

    @Test
    public void testAddEdge_Disjoint() {
        Graph graph = new Graph();
        graph.addVertex(new Vertex(0));
        graph.addVertex(new Vertex(1));
        graph.addVertex(new Vertex(2));

        graph.addEdge(0, 2);
        System.out.println(graph.toString());
    }

    @Test
    public void testAddEdge_Complex() {
        // Almost certainly not acyclic
        Graph graph = new Graph();
        graph.addVertex(new Vertex(0));
        graph.addVertex(new Vertex(1));
        graph.addVertex(new Vertex(2));
        graph.addVertex(new Vertex(3));
        graph.addVertex(new Vertex(4));
        graph.addVertex(new Vertex(5));
        graph.addVertex(new Vertex(6));
        graph.addVertex(new Vertex(7));
        graph.addVertex(new Vertex(8));
        graph.addVertex(new Vertex(9));

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

    @Test
    public void testTopologicalSort() {
        Graph graph = new Graph();
        graph.addVertex(new Vertex(0));
        graph.addVertex(new Vertex(1));
        graph.addVertex(new Vertex(2));
        graph.addVertex(new Vertex(3));
        graph.addVertex(new Vertex(4));
        graph.addVertex(new Vertex(5));

        graph.addEdge(2, 3);

        graph.addEdge(3, 1);

        graph.addEdge(4, 0);
        graph.addEdge(4, 1);

        graph.addEdge(5, 0);
        graph.addEdge(5, 2);

        List<Integer> sortedVertices = graph.getTopologicalSorting();
        assertThat(sortedVertices.get(0), is(5));
        assertThat(sortedVertices.get(1), is(4));
        assertThat(sortedVertices.get(2), is(2));
        assertThat(sortedVertices.get(3), is(3));
        assertThat(sortedVertices.get(4), is(1));
        assertThat(sortedVertices.get(5), is(0));
    }

    @Test
    public void testTopologicalSort_Complex() {
        Graph graph = new Graph();
        graph.addVertex(new Vertex(2));
        graph.addVertex(new Vertex(3));
        graph.addVertex(new Vertex(5));
        graph.addVertex(new Vertex(7));
        graph.addVertex(new Vertex(8));
        graph.addVertex(new Vertex(9));
        graph.addVertex(new Vertex(10));
        graph.addVertex(new Vertex(11));

        graph.addEdge(3, 8);
        graph.addEdge(3, 10);

        graph.addEdge(5, 11);

        graph.addEdge(7, 8);
        graph.addEdge(7, 11);

        graph.addEdge(8, 9);

        graph.addEdge(11, 9);
        graph.addEdge(11, 10);

        List<Integer> sortedVertices = graph.getTopologicalSorting();
        assertThat(sortedVertices.get(0), is(7));
        assertThat(sortedVertices.get(1), is(5));
        assertThat(sortedVertices.get(2), is(11));
        assertThat(sortedVertices.get(3), is(3));
        assertThat(sortedVertices.get(4), is(10));
        assertThat(sortedVertices.get(5), is(8));
        assertThat(sortedVertices.get(6), is(9));
        assertThat(sortedVertices.get(7), is(2));
    }

    @Test(expected = IllegalStateException.class)
    public void testTopologicalSort_CyclicGraph() {
        Graph graph = new Graph();
        graph.addVertex(new Vertex(0));
        graph.addVertex(new Vertex(1));
        graph.addVertex(new Vertex(2));

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);

        graph.getTopologicalSorting();
        fail("Expected an exception because the graph is not acyclic.");
    }

    @Test
    public void testFindLongestPath() {
        Graph graph = new Graph();
        graph.addVertex(new Vertex(0));
        graph.addVertex(new Vertex(1));
        graph.addVertex(new Vertex(2));
        graph.addVertex(new Vertex(3));
        graph.addVertex(new Vertex(4));
        graph.addVertex(new Vertex(5));

        graph.addEdge(0, 1);
        graph.addEdge(0, 3);

        graph.addEdge(1, 2);

        graph.addEdge(2, 4);
        graph.addEdge(2, 5);

        graph.addEdge(3, 1);
        graph.addEdge(3, 4);

        graph.addEdge(4, 5);

        List<Vertex> longestPath = graph.findLongestPath(0);
        System.out.println(longestPath);
    }
}
