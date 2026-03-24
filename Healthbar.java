package heros_arena;
import java.awt.Color;
import heros_arena.utils.ventanas.ventanaBitmap.VentanaGrafica;

public class Healthbar{
	private float percentage;
	Color healthyGreen = new Color(85, 180, 130); 
	Color poisonedGreen = new Color(85, 107, 47);
	private Color barColor = healthyGreen;
	public void updateStatus(boolean isPoisoned,  float percentage) {
        if (isPoisoned) {
            this.barColor = Color.BLACK;
        } else if(percentage<30) {
        	this.barColor = Color.RED;
        	
        } else {
            this.barColor = healthyGreen;
        }
    }

    public Color getBarColor() {
        return barColor;
    }

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	
	public void draw(VentanaGrafica v, int x, int y) {
	    v.dibujaRect(x, y, 300, 15, 1, Color.GRAY, Color.GRAY); 
	    v.dibujaRect(x, y, 3*(int)this.percentage, 15, 0, this.barColor, this.barColor);
	}

	public Healthbar(float percentage) {
		super();
		this.percentage = percentage;
	}
	
}