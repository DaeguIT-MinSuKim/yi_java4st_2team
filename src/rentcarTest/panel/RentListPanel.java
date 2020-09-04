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
import java.util.Date;
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
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import rentcarTest.Dao.service.CustomerService;
import rentcarTest.Dao.service.RentService;
import rentcarTest.dto.Car;
import rentcarTest.dto.Customer;
import rentcarTest.dto.Rent;
import rentcarTest.popup.CarRentPopup;
import rentcarTest.table.RentTable;

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
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		pTitle.add(lblNewLabel, BorderLayout.CENTER);
		
		pSearch = new JPanel();
		add(pSearch);
		pSearch.setLayout(new BoxLayout(pSearch, BoxLayout.X_AXIS));
		
		pSearch_check = new JPanel();
		pSearch_check.setBackground(new Color(255, 255, 255));
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
		chckbxRent.setBackground(new Color(255, 255, 255));
		pSearch_check.add(chckbxRent);
		
		pSearch_button = new JPanel();
		pSearch_button.setBackground(new Color(255, 255, 255));
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
		pTable.setBackground(new Color(255, 255, 255));
		add(pTable);
		pTable.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		pTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new RentTable();
		scrollPane.setViewportView(table);
		
		pBtns = new JPanel();
		pBtns.setBackground(new Color(255, 255, 255));
		add(pBtns);
		
		btnUpdate = new JButton("수정");
		pBtns.add(btnUpdate);
		
		btnRent = new JButton("대여");
		btnRent.addActionListener(this);
		pBtns.add(btnRent);
		
	}

	public void insertRent(Rent item) {
		service = new RentService();
		lists = service.showRents();
		table.setItems(lists);
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
		if (e.getSource() == btnRent) {
			CarRentPopup carRentPopup = new CarRentPopup();
			carRentPopup.setCarRentList(this);
			carRentPopup.setVisible(true);
		}
		if (e.getSource() == btnSearch) {
			actionPerformedBtnSearch(e);
		}
	}
	
	protected void actionPerformedBtnSearch(ActionEvent e) {
		rentFindList = null;
		Rent rent = new Rent();
		/*Date dateRent = new Date();  // 대여일자
		Date dateReturn = new Date();  // 반납일자*/
		String search = cmbCate.getSelectedItem().toString();  //검색분류
		String searchText = tfSearch.getText().trim();  //검색내용

		Car car = new Car();
		Customer ctm = new Customer();
		
		if (dateRent.getDate() != null) {
			System.out.println(dateRent.getDate());
		}
		if (searchText != null) {
			if (search.equals("차번호")) {
				car.setCarNo(searchText);
			}
			if (search.equals("성명")) {
				ctm.setName(searchText);
			}
			if (search.equals("연락처")) {
				ctm.setTel(searchText);
			}
		}
		rent.setCar_no(car);
		rent.setCtm_no(ctm);
		rentFindList = service.showFindRents(rent, dateRent.getDate(), dateReturn.getDate(), search);
		
		if (rentFindList != null) {
			table.setItems(rentFindList);
		} else {
			JOptionPane.showMessageDialog(null, "검색된 내용이 없습니다.");
		}
		
	}
}
