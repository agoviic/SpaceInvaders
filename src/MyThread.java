import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyThread extends Thread {

	private JPanel panel;
	private JLabel[] battleship;
	private JLabel spaceship;
	private int speed;
	private String difficulty;
	private JLabel lblBackground;
	private GUI2 g;
	int counter = 0;
	int movement = 0;

	public MyThread(JPanel panel, JLabel spaceship, int speed, String difficulty, JLabel lblBackground, GUI2 g) {
		this.g = g;
		this.spaceship = spaceship;
		this.panel = panel;
		this.speed = speed;
		this.difficulty = difficulty;
		this.lblBackground = lblBackground;
		setUp();
	}

	// Postavljanje battleshipova
	public void setUp() {
		if (difficulty.equalsIgnoreCase("hard")) {
			battleship = new JLabel[42];
			movement = 10;

		} else if (difficulty.equalsIgnoreCase("normal")) {
			battleship = new JLabel[28];
			movement = 15;

		} else {
			battleship = new JLabel[14];
			movement = 20;
		}
		int x = panel.getX() + 22;
		int y = 16;
		for (int i = 0; i < battleship.length; i++) {
			JLabel j = new JLabel();
			if (i == 14 || i == 28) {
				y += 64;
				x = panel.getX() + 22;
			}
			j.setBounds(x, y, 64, 64);
			j.setIcon(new ImageIcon(GUI2.class.getResource("/img/battleship.png")));
			x += 64;
			j.setVisible(false);
			battleship[i] = j;
			panel.add(battleship[i]);
		}
		panel.remove(lblBackground);
		panel.add(lblBackground);
		panel.repaint();
		panel.revalidate();

	}

	int x = 0;
	int y = 0;
	boolean end = false;
	Thread shooting = new Thread();

	// Postavljanje i pomijeranje battleshipova
	// U zavisnosti od rezultata otvara se novi frame koji saopstava ishod
	@Override
	public void run() {
		counter = battleship.length;
		for (int i = 0; i < battleship.length; i++) {
			JLabel j = battleship[i];
			j.setVisible(true);
			try {
				sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}

		x = spaceship.getX() + spaceship.getWidth() / 2;
		y = spaceship.getY();
		ThreadShooting t = new ThreadShooting(counter, panel, spaceship, lblBackground, battleship);
		t.start();

		while (!end) {
			for (int i = 0; i < battleship.length; i++) {
				JLabel j = battleship[i];
				int x = j.getX();
				int y = j.getY();

				if (spaceship.getY() <= battleship[i].getY() + battleship[i].getHeight() && battleship[i].isVisible()) {
					end = true;
					t.setCounter(0);
					break;
				}
				if (t.getCounter() == 0) {
					break;
				}
				j.setBounds(x, y + movement, 64, 64);
			}
			try {
				sleep(speed);
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}
			if (!end && t.getCounter()==0) {
				 counter = t.getCounter();
				break;
			}
		}
		if (counter == 0 || end == true) {
			panel.removeAll();
			JLabel j = new JLabel();
			j.setIcon(new ImageIcon(GUI2.class.getResource("/img/startscreen.png")));
			j.setBounds(180, 11, 582, 281);
			JLabel message = new JLabel();
			if (counter <= 0) {
				message.setText("Another day, another victory. Well played soldier!");
				message.setBounds(panel.getWidth() / 2 - 250, 350, 600, 58);

			} else {
				counter = t.getCounter();
				t.setCounter(0);
				message.setText("Mission Failed! We will get them next time.");
				message.setBounds(panel.getWidth() / 2 - 225, 350, 600, 58);

			}
			message.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 24));
			message.setForeground(Color.ORANGE);
			JButton back = new JButton("Main Menu");
			back.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 20));
			back.setForeground(Color.ORANGE);
			back.setBackground(new Color(106, 90, 205));
			back.setBounds(400, 450, 155, 35);
			GUI1 g1 = new GUI1();
			GUI2 g2 = new GUI2();
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					g1.setVisible(true);
					g.dispose();
				}
			});
			panel.add(j);
			panel.add(message);
			panel.add(back);
		}
		panel.repaint();
		panel.revalidate();

	}

}