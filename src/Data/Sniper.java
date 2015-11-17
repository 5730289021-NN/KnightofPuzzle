package Data;

import res.Resource;

import render.AnimationManager;

public class Sniper extends Monster {
	private AnimationManager sp;
	private AnimationManager asp;
	private boolean stand;
	
	public Sniper(){
		super(1300,250,100,40);
		sp = Resource.get("megaburny");
		asp = Resource.get("attacksniper");
		stand = true;
		if(this.hp <=0) stand =false;
	}

	@Override
	public void increseStat(int level) {
		if(level == 4){
			this.hp += 200;
			this.at += 20;
			this.def += 30;
			this.gold += 20;
			
		}
			
		
	}

	
}
