package rentcarTest.panel.popup;

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

@SuppressWarnings("serial")
public class EditMileagePanel extends JDialog implements ActionListener{
	
	private JPanel pBtn;
	private JButton btnEdit;
	private JPanel pContent;
	private JTextField tfNo;
	private JTextField tfName;
	private JTextField tfRemark;
	private JLabel lblAdd;
	private JTextField tfMileage;
	private JButton btnAdd;
	private JButton btnCancel;
	private JButton btnDeduct;
	private Object service;
	public EditMileagePanel() {
		initComponents();
	}
	
	
	public static void main(String[] args) {
		try {
			AddCustomerPanel dialog = new AddCustomerPanel();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initComponents() {
		getContentPane().setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(new BorderLayout());
		
		pBtn = new JPanel();
		getContentPane().add(pBtn, BorderLayout.SOUTH);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		btnDeduct = new JButton("차감");
		btnDeduct.addActionListener(this);
		pBtn.add(btnDeduct);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);
		
		pContent = new JPanel();
		getContentPane().add(pContent);
		pContent.setLayout(new GridLayout(0, 2, 20, 10));
		
		JLabel lblNo = new JLabel("고객번호");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblNo);
		
		tfNo = new JTextField();
		tfNo.setColumns(10);
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
		
		lblAdd = new JLabel("추가");
		lblAdd.setFont(new Font("굴림", Font.BOLD, 20));
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblAdd, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
		if (e.getSource() == btnDeduct) {
			actionPerformedBtnCancel(e);
		}
	}
	 
	protected void actionPerformedBtnAdd(ActionEvent e) {
		//int mlg_no = Integer.parseInt(tfNo.getText().trim());
		//int ctm_no = Integer.parseInt();
		//int mlg_kind 
		//int point = Integer.parseInt(tfMileage.getText().trim());
		//String mlg_remark= tfRemark.getText().trim();
		//Mileage item = new Mileage(mlg_no, ctm_no, mlg_kind, point, mlg_remark);
		//insertMile(item);
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		EditMileagePanel.this.dispose();
	}
	protected void actionPerformedBtnDeduct(ActionEvent e) {
		
	}
}