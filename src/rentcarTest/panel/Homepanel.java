package rentcarTest.panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

import analogclock.AnalogClock.TickTimerTask;

public class Homepanel extends JPanel {
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblToday;
	Calendar today = Calendar.getInstance();
	private int ThisMonth;
	private int ThisDate;
	private String Month;
	private String Date;

	private GregorianCalendar m_calendar;
	private int[] x = new int[2];
	private int[] y = new int[2];
	private java.util.Timer clocktimer = new java.util.Timer();

	private TimeZone clockTimeZone = TimeZone.getDefault();
	private JPanel panel;
	private JPanel pClock;

	public Homepanel() {
		initComponents();
	}

	private void initComponents() {
		setPreferredSize(new Dimension(650, 661));
		clocktimer.schedule(new TickTimerTask(), 0, 1000);
		setBackground(Color.WHITE);
		setLayout(null);

		ThisMonth = today.get(Calendar.MONTH) + 1;
		ThisDate = today.get(Calendar.DATE);
		Month = Integer.toString(ThisMonth);
		Date = Integer.toString(ThisDate);

		lblNewLabel = new JLabel(Month + "월 " + Date + "일 " /* + Hour + "시 " + Minute +"분 " */);
		lblNewLabel.setFont(new Font("인터파크고딕 M", Font.PLAIN, 35));
		lblNewLabel.setBounds(236, 163, 458, 123);
		add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 10, 10);
		add(panel);
		
		pClock = new JPanel();
		panel.add(pClock);
	}

	public AnalogClock() {
		this.setPreferredSize(new Dimension(210, 210));
		this.setMinimumSize(new Dimension(210, 210));

		clocktimer.schedule(new TickTimerTask(), 0, 1000);
	}

	public void paint(Graphics g) {

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		drawCardinals((Graphics2D) g);
		drawHands((Graphics2D) g);

	}

	void clockMinutes(int startRadius, int endRadius, double theta) {
		theta -= Math.PI / 2;
		x[0] = (int) (getWidth() / 2 + startRadius * Math.cos(theta));
		y[0] = (int) (getHeight() / 2 + startRadius * Math.sin(theta));
		x[1] = (int) (getWidth() / 2 + endRadius * Math.cos(theta));
		y[1] = (int) (getHeight() / 2 + endRadius * Math.sin(theta));
	}

	void drawCardinals(Graphics2D g) {
		g.setStroke(new BasicStroke(12));
		g.setColor(Color.black);

		for (double theta = 0; theta < Math.PI * 2; theta += Math.PI / 6) {
			clockMinutes(100, 100, theta);
			g.drawPolyline(x, y, 2);
		}
	}

	public void drawHands(Graphics2D g) {
		double h = 2 * Math.PI * (m_calendar.get(Calendar.HOUR));
		double m = 2 * Math.PI * (m_calendar.get(Calendar.MINUTE));
		double s = 2 * Math.PI * (m_calendar.get(Calendar.SECOND));

		g.setStroke(new BasicStroke(9));

		clockMinutes(0, 55, h / 12 + m / (60 * 12));
		g.setColor(Color.BLACK);
		g.drawPolyline(x, y, 2);

		clockMinutes(0, 70, m / 60 + s / (60 * 60));
		g.setColor(Color.black);
		g.drawPolyline(x, y, 2);

		clockMinutes(0, 70, s / 60);
		g.setColor(Color.red);
		g.drawPolyline(x, y, 2);

		g.fillOval(getWidth() / 2 - 8, getHeight() / 2 - 8, 16, 16);
	}

//method to update/refresh the clock every second
	class TickTimerTask extends TimerTask {
		public void run() {
			m_calendar = (GregorianCalendar) GregorianCalendar.getInstance(clockTimeZone);
			repaint();
		}
	}
}