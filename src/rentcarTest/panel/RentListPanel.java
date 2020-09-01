package rentcarTest.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import rentcarTest.Dao.service.RentService;
import rentcarTest.dto.Car;
import rentcarTest.dto.Kind;
import rentcarTest.dto.Rent;
import rentcarTest.table.RentTable;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RentListPanel extends JPanel implements ActionListener{
	private JTextField tfSearch;
	private RentTable table;
	
	private RentService service;
	private List<Rent> lists;
	private JComboBox<String> cmbCate;
	private JPanel pSearch_button;
	private JCheckBox chckbxRent;
	private JPanel pSearch_check;
	private JPanel pSearch;
	private JButton btnSearch;
	private JPanel pTable;
	private JScrollPane scrollPane;
	private JPanel pBtns;
	private JButton btnUpdate;
	private JButton btnRent;
	private List<Rent> rentFindList;
	
	public RentListPanel() {
		setPreferredSize(new Dimension(870, 650));
		service = new RentService();
		lists = service.showRents();
		
		initComponents();
		
		table.setItems(lists);
	}
	
	private void initComponents() {
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pTitle = new JPanel();
		pTitle.setBackground(new Color(255, 255, 255));
		add(pTitle);
		pTitle.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("대여 기록");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		pTitle.add(lblNewLabel, BorderLayout.CENTER);
		
		pSearch = new JPanel();
		add(pSearch);
		pSearch.setLayout(new BoxLayout(pSearch, BoxLayout.X_AXIS));
		
		pSearch_check = new JPanel();
		pSearch.add(pSearch_check);
		pSearch_check.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		chckbxRent = new JCheckBox("대여중");
		pSearch_check.add(chckbxRent);
		
		pSearch_button = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pSearch_button.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		pSearch.add(pSearch_button);
		
		cmbCate = new JComboBox<String>();
		setSearchCate();
		pSearch_button.add(cmbCate);
		
		tfSearch = new JTextField();
		tfSearch.setColumns(10);
		pSearch_button.add(tfSearch);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		pSearch_button.add(btnSearch);
		
		pTable = new JPanel();
		add(pTable);
		pTable.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		pTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new RentTable();
		scrollPane.setViewportView(table);
		
		pBtns = new JPanel();
		add(pBtns);
		
		btnUpdate = new JButton("수정");
		pBtns.add(btnUpdate);
		
		btnRent = new JButton("대여");
		pBtns.add(btnRent);
		
	}

	// 검색 분류
	private void setSearchCate() {
		String[] items = {"검색", "차번호", "성명", "연락처"};
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(items);
		cmbCate.setModel(model);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			actionPerformedBtnSearch(e);
		}
	}
	protected void actionPerformedBtnSearch(ActionEvent e) {
		rentFindList = null;
		String searchText = tfSearch.getText().trim();
		Rent rentListFind = new Rent();
		
		Object cmbCateText = cmbCate.getSelectedItem();
		
		if (cmbCateText.equals("검색")) {
		} else if (searchText.equals("")) {
			JOptionPane.showMessageDialog(null, "검색할 내용을 입력해주세요.");
		} else {
			if (cmbCateText.equals("차번호")) {
				rentListFind.setCar_no(new Car(searchText));
				rentFindList = service.findRents(rentListFind);
			}
		}
		table.setItems(rentFindList);
	}
}
