package rentcarTest.dto;

public class Memo {
	private String memo;
	private int year;
	private int day;
	private int month;

	public Memo() {
		super();
	}

	public Memo(String memo, int year, int day, int month) {
		super();
		this.memo = memo;
		this.year = year;
		this.day = day;
		this.month = month;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "Memo [memo=" + memo + ", year=" + year + ", day=" + day + ", month=" + month + "]";
	}
}
