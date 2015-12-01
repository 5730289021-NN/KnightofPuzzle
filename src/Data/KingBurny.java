package Data;

import render.AnimationManager;
import res.Resource;

public class KingBurny extends Monster {
	private  AnimationManager kb;
	private  AnimationManager akb;
	private  boolean stand;
	
	public KingBurny(){
		super(650,140,180,130);
		kb = Resource.get("kingburny");
		akb = Resource.get("attackkingburny");
		stand = true;
		if(this.hp <=0) stand =false;
	}

	@Override
	public void increseStat(int level) {
	}

}
