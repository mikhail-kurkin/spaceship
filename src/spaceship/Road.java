package spaceship;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import java.util.Timer;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Road extends JPanel implements ActionListener, Runnable {
	
	Timer mainTimer = new Timer(20, this);

	Image img_1 = new ImageIcon("res/bg_space.jpg").getImage();
	Image img_2 = new ImageIcon("res/bg_space1.jpg").getImage();
	Image img_3 = new ImageIcon("res/bg_space2.jpg").getImage();
//	Image img_1 = new ImageIcon(getClass().getClassLoader().getResource("res/bg_space.jpg")).getImage();
//	Image img_2 = new ImageIcon(getClass().getClassLoader().getResource("res/bg_space1.jpg")).getImage();
//	Image img_3 = new ImageIcon(getClass().getClassLoader().getResource("res/bg_space2.jpg")).getImage();
	
	Player p = new Player();
	
	Thread enemiesFactory = new Thread(this);
	
	Thread audioThread = new Thread(new AudioThread());
	
	List<Enemy> enemies = new ArrayList<Enemy>();
	
	public Road() {
		mainTimer.start();
		enemiesFactory.start();
		audioThread.start();
		addKeyListener(new MyKeyAdapter());
		setFocusable(true);
	}
	
	private class MyKeyAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			p.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			p.keyReleased(e);
		}
	}
	
	public void paint(Graphics g) {
		g = (Graphics2D) g;
	    g.drawImage(img_1, p.layer1, 0, null);
	    g.drawImage(img_2, p.layer2, 0, null);
	    g.drawImage(img_3, p.layer3, 0, null);
	    
	    g.drawImage(p.img, p.x, p.y, null);
	    
	    double v = (10000/Player.MAX_V) * p.v;
	    g.setColor(Color.WHITE);
	    Font font = new Font("Arial", Font.ITALIC, 20);
	    g.setFont(font);
	    g.drawString("Speed: " + v + " m/s", 100, 30);
	 
	    Iterator<Enemy> i = enemies.iterator();
	    while(i.hasNext()) {
	    	Enemy e = i.next();
	    	
	    	if(e.x >= 2500 || e.x <= -2500 )
	    	{
	    		i.remove();
	    	}
	    	else{
	    	e.move();
	    	g.drawImage(e.img, e.x, e.y, null);
	    	}
	   
	    }
	}
	
	public void actionPerformed(ActionEvent e) {
		p.move();
		repaint();
		testCollisionWithEnemies();
		testWin();
		//System.out.println(enemies.size());
	}

	private void testWin() {
		if (p.s > 200000){
			JOptionPane.showMessageDialog(null, "to infinity and beyond\n");
			System.exit(0);
		}
		
	}

	private void testCollisionWithEnemies() {
		Iterator<Enemy> i = enemies.iterator();
		while (i.hasNext()) {
			Enemy e = i.next();
			if(p.getRect().intersects(e.getRect())) {
				//i.remove();
				JOptionPane.showMessageDialog(null, "collision");
				System.exit(1);
			}
		}
		
	}

	@Override
	public void run() {
		while(true) {
			Random rand = new Random();
			try {
				Thread.sleep(rand.nextInt(2000));
				enemies.add(new Enemy(2000, rand.nextInt(600), rand.nextInt(5), this));
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
