package rentcarTest.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class CalFrame extends CalendarDataManager {
	JPanel calOpPanel;
	JButton BtnYearAgo;
	JButton BthMonthAgo;
	JLabel lblTable;
	JButton BtnNextMonth;
	JButton BtnNextYear;
	ListenForCalOpButtons lForCalOpButtons = new ListenForCalOpButtons();

	JPanel calPanel;
	JButton weekDaysName[];
	JButton dateBtns[][] = new JButton[6][7];
	listenForDateButs lForDateButs = new listenForDateButs();

	JPanel infoPanel;
	JLabel infoClock;

	JPanel memoPanel;
	JLabel selectedDate;
	JTextArea memoArea;
	JScrollPane memoAreaSP;
	JPanel memoSubPanel;
	JButton BtnSave;
	JButton BtnDel;
	JButton BtnClear;

	JPanel frameBottomPanel;
	final String WEEK_DAY_NAME[] = { "일", "월", "화", "수", "목", "금", "토" };
	final String title = "달력 메모장";
	private JFrame mainFrame;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new CalFrame();
			}
		});
	}

	public CalFrame() {

		mainFrame = new JFrame(title);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setSize(700, 400);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		calOpPanel = new JPanel();
		BtnYearAgo = new JButton("<<");
		BtnYearAgo.setToolTipText("Previous Year");
		BtnYearAgo.addActionListener(lForCalOpButtons);
		BthMonthAgo = new JButton("<");
		BthMonthAgo.setToolTipText("Previous Month");
		BthMonthAgo.addActionListener(lForCalOpButtons);
		lblTable = new JLabel("<html><table width=100><tr><th><font size=5>" + ((calMonth + 1) < 10 ? "&nbsp;" : "")
				+ (calMonth + 1) + " / " + calYear + "</th></tr></table></html>");
		BtnNextMonth = new JButton(">");
		BtnNextMonth.setToolTipText("Next Month");
		BtnNextMonth.addActionListener(lForCalOpButtons);
		BtnNextYear = new JButton(">>");
		BtnNextYear.setToolTipText("Next Year");
		BtnNextYear.addActionListener(lForCalOpButtons);
		///////////////// GridBagLayout//////////////////////////
		calOpPanel.setLayout(new GridBagLayout());
		GridBagConstraints calOpGC = new GridBagConstraints();
		calOpGC.gridx = 1;
		calOpGC.gridy = 1;
		calOpGC.gridwidth = 2;
		calOpGC.gridheight = 1;
		calOpGC.weightx = 1;
		calOpGC.weighty = 1;
		calOpGC.insets = new Insets(5, 5, 0, 0);
		calOpGC.anchor = GridBagConstraints.WEST;
		calOpGC.fill = GridBagConstraints.NONE;
		calOpGC.gridwidth = 3;
		calOpGC.gridx = 2;
		calOpGC.gridy = 1;
		calOpGC.anchor = GridBagConstraints.CENTER;
		calOpGC.gridwidth = 1;
		calOpGC.gridx = 1;
		calOpGC.gridy = 2;
		calOpPanel.add(BtnYearAgo, calOpGC);
		calOpGC.gridwidth = 1;
		calOpGC.gridx = 2;
		calOpGC.gridy = 2;
		calOpPanel.add(BthMonthAgo, calOpGC);
		calOpGC.gridwidth = 2;
		calOpGC.gridx = 3;
		calOpGC.gridy = 2;
		calOpPanel.add(lblTable, calOpGC);
		calOpGC.gridwidth = 1;
		calOpGC.gridx = 5;
		calOpGC.gridy = 2;
		calOpPanel.add(BtnNextMonth, calOpGC);
		calOpGC.gridwidth = 1;
		calOpGC.gridx = 6;
		calOpGC.gridy = 2;
		calOpPanel.add(BtnNextYear, calOpGC);
		/////////////////////////////////// 달력패널
		calPanel = new JPanel();
		weekDaysName = new JButton[7];
		for (int i = 0; i < CAL_WIDTH; i++) {
			weekDaysName[i] = new JButton(WEEK_DAY_NAME[i]);
			weekDaysName[i].setBorderPainted(false);
			weekDaysName[i].setContentAreaFilled(false);
			weekDaysName[i].setForeground(Color.WHITE);
			if (i == 0)
				weekDaysName[i].setBackground(new Color(200, 50, 50));
			else if (i == 6)
				weekDaysName[i].setBackground(new Color(50, 100, 200));
			else
				weekDaysName[i].setBackground(new Color(150, 150, 150));
			weekDaysName[i].setOpaque(true);
			weekDaysName[i].setFocusPainted(false);
			calPanel.add(weekDaysName[i]);
		}
		for (int i = 0; i < CAL_HEIGHT; i++) {
			for (int j = 0; j < CAL_WIDTH; j++) {
				dateBtns[i][j] = new JButton();
				dateBtns[i][j].setBorderPainted(false);
				dateBtns[i][j].setContentAreaFilled(false);
				dateBtns[i][j].setBackground(Color.WHITE);
				dateBtns[i][j].setOpaque(true);
				dateBtns[i][j].addActionListener(lForDateButs);
				calPanel.add(dateBtns[i][j]);
			}
		}
		calPanel.setLayout(new GridLayout(0, 7, 2, 2));
		calPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		showCalendar();
		//////////////////////////////////////
		infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		infoClock = new JLabel("", SwingConstants.RIGHT);
		infoClock.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		infoPanel.add(infoClock, BorderLayout.NORTH);
		////////////////////////////// 메모장
		memoPanel = new JPanel();
		memoPanel.setBorder(BorderFactory.createTitledBorder("할 일 메모"));
		memoArea = new JTextArea();
		memoArea.setLineWrap(true);
		memoArea.setWrapStyleWord(true);
		memoAreaSP = new JScrollPane(memoArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		readMemo();
		///////////// 메모 버튼
		memoSubPanel = new JPanel();
		BtnSave = new JButton("저장");
		BtnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					File f = new File("MemoData");
					if (!f.isDirectory())
						f.mkdir();

					String memo = memoArea.getText();
					if (memo.length() > 0) {
						BufferedWriter out = new BufferedWriter(
								new FileWriter("MemoData/" + calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1)
										+ (calDayOfMon < 10 ? "0" : "") + calDayOfMon + ".txt"));
						String str = memoArea.getText();
						out.write(str);
						out.close();
					}
				} catch (IOException e) {
				}
				showCalendar();
			}
		});
		BtnDel = new JButton("삭제");
		BtnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memoArea.setText("");
				File f = new File("MemoData/" + calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1)
						+ (calDayOfMon < 10 ? "0" : "") + calDayOfMon + ".txt");
				f.delete();
				showCalendar();
			}
		});
		BtnClear = new JButton("지우기");
		BtnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				memoArea.setText(null);
			}
		});
		memoSubPanel.add(BtnSave);
		memoSubPanel.add(BtnDel);
		memoSubPanel.add(BtnClear);
		memoPanel.setLayout(new BorderLayout());
		memoPanel.add(memoAreaSP, BorderLayout.CENTER);
		memoPanel.add(memoSubPanel, BorderLayout.SOUTH);
		///////////////////////////////
		JPanel frameSubPanelWest = new JPanel();
		Dimension calOpPanelSize = calOpPanel.getPreferredSize();
		calOpPanelSize.height = 90;
		calOpPanel.setPreferredSize(calOpPanelSize);
		frameSubPanelWest.setLayout(new BorderLayout());
		frameSubPanelWest.add(calOpPanel, BorderLayout.NORTH);
		frameSubPanelWest.add(calPanel, BorderLayout.CENTER);

		JPanel frameSubPanelEast = new JPanel();
		Dimension infoPanelSize = infoPanel.getPreferredSize();
		infoPanelSize.height = 65;
		infoPanel.setPreferredSize(infoPanelSize);
		frameSubPanelEast.setLayout(new BorderLayout());
		frameSubPanelEast.add(infoPanel, BorderLayout.NORTH);
		frameSubPanelEast.add(memoPanel, BorderLayout.CENTER);

		Dimension frameSubPanelWestSize = frameSubPanelWest.getPreferredSize();
		frameSubPanelWestSize.width = 410;
		frameSubPanelWest.setPreferredSize(frameSubPanelWestSize);

		frameBottomPanel = new JPanel();

		mainFrame.setLayout(new BorderLayout());
		mainFrame.add(frameSubPanelWest, BorderLayout.WEST);
		mainFrame.add(frameSubPanelEast, BorderLayout.CENTER);
		mainFrame.add(frameBottomPanel, BorderLayout.SOUTH);
		mainFrame.setVisible(true);

		focusToday();
	}

	private void focusToday() {
		if (today.get(Calendar.DAY_OF_WEEK) == 1)
			dateBtns[today.get(Calendar.WEEK_OF_MONTH)][today.get(Calendar.DAY_OF_WEEK) - 1].requestFocusInWindow();
		else
			dateBtns[today.get(Calendar.WEEK_OF_MONTH) - 1][today.get(Calendar.DAY_OF_WEEK) - 1].requestFocusInWindow();
	}

	private void readMemo() {
		try {
			File f = new File("MemoData/" + calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1)
					+ (calDayOfMon < 10 ? "0" : "") + calDayOfMon + ".txt");
			if (f.exists()) {
				BufferedReader in = new BufferedReader(
						new FileReader("MemoData/" + calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1)
								+ (calDayOfMon < 10 ? "0" : "") + calDayOfMon + ".txt"));
				String memoAreaText = new String();
				while (true) {
					String tempStr = in.readLine();
					if (tempStr == null)
						break;
					memoAreaText = memoAreaText + tempStr + System.getProperty("line.separator");
				}
				memoArea.setText(memoAreaText);
				in.close();
			} else
				memoArea.setText("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showCalendar() {
		for (int i = 0; i < CAL_HEIGHT; i++) {
			for (int j = 0; j < CAL_WIDTH; j++) {
				String fontColor = "black";
				if (j == 0)
					fontColor = "red";
				else if (j == 6)
					fontColor = "blue";

				File f = new File("MemoData/" + calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1)
						+ (calDates[i][j] < 10 ? "0" : "") + calDates[i][j] + ".txt");
				if (f.exists()) {
					dateBtns[i][j]
							.setText("<html><b><font color=" + fontColor + ">" + calDates[i][j] + "</font></b></html>");
				} else
					dateBtns[i][j].setText("<html><font color=" + fontColor + ">" + calDates[i][j] + "</font></html>");

				JLabel todayMark = new JLabel("<html><font color=green>*</html>");
				dateBtns[i][j].removeAll();
				if (calMonth == today.get(Calendar.MONTH) && calYear == today.get(Calendar.YEAR)
						&& calDates[i][j] == today.get(Calendar.DAY_OF_MONTH)) {
					dateBtns[i][j].add(todayMark);
					dateBtns[i][j].setToolTipText("Today");
				}

				if (calDates[i][j] == 0)
					dateBtns[i][j].setVisible(false);
				else
					dateBtns[i][j].setVisible(true);
			}
		}
	}

	private class ListenForCalOpButtons implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == BtnYearAgo)
				moveMonth(-12);
			else if (e.getSource() == BthMonthAgo)
				moveMonth(-1);
			else if (e.getSource() == BtnNextMonth)
				moveMonth(1);
			else if (e.getSource() == BtnNextYear)
				moveMonth(12);

			lblTable.setText("<html><table width=100><tr><th><font size=5>" + ((calMonth + 1) < 10 ? "&nbsp;" : "")
					+ (calMonth + 1) + " / " + calYear + "</th></tr></table></html>");
			showCalendar();
		}
	}

	private class listenForDateButs implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int k = 0, l = 0;
			for (int i = 0; i < CAL_HEIGHT; i++) {
				for (int j = 0; j < CAL_WIDTH; j++) {
					if (e.getSource() == dateBtns[i][j]) {
						k = i;
						l = j;
					}
				}
			}

			if (!(k == 0 && l == 0))
				calDayOfMon = calDates[k][l];

			cal = new GregorianCalendar(calYear, calMonth, calDayOfMon);
			readMemo();
		}
	}

}