package rentcarTest.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rentcarTest.Dao.MileageDao;
import rentcarTest.conn.JdbcUtil;
import rentcarTest.dto.Customer;
import rentcarTest.dto.Mileage;

public class MileageDaoImpl implements MileageDao {
	private static final MileageDaoImpl instance = new MileageDaoImpl();

	private MileageDaoImpl() {
		System.out.println("메서드 실행");
	}

	public static MileageDaoImpl getInstance() {
		return instance;

	}

	public List<Mileage> selectMileageByAll() {
		String sql = "SELECT MLG_NO, CTM_NO, MLG_KIND, MILEAGE, MLG_REMARK FROM MILEAGE";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Mileage> list = new ArrayList<Mileage>();
				do {
					list.add(getMileage(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		System.out.println("실행");
		return null;
	}

	@Override
	public List<Mileage> selectMileageByNo(Mileage mlg) {
		String sql = "SELECT MLG_NO, CTM_NO, MLG_KIND, MILEAGE, MLG_REMARK " + "  FROM MILEAGE " + " WHERE MLG_NO = ? ";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, mlg.getMlg_no());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<Mileage> item_list = new ArrayList<>();
					do {
						item_list.add(getMileage(rs));
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
	public int insertMileage(Mileage mlg) {
		String sql = "INSERT INTO MILEAGE VALUES (?,?,?,?,?)";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, mlg.getMlg_no());
			pstmt.setInt(2, mlg.getCtm_no());
			pstmt.setInt(3, mlg.getMlg_kind());
			pstmt.setInt(4, mlg.getPoint());
			pstmt.setString(5, mlg.getMlg_remark());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int getLastMlg_no() {
		List<Mileage> list = selectMileageByAll();
		return list.get(list.size()-1).getMlg_no()+1;
	}

	private Mileage getMileage(ResultSet rs) throws SQLException {
		int mlg_no = rs.getInt("MLG_NO");
		int ctm_no = rs.getInt("CTM_NO");
		int mlg_kind = rs.getInt("MLG_KIND");
		int point = rs.getInt("MILEAGE");
		String mlg_remark = rs.getString("MLG_REMARK");
		return new Mileage(mlg_no, ctm_no, mlg_kind, point, mlg_remark);
	}

	@Override
	public int deleteMileage(Mileage deleteMlg) {
		String sql = "DELETE FROM MILEAGE WHERE CTM_NO = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, deleteMlg.getMlg_no());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
