package rentcarTest;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Window.Type;

public class Login extends JFrame{
	//Image Icons
	private Image img_carlogo = new ImageIcon(Login.class.getResource("res/car2.png")).getImage().getScaledInstance(130,130,Image.SCALE_SMOOTH);
	private Image img_lock = new ImageIcon(Login.class.getResource("res/lock.png")).getImage().getScaledInstance(25,20,Image.SCALE_SMOOTH);
	private Image img_log = new ImageIcon(Login.class.getResource("res/log.jpg")).getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);

//	private String img_path = System.getProperty("user.dir") + File.separator + "images" + File.separator;


	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_2;
	private JTextField txtUsername;
	private JPanel pnlBtnLogin;
	private JLabel lblLogin;
	private JLabel lblCancel;
	private JLabel lblIconLogo;
	private JPasswordField pwdPassword;
	private JLabel lblmess;
	private JLabel lblLock;
	private Icon icon_lock;
	private JLabel lblLog;
	private JLabel lblTitle;

	public Login() {
		initComponents();
	}
	
	private void initComponents() {
		setType(Type.POPUP);
		setFont(new Font("인터파크고딕 L", Font.PLAIN, 13));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 639, 476);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setForeground(new Color(255, 255, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(163, 203, 300, 45);
		contentPane.add(panel);
		panel.setLayout(null);
		//ID
		txtUsername = new JTextField();
		txtUsername.setBorder(null);
		txtUsername.setFont(new Font("인터파크고딕 L", Font.PLAIN, 14));
		txtUsername.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				if(txtUsername.getText().equals("username")) {
					txtUsername.setText("");
				}else {
					txtUsername.selectAll();
				}
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if (txtUsername.getText().equals("")) {
					txtUsername.setText("username");
										
				}
			}
		
		});
		txtUsername.setText("Username");
		txtUsername.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsername.setBounds(12, 10, 222, 25);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		lblLog = new JLabel("");
		lblLog.setBounds(255, 0, 54, 45);
		lblLog.setIcon(new ImageIcon(img_log));
		panel.add(lblLog);
		
		//PASSWORD
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setLayout(null);
		panel_2.setBounds(163, 271, 300, 45);
		contentPane.add(panel_2);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBorder(null);
		pwdPassword.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				if(pwdPassword.getText().equals("password")) {
					pwdPassword.setEchoChar('●');
					pwdPassword.setText("");					
				}else {
					pwdPassword.selectAll();
				}
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if (pwdPassword.getText().equals("")) {
					pwdPassword.setText("Password");
					pwdPassword.setEchoChar((char)0);					
				}
			}
		});

		pwdPassword.setText("password");
		pwdPassword.setBounds(12, 10, 222, 25);
		panel_2.add(pwdPassword);
		
		lblLock = new JLabel("");
		lblLock.setBounds(252, 0, 54, 45);
		panel_2.add(lblLock);
		lblLock.setIcon(new ImageIcon(img_lock));
		//LOGIN
		pnlBtnLogin = new JPanel();
		pnlBtnLogin.setForeground(new Color(128, 128, 128));
		pnlBtnLogin.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(pwdPassword.getText().equals("1234") && txtUsername.getText().equals("admin")) {
					lblmess.setText("");
					//JOptionPane.showMessageDialog(null, "로그인 성공적...");
					Login.this.dispose();
					Home homeframe = new Home();
					homeframe.setVisible(true);
					
				}else if(txtUsername.getText().equals("") || txtUsername.getText().equals("admin") ||
						 pwdPassword.getText().equals("") || pwdPassword.getText().equals("1234")) {
					lblmess.setText("공백이 있어요!");
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				pnlBtnLogin.setBackground(new Color(169, 169, 169));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				pnlBtnLogin.setBackground(new Color(30, 144, 255));
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				pnlBtnLogin.setBackground(new Color(169, 169, 169));
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pnlBtnLogin.setBackground(new Color(30, 144, 255));
				
			}		
			
		});
		pnlBtnLogin.setBackground(new Color(30, 144, 255));
		pnlBtnLogin.setBounds(163, 339, 300, 45);
		contentPane.add(pnlBtnLogin);
		pnlBtnLogin.setLayout(null);
		
		lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setBounds(129, 3, 83, 35);
		pnlBtnLogin.add(lblLogin);
		// DISPOSE 
		lblCancel = new JLabel("X");
		lblCancel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null,"이 프로그램이 끄는 동시에 당신의 인생도 취소","확인메세지",JOptionPane.YES_NO_OPTION) == 0) {
					Login.this.dispose();
				}
			}
		});
		
		lblCancel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 28));
		lblCancel.setForeground(new Color(255, 255, 255));
		lblCancel.setBounds(590, 10, 20, 22);
		contentPane.add(lblCancel);
		//TITLE LOGO
		lblIconLogo = new JLabel("");
		lblIconLogo.setBounds(263, 32, 124, 122);
		lblIconLogo.setIcon(new ImageIcon(img_carlogo));
		contentPane.add(lblIconLogo);
		//BLANK MESSAGE
		lblmess = new JLabel("");
		lblmess.setForeground(new Color(128, 128, 128));
		lblmess.setHorizontalAlignment(SwingConstants.CENTER);
		lblmess.setFont(new Font("인터파크고딕 L", Font.PLAIN, 14));
		lblmess.setBounds(163, 394, 300, 15);
		contentPane.add(lblmess);
		//PROGRAM TITLE 
		lblTitle = new JLabel("크리링 렌터카");
		lblTitle.setFont(new Font("인터파크고딕 B", Font.PLAIN, 28));
		lblTitle.setForeground(new Color(30, 144, 255));
		lblTitle.setBounds(247, 142, 173, 45);
		contentPane.add(lblTitle);
	}
}
