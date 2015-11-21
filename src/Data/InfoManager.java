package Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class InfoManager {
	
	private static final int EMPTYSAVE = 0;
	private static final int NORMALSAVE = 1;
	public static final int MAXSLOT = 3;
	public static int SELECTED_SLOT; //array 0,1,2 but display 1,2,3
	public static int[] LEVEL_WEAPON = new int[MAXSLOT];
	public static int[] LEVEL_ARMOR = new int[MAXSLOT];
	public static int[] LEVEL_POTION = new int[MAXSLOT];
	public static int[] LEVEL_OVERALL = new int[MAXSLOT];
	public static int[] MAX_LEVEL_COMPLETE = new int[MAXSLOT];
	public static int[] MAXHP = new int[MAXSLOT];
	private static File file;
	static
	{
		Scanner in = null;
		try {
			FileInputStream fis = new FileInputStream("C:\\KnightofPuzzle\\kop.dat");
			in = new Scanner(fis);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Save game not found creating new save file..., Click to \"OK\" to continue.");			
			file = new File("C:\\KnightofPuzzle\\kop.dat");
			try {
				file.createNewFile();
				saveGame(EMPTYSAVE);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null,"Please create a folder called KnightofPuzzle in C:\\ ");
				e1.printStackTrace();
			}
			
		}
		for(int i=0;i<MAXSLOT;i++)
		{
			
			//System.out.println("D");
			LEVEL_WEAPON[i] = in.nextInt();
			LEVEL_ARMOR[i] = in.nextInt();
			LEVEL_POTION[i] = in.nextInt();
			MAX_LEVEL_COMPLETE[i] = in.nextInt();
			recalculateStat(i);
		}
		in.close();
	}
	
	public static void recalculateStat(int slot){
		LEVEL_OVERALL[slot] = (LEVEL_WEAPON[slot] + LEVEL_ARMOR[slot] + LEVEL_POTION[slot])/3;
		MAXHP[slot] =  100 * (int) Math.pow(2, MAX_LEVEL_COMPLETE[slot]);
	}
	
	public static void saveGame(int mode){
		PrintWriter writer;
		try {
			writer = new PrintWriter(file, "UTF-8");
			if(mode == EMPTYSAVE)
			{
				for(int i = 0;i<12;i++)
				{
					writer.write("0");
					if(i != 11)
					{
						writer.write(" ");
					}
				}
				
			} else if(mode == NORMALSAVE)
			{
				for(int i=0;i<MAXSLOT;i++)
				{
					writer.write(LEVEL_WEAPON[i]);
					writer.write(LEVEL_ARMOR[i]);
					writer.write(LEVEL_POTION[i]);
					writer.write(MAX_LEVEL_COMPLETE[i]);
				}
			}
			writer.flush();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	

}
