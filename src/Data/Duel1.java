package Data;

import render.AnimationManager;
import res.Resource;

public class Duel1 extends Monster {
	private AnimationManager d1;
	private AnimationManager ad1;
	private boolean stand;
	
	public Duel1(){
		super(900,95,99,30);
		d1 = Resource.get("duel");
		ad1 = Resource.get("attackduel");
		stand = true;
		if(this.hp <=0) stand =false;
		
	}

	@Override
	public void increseStat(int level) {
	}

}
