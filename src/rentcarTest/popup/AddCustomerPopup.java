package rentcarTest.popup;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import rentcarTest.Dao.service.CustomerService;
import rentcarTest.dto.Customer;
import rentcarTest.exception.EmptyTfException;
import rentcarTest.exception.InValidTfValue;
import rentcarTest.panel.CustomerListPanel;

public class AddCustomerPopup extends AbstractItemPopup<Customer> implements ActionListener {
	private JPanel pBtns;
	private JButton btnAdd;
	private JButton btnCancel;
	private JPanel pContent;
	private JTextField tfNo;
	private JTextField tfName;
	private JTextField tfTel;
	private JTextField tfAddress;
	private JTextField tfMlg;
	private JTextField tfRemark;
	private JLabel lblDialog;

	private CustomerService service = new CustomerService();

	private CustomerListPanel ctmList = new CustomerListPanel();

	public void setCtmList(CustomerListPanel ctmList) {
		this.ctmList = ctmList;
	}

	public AddCustomerPopup() {
		initComponents();
	}

	private void initComponents() {
		getContentPane().setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(new BorderLayout());

		pBtns = new JPanel();
		getContentPane().add(pBtns, BorderLayout.SOUTH);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtns.add(btnCancel);

		pContent = new JPanel();
		getContentPane().add(pContent);
		pContent.setLayout(new GridLayout(0, 2, 20, 10));

		JLabel lblNo = new JLabel("고객번호");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblNo);

		tfNo = new JTextField();
		tfNo.setColumns(10);
		tfNo.setEnabled(false);
		tfNo.setText(Integer.toString(service.lastCtmNo()));
		pContent.add(tfNo);

		JLabel lblName = new JLabel("고객명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblName);

		tfName = new JTextField();
		tfName.setColumns(10);
		pContent.add(tfName);

		JLabel lblTel = new JLabel("연락처");
		lblTel.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblTel);

		tfTel = new JTextField();
		tfTel.setColumns(10);
		pContent.add(tfTel);

		JLabel lblAddress = new JLabel("주소");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblAddress);

		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		pContent.add(tfAddress);

		JLabel lblMileage = new JLabel("마일리지");
		lblMileage.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblMileage);

		tfMlg = new JTextField();
		tfMlg.setColumns(10);
		pContent.add(tfMlg);

		JLabel lblRemark = new JLabel("비고");
		lblRemark.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblRemark);

		tfRemark = new JTextField();
		tfRemark.setColumns(10);
		pContent.add(tfRemark);

		lblDialog = new JLabel("고객 추가");
		lblDialog.setFont(new Font("굴림", Font.BOLD, 20));
		lblDialog.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblDialog, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		if (isTfEmpty()) {
			JOptionPane.showMessageDialog(null,"공란이 존재");
			throw new EmptyTfException("공란 이 존재");
		}
		if (!isValidTf()) {
			JOptionPane.showMessageDialog(null,"이름은 한글만, 연락처는 000-0000-0000만 가능, 마일리지는 숫자만 가능");
			throw new InValidTfValue("이름은 한글만, 연락처는 000-0000-0000만 가능, 마일리지는 숫자만 가능");
		}
		
		int no = Integer.parseInt(tfNo.getText().trim());
		String name = tfName.getText().trim();
		String tel = tfTel.getText().trim();
		String address = tfAddress.getText().trim();
		String remark = tfRemark.getText().trim();
		int mile = Integer.parseInt(tfMlg.getText().trim());
		Customer item = new Customer(no, name, tel, address, remark, mile);
		service.insertCtm(item);
		ctmList.insertCtm(item);
		AddCustomerPopup.this.dispose();
	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		AddCustomerPopup.this.dispose();
	}

	@Override
	boolean isValidTf() {
		String name = tfName.getText().trim();
		String tel = tfTel.getText().trim();
		String mlg = tfMlg.getText().trim();

		boolean nameCheck = Pattern.matches("^[가-힣]+$", name);
		boolean telCheck = Pattern.matches("\\d{3}-\\d{3,4}-\\d{4}", tel);
		boolean mlgCheck = Pattern.matches("^[0-9]*$", mlg);

		return nameCheck && telCheck && mlgCheck;
	}
	
	@Override
	public void setItem(Customer item) {
	}
}
