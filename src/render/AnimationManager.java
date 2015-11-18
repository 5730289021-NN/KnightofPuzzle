package render;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class AnimationManager {
	private boolean isPlay;
	private boolean isLoop;
	private int frame;
	private ImageData[] img;
	private int width, height;
	private int speed;

	public AnimationManager(ImageData[] img) {
		frame = 0;
		speed = 0;
		isPlay = false;
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
		isLoop = false;
	}

	public void loop() {
		isPlay = false;
		isLoop = true;
	}

	public void stop() {
		isPlay = false;
		isLoop = false;
	}

	public void update() {
		if(++speed < 4) return ;
		speed = 0;
		if (isLoop || isPlay) {
			frame++;
			if (frame >= img.length) {
				isPlay = false;
				frame = 0;
			}
		}
	}

	public BufferedImage getCurrentBufferedImage() {
		return img[frame].getImg();
	}

}
