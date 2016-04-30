package base;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister.Pack;

import LogicGame.PlayLogic;
import LogicGame.Puzzle;
import javafx.animation.Animation;
import render.AnimationManager;

public class ModeScreen extends JDialog implements ActionListener {
	private ButtonGroup bg;
	private AnimationManager[] img;
	public static String s;
	
	public ModeScreen(){
		this.setLayout(new GridLayout(1, 3));
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		JRadioButton j1 = new JRadioButton("Slide");
		JRadioButton j2 = new JRadioButton("Twist");
		JRadioButton j3 = new JRadioButton("Swap");
		bg = new ButtonGroup();
		bg.add(j1);
		bg.add(j2);
		bg.add(j3);
		p1.add(j1);
		p1.add(j2);
		p1.add(j3);
		JDialog d1 = this;
		j1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 s = "Slider";
				
			}
		});
		j2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				s = "Twist";
				
			}
		});
		j3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				s = "Swap";
				
			}
		});
		JPanel p2 = new JPanel();
		JButton b1 = new JButton("OK");
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 d1.dispose();
				
			}
		});
		p2.add(b1);
		JButton b2 = new JButton("Cancel");
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 d1.dispose();
				
			}
		});
		p2.add(b2);
		this.add(p1);
		this.add(p2);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Choose youe platfrom");
		
	}

}
