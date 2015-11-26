package render;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import base.GameScreen;
import res.Resource;

public class ShopTag extends Tag{
	private boolean isBuyable;
	
	public ShopTag(int type, int rarity, int x, int y) {
		super(type, rarity, x, y);
		isBuyable = false;
	}
	
	public void buyTag(){
		this.isBuyable = false;
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
