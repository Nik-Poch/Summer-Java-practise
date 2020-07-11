package etu.leti.algorithm;

import etu.leti.field.Cell;
import org.jetbrains.annotations.NotNull;

public class Node implements Comparable<Node>{
    private Integer vertex;
    private Cell from;
    private Cell to;
    private double weight;
    private double minDistance;
    private Node prev;

    public Node() {}

    public Node(Integer vertex, Cell from){
        this.vertex = vertex;
        this.from = from;
        this.minDistance = 0;
    }

    public Node(Integer vertex, Cell from, Cell to) {
        this.vertex = vertex;
        this.from = from;
        this.to = to;
        this.minDistance = Double.MAX_VALUE;
    }

    public Node(Integer vertex, Cell from, Cell to, double weight) {
        this.vertex = vertex;
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.minDistance = Double.MAX_VALUE;
    }

    public Integer getVertex() {
        return vertex;
    }

    public double getWeight() {
        return weight;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    @Override
    public int compareTo(@NotNull Node other) {
        return Double.compare(minDistance, other.minDistance);
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Cell getFrom() {
        return from;
    }

    public Cell getTo() {
        return to;
    }
}
