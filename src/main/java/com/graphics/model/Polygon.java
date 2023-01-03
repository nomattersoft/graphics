package com.graphics.model;

import java.util.Arrays;
import java.util.List;

public class Polygon {
	
	private List<Vertex> vertexes;
	
	
	public Polygon() {
	
	}
	
	public Polygon(Vertex ... vertexes) {
		this.vertexes = Arrays.asList(vertexes);
	}
	
	public Polygon(List<Vertex> vertexes) {
		this.vertexes = vertexes;
	}
	
	
	public List<Vertex> getVertexes() {
		return vertexes;
	}
	
	public void setVertexes(List<Vertex> vertexes) {
		this.vertexes = vertexes;
	}
}
