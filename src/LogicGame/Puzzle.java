
package LogicGame;

import java.awt.Color;
import java.awt.Graphics2D;

import com.sun.glass.events.KeyEvent;

import Random.Randomul;
import input.InputUtility;
import render.AnimationManager;

public class Puzzle {
	
	private final int tableSize = 5;
	
	private int[][] table = new int[tableSize][tableSize];
	private int[][] xPos = new int[tableSize][tableSize];
	private int[][] yPos = new int[tableSize][tableSize];
	private int timecount = 10;
	private final static int sp = 1;
	private final static int lp = 2;
	private final static int sw = 3;
	private final static int sh = 4;
	
	private AnimationManager[] img;
	private int emptyX, emptyY;
	private int slideX, slideY;
	private int imgSize;
	private boolean isSliding = false;
	
	private BoxSlider slider;
	
	public Puzzle(AnimationManager[] img){
		for (int i=0; i<5;i++){
			for(int j=0; j<5;j++){
				table[i][j] = Randomul.rand(1, 4);
			}
		}
		emptyX = tableSize - 1;
		emptyY = tableSize - 1;
		table[emptyY][emptyX] = 0;
		this.img = img;
	}
	
	public int[][] getTable() {
		return table;
	}
	
	public void draw(Graphics2D g, int x, int y, int size) {		
		
		int[][] data = table;
		int h = data.length;
		int w = data[0].length;
		
		if(!isSliding) {
			
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					
					imgSize = size / w;
					xPos[i][j] = x + size / w * j;
					yPos[i][j] = y + size / h * i;
					
					if(data[i][j] == 0) continue;
					g.drawImage(
						img[data[i][j] - 1].getCurrentBufferedImage(), 
						x + size / w * j, 
						y + size / h * i, 
						size / w, 
						size / h, 
						null
					);
				}
			}
		} else {
			if(slider != null) {
				if(slider.isFinish()) {
					slider = null;
					table[emptyY][emptyX] = table[slideY][slideX];
					table[slideY][slideX] = 0;
					emptyX = slideX;
					emptyY = slideY;
					slideX = slideY = -1;
					isSliding = false;
				} else {
					slider.update(g);
				}
					
				for(int i=0; i<h; i++) {
					for(int j=0; j<w; j++) {
						if(i == emptyY && j == emptyX) continue;
						if(i == slideY && j == slideX) continue;
						if(data[i][j] == 0) continue;
						g.drawImage(
							img[data[i][j] - 1].getCurrentBufferedImage(), 
							x + size / w * j, 
							y + size / h * i, 
							size / w, 
							size / h, 
							null
						);
						imgSize = size / w;
						xPos[i][j] = x + size / w * j;
						yPos[i][j] = y + size / h * i;
					}
				}
			}
		}
		
		update();
	}
	
	private void update() {
		if(InputUtility.getKeyPressed(KeyEvent.VK_UP)) {
			if(emptyY < tableSize - 1) {
				slide(emptyX, emptyY + 1);
			}
		} else if(InputUtility.getKeyPressed(KeyEvent.VK_DOWN)) {
			if(emptyY > 0) {
				slide(emptyX, emptyY - 1);
			}
		} else if(InputUtility.getKeyPressed(KeyEvent.VK_LEFT)) {
			if(emptyX < tableSize - 1) {
				slide(emptyX + 1, emptyY);
			}
		} else if(InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)) {
			if(emptyX > 0) {
				slide(emptyX - 1, emptyY);
			}
		}
	}
	
	public void slide(int x, int y){
		if(isSliding) return ;
		
		slideX = x;
		slideY = y;
		isSliding = true;
		
		int posSX = xPos[slideY][slideX];
		int posSY = yPos[slideY][slideX];
		int posEX = xPos[emptyY][emptyX];
		int posEY = yPos[emptyY][emptyX];
		
		slider = new BoxSlider(posSX, posSY, posEX, posEY, imgSize, img[table[slideY][slideX] - 1]);
	}

}
