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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.sound.midi.MidiDevice.Info;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Data.InfoManager;
import main.Main;
import render.InventoryTag;
import render.ShopTag;
import render.Tag;
import render.UpgradableTag;
import res.Resource;

public class ShopScreen extends JComponent{
	private static double[] xpos = new double[24];
	private static double[] ypos = new double[24];
	private static double deltax = (double) 1/24;
	private static double deltay = (double) 1/18;
	
	public static int[] xpos_ = new int[xpos.length];
	public static int[] ypos_ = new int[ypos.length];
	public static int deltax_;
	public static int deltay_;
	
	private static Tag[] tags = new Tag[12];
	public static int counterBuyOrUpgrade =0;
	
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
		addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println(arg0.getX()+ " " + arg0.getY());
				for(Tag tag : tags)
				{
					if(tag.isWithin(arg0.getX(), arg0.getY()))
					{
						System.out.println("Tag Type: " + tag.getType() + " Tag Rarity: " + tag.getRarity() + " is pointed.");
						if(tag instanceof ShopTag)
						{
							if(((ShopTag) tag).isBuyable())
							{
								String name = "";
								counterBuyOrUpgrade++;
								switch(tag.getRarity())
								{
									case Tag.GOLD:
										name += "Golden ";
										break;
									case Tag.SILVER:
										name += "Silver ";
										break;
									case Tag.COPPER:
										name += "Copper ";
										break;
								}
								
								switch(tag.getType())
								{
									case Tag.SWORD:
										name += "Sword";
										break;
									case Tag.SHIELD:
										name += "Shield";
										break;
								}
								int choice = JOptionPane.showConfirmDialog(null,"Are you sure that you are going to buy it?", name ,JOptionPane.YES_NO_OPTION);
								if(choice == JOptionPane.YES_OPTION)
								{
									if(((ShopTag) tag).buyTag())
									{
										counterBuyOrUpgrade++;
										for(Tag tag2 : tags)
										{
											if(tag2 instanceof InventoryTag && tag2.getType() == tag.getType() && tag2.getRarity() == tag.getRarity())
											{
												if(tag2.getType() == Tag.SWORD)
													InfoManager.LEVEL_WEAPON[InfoManager.SELECTED_SLOT] += Math.pow(2, tag2.getRarity()-1);
												else if(tag2.getType() == Tag.SHIELD)
													InfoManager.LEVEL_ARMOR[InfoManager.SELECTED_SLOT] += Math.pow(2, tag2.getRarity()-1);
												((InventoryTag) tag2).unlock();	
												
												InfoManager.saveGame(InfoManager.NORMALSAVE);
											}
										}
									}else
									{
										JOptionPane.showMessageDialog(null, "Sorry, Insufficient Money");
									}
								}
							}
						}else if(tag instanceof InventoryTag)
						{
							if(((InventoryTag) tag).isUnlocked())
							{
								for(Tag tag2 : tags)
								{
									if(tag2 instanceof InventoryTag && tag2.getType() == tag.getType())
									{
										((InventoryTag) tag2).setEquipped(false);
									}
								}
								((InventoryTag) tag).setEquipped(true);
								if(tag.getType() == Tag.SWORD)
								{
									InfoManager.SWORDEQUIP[InfoManager.SELECTED_SLOT] = ((InventoryTag) tag).getRarity();
								}else if(tag.getType() == Tag.SHIELD)
								{
									InfoManager.ARMOREQUIP[InfoManager.SELECTED_SLOT] = ((InventoryTag) tag).getRarity();
								}
								InfoManager.saveGame(InfoManager.NORMALSAVE);
							}
						}else if(tag instanceof UpgradableTag)
						{
							String name = "";
							switch(tag.getType())
							{
							case Tag.SMALLPOTION:
								name = "Small Potion";
								break;
							case Tag.LARGEPOTION:
								name = "Large Potion";
								break;
							}
							int choice = JOptionPane.showConfirmDialog(null,"Are you sure that you are going to upgrade it?", name ,JOptionPane.YES_NO_OPTION);
							if(choice == JOptionPane.YES_OPTION)
							{
							int situation = ((UpgradableTag) tag).upgrade();
							
							switch(situation)
							{
							case 1:
								if(tag.getType() == Tag.SMALLPOTION)
								InfoManager.LEVEL_SMALLPOTION[InfoManager.SELECTED_SLOT] = tag.getRarity();
								else if(tag.getType() == Tag.LARGEPOTION)
								InfoManager.LEVEL_LARGEPOTION[InfoManager.SELECTED_SLOT] = tag.getRarity();
								InfoManager.saveGame(InfoManager.NORMALSAVE);
								break;
							case 0:
								JOptionPane.showMessageDialog(null, "Sorry, Insufficient Money");
								break;
							case -1:
								JOptionPane.showMessageDialog(null, "You already have Maximum Potion Level");
								break;
							}
						}
						}
					}
					
					
				}
			}
		});
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		g2.setColor(Color.GRAY);
		g2.fillRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
		
		g2.setComposite(AlphaComposite.SrcOver.derive(0.75f));
		g2.drawImage(Resource.get("inventorybg").getCurrentBufferedImage(), 0, 0, GameScreen.WIDTH,GameScreen.HEIGHT,null);
		g2.setComposite(AlphaComposite.SrcOver.derive(1f));
		
		g2.setColor(Color.WHITE);
		g2.setFont(boldfont);
		
		drawWord("Shop",4,2,g2);
		drawWord("Inventory",16,2,g2);
		
		g2.setFont(font);
		drawWord("Sword",0,4,g2);
		drawWord("Sword",11,4,g2);
		drawWord("Shield",0,7,g2);
		drawWord("Shield", 11, 7, g2);
		drawWord("Potion", 11, 10, g2);
		drawWord("Small", 9, 11, g2);
		drawWord("Large", 9, 14, g2);
		drawWord("Money : " + InfoManager.MONEY[InfoManager.SELECTED_SLOT], 1, 11, g2);

		for(Tag tag : tags)
		{
			tag.drawTag(g2);
		}
	}
	

	
	private void drawWord(String word,int xpo,int ypo,Graphics2D g2)
	{
		g2.drawString(word, xpos_[xpo], ypos_[ypo] - fm.getDescent());
	}
	
	public void calculateData()
	{
		//INVENTORY
		int rem;
		//SWORD
		tags[4] = new InventoryTag(Tag.SWORD,Tag.COPPER, 11, 4);
		tags[5] = new InventoryTag(Tag.SWORD,Tag.SILVER, 15, 4);
		tags[6] = new InventoryTag(Tag.SWORD,Tag.GOLD, 19, 4);
		rem = InfoManager.LEVEL_WEAPON[InfoManager.SELECTED_SLOT];
		for(int i = 4;i<7;i++)
		{
			if(rem%2 == 1)
				((InventoryTag) tags[i]).unlock();
			rem /= 2;
		}//One Hot
		((InventoryTag)tags[3 + InfoManager.SWORDEQUIP[InfoManager.SELECTED_SLOT]]).setEquipped(true);
		
		//SHIELD
		tags[7] = new InventoryTag(Tag.SHIELD,Tag.COPPER, 11, 7);
		tags[8] = new InventoryTag(Tag.SHIELD,Tag.SILVER, 15, 7);
		tags[9] = new InventoryTag(Tag.SHIELD,Tag.GOLD, 19, 7);
		rem = InfoManager.LEVEL_ARMOR[InfoManager.SELECTED_SLOT];
		for(int i = 7;i<10;i++)
		{
			//System.out.println(rem);
			if(rem%2 == 1)
				((InventoryTag) tags[i]).unlock();
			rem /= 2;
		}//One Hot
		((InventoryTag)tags[6+InfoManager.ARMOREQUIP[InfoManager.SELECTED_SLOT]]).setEquipped(true);
		
		//UPGRADABLE
		//SMALL POTION
		tags[10] = new UpgradableTag(Tag.SMALLPOTION,InfoManager.LEVEL_SMALLPOTION[InfoManager.SELECTED_SLOT], 11, 10);
		tags[11] = new UpgradableTag(Tag.LARGEPOTION,InfoManager.LEVEL_LARGEPOTION[InfoManager.SELECTED_SLOT], 11, 13);
		
		//SHOP
		//SWORD
		tags[0] = new ShopTag(Tag.SWORD,Tag.SILVER, 0, 4, 800);
		tags[1] = new ShopTag(Tag.SWORD,Tag.GOLD, 5, 4, 2700);
		for(int i = 0; i <2;i++)
		{
			if(((InventoryTag) tags[i+5]).isUnlocked())
			{
				((ShopTag) tags[i]).setAlreadyBought();
			}
		}
		//SHIELD
		tags[2] = new ShopTag(Tag.SHIELD,Tag.SILVER, 0, 7, 400);
		tags[3] = new ShopTag(Tag.SHIELD,Tag.GOLD, 5, 7, 1500);
		for(int i = 2; i<4;i++)
		{
			if(((InventoryTag) tags[i+6]).isUnlocked())
			{
				((ShopTag) tags[i]).setAlreadyBought();
				//System.out.println("Bought");
			}
		}
	}
}
