package Data;

import render.AnimationManager;
import res.Resource;

public class MiniMaxwell extends Monster {
	private AnimationManager mnmw;
	private AnimationManager amnmw;
	private boolean stand;

	public MiniMaxwell() {
		super(2000, 110, 140, 50);
		mnmw = Resource.get("minimaxwell");
		amnmw = Resource.get("attackminimaxwell");
		stand = true;
		if(this.hp <= 0){
			stand = false;
		}
	}

	@Override
	public void increseStat(int level) {
		if (level == 2) {
			this.hp += 1000;
			this.at += 50;
			this.def += 10;
			this.gold += 30;
		} else if (level == 3) {
			this.hp += 1500;
			this.at += 110;
			this.def += 40;
			this.gold += 70;
		} else if (level == 4) {
			this.hp += 1800;
			this.at += 120;
			this.def += 60;
			this.gold += 150;

		}

	}
}
