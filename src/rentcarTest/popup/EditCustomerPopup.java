package rentcarTest.popup;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
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
import javax.swing.border.EmptyBorder;

import rentcarTest.Dao.service.CustomerService;
import rentcarTest.dto.Customer;
import rentcarTest.exception.EmptyTfException;
import rentcarTest.exception.InValidTfValue;
import rentcarTest.panel.CustomerListPanel;

public class EditCustomerPopup extends AbstractItemPopup<Customer> implements ActionListener {

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
	private JPanel pLeft;
	private JPanel pRight;

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
		
		pLeft = new JPanel();
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

		pRight = new JPanel();
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

	protected void actionPerformedBtnEdit(ActionEvent e) {
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
		int ctm_mlg = Integer.parseInt(tfMlg.getText().trim());
		Customer item = new Customer(no, name, tel, address, remark, ctm_mlg);

		service.updateCtm(item);
		ctmList.updateCtm(selIdx, item);
		EditCustomerPopup.this.dispose();
	}

	private void actionPerformedBtnDetail(ActionEvent e) {
		setTfEditable(pLeft, true);
		setTfEditable(pRight, true);
		
		btnEdit.setText("확인");
		btnCancel.setText("취소");
		lblDialog.setText("고객 수정");
	}

	public void setDetail() {
		setTfEditable(pLeft, false);
		setTfEditable(pRight, false);
		
		btnEdit.setText("수정");
		btnCancel.setText("닫기");
		lblDialog.setText("고객 세부 정보");
	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		EditCustomerPopup.this.dispose();
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

}
