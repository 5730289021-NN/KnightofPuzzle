package Data;

import render.AnimationManager;
import res.Resource;

public class Gunner extends Monster{
	private AnimationManager gn;
	private AnimationManager agn;
	private boolean stand;
	
	public Gunner(){
		super(600,90,100,90);
		gn = Resource.get("gunner");
		agn = Resource.get("attackgunner");
		stand = true;
		if(this.hp <=0) stand =false;
	}
	@Override
	public void increseStat(int level) {
		if(level == 2){
			this.hp += 300;
			this.at += 45;
			this.def += 60;
			this.gold += 50;
		}
		
	}

}
