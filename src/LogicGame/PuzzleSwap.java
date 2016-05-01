
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
	public int pressDirection =0;

	private int inBlueX;
	private int inBlueY;
	private int blueSize = 80;

	private AnimationManager[] img;

	public PuzzleSwap(AnimationManager[] img) {

		int d = tableSize * tableSize;

		int[] rand = new int[d + 1];

		for (int i = 0; i < d; i++) {
			if (i < 10)
				rand[i] = sw;
			else if (i < 16)
				rand[i] = sh;
			else if (i < 21)
				rand[i] = sp;
			else
				rand[i] = lp;
		}
		for (int i = 0; i < 100; i++) {
			int a = Randomul.rand(0, d - 1);
			int b = Randomul.rand(0, d - 1);
			int tmp = rand[a];
			rand[a] = rand[b];
			rand[b] = tmp;
		}

		for (int i = 0; i < tableSize; i++) {
			for (int j = 0; j < tableSize; j++) {
				table[i][j] = rand[i * tableSize + j];
			}
		}

		this.img = img;
	}

	public int[] calculateCombineStat() {
		int[] r = new int[5];
		boolean[][] mark = new boolean[tableSize][tableSize];
		int[][] dir = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
		int size = tableSize;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {

				if (table[i][j] == lp || table[i][j] == sp) {
					for (int k = 0; k < dir.length; k++) {
						int x = j + dir[k][0];
						int y = i + dir[k][1];

						if (x < 0 || x >= size || y < 0 || y >= size)
							continue;
						if (table[i][j] == table[y][x]) {
							r[table[i][j]]++;
							break;
						}
					}
				} else {
					int count = 1;
					for (int k = 0; k < dir.length; k++) {
						int x = j;
						int y = i;
						mark[i][j] = true;
						while (true) {
							x += dir[k][0];
							y += dir[k][1];

							if (x < 0 || x >= size || y < 0 || y >= size)
								break;
							if (table[i][j] != table[y][x])
								break;
							if (mark[y][x])
								break;
							mark[y][x] = true;
							count++;
						}
					}
					if (count > 1) {
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
		int imgSize;

		g.setColor(new Color(153, 255, 255));
		g.fillRect(xPos[inBlueY][inBlueX], yPos[inBlueY][inBlueX], blueSize, blueSize);

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {

				imgSize = size / w;
				xPos[i][j] = x + size / w * j;
				yPos[i][j] = y + size / h * i;

				if (data[i][j] == 0)
					continue;
				g.drawImage(img[data[i][j] - 1].getCurrentBufferedImage(), x + size / w * j, y + size / h * i,
						size / w, size / h, null);
			}
		}
	}

	public void update() {
		if (InputUtility.getKeyPressed(KeyEvent.VK_UP)) {
			if (pressDirection != 1) {
				if (inBlueY > 0) {
					inBlueY--;
				}
			}
			pressDirection = 1;
		} else if (InputUtility.getKeyPressed(KeyEvent.VK_DOWN)) {
			if (pressDirection != 2) {
				if (inBlueY < tableSize - 1) {
					inBlueY++;
				}
			}
			pressDirection = 2;
		} else if (InputUtility.getKeyPressed(KeyEvent.VK_LEFT)) {
			if (pressDirection != 3) {
				if (inBlueX > 0) {
					inBlueX--;
				}
			}
			pressDirection = 3;
		} else if (InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)) {
			if (pressDirection != 4) {
				if (inBlueX < tableSize - 1) {
					inBlueX++;
				}
			}
			pressDirection = 4;
		} else if (InputUtility.getKeyPressed(KeyEvent.VK_Z)) {
			
			
	} else pressDirection = 0;

	}}
