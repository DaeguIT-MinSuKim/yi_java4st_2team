package rentcarTest.popup;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import rentcarTest.Dao.service.CustomerService;
import rentcarTest.dto.Customer;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
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
	private JButton btnEdit;

	private CustomerService service = new CustomerService();
	
	public static void main(String[] args) {
		try {
			EditCustomerPopup dialog = new EditCustomerPopup();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditCustomerPopup() {
		initComponents();
	}
	private void initComponents() {
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		{
			JLabel lblDialog = new JLabel("고객 수정");
			lblDialog.setHorizontalAlignment(SwingConstants.CENTER);
			lblDialog.setFont(new Font("맑은 고딕", Font.BOLD, 30));
			getContentPane().add(lblDialog);
		}
		pContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pContent);
		pContent.setLayout(new BoxLayout(pContent, BoxLayout.X_AXIS));
		{
			JPanel pLeft = new JPanel();
			pContent.add(pLeft);
			pLeft.setLayout(new GridLayout(0, 2, 20, 10));
			{
				JLabel lblNo = new JLabel("고객번호");
				lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
				pLeft.add(lblNo);
			}
			{
				tfNo = new JTextField();
				pLeft.add(tfNo);
				tfNo.setColumns(10);
			}
			{
				JLabel lblName = new JLabel("고객명");
				lblName.setHorizontalAlignment(SwingConstants.RIGHT);
				pLeft.add(lblName);
			}
			{
				tfName = new JTextField();
				tfName.setColumns(10);
				pLeft.add(tfName);
			}
			{
				JLabel lblTel = new JLabel("연락처");
				lblTel.setHorizontalAlignment(SwingConstants.RIGHT);
				pLeft.add(lblTel);
			}
			{
				tfTel = new JTextField();
				tfTel.setColumns(10);
				pLeft.add(tfTel);
			}
			{
				JLabel lblAddress = new JLabel("주소");
				lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
				pLeft.add(lblAddress);
			}
			{
				tfAddress = new JTextField();
				tfAddress.setColumns(10);
				pLeft.add(tfAddress);
			}
			{
				JLabel lblMlg = new JLabel("마일리지");
				lblMlg.setHorizontalAlignment(SwingConstants.RIGHT);
				pLeft.add(lblMlg);
			}
			{
				tfMlg = new JTextField();
				tfMlg.setColumns(10);
				pLeft.add(tfMlg);
			}
		}
		{
			JPanel pRight = new JPanel();
			pContent.add(pRight);
			pRight.setLayout(new BoxLayout(pRight, BoxLayout.X_AXIS));
			{
				JLabel lblRemark = new JLabel("비고");
				pRight.add(lblRemark);
			}
			{
				tfRemark = new JTextField();
				tfRemark.setColumns(10);
				pRight.add(tfRemark);
			}
		}
		{
			JPanel pTable = new JPanel();
			getContentPane().add(pTable);
		}
		{
			JPanel pBtns = new JPanel();
			getContentPane().add(pBtns);
			pBtns.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				btnEdit = new JButton("확인");
				btnEdit.addActionListener(this);
				btnEdit.setActionCommand("OK");
				pBtns.add(btnEdit);
				getRootPane().setDefaultButton(btnEdit);
			}
			{
				btnCancel = new JButton("닫기");
				btnCancel.addActionListener(this);
				btnCancel.setActionCommand("Cancel");
				pBtns.add(btnCancel);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEdit) {
			actionPerformedBtnEdit(e);
		}
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
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
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		EditCustomerPopup.this.dispose();
	}
}
