package rentcarTest.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UsingDate {
	public static void main(String[] args) {
		Date d = new Date();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(df.format(d));
		
	}
}
