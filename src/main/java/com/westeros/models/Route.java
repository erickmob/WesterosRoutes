package com.westeros.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Route {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String source;
	private String target;
	private Integer distance;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public Route(String source, String target, Integer distance) {
		super();
		this.source = source;
		this.target = target;
		this.distance = distance;
	}
	
	 
    public Route() {
        super();
    }
}
