package spaceship;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
//import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;

public class Player {
	
	public static final int MAX_V = 50;
	public static final int MAX_TOP = 0;
	public static final int MAX_BOTTOM = 645;
	
	Image img_c = new ImageIcon("res/player.png").getImage();
	Image img_l = new ImageIcon("res/player_left.png").getImage();
	Image img_r = new ImageIcon("res/player_right.png").getImage();
	
//	Image img_c = new ImageIcon(getClass().getClassLoader().getResource("res/player.png")).getImage();
//	Image img_l = new ImageIcon(getClass().getClassLoader().getResource("res/player_left.png")).getImage();
//	Image img_r = new ImageIcon(getClass().getClassLoader().getResource("res/player_right.png")).getImage();
	
//	int h = new ImageIcon(getClass().getClassLoader().getResource("res/player.png")).getIconHeight();
//	int w = new ImageIcon(getClass().getClassLoader().getResource("res/player.png")).getIconWidth();

	int h = new ImageIcon("res/player.png").getIconHeight();
	int w = new ImageIcon("res/player.png").getIconWidth();


	Image img = img_c;
	
	public Rectangle getRect() {
		return new Rectangle(x, y, w, h);		
	}
	
	int v = 0;
	int dv = 0;
	int s = 0;
	
	int x = 30;
	int y = 100;
	int dy = 0;
	
	int layer1 = 0;
	int layer2 = 1915;
	int layer3 = 3830;
	
	
	public void move() {
		s += v;
		v += dv;
		if (v <= 0) v = 0;
		if(v >= MAX_V) v = MAX_V;
		y -=dy;
		if(y <= MAX_TOP) y = MAX_TOP;
		if (y >= MAX_BOTTOM) y = MAX_BOTTOM;
		if (layer3 - v <= 0) {
			layer1 = 0;
			layer2 = 1915;
			layer3 = 3830;
		} else {
		layer1 -= v;
		layer2 -= v;
		layer3 -= v;
		}
	}
	
	public void keyPressed(KeyEvent e){
		//JOptionPane.showMessageDialog(null, "key pressed");
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT){
			dv = 1;
		}
		if(key == KeyEvent.VK_LEFT){
			dv = -1;
		}
		
		if(key == KeyEvent.VK_UP){
			dy = 5;
			img = img_l;
		}
		if(key == KeyEvent.VK_DOWN){
			dy = -5;
			img = img_r;
		}
		
	}
	
	public void keyReleased(KeyEvent e){
		//JOptionPane.showMessageDialog(null, "key released");
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT){
			dv = 0;
		}
		
		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN){
			dy = 0;
			img = img_c;
		}
		
	}
	
}
