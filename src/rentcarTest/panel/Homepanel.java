package rentcarTest.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Homepanel extends JPanel {
	private JLabel lblNewLabel;
	Calendar today = Calendar.getInstance();
	private int ThisMonth;
	private int ThisDate;
	private String Month;
	private String Date;
	private AnalogClockPanel pClock;

	public Homepanel() {
		initComponents();
	}

	private void initComponents() {
		setPreferredSize(new Dimension(650, 661));
		setBackground(Color.WHITE);
		setLayout(null);

		ThisMonth = today.get(Calendar.MONTH) + 1;
		ThisDate = today.get(Calendar.DATE);
		Month = Integer.toString(ThisMonth);
		Date = Integer.toString(ThisDate);

		lblNewLabel = new JLabel(Month + "월 " + Date + "일 " /* + Hour + "시 " + Minute +"분 " */);
		lblNewLabel.setFont(new Font("인터파크고딕 M", Font.PLAIN, 35));
		lblNewLabel.setBounds(237, 115, 458, 123);
		add(lblNewLabel);

		pClock = new AnalogClockPanel();
		pClock.setBounds(140, 201, 338, 300);
		add(pClock);

	}
}