package rentcarTest.dto;

import java.util.Date;

public class Rent {
//	릴레이션 (대여 객체 특징 / DB연동 필요요소)
	private int rent_no; // 대여번호
	private Customer ctm_no; // 고객번호
	private Car car_no; // 차량번호
	private Date rent_date; // 대여일자
	private Date return_date; // 반납일자
	private int rent_time;	//대여시간
	private int is_driver; // 기사여부
	private String rent_remark; // 대여비고
	
//	생성자
//	더 추가할 생성자? 매개변수 뭘로할지 피드백 주세요
	public Rent(int rent_no, Customer ctm_no, Car car_no, Date rent_date, Date return_date, int is_driver,
			String rent_remark) {
		super();
		this.rent_no = rent_no;
		this.ctm_no = ctm_no;
		this.car_no = car_no;
		this.rent_date = rent_date;
		this.return_date = return_date;
		this.is_driver = is_driver;
		this.rent_remark = rent_remark;
	}

	public Rent(int rent_no) {
		super();
		this.rent_no = rent_no;
	}

	public Rent(Customer ctm_no) {
		super();
		this.ctm_no = ctm_no;
	}

	public Rent(Car car_no) {
		super();
		this.car_no = car_no;
	}

	public Rent(Date rent_date, Date return_date) {
		super();
		this.rent_date = rent_date;
		this.return_date = return_date;
	}
	
//	getter & setter & toString
	public int getRent_no() {
		return rent_no;
	}

	public void setRent_no(int rent_no) {
		this.rent_no = rent_no;
	}

	public Customer getCtm_no() {
		return ctm_no;
	}

	public void setCtm_no(Customer ctm_no) {
		this.ctm_no = ctm_no;
	}

	public Car getCar_no() {
		return car_no;
	}

	public void setCar_no(Car car_no) {
		this.car_no = car_no;
	}

	public Date getRent_date() {
		return rent_date;
	}

	public void setRent_date(Date rent_date) {
		this.rent_date = rent_date;
	}

	public Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}

	public int getIs_driver() {
		return is_driver;
	}

	public void setIs_driver(int is_driver) {
		this.is_driver = is_driver;
	}

	public String getRent_remark() {
		return rent_remark;
	}

	public void setRent_remark(String rent_remark) {
		this.rent_remark = rent_remark;
	}

	@Override
	public String toString() {
		return String.format(
				"%s,%s,%s,%s,%s,%s,%s,%s",
				rent_no, ctm_no, car_no, rent_date, return_date, is_driver, rent_remark);
	}

//	고객번호가 기본키이기 때문에 hashcode & equals 고객번호(num)만 비교
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rent_no;
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
		Rent other = (Rent) obj;
		if (rent_no != other.rent_no)
			return false;
		return true;
	}
	
	
}
