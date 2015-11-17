package Data;

import render.AnimationManager;
import res.Resource;

public class Maxwell extends Monster{
	private AnimationManager mw;
	private AnimationManager amw;
	private boolean stand;
	private int count =0;
	private boolean check = false;
	
	public Maxwell(){
		super(5000,260,250,500);
		mw = Resource.get("maxwell");
		amw = Resource.get("attackmaxwell");
		stand = true;
		if(this.hp <=0) stand = false;
		
		if(this.hp <=0) stand =false;
		
		 if (this.hp <= 500){
			check = false;
			this.at = 1500;
			count ++;
			if(count == 1){
				this.at = 260;
				check = true;
				count =0;
			}
			else if(this.hp <=2500){
				count =0;
				check = false;
				this.at = 500;
				count ++ ;
				if(count==1){
					this.at= 260;
					check = true;
					
				}
		
			else if(this.hp <= 3500){
				count=0;
			check = false;
			this.def = 500;
					count++;
					if(count==4){
						this.def =250;
						check = true;
					}
		}
		}
	
		}
		
		
	}

	@Override
	public void increseStat(int level) {

	}

}
