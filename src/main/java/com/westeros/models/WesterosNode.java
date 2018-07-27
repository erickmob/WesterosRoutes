package com.westeros.models;

public class WesterosNode {
    final private String name;


    public WesterosNode(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return name;
    }
}
