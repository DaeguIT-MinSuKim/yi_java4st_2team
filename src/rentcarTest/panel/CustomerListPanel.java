package rentcarTest.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import rentcarTest.Dao.service.CustomerService;
import rentcarTest.dto.Customer;
import rentcarTest.popup.AddCustomerPopup;
import rentcarTest.popup.EditCustomerPopup;
import rentcarTest.table.CustomerTable;

@SuppressWarnings("serial")
public class CustomerListPanel extends JPanel implements ActionListener {
	private JPanel pTable;
	private JScrollPane scrollPane;
	private CustomerTable table;
	private CustomerService service;
	private List<Customer> lists;
	private JPanel pBtns;
	
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
		
		pTable = new JPanel();
		add(pTable);
		pTable.setLayout(new BoxLayout(pTable, BoxLayout.Y_AXIS));
		
		scrollPane = new JScrollPane();
		pTable.add(scrollPane);
		
		table = new CustomerTable();
		//scrollPane.setViewportView(table);
		
		pBtns = new JPanel();
		add(pBtns);
		
	}
	public void actionPerformed(ActionEvent e) {
		// 우클릭 메뉴
		if (e.getSource() instanceof JMenuItem) {
			if (e.getActionCommand().equals("수정")) {
				actionPerformedBtnNewButton(e);
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
	
	// 수정
	protected void actionPerformedBtnNewButton(ActionEvent e) {
		AddCustomerPopup ctmPopup = new AddCustomerPopup();
		ctmPopup.setVisible(true);
	}
	protected void actionPerformedBtnNewButton_1(ActionEvent e) {
		EditCustomerPopup editPopup = new EditCustomerPopup();
		editPopup.setVisible(true);
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