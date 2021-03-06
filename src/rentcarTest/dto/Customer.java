package rentcarTest.dto;

import java.util.List;

public class Customer {
//	릴레이션 (고객 객체 특징 / DB연동 필요요소)
	private int no;		//고객번호(기본키)
	private String name;	//성명
	private String tel;		//연락처
	private String address;	//주소
	private String remark;	//고객비고
	private int mile;	//고객마일리지
	private int list_ctm;	//블랙리스트
	
//	생성자
//	더 추가할 생성자? 매개변수 뭘로할지 피드백 주세요
	public Customer() {}

	public Customer(int no) {
		this.no = no;
	}

	public Customer(String name) {
		this.name = name;
	}

	public Customer(int no, String name, String tel, String address, String remark, int mile) {
		super();
		this.no = no;
		this.name = name;
		this.tel = tel;
		this.address = address;
		this.remark = remark;
		this.mile = mile;
	}

	public Customer(int no, String name, String tel, String address, String remark, int mile, int list_ctm) {
		this.no = no;
		this.name = name;
		this.tel = tel;
		this.address = address;
		this.remark = remark;
		this.mile = mile;
		this.list_ctm = list_ctm;
	}

	//	getter & setter & toString
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getMile() {
		return mile;
	}

	public void setMile(int mile) {
		this.mile = mile;
	}
	
	public int getList_ctm() {
		return list_ctm;
	}

	public void setList_ctm(int list_ctm) {
		this.list_ctm = list_ctm;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s %s %s %s", no, name, tel,
				address, remark, mile);
	}

	//	고객번호가 기본키이기 때문에 hashcode & equals 고객번호(no)만 비교
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + no;
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
		if (no != other.no)
			return false;
		return true;
	}
	

}
