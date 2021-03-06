package res;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import base.IntroScreen;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import render.AnimationManager;
import render.ImageData;
import render.ImageReader;

public class Resource {
	private static HashMap<String,AnimationManager> rs = new HashMap<>();
	private static HashMap<String,AudioClip> audio = new HashMap<>();
	private static GraphicsEnvironment ge;

	
	public AnimationManager read(String url) throws ResourceException {
		return new AnimationManager(ImageReader.get(url));
	}
	
	public Resource(){
		audio.put("birdSound", Applet.newAudioClip(IntroScreen.class.getClassLoader().getResource("sound/bird.wav")));
		audio.put("thebeat", Applet.newAudioClip(IntroScreen.class.getClassLoader().getResource("sound/thebeat.wav")));
		audio.put("doorbell", Applet.newAudioClip(IntroScreen.class.getClassLoader().getResource("sound/doorbell2.wav")));
		audio.put("sword", Applet.newAudioClip(IntroScreen.class.getClassLoader().getResource("sound/sword.wav")));
		audio.put("gamebgm", Applet.newAudioClip(IntroScreen.class.getClassLoader().getResource("sound/Intense Battle Music.wav")));
		audio.put("zombiedeath", Applet.newAudioClip(IntroScreen.class.getClassLoader().getResource("sound/zombiedeath.wav")));
		
		try {
		
			rs.put("sword", read("pic/acc/sword.png"));
			rs.put("sword1" , read("pic/acc/sword1.png"));
			rs.put("sword2" , read("pic/acc/sword2.png"));
			rs.put("shield" ,read("pic/acc/shield.png"));
			rs.put("shield1", read("pic/acc/shield1.png"));
			rs.put("shield2" , read("pic/acc/shield2.png"));
			rs.put("smallpotion", read("pic/acc/smallpotion.png"));
			rs.put("largepotion", read("pic/acc/largepotion.png"));
			
	
			rs.put("earthbg", read("pic/bg/earthbg.jpg"));
			rs.put("heavenbg", read("pic/bg/heavenbg.jpg"));
			rs.put("underwaterbg", read("pic/bg/underwaterbg.jpg"));
			rs.put("deemobg", read("pic/bg/deemobg.jpg"));
			rs.put("inventorybg", read("pic/bg/inventorybg.png"));
			rs.put("missioncomplete", read("pic/bg/missioncomplete.jpg"));
			rs.put("gameover", read("pic/bg/gameover.jpg"));
	
			
			rs.put("bin", read("pic/etc/bin.png"));
			
			rs.put("introbg", read("pic/bg/introbg.gif"));
			rs.put("introbg2", read("pic/bg/introbg2.gif"));
			rs.put("starbg", read("pic/bg/starbg.png"));
			
			rs.put("titleIcon", read("pic/label/titleIcon.jpg"));
		
			rs.put("burny", read("pic/anime/burny.gif"));
			rs.put("attackburny", read("pic/anime/attackburny.gif"));
			rs.put("kingburny", read("pic/anime/KingBurny.gif"));
			rs.put("attackkingburny", read("pic/anime/attackkingburny.gif"));
			rs.put("megaburny", read("pic/anime/megaburny.gif"));
			rs.put("attackmegaburny", read("pic/anime/attackmegaburny.gif"));
			rs.put("duel", read("pic/anime/duel.gif"));
			rs.put("attackduel", read("pic/anime/attackduel.gif"));
			rs.put("duel2", read("pic/anime/duel2.gif"));
			rs.put("attackduel2", read("pic/anime/attackduel2.gif"));
			rs.put("gunner", read("pic/anime/gunner.gif"));
			rs.put("attackgunner", read("pic/anime/attackgunner.gif"));
			rs.put("gunner2", read("pic/anime/gunner2.gif"));
			rs.put("attackgunner2", read("pic/anime/attackgunner2.gif"));
			rs.put("tilith", read("pic/anime/tilith.gif"));
			rs.put("attacktilith", read("pic/anime/attacktilith.gif"));
			rs.put("minimaxwell", read("pic/anime/minimaxwell.gif"));
			rs.put("attackminimaxwell", read("pic/anime/attackminimaxwell.gif"));
			rs.put("maxwell", read("pic/anime/maxwell.gif"));
			rs.put("attackmaxwell", read("pic/anime/attackmaxwell.gif"));
			rs.put("sniper", read("pic/anime/sniper.gif"));
			rs.put("attacksniper", read("pic/anime/attacksniper.gif"));
			rs.put("me", read("pic/anime/me.gif"));
			rs.put("attackme", read("pic/anime/attackme.gif"));
			rs.put("merun", read("pic/anime/runme.gif"));
	
			
			rs.put("goldentag", read("pic/etc/goldtag.png"));
			rs.put("silvertag", read("pic/etc/silvertag.png"));
			rs.put("coppertag", read("pic/etc/coppertag.png"));
		} catch(ResourceException e) {
			e.printStackTrace();
		}
		
	}
	
	public static AnimationManager get(String key) {
		if(rs.containsKey(key)) {
			return rs.get(key);
		}
		throw new RuntimeException("AnimationManger key not found. " + key);
	}
	
	public static AudioClip getAudio(String key) {
		if(audio.containsKey(key)) {
			return audio.get(key);
		}
		throw new RuntimeException("Audio key not found. " + key);
	}
}
