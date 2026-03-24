package heros_arena;
import utils.ventanas.ventanaBitmap.VentanaGrafica;
public class Game{
	static Entity winner;
	public static Entity createSelectedEntity(int choice, String name, int x, int y, boolean isHero) {
	    switch (choice) {
	        case 0:
	            return new Warrior(5000, 300, name, false, false, true, isHero, x, y, 64, 48, 25);
	        case 1:
	            return new Ogre(8000, 200, name, false, false, true, isHero, x, y, 96, 96, 20);
	        case 2:
	            return new Goblin(3000, 450, name, false, false, true, isHero, x, y, 40, 40, 35);
	        default:
	            return new Warrior(5000, 300, name, false, false, true, isHero, x, y, 64, 48, 25);
	    }
	}
	public static void main(String[] args) {
		String[] options = {"Warrior", "Ogre", "Goblin"};
		int heroChoice = javax.swing.JOptionPane.showOptionDialog(null, "Select your Hero:", 
		        "Character Selection", 0, 3, null, options, options[0]);
		int oppChoice = javax.swing.JOptionPane.showOptionDialog(null, "Select your Opponent:", 
		        "Character Selection", 0, 0, null, options, options[0]);
		String heroName = javax.swing.JOptionPane.showInputDialog(null, "Select a name for yourself: ");
		
		VentanaGrafica ventana = new VentanaGrafica(900, 600, "Hero's Arena");
		Platform plat1 = new Platform(120, 450, 200, 20);
		Platform plat2 = new Platform(650, 450, 200, 20); 
		Platform plat3 = new Platform(400, 320, 300, 20); 
		Platform plat4 = new Platform(400, 180, 150, 20); 
		Entity hero = createSelectedEntity(heroChoice, heroName , 200, 0, true);
		Entity opp = createSelectedEntity(oppChoice, "Cletus", 600, 0, false);
		hero.setY(ventana.getAltura()-hero.getHeight());
		opp.setY(ventana.getAltura()-opp.getHeight());
		int startingHealthHero = hero.getHealth();
		int startingHealthOpp = opp.getHealth();
		Healthbar myHealth = new Healthbar(100);
		Healthbar oppHealth = new Healthbar(100);
		
		while (!ventana.estaCerrada()&&(opp.isAlive()||hero.isAlive())) {
			float heroPerc = (float) hero.getHealth() / startingHealthHero * 100;
		    float oppPerc = (float) opp.getHealth() / startingHealthOpp * 100;
			myHealth.setPercentage(heroPerc);
			oppHealth.setPercentage(oppPerc);
			myHealth.updateStatus(hero.isPoisoned(), heroPerc);
		    oppHealth.updateStatus(opp.isPoisoned(), oppPerc);
		    hero.update(opp);
		    opp.update(hero);
	        if(hero.getHealth()<=0) {
	        	hero.die();
	        	winner = opp;
	        	break;
	        }
	        if(opp.getHealth()<0) {
	        	opp.die();
	        	winner = hero;
	        	break;
	        }
	        if (ventana.isTeclaPulsada(37)) {
	            hero.moveLeft();
	        }
	        if (ventana.isTeclaPulsada(39)) {
	            hero.moveRight();
	        }
	        if (ventana.isTeclaPulsada(65)) {
	            opp.moveLeft();
	        }
	        if (ventana.isTeclaPulsada(68)) {
	            opp.moveRight();
	        }
	        if (ventana.isTeclaPulsada(32)) {
	            hero.attack(opp);
	        }
	        if (ventana.isTeclaPulsada(81)) {
	            opp.attack(hero);
	        }
	        ventana.borra();
		    myHealth.draw(ventana, 20, 20);
		    oppHealth.draw(ventana, 500, 20);
		    java.awt.Color grisPlat = java.awt.Color.DARK_GRAY;
		    java.awt.Color bordes = java.awt.Color.BLACK;
		    ventana.dibujaRect(plat1.getX(), plat1.getY(), plat1.getHeight(), plat1.getWidth(), 2, bordes, grisPlat);
		    ventana.dibujaRect(plat2.getX(), plat2.getY(), plat2.getHeight(), plat2.getWidth(), 2, bordes, grisPlat);
		    ventana.dibujaRect(plat3.getX(), plat3.getY(), plat3.getHeight(), plat3.getWidth(), 2, bordes, grisPlat);
		    ventana.dibujaRect(plat4.getX(), plat4.getY(), plat4.getHeight(), plat4.getWidth(), 2, bordes, grisPlat);
		    ventana.dibujaRect(hero.getX(), hero.getY(), hero.getWidth(), hero.getHeight(), 2, bordes, java.awt.Color.BLUE);
	        ventana.dibujaRect(opp.getX(), opp.getY(), opp.getWidth(), opp.getHeight(), 2, bordes, java.awt.Color.RED);

	        ventana.espera(20);
	    }
		ventana.borra();
		ventana.dibujaRect(winner.getX(), winner.getY(), winner.getWidth(), winner.getHeight(), 2, java.awt.Color.BLACK, java.awt.Color.GREEN);
		javax.swing.JOptionPane.showMessageDialog(null, winner.getName() + " is the winner!!!");
		
	}
}