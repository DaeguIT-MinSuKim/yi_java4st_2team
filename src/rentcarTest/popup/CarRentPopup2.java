package rentcarTest.popup;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import rentcarTest.Dao.Impl.CarDaoImpl;
import rentcarTest.dto.Car;

public class CarRentPopup2 extends JPanel implements ActionListener {
	private JLabel lblTitle;
	private JPanel pContent;
	private JLabel lblCode;
	private JTextField tfCode;
	private JLabel lblCtmName;
	private JTextField tfCtmName;
	private JLabel lblTel;
	private JTextField tfTel;
	private JLabel lblTime;
	private JTextField tfTime;
	private JLabel lblDate1;
	private JLabel lblDate2;
	private JTextField tfDate2;
	private JLabel lblCarKind;
	private JTextField tfDate1;
	private JLabel lblMileage2;
	private JLabel lblMileage3;
	private JLabel lblMileage;
	private JTextField tfMileage;
	private JLabel lblSale;
	private JLabel lblDiscount;
	private JComboBox cbCarKind;
	private JPanel pTotal;
	private JLabel lblTotal1;
	private JLabel lblTotal2;
	private JLabel lblTotal3;
	private JLabel lblCarName;
	private JComboBox cbCarName;
	private JTextField tfSale;
	private JLabel lblDiscount2;
	private JPanel pBtns;
	private JButton btnConfirm;
	private JButton btnCancel;

	/**
	 * Create the panel.
	 */
	public CarRentPopup2() {

		initComponents();
	}
	private void initComponents() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		lblTitle = new JLabel("차량 대여");
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitle.setFont(new Font("굴림", Font.BOLD, 20));
		add(lblTitle);
		
		pContent = new JPanel();
		add(pContent);
		pContent.setLayout(new GridLayout(0, 4, 15, 7));
		
		lblCode = new JLabel("코드 번호");
		lblCode.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblCode);
		
		tfCode = new JTextField();
		pContent.add(tfCode);
		tfCode.setColumns(10);
		
		lblCtmName = new JLabel("고객명");
		lblCtmName.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblCtmName);
		
		tfCtmName = new JTextField();
		pContent.add(tfCtmName);
		tfCtmName.setColumns(10);
		
		lblTel = new JLabel("연락처");
		lblTel.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblTel);
		
		tfTel = new JTextField();
		pContent.add(tfTel);
		tfTel.setColumns(10);
		
		lblTime = new JLabel("시간");
		lblTime.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblTime);
		
		tfTime = new JTextField();
		pContent.add(tfTime);
		tfTime.setColumns(10);
		
		lblDate1 = new JLabel("대여일자");
		lblDate1.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblDate1);
		
		tfDate1 = new JTextField();
		pContent.add(tfDate1);
		tfDate1.setColumns(10);
		
		lblDate2 = new JLabel("반납일자");
		lblDate2.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblDate2);
		
		tfDate2 = new JTextField();
		pContent.add(tfDate2);
		tfDate2.setColumns(10);
		
		lblCarKind = new JLabel("차분류선택");
		lblCarKind.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblCarKind);
		
		String[] kind = {"소형" , "중형", "승합차", "버스", "지프"};
		cbCarKind = new JComboBox(kind);
		pContent.add(cbCarKind);
		
		lblCarName = new JLabel("차종 선택");
		lblCarName.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblCarName);
		
		
		List<Car> car = CarDaoImpl.getInstance().selectCarByAll();
		cbCarName = new JComboBox();
		pContent.add(cbCarName);
		
		lblMileage = new JLabel("마일리지");
		lblMileage.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblMileage);
		
		tfMileage = new JTextField();
		pContent.add(tfMileage);
		tfMileage.setColumns(10);
		
		lblMileage2 = new JLabel("P");
		lblMileage2.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblMileage2);
		
		lblMileage3 = new JLabel("");
		pContent.add(lblMileage3);
		
		lblSale = new JLabel("할인");
		lblSale.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblSale);
		
		tfSale = new JTextField();
		pContent.add(tfSale);
		tfSale.setColumns(10);
		
		lblDiscount = new JLabel("장기렌트할인");
		lblDiscount.setHorizontalAlignment(SwingConstants.CENTER);
		pContent.add(lblDiscount);
		
		lblDiscount2 = new JLabel("5%");
		lblDiscount2.setForeground(Color.RED);
		pContent.add(lblDiscount2);
		
		pTotal = new JPanel();
		add(pTotal);
		pTotal.setLayout(new GridLayout(0, 3, 15, 10));
		
		lblTotal1 = new JLabel("최종금액");
		lblTotal1.setHorizontalAlignment(SwingConstants.CENTER);
		pTotal.add(lblTotal1);
		
		lblTotal2 = new JLabel("");
		lblTotal2.setForeground(Color.RED);
		lblTotal2.setHorizontalAlignment(SwingConstants.RIGHT);
		pTotal.add(lblTotal2);
		
		lblTotal3 = new JLabel("원");
		pTotal.add(lblTotal3);
		
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
		}
	}
	protected void actionPerformedBtnConfirm(ActionEvent e) {
		
	}
	
protected void actionPerformedBtnCancel(ActionEvent e) {
		
	}
}
