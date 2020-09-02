package rentcarTest.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import rentcarTest.Dao.service.MileageService;
import rentcarTest.dto.Mileage;
import rentcarTest.popup.EditMileagePopup;
import rentcarTest.table.MileageTable;

@SuppressWarnings("serial")
public class MileagePanel extends JPanel implements ActionListener {

	private MileageService service = new MileageService();
	private List<Mileage> lists = service.showMileage();
	private JPanel pTable;
	private JScrollPane scrollPane;
	private MileageTable table;
	private JPanel pBtns;
	private JButton btnEdit;
	private JPanel pTitle;
	private JLabel lblTitle;

	public MileagePanel() {

		initcomponents();

		scrollPane.setViewportView(table);
	}

	private void initcomponents() {
		setPreferredSize(new Dimension(870, 661));
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		pTitle = new JPanel();
		pTitle.setBackground(Color.WHITE);
		pTitle.setPreferredSize(new Dimension(50, 100));
		add(pTitle);
		pTitle.setLayout(new BoxLayout(pTitle, BoxLayout.X_AXIS));

		lblTitle = new JLabel("마일리지");
		lblTitle.setFont(new Font("굴림", Font.BOLD, 30));
		pTitle.add(lblTitle);

		pTable = new JPanel();
		add(pTable);
		pTable.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		pTable.add(scrollPane, BorderLayout.CENTER);

		table = new MileageTable();
		table.setItems(lists);
		scrollPane.setViewportView(table);

		pBtns = new JPanel();
		add(pBtns);

		btnEdit = new JButton("추가 및 차감");
		btnEdit.addActionListener(this);
		pBtns.add(btnEdit);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEdit) {
			EditMileagePopup milePopup = new EditMileagePopup();
			milePopup.setMileList(this);
			milePopup.setVisible(true);
		}
	}

	public void insertMile(Mileage item) {
		service = new MileageService();
		lists = service.showMileage();
		table.setItems(lists);
	}
}