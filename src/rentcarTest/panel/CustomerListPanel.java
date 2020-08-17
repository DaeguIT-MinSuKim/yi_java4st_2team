package rentcarTest.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;

public class CustomerListPanel extends JPanel {
	private JLabel lblNewLabel;
	public CustomerListPanel() {
		initComponents();
	}
	private void initComponents() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("고객 명단");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
		add(lblNewLabel, BorderLayout.CENTER);
	}
}