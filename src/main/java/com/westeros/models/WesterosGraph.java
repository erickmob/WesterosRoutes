package com.westeros.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WesterosGraph {
	private final List<WesterosNode> nodes;
    private final List<WesterosEdge> edges;

    public WesterosGraph(List<WesterosNode> nodes, List<WesterosEdge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }
    
    public WesterosGraph(Graph graph){
    	List<WesterosNode> nodeList = new ArrayList<WesterosNode>();
		List<WesterosEdge> edgeList = new ArrayList<WesterosEdge>();
		Map<String, List<Route>> groupBySource = 
				graph.getData().stream().collect(Collectors.groupingBy(Route::getSource));
		
		for(Map.Entry<String, List<Route>> entry : groupBySource.entrySet()){
			WesterosNode node = new WesterosNode(entry.getKey());
			nodeList.add(node);
		}
		
		for(Map.Entry<String, List<Route>> entry : groupBySource.entrySet()){
			WesterosNode nodeSource = nodeList.stream()                        
	                .filter(x -> entry.getKey().equals(x.getName())) 
	                .findAny().orElse(null);
			if(nodeSource != null){
				for(Route route : entry.getValue()){
					WesterosNode target = nodeList.stream()                        
			                .filter(x -> route.getTarget().equals(x.getName())) 
			                .findAny().orElse(null);
					if(target != null){
						edgeList.add(new WesterosEdge(nodeSource, target, route.getDistance()));					
					}                                      
				}
			}
		}
		this.nodes = nodeList;
		this.edges = edgeList;
    	
    }

    public List<WesterosNode> getNodes() {
        return nodes;
    }

    public List<WesterosEdge> getEdges() {
        return edges;
    }
    
    public WesterosNode getNodeFromListByName(String name){
    	WesterosNode node = this.nodes.stream()
    			.filter(x -> name.equals(x.getName()))
                .findAny()
                .orElse(null);  
    	return node;
    }

}
