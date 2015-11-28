package Data;

import render.AnimationManager;
import res.Resource;

public class Burny extends Monster{
	
	private AnimationManager bn;
	private AnimationManager abn;
	private boolean stand;
	
	public Burny(){
		super(300,50,50,10);
		bn = Resource.get("burny");
		abn = Resource.get("attackburny");
		stand = true;
		if(this.hp <=0) stand =false;
	}

	@Override
	public void increseStat(int level) {
	}

	

}
