package frame;

import java.awt.Color;
import java.awt.Graphics2D;

import base.GameScreen;

public class GameFrame implements Frame {

	@Override
	public void draw(Graphics2D g) {
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, GameScreen.WIDTH, 368);
		
		int bottomSize = 400;
		
		g.setColor(new Color(20,20,20));
		g.fillRect((GameScreen.WIDTH - bottomSize)/2, 368, bottomSize, bottomSize);
	}
}
