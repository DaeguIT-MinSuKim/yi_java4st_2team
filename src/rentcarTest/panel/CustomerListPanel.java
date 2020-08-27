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
import rentcarTest.panel.popup.AddCustomerPanel;
import rentcarTest.table.CustomerTable;

@SuppressWarnings("serial")
public class CustomerListPanel extends JPanel implements ActionListener {
	private JPanel pTable;
	private JScrollPane scrollPane;
	private CustomerTable table;
	private CustomerService service;
	private List<Customer> lists;
	private JPanel pBtns;
	private JButton btnNewButton;
	
	public CustomerListPanel() {
		service = new CustomerService();
		lists = service.showCustomers();
		initComponents();
	}
	
	private void initComponents() {
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
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
		
		btnNewButton = new JButton("수정");
		btnNewButton.addActionListener(this);
		pBtns.add(btnNewButton);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			actionPerformedBtnNewButton(e);
		}
	}
	protected void actionPerformedBtnNewButton(ActionEvent e) {
		AddCustomerPanel ctmPanel = new AddCustomerPanel();
		ctmPanel.setVisible(true);
	}
}