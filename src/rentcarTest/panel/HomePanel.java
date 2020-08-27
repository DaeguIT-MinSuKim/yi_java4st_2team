package rentcarTest.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class HomePanel extends JPanel{

	static JLabel label;
	static JButton preButton, nextButton;
	static JTable table;
	static JComboBox combo;
	static Container pane;
	static DefaultTableModel mtblCalendar;
	static JScrollPane scroll;
	static JPanel panel;
	static int realYear, realMonth, realDay, currentYear, currentMonth;

	public HomePanel() {
		initComponents();
	}
	
	private void initComponents() {
		// Create controls
		label = new JLabel("January");
		combo = new JComboBox();
		preButton = new JButton("prev");
		nextButton = new JButton("next");
		mtblCalendar = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		table = new JTable(mtblCalendar);
		scroll = new JScrollPane(table);
		panel = new JPanel(null);

		// Set border
		panel.setBorder(BorderFactory.createTitledBorder("Calendar"));

		// Register action listeners
		preButton.addActionListener(new btnPrev_Action());
		nextButton.addActionListener(new btnNext_Action());
		combo.addActionListener(new cmbYear_Action());

		// Add controls to pane
		pane.add(panel);
		panel.add(label);
		panel.add(combo);
		panel.add(preButton);
		panel.add(nextButton);
		panel.add(scroll);

		// Set bounds
		panel.setBounds(0, 0, 320, 335);
		label.setBounds(160 - label.getPreferredSize().width / 2, 25, 100, 25);
		combo.setBounds(230, 305, 80, 20);
		preButton.setBounds(10, 25, 50, 25);
		nextButton.setBounds(260, 25, 50, 25);
		scroll.setBounds(10, 50, 300, 250);

		// Get real month/year
		GregorianCalendar cal = new GregorianCalendar();
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH);
		realMonth = cal.get(GregorianCalendar.MONTH);
		realYear = cal.get(GregorianCalendar.YEAR);
		currentMonth = realMonth;
		currentYear = realYear;

		// Add headers
		String[] headers = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		for (int i = 0; i < 7; i++) {
			mtblCalendar.addColumn(headers[i]);
		}

		table.getParent().setBackground(table.getBackground());

		// No resize/reorder
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);

		// Single cell selection
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Set row/column count
		table.setRowHeight(38);
		mtblCalendar.setColumnCount(7);
		mtblCalendar.setRowCount(6);

		// Populate table
		for (int i = realYear - 100; i <= realYear + 100; i++) {
			combo.addItem(String.valueOf(i));
		}

		// Refresh calendar
		refreshCalendar(realMonth, realYear);
	}

	public static void refreshCalendar(int month, int year) {
		// Variables
		String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		int nod, som;

		// Allow/disallow buttons
		preButton.setEnabled(true);
		nextButton.setEnabled(true);
		if (month == 0 && year <= realYear - 10) {
			preButton.setEnabled(false);
		} // Too early
		if (month == 11 && year >= realYear + 100) {
			nextButton.setEnabled(false);
		} // Too late
		label.setText(months[month]);
		label.setBounds(160 - label.getPreferredSize().width / 2, 25, 180, 25);
		combo.setSelectedItem(String.valueOf(year));

		// Clear table
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				mtblCalendar.setValueAt(null, i, j);
			}
		}

		// Get first day of month and number of days
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);

		// Draw calendar
		for (int i = 1; i <= nod; i++) {
			int row = (i + som - 2) / 7;
			int column = (i + som - 2) % 7;
			mtblCalendar.setValueAt(i, row, column);
		}

		table.setDefaultRenderer(table.getColumnClass(0), new tblCalendarRenderer());
	}

	static class tblCalendarRenderer extends DefaultTableCellRenderer {

		public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
				int row, int column) {
			super.getTableCellRendererComponent(table, value, selected, focused, row, column);
			if (column == 0 || column == 6) {
				setBackground(new Color(255, 220, 220));
			} else {
				setBackground(new Color(255, 255, 255));
			}
			if (value != null) {
				if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth
						&& currentYear == realYear) {
					setBackground(new Color(220, 220, 255));
				}
			}
			setBorder(null);
			setForeground(Color.black);
			return this;
		}
	}

	static class btnPrev_Action implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (currentMonth == 0) {
				currentMonth = 11;
				currentYear -= 1;
			} else {
				currentMonth -= 1;
			}
			refreshCalendar(currentMonth, currentYear);
		}
	}

	static class btnNext_Action implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (currentMonth == 11) {
				currentMonth = 0;
				currentYear += 1;
			} else {
				currentMonth += 1;
			}
			refreshCalendar(currentMonth, currentYear);
		}
	}

	static class cmbYear_Action implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (combo.getSelectedItem() != null) {
				String b = combo.getSelectedItem().toString();
				currentYear = Integer.parseInt(b);
				refreshCalendar(currentMonth, currentYear);
			}
		}
	}
}
