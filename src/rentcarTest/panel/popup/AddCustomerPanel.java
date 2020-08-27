package rentcarTest.panel.popup;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.EmptyBorder;

import rentcarTest.Dao.service.CustomerService;
import rentcarTest.dto.Customer;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.CardLayout;

public class AddCustomerPanel extends JPanel implements ActionListener {
	private JPanel pTextField;
	private JTextField tfNo;
	private JTextField tfName;
	private JTextField tfTel;
	private JTextField tfAddress;
	private JTextField tfMileage;
	private JTextField tfRemark;
	private JPanel pBtns;
	private JButton btnAdd;
	private JButton btnCancel;
	private CustomerService service;

	public AddCustomerPanel() {
		initComponents();
	}
	
	private void initComponents() {
		setBorder(new EmptyBorder(20, 20, 20, 20));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel lblName = new JLabel("고객 추가");
		lblName.setFont(new Font("굴림", Font.BOLD, 20));
		lblName.setAlignmentY(Component.TOP_ALIGNMENT);
		lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblName);
		
		pTextField = new JPanel();
		add(pTextField);
		pTextField.setLayout(new GridLayout(0, 2, 20, 10));
		
		JLabel lblNo = new JLabel("고객번호");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pTextField.add(lblNo);
		
		tfNo = new JTextField();
		pTextField.add(tfNo);
		tfNo.setColumns(10);
		
		JLabel lblName_1 = new JLabel("고객명");
		lblName_1.setHorizontalAlignment(SwingConstants.RIGHT);
		pTextField.add(lblName_1);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		pTextField.add(tfName);
		
		JLabel lblTel = new JLabel("연락처");
		lblTel.setHorizontalAlignment(SwingConstants.RIGHT);
		pTextField.add(lblTel);
		
		tfTel = new JTextField();
		tfTel.setColumns(10);
		pTextField.add(tfTel);
		
		JLabel lblAddress = new JLabel("주소");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		pTextField.add(lblAddress);
		
		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		pTextField.add(tfAddress);
		
		JLabel lblMileage = new JLabel("마일리지");
		lblMileage.setHorizontalAlignment(SwingConstants.RIGHT);
		pTextField.add(lblMileage);
		
		tfMileage = new JTextField();
		tfMileage.setColumns(10);
		pTextField.add(tfMileage);
		
		JLabel lblRemark = new JLabel("비고");
		lblRemark.setHorizontalAlignment(SwingConstants.RIGHT);
		pTextField.add(lblRemark);
		
		tfRemark = new JTextField();
		tfRemark.setColumns(10);
		pTextField.add(tfRemark);
		
		pBtns = new JPanel();
		add(pBtns);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.setLayout(new BoxLayout(pBtns, BoxLayout.X_AXIS));
		pBtns.add(btnAdd);
		btnAdd.setPreferredSize(new Dimension(100, 30));
		
		btnCancel = new JButton("취소");
		pBtns.add(btnCancel);
		btnCancel.setPreferredSize(new Dimension(100, 30));
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		int no = Integer.parseInt(tfNo.getText());
		String name = tfName.getText();
		String tel = tfTel.getText();
		String address = tfAddress.getText();
		String remark = tfRemark.getText();
		int ctm_mlg = Integer.parseInt(tfMileage.getText());
		Customer item = new Customer(no, name, tel, address, remark, ctm_mlg);
		service.insertCtm(item);
		System.out.println("왜!");
	}
}
