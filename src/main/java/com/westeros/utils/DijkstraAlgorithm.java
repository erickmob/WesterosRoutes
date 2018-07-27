package com.westeros.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.westeros.models.WesterosEdge;
import com.westeros.models.WesterosGraph;
import com.westeros.models.WesterosNode;


public class DijkstraAlgorithm {
	private final List<WesterosNode> nodes;
    private final List<WesterosEdge> edges;
    private Set<WesterosNode> settledNodes;
    private Set<WesterosNode> unSettledNodes;
    private Map<WesterosNode, WesterosNode> predecessors;
    private Map<WesterosNode, Integer> distance;

    public DijkstraAlgorithm(WesterosGraph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<WesterosNode>(graph.getNodes());
        this.edges = new ArrayList<WesterosEdge>(graph.getEdges());
    }

    public void execute(WesterosNode source) {
        settledNodes = new HashSet<WesterosNode>();
        unSettledNodes = new HashSet<WesterosNode>();
        distance = new HashMap<WesterosNode, Integer>();
        predecessors = new HashMap<WesterosNode, WesterosNode>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
        	WesterosNode node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(WesterosNode node) {
        List<WesterosNode> adjacentNodes = getNeighbors(node);
        for (WesterosNode target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    public Integer getDistance(WesterosNode node, WesterosNode target) {
        for (WesterosEdge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<WesterosNode> getNeighbors(WesterosNode node) {
        List<WesterosNode> neighbors = new ArrayList<WesterosNode>();
        for (WesterosEdge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private WesterosNode getMinimum(Set<WesterosNode> westerosNodes) {
    	WesterosNode minimum = null;
        for (WesterosNode westerosNode : westerosNodes) {
            if (minimum == null) {
                minimum = westerosNode;
            } else {
                if (getShortestDistance(westerosNode) < getShortestDistance(minimum)) {
                    minimum = westerosNode;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(WesterosNode westerosNode) {
        return settledNodes.contains(westerosNode);
    }

    private int getShortestDistance(WesterosNode destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<WesterosNode> getPath(WesterosNode target) {
        LinkedList<WesterosNode> path = new LinkedList<WesterosNode>();
        WesterosNode step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

	public Map<WesterosNode, Integer> getDistance() {
		return distance;
	}

	public void setDistance(Map<WesterosNode, Integer> distance) {
		this.distance = distance;
	}
}
