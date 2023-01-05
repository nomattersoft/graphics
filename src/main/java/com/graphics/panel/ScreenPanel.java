package com.graphics.panel;

import com.graphics.Transformer;
import com.graphics.model.Polygon;
import com.graphics.model.Vertex;
import com.graphics.util.files.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import static com.graphics.Transformer.*;

public class ScreenPanel extends JPanel {
	
	private volatile double alfa = 0;
	
	public ScreenPanel() {
		addMouseWheelListener(e -> {
			if (e.isAltDown()) {
				Transformer.changeCamera(e.getWheelRotation());
			} else {
				Transformer.changePlane(e.getWheelRotation());
			}
			repaint();
		});
		
		new Thread(() -> {
			while (true) {
				alfa += 0.05;
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				repaint();
			}
		}).start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.decode("#2b2b2b"));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		List<Polygon> polygons = FileHandler.readFile("src/main/resources/polygons.json");
		polygons.addAll(shift(FileHandler.readFile("src/main/resources/cube.json"),
														new Vertex(-200, -150, -100)));
		
		for (int i = 0; i < 10; i++) {
			polygons.addAll(rotate(shift(FileHandler.readFile("src/main/resources/cube.json"),
					new Vertex(100, -300 + 120 * i , 100)), alfa * i));
		}
		
		drawSorted(g, polygons);
	}
	
	private void drawSorted(Graphics g, List<Polygon> polygons) {
		List<Polygon> sorted = polygons.stream().sorted((p1, p2) -> averageZ(p2) - averageZ(p1)).collect(Collectors.toList());
		sorted.forEach(p -> draw(g, perspective(p)));
	}
	
	private int averageZ(Polygon p) {
		return (int)p.getVertices().stream().mapToInt(Vertex::getZ).summaryStatistics().getAverage();
	}
	
	private int maxZ(Polygon polygon) {
		return polygon.getVertices().stream().max(Comparator.comparingInt(Vertex::getZ)).get().getZ();
	}
	
	private void draw(Graphics g, Polygon polygon) {
		Polygon p = shift(polygon, new Vertex(getWidth()/2, getHeight()/2, 0));
		g.setColor(Color.black);
		g.fillPolygon(getPoints(p, Vertex::getX), getPoints(p, Vertex::getY), p.getVertices().size());
		g.setColor(Color.decode(p.getColor()));
		g.drawPolygon(getPoints(p, Vertex::getX), getPoints(p, Vertex::getY), p.getVertices().size());
	}
	
	private static int[] getPoints(Polygon polygon, ToIntFunction<Vertex> mapper) {
		return polygon.getVertices().stream().mapToInt(mapper).toArray();
	}
}
