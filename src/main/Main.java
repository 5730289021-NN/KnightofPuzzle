package main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;

import Data.InfoManager;
import base.GameScreen;
import base.IntroScreen;
import base.SelectLevelScreen;
import input.InputUtility;
import res.Resource;

public class Main {
	private static JComponent currentScreen;
	
	public static final int INTROSCREEN = 1;
	public static final int SELECTSCREEN = 2;
	public static final int GAMESCREEN = 5;
	
	private static IntroScreen i;
	private static SelectLevelScreen sls;
	private static GameScreen g;
	private static JFrame frame;
	private static AudioClip bgm;


	
	public static void main(String[] args) {
		new Resource();
		new InfoManager();
		frame = new JFrame();
		
		i = new IntroScreen(frame);
		sls = new SelectLevelScreen();
		g = new GameScreen();
		frame = new JFrame();
		frame.setTitle("Knight of Puzzle");
		bgm = Resource.getAudio("birdSound");
		currentScreen = i;
		changeGameScreen(INTROSCREEN);
		frame.setPreferredSize(new Dimension(
			GameScreen.WIDTH,
			GameScreen.HEIGHT
		));
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while(true){
			try{
				Thread.sleep(20);
			}catch (Exception e){
				
			}
			currentScreen.repaint();
			currentScreen.requestFocus();
		}

	}
	
	public static void changeGameScreen(int Screen)
	{
		bgm.stop();
		frame.remove(currentScreen);
		System.out.print("Change game screen to ");
		switch(Screen)
		{
			case INTROSCREEN:
			{
				System.out.println("Intro");
				currentScreen = i;
				bgm = Resource.getAudio("birdSound");
				bgm.play();
				break;
			}
			case SELECTSCREEN:
			{
				System.out.println("Select");
				currentScreen = sls;
				bgm = Resource.getAudio("thebeat");
				bgm.play();
				break;
			}
			case GAMESCREEN:
			{
				System.out.println("Game");
				currentScreen = g;
				break;
			}
		}
		
		frame.add(currentScreen);
		frame.pack();
	}

}
