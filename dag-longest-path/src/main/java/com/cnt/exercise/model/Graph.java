package com.cnt.exercise.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.apache.commons.lang3.StringUtils;

public class Graph {

    private List<List<Integer>> adjacencyList;

    public Graph(int vertices) {
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(i, new ArrayList<>());
        }
    }

    public void addEdge(int from, int to) {
        adjacencyList.get(from).add(to);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0, adjacencyList.size())
                 .forEach(vertex -> stringBuilder.append(vertex)
                                                 .append("-->")
                                                 .append(StringUtils.join(adjacencyList.get(vertex), ", "))
                                                 .append("\n"));
        return stringBuilder.toString();
    }
}
