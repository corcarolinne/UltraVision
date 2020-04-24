import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class DashboardView extends JFrame {

	private JPanel contentPane;
	private JTextField searchTextField;
	private JButton searchButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardView frame = new DashboardView();
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
	public DashboardView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 408);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(248, 248, 255));
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel brandLabel = new JLabel("Ultra Vision \u00AE ");
		brandLabel.setForeground(Color.DARK_GRAY);
		brandLabel.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 26));
		GridBagConstraints gbc_brandLabel = new GridBagConstraints();
		gbc_brandLabel.gridwidth = 2;
		gbc_brandLabel.insets = new Insets(0, 0, 5, 5);
		gbc_brandLabel.anchor = GridBagConstraints.WEST;
		gbc_brandLabel.gridx = 4;
		gbc_brandLabel.gridy = 1;
		contentPane.add(brandLabel, gbc_brandLabel);
		
		searchTextField = new JTextField();
		searchTextField.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 16));
		searchTextField.setText("Search...");
		GridBagConstraints gbc_searchTextField = new GridBagConstraints();
		gbc_searchTextField.gridwidth = 2;
		gbc_searchTextField.insets = new Insets(0, 0, 0, 5);
		gbc_searchTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchTextField.gridx = 4;
		gbc_searchTextField.gridy = 3;
		contentPane.add(searchTextField, gbc_searchTextField);
		searchTextField.setColumns(10);
		
		searchButton = new JButton("");
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		searchButton.setIcon(new ImageIcon(DashboardView.class.getResource("/com/sun/javafx/scene/control/skin/caspian/fxvk-enter-button.png")));
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_searchButton.insets = new Insets(0, 0, 0, 5);
		gbc_searchButton.gridx = 6;
		gbc_searchButton.gridy = 3;
		contentPane.add(searchButton, gbc_searchButton);
	}

}
