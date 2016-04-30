package LogicGame;

import java.awt.Graphics2D;

public interface MiniGame {
	public void draw(Graphics2D g, int x, int y, int size);
	public int[] calculateCombineStat();
	public void update();
}
