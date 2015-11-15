package main;

import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JComponent;

import render.AnimationManager;
import render.GIFreader;
import render.GIFreader;

public class GameScreen extends JComponent {
	
	HashMap<String,AnimationManager> rs = new HashMap<>();
	
	public AnimationManager read(String url){
		return new AnimationManager(GIFreader.get(url));
	}
	public GameScreen(){
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
		
	}
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		//update object on screen + condition
		//rs.get.update();
	//	rs.draw((Graphics2D)g,0,0);
	}

}
