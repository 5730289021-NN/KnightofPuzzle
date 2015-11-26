package render;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import base.ShopScreen;
import res.Resource;

public class Tag {
	private static final BufferedImage coppertag = Resource.get("coppertag").getCurrentBufferedImage();
	private static final BufferedImage silvertag = Resource.get("silvertag").getCurrentBufferedImage();
	private static final BufferedImage goldentag = Resource.get("goldentag").getCurrentBufferedImage();
	
	private static final BufferedImage swordimg = Resource.get("sword").getCurrentBufferedImage();
	private static final BufferedImage sword1img = Resource.get("sword1").getCurrentBufferedImage();
	private static final BufferedImage sword2img = Resource.get("sword2").getCurrentBufferedImage();
	private static final BufferedImage shieldimg = Resource.get("shield").getCurrentBufferedImage();
	private static final BufferedImage shield1img = Resource.get("shield1").getCurrentBufferedImage();
	private static final BufferedImage shield2img = Resource.get("shield2").getCurrentBufferedImage();
	private static final BufferedImage smallpotionimg = Resource.get("smallpotion").getCurrentBufferedImage();
	private static final BufferedImage largepotionimg = Resource.get("largepotion").getCurrentBufferedImage();
	
	public static final int GOLD = 3;
	public static final int SILVER = 2;
	public static final int COPPER = 1;
	
	public static final int SWORD = 1;
	public static final int SHIELD = 2;
	public static final int SMALLPOTION = 3;
	public static final int LARGEPOTION = 4;
	private static int deltax = ShopScreen.deltax_;
	
	
	private int X;
	private int Y;
	private int realX;
	private int realY;
	private int type;
	private int rarity;
	private BufferedImage tagimg;
	private BufferedImage accimg;
	private int tagWidth;
	private int tagHeight;
	
	private boolean isBought;
	private boolean isSelected;
	
	
	public int getType() {
		return type;
	}

	public int getRarity() {
		return rarity;
	}

	public Tag(int type,int rarity,int x,int y)
	{
		this.type = type;
		this.rarity = rarity;
		switch(type)
		{
		case SWORD:
			switch(rarity)
			{
			case GOLD:
				tagimg = goldentag;
				accimg = sword2img;
				break;
			case SILVER:
				tagimg = silvertag;
				accimg = sword1img;
				break;
			case COPPER:
				tagimg = coppertag;
				accimg = swordimg;
				break;
			}
			break;
		case SHIELD:
			switch(rarity)
			{
			case GOLD:
				tagimg = goldentag;
				accimg = shield2img;
				break;
			case SILVER:
				tagimg = silvertag;
				accimg = shield1img;
				break;
			case COPPER:
				tagimg = coppertag;
				accimg = shieldimg;
				break;
			}
			break;
		case SMALLPOTION:
			switch(rarity)
			{
			case GOLD:
				tagimg = goldentag;
				break;
			case SILVER:
				tagimg = silvertag;
				break;
			case COPPER:
				tagimg = coppertag;
				break;
			}
			accimg = smallpotionimg;
			break;
		case LARGEPOTION:
			switch(rarity)
			{
			case GOLD:
				tagimg = goldentag;
				break;
			case SILVER:
				tagimg = silvertag;
				break;
			case COPPER:
				tagimg = coppertag;
				break;
			}
			accimg = largepotionimg;
			break;
		}
		this.X = x;
		this.Y = y;
		this.realX = ShopScreen.xpos_[this.X];
		this.realY = ShopScreen.ypos_[this.Y];
		this.tagWidth = 4 * deltax;
		this.tagHeight = 4 * tagimg.getHeight() * deltax/tagimg.getWidth();
	}
	
	public void drawTag(Graphics2D g2)
	{
		g2.drawImage(tagimg, realX, realY, tagWidth, tagHeight, null);
		g2.drawImage(accimg, realX, realY, tagWidth/2, accimg.getHeight() * 2 * deltax/accimg.getWidth(), null);
	}
	
	public boolean isWithin(int x, int y)
	{
		if(x > realX && x < realX + tagWidth && y > realY && y < realY + tagHeight)
		{
			System.out.println("Correct");
			return true;
		}else
		{
			//System.out.println("Range X :" + realX + "-" + (realX + tagWidth));
			//System.out.println("Range Y :" + realY + "-" + (realY + tagHeight));
			return false;
		}
	}
	

}
