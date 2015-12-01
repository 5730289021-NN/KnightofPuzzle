package Data;

import render.AnimationManager;
import res.Resource;

public class MegaBurny extends Monster {
	private AnimationManager mb;
	private AnimationManager amb;
	private boolean stand;
	
	public MegaBurny(){
		super(1300,190,180,140);
		mb = Resource.get("megaburny");
		amb = Resource.get("attackmegaburny");
		stand = true;
		if(this.hp <=0) stand =false;
	}

	@Override
	public void increseStat(int level) {

	}

}
