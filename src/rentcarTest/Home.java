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
import rentcarTest.panel.RentListPanel;

public class Home extends JFrame{
	private Image img_logo = new ImageIcon(Home.class.getResource("res/logout.png")).getImage().getScaledInstance(40, 40,Image.SCALE_SMOOTH);
	private JPanel contentPane;
	private JPanel panemenu;
	private JPanel RentListPane;
	private JPanel CustomerListPane;
	private JLabel lblLogo;
	private JPanel CarListPane;
	private JPanel MileagePane;
	private JPanel PerformancePane;
	private JLabel lblRent;
	private JLabel lblCustomerList;
	private JLabel lblCar;
	private JLabel lblMileage;
	private JLabel lblPerformance;
	private JPanel MainContentPane;
	
	private JPanel CustomerListPanel;
	private JPanel CarListPanel;
	private JPanel RentListPanel;
	private JLabel lblSignOut;

	public Home() {
		
		initComponents();
	}
	private void initComponents() {
		setTitle("RENT-CAR Management Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(100, 149, 237)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CustomerListPanel  = new CustomerListPanel();
		CarListPanel = new CarListPanel();
		RentListPanel = new RentListPanel();
		
		//로고
		panemenu = new JPanel();
		panemenu.setBackground(new Color(30, 144, 255));
		panemenu.setBounds(0, 0, 236, 661);
		contentPane.add(panemenu);
		panemenu.setLayout(null);
		
		lblLogo = new JLabel("Rent-Car");
		lblLogo.setForeground(new Color(255, 255, 255));
		lblLogo.setFont(new Font("인터파크고딕 M", Font.PLAIN, 30));
		lblLogo.setBounds(33, 30, 160, 51);
		panemenu.add(lblLogo);
		
			
		CustomerListPane = new JPanel();
		CustomerListPane.addMouseListener(new PanelButtonMouseAdaptor(CustomerListPane) {

			@Override
			public void mouseClicked(MouseEvent e) {
				MenuClicked(CustomerListPanel);
			}
			
		});
		CustomerListPane.setBackground(new Color(30, 144, 255));
		CustomerListPane.setForeground(new Color(255, 255, 255));
		CustomerListPane.setBounds(0, 95, 230, 60);
		panemenu.add(CustomerListPane);
		
		lblCustomerList = new JLabel("고객관리");
		lblCustomerList.setFont(new Font("인터파크고딕 L", Font.PLAIN, 17));
		lblCustomerList.setForeground(new Color(255, 255, 255));
		CustomerListPane.add(lblCustomerList);
		
		CarListPane = new JPanel();
		CarListPane.addMouseListener(new PanelButtonMouseAdaptor(CarListPane) {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuClicked(CarListPanel);
			}
		});
		
		CarListPane.setBackground(new Color(30, 144, 255));
		CarListPane.setForeground(new Color(255, 255, 255));
		CarListPane.setBounds(0, 155, 230, 60);
		panemenu.add(CarListPane);
		
		lblCar = new JLabel("차량관리");
		lblCar.setFont(new Font("인터파크고딕 L", Font.PLAIN, 17));
		lblCar.setForeground(new Color(255, 255, 255));
		CarListPane.add(lblCar);
		
		RentListPane = new JPanel();		
		RentListPane.addMouseListener(new PanelButtonMouseAdaptor(RentListPane) {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuClicked(RentListPanel);
			}
		});
		RentListPane.setBackground(new Color(30, 144, 255));
		RentListPane.setForeground(new Color(255, 255, 255));
		RentListPane.setBounds(0, 215, 231, 60);
		panemenu.add(RentListPane);
		
		lblRent = new JLabel("대여관리");
		lblRent.setFont(new Font("인터파크고딕 L", Font.PLAIN, 17));
		lblRent.setForeground(new Color(255, 255, 255));
		RentListPane.add(lblRent);
		
		MileagePane = new JPanel();
		MileagePane.addMouseListener(new PanelButtonMouseAdaptor(MileagePane) {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuClicked(MileagePane);
			}

		});
		MileagePane.setBackground(new Color(30, 144, 255));
		MileagePane.setForeground(new Color(255, 255, 255));
		MileagePane.setBounds(0, 275, 231, 60);
		panemenu.add(MileagePane);
		
		lblMileage = new JLabel("마일리지");
		lblMileage.setForeground(new Color(255, 255, 255));
		lblMileage.setFont(new Font("인터파크고딕 L", Font.PLAIN, 17));
		MileagePane.add(lblMileage);
		
		PerformancePane = new JPanel();
		PerformancePane.addMouseListener(new PanelButtonMouseAdaptor(PerformancePane) {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuClicked(PerformancePane);
			}

		});
		PerformancePane.setBackground(new Color(30, 144, 255));
		PerformancePane.setForeground(new Color(255, 255, 255));
		PerformancePane.setBounds(0, 335, 231, 60);
		panemenu.add(PerformancePane);
		
		lblPerformance = new JLabel("성과현황");
		lblPerformance.setFont(new Font("인터파크고딕 L", Font.PLAIN, 17));
		lblPerformance.setForeground(new Color(255, 255, 255));
		PerformancePane.add(lblPerformance);
		
		MainContentPane = new JPanel();
		MainContentPane.setBackground(new Color(255, 255, 255));
		MainContentPane.setBounds(236, 10, 631, 60);
		
		contentPane.add(MainContentPane);
		
		MainContentPane.add(CustomerListPanel); 
		MainContentPane.add(CarListPanel);
		MainContentPane.add(RentListPanel);
//		MainContentPane.add(MileagePanel);
//		MainContentPane.add(PerformancePane);
//		 
		MenuClicked(CustomerListPane);
		MenuClicked(CarListPane);
		
		lblSignOut = new JLabel("");
		lblSignOut.setBounds(172, 580, 94, 71);
//		lblSignOut.setIcon(new ImageIcon(img_path + "inout.jpg"));
		lblSignOut.setIcon(new ImageIcon(img_logo));
		
		panemenu.add(lblSignOut);

	}
	//MENU 
	public void MenuClicked(JPanel panel) {
		
		CarListPanel.setVisible(false);
		RentListPanel.setVisible(false);
		CustomerListPanel.setVisible(false);
//		MileageListPanel.setVisible(false);
//		PerformancePanel.setVisible(false);
		
		panel.setVisible(true);
	}
	private class PanelButtonMouseAdaptor extends MouseAdapter{
		JPanel panel;
		public PanelButtonMouseAdaptor(JPanel panel) {
			this.panel=panel;
		}
		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(new Color(220, 220, 220));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			panel.setBackground(new Color(30, 144, 255));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(220, 220, 220));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(new Color(30, 144, 255));
		}
		
	}
}
