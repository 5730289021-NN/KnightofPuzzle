	package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import LogicGame.Puzzle;
import base.GameScreen;
import render.AnimationManager;
import res.Resource;

public class GameFrame extends JComponent {

	AnimationManager[] puzzleItem = new AnimationManager[4];
	AnimationManager bg, me, burny;
	int seperateHeight = 368;
	
	Puzzle puzzle;
	
	public GameFrame() {
		puzzleItem[0] = Resource.get("smallpotion");
		puzzleItem[1] = Resource.get("largepotion");
		puzzleItem[2] = Resource.get("sword");
		puzzleItem[3] = Resource.get("shield");
		
		bg = Resource.get("underwaterbg");
		me = Resource.get("me");
		me.loop();
		burny = Resource.get("burny");
		burny.loop();
		
		puzzle = new Puzzle(puzzleItem);
	}
	
	public void update() {
		me.update();
		burny.update();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		drawStage(g2, seperateHeight);
		drawPuzzle(g2, seperateHeight, 400);
		drawBlood(g2);
		
		update();
	}
	
	private void drawLineStatus(Graphics2D g, Color color, String name, int x, int y) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Tahoma", Font.PLAIN, 20));
		g.drawString(name, x, y + 20);
		
		g.setColor(color);
		g.fillRect(x + 50, y, 220, 20);
	}
	
	public void drawBlood(Graphics2D g){
		drawLineStatus(g, Color.RED, "HP", 15, seperateHeight + 10);
		drawLineStatus(g, Color.GREEN, "Fury", 15, seperateHeight + 50);
		drawLineStatus(g, Color.RED, "HP", 720, seperateHeight + 10);
	}
	
	public void drawStage(Graphics2D g, int height) {
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, GameScreen.WIDTH, height);
		
		g.drawImage(
			bg.getCurrentBufferedImage(),
			0, 0,
			GameScreen.WIDTH, height,
			null
		);
		
		g.drawImage(
			me.getCurrentBufferedImage(),
			50, 150,
			me.getWidth()*2, me.getHeight()*2,
			null
		);
		
		g.drawImage(
			burny.getCurrentBufferedImage(),
			800, 200,
			burny.getWidth()*2, burny.getHeight()*2,
			null
		);
	}
	
	public void drawPuzzle(Graphics2D g, int y, int size) {
		puzzle.draw(g, (GameScreen.WIDTH - size) / 2, y, size);
	}
}
