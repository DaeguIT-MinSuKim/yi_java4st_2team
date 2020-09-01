package rentcarTest.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
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
import rentcarTest.dto.Customer;
import rentcarTest.dto.Rent;
import rentcarTest.table.RentTable;
import com.toedter.calendar.JDateChooser;

public class RentListPanel extends JPanel implements ActionListener, ItemListener {
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
	private JDateChooser dateRent;
	private JDateChooser dateReturn;
	
	public RentListPanel() {
		setPreferredSize(new Dimension(650, 650));
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
		chckbxRent.addItemListener(this);
		
		JLabel lblRentDate = new JLabel("대여일자");
		pSearch_check.add(lblRentDate);
		
		dateRent = new JDateChooser();
		pSearch_check.add(dateRent);
		
		JLabel lblReturnDate = new JLabel("반납일자");
		pSearch_check.add(lblReturnDate);
		
		dateReturn = new JDateChooser();
		pSearch_check.add(dateReturn);
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

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == chckbxRent) {
			selectSearchCheckedRent(e);
		}

	}

	// check_box - 대여중
	private void selectSearchCheckedRent(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			lists = service.showRentsIng();
			table.setItems(lists);
		} else {
			lists = service.showRents();
			table.setItems(lists);
		}
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
		
		Car car = new Car();
		Customer ctm = new Customer();

		rentListFind.setCar_no(car);
		rentListFind.setCtm_no(ctm);
		
		Object cmbCateText = cmbCate.getSelectedItem();
		
		if (dateRent.getDate() != null) {
			Calendar rent_date = Calendar.getInstance();
			rent_date.clear();
			rent_date.setTime(dateRent.getDate());
			

			Calendar return_date = Calendar.getInstance();
			return_date.clear();
			return_date.setTime(dateReturn.getDate());
			
			rentListFind.setRent_date(rent_date.getTime());
			rentListFind.setReturn_date(return_date.getTime());
			
			service.showDateRents(rentListFind);
			System.out.println("dateRent.getDate()"+ dateRent.getDate());
			System.out.println(rentListFind);
		}
		
		if (cmbCateText.equals("검색")) {
			rentFindList = service.showRents();
			
		} else if (searchText.equals("")) {
			JOptionPane.showMessageDialog(null, "검색할 내용을 입력해주세요.");
		} else {
			if (cmbCateText.equals("차번호")) {
				car.setCarNo(searchText);
				rentFindList = service.findRents(rentListFind);
			}
			if (cmbCateText.equals("성명")) {
				ctm.setName(searchText);
				rentFindList = service.findRents(rentListFind);
			}
			if (cmbCateText.equals("연락처")) {
				ctm.setTel(searchText);
				rentFindList = service.findRents(rentListFind);
			}
		}
		table.setItems(rentFindList);
	}
}
