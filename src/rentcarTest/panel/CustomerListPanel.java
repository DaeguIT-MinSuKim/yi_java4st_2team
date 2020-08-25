package rentcarTest.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import rentcarTest.Dao.service.CustomerService;
import rentcarTest.dto.Customer;
import rentcarTest.table.CustomerTable;

@SuppressWarnings("serial")
public class CustomerListPanel extends JPanel {
	private JPanel panel;
	private JScrollPane scrollPane;
	private CustomerTable table;
	private CustomerService service;
	private List<Customer> lists;
	
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
		panel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new CustomerTable();
		table.setItems(lists);
		scrollPane.setViewportView(table);
	}
}