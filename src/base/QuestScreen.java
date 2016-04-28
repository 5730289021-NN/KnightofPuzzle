package base;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import Data.InfoManager;
import javafx.scene.control.ProgressBar;

public class QuestScreen extends JComponent {
	private ProgressBar pb;
	private int money;
	private int reward;
	private int RewardComplete;
	private double ValueProgress;
	private int n1,n2,n3,n4;
	private boolean isDone = false;
	private ArrayList<String> ListQuest = new ArrayList<String>();
	private JList<JPanel> jl;
	public QuestScreen(){
		super();
		setVisible(true);
		setFocusable(true);
		requestFocus();
		int x =1;
		JPanel p1 = new JPanel();
		JLabel l1 = new JLabel("Rank "+ x + "                    Reward "+ reward);
		pb = new ProgressBar(ValueProgress);
		JPanel PanelDown = new JPanel();
		JPanel sub1 = new JPanel();
		JLabel s1 = new JLabel("Kill " + n1 +" enemies");
		JPanel sub2 = new JPanel();
		JLabel s2 = new JLabel("Use fury "+ n2);
		JPanel sub3 = new JPanel();
		JLabel s3 = new JLabel("kuy");
		JPanel sub4 = new JPanel();
		JLabel s4 = new JLabel("kuy");
		JPanel sub5 = new JPanel();
		
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
