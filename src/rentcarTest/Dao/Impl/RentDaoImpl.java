package rentcarTest.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rentcarTest.Dao.RentDao;
import rentcarTest.conn.JdbcUtil;
import rentcarTest.dto.Car;
import rentcarTest.dto.Customer;
import rentcarTest.dto.Rent;
import rentcarTest.dto.UsingDate;

public class RentDaoImpl implements RentDao {
	private static final RentDaoImpl instance = new RentDaoImpl();

	private RentDaoImpl() {
		System.out.println("메소드 실행");
	}

	public static RentDaoImpl getInstance() {
		return instance;
	}

	@Override
	public List<Rent> selectRentByAll() {
		String sql = "SELECT car.CAR_NAME, car.CAR_NO, c.CTM_NO , c.CTM_NAME , c.TEL, r.RENT_DATE, r.RETURN_DATE, r.RENT_TIME, r.IS_DRIVER, r.RENT_REMARK "
				+ "  FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO JOIN CAR car ON r.Car_NO = car.CAR_NO";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Rent> list = new ArrayList<Rent>();
				do {
					list.add(getRent(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public List<Rent> selectRentByFind(Rent rent) {
		String sql = "SELECT car.CAR_NAME, car.CAR_NO, c.CTM_NO , c.CTM_NAME , c.TEL, r.RENT_DATE, r.RETURN_DATE, r.RENT_TIME, r.IS_DRIVER, r.RENT_REMARK "
				+ "  FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO JOIN CAR car ON r.Car_NO = car.CAR_NO"
				+ " WHERE car.CAR_NO LIKE '%' || ? || '%' OR c.CTM_NAME LIKE '%' || ? || '%' OR c.TEL LIKE '%' || ? || '%'" 
				+ " ORDER BY r.RENT_NO";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, "%"+rent.getCar_no().getCarNo()+"%");
			pstmt.setString(2, "%"+rent.getCtm_no().getName()+"%");
			pstmt.setString(3, "%"+rent.getCtm_no().getTel()+"%");
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<Rent> item_list = new ArrayList<>();
					do {
						item_list.add(getRent(rs));
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
	public List<Rent> selectRentByDate(Rent rent) {
		String sql = "SELECT car.CAR_NAME, car.CAR_NO, c.CTM_NO , c.CTM_NAME , c.TEL, r.RENT_DATE, r.RETURN_DATE, r.RENT_TIME, r.IS_DRIVER, r.RENT_REMARK "
				+ "  FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO JOIN CAR car ON r.Car_NO = car.CAR_NO "
				+ " WHERE r.RENT_DATE >= ? AND r.RETURN_DATE <= ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setTimestamp(1, new Timestamp(rent.getRent_date().getTime()));
			pstmt.setTimestamp(2, new Timestamp(rent.getReturn_date().getTime()));
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<Rent> item_list = new ArrayList<>();
					do {
						item_list.add(getRent(rs));
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
	public List<Rent> selectRentByRent() {
		String sql = "SELECT car.CAR_NAME, car.CAR_NO, c.CTM_NO , c.CTM_NAME , c.TEL, r.RENT_DATE, r.RETURN_DATE, r.RENT_TIME, r.IS_DRIVER, r.RENT_REMARK "
				+ "  FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO JOIN CAR car ON r.Car_NO = car.CAR_NO "
				+ " WHERE c.CTM_NO IN(SELECT R.CTM_NO FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO WHERE IS_RENT=1) "
				+ " ORDER BY r.RENT_NO";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Rent> list = new ArrayList<>();
				do {
					list.add(getRent(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public int insertRent(Rent rent) {
		String sql = "INSERT INTO RENT values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, rent.getRent_no());
			pstmt.setInt(2, rent.getCtm_no().getNo());
			pstmt.setString(3, rent.getCar_no().getCarNo());
			pstmt.setDate(4, new java.sql.Date(rent.getRent_date().getTime()));
			pstmt.setDate(5, new java.sql.Date(rent.getReturn_date().getTime()));
			/*
			 * pstmt.setString(4, String.valueOf(rent.getRent_date())); pstmt.setString(5,
			 * String.valueOf(rent.getReturn_date()));
			 */
			pstmt.setInt(6, rent.getRent_time());
			pstmt.setInt(7, rent.getIs_driver());
			pstmt.setString(8, rent.getRent_remark());
			pstmt.setInt(9, 1);

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateRent(Rent rent) {
		String sql = "UPDATE RENT "
				+ "   SET CTM_NO = ?, CAR_NO = ?, RENT_DATE = ?, RETURN_DATE = ?, RENT_TIME = ?, IS_DRIVER = ?, RENT_REMARK = ? "
				+ " WHERE RENT_NO = ?";

		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql.toString())) {
			pstmt.setInt(1, rent.getCtm_no().getNo());
			pstmt.setString(2, rent.getCar_no().getCarNo());
			pstmt.setTimestamp(3, new Timestamp(rent.getRent_date().getTime()));
			pstmt.setTimestamp(4, new Timestamp(rent.getReturn_date().getTime()));
			/*
			 * pstmt.setTimestamp(5, new Timestamp(rent.getReturn_date().getTime()));
			 * pstmt.setTimestamp(6, new Timestamp(rent.getRent_date().getTime()));
			 */
			pstmt.setInt(5, rent.getRent_time());
			pstmt.setInt(6, rent.getIs_driver());
			pstmt.setString(7, rent.getRent_remark());
			pstmt.setInt(8, rent.getRent_no());

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Rent getRent(ResultSet rs) throws SQLException {
		Rent rent = new Rent();
		Customer ctm = new Customer();
		Car car = new Car();

		ctm.setNo(rs.getInt("CTM_NO"));
		ctm.setName(rs.getString("CTM_NAME"));
		ctm.setTel(rs.getString("TEL"));

		car.setCarName(rs.getString("CAR_NAME"));
		car.setCarNo(rs.getString("CAR_NO"));

		rent.setCtm_no(ctm);
		rent.setCar_no(car);
		rent.setRent_date(rs.getTimestamp("RENT_DATE"));
		rent.setReturn_date(rs.getTimestamp("RETURN_DATE"));
		rent.setRent_time(rs.getInt("RENT_TIME"));
		rent.setIs_driver(rs.getInt("IS_DRIVER"));
		rent.setRent_remark(rs.getString("RENT_REMARK"));

		return rent;
	}

	@Override
	public long rentLookupCarKind(String kind) {
		String sql = "SELECT SUM(RENT_TIME * FARE * (100 - SALE) / 100) AS 최종요금 "
				+ "FROM RENT r LEFT OUTER JOIN CAR c ON r.CAR_NO = c.CAR_NO " + "WHERE CAR_KIND = ? "
				+ "GROUP BY C.CAR_KIND";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, kind);
			try (ResultSet rs = pstmt.executeQuery()) {
				int res = 0;
				if (rs.next()) {
					res = rs.getInt(1);
				}
				return res;				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}