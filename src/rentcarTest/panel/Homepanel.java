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
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JScrollPane scrollPane;
	private JPanel panel_1;
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

		panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(51, 282, 416, 215);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
			
		lblNewLabel = new JLabel("오늘 대여 고객");
		lblNewLabel.setForeground(SystemColor.info);
		lblNewLabel.setFont(new Font("인터파크고딕 B", Font.PLAIN, 15));
		lblNewLabel.setBounds(199, 237, 123, 35);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("오늘 반납 고객");
		lblNewLabel_1.setForeground(SystemColor.info);
		lblNewLabel_1.setFont(new Font("인터파크고딕 B", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(668, 237, 137, 35);
		add(lblNewLabel_1);
		
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		panel_1.setBounds(509, 282, 416, 215);
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		
		table_2 = new CustomerTable();
		table_2.setItems(lists);
		scrollPane_1.setViewportView(table_2);
		
		table_1 = new CustomerTable();
		table_1.setItems(lists);
		scrollPane.setViewportView(table_1);
	}
}