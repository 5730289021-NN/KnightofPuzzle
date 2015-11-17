package frame;

import java.awt.Color;
import java.awt.Graphics2D;

import render.AnimationManager;
import res.Resource;
import LogicGame.Puzzle;
import base.GameScreen;

public class GameFrame implements Frame {

	AnimationManager[] puzzleItem = new AnimationManager[4];
	
	public GameFrame() {
		puzzleItem[0] = Resource.get("smallpotion");
		puzzleItem[1] = Resource.get("largepotion");
		puzzleItem[2] = Resource.get("sword");
		puzzleItem[3] = Resource.get("shield");
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, GameScreen.WIDTH, 368);

		drawPuzzle(g, 368, 400);
	}
	
	public void drawPuzzle(Graphics2D g, int y, int size) {
		
		int x = (GameScreen.WIDTH - size) / 2;
		
		g.setColor(new Color(20, 20, 20));
		g.fillRect(x, y, size, size);
		
		int[][] data = Puzzle.getInstance().getTable();
		int h = data.length;
		int w = data[0].length;
		
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				g.drawImage(
					puzzleItem[data[i][j] - 1].getCurrentBufferedImage(), 
					x + size / w * j, 
					y + size / h * i, 
					size / w, 
					size / h, 
					null
				);
			}
		}
	}
}
