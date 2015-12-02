package LogicGame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import render.AnimationManager;

public class BoxSlider { 
	private AnimationManager am;
	private boolean start=false;
	private boolean end = false;
	private int x,y,sx,sy,ex,ey;
	private int imgSize;
	private int frame=0 ;
	
	private int frameCount = 5;
	
	public BoxSlider(int sx,int sy,int ex,int ey,int imgSize,AnimationManager am){
		this.sx=sx;
		this.sy=sy;
		this.am = am;
		this.ex= ex;
		this.ey= ey;
		this.imgSize = imgSize;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isFinish() {
		return frame >= frameCount;
	}
	
	public void update(Graphics g){
		BufferedImage img = am.getCurrentBufferedImage();
		x =(ex-sx)*frame/frameCount + sx;
		y = (ey-sy)*frame/frameCount + sy;
		g.drawImage(img,x,y,imgSize,imgSize,null);
		frame++;
		
	}
	

}
