package Data;

import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import render.InventoryTag;

public class InfoManager {
	
	public static final int EMPTYSAVE = 0;
	public static final int NORMALSAVE = 1;
	public static final int MAXSLOT = 3;
	
	
	public static int SELECTED_SLOT; //array 0,1,2 but display 1,2,3
	
	
	public static int[] LEVEL_WEAPON = new int[MAXSLOT]; //4 + 2 + 1 System
	public static int[] LEVEL_ARMOR = new int[MAXSLOT]; //4 + 2 + 1 System
	public static int[] LEVEL_SMALLPOTION = new int[MAXSLOT]; //Exact Number System
	public static int[] LEVEL_LARGEPOTION = new int[MAXSLOT]; //Exact Number System
	public static int[] EQUIP = new int[MAXSLOT]; //(X - X - X)3 System
	public static int[] MONEY = new int[MAXSLOT]; //Exact Number System
	public static int[] MAX_LEVEL_COMPLETE = new int[MAXSLOT]; //Exact Number System
	
	public static int[] MAXHP = new int[MAXSLOT]; //Exact Number System
	public static int[] SWORDEQUIP = new int[MAXSLOT]; //Exact Number System
	public static int[] ARMOREQUIP = new int[MAXSLOT]; //Exact Number System
	public static int[] SMALLPOTIONEQUIP = new int[MAXSLOT]; //Exact Number System
	public static int[] LARGEPOTIONEQUIP = new int[MAXSLOT]; //Exact Number System
	
	private static File file;
	static
	{
		Scanner in = null;
		try {
			FileInputStream fis = new FileInputStream("C:\\KnightofPuzzle\\kop.dat");
			file = new File("C:\\KnightofPuzzle\\kop.dat");
			in = new Scanner(fis);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Save game not found creating new save file..., Click to \"OK\" to continue.");			
			file = new File("C:\\KnightofPuzzle\\kop.dat");
			try {
				file.createNewFile();
				saveGame(EMPTYSAVE);
				System.exit(0);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null,"Please create a folder called KnightofPuzzle in C:\\ ");
				e1.printStackTrace();
			}
			
		}
		try
		{
			for(int i=0;i<MAXSLOT;i++)
			{
				LEVEL_WEAPON[i] = in.nextInt();
				LEVEL_ARMOR[i] = in.nextInt();
				LEVEL_SMALLPOTION[i] = in.nextInt();
				LEVEL_LARGEPOTION[i] = in.nextInt();
				EQUIP[i] = in.nextInt();
				MONEY[i] = in.nextInt();
				MAX_LEVEL_COMPLETE[i] = in.nextInt();
				preCalculateStat(i);
			}	
		}catch(NoSuchElementException e)
		{
			JOptionPane.showMessageDialog(null, "NoSuchElementException, Please delete the kop.dat in C://KnightofPuzzle and try again" );
			System.exit(0);
		}
		in.close();
	}
	
	public static void preCalculateStat(int slot){
		//TODO
		MAXHP[slot] =  100 * (int) Math.pow(2, MAX_LEVEL_COMPLETE[slot]);
		int equip = EQUIP[slot];
		SWORDEQUIP[slot] = equip/1000;
		equip %= 1000;
		ARMOREQUIP[slot] = equip/100;
		equip %= 100;
		SMALLPOTIONEQUIP[slot] = equip/10;
		equip %= 10;
		LARGEPOTIONEQUIP[slot] = equip;
	}
	
	public static void postCalculateStat(int slot){
		//TODO
		EQUIP[slot] = SWORDEQUIP[slot] * 1000 + ARMOREQUIP[slot] * 100 + SMALLPOTIONEQUIP[slot] * 10 + LARGEPOTIONEQUIP[slot];
	}
	
	public static void saveGame(int mode){

		PrintWriter writer;
		try {
			writer = new PrintWriter(file, "UTF-8");
			if(mode == EMPTYSAVE)
			{
				for(int i = 0;i<21;i++)
				{
					writer.write("0");
					if(i != 20)
					{
						writer.write(" ");
					}
				}
				
			} else if(mode == NORMALSAVE)
			{
				postCalculateStat(SELECTED_SLOT);
				for(int i=0;i<MAXSLOT;i++)
				{
					writer.write(LEVEL_WEAPON[i] + " ");
					writer.write(LEVEL_ARMOR[i] + " ");
					writer.write(LEVEL_SMALLPOTION[i] + " ");
					writer.write(LEVEL_LARGEPOTION[i] + " ");
					writer.write(EQUIP[i] + " ");
					writer.write(MONEY[i] + " ");
					writer.write(MAX_LEVEL_COMPLETE[i] + " ");
				}
			}
			writer.flush();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	public static void initialize(int slot)
	{
		LEVEL_WEAPON[slot] = 1;
		LEVEL_ARMOR[slot] = 1;
		LEVEL_SMALLPOTION[slot] = 1;
		LEVEL_LARGEPOTION[slot] = 1;
		EQUIP[slot] = 1111;
		MONEY[slot] = 0;
		MAX_LEVEL_COMPLETE[slot] = 0;
		preCalculateStat(slot);
	}

}
