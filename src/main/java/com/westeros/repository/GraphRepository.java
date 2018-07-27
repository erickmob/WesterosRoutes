package com.westeros.repository;

import org.springframework.data.repository.CrudRepository;

import com.westeros.models.Graph;

public interface GraphRepository extends CrudRepository<Graph, Long> {

}
