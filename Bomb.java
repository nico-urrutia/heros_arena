package heros_arena;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Bomb{
	float wait;//Time in milliseconds
	int damage;
	int x;
	int y;
	int explodeDistance;
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public float getWait() {
		return wait;
	}
	public void setWait(float wait) {
		this.wait = wait;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public int getExplodeDistance() {
		return explodeDistance;
	}
	public void setExplodeDistance(int explodeDistance) {
		this.explodeDistance = explodeDistance;
	}
	public Bomb(float wait, int damage, int x, int y, int explodeDistance) {
		super();
		this.wait = wait;
		this.damage = damage;
		this.x = x;
		this.y = y;
		this.explodeDistance = explodeDistance;
	}
	public void explode(Entity target) {
		scheduler.schedule(() -> {
			if ((target.getX()-this.getX())<this.getExplodeDistance() && (target.getY()-this.getY())<this.getExplodeDistance()) {
				target.takeDamage(this.getDamage());			
			}
        }, (long) this.getWait(), TimeUnit.MILLISECONDS);

	}
}