package rentcarTest.dto;

public class Mileage {
//	릴레이션 (고객 객체 특징 / DB연동 필요요소)
	private int mlg_no;			// 마일리지번호 (기본키)
	private int ctm_no;			// 고객번호(외래키)
	private int mlg_kind;		// 마일리지 분류 (+ , - 체크사항)
	private int point;			// 마일리지
	private String mlg_remark; 	// 마일리지비고
	private String mlg_skind; //마일리지 차감 및 추가를 String 타입으로 보이게 함
	
//	생성자
//	더 추가할 생성자? 매개변수 뭘로할지 피드백 주세요
	public Mileage(int mlg_no, int ctm_no, int mlg_kind, int point, String mlg_remark) {
		super();
		this.mlg_no = mlg_no;
		this.ctm_no = ctm_no;
		this.mlg_kind = mlg_kind;
		this.point = point;
		this.mlg_remark = mlg_remark;
	}
	public Mileage() {
		super();
	}
	
public Mileage(int mlg_no) {
		super();
		this.mlg_no = mlg_no;
	}
//	getter & setter & toString
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
	public int getMlg_kind() {
		return mlg_kind;
	}
	public String getStringMlg_kind() {
		 getMlg_kind();
		if(mlg_kind == 1) {
			mlg_skind = "추가";
		}else {
			mlg_skind = "차감";
		}
		return mlg_skind;
	}
	public void setMlg_kind(int mlg_kind) {
		this.mlg_kind = mlg_kind;
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
		return String.format("Mileage [mlg_no=%s, ctm_no=%s, mlg_kind=%s, point=%s, mlg_remark=%s]", mlg_no, ctm_no,
				mlg_kind, point, mlg_remark);
	}
	
//	고객번호가 기본키이기 때문에 hashcode & equals 고객번호(no)만 비교
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
