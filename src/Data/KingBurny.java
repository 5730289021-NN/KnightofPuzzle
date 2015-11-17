package Data;

import render.AnimationManager;
import res.Resource;

public class KingBurny extends Monster {
	private  AnimationManager kb;
	private  AnimationManager akb;
	private  boolean stand;
	
	public KingBurny(){
		super(600,90,120,25);
		kb = Resource.get("kingburny");
		akb = Resource.get("attackkingburny");
		stand = true;
		if(this.hp <=0) stand =false;
	}

	@Override
	public void increseStat(int level) {
	}

}
