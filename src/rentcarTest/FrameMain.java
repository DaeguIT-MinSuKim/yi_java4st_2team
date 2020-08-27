package rentcarTest;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import java.io.*;
import javax.swing.*;

class JDBCpro extends JFrame implements ActionListener {
	String[] days = { "일", "월", "화", "수", "목", "금", "토" };
	int year, month, day, todays, memoday = 0;
	Font font;
	Color bc, fc;
	Calendar today;
	Calendar cal;
	JButton btnBefore, btnAfter;
	JButton btnAdd, btnDel;
	JButton[] calBtn = new JButton[49];
	JLabel thing;
	JLabel time;
	JPanel panWest;
	JPanel panSouth;
	JPanel panNorth;
	JPanel panEast;
	JTextField txtMonth, txtYear, txtWrite;
	JTextField txtTime;
	BorderLayout bLayout = new BorderLayout();

	////////////////////////////////////////

	Connection con = null;
	Statement stmt = null;
	String driver = "org.gjt.mm.mysql.Driver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl?useSSL=false";
	String user = "RENT_TEST";
    String password = "rootroot";
	ResultSet rs = null;
	String sql, tempmemo;

	public JDBCpro() {
		today = Calendar.getInstance(); // 디폴트의 타임 존 및 로케일을 사용해 달력을 가져옵니다.
		cal = new GregorianCalendar();
		year = today.get(Calendar.YEAR);
		month = today.get(Calendar.MONTH) + 1;// 1월의 값이 0

		panNorth = new JPanel();
		panNorth.add(btnBefore = new JButton("Before"));
		panNorth.add(txtYear = new JTextField(year + "년"));
		panNorth.add(txtMonth = new JTextField(month + "월"));

		txtYear.setEnabled(false);
		txtMonth.setEnabled(false);

		panNorth.add(btnAfter = new JButton("After"));

		font = new Font("Sherif", Font.BOLD, 24);
		txtYear.setFont(font);
		txtMonth.setFont(font);

		panNorth.add(btnAdd = new JButton("메모추가"));
		panNorth.add(btnDel = new JButton("메모삭제"));
		add(panNorth, "North");

		panWest = new JPanel(new GridLayout(7, 7));// 격자나,눈금형태의 배치관리자
		font = new Font("Sherif", Font.BOLD, 12);

		gridInit();
		calSet();
		hideInit();

		add(panWest, "West");
		panEast = new JPanel();
		panEast.add(time = new JLabel("메모"));
		panEast.add(txtWrite = new JTextField());
		txtWrite.setPreferredSize(new Dimension(200, 150));

		add(panEast, "East");
		btnBefore.addActionListener(this);
		btnAfter.addActionListener(this);
		btnAdd.addActionListener(this);
		btnDel.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Swing을 이용한 JDBC 메모장");
		setBounds(100, 100, 600, 300);
		setVisible(true);

	}// end constuctor

	public void calSet() {
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, (month - 1));
		cal.set(Calendar.DATE, 1);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int j = 0;
		int hopping = 0;
		calBtn[0].setForeground(new Color(255, 0, 0));// 일요일 "일"
		calBtn[6].setForeground(new Color(0, 0, 255));// 토요일 "토"
		for (int i = cal.getFirstDayOfWeek(); i < dayOfWeek; i++) {
			j++;
		}

		hopping = j;
		for (int kk = 0; kk < hopping; kk++) {
			calBtn[kk + 7].setText("");
		}

		for (int i = cal.getMinimum(Calendar.DAY_OF_MONTH); i <= cal.getMaximum(Calendar.DAY_OF_MONTH); i++) {
			cal.set(Calendar.DATE, i);
			if (cal.get(Calendar.MONTH) != month - 1) {
				break;
			}
			getConnect();
			todays = i;
			checkday();
			if (memoday == 1) {
				calBtn[i + 6 + hopping].setForeground(new Color(0, 255, 0));
			}

			else {
				calBtn[i + 6 + hopping].setForeground(new Color(0, 0, 0));
				if ((i + hopping - 1) % 7 == 0) {// 일요일
					calBtn[i + 6 + hopping].setForeground(new Color(255, 0, 0));
				}
				if ((i + hopping) % 7 == 0) {// 토요일
					calBtn[i + 6 + hopping].setForeground(new Color(0, 0, 255));
				}
			}

			calBtn[i + 6 + hopping].setText((i) + "");

		}

	}

	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == btnBefore) {
			this.panWest.removeAll();
			calInput(-1);
			gridInit();
			panelInit();
			calSet();
			hideInit();
			this.txtYear.setText(year + "년");
			this.txtMonth.setText(month + "월");
		} else if (ae.getSource() == btnAfter) {
			this.panWest.removeAll();
			calInput(1);
			gridInit();
			panelInit();
			calSet();
			hideInit();
			this.txtYear.setText(year + "년");
			this.txtMonth.setText(month + "월");
		}

		else if (ae.getSource() == btnAdd) {
			getConnect();
			addMemo();
			calSet();
			txtWrite.setText("");
		} else if (ae.getSource() == btnDel) {
			getConnect();
			deleteMemo();
			calSet();
			txtWrite.setText("");
		} else if (Integer.parseInt(ae.getActionCommand()) >= 1 && Integer.parseInt(ae.getActionCommand()) <= 31) {
			day = Integer.parseInt(ae.getActionCommand());
			System.out.println(day);
			getConnect();
			searchmemo();
			calSet();
		}

	}// end actionperformed()

	public void hideInit() {
		for (int i = 0; i < calBtn.length; i++) {
			if ((calBtn[i].getText()).equals(""))
				calBtn[i].setEnabled(false);
		}
	}

	public void gridInit() {

		for (int i = 0; i < days.length; i++)
			panWest.add(calBtn[i] = new JButton(days[i]));
		for (int i = days.length; i < 49; i++) {
			panWest.add(calBtn[i] = new JButton(""));
			calBtn[i].addActionListener(this);
		}

	}

	public void panelInit() {
		GridLayout gridLayout1 = new GridLayout(7, 7);
		panWest.setLayout(gridLayout1);

	}

	public void calInput(int gap) {
		month += (gap);
		if (month <= 0) {
			month = 12;
			year = year - 1;
		} else if (month >= 13) {
			month = 1;
			year = year + 1;
		}

	}// end calInput()

	public void getConnect() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, driver);
			stmt = con.createStatement();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}// end dbConnect()

	public void addMemo() {
		try {
			String temp = txtWrite.getText();
			if (temp.equals("")) {
				JOptionPane.showMessageDialog(null, "내용이 없습니다.");
				return;
			}
			sql = "insert into memo (memo,year,month,day) values (";
			sql += "'" + temp + "',";
			sql += "" + year + ",";
			sql += "" + month + ",";
			sql += "" + day + ");";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// end add()

	public void deleteMemo() {
		try {
			sql = "delete from memo where year=";
			sql += year + " and month=";
			sql += month + " and day=";
			sql += +day + ";";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchmemo() {
		try {
			sql = "select memo from memo where year=";
			sql += year + " and month=";
			sql += month + " and day=";
			sql += "" + day + ";";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			String gettemp = "";
			while (rs.next()) {
				gettemp += rs.getString("memo") + "  ";
			}
			txtWrite.setText(gettemp);
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// end searchmemo()

	public void checkday() {
		sql = "select * from memo where year=";
		sql += year + " and month=";
		sql += month + " and day=";
		sql += "" + todays + ";";
		// System.out.println(sql);

		try {
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				memoday = 1;
			} else
				memoday = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class FrameMain{
	public static void main(String[] args) {
		JDBCpro jdbc = new JDBCpro();
	}
}