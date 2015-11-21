package base;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.Main;
import render.AnimationManager;
import res.Resource;
import Data.InfoManager;

public class IntroScreen extends JComponent{
	
	private static final boolean CONTINUECHOICE = true;
	private static final boolean NEWPLAYCHOICE = false;
	private JButton playButton;
	private JButton continueButton;
	private JButton exitButton;
	private AnimationManager introbg;

	public IntroScreen(JFrame frame){
		super();
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(GameScreen.WIDTH, GameScreen.HEIGHT));
		setVisible(true);
		setBackground(Color.WHITE);
		setDoubleBuffered(false);
		introbg = Resource.get("introbg2");
		introbg.loop();
		playButton = new JButton("PLAY");
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showDialog(NEWPLAYCHOICE);
			}
		});
		continueButton = new JButton("CONTINUE");
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showDialog(CONTINUECHOICE);
			}
		});
		
		final JFrame closeFrame = frame;
		exitButton = new JButton("EXIT");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Close the window
				closeFrame.dispose();
			}
		});
		add(playButton);
		add(continueButton);
		add(exitButton);
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		//g.fillRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
		//introbg.update();
		g.drawImage(introbg.getCurrentBufferedImage(),0,0,GameScreen.WIDTH,GameScreen.HEIGHT,null);
	}
	
	private void showDialog(boolean mode){
		String s1 = "";
		String[] slots = new String[InfoManager.MAXSLOT];
		if(mode)
		{
			s1 = "Load";
			for(int i = 0;i<InfoManager.MAXSLOT;i++)
			{
				if(InfoManager.MAX_LEVEL_COMPLETE[i] == 0)
				{
					slots[i] = "Empty Slot";
				}else
				{
					slots[i] = "Slot " + (i+1);
				}
			}
		}
		else
		{
			s1 = "Play";
			for(int i = 0;i<InfoManager.MAXSLOT;i++)
			{
				if(InfoManager.MAX_LEVEL_COMPLETE[i] == 0)
				{
					slots[i] = "New Slot " + (i+1);;
				}else
				{
					slots[i] = "Slot " + (i+1);
				}
			}
		}
		
		String s = (String) JOptionPane.showInputDialog(this,"Choose Slot to " + s1,s1,JOptionPane.PLAIN_MESSAGE,null,slots,slots[0]);
		if ((s != null) && (s.length() > 0)) {
			if(mode)//Continue mode
			{
				if(s.equals("Empty Slot"))
				{
					JOptionPane.showMessageDialog(null, "You cannot load game from Empty slot, Please repick");
				}
				else
				{
					InfoManager.SELECTED_SLOT = Integer.parseInt(s.substring(5,6)) - 1;
					System.out.println("Load Slot : " + InfoManager.SELECTED_SLOT);
					Main.changeGameScreen(Main.SELECTSCREEN);
				}
			}else//Play
			{
				if(s.contains("New Slot"))
				{
					InfoManager.SELECTED_SLOT = Integer.parseInt(s.substring(9,10)) - 1;
					System.out.println("New Slot : " + InfoManager.SELECTED_SLOT);
					Main.changeGameScreen(Main.SELECTSCREEN);
				}
				else
				{
					int ans = JOptionPane.showConfirmDialog(null, "Are you sure do you want to override the save game " + s + " ?", "Dialog", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(ans == JOptionPane.YES_OPTION)
					{
						InfoManager.LEVEL_WEAPON[Integer.parseInt(s.substring(5,6)) - 1] = 0;
						InfoManager.LEVEL_ARMOR[Integer.parseInt(s.substring(5,6)) - 1] = 0;
						InfoManager.LEVEL_POTION[Integer.parseInt(s.substring(5,6)) - 1] = 0;
						InfoManager.MAX_LEVEL_COMPLETE[Integer.parseInt(s.substring(5,6)) - 1] = 0;
						InfoManager.recalculateStat(Integer.parseInt(s.substring(5,6)) - 1);
						InfoManager.SELECTED_SLOT = Integer.parseInt(s.substring(5,6)) - 1;
						Main.changeGameScreen(Main.SELECTSCREEN);
					}
					
				}
			}
		}else
		{
			System.out.println("No slot selected");
		}
	}

}
