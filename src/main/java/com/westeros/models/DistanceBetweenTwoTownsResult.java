package com.westeros.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DistanceBetweenTwoTownsResult {
	private Integer distance;
	private List<String> pathArray;
	
	
	public DistanceBetweenTwoTownsResult(Integer distance, LinkedList<WesterosNode> path) {
		this.distance = distance;
		pathArray = new ArrayList<String>();
        for (WesterosNode node : path) {
        	pathArray.add(node.getName());
        }
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public List<String> getPathArray() {
		return pathArray;
	}
	public void setPathArray(List<String> pathArray) {
		this.pathArray = pathArray;
	}



}
