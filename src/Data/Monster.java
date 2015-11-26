package Data;

public abstract class Monster {
	protected int hp;
	protected int at;
	protected int def;
	protected int gold;

	public Monster(int hp,int at,int def,int gold){
		this.hp = hp;
		this.at = at;
		this.def = def;
		this.gold = gold;
	}
	
	public int getHp() {
		return hp;
	}
	
	public int getAt() {
		return at;
	}
	
	public int getDef() {
		return def;
	}
	
	public int getGold() {
		return gold;
	}
	
	public abstract void increseStat(int level);

}
