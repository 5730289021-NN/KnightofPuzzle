package base;

import frame.PlayFrame;

public class QuestData {
	public static boolean[] isFinish = new boolean[6];
	
	public QuestData(){
		for(int i=0; i<6;++i)
			isFinish[i] = false; 
		
	}
	public void update(){
		if(PlayFrame.counterMonsterDie == 50){
			isFinish[0] = true;
		} else if(PlayFrame.counterMonsterDie == 300){
			isFinish[1] = true;
		} else if(PlayFrame.counterMyLose == 20){
			isFinish[2] = true;
		} else if(PlayFrame.counterGameFinish == 30){
			isFinish[3] = true;
		} else if(PlayFrame.counterUseFury == 40){
			isFinish[4] = true;
		} else if(ShopScreen.counterBuyOrUpgrade == 8){
			isFinish[5] = true;
		}
			
	}

}
