
package LogicGame;

import input.InputUtility;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import render.AnimationManager;
import Random.Randomul;

import com.sun.glass.events.KeyEvent;

public class PuzzleSwap implements MiniGame {
	
	private final int tableSize = 5;
	
	private int[][] table = new int[tableSize][tableSize];
	private int[][] xPos = new int[tableSize][tableSize];
	private int[][] yPos = new int[tableSize][tableSize];
	
	public final static int sp = 1;
	public final static int lp = 2;
	public final static int sw = 3;
	public final static int sh = 4;
	
	private int inBlueX;
	private int inBlueY;
	private int temp;
	private boolean isChoose = false;
	private int blueSize = 80;
	
	
	private AnimationManager[] img;
	//private int emptyX, emptyY;
	//private int slideX, slideY;
	private int imgSize;
	private boolean isSliding = false;
	
	
	private BoxSlider slider;
	
	public PuzzleSwap(AnimationManager[] img){
		
		int d = tableSize*tableSize;
		
		int[] rand = new int[d+1];
		
		for(int i=0; i<d; i++) {
			if(i < 10) rand[i] = sw;
			else if(i < 16) rand[i] = sh;
			else if(i < 21) rand[i] = sp;
			else rand[i] = lp; 
		}
		for(int i=0; i<100; i++) {
			int a = Randomul.rand(0, d-1);
			int b = Randomul.rand(0, d-1);
			int tmp = rand[a];
			rand[a] = rand[b];
			rand[b] = tmp;
		}
		
		for (int i=0; i<tableSize;i++){
			for(int j=0; j<tableSize;j++){
				table[i][j] = rand[i*tableSize+j];
			}
		}
		
		this.img = img;
	}
	
	public int[] calculateCombineStat() {
		int[] r = new int[5];
		boolean[][] mark = new boolean[tableSize][tableSize];
		int[][] dir = {{0,1},{1,0},{-1,0},{0,-1}};
		int size = tableSize;
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				
				if(table[i][j] == lp || table[i][j] == sp) {
					for(int k=0; k<dir.length; k++) {
						int x = j + dir[k][0];
						int y = i + dir[k][1];
						
						if(x < 0 || x >= size || y < 0 || y >= size) continue;
						if(table[i][j] == table[y][x]) {
							r[table[i][j]]++;
							break;
						}
					}
				} else {
					int count = 1;
					for(int k=0; k<dir.length; k++) {
						int x = j;
						int y = i;
						mark[i][j] = true;
						while(true) {
							x += dir[k][0];
							y += dir[k][1];
							
							if(x < 0 || x >= size || y < 0 || y >= size) break;
							if(table[i][j] != table[y][x]) break;
							if(mark[y][x]) break;
							mark[y][x] = true;
							count++;
						}
					}
					if(count > 1) {
						r[table[i][j]] += count * count;
					}
				}
			}
		}
		
		return r;
	}
	
	public int[][] getTable() {
		return table;
	}
	
	public void draw(Graphics2D g, int x, int y, int size) {		
		
		int[][] data = table;
		int h = data.length;
		int w = data[0].length;
		
		if(isChoose){
			g.setColor(new Color(153, 255, 255));
			g.fillRect(xPos[inBlueY][inBlueX], yPos[inBlueY][inBlueX], blueSize, blueSize);
		}
		
		
		
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
	}
	
	public void update() {
		if(InputUtility.isMouseLeftClicked()){
			if(!isChoose){
				//method for detect which box that mouse click to = mA()
			}
			
			else if(isChoose){
				//if(mA().get() == inBlueX && mA().getY() == inBlueY)
				//cancel();
				
				/*else */
				swap(inBlueX, inBlueY , 0 , 0);
			}
			
		}
	}
	
	public void swap(int x1, int y1, int x2, int y2){
		//in progress
		
		
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
	
	public void cancel() {
		isChoose = false;
		inBlueX = -1;
		inBlueY = -1;
		temp = 0;
	}

}
