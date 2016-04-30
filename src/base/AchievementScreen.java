package base;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import Data.InfoManager;
import frame.PlayFrame;
import javafx.scene.control.ProgressBar;
import main.Main;

public class AchievementScreen extends JDialog {
	private JProgressBar pb;
	private int money;
	private int reward;
	private int RewardComplete;
	private int ValueProgress;
	private int n1,n2,n3,n4,n5;
	private boolean isDone = false;
	private ArrayList<String> ListQuest = new ArrayList<String>();
	private JList<JPanel> jl;
	private JLabel s1,s2,s3,s4,s5,s6;
	public AchievementScreen(){
		super();
		JPanel Panel = new JPanel();
		Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
		JPanel sub1 = new JPanel();
		 s1 = new JLabel("Kill 50 enemies");
		sub1.add(s1);
		JPanel sub2 = new JPanel();
		 s2 = new JLabel("Use fury 40 times");
		sub2.add(s2);
		JPanel sub3 = new JPanel();
		 s3 = new JLabel("Buy and upgrad all assets");
		sub3.add(s3);
		JPanel sub4 = new JPanel();
		 s4 = new JLabel("You kill totally 300 enemies");
		JPanel sub5 = new JPanel();
		 s5 = new JLabel("You lose 20 times");
		sub5.add(s5);
		JPanel sub6 = new JPanel();
		 s6 = new JLabel("Complete games 30 times");
		sub6.add(s6);
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		JDialog d1 = this;
		JButton b1 = new JButton("back");
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				d1.dispose();
				
			}
		});
		p1.add(b1);
		sub4.add(s4);
		Panel.add(sub1);
		Panel.add(sub2);
		Panel.add(sub3);
		Panel.add(sub4);
		Panel.add(sub5);
		Panel.add(sub6);
		Panel.add(p1);
		this.add(Panel);
		
	}
	public void update(){
		if(QuestData.isFinish[0]) s1.setVisible(false);
		else if(QuestData.isFinish[1]) s4.setVisible(false);
		else if(QuestData.isFinish[2]) s5.setVisible(false);
		else if(QuestData.isFinish[3]) s6.setVisible(false);
		else if(QuestData.isFinish[4]) s2.setVisible(false);
		else if(QuestData.isFinish[5]) s3.setVisible(false);
	}
	
}
