package Data;

import render.AnimationManager;
import res.Resource;

public class Duel2 extends Monster{
	private AnimationManager d2;
	private AnimationManager ad2;
	private boolean stand;
	
	public Duel2(){
		super(1600,210,160,130);
		d2 = Resource.get("duel2");
		ad2 = Resource.get("attackduel2");
		stand = true;
		if(this.hp <=0) stand =false;
		
	}

	@Override
	public void increseStat(int level) {
		if(level ==4){
		this.hp += 600;
		this.at += 60;
		this.def += 60;
		this.gold += 50;
		}
		
	}

}
