package rentcarTest.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import rentcarTest.Dao.service.RentService;
import rentcarTest.dto.Rent;
import rentcarTest.table.RentTable;

public class RentListPanel extends JPanel{
	private JTextField textField;
	private RentTable table;
	
	private RentService service;
	private List<Rent> lists;
	
	public RentListPanel() {
		service = new RentService();
		lists = service.showRents();
		
		initComponents();
		
		table.setItems(lists);
	}
	
	private void initComponents() {
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pTitle = new JPanel();
		add(pTitle);
		pTitle.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("대여 기록");
		pTitle.add(lblNewLabel);
		
		JPanel pSearch = new JPanel();
		add(pSearch);
		pSearch.setLayout(new BoxLayout(pSearch, BoxLayout.X_AXIS));
		
		JPanel pSearch_check = new JPanel();
		pSearch.add(pSearch_check);
		pSearch_check.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JCheckBox chckbxRent = new JCheckBox("대여중");
		pSearch_check.add(chckbxRent);
		
		JPanel pSearch_button = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pSearch_button.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		pSearch.add(pSearch_button);
		
		textField = new JTextField();
		textField.setColumns(10);
		pSearch_button.add(textField);
		
		JButton btnSearch = new JButton("검색");
		pSearch_button.add(btnSearch);
		
		JPanel pTable = new JPanel();
		add(pTable);
		pTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new RentTable();
		scrollPane.setViewportView(table);
		
		JPanel pBtns = new JPanel();
		add(pBtns);
		
		JButton btnUpdate = new JButton("수정");
		pBtns.add(btnUpdate);
		
		JButton btnRent = new JButton("대여");
		pBtns.add(btnRent);
		
	}

}
