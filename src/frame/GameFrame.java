package frame;

import java.awt.Color;
import java.awt.Graphics2D;

import render.AnimationManager;
import res.Resource;
import LogicGame.Puzzle;
import base.GameScreen;

public class GameFrame implements Frame {

	AnimationManager[] puzzleItem = new AnimationManager[4];
	AnimationManager bg, me, burny;
	
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
	}
	
	@Override
	public void update() {
		me.update();
		burny.update();
	}
	
	@Override
	public void draw(Graphics2D g) {
		drawStage(g, 368);
		drawPuzzle(g, 368, 400);
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
			50, 50,
			me.getWidth()*4, me.getHeight()*4,
			null
		);
		
		g.drawImage(
			burny.getCurrentBufferedImage(),
			800, 150,
			burny.getWidth()*4, burny.getHeight()*4,
			null
		);
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
				if(data[i][j] == 0) continue;
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
