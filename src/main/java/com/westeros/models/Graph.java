package com.westeros.models;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Graph{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(cascade = {CascadeType.ALL})
    private List<Route> data = new ArrayList<Route>();
    

    public void addRoute(Route routeA) {
    	data.add(routeA);
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Route> getData() {
		return data;
	}

	public void setData(List<Route> data) {
		this.data = data;
	}
	
	public Route getRouteBetWeen(String from, String town){
		
		Route route = this.getData().stream()
    			.filter(x -> from.equals(x.getSource()) && town.equals(x.getTarget()))
                .findAny()
                .orElse(null);  
		
		return route;
	}

	
	public List<Route> getAllRoutesTo(String town){
		
		List<Route> routes = this.getData().stream().filter(p -> p.getTarget().equals(town)).collect(Collectors.toList());
		
		return routes;
	}

}