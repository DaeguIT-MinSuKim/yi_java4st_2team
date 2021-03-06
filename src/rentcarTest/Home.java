package rentcarTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import rentcarTest.panel.CalFrame;
import rentcarTest.panel.CarListPanel;
import rentcarTest.panel.CustomerListPanel;
import rentcarTest.panel.Homepanel;
import rentcarTest.panel.MileagePanel;
import rentcarTest.panel.PerformanceListPanel;
import rentcarTest.panel.RentListPanel;

public class Home extends JFrame {
	private Image img_logo = new ImageIcon(Home.class.getResource("../res/logout.png")).getImage().getScaledInstance(40,
			40, Image.SCALE_SMOOTH);
	private Image img_person = new ImageIcon(Home.class.getResource("../res/person.png")).getImage()
			.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	private Image img_todo = new ImageIcon(Home.class.getResource("../res/things.png")).getImage().getScaledInstance(25,
			25, Image.SCALE_SMOOTH);
	private Image img_car = new ImageIcon(Home.class.getResource("../res/carrent.png")).getImage()
			.getScaledInstance(200, 50, Image.SCALE_SMOOTH);
	private JPanel contentPane;
	private JPanel panemenu;
	private JPanel RentListPane;
	private JPanel CustomerListPane;
	private JLabel lblLogo;
	private JPanel CarListPane;
	private JPanel MileagePane;
	private JPanel PerformancePane;
	private JPanel HomePane;
	private JLabel lblRent;
	private JLabel lblCustomerList;
	private JLabel lblCar;
	private JLabel lblMileage;
	private JLabel lblPerformance;
	private JPanel MainContentPane;

	private JPanel CustomerListPanel;
	private JPanel CarListPanel;
	private JPanel RentListPanel;
	private JPanel MileagePanel;
	private JPanel HomePanel;
	private JLabel lblSignOut;
	private JLabel lblPerson;
	private JLabel lblWelcome;
	private JPanel paneTodo;
	private JLabel lbltodo;
	private JLabel lblScheduler;
	private JLabel lblMonthDate;
	private int ThisMonth;
	private int ThisDate;
	private String Month;
	private String Date;
	Calendar today = Calendar.getInstance();
	private JPanel PerformancePanel;

	public Home() {
		initComponents();
		setResizable(false);
	}

	private void initComponents() {
		setTitle("RENT-CAR Management Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 752);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(100, 149, 237)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// contentPane 안의 panel 선언
		CustomerListPanel = new CustomerListPanel();
		MileagePanel = new MileagePanel();
		// 사이드바 _1_로고
		panemenu = new JPanel();
		panemenu.setBackground(new Color(30, 144, 255));
		panemenu.setBounds(0, 0, 260, 723);
		contentPane.add(panemenu);
		panemenu.setLayout(null);

		// 사이드 바_2_고객관리
		CustomerListPane = new JPanel();
		CustomerListPane.addMouseListener(new PanelButtonMouseAdaptor(CustomerListPane) {

			@Override
			public void mouseClicked(MouseEvent e) {
				MenuClicked(CustomerListPanel);
				HomePanel.setVisible(false);

			}
		});
		CustomerListPane.setBackground(new Color(30, 144, 255));
		CustomerListPane.setForeground(new Color(255, 255, 255));
		CustomerListPane.setBounds(0, 95, 250, 60);
		panemenu.add(CustomerListPane);
		// 사이드 바_3_차량관리
		CarListPane = new JPanel();
		CarListPane.addMouseListener(new PanelButtonMouseAdaptor(CarListPane) {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuClicked(CarListPanel);
				HomePanel.setVisible(false);

			}
		});

		CarListPane.setBackground(new Color(30, 144, 255));
		CarListPane.setForeground(new Color(255, 255, 255));
		CarListPane.setBounds(0, 155, 250, 60);
		panemenu.add(CarListPane);
		CarListPane.setLayout(new BorderLayout(0, 0));

		lblCar = new JLabel("차량관리");
		lblCar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCar.setFont(new Font("S-CORE DREAM", Font.PLAIN, 17));
		lblCar.setForeground(new Color(255, 255, 255));
		CarListPane.add(lblCar);
		// 사이드 바_4_대여관리
		RentListPane = new JPanel();
		RentListPane.addMouseListener(new PanelButtonMouseAdaptor(RentListPane) {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuClicked(RentListPanel);
				HomePanel.setVisible(false);

			}
		});
		RentListPane.setBackground(new Color(30, 144, 255));
		RentListPane.setForeground(new Color(255, 255, 255));
		RentListPane.setBounds(0, 215, 250, 60);
		panemenu.add(RentListPane);
		RentListPane.setLayout(new BorderLayout(0, 0));

		lblRent = new JLabel("대여관리");
		lblRent.setHorizontalAlignment(SwingConstants.CENTER);
		lblRent.setFont(new Font("인터파크고딕 L", Font.PLAIN, 17));
		lblRent.setForeground(new Color(255, 255, 255));
		RentListPane.add(lblRent);
		// 사이드 바_5_마일리지
		MileagePane = new JPanel();
		MileagePane.addMouseListener(new PanelButtonMouseAdaptor(MileagePane) {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuClicked(MileagePanel);
				HomePanel.setVisible(false);
			}
		});
		MileagePane.setBackground(new Color(30, 144, 255));
		MileagePane.setForeground(new Color(255, 255, 255));
		MileagePane.setBounds(0, 275, 250, 60);
		panemenu.add(MileagePane);
		MileagePane.setLayout(new BorderLayout(0, 0));

		lblMileage = new JLabel("마일리지");
		lblMileage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMileage.setForeground(new Color(255, 255, 255));
		lblMileage.setFont(new Font("인터파크고딕 L", Font.PLAIN, 17));
		MileagePane.add(lblMileage);
		// 사이드 바_6_성과현황
		PerformancePane = new JPanel();
		PerformancePane.addMouseListener(new PanelButtonMouseAdaptor(PerformancePane) {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuClicked(PerformancePanel);
				HomePanel.setVisible(false);
			}

		});
		PerformancePane.setBackground(new Color(30, 144, 255));
		PerformancePane.setForeground(new Color(255, 255, 255));
		PerformancePane.setBounds(0, 335, 250, 60);
		panemenu.add(PerformancePane);
		PerformancePane.setLayout(new BorderLayout(0, 0));

		lblPerformance = new JLabel("성과현황");
		lblPerformance.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerformance.setFont(new Font("인터파크고딕 L", Font.PLAIN, 17));
		lblPerformance.setForeground(new Color(255, 255, 255));
		PerformancePane.add(lblPerformance);

		ThisMonth = today.get(Calendar.MONTH) + 1;
		ThisDate = today.get(Calendar.DATE);
		Month = Integer.toString(ThisMonth);
		Date = Integer.toString(ThisDate);

		// 오른쪽 MainContentPane
		MainContentPane = new JPanel();
		MainContentPane.setBackground(new Color(255, 255, 255));
		MainContentPane.setBounds(252, -12, 942, 735);
		contentPane.add(MainContentPane);

		// 페널 추가
		// MainContentPane.add(HomePanel);
		MainContentPane.add(CustomerListPanel);
		CarListPanel = new CarListPanel();
		MainContentPane.add(CarListPanel);
		RentListPanel = new RentListPanel();
		MainContentPane.add(RentListPanel);
		MileagePanel = new MileagePanel();
		MainContentPane.add(MileagePanel);
		HomePanel = new Homepanel();
		MainContentPane.add(HomePanel);
		PerformancePanel = new PerformanceListPanel();
		MainContentPane.add(PerformancePanel);

//		MainContentPane.add(PerformancePane);
//		 
		MenuClicked(CustomerListPane);
		CustomerListPane.setLayout(new BorderLayout(0, 0));

		lblCustomerList = new JLabel("고객관리");
		lblCustomerList.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerList.setFont(new Font("에스코어 드림 4", Font.PLAIN, 17));
		lblCustomerList.setForeground(new Color(255, 255, 255));
		CustomerListPane.add(lblCustomerList);
		MenuClicked(CarListPane);
		// 로그아웃 로고 - 클릭시 dispose();
		// lblSignOut = new JLabel("");
		// lblSignOut.setBounds(181, 580, 49, 71);
		// lblSignOut.setIcon(new ImageIcon(img_logo));

		// panemenu.add(lblSignOut);

		HomePane = new JPanel();
		HomePane.addMouseListener(new PanelButtonMouseAdaptor(HomePane) {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuClicked(HomePanel);
			}
		});
		HomePane.setBounds(0, 10, 250, 79);
		panemenu.add(HomePane);
		HomePane.setBackground(new Color(30, 144, 255));
		HomePane.setForeground(new Color(255, 255, 255));
		HomePane.setLayout(null);

		lblLogo = new JLabel("RENTCAR");
		lblLogo.setBounds(43, 0, 195, 79);
		HomePane.add(lblLogo);
		lblLogo.setForeground(new Color(255, 255, 255));
		lblLogo.setFont(new Font("인터파크고딕 M", Font.BOLD, 36));
		// lblLogo.setIcon(new ImageIcon(img_car));

		lblWelcome = new JLabel("관리자님, 환영합니다.");
		lblWelcome.setForeground(new Color(0, 0, 0));
		lblWelcome.setBounds(55, 573, 183, 53);
		panemenu.add(lblWelcome);
		lblWelcome.setFont(new Font("인터파크고딕 L", Font.BOLD, 17));

		lblPerson = new JLabel("");
		lblPerson.setBounds(95, 495, 77, 82);
		panemenu.add(lblPerson);
		lblPerson.setIcon(new ImageIcon(img_person));

		paneTodo = new JPanel();
		paneTodo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				CalFrame calframe = new CalFrame();
			}
		});
		paneTodo.setBounds(64, 651, 114, 39);
		panemenu.add(paneTodo);
		paneTodo.setBackground(new Color(30, 144, 255));

		lbltodo = new JLabel("");
		paneTodo.add(lbltodo);
		lbltodo.setIcon(new ImageIcon(img_todo));
		paneTodo.add(lbltodo);

		lblScheduler = new JLabel("스케쥴러");
		lblScheduler.setForeground(new Color(0, 0, 0));
		lblScheduler.setFont(new Font("인터파크고딕 L", Font.PLAIN, 17));
		paneTodo.add(lblScheduler);

		lblMonthDate = new JLabel(Month + "/" + Date /* + Hour + "시 " + Minute +"분 " */);
		lblMonthDate.setBounds(114, 416, 146, 123);
		panemenu.add(lblMonthDate);
		lblMonthDate.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));

	}

	// 오른쪽 MainContentPane 클릭시 보이는 메뉴 컨트롤
	public void MenuClicked(JPanel panel) {
		HomePanel.setVisible(true);
		CarListPanel.setVisible(false);
		RentListPanel.setVisible(false);
		CustomerListPanel.setVisible(false);
		MileagePanel.setVisible(false);
		PerformancePanel.setVisible(false);

		panel.setVisible(true);
	}

	// 마우스 올렸을 떄 색이 변하는 사이드바
	private class PanelButtonMouseAdaptor extends MouseAdapter {
		JPanel panel;

		public PanelButtonMouseAdaptor(JPanel panel) {
			this.panel = panel;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(new Color(220, 220, 220));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			panel.setBackground(new Color(135, 206, 250));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(135, 206, 250));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(new Color(30, 144, 255));
		}

	}
}