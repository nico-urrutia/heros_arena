package heros_arena;
import java.util.Objects;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class Entity{//Subclases: Warrior, Orc, Goblin
	private int health;
	private int damage;
	private String name;
	private boolean stunned = false;
	private boolean poisoned = false;
	private boolean alive = true;
	private boolean me = true;
	private int x;
	private int y;
	private int height;
	private int width;
	private int xspeed;
	protected int yspeed = 20;
	protected boolean jumping = false;
	protected final int gravity = 1;
	protected final int jumpPower = -15;
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private boolean isOnSolid =  true;
	
	protected void playSound(String soundFilePath) {
	    try {
	        File soundFile = new File(soundFilePath);
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioIn);
	        clip.start();
	    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	        System.err.println("Error playing sound: " + e.getMessage());
	    }
	}
	
	public boolean isOnSolid() {
		return isOnSolid;
	}

	public void setOnSolid(boolean isOnSolid) {
		this.isOnSolid = isOnSolid;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

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

	public int getxspeed() {
		return xspeed;
	}

	public void setxspeed(int xspeed) {
		this.xspeed = xspeed;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isStunned() {
		return stunned;
	}

	public void setStunned(boolean stunned) {
		this.stunned = stunned;
	}
	
	public void getStunned(int time) {
		this.setStunned(true);
		scheduler.schedule(() -> {
            this.setStunned(false);
        }, time, TimeUnit.MILLISECONDS);
		
	}

	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMe() {
		return me;
	}

	public void setMe(boolean me) {
		this.me = me;
	}

	
	public Entity(int health, int damage, String name, boolean stunned, boolean poisoned, boolean alive, boolean me, int x, int y, int height, int width, int xspeed) {
		super();
		this.health = health;
		this.damage = damage;
		this.name = name;
		this.stunned = stunned;
		this.setPoisoned(poisoned);
		this.alive = alive;
		this.me = me;
		this.x = x;
		this.y = y;
		this.xspeed = xspeed;
		this.height = height;
		this.width = width;
	}


	public void takeDamage(int amount) {
		this.setHealth(this.getHealth()-amount);
	}
	
	public boolean attack(Entity target) {
		if(this.isStunned()) {
			return false;
		}
		double dx = target.getX() - this.getX();
		double dy = target.getY() - this.getY();
		double distanceSquared = (dx * dx) + (dy * dy);

		if (distanceSquared > 10000) {
		    return false; 
		}
		return true;
	}
	
	public void die() {
		this.setAlive(false);
	}
	
	public void warCry() {
		//Each entity has a war cry
	}
	
	public void moveLeft() {
		x-=this.xspeed;
	}
	 public void moveRight() {
		 x+= this.xspeed;
	}
	
	public void jump() {
	}

	@Override
	public String toString() {
		return "Entity [health=" + health + ", damage=" + damage + ", name=" + name + ", stunned=" + stunned
				+ ", alive=" + alive + ", me=" + me + ", x=" + x + ", y=" + y + ", height=" + height + ", width="
				+ width + ", xspeed=" + xspeed + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(alive, damage, gravity, health, height, isOnSolid, jumpPower, jumping, me, name, poisoned,
				xspeed, stunned, yspeed, width, x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		return alive == other.alive && damage == other.damage && gravity == other.gravity && health == other.health
				&& height == other.height && isOnSolid == other.isOnSolid && jumpPower == other.jumpPower
				&& jumping == other.jumping && me == other.me && Objects.equals(name, other.name)
				&& poisoned == other.poisoned && xspeed == other.xspeed && stunned == other.stunned && yspeed == other.yspeed
				&& width == other.width && x == other.x && y == other.y;
	}

	public void update(Entity target) {
		
	}

	public boolean isPoisoned() {
		return poisoned;
	}

	public void setPoisoned(boolean poisoned) {
		this.poisoned = poisoned;
	}

}
