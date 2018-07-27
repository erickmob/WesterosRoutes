package com.westeros.models;

import java.util.ArrayList;
import java.util.List;

public class MaxStopsResult {
	private List<RouteStops> routes = new ArrayList<RouteStops>();

	public List<RouteStops> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteStops> routes) {
		this.routes = routes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((routes == null) ? 0 : routes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaxStopsResult other = (MaxStopsResult) obj;
		if (routes == null) {
			if (other.routes != null)
				return false;
		} else if (!routes.equals(other.routes))
			return false;
		return true;
	}
}
