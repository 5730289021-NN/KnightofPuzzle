package render;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import Data.InfoManager;
import base.GameScreen;
import res.Resource;

public class ShopTag extends Tag{
	private boolean isBuyable;
	private int price;
	
	public ShopTag(int type, int rarity, int x, int y, int price) {
		super(type, rarity, x, y);
		isBuyable = true;
		this.price = price;
	}
	
	public boolean isBuyable()
	{
		return isBuyable;
	}
	
	public void setAlreadyBought()
	{
		this.isBuyable = false;
	}
	
	public boolean buyTag(){
		if(InfoManager.MONEY[InfoManager.SELECTED_SLOT] >= price)
		{
			InfoManager.MONEY[InfoManager.SELECTED_SLOT] -= price;
			this.isBuyable = false;
			return true;
		}
		else
			return false;
	}
	
	@Override
	public void drawTag(Graphics2D g2)
	{
		if(!isBuyable)
		{
			g2.setColor(Color.BLACK);
			g2.setComposite(AlphaComposite.SrcOver.derive(0.5f));
		}
		g2.drawImage(tagimg, realX, realY, tagWidth, tagHeight, null);
		g2.drawImage(accimg, realX, realY, tagWidth/2, accimg.getHeight() * 2 * deltax/accimg.getWidth(), null);
		g2.setComposite(AlphaComposite.SrcOver.derive(1f));
	}
}
