package base;

import java.awt.Graphics;

import javax.swing.JComponent;

public class GameScreen extends JComponent {
	
	public final static int WIDTH = 1024;
	public final static int HEIGHT = 768;
	
	public GameScreen() {
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
	}

}
