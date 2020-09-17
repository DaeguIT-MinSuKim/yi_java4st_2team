package rentcarTest.dto;

public class RentPerformance {
	private String car_Kind;
	private String car_Name;
	private long rent_Time;
	private long sale_Price;
	private long final_Price;

	public RentPerformance() {
	}

	public RentPerformance(String car_Kind, String car_Name, long rent_Time, long sale_Price, long final_Price) {
		super();
		this.car_Kind = car_Kind;
		this.car_Name = car_Name;
		this.rent_Time = rent_Time;
		this.sale_Price = sale_Price;
		this.final_Price = final_Price;
	}

	public String getCar_Kind() {
		return car_Kind;
	}

	public void setCar_Kind(String car_Kind) {
		this.car_Kind = car_Kind;
	}

	public String getCar_Name() {
		return car_Name;
	}

	public void setCar_Name(String car_Name) {
		this.car_Name = car_Name;
	}

	public long getRent_Time() {
		return rent_Time;
	}

	public void setRent_Time(long rent_Time) {
		this.rent_Time = rent_Time;
	}

	public long getSale_Price() {
		return sale_Price;
	}

	public void setSale_Price(long sale_Price) {
		this.sale_Price = sale_Price;
	}

	public long getFinal_Price() {
		return final_Price;
	}

	public void setFinal_Price(long final_Price) {
		this.final_Price = final_Price;
	}

	@Override
	public String toString() {
		return String.format("RentPerformance [car_Kind=%s, car_Name=%s, rent_Time=%s, sale_Price=%s, final_Price=%s]",
				car_Kind, car_Name, rent_Time, sale_Price, final_Price);
	}

}
