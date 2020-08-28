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
		String sql = "SELECT CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_REMARK, CTM_MLG, LIST_CTM FROM CUSTOMER";
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
	      String sql = "SELECT CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_MLG, CTM_REMARK, LIST_CTM " + 
	            "  FROM CUSTOMER " + 
	            " WHERE CTM_NO = ? OR CTM_NAME LIKE '%' || ? || '%' OR TEL LIKE '%' || ? || '%' OR ADDRESS LIKE '%' || ? || '%'" +
	            " ORDER BY CTM_NO";
	      try (Connection con = JdbcUtil.getConnection();
	            PreparedStatement pstmt = con.prepareStatement(sql)){
	         pstmt.setInt(1, ctm.getNo());
	         pstmt.setString(2, "%"+ ctm.getName() +"%");
	         pstmt.setString(3, "%"+ ctm.getTel() +"%");
	         pstmt.setString(4, "%"+ ctm.getAddress() +"%");
	         try(ResultSet rs = pstmt.executeQuery()){
	            if(rs.next()) {
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
	   public List<Customer> selectCustomerBlackList() {
	      String sql="SELECT CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_REMARK, CTM_MLG, LIST_CTM FROM CUSTOMER WHERE LIST_CTM = 1";
	      try(Connection con = JdbcUtil.getConnection(); 
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
			pstmt.setInt(6, ctm.getCtm_mlg());
			pstmt.setInt(7, 0);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateCustomer(Customer ctm) {
		 String sql = "UPDATE CUSTOMER SET CTM_NAME=?, TEL=?, ADDRESS=?, CTM_REMARK=?, CTM_MLG=? WHERE CTM_NO=?";

	        try (Connection con = JdbcUtil.getConnection();
	               PreparedStatement pstmt = con.prepareStatement(sql.toString())) {
	           
	           pstmt.setString(1, ctm.getName());
	            pstmt.setString(2, ctm.getTel());
	            pstmt.setString(3, ctm.getAddress());
	            pstmt.setString(4, ctm.getRemark());
	            pstmt.setInt(5, ctm.getCtm_mlg());
	            pstmt.setInt(6, ctm.getNo());
	            return pstmt.executeUpdate();
	            
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }

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
		int mlg = rs.getInt("CTM_MLG");
		int list_ctm = rs.getInt("LIST_CTM");
		return new Customer(no, name, tel, address, remark, mlg, list_ctm);
	}

}
