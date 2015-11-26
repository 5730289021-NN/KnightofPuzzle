package frame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import main.Main;
import render.AnimationManager;
import render.RenderHelper;
import res.Resource;
import LogicGame.PlayLogic;
import LogicGame.Puzzle;
import base.GameScreen;

public class PlayFrame extends JComponent {

	private static final int START_STATE = 0;
	private static final int PLAY_STATE = 1;
	private static final int MINI_FINISH = 2;
	
	private AnimationManager[] puzzleItem = new AnimationManager[4];
	private AnimationManager bg, me, burny, attackme;
	private int state;
	private int seperateHeight = 368;
	private PlayLogic logic;
	
	private int currentMiniPosY = 0;
	
	Puzzle puzzle;
	private JButton menuButton;
	
	
	public PlayFrame() {
		logic = new PlayLogic(this);
		
		state = START_STATE;
		
		puzzleItem[0] = Resource.get("smallpotion");
		puzzleItem[1] = Resource.get("largepotion");
		puzzleItem[2] = Resource.get("sword");
		puzzleItem[3] = Resource.get("shield");
		
		bg = Resource.get("underwaterbg");
		me = Resource.get("me");
		me.loop();
		burny = Resource.get("burny");
		burny.loop();
		attackme = Resource.get("attackme");
		
		puzzle = new Puzzle(puzzleItem);
		currentMiniPosY = 0;
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		menuButton = new JButton("Menu");
		menuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showOptionDialog();
			}
		});
		
		add(menuButton);
		
	}
	
	private void showOptionDialog() {
		//TODO pause?
		String[] options = {"Resume","Restart","Quit"};
		int choice = JOptionPane.showOptionDialog(this, "Choose your choice", "Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		System.out.println(options[choice]);
		switch(choice)
		{
			case 0:
			{
				return;
			}
			case 1:
			{
				if(JOptionPane.showConfirmDialog(this,"Are you sure?","Restart",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == 0)
				{
					restart();
				}else
				{
					return;
				}
				break;
			}
			case 2:
			{
				if(JOptionPane.showConfirmDialog(this,"Are you sure?","Quit",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == 0)
				{
					Main.changeGameScreen(Main.SELECTSCREEN);
				}else
				{
					return;
				}
				break;
			}
		}
		
		
	}
	
	public void miniGameComplete() {
		currentMiniPosY = seperateHeight;
		state = MINI_FINISH;
		attackme.play();
	}

	private void restart() {
		// TODO Auto-generated method stub
		
	}

	public void update() {
		
		if(state == PLAY_STATE) {
			puzzle.update();
		} else if(state == MINI_FINISH) {
			attackme.update();
		}
		
		me.update();
		burny.update();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(new Color(20, 20, 20));
		g.fillRect((GameScreen.WIDTH - 400) / 2, seperateHeight, 400, 400);
		
		if(state == START_STATE) {
			if(currentMiniPosY >= seperateHeight) {
				currentMiniPosY = seperateHeight;
				state = PLAY_STATE;
			}
			drawPuzzle(g2, currentMiniPosY, 400);
			currentMiniPosY += 10;
		} else if(state == PLAY_STATE) {
			logic.updateTimeStamp();
			drawPuzzle(g2, seperateHeight, 400);
		} else if(state == MINI_FINISH) {
			currentMiniPosY += 10;
			drawPuzzle(g2, currentMiniPosY, 400);
			if(currentMiniPosY >= GameScreen.HEIGHT) {
				currentMiniPosY = 0;
				puzzle = new Puzzle(puzzleItem);
				state = START_STATE;
			}
		}
		
		
		drawStage(g2, seperateHeight);
		drawTime(g2, logic.getTimeCounter());
		drawBlood(g2);
		
		update();
	}
	
	private void drawLineStatus(Graphics2D g, Color color, String name, int x, int y, int percent) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Tahoma", Font.PLAIN, 20));
		g.drawString(name, x, y + 20);
		
		g.setColor(color);
		g.fillRect(x + 50, y, 220 * percent / 100, 20);
	}
	
	public void drawBlood(Graphics2D g){
		drawLineStatus(g, Color.RED, "HP", 15, seperateHeight + 10, 100);
		drawLineStatus(g, Color.GREEN, "Fury", 15, seperateHeight + 50, 100);
		drawLineStatus(g, Color.RED, "HP", 720, seperateHeight + 10, 100);
	}
	
	public void drawTime(Graphics2D g, int time) {
		String txt = time + "";
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Tahoma", Font.PLAIN, 40));
		int width = g.getFontMetrics().stringWidth(txt);
		g.drawString(txt, (GameScreen.WIDTH - width)/2, 50);
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
			burny.getCurrentBufferedImage(),
			800, 200,
			burny.getWidth()*2, burny.getHeight()*2,
			null
		);
		
		if(state == MINI_FINISH) {
			RenderHelper.draw(
				g, 
				attackme.getCurrentBufferedImage(), 
				150, 220, 
				attackme.getWidth()*2, attackme.getHeight()*2, 
				RenderHelper.LEFT | RenderHelper.BOTTOM
			);
		} else {
			RenderHelper.draw(
				g, 
				me.getCurrentBufferedImage(), 
				150, 220, 
				me.getWidth()*2, me.getHeight()*2, 
				RenderHelper.LEFT | RenderHelper.BOTTOM
			);
		}
	}
	
	public void drawPuzzle(Graphics2D g, int y, int size) {
		puzzle.draw(g, (GameScreen.WIDTH - size) / 2, y, size);
	}
	
	public static void showDialog()
	{
		
	}
}
