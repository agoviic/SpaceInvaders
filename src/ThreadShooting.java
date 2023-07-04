import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ThreadShooting extends Thread {
	private int counter;
	private JPanel panel;
	private JLabel spaceship;
	private JLabel lblBackground;
	private JLabel[] battleship;

	public ThreadShooting(int counter, JPanel panel, JLabel spaceship, JLabel lblBackground, JLabel[] battleship) {
		this.counter = counter;
		this.panel = panel;
		this.spaceship = spaceship;
		this.lblBackground = lblBackground;
		this.battleship = battleship;
	}

	int x = 0;
	int y = 0;

	// generisanje, pomeranje i usred kolizije ili izlaska iz panela brisanje
	// buttona koji predstavljaju metkove
	@Override
	public void run() {
		// counter = battleship.length;
		while (counter > 0) {
			JButton b = new JButton();
			b.setBackground(Color.WHITE);
			x = spaceship.getX() + spaceship.getWidth() / 2;
			y = spaceship.getY();
			while (y > panel.getY() - b.getHeight()) {
				if (checkForCollision(b.getX(), b.getY(), battleship, b)) {
					counter--;
					panel.remove(b);
				}
				b.setBounds(x, y, 5, 10);
				y -= 50;
				panel.add(b);
				panel.add(lblBackground);
				try {
					sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
				panel.revalidate();
			}
			b.setVisible(false);
			panel.remove(b);
		}
	}

	// Pomocna metoda koja provjerava da li je metak pogodio battleship
	public boolean checkForCollision(int x, int y, JLabel[] battleships, JButton b) {
		for (int i = 0; i < battleship.length; i++) {
			if (x >= battleship[i].getX() && x <= battleship[i].getX() + battleship[i].getWidth()
					&& y <= battleship[i].getY() + battleship[i].getHeight() && battleship[i].isVisible()
					&& b.isVisible()) {
				battleship[i].setVisible(false);
				b.setVisible(false);
				return true;
			}
		}
		return false;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

}
