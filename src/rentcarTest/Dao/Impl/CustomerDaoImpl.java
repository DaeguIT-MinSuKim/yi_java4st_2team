package rentcarTest.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import rentcarTest.Dao.CustomerDao;
import rentcarTest.conn.JdbcUtil;
import rentcarTest.dto.Customer;

public class CustomerDaoImpl implements CustomerDao {
	private static final CustomerDaoImpl instance = new CustomerDaoImpl();
	Calendar today = Calendar.getInstance();

	private CustomerDaoImpl() {
		System.out.println("메소드 실행");
	}

	public static CustomerDaoImpl getInstance() {
		return instance;
	}

	@Override
	public List<Customer> selectCustomerByAll() {
		String sql = "SELECT CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_REMARK, CTM_MLG, LIST_CTM FROM CUSTOMER ORDER BY CTM_NO";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Customer> list = new ArrayList<Customer>();
				do {
					list.add(getCustomer(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println("실행?");
		return null;
	}

	@Override
	public List<Customer> selectCustomerByFind(Customer ctm) {
		String sql = "SELECT CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_MLG, CTM_REMARK, LIST_CTM " + "  FROM CUSTOMER "
				+ " WHERE CTM_NO = ? OR CTM_NAME LIKE ? OR TEL LIKE ? OR ADDRESS LIKE ?" + " ORDER BY CTM_NO";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, ctm.getNo());
			pstmt.setString(2, "%" + ctm.getName() + "%");
			pstmt.setString(3, "%" + ctm.getTel() + "%");
			pstmt.setString(4, "%" + ctm.getAddress() + "%");
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<Customer> item_list = new ArrayList<>();
					do {
						item_list.add(getCustomer(rs));
					} while (rs.next());
					return item_list;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public List<Customer> selectCustomerByRent() {
		String sql = "SELECT CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_REMARK, CTM_MLG, LIST_CTM FROM CUSTOMER WHERE CTM_NO IN(SELECT R.CTM_NO FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO WHERE IS_RENT=1) ORDER BY CTM_NO";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Customer> list = new ArrayList<Customer>();
				do {
					list.add(getCustomer(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public List<Customer> selectCustomerToday() {
		String sql = "SELECT CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_REMARK, CTM_MLG FROM CUSTOMER C NATURAL JOIN RENT R"
				+ " WHERE RENT_DATE = '?-?-?' ";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, today.get(Calendar.YEAR));
			pstmt.setInt(2, today.get(Calendar.MONTH));
			pstmt.setInt(3, today.get(Calendar.DATE));
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<Customer> item_list = new ArrayList<>();
					do {
						item_list.add(getCustomer(rs));
					} while (rs.next());
					return item_list;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public List<Customer> selectCustomerBlackList() {
		String sql = "SELECT CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_REMARK, CTM_MLG, LIST_CTM FROM CUSTOMER WHERE LIST_CTM = 1";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Customer> list = new ArrayList<Customer>();
				do {
					list.add(getCustomer(rs));
				} while (rs.next());
				return list;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public int insertCustomer(Customer ctm) {
		String sql = "INSERT INTO CUSTOMER VALUES (?,?,?,?,?,?,?)";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, ctm.getNo());
			pstmt.setString(2, ctm.getName());
			pstmt.setString(3, ctm.getTel());
			pstmt.setString(4, ctm.getAddress());
			pstmt.setString(5, ctm.getRemark());
			pstmt.setInt(6, ctm.getMile());
			pstmt.setInt(7, 0);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateCustomer(Customer ctm) {
		String sql = "UPDATE CUSTOMER SET CTM_NAME=?, TEL=?, ADDRESS=?, CTM_REMARK=?, CTM_MLG=?, LIST_CTM = ? WHERE CTM_NO=?";

	        try (Connection con = JdbcUtil.getConnection();
	               PreparedStatement pstmt = con.prepareStatement(sql.toString())) {
	           
	           pstmt.setString(1, ctm.getName());
	            pstmt.setString(2, ctm.getTel());
	            pstmt.setString(3, ctm.getAddress());
	            pstmt.setString(4, ctm.getRemark());
	            pstmt.setInt(5, ctm.getMile());
	            pstmt.setInt(6, ctm.getList_ctm());
	            pstmt.setInt(7, ctm.getNo());
	            return pstmt.executeUpdate();
	            
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }

	}

	@Override
	public int deleteCustomer(Customer ctm) {
		String sql = "UPDATE CUSTOMER SET CTM_NAME = ? WHERE CTM_NO = ?";
		String name = getName(ctm);
		System.out.println("탈퇴? - " + name);
		try (Connection setCon = JdbcUtil.getConnection(); PreparedStatement setPstmt = setCon.prepareStatement(sql)) {
			if (deleteCheck(ctm)) {
				System.out.println("1");
				setPstmt.setString(1, name.substring(0, name.length() - 4));
			} else {
				System.out.println("2");
				setPstmt.setString(1, name + "(탈퇴)");
			}
			System.out.println("3");
			setPstmt.setInt(2, ctm.getNo());
			return setPstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public boolean deleteCheck(Customer ctm) {
		String name = getName(ctm);
		if (name.length() > 4) {
			if (name.substring(name.length() - 4, name.length()).equals("(탈퇴)"))
				return true; // 삭제된사람
		} else {
			return false; // 삭제 안된사람
		}
		return false;
	}

	private String getName(Customer ctm) {
		String sql = "SELECT CTM_NAME FROM CUSTOMER WHERE CTM_NO = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, ctm.getNo());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String name = rs.getString("CTM_NAME").trim();
			return name;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Customer getCustomer(ResultSet rs) throws SQLException {
		int no = rs.getInt("CTM_NO");
		String name = rs.getString("CTM_NAME");
		String tel = rs.getString("TEL");
		String address = rs.getString("ADDRESS");
		String remark = rs.getString("CTM_REMARK");
		int mile = rs.getInt("CTM_MLG");
    	int list_ctm = rs.getInt("LIST_CTM");
		return new Customer(no, name, tel, address, remark, mile, list_ctm);
	}

	public int getLastCtm_no() {
		List<Customer> list = selectCustomerByAll();
		return list.get(list.size() - 1).getNo() + 1;
	}
}
