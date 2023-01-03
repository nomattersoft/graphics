package com.graphics;

import com.graphics.panel.ScreenPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(new ScreenPanel());
		frame.setVisible(true);
	}
}