package heros_arena;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import heros_arena.utils.ventanas.ventanaBitmap.VentanaGrafica;
class Goblin extends Entity{
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	public Goblin(int health, int damage, String name, boolean stunned, boolean poisoned, boolean alive, boolean me, int x, int y, int height, int width, int speed) {
		super(15000, 800, name, false, false, true, me, x, y, height, width, 37);
	}
	
	@Override
	public boolean attack(Entity target) {
		if(super.attack(target)) {
		stab(target);
		}
		return true;
	}
	@Override
	public void warCry() {
		String path = "C:\\Users\\agian\\eclipse-workspace\\Program II\\src\\heros_arena\\audios\\goblin_cry.wav";
	    playSound(path);
	}
	public void stab(Entity target) {
		String path = "C:\\Users\\agian\\eclipse-workspace\\Program II\\src\\heros_arena\\audios\\goblin_stab.wav";
	    playSound(path);
		target.takeDamage(this.getDamage());
		if((100*(Math.random()))<10) {
			poison(target);
		}
	}
	@Override
	public void update(Entity target) {
		if(this.getHealth()<=0) {
			this.die();
		}
	}
	public void poison(Entity target) {
		target.setPoisoned(true);
		int i;
		for(i=0; i<4; i++) {
			scheduler.schedule(() -> {
				target.takeDamage(200);
	        }, 500*i
					, TimeUnit.MILLISECONDS);
		}
		scheduler.schedule(() -> {
	        target.setPoisoned(false);
	    }, 2000, TimeUnit.MILLISECONDS);
	}

	@Override
	void draw(VentanaGrafica v) {
		// TODO
		
	}																																											
}