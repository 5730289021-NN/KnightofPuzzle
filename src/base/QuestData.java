package base;

public class QuestData {
	public static boolean[] isFinish = new boolean[5];
	public static int[] conditionEachQuest = new int[5];
	
	public QuestData(){
		conditionEachQuest[0] = 50;
		conditionEachQuest[1] = 40;
		conditionEachQuest[2] = 6;
		conditionEachQuest[3] = 100;
		conditionEachQuest[4] = 20;
		for(int i=0; i<5;++i){
			isFinish[i] = false; 
		}
		
	}

}
