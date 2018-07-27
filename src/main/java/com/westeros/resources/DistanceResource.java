package com.westeros.resources;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.westeros.exceptions.NotFoundException;
import com.westeros.models.WesterosGraph;
import com.westeros.models.WesterosNode;
import com.westeros.models.DistanceBetweenTwoTownsResult;
import com.westeros.models.Graph;
import com.westeros.models.PathNodes;
import com.westeros.models.Route;
import com.westeros.repository.GraphRepository;
import com.westeros.utils.DijkstraAlgorithm;

@RestController
@RequestMapping("/distance")
public class DistanceResource {
	
	@Autowired
	private GraphRepository gr;

	@PostMapping(value = "/{graphId}", produces = "application/json")
	@ResponseBody
	public Object distanceForPath(@PathVariable Long graphId,@RequestBody PathNodes path){
		Graph graph = gr.findOne(graphId);
		if(graph != null){
			if(graph.getData().isEmpty() || graph.getData().size() == 1){
				return 0;
			}else{
				String from = null;
				Integer distance = 0;
				Route route = null;
				for(String town : path.getPath()){
					if(from == null){
						from = town;
					}else{
						route = graph.getRouteBetWeen(from, town);
						if(route != null){
							distance += route.getDistance();	
							from = town;
						}else{
							distance = -1;
							break;
						}
					}
				}
				return distance;
			}
			
		}else{
			throw new NotFoundException();
		}
		
	}

	@PostMapping(value = "/{graphId}/from/{town1}/to/{town2}", produces = "application/json")
	@ResponseBody
	public Object distanceBetweenTwoTowns(@PathVariable Long graphId, @PathVariable String town1,@PathVariable String town2){
		
		
		DijkstraAlgorithm dijkstra = null;
        LinkedList<WesterosNode> path = null;
		WesterosNode sourceTown = null;
		WesterosNode targetTown = null;
		Graph graph = gr.findOne(graphId);
		
		
		if(town1.equals(town2)){
			return 0;
		}
		
		if(graph != null){

			WesterosGraph westerosGraph = new WesterosGraph(graph);
			sourceTown = westerosGraph.getNodeFromListByName(town1);
			targetTown = westerosGraph.getNodeFromListByName(town2);
			
	        dijkstra = new DijkstraAlgorithm(westerosGraph);
	        dijkstra.execute(sourceTown);
	        path = dijkstra.getPath(targetTown);
	        if(path != null){
		        return new DistanceBetweenTwoTownsResult(dijkstra.getDistance().get(targetTown), path);
	        }else{
	        	return -1;
	        }
		}
		else{
			throw new NotFoundException();
		}
		
	}

}
