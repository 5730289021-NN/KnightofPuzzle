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
			public int compare(Renderable arg0, Renderable arg1) {
				// TODO Auto-generated method stub
				return 0;
			}
		
		});
	}
	
	public ArrayList<Renderable> getRenderableList(){
		return entities;
		
	}

}
