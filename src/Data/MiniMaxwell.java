package Data;

import render.AnimationManager;
import res.Resource;

public class MiniMaxwell extends Monster {
	private AnimationManager mnmw;
	private AnimationManager amnmw;
	private boolean stand;

	public MiniMaxwell() {
		super(2000, 160, 150, 100);
		mnmw = Resource.get("minimaxwell");
		amnmw = Resource.get("attackminimaxwell");
		stand = true;
		if (this.hp <= 0) {
			stand = false;
		}
	}

	@Override
	public void increseStat(int level) {
		if (level == 2) {
			this.hp += 1000;
			this.at += 70;
			this.def += 30;
			this.gold += 30;
		} else if (level == 3) {
			this.hp += 1500;
			this.at += 120;
			this.def += 50;
			this.gold += 100;
		} else if (level == 4) {
			this.hp += 1800;
			this.at += 140;
			this.def += 80;
			this.gold += 200;

		}

	}
}
