package rentcarTest.popup;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import rentcarTest.Dao.CarDao;
import rentcarTest.Dao.Impl.CarDaoImpl;
import rentcarTest.Dao.service.CarService;
import rentcarTest.conn.JdbcUtil;
import rentcarTest.dto.Car;
import rentcarTest.dto.Kind;
import rentcarTest.panel.CarListPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;

public class AddCarPopup extends JDialog implements ActionListener {
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
	private JPanel pContent;

	private CarService service = new CarService();
	private CarListPanel carlist = new CarListPanel();

	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd = null;
	Connection con = JdbcUtil.getConnection();

	public AddCarPopup() {
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("차량 추가\r\n");
		setBounds(100, 100, 450, 370);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JLabel lblTitle = new JLabel("차량 추가");
		lblTitle.setFont(new Font("굴림", Font.BOLD, 20));
		lblTitle.setAlignmentY(0.0f);
		lblTitle.setAlignmentX(0.5f);
		getContentPane().add(lblTitle);

		pContent = new JPanel();
		pContent.setBorder(new EmptyBorder(15, 15, 15, 15));
		getContentPane().add(pContent);
		pContent.setLayout(new GridLayout(0, 2, 20, 10));

		lblCarKind = new JLabel("차분류");
		lblCarKind.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCarKind.setFont(new Font("굴림", Font.PLAIN, 15));
		pContent.add(lblCarKind);

		cbCarKind = new JComboBox<String>();
		String[] items = { "소형", "중형", "승합차", "버스", "지프" };
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(items);
		cbCarKind.setModel(model);
		pContent.add(cbCarKind);

		lblCarName = new JLabel("차종");
		lblCarName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCarName.setFont(new Font("굴림", Font.PLAIN, 15));
		pContent.add(lblCarName);

		tfCarName = new JTextField();
		tfCarName.setColumns(10);
		pContent.add(tfCarName);

		lblCarNo = new JLabel("차번호");
		lblCarNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCarNo.setFont(new Font("굴림", Font.PLAIN, 15));
		pContent.add(lblCarNo);

		tfCarNo = new JTextField();
		tfCarNo.setColumns(10);
		pContent.add(tfCarNo);

		lblFuel = new JLabel("연료");
		lblFuel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuel.setFont(new Font("굴림", Font.PLAIN, 15));
		pContent.add(lblFuel);

		cbFuel = new JComboBox<String>();
		String[] fuel = { "LPG", "전기", "휘발유", "휘발휘발", "경유" };
		DefaultComboBoxModel<String> fmodel = new DefaultComboBoxModel<String>(fuel);
		cbFuel.setModel(fmodel);

		pContent.add(cbFuel);

		lblDistance = new JLabel("주행 거리");
		lblDistance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDistance.setFont(new Font("굴림", Font.PLAIN, 15));
		pContent.add(lblDistance);

		tfDistance = new JTextField();
		tfDistance.setColumns(10);
		pContent.add(tfDistance);

		lblFare = new JLabel("기본요금");
		lblFare.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFare.setFont(new Font("굴림", Font.PLAIN, 15));
		pContent.add(lblFare);

		tfFare = new JTextField();
		tfFare.setColumns(10);
		pContent.add(tfFare);

		lblSale = new JLabel("장기대여 할인");
		lblSale.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSale.setFont(new Font("굴림", Font.PLAIN, 15));
		pContent.add(lblSale);

		tfSale = new JTextField();
		tfSale.setColumns(10);
		pContent.add(tfSale);

		lblCRemark = new JLabel("비고");
		lblCRemark.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCRemark.setFont(new Font("굴림", Font.PLAIN, 15));
		pContent.add(lblCRemark);

		tfCRemark = new JTextField();
		tfCRemark.setColumns(10);
		pContent.add(tfCRemark);

		pBtns = new JPanel();
		getContentPane().add(pBtns);
		pBtns.setLayout(new BoxLayout(pBtns, BoxLayout.X_AXIS));

		btnConfirm = new JButton("확인");
		btnConfirm.addActionListener((ActionListener) this);
		pBtns.add(btnConfirm);

		btnCancel = new JButton("취소");
		pBtns.add(btnCancel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnConfirm) {
			actionPerformedBtnConfirm(e);
		}
		if (e.getSource() == btnCancel) {
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
		Car item = new Car(carNo, carName, carKind, fuel, distance, fare, sale, carRemark);
		service.insertCar(item);

	}

	public static void main(String[] args) {

	}
}
