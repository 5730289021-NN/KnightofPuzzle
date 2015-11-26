package main;

import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

import Data.InfoManager;
import base.GameScreen;
import base.IntroScreen;
import base.SelectLevelScreen;
import base.ShopScreen;
import frame.PlayFrame;
import input.InputUtility;
import res.Resource;

public class Main {
	private static JComponent currentScreen;
	
	public static final int SleepTime = 20;
	
	public static final int INTROSCREEN = 1;
	public static final int SELECTSCREEN = 2;
	public static final int SHOPSCREEN = 3;
	public static final int GAMESCREEN = 5;
	private static IntroScreen i;
	private static SelectLevelScreen sls;
	private static ShopScreen ss;
	private static PlayFrame g;
	private static JFrame frame;
	private static AudioClip bgm;


	
	public static void main(String[] args) {
		new Resource();
		new InfoManager();
		frame = new JFrame();
		
		i = new IntroScreen(frame);
		sls = new SelectLevelScreen();
		ss = new ShopScreen();
		g = new PlayFrame();
		frame = new JFrame();
		frame.setTitle("Knight of Puzzle");
		bgm = Resource.getAudio("birdSound");
		currentScreen = i;
		changeGameScreen(GAMESCREEN);
		
		addListener(frame);
		
		frame.setPreferredSize(new Dimension(
			GameScreen.WIDTH,
			GameScreen.HEIGHT +45
		));
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while(true){
			try{
				Thread.sleep(SleepTime);
			}catch (Exception e){
				
			}
			InputUtility.postUpdate();
			currentScreen.repaint();
			currentScreen.requestFocus();
			frame.requestFocus();
		}

	}
	
	private static void addListener(JFrame frame) {
		frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				InputUtility.setKeyPressed(e.getKeyCode(), false);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				InputUtility.setKeyPressed(e.getKeyCode(), true);
				InputUtility.setKeyTriggered(e.getKeyCode(), true);
			}
		});
		frame.setFocusable(true);
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
			case SHOPSCREEN:
			{
				System.out.println("Shop");
				currentScreen = ss;
				bgm = Resource.getAudio("doorbell");
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
