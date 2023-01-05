package com.graphics.model;

import java.util.Arrays;
import java.util.List;

public class Polygon {
	
	private List<Vertex> vertices;
	
	private String color;
	
	
	public Polygon() {
	
	}
	
	public Polygon(Vertex ... vertices) {
		this.vertices = Arrays.asList(vertices);
	}
	
	public Polygon(List<Vertex> vertices, String color) {
		this.vertices = vertices;
		this.color = color;
	}
	
	
	public List<Vertex> getVertices() {
		return vertices;
	}
	
	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
}
