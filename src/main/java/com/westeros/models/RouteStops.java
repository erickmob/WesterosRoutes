package com.westeros.models;

public class RouteStops {
	String route;
	Integer stops;
	
	
	
	public RouteStops(String routes) {
		this.route = routes;
		this.stops = routes.length() -1;
	}



	public String getRoute() {
		return route;
	}



	public void setRoute(String route) {
		this.route = route;
	}



	public Integer getStops() {
		return stops;
	}



	public void setStops(Integer stops) {
		this.stops = stops;
	}
	

}
