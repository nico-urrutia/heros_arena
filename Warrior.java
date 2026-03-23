package heros_arena;
class Warrior extends Entity{
	private boolean enraged = false;
	
	public boolean isEnraged() {
		return enraged;
	}

	public Warrior(int health, int damage, String name, boolean stunned, boolean poisoned, boolean alive, boolean me, int x, int y, int height, int width, int speed) {
		super(50000, 300, name, false, false, true, me, x, y, height, width, 25);
	}
	
	@Override
	public boolean attack(Entity target) {
		if(super.attack(target)){
		ironFist(target);
		}
		return true;
	}
	@Override
	public void warCry() {
		String path = "C:\\Users\\agian\\eclipse-workspace\\Program II\\src\\heros_arena\\audios\\warrior_cry.wav";
	    playSound(path);
	}
	public void ironFist(Entity target) {
		String path = "C:\\Users\\agian\\eclipse-workspace\\Program II\\src\\heros_arena\\audios\\warrior_ironFist.wav";
	    playSound(path);
		target.takeDamage(this.getDamage());
		double chance = Math.random();
		if (target.isAlive() && chance<=0.10){
			target.getStunned(1000);
		}
	}
	
	public void update() {
        if (this.getHealth()<1000 && !this.enraged) {
            BerserkerRage();
        }
		if(this.getHealth()<=0) {
			this.die();
		}
	}
	public void BerserkerRage() {
		this.enraged = true;
		this.setDamage((int)(getDamage()*1.2));
	}
	
}