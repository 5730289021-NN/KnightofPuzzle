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
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import main.Main;
import res.Resource;

public class ShopScreen extends JComponent{
	private static double[] xpos = new double[24];
	private static double[] ypos = new double[24];
	private static double deltax = (double) 1/24;
	private static double deltay = (double) 1/18;
	
	private static int[] xpos_ = new int[xpos.length];
	private static int[] ypos_ = new int[ypos.length];
	private static int deltax_;
	private static int deltay_;
	
	private static BufferedImage coppertag = Resource.get("coppertag").getCurrentBufferedImage();
	private static BufferedImage silvertag = Resource.get("silvertag").getCurrentBufferedImage();
	private static BufferedImage goldentag = Resource.get("goldentag").getCurrentBufferedImage();
	
	private static JButton backButton;
	private static final Font boldfont = new Font("Tahoma", Font.BOLD, 30);
	private static final Font font = new Font("Tahoma", Font.PLAIN, 25);
	//private static String[] label = {"Shop", "Inventory", "Sword", "Shield", "Large Potion", "Small Potion"};
	private static FontMetrics fm = new FontMetrics(font) {};
	static{
		for(int i = 0;i<xpos.length;i++)
		{
			xpos[i] = deltax * i;
		}
		
		for(int i = 0;i<ypos.length;i++)
		{
			ypos[i] = deltay * i;
		}
		
		for(int i = 0;i<xpos_.length;i++)
		{
			xpos_[i] = (int) (GameScreen.WIDTH * xpos[i]);
		}
		
		for(int i = 0;i<ypos_.length;i++)
		{
			ypos_[i] = (int) (GameScreen.HEIGHT * ypos[i]);
		}
		
		deltax_ = xpos_[1] - xpos_[0];
		deltay_ = ypos_[1] - ypos_[0];
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
		
		drawWord("Shop",4,2,g2);
		drawWord("Inventory",16,2,g2);
		drawWord("Sword",1,4,g2);
		drawWord("Sword",11,4,g2);
		drawWord("Shield",1,7,g2);
		drawWord("Shield", 11, 7, g2);
		drawWord("Potion", 11, 10, g2);
		drawWord("Small", 9, 11, g2);
		drawWord("Large", 9, 14, g2);
		
		//TODO
		drawTag(silvertag, 1, 4, g2);
		drawTag(goldentag, 5, 4, g2);
		drawTag(silvertag, 1, 7, g2);
		drawTag(goldentag, 5, 7, g2);
		
		drawTag(coppertag, 11, 4, g2);
		drawTag(silvertag, 15, 4, g2);
		drawTag(goldentag, 19, 4, g2);
		
		drawTag(coppertag, 11, 7, g2);
		drawTag(silvertag, 15, 7, g2);
		drawTag(goldentag, 19, 7, g2);
		
		drawTag(coppertag, 11, 10, g2);
		drawTag(silvertag, 15, 10, g2);
		drawTag(goldentag, 19, 10, g2);
		
		drawTag(coppertag, 11, 13, g2);
		drawTag(silvertag, 15, 13, g2);
		drawTag(goldentag, 19, 13, g2);
	}
	
	private void drawTag(BufferedImage tag,int xpo,int ypo,Graphics2D g2)
	{
		g2.drawImage(tag, xpos_[xpo], ypos_[ypo], 4 * deltax_, tag.getHeight() * 4 * deltax_/tag.getWidth(), null);
	}
	
	private void drawWord(String word,int xpo,int ypo,Graphics2D g2)
	{
		g2.drawString(word, xpos_[xpo], ypos_[ypo]);
	}
}
