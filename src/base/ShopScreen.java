package base;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import main.Main;
import res.Resource;

public class ShopScreen extends JComponent{
	private static final double[] xpos = {0, 0.125, 0.25, 0.375, 0.5, 0.625, 0.75, 0.875};
	private static final double[] ypos = {0, 0.2, 0.4, 0.6, 0.8};
	private static int[] xpos_ = new int[xpos.length];
	private static int[] ypos_ = new int[ypos.length];
	private static JButton backButton;
	private static final Font boldfont = new Font("Tahoma", Font.BOLD, 30);
	private static final Font font = new Font("Tahoma", Font.PLAIN, 25);
	private static String[] label = {"Shop", "Inventory", "Sword", "Shield", "Large Potion", "Small Potion"};
	private static FontMetrics fm = new FontMetrics(font) {};
	static{
		for(int i = 0;i<xpos_.length;i++)
		{
			xpos_[i] = (int) (GameScreen.WIDTH * xpos[i]);
		}
		for(int i = 0;i<ypos_.length;i++)
		{
			ypos_[i] = (int) (GameScreen.HEIGHT * ypos[i]);
		}
		
	}
	
	public ShopScreen(){
		super();
		setLayout(new BorderLayout());
		JPanel buttomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttomPanel.setBackground(new Color(0, 0, 0, 0));
		setPreferredSize(new Dimension(GameScreen.WIDTH, GameScreen.HEIGHT));
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.changeGameScreen(Main.SELECTSCREEN);
			}
		});
		buttomPanel.add(backButton);
		add(buttomPanel,BorderLayout.SOUTH);
		setVisible(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
		
		g2.setComposite(AlphaComposite.SrcOver.derive(0.75f));
		g2.drawImage(Resource.get("inventorybg").getCurrentBufferedImage(), 0, 0, GameScreen.WIDTH,GameScreen.HEIGHT,null);
		g2.setComposite(AlphaComposite.SrcOver.derive(1f));
		
		
		g2.setFont(boldfont);
		g2.setColor(Color.GRAY);
		
		g2.drawString(label[0], xpos_[1], (int) (ypos_[0] + fm.getHeight()));
		g2.drawString(label[1], xpos_[5], (int) (ypos_[0] + fm.getHeight()));
		
		
		g2.setFont(font);
		g2.drawString(label[2], xpos_[0], (int) (ypos_[1] + fm.getHeight()));
		g2.drawString(label[3], xpos_[0], (int) (ypos_[2] + fm.getHeight()));
		g2.drawString(label[2], xpos_[4], (int) (ypos_[1] + fm.getHeight()));
		g2.drawString(label[3], xpos_[4], (int) (ypos_[2] + fm.getHeight()));
		g2.drawString(label[4], xpos_[4], (int) (ypos_[3] + fm.getHeight()));
		g2.drawString(label[5], xpos_[4], (int) (ypos_[4] + fm.getHeight()));
	}
}
