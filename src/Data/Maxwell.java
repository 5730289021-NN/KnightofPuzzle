package Data;

import render.AnimationManager;
import res.Resource;

public class Maxwell extends Monster {
	
	private int currentState = 1;

	private static int defaultHp = 5000;
	private static int defaultAttack = 400;
	private static int defaultDefense = 300;
	
	
	public Maxwell() {
		super(defaultHp, defaultAttack, defaultDefense, 650);
	}
	
	public void updateStat(int hp) {
		if(hp <= 500) {
			if(currentState != 4) {
				currentState = 4;
				at = 1700;
			} else {
				at = defaultAttack;
			}
		} else if(hp <= 2500) {
			if(currentState != 3) {
				currentState = 3;
				at = 500;
			} else {
				at = defaultAttack;
			}
		} else if(hp <= 3500) {
			if(currentState != 2) {
				currentState = 2;
				def = 500;
			} else {
				def = defaultDefense;
			}
		}
	}

	@Override
	public void increseStat(int level) {

	}

}
