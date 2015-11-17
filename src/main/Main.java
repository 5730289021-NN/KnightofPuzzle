package main;

import java.awt.Dimension;

import javax.swing.JFrame;

import base.GameScreen;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		GameScreen g = new GameScreen();
		frame.add(g);
		frame.setPreferredSize(new Dimension(
			GameScreen.WIDTH,
			GameScreen.HEIGHT
		));
		frame.pack();
		frame.setVisible(true);
		while(true){
			try{
				Thread.sleep(50);
			}catch (Exception e){
				
			}
			g.repaint();
		}

	}

}
