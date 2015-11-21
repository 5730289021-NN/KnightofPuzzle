package main;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;

import base.GameScreen;
import base.IntroScreen;
import base.SelectLevelScreen;
import res.Resource;

public class Main {
	private static JComponent currentScreen;
	
	public static final int INTROSCREEN = 1;
	public static final int SELECTSCREEN = 2;
	public static final int GAMESCREEN = 5;
	
	private static IntroScreen i;
	private static SelectLevelScreen sls;
	private static GameScreen g;
	
	public static void main(String[] args) {
		new Resource();
		i = new IntroScreen();
		sls = new SelectLevelScreen();
		g = new GameScreen();
		JFrame frame = new JFrame();
		frame.setTitle("Knight of Puzzle");
		changeGameScreen(INTROSCREEN);
		frame.add(currentScreen);
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
			currentScreen.repaint();
		}

	}
	
	public static void changeGameScreen(int Screen)
	{
		switch(Screen)
		{
			case INTROSCREEN:
			{
				currentScreen = i;
				break;
			}
			case SELECTSCREEN:
			{
				currentScreen = sls;
				break;
			}
			case GAMESCREEN:
			{
				currentScreen = g;
				break;
			}
		}
	}

}
