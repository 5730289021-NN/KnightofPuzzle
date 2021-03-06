package frame;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import com.sun.glass.events.KeyEvent;

import Data.InfoManager;
import LogicGame.MiniGame;
import LogicGame.PlayLogic;
import LogicGame.Puzzle;
import LogicGame.PuzzleSwap;
import LogicGame.PuzzleTwist;
import base.GameScreen;
import base.ModeScreen;
import base.SelectLevelScreen;
import input.InputUtility;
import main.Main;
import render.AnimationManager;
import render.ImageData;
import render.RenderHelper;
import res.Resource;


public class PlayFrame extends JComponent {

	private static final int START_STATE = 0;
	private static final int PLAY_STATE = 1;
	private static final int MINI_FINISH = 2;
	private static final int PLAYER_TURN = 3;
	private static final int MONSTER_TURN = 4;
	private static final int WAVE_COMPLETE = 5;
	private static final int GAME_FINISH = 6;
	private static final int ME_DIE = 7;
	
	
	private static AudioClip soundEffect;
	private AnimationManager[] puzzleItem = new AnimationManager[4];
	private AnimationManager bg, me, enemy, attackme, attackenemy;
	private int state;
	private boolean isPause = false;
	
	private Image buffer;
	
	private int currentMiniPosY = 0;
	private int seperateHeight = 368;
	private int puzzleSize = 400;
	public static int counterMonsterDie =0;
	public static int counterMyLose = 0;
	public static int counterGameFinish = 0;
	public static int counterUseFury = 0;
	
	
	private MiniGame puzzle;
	private PlayLogic logic;
	private JButton menuButton;
	
	private int currentTime, finishTime;
	
	private int[] combineStat;
	
	public PlayFrame() {
		
		// Current level
		int level = SelectLevelScreen.meLocation + 1;
		logic = new PlayLogic(this, level);
		
		state = START_STATE;
		buffer = new BufferedImage(GameScreen.WIDTH, GameScreen.HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		puzzleItem[0] = Resource.get("smallpotion");
		puzzleItem[1] = Resource.get("largepotion");
		preGame();
		
		bg = Resource.get("underwaterbg");
		switch(level) {
		case 1 : bg = Resource.get("underwaterbg"); break;
		case 2 : bg = Resource.get("earthbg"); break;
		case 3 : bg = Resource.get("deemobg"); break;
		case 4 : bg = Resource.get("heavenbg"); break;
		}
		
		me = new AnimationManager(Resource.get("me").getAllImage());
		me.loop();
		attackme = new AnimationManager(Resource.get("attackme").getAllImage());
		
		// Change these 3 lines for change monster
		String monsterName = logic.getCurrentMonsterName();
		attackenemy = Resource.get("attack" + monsterName);
		enemy = Resource.get(monsterName);
		logic.setMonster(monsterName);
		enemy.loop();
		
		puzzle = createMiniPuzzle();
		currentMiniPosY = 0;
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		menuButton = new JButton("Menu");
		menuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showOptionDialog();
			}
		});
		
		add(menuButton);	
	}
	
	private void showOptionDialog() {
		//TODO pause?
		String[] options = {"Resume","Restart","Quit"};
		isPause = true;
		int choice = JOptionPane.showOptionDialog(this, "Choose your choice", "Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		//System.out.println(options[choice]);
		switch(choice)
		{
			case 0:
			{	isPause = false;
				return;
			}
			case 1:
			{
				if(JOptionPane.showConfirmDialog(this,"Are you sure?","Restart",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == 0)
				{
					restart();
				}else
				{
					return;
				}
				break;
			}
			case 2:
			{
				if(JOptionPane.showConfirmDialog(this,"Are you sure?","Quit",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE) == 0)
				{
					Main.changeGameScreen(Main.SELECTSCREEN);
				}else
				{
					return;
				}
				break;
			}
		}
		isPause = false;
		
	}
	
	public void miniGameComplete() {
		currentMiniPosY = seperateHeight;
		state = MINI_FINISH;
		attackme.play();
		attackenemy.play();
		combineStat = puzzle.calculateCombineStat();
		System.out.println(combineStat[1]+" "+combineStat[2]+" "+combineStat[3]+" "+combineStat[4]);
		System.out.println(logic.calculateDecreaseHpMonster(combineStat[Puzzle.sw], combineStat[Puzzle.sh]));
		System.out.println(logic.calculateDecreaseHpMe(combineStat[Puzzle.sw], combineStat[Puzzle.sh]));
		soundSword();
	}

	private void restart() {
		Main.changeGameScreen(Main.GAMESCREEN);
		
	}

	public synchronized void update() {
		
		if(state == PLAY_STATE) {
			puzzle.update();
		} else if(state == MINI_FINISH || state == PLAYER_TURN) {
			attackme.update();
		} else if(state == MONSTER_TURN) {
			attackenemy.update();
		}
		
		if(InputUtility.getKeyPressed(KeyEvent.VK_SPACE)) {
			logic.setUseFury();
		}
		
		me.update();
		enemy.update();
	}
	
	private MiniGame createMiniPuzzle() {
		String a = ModeScreen.s;
		if(a == null) a = "Slide";
		
		switch(a){
			case("Slide") :
			{
				return new Puzzle(puzzleItem);
			}
			case("Twist") :
			{
				return new PuzzleTwist(puzzleItem);
			}
			case("Swap") :
			{
				return new PuzzleSwap(puzzleItem);
			}
			default :
			{
				return new Puzzle(puzzleItem);
			}
		}
	}
	
	@Override
	protected synchronized void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics screengc = g;
		
		if(isPause) {
			screengc.drawImage(buffer, 0, 0, null);
			return ;
		}
		
		g = buffer.getGraphics();
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
		
		g.setColor(new Color(20, 20, 20));
		g.fillRect((GameScreen.WIDTH - puzzleSize) / 2, seperateHeight, puzzleSize, puzzleSize);
		
		if(state == START_STATE) {
			if(currentMiniPosY >= seperateHeight) {
				currentMiniPosY = seperateHeight;
				state = PLAY_STATE;
			}
			drawPuzzle(g2, currentMiniPosY, puzzleSize);
			currentMiniPosY += 10;
		
		} else if(state == PLAY_STATE) {
			logic.updateTimeStamp();
			drawPuzzle(g2, seperateHeight, puzzleSize);
		
		} else if(state == MINI_FINISH) {
			currentMiniPosY = seperateHeight;
			state = PLAYER_TURN;
			drawPuzzle(g2, currentMiniPosY, puzzleSize);
			
		} else if(state == PLAYER_TURN) {
			drawPuzzle(g2, currentMiniPosY, puzzleSize);
			if(attackme.isFinish()) {
				state = MONSTER_TURN;
				logic.decreaseHpMonster(
					combineStat[Puzzle.sw], 
					combineStat[Puzzle.sh]
				);
				
				if(logic.isMonsterDie()) {
					state = WAVE_COMPLETE;
					logic.increaseGold();
					counterMonsterDie++;
					currentMiniPosY = seperateHeight;
					
					soundDeath();
					counterMonsterDie++;
					
					currentTime = 0;
					finishTime = 2000;
				}
				System.out.println("Monster hp :"+logic.getHpMonster());
			}
			
		} else if(state == MONSTER_TURN) {
			
			currentMiniPosY += 10;
			drawPuzzle(g2, currentMiniPosY, puzzleSize);
			
			if(currentMiniPosY >= GameScreen.HEIGHT) {
				currentMiniPosY = GameScreen.HEIGHT;
				puzzle = createMiniPuzzle();
			}
			if(attackenemy.isFinish() && currentMiniPosY >= GameScreen.HEIGHT) {
				state = START_STATE;
				currentMiniPosY = 0;
				
				logic.increaseFury();
				logic.decreaseHpMe(combineStat[Puzzle.sw], combineStat[Puzzle.sh]);
				
				if(logic.isMeDie()) {
					state = ME_DIE;
					currentTime = 0;
					finishTime = 2000;
				} else {
					logic.increaseHpMe(combineStat[Puzzle.lp], 1);
					logic.increaseHpMe(combineStat[Puzzle.sp], 2);
					System.out.println("Me hp :"+logic.getHpMe());
				}
			}
		} else if(state == WAVE_COMPLETE) {
			
			drawPuzzle(g2, currentMiniPosY, puzzleSize);
			currentMiniPosY += 10;
			if(currentMiniPosY >= GameScreen.HEIGHT) {
				currentMiniPosY = GameScreen.HEIGHT;
			}
			
			currentTime += Main.SleepTime;
			if(currentMiniPosY >= GameScreen.HEIGHT && currentTime > finishTime) {
				state = START_STATE;
				currentMiniPosY = 0;
				
				puzzle = createMiniPuzzle();
				
				logic.increaseWave();
				
				if(logic.isWaveComplete()) {
					state = GAME_FINISH;
					currentTime = 0;
					finishTime = 4000;
				} else {
					String monsterName = logic.getCurrentMonsterName();
					
					enemy = Resource.get(monsterName);
					attackenemy = Resource.get("attack" + monsterName);
					enemy.loop();
					logic.setMonster(monsterName);
				}
			}
		} else if(state == GAME_FINISH) {
			currentTime += Main.SleepTime;
			if(currentTime >= finishTime) {
				logic.updateGoldToInfo();
				InfoManager.MAX_LEVEL_COMPLETE[InfoManager.SELECTED_SLOT] = Math.min(
					4,
					InfoManager.MAX_LEVEL_COMPLETE[InfoManager.SELECTED_SLOT] + 1
				);
				counterGameFinish++;
				Main.changeGameScreen(Main.SELECTSCREEN);
			}
		} else if(state == ME_DIE) {
			currentTime += Main.SleepTime;
			if(currentTime >= finishTime) {
				
				counterMyLose++;
				Main.changeGameScreen(Main.SELECTSCREEN);
			}
		}
		
		drawStage(g2, seperateHeight);
		
		g.setColor(Color.WHITE);
		g.fillRect((GameScreen.WIDTH + puzzleSize) / 2, seperateHeight, GameScreen.WIDTH, GameScreen.HEIGHT);
		
		drawTime(g2, logic.getTimeCounter());
		drawStatus(g2);
		
		if(state == GAME_FINISH) {
			g.drawImage(Resource.get("missioncomplete").getCurrentBufferedImage(), 0, 0, GameScreen.WIDTH, GameScreen.HEIGHT, null);
		}
		if(state == ME_DIE) {
			g.drawImage(Resource.get("gameover").getCurrentBufferedImage(), 0, 0, GameScreen.WIDTH, GameScreen.HEIGHT, null);
		}
		
		screengc.drawImage(buffer, 0, 0, null);
		
		update();
	}
	
	private void drawLineStatus(Graphics2D g, Color color, String name, int x, int y, int percent) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Tahoma", Font.PLAIN, 20));
		g.drawString(name, x, y + 20);
		
		g.setColor(color);
		g.fillRect(x + 50, y, 220 * percent / 100, 20);
	}
	
	public void drawStatus(Graphics2D g){
		drawLineStatus(g, Color.RED, "HP", 15, seperateHeight + 10, logic.getHpMePercentage());
		
		if(logic.isUseFury()) {
			drawLineStatus(g, Color.BLUE, "Fury", 15, seperateHeight + 70, logic.getFuryPercentage());
			counterUseFury++;
		} else {
			drawLineStatus(g, Color.GREEN, "Fury", 15, seperateHeight + 70, logic.getFuryPercentage());
		}
		drawLineStatus(g, Color.RED, "HP", 720, seperateHeight + 10, logic.getHpMonsterPercentage());
		
		g.setColor(Color.BLACK);
		g.drawString(logic.getHpMe() + "/" + logic.getFullHpMe(), 65, seperateHeight + 55);
		g.drawString(logic.getHpMonster() + "/" + logic.getFullHpMonster(), 770, seperateHeight + 55);
		
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Tahoma", Font.PLAIN, 20));
		g.drawString("Gold : " + logic.getGold(), GameScreen.WIDTH - 110, 25 );
	}
	
	public void drawTime(Graphics2D g, int time) {
		String txt = time + "";
		g.setColor(new Color(255, 191, 0));
		g.setFont(new Font("Tahoma", Font.PLAIN, 50));
		int width = g.getFontMetrics().stringWidth(txt);
		g.drawString(txt, (GameScreen.WIDTH - width)/2, 55);
	}
	
	public void drawStage(Graphics2D g, int height) {
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, GameScreen.WIDTH, height);
		
		g.drawImage(
			bg.getCurrentBufferedImage(),
			0, 0,
			GameScreen.WIDTH, height,
			null
		);
		
		
		if(state == MINI_FINISH || state == PLAYER_TURN) {
			RenderHelper.draw(
				g, 
				attackme.getCurrentBufferedImage(), 
				150, 220, 
				attackme.getWidth()*2, attackme.getHeight()*2, 
				RenderHelper.LEFT | RenderHelper.BOTTOM
			);
		} else {
			RenderHelper.draw(
				g, 
				me.getCurrentBufferedImage(), 
				150, 220, 
				me.getWidth()*2, me.getHeight()*2, 
				RenderHelper.LEFT | RenderHelper.BOTTOM
			);
		}
		
		if(state != WAVE_COMPLETE) {
			if(state == MONSTER_TURN) {
				ImageData bf = attackenemy.getCurrentImageData();
				RenderHelper.draw(
					g,
					bf.getImg(),
					800 - bf.getOffsetX(), 220 - bf.getOffsetY(),
					bf.getWidth()*2, bf.getHeight()*2,
					RenderHelper.RIGHT | RenderHelper.BOTTOM
				);
			} else {		
				BufferedImage bf = enemy.getCurrentBufferedImage();
				RenderHelper.draw(
					g,
					bf,
					800, 220,
					bf.getWidth()*2, bf.getHeight()*2,
					RenderHelper.RIGHT | RenderHelper.BOTTOM
				);
			}
		}
	}
	
	public void drawPuzzle(Graphics2D g, int y, int size) {
		puzzle.draw(g, (GameScreen.WIDTH - size) / 2, y, size);
	}
	
	public void preGame(){
		switch(InfoManager.SWORDEQUIP[InfoManager.SELECTED_SLOT])
		{
			case 1:
			{
				puzzleItem[2] = Resource.get("sword");
				break;
			}
			case 2:
			{
				puzzleItem[2] = Resource.get("sword1");
				break;
			}
			case 3:
			{
				puzzleItem[2] = Resource.get("sword2");
				break;
			}
		}
		switch(InfoManager.ARMOREQUIP[InfoManager.SELECTED_SLOT])
		{
			case 1:
			{
				puzzleItem[3] = Resource.get("shield");
				break;
			}
			case 2:
			{
				puzzleItem[3] = Resource.get("shield1");
				break;
			}
			case 3:
			{
				puzzleItem[3] = Resource.get("shield2");
				break;
			}
		}
	}
	
	public void soundSword()
	{
		soundEffect = Resource.getAudio("sword");
		soundEffect.play();
	}
	public synchronized void soundDeath()
	{
		soundEffect = Resource.getAudio("zombiedeath");
		soundEffect.play();
	}
}
