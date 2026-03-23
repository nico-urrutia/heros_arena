package heros_arena;
class Ogre extends Entity{
	boolean bombDropped = false;
	public Ogre(int health, int damage, String name, boolean stunned, boolean poisoned, boolean alive, boolean me, int x, int y, int height, int width, int speed) {
		super(80000, 200, name, false, false, true, me, x, y, height, width, 20);
	}
	
	@Override
	public boolean attack(Entity target) {
		if(super.attack(target)){
		punch(target);
		}
		return true;
	}
	@Override
	public void warCry() {
		String path = "C:\\Users\\agian\\eclipse-workspace\\Program II\\src\\heros_arena\\audios\\ogre_cry.wav";
	    playSound(path);
	}
	public void punch(Entity target) {
		String path = "C:\\Users\\agian\\eclipse-workspace\\Program II\\src\\heros_arena\\audios\\ogre_punch.wav";
	    playSound(path);
		target.takeDamage(this.getDamage());
	}
	@Override
	public void update(Entity target) {
		if(this.getHealth()<4000 && !bombDropped) {
			this.dropBomb(target);
			bombDropped = true;
		}
		if(this.getHealth()<=0) {
			this.die();
		}
	}
	public void dropBomb(Entity target) {
		Bomb bomb = new Bomb(1000, 700, this.getX(), this.getY(), 100);
		bomb.explode(target);
	}
	

}