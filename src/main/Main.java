package main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {

	//meow
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		GameScreen g = new GameScreen();
		frame.add(g);
		frame.setPreferredSize(new Dimension(1600, 900));
		frame.pack();
		frame.setVisible(true);
		while(true){
			try{
				Thread.sleep(20);
			}catch (Exception e){
				
			}
			g.repaint();
		}

	}

}
