package LogicGame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import render.AnimationManager;

public class Moveable { 
	private AnimationManager am;
	private boolean start=false;
	private boolean end = false;
	private int x,y,sx,sy,ex,ey;
	private int frame=0 ;
	
	public Moveable(int x,int y,int sx,int sy,int ex,int ey,AnimationManager am){
		this.x= x;
		this.y=y;
		this.sx=sx;
		this.sy=sy;
		this.am = am;
		this.ex= ex;
		this.ey= ey;
		
		
	}
	
	public void update(Graphics g){
		BufferedImage img = am.getCurrentBufferedImage();
		x =(ex-sx)*frame/50 + sx;
		y = (ey-sy)*frame/50 + sy;
		g.drawImage(img,x,y,am.getWidth(),am.getHeight(),null);
		frame++;
		
	}
	

}
