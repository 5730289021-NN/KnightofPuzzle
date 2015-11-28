package render;

import Data.InfoManager;

public class UpgradableTag extends Tag{

	
	public UpgradableTag(int type, int rarity, int x, int y) {
		super(type, rarity, x, y);
	}
	public int upgrade()
	{
		//TODO
		int price = rarity * 500;
		if(rarity != GOLD)
		{
			if(InfoManager.MONEY[InfoManager.SELECTED_SLOT] >= price)
			{
				InfoManager.MONEY[InfoManager.SELECTED_SLOT] -= price;
				return 1;//Bought
			}
			else
				return 0;//No money
		}
		else
			return -1;//Already Max Level
	}
}
