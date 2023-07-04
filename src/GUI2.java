
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class GUI2 extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblBackground;
	private JLabel lblSpaceship;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI2 frame = new GUI2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void close() {
		GUI2 g = new GUI2();
		g.dispose();
	}

	/**
	 * Create the frame.
	 */
	public GUI2() {
		setTitle("Space Invaders");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(170, 60, 958, 646);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanel());
	}

	JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.BLACK);
			panel.setBounds(0, 0, 942, 607);
			panel.setLayout(null);
			panel.setOpaque(true);
			panel.add(getLblSpaceship());
			panel.add(getLblBackground());
		}
		return panel;
	}

	JLabel getLblBackground() {
		if (lblBackground == null) {
			lblBackground = new JLabel("");
			lblBackground.setIcon(new ImageIcon(GUI2.class.getResource("/img/space-background.jpg")));
			lblBackground.setBounds(0, 0, 940, 607);
		}
		return lblBackground;
	}

	int currentX = 0;
	int currentY = 0;
	int x = 0;
	int y = 0;

	JLabel getLblSpaceship() {
		if (lblSpaceship == null) {
			lblSpaceship = new JLabel("");
			lblSpaceship.setBounds(450, 543, 64, 64);
			currentX = lblSpaceship.getX();
			currentY = lblSpaceship.getY();
			lblSpaceship.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						if (currentX > panel.getX()) {
							currentX -= 25;
						}
						lblSpaceship.setBounds(currentX, currentY, 64, 64);
					}
					if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						if (lblSpaceship.getBounds().getX()
								+ lblSpaceship.getBounds().getWidth() < panel.getBounds().getWidth() - 14) {
							currentX += 25;
						}
						lblSpaceship.setBounds(currentX, currentY, 64, 64);
					}
					

				}
			});
			lblSpaceship.setFocusable(true);
			lblSpaceship.requestFocus();
			lblSpaceship.setIcon(new ImageIcon(GUI2.class.getResource("/img/spaceship.png")));
		}
		return lblSpaceship;
	}
}
