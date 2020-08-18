package rentcarTest.dto;

public class Mileage {
//	릴레이션 (고객 객체 특징 / DB연동 필요요소)
	private int mlg_no; // 마일리지번호
	private int ctm_no; // 고객번호(기본키,외래키)
	private int point; // 마일리지
	private String mlg_remark; // 마일리지비고

	public Mileage(int mlg_no, int ctm_no, int point, String mlg_remark) {
		this.mlg_no = mlg_no;
		this.ctm_no = ctm_no;
		this.point = point;
		this.mlg_remark = mlg_remark;
	}

	public int getMlg_no() {
		return mlg_no;
	}

	public void setMlg_no(int mlg_no) {
		this.mlg_no = mlg_no;
	}

	public int getCtm_no() {
		return ctm_no;
	}

	public void setCtm_no(int ctm_no) {
		this.ctm_no = ctm_no;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getMlg_remark() {
		return mlg_remark;
	}

	public void setMlg_remark(String mlg_remark) {
		this.mlg_remark = mlg_remark;
	}

	@Override
	public String toString() {
		return String.format("Mileage [mlg_no=%s, ctm_no=%s, point=%s, mlg_remark=%s]", mlg_no, ctm_no, point,
				mlg_remark);
	}
//	고객번호가 기본키이기 때문에 hashcode & equals 마일리지번호(mlg_no)만 비교

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mlg_no;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mileage other = (Mileage) obj;
		if (mlg_no != other.mlg_no)
			return false;
		return true;
	}
	
	
}
