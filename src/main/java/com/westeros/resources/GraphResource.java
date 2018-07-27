package com.westeros.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.westeros.exceptions.NotFoundException;
import com.westeros.models.Graph;
import com.westeros.repository.GraphRepository;

@RestController
@RequestMapping("/graph")
public class GraphResource {
	
	@Autowired
	private GraphRepository gr;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Graph save(@RequestBody Graph graph){
		return gr.save(graph);		
	}
	
	@GetMapping(value = "/{id}")
	public @ResponseBody Graph find(@PathVariable Long id){
		Graph graph = gr.findOne(id);
		if(graph == null){
			throw new NotFoundException();
		}
		return graph;
	}


}
