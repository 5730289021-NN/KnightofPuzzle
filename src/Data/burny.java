package Data;

import render.AnimationManager;
import res.Resource;

public class burny implements Stat{
	
	private AnimationManager bn;
	private AnimationManager abn;
	private boolean stand;
	
	public burny(){
		bn = Resource.get("burny");
		abn = Resource.get("attackburny");
		stand = true;
	}

	@Override
	public int attack() {
		return 200;
	}

	@Override
	public int defense() {
		
		return 50;
	}

	@Override
	public int hp() {
		return 600;
	}

	@Override
	public int fury() {
		return 0;
	}

	@Override
	public boolean isDestroyed() {
		return false;
	}

}
