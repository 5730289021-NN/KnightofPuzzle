package LogicGame;

import main.Main;
import Data.Burny;
import Data.Duel1;
import Data.Duel2;
import Data.Gunner;
import Data.Gunner2;
import Data.InfoManager;
import Data.KingBurny;
import Data.Maxwell;
import Data.Me;
import Data.MegaBurny;
import Data.MiniMaxwell;
import Data.Monster;
import Data.Sniper;
import Data.Tilith;
import frame.PlayFrame;

public class PlayLogic {

	private static final int GAME_TIME_DURATION = 12;
	private static final int MAXIMUM_FURY = 7;
	private static final int[] MAXIMUM_WAVE = {0,4,4,5,7};

	private int timeCounter;
	private int timeStamp = 0;
	private int previousTimeStampSecond = 0;
	private PlayFrame game;

	private Monster monster;
	private Monster me;
	private int hpMe, hpMonster;
	private int furyCounting;
	private boolean useFury;
	private int currentGold;

	private int level, wave;
	
	public PlayLogic(PlayFrame game, int level) {
		timeStamp = 0;
		timeCounter = GAME_TIME_DURATION;
		this.game = game;
		furyCounting = 0;
		currentGold = 0;
		useFury = false;
		
		this.level = level;
		wave = 1;
		
		me = new Me();
		me.increseStat(level);
		this.hpMe = me.getHp();
	}

	public void updateTimeStamp() {
		timeStamp += Main.SleepTime;
		if (timeStamp / 1000 != previousTimeStampSecond) {
			previousTimeStampSecond = timeStamp / 1000;
			countingTime();
		}
	}

	public void countingTime() {
		timeCounter--;
		if (timeCounter <= 0) {
			timeCounter = GAME_TIME_DURATION;
			game.miniGameComplete();
		}
	}

	public int getTimeCounter() {
		return timeCounter;
	}
	
	public void setMonster(Monster monster) {
		this.monster = monster;
		this.monster.increseStat(level);
		this.hpMonster = monster.getHp();
	}
	
	public void setMonster(String name) {
		if(name.equals("burny")) {
			setMonster(new Burny());
		} else if(name.equals("gunner")) {
			setMonster(new Gunner());
		} else if(name.equals("duel")) {
			setMonster(new Duel1());
		} else if(name.equals("minimaxwell")) {
			setMonster(new MiniMaxwell());
		} else if(name.equals("tilith")) {
			setMonster(new Tilith());
		} else if(name.equals("kingburny")) {
			setMonster(new KingBurny());
		} else if(name.equals("sniper")) {
			setMonster(new Sniper());
		} else if(name.equals("duel2")) {
			setMonster(new Duel2());
		} else if(name.equals("megaburny")) {
			setMonster(new MegaBurny());
		} else if(name.equals("gunner2")) {
			setMonster(new Gunner2());
		} else if(name.equals("maxwell")) {
			setMonster(new Maxwell());
		} else {
			throw new RuntimeException("No name found in setMonster in PlayLogic");
		}
	}
	
	public int getHpMe() {
		return Math.max(0, hpMe);
	}
	
	public int getFullHpMe() {
		return me.getHp();
	}
	
	public int getHpMePercentage() {
		return hpMe * 100 / me.getHp();
	}
	
	public int getHpMonster() {
		return Math.max(0, hpMonster);
	}
	
	public int getFullHpMonster() {
		return monster.getHp();
	}
	
	public int calculateDecreaseHpMe(int sword, int shield) {
		
		int def = 0;
		int armorEquip = InfoManager.ARMOREQUIP[InfoManager.SELECTED_SLOT];
		
		switch(armorEquip) {
		case 1 : def = 47; break;
		case 2 : def = 94; break;
		case 3 : def = 141; break;
		}
		
		return Math.max(1 , monster.getAt() + (10 * (sword / 2)) - (me.getDef() + def));
	}
	
	public void decreaseHpMe(int sword, int shield) {
		
		if(monster instanceof Maxwell) {
			((Maxwell) monster).updateStat(hpMonster);
		}
		
		hpMe -= calculateDecreaseHpMe(sword, shield);
	}
	
	public int getHpMonsterPercentage() {
		return hpMonster * 100 / monster.getHp();
	}
	
	public int calculateDecreaseHpMonster(int sword, int shield) {
		
		int swordEquip = InfoManager.SWORDEQUIP[InfoManager.SELECTED_SLOT];
		int attack = 0;
		
		switch(swordEquip) {
		case 1 : attack = 8; break;
		case 2 : attack = 16; break;
		case 3 : attack = 24; break;
		}
		
		int fury = 0;
		if(useFury) {
			fury = 500;
		}
		
		return Math.max(1, me.getAt() + fury + attack + 10 * sword - monster.getDef());
	}
	
	public void decreaseHpMonster(int sword, int shield) {
		
		if(useFury) {
			furyCounting = 0;
			useFury = false;
		}
		
		hpMonster -= calculateDecreaseHpMonster(sword, shield);
	}
	
	public int getGold() {
		return currentGold;
	}
	
	public void increaseGold() {
		currentGold += monster.getGold();
	}
	
	public void updateGoldToInfo() {
		InfoManager.MONEY[InfoManager.SELECTED_SLOT] += currentGold;
	}
	
	public boolean isMonsterDie() {
		return hpMonster <= 0;
	}
	
	public boolean isMeDie() {
		return hpMe <= 0;
	}
	
	public boolean isWaveComplete() {
		return wave > MAXIMUM_WAVE[level];
	}
	
	public void setUseFury() {
		if(furyCounting == MAXIMUM_FURY) {
			useFury = true;
		}
	}
	
	public boolean isUseFury() {
		return useFury;
	}
	
	public String getCurrentMonsterName() {
		if(level == 1) {
			if(wave == 1) return "burny";
			if(wave == 2) return "gunner";
			if(wave == 3) return "duel";
			if(wave == 4) return "minimaxwell";
		} else if(level == 2) {
			if(wave == 1) return "burny";
			if(wave == 2) return "gunner";
			if(wave == 3) return "tilith";
			if(wave == 4) return "minimaxwell";
		} else if(level == 3) {
			if(wave == 1) return "kingburny";
			if(wave == 2) return "sniper";
			if(wave == 3) return "tilith";
			if(wave == 4) return "duel2";
			if(wave == 5) return "minimaxwell";
		} else if(level == 4) {
			if(wave == 1) return "megaburny";
			if(wave == 2) return "sniper";
			if(wave == 3) return "tilith";
			if(wave == 4) return "duel2";
			if(wave == 5) return "gunner2";
			if(wave == 6) return "minimaxwell";
			if(wave == 7) return "maxwell";
		}
		return "burny";
	}

	public void increaseFury() {
		if (++furyCounting > MAXIMUM_FURY) {
			furyCounting = MAXIMUM_FURY;
		}
	}
	
	public void increaseHpMe(int potion, int type) {
		
		int sp = 15 * InfoManager.LEVEL_SMALLPOTION[InfoManager.SELECTED_SLOT];
		int lp = 30 * InfoManager.LEVEL_LARGEPOTION[InfoManager.SELECTED_SLOT];
		
		hpMe += potion * (type == 1 ? sp : lp);
		hpMe = Math.min(hpMe, me.getHp());
	}
	
	public void increaseWave() {
		wave += 1;
	}
	
	public int getFuryPercentage() {
		return furyCounting * 100 / MAXIMUM_FURY;
	}

	public int getFury() {
		return furyCounting;
	}
}
