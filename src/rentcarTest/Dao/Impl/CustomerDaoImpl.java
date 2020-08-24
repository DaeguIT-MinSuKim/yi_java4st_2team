package rentcarTest.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rentcarTest.Dao.CustomerDao;
import rentcarTest.conn.JdbcUtil;
import rentcarTest.dto.Customer;
import rentcarTest.dto.Mileage;

public class CustomerDaoImpl implements CustomerDao {
	private static final CustomerDaoImpl instance = new CustomerDaoImpl();

	private CustomerDaoImpl() {
		System.out.println("메소드 실행");
	}

	public static CustomerDaoImpl getInstance() {
		return instance;
	}

	@Override
	public List<Customer> selectCustomerByAll() {
		String sql = "SELECT DISTINCT c.CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_REMARK, (SELECT SUM(MILEAGE) FROM MILEAGE WHERE CTM_NO = c.CTM_NO ) AS MILEAGE FROM CUSTOMER c JOIN MILEAGE m ON c.CTM_NO = m.CTM_NO";
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
		String sql = "SELECT DISTINCT c.CTM_NO, c.CTM_NAME, c.TEL, c.ADDRESS, (SELECT SUM(MILEAGE) FROM MILEAGE WHERE CTM_NO = ?) AS MILEAGE, c.CTM_REMARK "
				+ "  FROM CUSTOMER c JOIN MILEAGE m ON c.CTM_NO = m.CTM_NO " + " WHERE c.CTM_NO = ?";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			pstmt.setInt(1, ctm.getNo());
			pstmt.setInt(2, ctm.getNo());

			if (rs.next()) {
				List<Customer> list = new ArrayList<Customer>();
				do {
					list.add(getCustomer(rs));
				} while (rs.next());
				return list;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * @Override public List<Customer> selectCustomerByFind(Customer ctm) { String
	 * sql =
	 * "SELECT DISTINCT c.CTM_NO, c.CTM_NAME, c.TEL, c.ADDRESS, (SELECT SUM(MILEAGE) FROM MILEAGE WHERE CTM_NO = ?) AS MILEAGE, c.CTM_REMARK "
	 * + "  FROM CUSTOMER c JOIN MILEAGE m ON c.CTM_NO = m.CTM_NO " +
	 * " WHERE c.CTM_NO = ?"; String sql =
	 * "SELECT DISTINCT CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_REMARK " +
	 * "  FROM CUSTOMER " + " WHERE CTM_NO = ?"; try (Connection con =
	 * JdbcUtil.getConnection(); PreparedStatement pstmt =
	 * con.prepareStatement(sql)){ pstmt.setInt(1, ctm.getNo()); //pstmt.setInt(2,
	 * ctm.getNo()); try(ResultSet rs = pstmt.executeQuery()){ if(rs.next()) {
	 * List<Customer> item = new ArrayList<>(); do { item.add(getCustomer(rs)); }
	 * while (rs.next()); return item; Customer item = getCustomer(rs); if
	 * (rs.getInt("CTM_NO") != 0) { List<Mileage> list = new ArrayList<>(); do {
	 * list.add(getMileage(rs)); } while (rs.next()); item.setList(list); return
	 * item; } } } } catch (SQLException e) { throw new RuntimeException(e); }
	 * return null; }
	 */

	@Override
	public List<Customer> selectCustomerByRent() {
		String sql = "SELECT" + "  FROM CUSTOMER" + " WHERE CTM_NO IN(SELECT CTM_No"
				+ "   FROM RENT r LEFT OUTER JOIN CAR c ON r.CAR_NO = c.CAR_NO" + "  WHERE c.IS_RENT = 0";
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
	public List<Customer> selectCustomerBlackList(Customer ctm) {
		return null;
	}

	@Override
	public int insertCustomer(Customer ctm) {
		String sql = "INSERT INTO CUSTOMER VALUES (?,?,?,?,?)";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, ctm.getNo());
			pstmt.setString(2, ctm.getName());
			pstmt.setString(3, ctm.getTel());
			pstmt.setString(4, ctm.getAddress());
			pstmt.setString(5, ctm.getRemark());
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateCustomer(Customer ctm) {
		return 0;
	}

	@Override
	public int deleteCustomer(Customer ctm) {
		String sql = "DELETE FROM CUSTOMER WHERE CTM_NO = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, ctm.getNo());
			return pstmt.executeUpdate();

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
		int mlg = rs.getInt("MILEAGE");
		return new Customer(no, name, tel, address, remark, mlg);
	}

	private Mileage getMileage(ResultSet rs) throws SQLException {
		Mileage mlg = new Mileage();
		mlg.setPoint(rs.getInt("MILEAGE"));
		return mlg;
	}

}
