package rentcarTest.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import rentcarTest.Dao.service.CustomerService;
import rentcarTest.dto.Customer;
import rentcarTest.popup.AddCustomerPopup;
import rentcarTest.popup.EditCustomerPopup;
import rentcarTest.table.CustomerTable;

@SuppressWarnings("serial")
public class CustomerListPanel extends JPanel implements ActionListener, ItemListener {
	private JPanel pTable;
	private JScrollPane scrollPane;
	private CustomerTable table;
	private CustomerService service;
	private List<Customer> lists;
	private JPanel pBtns;
	private JPanel pTitle;
	private JLabel lblTitle;
	private JPanel pSearch;
	private JButton btnAdd;
	private JButton btnRent;
	private JPanel pSearch_check;
	private JPanel pSearch_button;
	private JCheckBox chckbxRent;
	private JCheckBox chckbxBlackList;
	private JComboBox<String> cmbCate;
	private JTextField tfSearch;
	private JButton btnSearch;
	
	public CustomerListPanel() {
		service = new CustomerService();
		lists = service.showCustomers();
		
		initComponents();
		
		table.setItems(lists);
		
		CustomPopupMenu popMenu = new CustomPopupMenu(this);
		table.setComponentPopupMenu(popMenu);
		scrollPane.setViewportView(table);
	}
	
	private void initComponents() {
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		pTitle = new JPanel();
		add(pTitle);
		pTitle.setLayout(new BorderLayout(0, 0));
		
		lblTitle = new JLabel("고객 명단");
		lblTitle.setFont(new Font("굴림", Font.BOLD, 30));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pTitle.add(lblTitle);
		
		pSearch = new JPanel();
		add(pSearch);
		pSearch.setLayout(new BoxLayout(pSearch, BoxLayout.X_AXIS));
		
		pSearch_check = new JPanel();
		pSearch.add(pSearch_check);
		pSearch_check.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		chckbxRent = new JCheckBox("대여중");
		chckbxRent.addItemListener(this);
		pSearch_check.add(chckbxRent);
		
		chckbxBlackList = new JCheckBox("블랙리스트");
		pSearch_check.add(chckbxBlackList);
		
		pSearch_button = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pSearch_button.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		pSearch.add(pSearch_button);
		
		cmbCate = new JComboBox<>();
		setSearchCate();
		pSearch_button.add(cmbCate);
		
		tfSearch = new JTextField();
		pSearch_button.add(tfSearch);
		tfSearch.setColumns(10);
		
		btnSearch = new JButton("검색");
		pSearch_button.add(btnSearch);
		
		pTable = new JPanel();
		add(pTable);
		pTable.setLayout(new BoxLayout(pTable, BoxLayout.Y_AXIS));
		
		scrollPane = new JScrollPane();
		pTable.add(scrollPane);
		
		table = new CustomerTable();
		//scrollPane.setViewportView(table);
		
		pBtns = new JPanel();
		add(pBtns);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnRent = new JButton("대여");
		btnRent.addActionListener(this);
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

			System.out.println("테스트");	
		} else {
			System.out.println("테스트2");
		}
	}

	// 검색 분류
	private void setSearchCate() {
		String[] items = {
				"고객번호",
				"성명",
				"연락처",
				"주소"
				};
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(items);
		cmbCate.setModel(model);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			System.out.println("추가");
		}
		if (e.getSource() == btnRent) {
			System.out.println("대여");
		}
		// 우클릭 메뉴
		if (e.getSource() instanceof JMenuItem) {
			if (e.getActionCommand().equals("수정")) {
				AddCustomerPopup ctmPopup = new AddCustomerPopup();
				ctmPopup.setVisible(true);
			}
			if (e.getActionCommand().equals("삭제")) {
				System.out.println("삭제");
			}
			if (e.getActionCommand().equals("세부정보")) {
				System.out.println("세부정보");
			}
			if (e.getActionCommand().equals("마일리지")) {
				System.out.println("마일리지");
			}
		}
	}
	
	
	private class CustomPopupMenu extends JPopupMenu {
		public CustomPopupMenu(ActionListener listener) {
			JMenuItem updateMenu = new JMenuItem("수정");
			updateMenu.addActionListener(listener);
			add(updateMenu);
			JMenuItem deleteeMenu = new JMenuItem("삭제");
			deleteeMenu.addActionListener(listener);
			add(deleteeMenu);
			JMenuItem detailMenu = new JMenuItem("세부정보");
			detailMenu.addActionListener(listener);
			add(detailMenu);
			JMenuItem mileageMenu = new JMenuItem("마일리지");
			mileageMenu.addActionListener(listener);
			add(mileageMenu);
		}
	}
}