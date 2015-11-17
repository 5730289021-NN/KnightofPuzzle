
package LogicGame;

import Random.Randomul;

public class Puzzle {
	private static Puzzle instance;
	private int[][] table = new int[5][5];
	
	private final static int sp = 1;
	private final static int lp = 2;
	private final static int sw = 3;
	private final static int sh = 4;
	
	public static Puzzle getInstance() {
		if(instance == null) instance = new Puzzle();
		return instance;
	}
	
	private Puzzle(){
		for (int i=0; i<5;i++){
			for(int j=0; j<5;j++){
				table[i][j] = Randomul.rand(1, 4);
			}
		}
		table[4][4] = 0;
	}
	
	public int[][] getTable() {
		return table;
	}

}
