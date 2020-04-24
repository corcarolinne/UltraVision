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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DashboardView extends JFrame {

	private JPanel contentPane;
	private JTextField searchTextField;
	private JButton searchButton;
	private JComboBox comboBox;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JTable table;
	private JLabel lblNewLabel;
	private JTable table_1;

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
		setBounds(100, 100, 588, 651);
		contentPane = new JPanel();
		contentPane.setToolTipText("New");
		contentPane.setForeground(new Color(248, 248, 255));
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 185, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel brandLabel = new JLabel("Ultra Vision \u00AE ");
		brandLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		brandLabel.setHorizontalAlignment(SwingConstants.CENTER);
		brandLabel.setForeground(Color.DARK_GRAY);
		brandLabel.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 26));
		GridBagConstraints gbc_brandLabel = new GridBagConstraints();
		gbc_brandLabel.gridwidth = 5;
		gbc_brandLabel.insets = new Insets(0, 0, 5, 5);
		gbc_brandLabel.anchor = GridBagConstraints.WEST;
		gbc_brandLabel.gridx = 5;
		gbc_brandLabel.gridy = 1;
		contentPane.add(brandLabel, gbc_brandLabel);
		
		searchTextField = new JTextField();
		searchTextField.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 16));
		searchTextField.setText("Search...");
		GridBagConstraints gbc_searchTextField = new GridBagConstraints();
		gbc_searchTextField.gridwidth = 5;
		gbc_searchTextField.insets = new Insets(0, 0, 5, 5);
		gbc_searchTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchTextField.gridx = 3;
		gbc_searchTextField.gridy = 3;
		contentPane.add(searchTextField, gbc_searchTextField);
		searchTextField.setColumns(10);
		
		btnNewButton_1 = new JButton("Add Return");
		btnNewButton_1.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnNewButton = new JButton("Add Rent");
		btnNewButton.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		searchButton = new JButton("");
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		searchButton.setIcon(new ImageIcon(DashboardView.class.getResource("/com/sun/javafx/scene/control/skin/caspian/fxvk-enter-button.png")));
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_searchButton.insets = new Insets(0, 0, 5, 5);
		gbc_searchButton.gridx = 9;
		gbc_searchButton.gridy = 3;
		contentPane.add(searchButton, gbc_searchButton);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 16));
		comboBox.setToolTipText("New");
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Title", "Customer"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 5;
		gbc_comboBox.gridy = 5;
		contentPane.add(comboBox, gbc_comboBox);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 6;
		gbc_btnNewButton.gridy = 5;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 7;
		gbc_btnNewButton_1.gridy = 5;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
		
		lblNewLabel = new JLabel("Available Titles");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 7;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
	}	

}
