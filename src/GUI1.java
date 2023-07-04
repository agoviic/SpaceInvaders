import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GUI1 extends JFrame {

	private static final long serialVersionUID = -4550734043778833766L;
	private JPanel contentPane;
	private JButton btnPlay;
	private JLabel lblVesion;
	private JLabel lblNewLabel;
	private JComboBox comboBox;
	private JButton btnExit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI1 frame = new GUI1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Space Invaders");
		setResizable(false);
		setBounds(300, 110, 742, 473);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnPlay());
		contentPane.add(getLblVesion());
		contentPane.add(getLblNewLabel());
		contentPane.add(getComboBox());
		contentPane.add(getBtnExit());
	}

	private JButton getBtnPlay() {
		if (btnPlay == null) {
			btnPlay = new JButton("PLAY");
			btnPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUI2 g2 = new GUI2();
					
					int speed = 2000;
					String difficulty = "";
					if (comboBox.getSelectedIndex() == 0) {
						speed = 700;
						difficulty = "easy";
					} else if (comboBox.getSelectedIndex() == 1) {
						speed = 850;
						difficulty = "normal";

					} else {
						speed = 1000;
						difficulty = "hard";

					}
					g2.setVisible(true);
					GUI1.this.dispose();
					contentPane.setVisible(false);
					MyThread m = new MyThread(g2.getPanel(), g2.getLblSpaceship(), speed, difficulty,
					g2.getLblBackground(), g2);
					m.start();
				}
			});
			btnPlay.setBackground(new Color(106, 90, 205));
			btnPlay.setForeground(Color.ORANGE);
			btnPlay.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
			btnPlay.setBounds(145, 324, 134, 36);
		}
		return btnPlay;
	}

	private JLabel getLblVesion() {
		if (lblVesion == null) {
			lblVesion = new JLabel("v1.0.0");
			lblVesion.setForeground(Color.ORANGE);
			lblVesion.setBounds(339, 416, 62, 14);
		}
		return lblVesion;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("New label");
			lblNewLabel.setIcon(new ImageIcon(GUI1.class.getResource("/img/startscreen.png")));
			lblNewLabel.setBounds(64, 11, 582, 281);
		}
		return lblNewLabel;
	}

	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "Easy", "Normal", "Hard" }));
			comboBox.setBackground(new Color(106, 90, 205));
			comboBox.setForeground(Color.ORANGE);
			comboBox.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 16));
			comboBox.setBounds(308, 324, 134, 36);
		}
		return comboBox;
	}
	private JButton getBtnExit() {
		if (btnExit == null) {
			btnExit = new JButton("EXIT\r\n");
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUI1.this.dispose();
				}
			});
			btnExit.setForeground(Color.ORANGE);
			btnExit.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
			btnExit.setBackground(new Color(106, 90, 205));
			btnExit.setBounds(468, 324, 134, 36);
		}
		return btnExit;
	}
}
