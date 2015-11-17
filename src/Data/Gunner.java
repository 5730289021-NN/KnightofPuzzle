package Data;

import render.AnimationManager;
import res.Resource;

public class Gunner extends Monster{
	private AnimationManager gn;
	private AnimationManager agn;
	private boolean stand;
	
	public Gunner(){
		super(500,60,80,20);
		gn = Resource.get("gunner");
		agn = Resource.get("attackgunner");
		stand = true;
		if(this.hp <=0) stand =false;
	}
	@Override
	public void increseStat(int level) {
		if(level == 2){
			this.hp += 200;
			this.at += 20;
			this.def += 20;
			this.gold += 5;
		}
		
	}

}
