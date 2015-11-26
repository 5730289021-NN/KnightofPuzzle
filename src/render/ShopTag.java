package render;

import java.awt.Graphics2D;

public class ShopTag extends Tag{
	private boolean isBuyable;
	
	public ShopTag(int type, int rarity, int x, int y) {
		super(type, rarity, x, y);
		isBuyable = true;
	}
	
	public void buyTag(){
		this.isBuyable = false;
	}
	
	@Override
	public void drawTag(Graphics2D g2)
	{
		if(!isBuyable)
		{
			
			g2.drawImage(tagimg, realX, realY, tagWidth, tagHeight, null);
			g2.drawImage(accimg, realX, realY, tagWidth/2, accimg.getHeight() * 2 * deltax/accimg.getWidth(), null);
			
		}
	}
}
