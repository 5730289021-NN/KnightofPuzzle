package base;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import res.Resource;

public class ShopScreen extends JComponent{
	private static final double[] xpos = {0.1,0.3,0.4,0.5,0.75};
	private static final double[] ypos = {0.5,0.66,0.44,0.77,0.17};
	private static int[] xpos_ = new int[5];
	private static int[] ypos_ = new int[5];
	
	static{
		for(int i = 0;i<5;i++)
		{
			xpos_[i] = (int) (GameScreen.WIDTH * xpos[i]);
			ypos_[i] = (int) (GameScreen.HEIGHT * ypos[i]);
		}
	}
	
	public ShopScreen(){
		super();
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(GameScreen.WIDTH, GameScreen.HEIGHT));
		setVisible(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		g2.fillRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
		g2.drawImage(Resource.get("inventorybg").getCurrentBufferedImage(), 0, 0, GameScreen.WIDTH,GameScreen.HEIGHT,null);
		
	}
	
	
	
}
