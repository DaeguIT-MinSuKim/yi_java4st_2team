package rentcarTest.dto;

public class Car {
//	릴레이션 (차량 객체 특징 / DB연동 필요요소)
	private String carNo; // 차번호(기본키)
	private String carName; // 차종
	private Kind carKind; // 차분류
	private String fuel; // 연료
	private int distance; // 주행거리
	private int fare; // 기본요금
	private int sale; // 장기할인
	private String carRemark; // 차량비고

//	생성자
//	더 추가할 생성자? 매개변수 뭘로할지 피드백 주세요
	public Car() {}
	
	public Car(String carNo, String carName, Kind carKind, String fuel, int distance, int fare, int sale,
			String carRemark) {
		super();
		this.carNo = carNo;
		this.carName = carName;
		this.carKind = carKind;
		this.fuel = fuel;
		this.distance = distance;
		this.fare = fare;
		this.sale = sale;
		this.carRemark = carRemark;
	}

	public Car(String carNo) {
		this.carNo = carNo;
	}

	public Car(Kind carKind) {
		this.carKind = carKind;
	}


	//	getter & setter & toString
	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public Kind getCarKind() {
		return carKind;
	}

	public void setCarKind(Kind carKind) {
		this.carKind = carKind;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	public String getCarRemark() {
		return carRemark;
	}

	public void setCarRemark(String carRemark) {
		this.carRemark = carRemark;
	}

	@Override
	public String toString() {
		return String.format("%s,%s,%s,%s,%s,%s,%s,%s", carNo, carName, carKind, fuel, distance, fare, sale, carRemark);
	}

//	차량번호가 기본키이기 때문에 hashcode & equals 차량번호(no)만 비교
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carNo == null) ? 0 : carNo.hashCode());
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
		Car other = (Car) obj;
		if (carNo == null) {
			if (other.carNo != null)
				return false;
		} else if (!carNo.equals(other.carNo))
			return false;
		return true;
	}

	
	
}
