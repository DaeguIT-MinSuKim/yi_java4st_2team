package rentcarTest.dto;

public class Customer {
//	릴레이션 (고객 객체 특징 / DB연동 필요요소)
	private int num;		//고객번호(기본키)
	private String name;	//성명
	private int tel;		//연락처
	private String address;	//주소
	private String remark;	//고객비고
	private int mileage;	//마일리지
	private String mlg_remark; //마일리지비고
	
//	생성자
//	더 추가할 생성자? 매개변수 뭘로할지 피드백 주세요
	public Customer(int num, String name, int tel, String address, String remark, int mileage, String mlg_remark) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
		this.address = address;
		this.remark = remark;
		this.mileage = mileage;
		this.mlg_remark = mlg_remark;
	}
	
	public Customer(int num) {
		this.num = num;
	}

//	getter & setter & toString
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getMlg_remark() {
		return mlg_remark;
	}
	
	public void setMlg_remark(String mlg_remark) {
		this.mlg_remark = mlg_remark;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s %s %s %s", num, name, tel, address, mileage, remark);
	}

	//	고객번호가 기본키이기 때문에 hashcode & equals 고객번호(num)만 비교
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num;
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
		Customer other = (Customer) obj;
		if (num != other.num)
			return false;
		return true;
	}
	

}
