package Data;

import res.Resource;

import render.AnimationManager;

public class Sniper extends Monster {
	private AnimationManager sp;
	private AnimationManager asp;
	private boolean stand;
	
	public Sniper(){
		super(1400,290,130,110);
		sp = Resource.get("megaburny");
		asp = Resource.get("attacksniper");
		stand = true;
		if(this.hp <=0) stand =false;
	}

	@Override
	public void increseStat(int level) {
		if(level == 4){
			this.hp += 400;
			this.at += 30;
			this.def += 40;
			this.gold += 50;
			
		}
			
		
	}

	
}
