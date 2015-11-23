
package LogicGame;

import java.awt.Color;
import java.awt.Graphics2D;

import Random.Randomul;
import render.AnimationManager;

public class Puzzle {
	private int[][] table = new int[5][5];
	private int[][] xPos = new int[5][5];
	private int[][] yPos = new int[5][5];
	private int timecount = 10;
	private final static int sp = 1;
	private final static int lp = 2;
	private final static int sw = 3;
	private final static int sh = 4;
	
	private AnimationManager[] img;
	private int emptyX, emptyY;
	private int slideX, slideY;
	private boolean isSliding = false;
	
	private BoxSlider slider;
	
	public Puzzle(AnimationManager[] img){
		for (int i=0; i<5;i++){
			for(int j=0; j<5;j++){
				table[i][j] = Randomul.rand(1, 4);
			}
		}
		emptyX = 4;
		emptyY = 4;
		table[4][4] = 0;
		this.img = img;
	}
	
	public int[][] getTable() {
		return table;
	}
	
	public void draw(Graphics2D g, int x, int y, int size) {
		g.setColor(new Color(20, 20, 20));
		g.fillRect(x, y, size, size);
		
		if(isSliding) {
			
			int[][] data = table;
			int h = data.length;
			int w = data[0].length;
			
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if(data[i][j] == 0) continue;
					g.drawImage(
						img[data[i][j] - 1].getCurrentBufferedImage(), 
						x + size / w * j, 
						y + size / h * i, 
						size / w, 
						size / h, 
						null
					);
					xPos[i][j] = x + size / w * j;
					yPos[i][j] = y + size / h * i;
				}
			}
		} else {
			if(slider != null) {
				if(slider.isFinish()) {
					slider = null;
					table[emptyX][emptyY] = table[slideX][slideY];
					emptyX = slideX;
					emptyY = slideY;
					isSliding = false;
				} else {
					//TODO code update animation of box
				}
			}
		}
	}
	
	public void slide(int x, int y){
		if(isSliding) return ;
		
		slideX = x;
		slideY = y;
		isSliding = true;
		slider = new BoxSlider(slideX, slideY, emptyX, emptyY, img[table[slideX][slideY] - 1]);
	}

}
