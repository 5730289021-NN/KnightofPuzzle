package render;

import java.awt.Graphics2D;

public class AnimationManager {
	private boolean isPlay;
	private boolean isLoop;
	private int frame;
	private ImageData[] img;
	
	public AnimationManager(ImageData[] img){
		frame =0;
		isPlay = false;
		this.img = img;
	}
	public void play(){
		isPlay = true;
		isLoop = false;
	}
	public void loop(){
		isPlay = false;
		isLoop = true;
	}
	public void stop(){
		isPlay = false;
		isLoop = false;
	}
	public void update(){
		if(isLoop || isPlay){
			frame++;
			if(frame >= img.length){
				isPlay = false;
				frame = 0;
			}
		}
	}
	public void draw(Graphics2D g2,int x,int y){
		if(isPlay || isLoop){
			ImageData gif = img[frame];
			g2.drawImage(gif.getImg(),x+gif.getOffsetX(),y+gif.getOffsetY(),null);
		}
	}
	

}
