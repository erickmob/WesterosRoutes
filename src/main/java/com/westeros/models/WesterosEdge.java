package com.westeros.models;

public class WesterosEdge {
    private final WesterosNode source;
    private final WesterosNode destination;
    private final int weight;

    public WesterosEdge( WesterosNode source, WesterosNode destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public WesterosNode getDestination() {
        return destination;
    }

    public WesterosNode getSource() {
        return source;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }
}
