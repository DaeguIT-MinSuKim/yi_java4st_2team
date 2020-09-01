package rentcarTest.popup;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import rentcarTest.Dao.service.CustomerService;
import rentcarTest.Dao.service.MileageService;
import rentcarTest.dto.Mileage;
import rentcarTest.panel.MileagePanel;

public class EditMileagePopup extends JDialog implements ActionListener {

	private JPanel pBtns;
	private JButton btnAdd;
	private JButton btnDeduct;
	private JButton btnCancel;
	private JPanel pContent;
	private JTextField tfNo;
	private JTextField tfName;
	private JTextField tfMileage;
	private JTextField tfRemark;
	private JLabel lblDialog;
	private int mlg_kind = 0;
	private int point;

	private MileageService Mservice = new MileageService();
	private CustomerService Cservice = new CustomerService();
	private MileagePanel mList = new MileagePanel();

	public EditMileagePopup() {
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

		btnDeduct = new JButton("차감");
		btnDeduct.addActionListener(this);
		pBtns.add(btnDeduct);

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
		// tfNo.setEnabled(false);
		// tfNo.setText(Cservice.findCustomers()); 고객명 검색 impl d
		pContent.add(tfNo);

		JLabel lblName = new JLabel("고객명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblName);

		tfName = new JTextField();
		tfName.setColumns(10);
		pContent.add(tfName);

		JLabel lblMileage = new JLabel("마일리지");
		lblMileage.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblMileage);

		tfMileage = new JTextField();
		tfMileage.setColumns(10);
		pContent.add(tfMileage);

		JLabel lblRemark = new JLabel("비고");
		lblRemark.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblRemark);

		tfRemark = new JTextField();
		tfRemark.setColumns(10);
		pContent.add(tfRemark);

		lblDialog = new JLabel("마일리지 관리");
		lblDialog.setFont(new Font("굴림", Font.BOLD, 20));
		lblDialog.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblDialog, BorderLayout.NORTH);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		if (e.getSource() == btnDeduct) {
			actionPerformedBtnDeduct(e);
			mlg_kind = 0;
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
			mlg_kind = 1;
		}
	}

	private void actionPerformedBtnAdd(ActionEvent e) {
		int mlg_no = Mservice.lastMlgNo();
		int ctm_no = Integer.parseInt(tfNo.getText().trim());
		int point = Integer.parseInt(tfMileage.getText().trim());
		String mlg_remark = tfRemark.getText().trim();
		Mileage item = new Mileage(mlg_no, ctm_no, mlg_kind, point, mlg_remark);
		Mservice.insertMile(item);
		mList.insertMile(item);
		EditMileagePopup.this.dispose();

	}

	private void actionPerformedBtnDeduct(ActionEvent e) {
		int mlg_no = Mservice.lastMlgNo();
		int ctm_no = Integer.parseInt(tfNo.getText().trim());
		int point = Integer.parseInt(tfMileage.getText().trim());
		String mlg_remark = tfRemark.getText().trim();
		Mileage item = new Mileage(mlg_no, ctm_no, mlg_kind, point, mlg_remark);
		Mservice.insertMile(item);
		mList.insertMile(item);
		EditMileagePopup.this.dispose();
	}

	private void actionPerformedBtnCancel(ActionEvent e) {
		EditMileagePopup.this.dispose();
	}
}
