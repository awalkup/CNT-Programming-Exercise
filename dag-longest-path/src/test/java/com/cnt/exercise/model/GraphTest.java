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
        fail("Expected IllegalStateException because the graph is not acyclic.");
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
        assertThat(longestPath.size(), is(6));
        assertThat(longestPath.get(0).getId(), is(0));
        assertThat(longestPath.get(1).getId(), is(3));
        assertThat(longestPath.get(2).getId(), is(1));
        assertThat(longestPath.get(3).getId(), is(2));
        assertThat(longestPath.get(4).getId(), is(4));
        assertThat(longestPath.get(5).getId(), is(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindLongestPath_NoVertices() {
        Graph graph = new Graph();

        graph.findLongestPath(0);
        fail("Expected IllegalArgumentException");
    }

    @Test
    public void testFindLongestPath_DisjointGraph() {
        Graph graph = new Graph();
        graph.addVertex(new Vertex(0));
        graph.addVertex(new Vertex(1));
        graph.addVertex(new Vertex(2));
        graph.addVertex(new Vertex(3));
        graph.addVertex(new Vertex(4));

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);

        graph.addEdge(1, 2);

        graph.addEdge(4, 3);

        List<Vertex> longestPath = graph.findLongestPath(0);
        assertThat(longestPath.size(), is(3));
        assertThat(longestPath.get(0).getId(), is(0));
        assertThat(longestPath.get(1).getId(), is(1));
        assertThat(longestPath.get(2).getId(), is(2));
    }

    @Test
    public void testFindLongestPath_NoEdges() {
        Graph graph = new Graph();
        graph.addVertex(new Vertex(0));
        graph.addVertex(new Vertex(1));
        graph.addVertex(new Vertex(2));

        List<Vertex> longestPath = graph.findLongestPath(2);
        assertThat(longestPath.size(), is(1));
        assertThat(longestPath.get(0).getId(), is(2));
    }

    @Test
    public void testFindLongestPath_Complex() {
        Graph graph = new Graph();
        graph.addVertex(new Vertex(1));
        graph.addVertex(new Vertex(2));
        graph.addVertex(new Vertex(3));
        graph.addVertex(new Vertex(4));
        graph.addVertex(new Vertex(5));
        graph.addVertex(new Vertex(6));
        graph.addVertex(new Vertex(7));
        graph.addVertex(new Vertex(8));
        graph.addVertex(new Vertex(9));
        graph.addVertex(new Vertex(10));
        graph.addVertex(new Vertex(11));
        graph.addVertex(new Vertex(12));
        graph.addVertex(new Vertex(13));
        graph.addVertex(new Vertex(14));
        graph.addVertex(new Vertex(15));

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(1, 5);
        graph.addEdge(1, 6);
        graph.addEdge(1, 8);
        graph.addEdge(1, 9);
        graph.addEdge(1, 10);
        graph.addEdge(1, 12);
        graph.addEdge(1, 13);
        graph.addEdge(1, 14);
        graph.addEdge(1, 15);

        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(2, 7);

        graph.addEdge(3, 4);
        graph.addEdge(3, 7);
        graph.addEdge(3, 9);

        graph.addEdge(5, 7);
        graph.addEdge(5, 9);

        graph.addEdge(8, 9);
        graph.addEdge(8, 10);
        graph.addEdge(8, 11);

        graph.addEdge(9, 10);
        graph.addEdge(9, 11);
        graph.addEdge(9, 12);
        graph.addEdge(9, 13);

        graph.addEdge(10, 12);

        graph.addEdge(11, 13);
        graph.addEdge(11, 14);
        graph.addEdge(11, 15);

        System.out.println(graph);

        List<Vertex> longestPath = graph.findLongestPath(1);
        System.out.println(longestPath);
        assertThat(longestPath.size(), is(6));
        assertThat(longestPath.get(0).getId(), is(1));
        assertThat(longestPath.get(1).getId(), is(2));
        assertThat(longestPath.get(2).getId(), is(5));
        assertThat(longestPath.get(3).getId(), is(9));
        assertThat(longestPath.get(4).getId(), is(10));
        assertThat(longestPath.get(5).getId(), is(12));
    }

    @Test
    public void testFindLongestPath_Complex2() {
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
        graph.addVertex(new Vertex(10));
        graph.addVertex(new Vertex(11));
        graph.addVertex(new Vertex(12));
        graph.addVertex(new Vertex(13));

        graph.addEdge(0, 4);
        graph.addEdge(0, 5);
        graph.addEdge(0, 11);

        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(1, 8);

        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(2, 9);

        graph.addEdge(3, 6);
        graph.addEdge(3, 13);

        graph.addEdge(4, 7);

        graph.addEdge(5, 8);
        graph.addEdge(5, 12);

        graph.addEdge(6, 5);

        graph.addEdge(8, 7);

        graph.addEdge(9, 10);
        graph.addEdge(9, 11);

        graph.addEdge(10, 13);

        graph.addEdge(12, 9);

        System.out.println(graph);

        List<Vertex> longestPath = graph.findLongestPath(2);
        System.out.println(longestPath);
        assertThat(longestPath.size(), is(7));
        assertThat(longestPath.get(0).getId(), is(2));
        assertThat(longestPath.get(1).getId(), is(6));
        assertThat(longestPath.get(2).getId(), is(5));
        assertThat(longestPath.get(3).getId(), is(12));
        assertThat(longestPath.get(4).getId(), is(9));
        assertThat(longestPath.get(5).getId(), is(10));
        assertThat(longestPath.get(6).getId(), is(13));
    }
}
