package base;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import render.AnimationManager;
import res.Resource;

public class IntroScreen extends JComponent{
	
	private static final boolean CONTINUECHOICE = true;
	private static final boolean NEWPLAYCHOICE = false;
	private JButton playButton;
	private JButton continueButton;
	private JButton exitButton;
	private AnimationManager introbg;
	private String[] slots = {"Slot 1","Slot 2","Slot 3"};

	public IntroScreen(){
		super();
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(GameScreen.WIDTH, GameScreen.HEIGHT));
		setVisible(true);
		setBackground(Color.WHITE);
		setDoubleBuffered(true);
		introbg = Resource.get("introbg");
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
		exitButton = new JButton("EXIT");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
		g.fillRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
		introbg.update();
		g.drawImage(introbg.getCurrentBufferedImage(0),0,0,GameScreen.WIDTH,GameScreen.HEIGHT,null);
		g.drawImage(introbg.getCurrentBufferedImage(),0,0,GameScreen.WIDTH,GameScreen.HEIGHT,null);
	}
	
	private void showDialog(boolean mode){
		String s1 = "";
		if(mode)
		{
			s1 = "Load";
		}
		else
		{
			s1 = "Play";
		}
		String s = (String) JOptionPane.showInputDialog(this,"Choose Slot to " + s1,s1,JOptionPane.PLAIN_MESSAGE,null,slots,"Slot 1");
		if ((s != null) && (s.length() > 0)) {
			System.out.println(s);
		}else
		{
			System.out.println("Error");
		}
	}

}
