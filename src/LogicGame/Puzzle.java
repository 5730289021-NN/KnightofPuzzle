
package LogicGame;

import java.util.Random;

import Random.Randomul;

public class Puzzle {
	private int[][] a = new int[5][5];
	
	private final static int sp = 1;
	private final static int lp = 2;
	private final static int sw = 3;
	private final static int sh = 4;
	
	public Puzzle(){
		for (int i=0; i<5;i++){
			for(int j=0; j<5;j++){
				a[i][j] = Randomul.rand(1, 4);
			}
		}
	}
	
	//test merge
	public int[][] getTable() {
		return a;
	}

}
