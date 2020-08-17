package rentcarTest.panel.popup;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Font;

public class EditCustomerPanel extends JPanel {
	private JLabel lblNewLabel;
	private JPanel panel;

	/**
	 * Create the panel.
	 */
	public EditCustomerPanel() {

		initComponents();
	}
	private void initComponents() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		lblNewLabel = new JLabel("고객 추가");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel);
		
		panel = new JPanel();
		add(panel);
	}

}
