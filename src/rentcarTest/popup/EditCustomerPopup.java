package rentcarTest.popup;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import rentcarTest.Dao.service.CustomerService;
import rentcarTest.dto.Customer;
import rentcarTest.panel.CustomerListPanel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.management.StringValueExp;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditCustomerPopup extends JDialog implements ActionListener {

	private final JPanel pContent = new JPanel();
	private JTextField tfNo;
	private JTextField tfName;
	private JTextField tfTel;
	private JTextField tfAddress;
	private JTextField tfMlg;
	private JTextField tfRemark;
	private JButton btnCancel;
	private int selIdx;

	private CustomerService service = new CustomerService();
	private CustomerListPanel ctmList;
	private JLabel lblDialog;
	private JButton btnEdit;

	public void setSelIdx(int selIdx) {
		this.selIdx = selIdx;
	}

	public void setCtmList(CustomerListPanel ctmList) {
		this.ctmList = ctmList;
	}
	
	public EditCustomerPopup() {
		initComponents();
	}

	private void initComponents() {
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		lblDialog = new JLabel("고객 수정");
		lblDialog.setHorizontalAlignment(SwingConstants.CENTER);
		lblDialog.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		getContentPane().add(lblDialog);

		pContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pContent);
		pContent.setLayout(new BoxLayout(pContent, BoxLayout.X_AXIS));
		JPanel pLeft = new JPanel();
		pContent.add(pLeft);
		pLeft.setLayout(new GridLayout(0, 2, 20, 10));

		JLabel lblNo = new JLabel("고객번호");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pLeft.add(lblNo);

		tfNo = new JTextField();
		tfNo.setEnabled(false);
		tfNo.setColumns(10);
		pLeft.add(tfNo);

		JLabel lblName = new JLabel("고객명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		pLeft.add(lblName);

		tfName = new JTextField();
		tfName.setColumns(10);
		pLeft.add(tfName);

		JLabel lblTel = new JLabel("연락처");
		lblTel.setHorizontalAlignment(SwingConstants.RIGHT);
		pLeft.add(lblTel);

		tfTel = new JTextField();
		tfTel.setColumns(10);
		pLeft.add(tfTel);

		JLabel lblAddress = new JLabel("주소");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		pLeft.add(lblAddress);

		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		pLeft.add(tfAddress);

		JLabel lblMlg = new JLabel("마일리지");
		lblMlg.setHorizontalAlignment(SwingConstants.RIGHT);
		pLeft.add(lblMlg);

		tfMlg = new JTextField();
		tfMlg.setColumns(10);
		pLeft.add(tfMlg);

		JPanel pRight = new JPanel();
		pContent.add(pRight);
		pRight.setLayout(new BoxLayout(pRight, BoxLayout.X_AXIS));

		JLabel lblRemark = new JLabel("비고");
		pRight.add(lblRemark);

		tfRemark = new JTextField();
		tfRemark.setColumns(10);
		pRight.add(tfRemark);

		JPanel pTable = new JPanel();
		getContentPane().add(pTable);

		JPanel pBtns = new JPanel();
		getContentPane().add(pBtns);
		pBtns.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnEdit = new JButton("확인");
		btnEdit.addActionListener(this);
		pBtns.add(btnEdit);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		btnCancel.setActionCommand("Cancel");
		pBtns.add(btnCancel);

	}

	public void setDetail() {
		tfName.setEnabled(false);
		tfTel.setEnabled(false);
		tfAddress.setEnabled(false);
		tfMlg.setEnabled(false);
		tfRemark.setEnabled(false);
		btnEdit.setText("수정");
		btnCancel.setText("닫기");
		lblDialog.setText("고객 세부 정보");
	}

	public void setItem(Customer ctm) {
		tfNo.setText(String.valueOf(ctm.getNo()));
		tfName.setText(ctm.getName());
		tfTel.setText(ctm.getTel());
		tfAddress.setText(ctm.getAddress());
		tfMlg.setText(String.valueOf(ctm.getCtm_mlg()));
		tfRemark.setText(ctm.getRemark());
	}
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEdit) {
			if (e.getActionCommand().equals("확인")) {
				actionPerformedBtnEdit(e);
			} else {
				actionPerformedBtnDetail(e);
			}
		}
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
	}

	private void actionPerformedBtnDetail(ActionEvent e) {
		tfName.setEnabled(true);
		tfTel.setEnabled(true);
		tfAddress.setEnabled(true);
		tfMlg.setEnabled(true);
		tfRemark.setEnabled(true);
		
		btnEdit.setText("확인");
		btnCancel.setText("취소");
		lblDialog.setText("고객 수정");
	}

	protected void actionPerformedBtnEdit(ActionEvent e) {
		int no = Integer.parseInt(tfNo.getText().trim());
		String name = tfName.getText().trim();
		String tel = tfTel.getText().trim();
		String address = tfAddress.getText().trim();
		String remark = tfRemark.getText().trim();
		int ctm_mlg = Integer.parseInt(tfMlg.getText().trim());
		Customer item = new Customer(no, name, tel, address, remark, ctm_mlg);
		
		service.updateCtm(item);
		ctmList.updateCtm(selIdx, item);
		EditCustomerPopup.this.dispose();
	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		EditCustomerPopup.this.dispose();
	}
}
