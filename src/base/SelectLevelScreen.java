package base;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Data.InfoManager;
import input.InputUtility;
import main.Main;
import render.AnimationManager;
import res.Resource;

public class SelectLevelScreen extends JComponent {
	private static final double[] xpos = { 0.1, 0.3, 0.5, 0.75 };
	private static final double[] ypos = { 0.5, 0.66, 0.44, 0.17 };
	private static int[] xpos_ = new int[4];
	private static int[] ypos_ = new int[4];
	private int meXPos, meYPos;
	public static int meLocation;

	private JButton playButton;
	private JButton shopButton;
	private JButton backButton;
	private JButton achievement;
	private JButton mode;

	private AnimationManager me;
	private AnimationManager maxwell;
	private int direction = 0;

	static {
		for (int i = 0; i < 4; i++) {
			xpos_[i] = (int) (GameScreen.WIDTH * xpos[i]);
			ypos_[i] = (int) (GameScreen.HEIGHT * ypos[i]);
		}
	}

	public SelectLevelScreen() {
		super();
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(GameScreen.WIDTH, GameScreen.HEIGHT));
		setVisible(true);
		setFocusable(true);
		requestFocus();
		me = Resource.get("me");
		maxwell = Resource.get("minimaxwell");
		me.loop();
		maxwell.loop();
		meLocation = 0;
		meXPos = xpos_[meLocation] - me.getWidth() / 2;
		meYPos = ypos_[meLocation] - me.getHeight();
		playButton = new JButton("Fight Level : " + (meLocation + 1));
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.changeGameScreen(Main.GAMESCREEN);
				// TODO
			}
		});
		shopButton = new JButton("Inventory/Shop");
		shopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.changeGameScreen(Main.SHOPSCREEN);
			}
		});
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.changeGameScreen(Main.INTROSCREEN);
			}
		});
		achievement = new JButton("Achievement");
		achievement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Quest");
				AchievementScreen d1 = new AchievementScreen();
				d1.setSize(new Dimension(550, 320));
				d1.setTitle("Achievement");
				d1.setModal(true);
				d1.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				d1.setVisible(true);

			}
		});
		mode = new JButton("Mode");
		mode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Mode");
				ModeScreen ms = new ModeScreen();
				ms.setSize(new Dimension(400, 90));
				ms.setTitle("Mode");
				ms.setModal(true);
				ms.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				ms.setVisible(true);	

			}
		});
		add(playButton);
		add(shopButton);
		add(achievement);
		add(mode);
		add(backButton);

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				InputUtility.setKeyPressed(e.getKeyCode(), false);
			}

			public void keyPressed(KeyEvent e) {
				InputUtility.setKeyPressed(e.getKeyCode(), true);
				InputUtility.setKeyTriggered(e.getKeyCode(), true);
				// System.out.println(e.getKeyCode());
			}
		});

		Thread meThread = new Thread(new Runnable() {
			@Override
			public void run() {
				double percent = 0;
				while (true) {
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)) {
						synchronized (this) {
							direction = 1;
							// System.out.println("Right");
						}
					}
					if (InputUtility.getKeyPressed(KeyEvent.VK_LEFT)) {
						synchronized (this) {
							direction = -1;
							// System.out.println("Left");
						}
					}
					if (InputUtility.getKeyTriggered(KeyEvent.VK_C)) {
						String s = JOptionPane.showInputDialog("Say :");
						switch (s.toLowerCase().trim()) {
						case "secretlevel": {
							if (InfoManager.MAX_LEVEL_COMPLETE[InfoManager.SELECTED_SLOT] < 3) {
								InfoManager.MAX_LEVEL_COMPLETE[InfoManager.SELECTED_SLOT] += 1;
								System.out.println("Cheat: Level + 1");
							}
							break;
						}
						case "exit": {
							System.exit(0);
							break;
						}
						}
						if (s.trim().toLowerCase().contains("iwantmoney")) {
							System.out.println("Cheat: Add Money " + s.substring(10, s.trim().length()) + " B");
							try {
								int moneytoAdd = Integer.parseInt(s.substring(10, s.trim().length()));
								InfoManager.MONEY[InfoManager.SELECTED_SLOT] += moneytoAdd;
							} catch (NumberFormatException e) {
								InfoManager.MONEY[InfoManager.SELECTED_SLOT] += 10;
							}
						}
					}

					if (direction != 0
							&& (meLocation + direction) <= InfoManager.MAX_LEVEL_COMPLETE[InfoManager.SELECTED_SLOT]) {
						try {
							percent = percent + 0.01;
							// System.out.println(percent);
							if (!(direction == -1 && meLocation == 0) || !(direction == 1 && meLocation == 3)) {
								me = new AnimationManager(Resource.get("merun").getAllImage());
								if (percent >= 0.7) {
									me = new AnimationManager(Resource.get("attackme").getAllImage());
									me.loop();
									me.update();
								}
								meXPos = (int) (xpos_[meLocation] * (1 - percent)
										+ (xpos_[meLocation + direction] * percent) - me.getWidth() / 2);
								meYPos = (int) (ypos_[meLocation] * (1 - percent)
										+ (ypos_[meLocation + direction] * percent) - me.getHeight());

							}
							if (percent >= 1) {
								me = new AnimationManager(Resource.get("me").getAllImage());
								synchronized (this) {
									// System.out.println("direction=" +
									// direction + "meLocation=" + meLocation);
									if (!(direction != -1 && meLocation == 0) || !(direction == 1 && meLocation == 4)) {
										// System.out.println(meLocation + "
										// 1");
										meLocation += direction;
										// System.out.println(meLocation + "
										// 2");
									}
									meXPos = xpos_[meLocation] - me.getWidth() / 2;
									meYPos = ypos_[meLocation] - me.getHeight();
									playButton.setText("Fight Level : " + (meLocation + 1));
									if (meLocation == 3)
										playButton.setText("FIGHT THE BOSS");
									direction = 0;
									percent = 0;
								}
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							meLocation = 0;
							meXPos = xpos_[meLocation] - me.getWidth() / 2;
							meYPos = ypos_[meLocation] - me.getHeight();
							playButton.setText("Fight Level : " + (meLocation + 1));
						}
					}
					InputUtility.postUpdate();

				}

			}
		});
		meThread.start();

	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		g2.fillRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
		g2.drawImage(Resource.get("starbg").getCurrentBufferedImage(), 0, 0, GameScreen.WIDTH, GameScreen.HEIGHT, null);
		g2.setColor(Color.YELLOW);
		int drawLevel = InfoManager.MAX_LEVEL_COMPLETE[InfoManager.SELECTED_SLOT] + 1;
		drawLevel = Math.min(drawLevel, xpos_.length);
		g2.drawPolyline(xpos_, ypos_, drawLevel);
		for (int i = 0; i < drawLevel; i++) {
			g2.setColor(Color.RED);
			g2.fillOval(xpos_[i] - 10, ypos_[i] - 10, 20, 20);
			g2.setColor(Color.WHITE);
			g2.fillOval(xpos_[i] - 5, ypos_[i] - 5, 10, 10);
		}
		me.update();
		maxwell.update();

		if (direction == 0 || direction == 1) {
			g2.drawImage(me.getCurrentBufferedImage(), meXPos, meYPos, me.getWidth(), me.getHeight(), null);
		} else {
			g2.drawImage(me.getCurrentBufferedImage(), meXPos + me.getWidth(), meYPos, -me.getWidth(), me.getHeight(),
					null);
		}

		g2.drawImage(maxwell.getCurrentBufferedImage(), xpos_[3], ypos_[3] - maxwell.getHeight(), maxwell.getWidth(),
				maxwell.getHeight(), null);
	}

	public SelectLevelScreen getContext() {
		return this;
	}

}
