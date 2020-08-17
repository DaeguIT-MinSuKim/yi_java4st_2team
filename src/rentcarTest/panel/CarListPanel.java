package rentcarTest.panel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class CarListPanel  extends JPanel{
	private JLabel lblNewLabel;
	public CarListPanel() {
		initComponents();
	}
	private void initComponents() {
		
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("차량 현황 ");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
		add(lblNewLabel, BorderLayout.CENTER);
	}

}
