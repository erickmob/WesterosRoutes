package com.westeros.resources;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.westeros.exceptions.NotFoundException;
import com.westeros.models.Graph;
import com.westeros.models.MaxStopsResult;
import com.westeros.models.Route;
import com.westeros.models.RouteStops;
import com.westeros.repository.GraphRepository;

@RestController
@RequestMapping("/routes")
public class RoutesResource {
	
	@Autowired
	private GraphRepository gr;
	
	@PostMapping(value = "/{graphId}/from/{town1}/to/{town2}", produces = "application/json")
	@ResponseBody
	public MaxStopsResult availableRoutes(@PathVariable Long graphId, @PathVariable String town1,@PathVariable String town2, @RequestParam(value="maxStops", required=false) Long maxStops){
		String path = new String();
		MaxStopsResult routeStopList = new MaxStopsResult();
		List<Route> visited = new ArrayList<Route>();
		Graph graph = gr.findOne(graphId);
		
		if(graph != null){
			sourceReached(graph,routeStopList.getRoutes(), town1, town2, path, visited);
			if(maxStops != null){
				routeStopList.setRoutes(routeStopList.getRoutes().stream().filter(p -> p.getStops() <= maxStops).sorted(Comparator.comparing(RouteStops::getStops)).collect(Collectors.toList()));
			}
			
		}else{
			throw new NotFoundException();
		}
		return routeStopList;
		
		
	}
	
    private void sourceReached(Graph graph,List<RouteStops> routeStopList,  String town1, String town2,  String path, List<Route> visited) {

    	List<Route> routes = graph.getAllRoutesTo(town2);
    	
    	if(routes.size() > 0){
    		path = town2.concat(path);

        	for(Route r : routes){

        		if(!visited.contains(r)) {

        			visited.add(r);
            		
            		if(town1.equals(r.getSource())){
            			path = town1.concat(path);
            			routeStopList.add(new RouteStops(path));
                		path = "";
                		visited.clear();
            		}else{
                		town2 = r.getSource();
                		sourceReached(graph, routeStopList, town1,town2, path, visited);
            		}
        		}
        	}
    	}else{
    		path = "";
    	}
		
	}

}
