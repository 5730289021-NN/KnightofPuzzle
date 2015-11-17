package res;

import java.util.HashMap;

import render.AnimationManager;
import render.ImageData;
import render.ImageReader;

public class Resource {
	private static HashMap<String,AnimationManager> rs = new HashMap<>();
	
	public AnimationManager read(String url){
		return new AnimationManager(ImageReader.get(url));
	}
	public Resource(){
		
		rs.put("sword", read("pic/anime/sword.png"));
		rs.put("sword1" , read("pic/anime/sword1.png"));
		rs.put("sword2" , read("pic/anime/sword2.png"));
		rs.put("shiled" ,read("pic/anime/shiled.png"));
		rs.put("shiled1", read("pic/anime/shiled1.png"));
		rs.put("shiled2" , read("pic/anime/shiled2.png"));
		rs.put("bin", read("pic/anime/bin.png"));
		
		rs.put("earthbg", read("pic/anime/earthbg.jpg"));
		rs.put("heavenbg", read("pic/anime/heavenbg.jpg"));
		rs.put("underwaterbg", read("pic/anime/underwaterbg.jpg"));
		rs.put("deemobg", read("pic/anime/deemobg.jpg"));
		
		rs.put("burny", read("pic/anime/burny.gif"));
		rs.put("attackburny", read("pic/anime/attackburny.gif"));
		rs.put("kingburny", read("pic/anime/KingBurny.gif"));
		rs.put("attackkingburny", read("pic/anime/attackkingburny.gif"));
		rs.put("megaburny", read("pic/anime/megaburny.gif"));
		rs.put("attackmegaburny", read("pic/anime/attackmegaburny.gif"));
		rs.put("duel", read("pic/anime/duel.gif"));
		rs.put("attackduel", read("pic/anime/attackduel.gif"));
		rs.put("duel2", read("pic/anime/attackduel2.gif"));
		rs.put("attackduel2", read("pic/anime/attackduel2.gif"));
		rs.put("gunner", read("pic/anime/gunner.gif"));
		rs.put("attackgunner", read("pic/anime/attackgunner.gif"));
		rs.put("gunner2", read("pic/anime/attackgunner2.gif"));
		rs.put("attackgunner2", read("pic/anime/attackgunner2.gif"));
		rs.put("tilith", read("pic/anime/tilith.gif"));
		rs.put("attacktilith", read("pic/anime/attacktilith.gif"));
		rs.put("minimaxwell", read("pic/anime/attackminimaxwell.gif"));
		rs.put("attackminimaxwell", read("pic/anime/attackminimaxwell.gif"));
		rs.put("maxwell", read("pic/anime/maxwell.gif"));
		rs.put("attackmaxwell", read("pic/anime/attackmaxwell.gif"));
		rs.put("sniper", read("pic/anime/sniper.gif"));
		rs.put("attacksniper", read("pic/anime/attacksniper.gif"));
		rs.put("me", read("pic/anime/me.gif"));
		rs.put("attackme", read("pic/anime/attackme.gif"));
		
	}
	
	public static AnimationManager get(String key) {
		return rs.get(key);
	}
}
