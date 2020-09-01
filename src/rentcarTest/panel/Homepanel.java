package rentcarTest.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.Calendar;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import rentcarTest.Dao.service.CustomerService;
import rentcarTest.dto.Customer;
import rentcarTest.popup.EditCustomerPopup;
import rentcarTest.table.CustomerTable;

@SuppressWarnings("serial")
public class Homepanel extends JPanel {
	Calendar today = Calendar.getInstance();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JScrollPane scrollPane;
	private CustomerTable table_1;
	private JScrollPane scrollPane_1;
	private CustomerTable table_2;
	private List<Customer> lists;
	private CustomerService service;
	private EditCustomerPopup editPopup;

	public Homepanel() {
		service = new CustomerService();
		lists = service.showCustomers();
		editPopup = new EditCustomerPopup();
		initComponents();
	}

	private void initComponents() {
		setPreferredSize(new Dimension(1000, 800));
		setBackground(new Color(30, 144, 255));
		setLayout(null);
			
		lblNewLabel = new JLabel("오늘 대여 고객");
		lblNewLabel.setForeground(SystemColor.info);
		lblNewLabel.setFont(new Font("인터파크고딕 B", Font.PLAIN, 15));
		lblNewLabel.setBounds(97, 37, 123, 35);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("오늘 반납 고객");
		lblNewLabel_1.setForeground(SystemColor.info);
		lblNewLabel_1.setFont(new Font("인터파크고딕 B", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(97, 383, 137, 35);
		add(lblNewLabel_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(97, 77, 803, 215);
		add(scrollPane);
		
		table_1 = new CustomerTable();
		table_1.setItems(lists);
		scrollPane.setViewportView(table_1);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(97, 428, 803, 215);
		add(scrollPane_1);
		
		table_2 = new CustomerTable();
		table_2.setItems(lists);
		scrollPane_1.setViewportView(table_2);
	}
}