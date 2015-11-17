package Data;

import render.AnimationManager;
import res.Resource;

public class Gunner2 extends Monster{
	private AnimationManager gn2;
	private AnimationManager agn2;
	private boolean stand;
	
	public Gunner2(){
		super(2500,270,210,150);
		gn2 = Resource.get("gunner2");
		agn2 = Resource.get("attackgunner2");
		stand = true;
		if(this.hp <=0) stand =false;
	}
	@Override
	public void increseStat(int level) {
	}

}
