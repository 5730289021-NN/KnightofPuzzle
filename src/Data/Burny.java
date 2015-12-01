package Data;

import render.AnimationManager;
import res.Resource;

public class Burny extends Monster{
	
	private AnimationManager bn;
	private AnimationManager abn;
	private boolean stand;
	
	public Burny(){
		super(400,80,70,30);
		bn = Resource.get("burny");
		abn = Resource.get("attackburny");
		stand = true;
		if(this.hp <=0) stand =false;
	}

	@Override
	public void increseStat(int level) {
		if(level ==2){
			this.hp += 200;
			this.at += 50;
			this.def += 70;
			this.gold += 20;
		}
	}

	

}
