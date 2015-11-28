package LogicGame;

import main.Main;
import Data.Me;
import Data.Monster;
import frame.PlayFrame;

public class PlayLogic {

	private static final int GAME_TIME_DURATION = 25;

	private int timeCounter;
	private int timeStamp = 0;
	private int previousTimeStampSecond = 0;
	private PlayFrame game;

	private Monster monster;
	private Monster me;
	private int hpMe, hpMonster;
	private int furyCounting;

	public PlayLogic(PlayFrame game) {
		timeStamp = 0;
		timeCounter = GAME_TIME_DURATION;
		this.game = game;
		furyCounting = 0;
		
		me = new Me(); 
		hpMe = me.getHp();
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
		this.hpMonster = monster.getHp();
	}
	
	public int getHpMe() {
		return hpMe;
	}
	
	public int getHpMePercentage() {
		return hpMe * 100 / me.getHp();
	}
	
	public int getHpMonster() {
		return hpMonster;
	}
	
	public int calculateDecreaseHpMe(int sword, int shield) {
		return Math.max(1 , monster.getAt() + (10 * (sword / 2)) - (me.getDef() + 8 * shield));
	}
	
	public void decreaseHpMe(int sword, int shield) {
		hpMe -= calculateDecreaseHpMe(sword, shield);
	}
	
	public int getHpMonsterPercentage() {
		return hpMonster * 100 / monster.getHp();
	}
	
	public int calculateDecreaseHpMonster(int sword, int shield) {
		return Math.max(1, me.getAt() + 10 * sword - (monster.getDef() + 15 * (shield / 2)));
	}
	
	public void decreaseHpMonster(int sword, int shield) {
		hpMonster -= calculateDecreaseHpMonster(sword, shield);
	}

	public void increaseFury() {
		if (++furyCounting > 7) {
			furyCounting = 7;
		}
	}
	
	public void increaseHpMe(int potion, int type) {
		hpMe += potion * (type == 1 ? 10 : 15);
		hpMe = Math.max(hpMe, me.getHp());
	}
	
	public int getFuryPercentage() {
		return furyCounting * 100 / 7;
	}

	public int getFury() {
		return furyCounting;
	}
}
