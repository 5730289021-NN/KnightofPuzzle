package base;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class IntroScreen extends JComponent{
	
	private JButton playButton;
	private JButton continueButton;
	private JButton exitButton;
	private String[] save = {"Slot 1","Slot 2","Slot 3"};

	public IntroScreen(){
		super();
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(GameScreen.WIDTH, GameScreen.HEIGHT));
		setVisible(true);
		setBackground(Color.WHITE);
		playButton = new JButton("PLAY");
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showDialog();
			}
		});
		
		exitButton = new JButton("EXIT");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Exit");
			}
		});
		continueButton = new JButton("CONTINUE");
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Continue");
			}
		});
		add(playButton);
		add(exitButton);
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
	}
	
	private void showDialog(){
		String s = (String) JOptionPane.showInputDialog(this,"Choose Slot to Play","Play",JOptionPane.PLAIN_MESSAGE,null,save,"Slot 1");
		if ((s != null) && (s.length() > 0)) {
			System.out.println(s);
		}else
		{
			System.out.println("Error");
		}
	}

}
