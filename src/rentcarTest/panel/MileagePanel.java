package rentcarTest.panel;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import rentcarTest.Dao.service.MileageService;
import rentcarTest.dto.Mileage;
import rentcarTest.table.MileageTable;

@SuppressWarnings("serial")
public class MileagePanel extends JPanel {

	private MileageService service;
	private List<Mileage> lists;
	private JPanel pTable;
	private JScrollPane scrollPane;
	private MileageTable table;
	private JPanel pBtns;
	private JButton btnEdit;

	public MileagePanel() {
		initComponents();
	}

	private void initComponents() {
		service = new MileageService();
		lists = service.showMileage();
		initcomponents();
	}

	private void initcomponents() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		pTable = new JPanel();
		add(pTable);
		pTable.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		pTable.add(scrollPane, BorderLayout.CENTER);

		table = new MileageTable();
		table.setItems(lists);
		scrollPane.setViewportView(table);

		pBtns = new JPanel();
		add(pBtns);

		btnEdit = new JButton("추가 및 차감");
		pBtns.add(btnEdit);
	}
}
