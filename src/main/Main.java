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
import res.Resource;

public class Main {
	private static JComponent currentScreen;
	
	public static final int INTROSCREEN = 1;
	public static final int SELECTSCREEN = 2;
	public static final int GAMESCREEN = 5;
	//
	private static IntroScreen i;
	private static SelectLevelScreen sls;
	private static GameScreen g;
	private static JFrame frame;
	static AudioClip bgSound = Applet.newAudioClip(IntroScreen.class.getClassLoader().getResource("sound/bird.wav"));
	
	public static void main(String[] args) {
		new Resource();
		new InfoManager();
		i = new IntroScreen();
		sls = new SelectLevelScreen();
		g = new GameScreen();
		frame = new JFrame();
		Thread t = new Thread(new Runnable() {
			public void run() {
				bgSound.play();
			}
		});
		t.start();
		frame.setTitle("Knight of Puzzle");
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
		}

	}
	
	public static void changeGameScreen(int Screen)
	{
		frame.remove(currentScreen);
		System.out.print("Change game screen to ");
		switch(Screen)
		{
			case INTROSCREEN:
			{
				System.out.println("Intro");
				currentScreen = i;
				break;
			}
			case SELECTSCREEN:
			{
				System.out.println("Select");
				currentScreen = sls;
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
