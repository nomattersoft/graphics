package com.graphics.panel;

import com.graphics.Transformer;
import com.graphics.model.Polygon;
import com.graphics.util.files.FileHandler;
import com.graphics.model.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import static com.graphics.Transformer.perspective;
import static com.graphics.Transformer.shift;

public class ScreenPanel extends JPanel {
	
	
	public ScreenPanel() {
		addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				Transformer.changeFocus(e.getWheelRotation());
				repaint();
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.decode("#2b2b2b"));
		g.fillRect(0, 0, getWidth(), getHeight());
		
//		for (Polygon p : FileHandler.readFile("src/main/resources/polygons.json")) {
//			draw(g, perspective(p), Color.red);
//		}
//
//		for (Polygon p : FileHandler.readFile("src/main/resources/cube.json")) {
//			draw(g, perspective(shift(p, new Vertex(100, 100, 100))), Color.green);
//		}
		
		List<Polygon> polygons = FileHandler.readFile("src/main/resources/polygons.json");
		polygons.addAll(shift(FileHandler.readFile("src/main/resources/cube.json"), new Vertex(100, 100, 100)));
		
		drawSorted(g, polygons);
	}
	
	private void drawSorted(Graphics g, List<Polygon> polygons) {
		List<Polygon> sorted = polygons.stream().sorted((p1, p2) -> averageZ(p2) - averageZ(p1)).collect(Collectors.toList());
		sorted.forEach(p -> draw(g, perspective(p)));
	}
	
	private int averageZ(Polygon p) {
		return (int)p.getVertexes().stream().mapToInt(Vertex::getZ).summaryStatistics().getAverage();
	}
	
	private int maxZ(Polygon polygon) {
		return polygon.getVertexes().stream().max(Comparator.comparingInt(Vertex::getZ)).get().getZ();
	}
	
	private void draw(Graphics g, Polygon polygon) {
		Polygon p = shift(polygon, new Vertex(getWidth()/2, getHeight()/2, 0));
		g.setColor(Color.black);
		g.fillPolygon(getPoints(p, Vertex::getX), getPoints(p, Vertex::getY), p.getVertexes().size());
		g.setColor(Color.decode(p.getColor()));
		g.drawPolygon(getPoints(p, Vertex::getX), getPoints(p, Vertex::getY), p.getVertexes().size());
	}
	
	private static int[] getPoints(Polygon polygon, ToIntFunction<Vertex> mapper) {
		return polygon.getVertexes().stream().mapToInt(mapper).toArray();
	}
}
