package LogicGame;

import main.Main;
import frame.PlayFrame;

public class PlayLogic {
	
	private static final int GAME_TIME_DURATION = 5;
	
	private int timeCounter;
	private int timeStamp = 0;
	private int previousTimeStampSecond = 0;
	private PlayFrame game;
	
	public PlayLogic(PlayFrame game) {
		timeStamp = 0;
		timeCounter = GAME_TIME_DURATION;
		this.game = game;
	}
	
	public void updateTimeStamp() {
		timeStamp += Main.SleepTime;
		if(timeStamp / 1000 != previousTimeStampSecond) {
			previousTimeStampSecond = timeStamp / 1000;
			countingTime();
		}
	}
	
	public void countingTime() {
		timeCounter--;
		if(timeCounter <= 0) {
			timeCounter = GAME_TIME_DURATION;
			game.miniGameComplete();
		}
	}
	
	public int getTimeCounter() {
		return timeCounter;
	}
}
