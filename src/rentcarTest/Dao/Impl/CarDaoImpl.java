package rentcarTest.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rentcarTest.Dao.CarDao;
import rentcarTest.conn.JdbcUtil;
import rentcarTest.dto.Car;
import rentcarTest.dto.Customer;
import rentcarTest.dto.Kind;
import rentcarTest.dto.Mileage;

public class CarDaoImpl implements CarDao {
	private static final CarDaoImpl instance = new CarDaoImpl();

	@Override
	public List<Car> selectCarByAll() {
		String sql = "SELECT CAR_NO, CAR_NAME, CAR_KIND, FUEL, DISTANCE, FARE, SALE, CAR_REMARK FROM CAR";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Car> list = new ArrayList<Car>();
				do {
					list.add(getCar(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	private Car getCar(ResultSet rs) throws SQLException {
		String no = rs.getString("CAR_NO");
		String name = rs.getNString("CAR_NAME");
		Kind carKind = new Kind(rs.getString("CAR_KIND"));
		String fuel = rs.getString("FUEL");
		int distance = rs.getInt("DISTANCE");
		int fare = rs.getInt("FARE");
		int sale = rs.getInt("SALE");
		String carRemark = rs.getString("CAR_REMARK");
		return new Car(no, name, carKind, fuel, distance, fare, sale, carRemark);
	}

	@Override
	public List<Car> selectCarByFind(Car car) {
		String sql = "SELECT CAR_NO, CAR_NAME, CAR_KIND, FUEL, DISTANCE, FARE, SALE, CAR_REMARK "
				+ "FROM CAR WHERE CAR_NO = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				pstmt.setString(1, car.getCarNo());

				if (rs.next()) {
					List<Car> list = new ArrayList<>();
					do {
						list.add(getCar(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public List<Car> selectCarByRent() {
		String sql = "SELECT CAR_NO, CAR_NAME, CAR_KIND, FUEL, DISTANCE, FARE, SALE, CAR_REMARK FROM CAR "
				+ "WHERE CAR_NO IN (SELECT R.CAR_NO FROM RENT R LEFT OUTER JOIN CAR C ON R.CAR_NO = C.CAR_NO WHERE IS_RENT=1)";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Car> list = new ArrayList<Car>();
				do {
					list.add(getCar(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public int insertCar(Car car) {
		String sql = "INSERT INTO CAR VALUES (?,?,?,?,?,?,?,?)";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, car.getCarNo());
			pstmt.setString(2, car.getCarName());
			pstmt.setString(3, car.getCarKind().getCar_kind());
			pstmt.setString(4, car.getFuel());
			pstmt.setInt(5, car.getDistance());
			pstmt.setInt(6, car.getFare());
			pstmt.setInt(7, car.getSale());
			pstmt.setString(8, car.getCarRemark());
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	public int updateCar(Car car) {
		String sql = "UPDATE CAR SET CAR_NAME = ? , CAR_KIND = ? , FUEL = ? , DISTANCE = ? , FARE = ? , SALE = ? , CAR_REMARK = ? FROM CAR WHERE CAR_NO = ?  ";

		try (Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql.toString())) {
			pstmt.setString(1, car.getCarName());
			pstmt.setString(2, car.getCarKind().getCar_kind());
			pstmt.setString(3, car.getFuel());
			pstmt.setInt(4, car.getDistance());
			pstmt.setInt(5, car.getFare());
			pstmt.setInt(6, car.getSale());
			pstmt.setString(7, car.getCarRemark());

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteCar(Car car) {
		String sql = "DELETE FROM CAR WHERE CAR_NO = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, car.getCarNo());
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static CarDao getInstance() {
		return instance;
	}


	}
