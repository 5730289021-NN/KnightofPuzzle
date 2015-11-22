package base;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;

import input.InputUtility;
import render.AnimationManager;
import res.Resource;

public class SelectLevelScreen extends JComponent{
	private static final double[] xpos = {0.1,0.3,0.4,0.5,0.75};
	private static final double[] ypos = {0.5,0.66,0.44,0.77,0.17};
	private static int[] xpos_ = new int[5];
	private static int[] ypos_ = new int[5];
	private int meXPos, meYPos;
	
	
	private AnimationManager me;
	private AnimationManager maxwell;
	static{
		for(int i = 0;i<5;i++)
		{
			xpos_[i] = (int) (GameScreen.WIDTH * xpos[i]);
			ypos_[i] = (int) (GameScreen.HEIGHT * ypos[i]);
		}
	}
	
	public SelectLevelScreen(){
		super();
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(GameScreen.WIDTH, GameScreen.HEIGHT));
		setVisible(true);
		me = Resource.get("me");
		maxwell = Resource.get("minimaxwell");
		me.loop();
		maxwell.loop();
		meXPos = xpos_[0] - me.getWidth()/2;
		meYPos = ypos_[0] - me.getHeight();
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				InputUtility.setKeyPressed(e.getKeyCode(), false);
			}
			public void keyPressed(KeyEvent e) {
				InputUtility.setKeyPressed(e.getKeyCode(), true);
				InputUtility.setKeyTriggered(e.getKeyCode(), true);
			}
		});
		
		Thread meThread = new Thread(new Runnable() {
			@Override
			public void run() {
				int count = 0;
				while(true)
				{
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(InputUtility.getKeyPressed(KeyEvent.VK_RIGHT))
					{
						if(count == 100)
						{
							count = 0;
						}
						//meXPos = xpos_[0] * - me.getWidth()/2;
					}
				}
				
			}
		});
		meThread.start();
		
	}
	protected void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		g2.fillRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
		g2.drawImage(Resource.get("starbg").getCurrentBufferedImage(), 0, 0, GameScreen.WIDTH,GameScreen.HEIGHT,null);
		g2.setColor(Color.YELLOW);
		g2.drawPolyline(xpos_, ypos_, 5);
		for(int i = 0;i<5;i++)
		{
			g2.setColor(Color.RED);
			g2.fillOval(xpos_[i] - 10, ypos_[i] - 10, 20, 20);
			g2.setColor(Color.WHITE);
			g2.fillOval(xpos_[i] - 5, ypos_[i] - 5, 10, 10);
		}
		me.update();
		maxwell.update();
		g2.drawImage(
				me.getCurrentBufferedImage(),
				meXPos, meYPos,
				me.getWidth(), me.getHeight(),
				null
			);
		g2.drawImage(
				maxwell.getCurrentBufferedImage(),
				xpos_[4] - maxwell.getWidth()/2, ypos_[4] - maxwell.getHeight(),
				maxwell.getWidth(), maxwell.getHeight(),
				null
			);
	}
	
}
