package base;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import javafx.scene.control.ProgressBar;

public class QuestScreen extends JComponent {
	private ProgressBar pb;
	private int money;
	private int reward;
	private int RewardComplete;
	private double ValueProgress;
	private boolean isDone = false;
	private ArrayList<String> ListQuest = new ArrayList<String>();
	private JList<ListQuest> jl;
	public QuestScreen(){
		super();
		setVisible(true);
		setFocusable(true);
		requestFocus();
		int x =1;
		JPanel p1 = new JPanel();
		JLabel l1 = new JLabel("Rank "+ x + "                    Reward "+ money);
		pb = new ProgressBar(ValueProgress);
		
	}
	public void updateRank(){
		
	}
	private void calculate(int x,double ValueProgress,int reward,int RewardComplete){
		
		
	}
}
