package rentcarTest.popup;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import javax.swing.border.EmptyBorder;

import rentcarTest.Dao.CarDao;
import rentcarTest.Dao.Impl.CarDaoImpl;
import rentcarTest.dto.Car;
import rentcarTest.dto.Kind;

import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class AddCarPopup2 extends JPanel implements ActionListener {
	private JLabel lblTitle;
	private JPanel pAdd;
	private JLabel lblCarKind;
	private JLabel lblCarName;
	private JTextField tfCarName;
	private JLabel lblCarNo;
	private JTextField tfCarNo;
	private JLabel lblFuel;
	private JLabel lblFare;
	private JTextField tfFare;
	private JLabel lblSale;
	private JTextField tfSale;
	private JLabel lblCRemark;
	private JTextField tfCRemark;
	private JComboBox cbCarKind;
	private JComboBox cbFuel;
	private JPanel pBtns;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JLabel lblDistance;
	private JTextField tfDistance;
	private CarDao dao = CarDaoImpl.getInstance();
	/**
	 * Create the panel.
	 */
	public AddCarPopup2() {
		
		initComponents();
	}
	private void initComponents() {
		setBorder(new EmptyBorder(20, 20, 20, 20));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		lblTitle = new JLabel("차량 추가");
		lblTitle.setAlignmentY(Component.TOP_ALIGNMENT);
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitle.setFont(new Font("굴림", Font.BOLD, 20));
		add(lblTitle);
		
		pAdd = new JPanel();
		pAdd.setLayout(new GridLayout(0, 2, 20, 10));
		add(pAdd);
				
		lblCarKind = new JLabel("차분류");
		lblCarKind.setFont(new Font("굴림", Font.PLAIN, 15));
		lblCarKind.setHorizontalAlignment(SwingConstants.RIGHT);
		pAdd.add(lblCarKind);
		
		String[] kind = {"소형" , "중형", "승합차", "버스", "지프"};
		cbCarKind = new JComboBox(kind);
		
		
		
		pAdd.add(cbCarKind);
		
		lblCarName = new JLabel("차종");
		lblCarName.setFont(new Font("굴림", Font.PLAIN, 15));
		lblCarName.setHorizontalAlignment(SwingConstants.RIGHT);
		pAdd.add(lblCarName);
		
		tfCarName = new JTextField();
		pAdd.add(tfCarName);
		tfCarName.setColumns(10);
		
		lblCarNo = new JLabel("차번호");
		lblCarNo.setFont(new Font("굴림", Font.PLAIN, 15));
		lblCarNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pAdd.add(lblCarNo);
		
		tfCarNo = new JTextField();
		pAdd.add(tfCarNo);
		tfCarNo.setColumns(10);
		
		lblFuel = new JLabel("연료");
		lblFuel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblFuel.setHorizontalAlignment(SwingConstants.RIGHT);
		pAdd.add(lblFuel);
		
		String[] fuel = {"휘발유", "경유", "LPG", "전기"};
		cbFuel = new JComboBox(fuel);
		pAdd.add(cbFuel);
		
		lblDistance = new JLabel("주행 거리");
		lblDistance.setFont(new Font("굴림", Font.PLAIN, 15));
		lblDistance.setHorizontalAlignment(SwingConstants.RIGHT);
		pAdd.add(lblDistance);
		
		tfDistance = new JTextField();
		pAdd.add(tfDistance);
		tfDistance.setColumns(10);
		
		lblFare = new JLabel("기본요금");
		lblFare.setFont(new Font("굴림", Font.PLAIN, 15));
		lblFare.setHorizontalAlignment(SwingConstants.RIGHT);
		pAdd.add(lblFare);
		
		tfFare = new JTextField();
		pAdd.add(tfFare);
		tfFare.setColumns(10);
		
		lblSale = new JLabel("장기대여 할인");
		lblSale.setFont(new Font("굴림", Font.PLAIN, 15));
		lblSale.setHorizontalAlignment(SwingConstants.RIGHT);
		pAdd.add(lblSale);
		
		tfSale = new JTextField();
		pAdd.add(tfSale);
		tfSale.setColumns(10);
		
		lblCRemark = new JLabel("비고");
		lblCRemark.setFont(new Font("굴림", Font.PLAIN, 15));
		lblCRemark.setHorizontalAlignment(SwingConstants.RIGHT);
		pAdd.add(lblCRemark);
		
		tfCRemark = new JTextField();
		pAdd.add(tfCRemark);
		tfCRemark.setColumns(10);
		
		pBtns = new JPanel();
		add(pBtns);
		pBtns.setLayout(new BoxLayout(pBtns, BoxLayout.X_AXIS));
		
		btnConfirm = new JButton("확인");
		btnConfirm.addActionListener(this);
		pBtns.add(btnConfirm);
		
		btnCancel = new JButton("취소");
		pBtns.add(btnCancel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnConfirm) {
			actionPerformedBtnConfirm(e);
		}if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
	}
	private void actionPerformedBtnCancel(ActionEvent e) {
		tfCarName.setText("");
		tfCarNo.setText("");
		tfDistance.setText("");
		tfFare.setText("");
		tfSale.setText("");
		tfCRemark.setText("");
		System.out.println("취소");
		
	}
	private void actionPerformedBtnConfirm(ActionEvent e) {
		Kind carKind = new Kind(cbCarKind.getSelectedItem().toString());
		String carName = tfCarName.getText();
		String carNo = tfCarNo.getText();
		String fuel = cbFuel.getSelectedItem().toString();
		int distance = Integer.parseInt(tfDistance.getText());
		int fare = Integer.parseInt(tfFare.getText());
		int sale = Integer.parseInt(tfSale.getText());
		String carRemark = tfCRemark.getText();
		System.out.println("확인");
		System.out.println(carKind);
		System.out.println(fuel);
		Car car = new Car(carNo, carName, carKind, fuel, distance, fare, sale, carRemark);
		dao.insertCar(car);
		
		
	}

}
