package render;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class PNGreader {
	private static ClassLoader cl = PNGreader.class.getClassLoader();
	public static BufferedImage sword,sword1,sword2,shiled,shiled1,shiled2,bin;
	
	public PNGreader(){
		try{
			sword = ImageIO.read(cl.getResource("pic/anime/sword.png"));
			sword1 = ImageIO.read(cl.getResource("pic/anime/sword1.png"));
			sword2 = ImageIO.read(cl.getResource("pic/anime/sword2.png"));
			shiled = ImageIO.read(cl.getResource("pic/anime/shiled.png"));
			shiled1 = ImageIO.read(cl.getResource("pic/anime/shiled1.png"));
			shiled2 = ImageIO.read(cl.getResource("pic/anime/shiled2.png"));
			bin = ImageIO.read(cl.getResource("pic/anime/bin.png"));
			
		}catch(Exception e){
			sword = null;
			sword1= null;
			sword2= null;
			shiled= null;
			shiled1= null;
			shiled2= null;
			bin= null;
			
		}
	}

}
