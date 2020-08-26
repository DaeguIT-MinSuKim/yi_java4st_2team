package rentcarTest.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rentcarTest.Dao.RentDao;
import rentcarTest.conn.JdbcUtil;
import rentcarTest.dto.Car;
import rentcarTest.dto.Customer;
import rentcarTest.dto.Rent;

public class RentDaoImpl implements RentDao{
	private static final RentDaoImpl instance = new RentDaoImpl();

	private RentDaoImpl() {
		System.out.println("메소드 실행");
	}

	public static RentDaoImpl getInstance() {
		return instance;
	}

	@Override
	public List<Rent> selectRentByAll() {
		String sql = "SELECT r.RENT_NO, car.CAR_NAME, car.CAR_NO, c.CTM_NO , c.CTM_NAME , c.TEL, r.RENT_DATE, r.RETURN_DATE, r.RENT_TIME, r.IS_DRIVER, r.RENT_REMARK " + 
				"  FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO JOIN CAR car ON r.Car_NO = car.CAR_NO";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			System.out.println("1");
			if (rs.next()) {
				List<Rent> list = new ArrayList<Rent>();
				System.out.println("2");
				do {
					list.add(getRent(rs));
					System.out.println("3");
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public RentDao selectRentByFind(Rent rent) {
		return null;
	}

	@Override
	public RentDao selectRentByDate(Rent rent) {
		return null;
	}

	@Override
	public RentDao selectRentByRent(Rent rent) {
		return null;
	}

	@Override
	public int insertRent(Rent rent) {
		return 0;
	}

	@Override
	public int updateRent(Rent rent) {
		return 0;
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
		//rent.setRent_date(new Date("r.RENT_DATE"));
		//rent.setRent_date(new Date("r.RETURN_DATE"));
		rent.setRent_time(rs.getInt("RENT_TIME"));
		rent.setIs_driver(rs.getInt("IS_DRIVER"));
		rent.setRent_remark(rs.getString("RENT_REMARK"));
		
		
		return rent;
	}

}
