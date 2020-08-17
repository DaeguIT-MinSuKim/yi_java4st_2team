package rentcarTest.dto;

public class Kind {
//	릴레이션 (차종 객체 특징 / DB연동 필요요소)
	private String car_kind;
	private String kind_name;

//	생성자
//	더 추가할 생성자? 매개변수 뭘로할지 피드백 주세요
	public Kind() {
	}

	public Kind(String car_kind) {
		super();
		this.car_kind = car_kind;
	}

	public Kind(String car_kind, String kind_name) {
		this.car_kind = car_kind;
		this.kind_name = kind_name;
	}
	
//	getter & setter
	public String getCar_kind() {
		return car_kind;
	}

	public void setCar_kind(String car_kind) {
		this.car_kind = car_kind;
	}

	public String getKind_name() {
		return kind_name;
	}

	public void setKind_name(String kind_name) {
		this.kind_name = kind_name;
	}

}
