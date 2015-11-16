package render;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();
	private ArrayList<Renderable> entities;
	
	public static RenderableHolder getinstance(){
		return instance;
		
	}
	
	public RenderableHolder(){
		entities = new ArrayList<>();
		
	}
	
	public void add(Renderable r){
		entities.add(r);
		Collections.sort(entities,new Comparator<Renderable>(){

			@Override
			public int compare(Renderable r1, Renderable r2) {
				if(r1.getZ() > r2.getZ()) return 1;
				return 0;
			}
		
		});
	}
	
	public ArrayList<Renderable> getRenderableList(){
		return entities;
		
	}

}
