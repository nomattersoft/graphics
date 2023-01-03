package com.graphics;

import com.graphics.model.Polygon;
import com.graphics.model.Vertex;

import java.util.stream.Collectors;

public class Transformer {
	
	private static int f = 100;
	
	public static int perspective(int p, int z) {
		return f*p/z;
	}
	
	public static Vertex perspective(Vertex v) {
		return new Vertex(
				perspective(v.getX(), v.getZ()),
				perspective(v.getY(), v.getZ()),
				v.getZ());
	}
	
	public static Polygon perspective(Polygon p) {
		return new Polygon(p.getVertexes().stream().map(Transformer::perspective).collect(Collectors.toList()));
	}
	
	public static Vertex shift(Vertex v, Vertex d) {
		return new Vertex(
			v.getX() + d.getX(),
			v.getY() + d.getY(),
			v.getZ() + d.getZ()
		);
	}
	
	public static Polygon shift(Polygon p, Vertex d) {
		return new Polygon(p.getVertexes().stream().map(v -> shift(v, d)).collect(Collectors.toList()));
	}
	public static void changeFocus(int value) {
		f += value;
	}
}
