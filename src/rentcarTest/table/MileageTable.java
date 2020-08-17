package rentcarTest.table;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


public class MileageTable extends JTable{
	public MileageTable() {
		initComponents();
	}
	private void initComponents() {
		setBackground(UIManager.getColor("CheckBox.background"));
	}
}