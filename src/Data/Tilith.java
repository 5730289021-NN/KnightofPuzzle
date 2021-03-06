package Data;

import render.AnimationManager;
import res.Resource;

public class Tilith extends Monster{
	private AnimationManager tl;
	private AnimationManager atl;
	private boolean stand;
	
	public Tilith(){
		super(1200,170,130,80);
		tl = Resource.get("tilith");
		atl = Resource.get("attacktilith");
		stand = true;
		if(this.hp <= 0) stand =false;
	}

	@Override
	public void increseStat(int level) {
		if(level ==3){
			this.hp += 200;
			this.at += 40;
			this.def += 60;
			this.gold += 40;
		}
		else if(level ==4){
			this.hp += 800;
			this.at += 70;
			this.def += 70;
			this.gold += 40;
		}
		
	}

}
