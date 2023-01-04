package com.graphics.model;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Polygon {
	
	private List<Vertex> vertexes;
	
	private String color;
	
	
	public Polygon() {
	
	}
	
	public Polygon(Vertex ... vertexes) {
		this.vertexes = Arrays.asList(vertexes);
	}
	
	public Polygon(List<Vertex> vertexes, String color) {
		this.vertexes = vertexes;
		this.color = color;
	}
	
	
	public List<Vertex> getVertexes() {
		return vertexes;
	}
	
	public void setVertexes(List<Vertex> vertexes) {
		this.vertexes = vertexes;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
}
