package render;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class InventoryTag extends Tag{

	private boolean isEquipped;
	private boolean isUnlocked;
	
	public InventoryTag(int type, int rarity, int x, int y) {
		super(type, rarity, x, y);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void drawTag(Graphics2D g2)
	{
		if(!isUnlocked && !isEquipped)
		{
			g2.setComposite(AlphaComposite.SrcOver.derive(0f));
		}else if(isUnlocked && !isEquipped)
		{
			g2.setComposite(AlphaComposite.SrcOver.derive(0.5f));
		}else if(isUnlocked && isEquipped)
		{
			g2.setComposite(AlphaComposite.SrcOver.derive(1f));
		}else
		{
			g2.setComposite(AlphaComposite.SrcOver.derive(0f));
		}
		g2.drawImage(tagimg, realX, realY, tagWidth, tagHeight, null);
		g2.drawImage(accimg, realX, realY, tagWidth/2, accimg.getHeight() * 2 * deltax/accimg.getWidth(), null);
		g2.setComposite(AlphaComposite.SrcOver.derive(1f));
	}
	
	public void unlock()
	{
		isUnlocked = true;
		//TODO
	}
	public boolean isEquipped() {
		return isEquipped;
	}
	public void setEquipped(boolean isEquipped) {
		this.isEquipped = isEquipped;
	}
	public boolean isUnlocked() {
		return isUnlocked;
	}

}
