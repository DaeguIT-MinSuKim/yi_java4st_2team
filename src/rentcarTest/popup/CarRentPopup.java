package rentcarTest.popup;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import rentcarTest.Dao.service.RentService;
import rentcarTest.dto.Car;
import rentcarTest.dto.Customer;
import rentcarTest.dto.Kind;
import rentcarTest.dto.Rent;
import rentcarTest.panel.RentListPanel;


public class CarRentPopup extends AbstractItemPopup<Rent> implements ActionListener, ItemListener, MouseListener{
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
	private JLabel lblDriver;
	private JCheckBox chkDriver;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	private JLabel lblNewLabel;
	
	private RentService service = new RentService();
	private RentListPanel rentList = new RentListPanel();
	private JDateChooser dateRent;
	private JDateChooser dateReturn;
	private JTextField tfCarKind;
	private int driverCheck = 0;
	
	Car selectCar = new Car();
	
	public void setCarRentList(RentListPanel rentList) {
		this.rentList = rentList;
	}
	
	
	public CarRentPopup() {
		initComponents();
	}

	
	public void initComponents() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		setBounds(100, 100, 450, 450);
		
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
		tfCtmName.addMouseListener(this);
		tfCtmName.setColumns(10);
		pContent.add(tfCtmName);
		
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
		tfTime.setEditable(false);
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
		
		dateRent = new JDateChooser();
		pContent.add(dateRent);
		
		lblDate2 = new JLabel("반납일자");
		lblDate2.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblDate2);
		
		dateReturn = new JDateChooser();
		pContent.add(dateReturn);
		
		lblCarKind = new JLabel("차분류선택");
		lblCarKind.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblCarKind);
		String[] items = { "소형", "중형", "승합차", "버스", "지프" };
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(items);
		
		tfCarKind = new JTextField();
		tfCarKind.setEditable(false);
		pContent.add(tfCarKind);
		tfCarKind.setColumns(10);
		
		lblCarName = new JLabel("차종 선택");
		lblCarName.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblCarName);
		
		tfCarName = new JTextField();
		tfCarName.addMouseListener(this);
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
		tfSale.setEditable(false);
		tfSale.setColumns(10);
		pContent.add(tfSale);
		
		lblDiscount1 = new JLabel("장기렌트할인");
		lblDiscount1.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblDiscount2 = new JLabel("%");
		lblDiscount2.setForeground(Color.RED);
		
		lblDiscount1.setVisible(false);
		lblDiscount2.setVisible(false);
		
		pContent.add(lblDiscount1);
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
		btnConfirm.addActionListener(this);
		pBtns.add(btnConfirm);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtns.add(btnCancel);
	}	

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == chkDriver) {
			selectSearchCheckedDriver(e);
		}
		
	}


	private void selectSearchCheckedDriver(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			driverCheck = 1;
		} else {
			driverCheck = 0;
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource());
		if (e.getActionCommand().equals("확인")) {
			actionPerformedBtnAdd(e);
		}
		if (e.getActionCommand().equals("추가")) {
			actionPerformedBtnRentAdd(e);
		}
		if (e.getSource() == btnCancel) {
			System.out.println("취소");
		}
		
	}
	
	private void actionPerformedBtnRentAdd(ActionEvent e) {
		
		if (Integer.parseInt(tfMileage.getText().trim()) > Integer.parseInt(lblMileage3.getText().trim())) {
			JOptionPane.showMessageDialog(null, "보유하신 마일리지 금액보다 더 많은 금액입니다. 다시 작성해주세요.");
			tfSale.setText("");
			btnConfirm.setText("확인");
			return;
		}
		
		List<Rent> list = service.showRents();
		int num = list.get(list.size() - 1).getRent_no() + 1;

		Customer ctm = new Customer();
		ctm.setNo(Integer.parseInt(tfCode.getText().trim()));
		ctm.setName(tfCtmName.getText().trim());
		ctm.setTel(tfTel.getText().trim());
		
		Kind kind = new Kind();
		kind.setKind_name(tfCarKind.getText().trim());
		Car car = selectCar;
		car.setCarKind(kind);
		
		
		Rent item = new Rent();
		item.setRent_no(num);
		item.setCtm_no(ctm);
		item.setCar_no(car);
		item.setRent_date(dateRent.getDate());
		item.setReturn_date(dateReturn.getDate());
		item.setRent_time(Integer.parseInt(tfTime.getText().trim()));
		item.setIs_driver(driverCheck);
		item.setRent_remark(textField.getText());
		
		System.out.println(item);
		service.insertRent(item);
		rentList.insertRent(item);
		this.dispose();
	}


	private void actionPerformedBtnAdd(ActionEvent e) {
		/*if (isTfEmpty()) {
			JOptionPane.showMessageDialog(null, "고객정보, 대여날짜, 차량정보를 선택해주세요.");
		}*/
		
		String ctm_mlg = tfMileage.getText().trim();
		
		Customer ctm = new Customer();
		ctm.setNo(Integer.parseInt(tfCode.getText().trim()));
		ctm.setName(tfCtmName.getText().trim());
		ctm.setTel(tfTel.getText().trim());
		
		Kind kind = new Kind();
		kind.setKind_name(tfCarKind.getText().trim());
		Car car = selectCar;
		car.setCarKind(kind);
		
		Rent item = new Rent();
		item.setCtm_no(ctm);
		item.setCar_no(car);
		item.setRent_date(dateRent.getDate());
		item.setReturn_date(dateReturn.getDate());
		item.setRent_time((int) ((dateReturn.getDate().getTime() - dateRent.getDate().getTime()) / (60 * 60 * 1000)));
		item.setIs_driver(driverCheck);

		long totalPrice = car.getFare() * item.getRent_time();
		
		if (ctm_mlg.equals("")) {
			ctm_mlg = "0";
			tfMileage.setText("0");
		}

		

		if (Integer.parseInt(ctm_mlg) > Integer.parseInt(lblMileage3.getText())) {
			JOptionPane.showMessageDialog(null, "보유하신 마일리지 금액보다 더 많은 금액입니다. 다시 작성해주세요.");
			tfSale.setText("");
			return;
		}
		
		if (item.getRent_time() >= 720) {
			lblDiscount1.setVisible(true);
			lblDiscount2.setVisible(true);
			totalPrice = totalPrice - (long)((double)totalPrice * ((item.getCar_no().getSale() / 100f))) - Integer.parseInt(ctm_mlg);
			tfSale.setText(String.format("%s", (long)((double)totalPrice * ((item.getCar_no().getSale() / 100f)) + Integer.parseInt(ctm_mlg))));
		} else {
			tfSale.setText(ctm_mlg);			
			totalPrice -= Integer.parseInt(ctm_mlg);
		}
		
		tfTime.setText(String.format("%s", item.getRent_time()));
		lblTotal2.setText(String.format("%,d", totalPrice));
		
		btnConfirm.setText("추가");
		
		System.out.println(item);
		
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tfCarName) {
			mouseClickedTfCarName(e);
		}
		if (e.getSource() == tfCtmName) {
			mouseClickedTfCtmName(e);
		}
	}

	public void mouseEntered(MouseEvent e) {
		System.out.println("mouseEntered");
	}

	public void mouseExited(MouseEvent e) {
		System.out.println("mouseExited");
	}

	public void mousePressed(MouseEvent e) {
		System.out.println("mousePressed");
	}

	public void mouseReleased(MouseEvent e) {
		System.out.println("mouseReleased");
	}

	protected void mouseClickedTfCtmName(MouseEvent e) {
		System.out.println("mouseClickedTfCtmName");
		CustomerSearchPopup ctmSchPopup = new CustomerSearchPopup();
		ctmSchPopup.setCtmList(this);
		ctmSchPopup.setVisible(true);
	}
	
	protected void mouseClickedTfCarName(MouseEvent e) {
		System.out.println("mouseClickedTfCarName");
		CarSearchPopup carSchPopup = new CarSearchPopup();
		carSchPopup.setCarList(this);
		carSchPopup.setVisible(true);
	}
	
	
	
	public void setCustomer(Customer ctm) {
		tfCode.setText(String.format("%s", ctm.getNo()));
		tfCtmName.setText(ctm.getName());
		tfTel.setText(ctm.getTel());
		lblMileage3.setText(String.format("%s", ctm.getMile()));
		
	}
	
	public void setCar(Car car) {
		tfCarKind.setText(car.getCarKind().getKind_name());
		tfCarName.setText(car.getCarName());
		lblDiscount2.setText(String.format("%s %%", car.getSale()));
		
		selectCar = car;
		
	}

	@Override
	boolean isValidTf() {

		String ctmName = tfCtmName.getText().trim();
		String ctmTel = tfTel.getText().trim();
		Date rentDate = dateRent.getDate();
		Date returnDate = dateReturn.getDate();
		String carName = tfCarName.getText().trim();
		return ctmName == null && ctmTel == null && rentDate == null && returnDate == null && carName ==null;
	}


	@Override
	public void setItem(Rent item) {
		
	}
}
