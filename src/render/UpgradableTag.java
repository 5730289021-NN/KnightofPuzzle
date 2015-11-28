package render;

import Data.InfoManager;

public class UpgradableTag extends Tag{

	private int price;
	
	public UpgradableTag(int type, int rarity, int x, int y) {
		super(type, rarity, x, y);
		price = rarity * 500;
	}
	public int upgrade()
	{
		//TODO
		if(rarity != GOLD)
		{
			if(InfoManager.MONEY[InfoManager.SELECTED_SLOT] >= price)
			{
				InfoManager.MONEY[InfoManager.SELECTED_SLOT] -= price;
				price = rarity * 500;
				return 1;//Bought
			}
			else
				return 0;//No money
		}
		else
			return -1;//Already Max Level
	}
}
