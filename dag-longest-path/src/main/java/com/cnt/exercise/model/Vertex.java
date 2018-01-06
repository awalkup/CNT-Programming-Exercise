package com.cnt.exercise.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Vertex {

    private Integer id;
    private Set<Vertex> edges = new HashSet<>();

    public Vertex(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Set<Vertex> getEdges() {
        return edges;
    }

    public void addEdge(Vertex vertex) {
        edges.add(vertex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vertex vertex = (Vertex) o;
        return Objects.equals(getId(), vertex.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
}
