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

    public void addVertex(Vertex vertex) {
        if (vertices.containsKey(vertex.getId())) {
            throw new IllegalArgumentException("Vertex " + vertex.getId() + " already exists in the graph");
        }
        vertices.putIfAbsent(vertex.getId(), vertex);
    }

    public void addEdge(Integer from, Integer to) {
        if (!vertices.containsKey(from)) {
            throw new IllegalArgumentException("Vertex " + from + " does not exist in the graph.");
        } else if (!vertices.containsKey(to)) {
            throw new IllegalArgumentException("Vertex " + to + " does not exist in the graph.");
        }

        vertices.get(from).addEdge(vertices.get(to));
    }

    public void addEdge(Vertex from, Vertex to) {
        if (from == null) {
            throw new IllegalArgumentException("From vertex cannot be null");
        } else if (to == null) {
            throw new IllegalArgumentException("To vertex cannot be null");
        }
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
            throw new IllegalStateException("This graph is not acyclic, cannot create a topological sorting.");
        }

        seenVertices.add(currentVertex.getId());
        for (Vertex vertex : currentVertex.getEdges()) {
            visit(vertex, sortedVertices, seenVertices);
        }

        sortedVertices.add(currentVertex.getId());
    }

    public List<Vertex> findLongestPath(Integer sourceVertexId) {
        Vertex sourceVertex = vertices.get(sourceVertexId);
        if (sourceVertex == null) {
            throw new IllegalArgumentException("Source vertex " + sourceVertexId + " does not exist in the graph.");
        }

        List<Vertex> longestPath = findLongestPathHelper(sourceVertex, new HashMap<>());
        Collections.reverse(longestPath);
        return longestPath;
    }

    private List<Vertex> findLongestPathHelper(Vertex currentVertex, Map<Integer, List<Vertex>> longestPathMap) {
        if (longestPathMap.containsKey(currentVertex.getId())) {
            return longestPathMap.get(currentVertex.getId());
        }

        List<Vertex> currentLongestPath = new ArrayList<>();
        for (Vertex vertex : currentVertex.getEdges()) {
            List<Vertex> vertexPath = findLongestPathHelper(vertex, longestPathMap);
            if (vertexPath.size() > currentLongestPath.size()) {
                currentLongestPath = vertexPath;
            }
        }
        currentLongestPath.add(currentVertex);

        longestPathMap.put(currentVertex.getId(), currentLongestPath);
        return new ArrayList<>(currentLongestPath);
    }
}
