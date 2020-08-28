package rentcarTest.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RentListPanel extends JPanel{
	private JLabel lblNewLabel;
	public RentListPanel() {
		initComponents();
	}
	private void initComponents() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("대여 현황");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
		add(lblNewLabel, BorderLayout.CENTER);
		
	}

}
