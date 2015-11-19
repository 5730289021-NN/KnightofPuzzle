package main;

import java.awt.Dimension;

import javax.swing.JFrame;

import base.GameScreen;
import base.IntroScreen;
import base.SelectLevelScreen;
import res.Resource;

public class Main {

	public static void main(String[] args) {
		new Resource();
		JFrame frame = new JFrame();
		frame.setTitle("Knight of Puzzle");
		//GameScreen g = new GameScreen();
		//frame.add(g);
		IntroScreen i = new IntroScreen();
		frame.add(i);
		//SelectLevelScreen sls = new SelectLevelScreen();
		//frame.add(sls);
		frame.setPreferredSize(new Dimension(
			GameScreen.WIDTH,
			GameScreen.HEIGHT
		));
		frame.pack();
		frame.setVisible(true);
		while(true){
			try{
				Thread.sleep(20);
			}catch (Exception e){
				
			}
			i.repaint();
		}

	}

}
