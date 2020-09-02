package rentcarTest.Dao.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import rentcarTest.conn.JdbcUtil;
import rentcarTest.dto.Customer;
import rentcarTest.dto.Mileage;

public class TransactionService {
	
	//마일리지테이불에 마일리지 추가, 고객테이블에 마일리지 추가
	public void transAddMileAndCustomer(Mileage mlg, Customer ctm) {
		String mSql = "INSERT INTO MILEAGE VALUES(?,?,?,?,?)";
		String cSql = "UPDATE CUSTOMER SET CTM_MLG=CTM_MLG+? WHERE CTM_NO=?";
		Connection con = null; 
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false); 
			System.out.println(con.getAutoCommit());
			try (PreparedStatement mpstmt = con.prepareStatement(mSql)) {
				mpstmt.setInt(1, mlg.getMlg_no());
				mpstmt.setInt(2, mlg.getCtm_no());
				mpstmt.setInt(3, mlg.getMlg_kind());
				mpstmt.setInt(4, mlg.getPoint());
				mpstmt.setString(5, mlg.getMlg_remark());
				mpstmt.executeUpdate();
			}
			try (PreparedStatement cpstmt = con.prepareStatement(cSql)) {
				cpstmt.setInt(1, ctm.getMile());
				cpstmt.setInt(2, ctm.getNo());
			}
			con.commit();
			con.setAutoCommit(true);
			System.out.println(con.getAutoCommit());
		} catch (SQLException e) {
			System.out.println("rollback");
			processRollback(con, e);
		}
	}
	//마일리지 테이블에 차감했을때 고객테이블에도 차감
	public void transDeductMileAndCustomer(Mileage mlg, Customer ctm) {
		String mSql = "INSERT INTO MILEAGE VALUES(?,?,?,?,?)";
		String cSql = "UPDATE CUSTOMER SET CTM_MLG=CTM_MLG-?  WHERE CTM_NO=?";
		Connection con = null; 
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false); 
			System.out.println(con.getAutoCommit());
			try (PreparedStatement mpstmt = con.prepareStatement(mSql)) {
				mpstmt.setInt(1, mlg.getMlg_no());
				mpstmt.setInt(2, mlg.getCtm_no());
				mpstmt.setInt(3, mlg.getMlg_kind());
				mpstmt.setInt(4, mlg.getPoint());
				mpstmt.setString(5, mlg.getMlg_remark());
				mpstmt.executeUpdate();
			}
			try (PreparedStatement cpstmt = con.prepareStatement(cSql)) {
				cpstmt.setInt(1, ctm.getMile());
				cpstmt.setInt(2, ctm.getNo());
			}
			con.commit();
			con.setAutoCommit(true);
			System.out.println(con.getAutoCommit());
		} catch (SQLException e) {
			System.out.println("rollback");
			processRollback(con, e);
		}
	}

	private void processRollback(Connection con, SQLException e) {
		try {
			con.rollback();
			con.setAutoCommit(true);
			System.out.println(con.getAutoCommit());
		} catch (SQLException ee) {
			throw new RuntimeException(ee);
		}
		;
		throw new RuntimeException(e);
	}		
}


