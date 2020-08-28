package rentcarTest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import rentcarTest.panel.CarListPanel;
import rentcarTest.panel.CustomerListPanel;
import rentcarTest.panel.HomePanel;
import rentcarTest.panel.MileagePanel;
import rentcarTest.panel.RentListPanel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Point;

public class Home extends JFrame {
	private Image img_logo = new ImageIcon(Home.class.getResource("../res/logout.png")).getImage().getScaledInstance(40,
			40, Image.SCALE_SMOOTH);
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

	private JPanel HomePanel = new JPanel();
	private JLabel lblSignOut;
	private JPanel panel_1;
	
	private Color menuBgColor = new Color(30, 144, 255);

	public Home() {
		initComponents();
	}

	private void initComponents() {
		setTitle("RENT-CAR Management Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(100, 149, 237)));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		// contentPane 안의 panel 선언
		CustomerListPanel = new CustomerListPanel();
		MileagePanel = new MileagePanel();
		
		//HomePanel = new HomePanel();
		// 사이드바 _1_로고
		panemenu = new JPanel();
		panemenu.setPreferredSize(new Dimension(280, 10));
		panemenu.setBackground(menuBgColor);
		contentPane.add(panemenu, BorderLayout.WEST);
		panemenu.setLayout(new BoxLayout(panemenu, BoxLayout.Y_AXIS));

		// 사이드 바_2_고객관리
		CustomerListPane = new JPanel();
		CustomerListPane.addMouseListener(new PanelButtonMouseAdaptor(CustomerListPane) {

			@Override
			public void mouseClicked(MouseEvent e) {
				MenuClicked(CustomerListPanel);
				HomePanel.setVisible(false);

			}
		});
		CustomerListPane.setBackground(menuBgColor);
		CustomerListPane.setForeground(new Color(255, 255, 255));
		// 사이드 바_3_차량관리
		CarListPane = new JPanel();
		CarListPane.addMouseListener(new PanelButtonMouseAdaptor(CarListPane) {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuClicked(CarListPanel);
				HomePanel.setVisible(false);

			}
		});

		CarListPane.setBackground(menuBgColor);
		CarListPane.setForeground(new Color(255, 255, 255));
		CarListPane.setLayout(new BorderLayout(0, 0));

		lblCar = new JLabel("차량관리");
		lblCar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCar.setFont(new Font("인터파크고딕 L", Font.PLAIN, 17));
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
		RentListPane.setBackground(menuBgColor);
		RentListPane.setForeground(new Color(255, 255, 255));
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
		MileagePane.setBackground(menuBgColor);
		MileagePane.setForeground(new Color(255, 255, 255));
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
				MenuClicked(PerformancePane);
				HomePanel.setVisible(false);
			}

		});
		PerformancePane.setBackground(menuBgColor);
		PerformancePane.setForeground(new Color(255, 255, 255));
		PerformancePane.setLayout(new BorderLayout(0, 0));

		lblPerformance = new JLabel("성과현황");
		lblPerformance.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerformance.setFont(new Font("인터파크고딕 L", Font.PLAIN, 17));
		lblPerformance.setForeground(new Color(255, 255, 255));
		PerformancePane.add(lblPerformance);
		// 오른쪽 MainContentPane
		MainContentPane = new JPanel();
		MainContentPane.setPreferredSize(new Dimension(920, 10));
		MainContentPane.setBackground(new Color(255, 255, 255));
		contentPane.add(MainContentPane, BorderLayout.CENTER);

		// 페널 추가
		//MainContentPane.add(HomePanel);
		MainContentPane.add(CustomerListPanel);
		CarListPanel = new CarListPanel();
		MainContentPane.add(CarListPanel);
		RentListPanel = new RentListPanel();
		MainContentPane.add(RentListPanel);
		MileagePanel = new MileagePanel();
		MainContentPane.add(MileagePanel);

//		MainContentPane.add(PerformancePane);
//		 
		MenuClicked(CustomerListPane);
				CustomerListPane.setLayout(new BorderLayout(0, 0));
		
				lblCustomerList = new JLabel("고객관리");
				lblCustomerList.setHorizontalAlignment(SwingConstants.CENTER);
				lblCustomerList.setFont(new Font("인터파크고딕 L", Font.PLAIN, 17));
				lblCustomerList.setForeground(new Color(255, 255, 255));
				CustomerListPane.add(lblCustomerList);
		MenuClicked(CarListPane);
		// 로그아웃 로고 - 클릭시 dispose();
		lblSignOut = new JLabel("");
		lblSignOut.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSignOut.setIcon(new ImageIcon(img_logo));

		HomePane = new JPanel();
		HomePane.setPreferredSize(new Dimension(10, 150));
		HomePane.addMouseListener(new PanelButtonMouseAdaptor(HomePane) {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuClicked(HomePanel);
			}
		});
		panemenu.add(HomePane);
		HomePane.setBackground(menuBgColor);
		HomePane.setForeground(new Color(255, 255, 255));
		HomePane.setLayout(new BoxLayout(HomePane, BoxLayout.X_AXIS));

		lblLogo = new JLabel("Rent_Car");
		HomePane.add(lblLogo);
		lblLogo.setForeground(new Color(255, 255, 255));
		lblLogo.setFont(new Font("인터파크고딕 M", Font.PLAIN, 30));

		panemenu.add(CustomerListPane);
		panemenu.add(CarListPane);
		panemenu.add(RentListPane);
		panemenu.add(MileagePane);
		panemenu.add(PerformancePane);
		
		panel_1 = new JPanel();
		panel_1.setBackground(menuBgColor);
		panel_1.setPreferredSize(new Dimension(10, 190));
		panemenu.add(panel_1);
		panemenu.add(lblSignOut);
	}

	// 오른쪽 MainContentPane 클릭시 보이는 메뉴 컨트롤
	public void MenuClicked(JPanel panel) {

		//HomePanel.setVisible(true);
		CarListPanel.setVisible(false);
		RentListPanel.setVisible(false);
		CustomerListPanel.setVisible(false);
		MileagePanel.setVisible(false);
//		PerformancePanel.setVisible(false);

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
			panel.setBackground(menuBgColor);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(220, 220, 220));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(menuBgColor);
		}

	}
}
