package Data;

import render.AnimationManager;
import res.Resource;

public class Me extends Monster {
	private AnimationManager me;
	private AnimationManager ame; 
	private boolean stand;

	public Me() {
		super(600, 80, 60, 0);
		me = Resource.get("me");
		ame = Resource.get("attackme");
		stand = true;
		if (this.hp == 0)
			stand = false;
	}

	@Override
	public void increseStat(int level) {
		if (level == 2) {
			this.hp += 400;
			this.at += 40;
			this.def += 50;
			this.gold += 0;
		} else if (level == 3) {
			this.hp += 1400;
			this.at += 110;
			this.def += 70;
			this.gold += 0;
		} else if (level == 4) {
			this.hp += 3400;
			this.at += 160;
			this.def += 100;
			this.gold += 0;

		}

	}
}
