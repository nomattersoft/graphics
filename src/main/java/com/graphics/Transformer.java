package com.graphics;

import com.graphics.model.Polygon;
import com.graphics.model.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Transformer {
	
	private static int camera = -320;
	private static int plane = -40;
	
	public static int perspective(int p, int z) {
		return p*(camera - plane)/(camera - z);
	}
	
	public static Vertex perspective(Vertex v) {
		return new Vertex(
				perspective(v.getX(), v.getZ()),
				perspective(v.getY(), v.getZ()),
				v.getZ());
	}
	
	public static Polygon perspective(Polygon p) {
		return new Polygon(p.getVertices().stream().map(Transformer::perspective).collect(Collectors.toList()), p.getColor());
	}
	
	public static List<Polygon> shift(List<Polygon> polygons, Vertex vertex) {
		List<Polygon> shiftedPolygons = new ArrayList<>();
		for (Polygon polygon : polygons) {
			shiftedPolygons.add(shift(polygon, vertex));
		}
		return shiftedPolygons;
	}
	
	public static Vertex shift(Vertex v, Vertex d) {
		return new Vertex(
			v.getX() + d.getX(),
			v.getY() + d.getY(),
			v.getZ() + d.getZ()
		);
	}
	
	public static Polygon shift(Polygon p, Vertex d) {
		return new Polygon(p.getVertices().stream().map(v -> shift(v, d)).collect(Collectors.toList()), p.getColor());
	}
	
	public static List<Polygon> rotate(List<Polygon> polygons, double alfa) {
		List<Polygon> rotatedPolygons = new ArrayList<>();
		for (Polygon polygon : polygons) {
			rotatedPolygons.add(rotate(polygon, alfa));
		}
		return rotatedPolygons;
	}
	
	public static Polygon rotate(Polygon p, double alfa) {
		return new Polygon(p.getVertices().stream().map(v -> rotate(v, alfa)).collect(Collectors.toList()), p.getColor());
	}
	
	public static Vertex rotate(Vertex v, double alfa) {
		double x = v.getX() * Math.cos(alfa) + v.getZ() * Math.sin(alfa);
		double z = -v.getX() * Math.sin(alfa) + v.getZ() * Math.cos(alfa);
		return new Vertex((int) x, v.getY(), (int) z);
	}
	
	public static void changeCamera(int value) {
		camera += value * 20;
	}
	
	public static void changePlane(int value) {
		plane += value * 20;
	}
}
