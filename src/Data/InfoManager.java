package Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.net.URISyntaxException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.sun.corba.se.impl.protocol.InfoOnlyServantCacheLocalCRDImpl;

public class InfoManager {
	
	private static final int MAXSLOT = 3;
	private static int AMOUNT_USED_SLOT;
	private static int SELECTED_SLOT;
	private static int[] LEVEL_WEAPON = new int[MAXSLOT];
	private static int[] LEVEL_ARMOR = new int[MAXSLOT];
	private static int[] LEVEL_POTION = new int[MAXSLOT];
	private static int[] LEVEL_OVERALL = new int[MAXSLOT];
	private static int[] MAX_LEVEL_COMPLETE = new int[MAXSLOT];
	private static int[] MAXHP = new int[MAXSLOT];

	static
	{
		Scanner in = null;
		try
		{
			InputStream is = InfoManager.class.getResourceAsStream("kop.dat");
			in = new Scanner(is);
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "ERROR Contact me");
		}
		AMOUNT_USED_SLOT = in.nextInt();
		for(int i=0;i<AMOUNT_USED_SLOT;i++)
		{
			LEVEL_WEAPON[i] = in.nextInt();
			LEVEL_ARMOR[i] = in.nextInt();
			LEVEL_POTION[i] = in.nextInt();
			MAX_LEVEL_COMPLETE[i] = in.nextInt();
			recalculateStat(i);
		}
	}
	
	public static void recalculateStat(int slot){
		LEVEL_OVERALL[slot] = (LEVEL_WEAPON[slot] + LEVEL_ARMOR[slot] + LEVEL_POTION[slot])/3;
		MAXHP[slot] =  100 * (int) Math.pow(2, MAX_LEVEL_COMPLETE[slot]);
	}
	
	public static void saveGame(){
		//OutputStream out = InfoManager.class.getResourceAsStream("kop.dat");
		
	}
	

}
