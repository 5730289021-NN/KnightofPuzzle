package base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import frame.Frame;
import frame.GameFrame;

public class GameScreen extends JComponent {
	
	public final static int WIDTH = 1024;
	public final static int HEIGHT = 768;
	
	private Frame game;
	
	public GameScreen() {
		game = new GameFrame();
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		
		game.draw((Graphics2D) g);
	}

}