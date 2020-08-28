package rentcarTest.panel;

import javax.swing.JPanel;

import rentcarTest.Home;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Homepanel extends JPanel {
	private Image img_logo = new ImageIcon(Home.class.getResource("../res/person.png")).getImage().getScaledInstance(90,
			90, Image.SCALE_SMOOTH);
	private Image img_todo = new ImageIcon(Home.class.getResource("../res/things.png")).getImage().getScaledInstance(15,
			15, Image.SCALE_SMOOTH);
	private JLabel lblPerson;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lbltodo;
	private JPanel panel;
	
	public Homepanel() {
		initComponents();
	}
	private void initComponents() {
		setBackground(new Color(30, 144, 255));
		setLayout(null);
		
		lblPerson = new JLabel("");
		lblPerson.setBounds(175, 76, 98, 92);
		lblPerson.setIcon(new ImageIcon(img_logo));
		add(lblPerson);
		
		lblNewLabel = new JLabel("관리자님, 환영합니다.");
		lblNewLabel.setFont(new Font("인터파크고딕 L", Font.BOLD, 15));
		lblNewLabel.setBounds(154, 164, 183, 53);
		add(lblNewLabel);
		
		panel = new JPanel();
		panel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

	
		});
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(164, 207, 114, 39);
		add(panel);
		
		lbltodo = new JLabel("");
		panel.add(lbltodo);
		lbltodo.setIcon(new ImageIcon(img_todo));
		
		lblNewLabel_1 = new JLabel("오늘 할 일");
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("인터파크고딕 L", Font.PLAIN, 14));
	}
}
