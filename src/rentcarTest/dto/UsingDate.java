package rentcarTest.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UsingDate {
	public static void main(String[] args) {
		Date d = new Date();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy년MM월dd일 hh시 mm분");
		System.out.println(df.format(d));
		
		
	}
}
