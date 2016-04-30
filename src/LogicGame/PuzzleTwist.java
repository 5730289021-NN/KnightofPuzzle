package LogicGame;

import java.awt.Color;
import java.awt.Graphics2D;

import com.sun.glass.events.KeyEvent;

import Random.Randomul;
import input.InputUtility;
import render.AnimationManager;

//test

public class PuzzleTwist implements MiniGame {
	private final int tableSize = 5;

	private int[][] table = new int[tableSize][tableSize];
	private int[][] xPos = new int[tableSize][tableSize];
	private int[][] yPos = new int[tableSize][tableSize];
	public final static int sp = 1;
	public final static int lp = 2;
	public final static int sw = 3;
	public final static int sh = 4;
	public int pressDirection = 0;

	private AnimationManager[] img;
	// private int emptyX, emptyY;
	// private int slideX, slideY;
	private int imgSize;
	private boolean isSliding = false;

	private BoxSlider slider1, slider2, slider3, slider4;

	private int yBoxX, yBoxY;
	private int yBoxSize = 160;
	private int temp;

	public PuzzleTwist(AnimationManager[] img) {

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

		g.setColor(Color.YELLOW);
		g.fillRect(xPos[yBoxY][yBoxX], yPos[yBoxY][yBoxX], yBoxSize, yBoxSize);

		if (!isSliding) {

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
		} else {
			if (slider1 != null && slider2 != null && slider3 != null && slider4 != null) {
				if (slider1.isFinish() && slider2.isFinish() && slider3.isFinish() && slider4.isFinish()) {
					slider1 = null;
					slider2 = null;
					slider3 = null;
					slider4 = null;

					temp = table[yBoxY][yBoxX + 1];
					table[yBoxY][yBoxX + 1] = table[yBoxY][yBoxX];
					table[yBoxY][yBoxX] = table[yBoxY + 1][yBoxX];
					table[yBoxY + 1][yBoxX] = table[yBoxY + 1][yBoxX + 1];
					table[yBoxY + 1][yBoxX + 1] = temp;
					// table[emptyY][emptyX] = table[slideY][slideX];
					// table[slideY][slideX] = 0;
					// emptyX = slideX;
					// emptyY = slideY;
					// slideX = slideY = -1;
					isSliding = false;
				} else {
					slider1.update(g);
					slider2.update(g);
					slider3.update(g);
					slider4.update(g);
				}

				for (int i = 0; i < h; i++) {
					for (int j = 0; j < w; j++) {
						g.drawImage(img[data[i][j] - 1].getCurrentBufferedImage(), x + size / w * j, y + size / h * i,
								size / w, size / h, null);
						imgSize = size / w;
						xPos[i][j] = x + size / w * j;
						yPos[i][j] = y + size / h * i;
					}
				}
			}
		}
	}

	public void update() {
		if (InputUtility.getKeyPressed(KeyEvent.VK_UP)) {
			if (pressDirection != 1) {
				if (yBoxY > 0) {
					yBoxY--;
				}
			}
			pressDirection = 1;
		} else if (InputUtility.getKeyPressed(KeyEvent.VK_DOWN)) {
			if (pressDirection != 2) {
				if (yBoxY < tableSize - 2) {
					yBoxY++;
				}
			}
			pressDirection = 2;
		} else if (InputUtility.getKeyPressed(KeyEvent.VK_LEFT)) {
			if (pressDirection != 3) {
				if (yBoxX > 0) {
					yBoxX--;
				}
			}
			pressDirection = 3;
		} else if (InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)) {
			if (pressDirection != 4) {
				if (yBoxX < tableSize - 2) {
					yBoxX++;
				}
			}
			pressDirection = 4;
		} else if (InputUtility.getKeyPressed(KeyEvent.VK_Z)) {
			twist(yBoxX, yBoxY);
		} else
			pressDirection = 0;
	}

	public void twist(int x, int y) {
		if (isSliding)
			return;

		int topX = x;
		int topY = y;
		isSliding = true;

		int posSX1 = xPos[topY][topX];
		int posSY1 = yPos[topY][topX];
		int posEX1 = xPos[topY][topX + 1];
		int posEY1 = yPos[topY][topX + 1];

		int posSX2 = xPos[topY][topX + 1];
		int posSY2 = yPos[topY][topX + 1];
		int posEX2 = xPos[topY + 1][topX + 1];
		int posEY2 = yPos[topY + 1][topX + 1];

		int posSX3 = xPos[topY + 1][topX + 1];
		int posSY3 = yPos[topY + 1][topX + 1];
		int posEX3 = xPos[topY + 1][topX];
		int posEY3 = yPos[topY + 1][topX];

		int posSX4 = xPos[topY + 1][topX];
		int posSY4 = yPos[topY + 1][topX];
		int posEX4 = xPos[topY][topX];
		int posEY4 = yPos[topY][topX];

		slider1 = new BoxSlider(posSX1, posSY1, posEX1, posEY1, imgSize, img[table[topY][topX] - 1]);
		slider2 = new BoxSlider(posSX2, posSY2, posEX2, posEY2, imgSize, img[table[topY][topX + 1] - 1]);
		slider3 = new BoxSlider(posSX3, posSY3, posEX3, posEY3, imgSize, img[table[topY + 1][topX + 1] - 1]);
		slider4 = new BoxSlider(posSX4, posSY4, posEX4, posEY4, imgSize, img[table[topY + 1][topX] - 1]);

	}
}
