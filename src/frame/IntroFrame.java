package frame;

import java.awt.Color;
import java.awt.Graphics2D;

import base.GameScreen;
import render.AnimationManager;
import res.Resource;

public class IntroFrame implements Frame{
	
	private AnimationManager introBG;
	
	public IntroFrame(){
		introBG = Resource.get("introbg");
		introBG.loop();
	}
	

	@Override
	public void update() {
		introBG.update();
	}

	@Override
	public void draw(Graphics2D g) {
		drawBackGround(g);
	}

	private void drawBackGround(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
		g.drawImage(
				introBG.getCurrentBufferedImage(),
				0, 0,
				GameScreen.WIDTH, GameScreen.HEIGHT,
				null
			);
	}

}
