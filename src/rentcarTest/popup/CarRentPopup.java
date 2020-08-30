package rentcarTest.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import java.awt.GridLayout;
import javax.swing.SwingConstants;

import rentcarTest.conn.JdbcUtil;
import rentcarTest.dto.Car;
import rentcarTest.dto.Customer;
import rentcarTest.dto.Kind;
import rentcarTest.panel.CalendarDataManager;

import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class CarRentPopup extends JDialog implements ActionListener, ItemListener{
	private JTextField tfCode;
	private JTextField tfCtmName;
	private JTextField tfTel;
	private JTextField tfTime;
	private JTextField tfMileage;
	private JTextField tfSale;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JPanel pBtns;
	private JLabel lblTitle;
	private JPanel pTotal;
	private JPanel pContent;
	private JLabel lblTotal1;
	private JLabel lblTotal2;
	private JLabel lblTotal3;
	private JLabel lblCode;
	private JLabel lblCtmName;
	private JLabel lblTel;
	private JLabel lblTime;
	private JLabel lblDate1;
	private JLabel lblDate2;
	private JLabel lblCarKind;
	private JComboBox cbCarKind;
	private JLabel lblCarName;
	private JLabel lblMileage;
	private JLabel lblMileage2;
	private JLabel lblMileage3;
	private JLabel lblSale;
	private JLabel lblDiscount1;
	private JLabel lblDiscount2;
	private JTextField tfCarName;
	private JSpinner spDate1;
	private JSpinner spDate2;
	Calendar calendar = Calendar.getInstance();
	Date value = calendar.getTime();
	private JLabel lblCarNo;
	private JTextField tfCarNo;
	private JLabel lblCtmNo;
	private JTextField tfCtmNo;
	private JLabel lblDriver;
	private JCheckBox chkDriver;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	private JLabel lblNewLabel;
	
	

	
	public CarRentPopup() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		lblTitle = new JLabel("차량 대여");
		lblTitle.setFont(new Font("굴림", Font.BOLD, 20));
		lblTitle.setAlignmentX(0.5f);
		getContentPane().add(lblTitle);
		
		pContent = new JPanel();
		getContentPane().add(pContent);
		pContent.setLayout(new GridLayout(0, 4, 15, 7));
		
		lblCode = new JLabel("코드 번호");
		lblCode.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblCode);
		
		tfCode = new JTextField();
		tfCode.setEditable(false);
		tfCode.setColumns(10);
		pContent.add(tfCode);
		
		lblCtmName = new JLabel("고객명");
		lblCtmName.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblCtmName);
		
		tfCtmName = new JTextField();
		tfCtmName.setColumns(10);
		pContent.add(tfCtmName);
		
		lblCarNo = new JLabel("차량 번호");
		lblCarNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblCarNo);
		
		tfCarNo = new JTextField();
		pContent.add(tfCarNo);
		tfCarNo.setColumns(10);
		
		lblCtmNo = new JLabel("고객 번호");
		lblCtmNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblCtmNo);
		
		tfCtmNo = new JTextField();
		pContent.add(tfCtmNo);
		tfCtmNo.setColumns(10);
		
		lblTel = new JLabel("연락처");
		lblTel.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblTel);
		
		tfTel = new JTextField();
		tfTel.setColumns(10);
		pContent.add(tfTel);
		
		lblTime = new JLabel("시간");
		lblTime.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblTime);
		
		tfTime = new JTextField();
		tfTime.setColumns(10);
		pContent.add(tfTime);
		
		lblDate1 = new JLabel("대여일자");
		lblDate1.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblDate1);
		
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		calendar.add(Calendar.YEAR, 0);
		Date min = calendar.getTime();
		calendar.add(Calendar.YEAR, 1);
		Date max = calendar.getTime();
		
		SpinnerDateModel sm = new SpinnerDateModel(date, min, max, Calendar.DATE); 
		spDate1 = new JSpinner(sm);
		JSpinner.DateEditor der = new JSpinner.DateEditor(spDate1, "YY.MM.dd");
		spDate1.setEditor(der);
	
		
		pContent.add(spDate1);
		
		lblDate2 = new JLabel("반납일자");
		lblDate2.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblDate2);
		
		spDate2 = new JSpinner();

		pContent.add(spDate2);
		
		lblCarKind = new JLabel("차분류선택");
		lblCarKind.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblCarKind);
		
		
		cbCarKind = new JComboBox<String>();
		String[] items = { "소형", "중형", "승합차", "버스", "지프" };
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(items);
		cbCarKind.setModel(model);
		pContent.add(cbCarKind);
		
		lblCarName = new JLabel("차종 선택");
		lblCarName.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblCarName);
		
		tfCarName = new JTextField();
		tfCarName.setEditable(false);
		pContent.add(tfCarName);
		tfCarName.setColumns(10);
		
		lblMileage = new JLabel("마일리지");
		lblMileage.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblMileage);
		
		tfMileage = new JTextField();
		tfMileage.setColumns(10);
		pContent.add(tfMileage);
		
		lblMileage3 = new JLabel("");
		pContent.add(lblMileage3);
		
		lblMileage2 = new JLabel("P 사용 가능");
		pContent.add(lblMileage2);
		
		lblSale = new JLabel("할인");
		lblSale.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblSale);
		
		tfSale = new JTextField();
		tfSale.setColumns(10);
		pContent.add(tfSale);
		
		lblDiscount1 = new JLabel("장기렌트할인");
		lblDiscount1.setHorizontalAlignment(SwingConstants.CENTER);
		pContent.add(lblDiscount1);
		
		lblDiscount2 = new JLabel("%");
		lblDiscount2.setForeground(Color.RED);
		pContent.add(lblDiscount2);
		
		pTotal = new JPanel();
		getContentPane().add(pTotal);
		pTotal.setLayout(new GridLayout(0, 3, 15, 10));
		
		lblDriver = new JLabel("운전기사 여부");
		lblDriver.setHorizontalAlignment(SwingConstants.RIGHT);
		pTotal.add(lblDriver);
		
		chkDriver = new JCheckBox("");
		chkDriver.addItemListener(this);
		chkDriver.setHorizontalAlignment(SwingConstants.CENTER);
		pTotal.add(chkDriver);
		
		lblNewLabel_1 = new JLabel("");
		pTotal.add(lblNewLabel_1);
		
		lblTotal1 = new JLabel("최종금액");
		lblTotal1.setHorizontalAlignment(SwingConstants.RIGHT);
		pTotal.add(lblTotal1);
		
		lblTotal2 = new JLabel("");
		lblTotal2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal2.setForeground(Color.RED);
		pTotal.add(lblTotal2);
		
		lblTotal3 = new JLabel("원");
		pTotal.add(lblTotal3);
		
		lblNewLabel = new JLabel("대여 비고");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		pTotal.add(lblNewLabel);
		
		textField = new JTextField();
		pTotal.add(textField);
		textField.setColumns(10);
		
		pBtns = new JPanel();
		getContentPane().add(pBtns);
		
		btnConfirm = new JButton("확인");
		pBtns.add(btnConfirm);
		
		btnCancel = new JButton("취소");
		pBtns.add(btnCancel);
	}


	public void setItemFromCar(Car item) {
		tfCarNo.setText(item.getCarNo());
		cbCarKind.setVisible(false);
		JTextField tfCarKind = new JTextField();
		pContent.add(tfCarKind);
		tfCarKind.setText(String.valueOf(item.getCarKind()));
		tfCarName.setText(item.getCarName());
		int fare = item.getFare();
		lblDiscount2.setText(String.valueOf(item.getSale()));
		lblTotal2.setText(String.valueOf(item.getFare()));
		
		
	}
	
	public void setItemFromCustomer(Customer item) {
		tfCtmNo.setText(String.valueOf(item.getNo()));
		tfCtmName.setText(item.getName());
		tfTel.setText(item.getTel());
		lblMileage3.setText(String.valueOf(item.getCtm_mlg()));
	}
	
	private void checkIsDriver(ItemEvent e) {
		
	}
	
	public void actionPerformed(ActionEvent e) {
		String code = tfCode.getText().trim();
		String ctmName = tfCtmName.getText().trim();
		String carNo = tfCarNo.getText().trim();
		String tel = tfTel.getText().trim();
		String time = tfTime.getText().trim();
		Kind carKind = new Kind(cbCarKind.getSelectedItem().toString());
		String carName = tfCarName.getText().trim();
		String mileage = tfMileage.getText().trim();
		
		
	}

}
