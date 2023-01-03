package com.graphics.panel;

import com.graphics.Transformer;
import com.graphics.model.Polygon;
import com.graphics.util.files.FileHandler;
import com.graphics.model.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.function.ToIntFunction;

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
		
		for (com.graphics.model.Polygon p : FileHandler.readFile("src/main/resources/polygons.json")) {
			draw(g, Transformer.perspective(p), Color.red);
		}
		
		for (com.graphics.model.Polygon p : FileHandler.readFile("src/main/resources/cube.json")) {
			draw(g, Transformer.perspective(Transformer.shift(p, new Vertex(100, 100, 100))), Color.green);
		}
	}
	
	private void draw(Graphics g, com.graphics.model.Polygon polygon, Color color) {
		com.graphics.model.Polygon p = Transformer.shift(polygon, new Vertex(getWidth()/2, getHeight()/2, 0));
		g.setColor(Color.black);
		g.fillPolygon(getPoints(p, Vertex::getX), getPoints(p, Vertex::getY), p.getVertexes().size());
		g.setColor(color);
		g.drawPolygon(getPoints(p, Vertex::getX), getPoints(p, Vertex::getY), p.getVertexes().size());
	}
	
	private static int[] getPoints(Polygon polygon, ToIntFunction<Vertex> mapper) {
		return polygon.getVertexes().stream().mapToInt(mapper).toArray();
	}
}
