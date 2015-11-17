package LogicGame;

import java.util.Random;

import Random.Randomul;

public class Puzzle {
	private int[][] a = new int[5][5];
	
	private final int sp = 1;
	private final int lp = 2;
	private final int sw = 3;
	private final int sh = 4;
	
	public Puzzle(){
		for (int i=0; i<5;i++){
			for(int j=0; j<5;j++){
				a[i][j] = Randomul.rand(1, 4);
			}
		}
	}

}
