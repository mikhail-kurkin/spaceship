package spaceship;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy {
	
	int x;
	int y;
	int v;
	//int h = new ImageIcon(getClass().getClassLoader().getResource("res/enemy.png")).getIconHeight();
	//int w = new ImageIcon(getClass().getClassLoader().getResource("res/enemy.png")).getIconWidth();
	int h = new ImageIcon("res/enemy.png").getIconHeight();
	int w = new ImageIcon("res/enemy.png").getIconWidth();


	Image img = new ImageIcon("res/enemy.png").getImage();
	
	//Image img = new ImageIcon(getClass().getClassLoader().getResource("res/enemy.png")).getImage();
	
	Road road;
	
	public Rectangle getRect() {
		return new Rectangle(x, y, w, h);
	}
	
	public Enemy(int x, int y, int v, Road road){
		this.x = x;
		this.y = y;
		this.v = v;
		this.road = road;
	}
	
	public void move() {
		x = x - road.p.v - v;
	}
	

}
