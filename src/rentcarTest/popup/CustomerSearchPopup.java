package rentcarTest.popup;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import rentcarTest.Dao.service.CustomerService;
import rentcarTest.dto.Customer;
import rentcarTest.table.CustomerSearchTable;

public class CustomerSearchPopup extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel pTitle;
	private JPanel pSearch;
	private JPanel pTable;
	private JPanel pBtns;
	private JButton btnAdd;
	private JScrollPane scrollPane;
	private CustomerSearchTable table;
	private JComboBox<String> cmbCate;
	private JTextField tfSearch;
	private JButton btnSearch;
	private JLabel lblTitle;
	
	private CustomerService service = new CustomerService();
	private List<Customer> lists = service.showCustomers();
	private List<Customer> ctmFindList;
	
	private CarRentPopup ctmList = new CarRentPopup();
	
	public void setCtmList(CarRentPopup ctmList) {
		this.ctmList = ctmList;
	}
	
	public CustomerSearchPopup() {
		
		initComponents();
	}
	
	private void initComponents() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		setBounds(100, 100, 700, 350);
		
		pTitle = new JPanel();
		getContentPane().add(pTitle);
		
		lblTitle = new JLabel("고객 명단");
		pTitle.add(lblTitle);
		
		pSearch = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pSearch.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(pSearch);
		
		cmbCate = new JComboBox<String>();
		setSearchCate();
		pSearch.add(cmbCate);
		
		tfSearch = new JTextField();
		tfSearch.setColumns(10);
		pSearch.add(tfSearch);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		pSearch.add(btnSearch);
		
		pTable = new JPanel();
		getContentPane().add(pTable);
		pTable.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		pTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new CustomerSearchTable();
		table.setItems(lists);
		scrollPane.setViewportView(table);
		
		pBtns = new JPanel();
		getContentPane().add(pBtns);
		
		btnAdd = new JButton("선택");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
	}
	
	// 검색 분류
	private void setSearchCate() {
		String[] items = {"검색", "고객번호", "성명", "연락처", "주소" };
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(items);
		cmbCate.setModel(model);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			actionPerformedBtnSearch(e);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
	}
	
	private void actionPerformedBtnAdd(ActionEvent e) {
		int selIdx = table.getSelectedRow();
		if (selIdx == -1) {
			JOptionPane.showMessageDialog(null, "해당 회원을 선택하세요.");
			return;
		}
		
		Customer ctm = lists.get(selIdx);
		/*ctm.setNo(lists.get(selIdx).getNo());
		ctm.setName(lists.get(selIdx).getName());
		ctm.setTel(lists.get(selIdx).getTel());
		ctm.setMile(lists.get(selIdx).getMile());
		System.out.println(ctm);*/
		
		ctmList.setCustomer(ctm);
		this.dispose();
	}

	protected void actionPerformedBtnSearch(ActionEvent e) {
		ctmFindList = null;
		String searchText = tfSearch.getText().trim();
		Customer ctmListFind = new Customer();

		Object cmbCateText = cmbCate.getSelectedItem();
		if (cmbCateText.equals("검색")) {
			ctmFindList = service.showCustomers();
		} else if (searchText.equals("")) {
			JOptionPane.showMessageDialog(null, "검색할 내용을 입력해주세요.");
		} else {
			if (cmbCateText.equals("고객번호")) {
				ctmListFind.setNo(Integer.parseInt(searchText));
				ctmFindList = service.findCustomers(ctmListFind);
			}
			if (cmbCateText.equals("연락처")) {
				ctmListFind.setTel(searchText);
				ctmFindList = service.findCustomers(ctmListFind);
			}
			if (cmbCateText.equals("성명")) {
				ctmListFind.setName(searchText);
				ctmFindList = service.findCustomers(ctmListFind);
			}
			if (cmbCateText.equals("주소")) {
				ctmListFind.setAddress(searchText);
				ctmFindList = service.findCustomers(ctmListFind);
			}
		}
		
		if (ctmFindList != null) {
			table.setItems(ctmFindList);			
		} else {
			JOptionPane.showMessageDialog(null, "검색된 내용이 없습니다.");
		}
	}
}
