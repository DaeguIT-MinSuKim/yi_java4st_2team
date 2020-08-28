package rentcarTest.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import rentcarTest.Dao.service.CustomerService;
import rentcarTest.dto.Customer;
import rentcarTest.popup.AddCustomerPopup;
import rentcarTest.popup.EditCustomerPopup;
import rentcarTest.table.CustomerTable;
import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class CustomerListPanel extends JPanel implements ActionListener {
	private JPanel pTable;
	private JScrollPane scrollPane;
	private CustomerTable table;
	private CustomerService service;
	private List<Customer> lists;
	private JPanel pBtns;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JPanel panel;
	private JLabel lblNewLabel;
	
	public CustomerListPanel() {
		service = new CustomerService();
		lists = service.showCustomers();
		initComponents();
	}
	
	private void initComponents() {
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		panel = new JPanel();
		add(panel);
		
		lblNewLabel = new JLabel("고객 관리");
		lblNewLabel.setFont(new Font("인터파크고딕 L", Font.PLAIN, 18));
		panel.add(lblNewLabel);
		
		pTable = new JPanel();
		add(pTable);
		pTable.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		pTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new CustomerTable();
		table.setItems(lists);
		scrollPane.setViewportView(table);
		
		pBtns = new JPanel();
		add(pBtns);
		
		btnNewButton = new JButton("추가");
		btnNewButton.addActionListener(this);
		pBtns.add(btnNewButton);
		
		btnNewButton_1 = new JButton("수정");
		btnNewButton_1.addActionListener(this);
		pBtns.add(btnNewButton_1);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton_1) {
			actionPerformedBtnNewButton_1(e);
		}
		if (e.getSource() == btnNewButton) {
			actionPerformedBtnNewButton(e);
		}
	}
	protected void actionPerformedBtnNewButton(ActionEvent e) {
		AddCustomerPopup ctmPopup = new AddCustomerPopup();
		ctmPopup.setVisible(true);
	}
	protected void actionPerformedBtnNewButton_1(ActionEvent e) {
		EditCustomerPopup editPopup = new EditCustomerPopup();
		editPopup.setVisible(true);
	}
}