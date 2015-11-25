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

import LogicGame.Puzzle;
import base.GameScreen;
import main.Main;
import render.AnimationManager;
import res.Resource;

public class GameFrame extends JComponent {

	AnimationManager[] puzzleItem = new AnimationManager[4];
	AnimationManager bg, me, megaburny;
	int seperateHeight = 368;
	
	Puzzle puzzle;
	private JButton menuButton;
	
	
	public GameFrame() {
		puzzleItem[0] = Resource.get("smallpotion");
		puzzleItem[1] = Resource.get("largepotion");
		puzzleItem[2] = Resource.get("sword");
		puzzleItem[3] = Resource.get("shield");
		
		bg = Resource.get("underwaterbg");
		me = Resource.get("me");
		me.loop();
		megaburny = Resource.get("megaburny");
		megaburny.loop();
		
		puzzle = new Puzzle(puzzleItem);
		
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

	private void restart() {
		// TODO Auto-generated method stub
		
	}

	public void update() {
		me.update();
		megaburny.update();
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
			megaburny.getCurrentBufferedImage(),
			800, 150,
			megaburny.getWidth(), megaburny.getHeight(),
			null
		);
	}
	
	public void drawPuzzle(Graphics2D g, int y, int size) {
		puzzle.draw(g, (GameScreen.WIDTH - size) / 2, y, size);
	}
	
	public static void showDialog()
	{
		
	}
}
