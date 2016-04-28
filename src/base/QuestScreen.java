package base;

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

public class QuestScreen extends JDialog {
	private JProgressBar pb;
	private int money;
	private int reward;
	private int RewardComplete;
	private int ValueProgress;
	private int n1,n2,n3,n4;
	private boolean isDone = false;
	private ArrayList<String> ListQuest = new ArrayList<String>();
	private JList<JPanel> jl;
	public QuestScreen(){
		super();
		int x =1;
		JLabel l1 = new JLabel("Rank "+ x + "                                                                Reward "+ reward);
		pb = new JProgressBar(ValueProgress);
		JPanel Panel = new JPanel();
		Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
		JPanel sub1 = new JPanel();
		JLabel s1 = new JLabel("Kill " + n1 +" enemies"+"  Reward "+RewardComplete);
		sub1.add(s1);
		JPanel sub2 = new JPanel();
		JLabel s2 = new JLabel("Use fury "+ n2+"  Reward "+RewardComplete);
		sub2.add(s2);
		JPanel sub3 = new JPanel();
		JLabel s3 = new JLabel("Kill boss "+ n3+"  Reward "+RewardComplete);
		sub3.add(s3);
		JPanel sub4 = new JPanel();
		JLabel s4 = new JLabel("Total kill "+ n4+"  Reward "+RewardComplete);
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
		Panel.add(l1);
		Panel.add(pb);
		Panel.add(sub1);
		Panel.add(sub2);
		Panel.add(sub3);
		Panel.add(sub4);
		Panel.add(p1);
		this.add(Panel);
		
		
	}
	
	private void calculate(int x,double ValueProgress,int reward,int RewardComplete){
		if(isDone == true){
			ValueProgress += RewardComplete;
			if(ValueProgress >=100) {
				ValueProgress = ValueProgress - 100;
				x++;
				InfoManager.MONEY[InfoManager.SELECTED_SLOT] += reward;
				reward += 150;
				
			}
		}
		
	}
}
