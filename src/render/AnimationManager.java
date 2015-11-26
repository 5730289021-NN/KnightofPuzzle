package render;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class AnimationManager {
	private boolean isPlay;
	private boolean isLoop;
	private boolean isFinish;
	private int frame;
	private ImageData[] img;
	private int width, height;
	private int speed;

	public AnimationManager(ImageData[] img) {
		frame = 0;
		speed = 0;
		isPlay = isFinish = false;
		this.img = img;
		for (int i = 0; i < img.length; i++) {
			width = Math.max(width, img[i].getWidth());
			height = Math.max(height, img[i].getHeight());
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void play() {
		isPlay = true;
		isLoop = isFinish = false;
	}

	public void loop() {
		isPlay = false;
		isLoop = true;
		isFinish = false;
	}

	public void stop() {
		isPlay = false;
		isLoop = false;
		isFinish = false;
	}
	
	public boolean isFinish() {
		return isFinish;
	}

	public void update() {
		if(++speed < 4) return ;
		speed = 0;
		if (isLoop || isPlay) {
			frame++;
			if (frame >= img.length) {
				isPlay = false;
				isFinish = true;
				frame = 0;
			}
		}
	}

	public BufferedImage getCurrentBufferedImage() {
		return img[frame].getImg();
	}
	
	public BufferedImage getCurrentBufferedImage(int frameNo) {
		return img[frameNo].getImg();
	}

}
