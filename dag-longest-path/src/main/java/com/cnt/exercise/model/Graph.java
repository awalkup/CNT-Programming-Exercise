package com.cnt.exercise.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class Graph {

    private Map<Integer, Vertex> vertices = new HashMap<>();

    public void addVertex(Vertex vertex) throws Exception {
        if (vertices.containsKey(vertex.getId())) {
            throw new Exception("Vertex " + vertex.getId() + " already exists in the graph");
        }
        vertices.putIfAbsent(vertex.getId(), vertex);
    }

    public void addEdge(Integer from, Integer to) throws Exception {
        if (!vertices.containsKey(from)) {
            throw new Exception("Vertex " + from + " does not exist in the graph.");
        } else if (!vertices.containsKey(to)) {
            throw new Exception("Vertex " + to + " does not exist in the graph.");
        }

        vertices.get(from).addEdge(vertices.get(to));
    }

    public void addEdge(Vertex from, Vertex to) {
        from.addEdge(to);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        vertices.values().forEach(vertex -> stringBuilder.append(vertex.getId())
                                                         .append("-->")
                                                         .append(StringUtils.join(vertex.getEdges()
                                                                                        .stream()
                                                                                        .map(Vertex::getId)
                                                                                        .collect(Collectors.toSet()), ", "))
                                                         .append("\n"));
        return stringBuilder.toString();
    }

    public List<Integer> getTopologicalSorting() {
        List<Integer> sortedVertices = new ArrayList<>();
        Set<Integer> seenVertices = new HashSet<>();

        vertices.values().forEach(vertex -> visit(vertex, sortedVertices, seenVertices));

        Collections.reverse(sortedVertices);
        return sortedVertices;
    }

    private void visit(Vertex currentVertex, List<Integer> sortedVertices, Set<Integer> seenVertices) {
        if (sortedVertices.contains(currentVertex.getId())) {
            // Already seen this vertex
            return;
        } else if (seenVertices.contains(currentVertex.getId())) {
            throw new RuntimeException("This graph is not acyclic, cannot create a topological sorting.");
        }

        seenVertices.add(currentVertex.getId());
        for (Vertex vertex : currentVertex.getEdges()) {
            visit(vertex, sortedVertices, seenVertices);
        }

        sortedVertices.add(currentVertex.getId());
    }
}
