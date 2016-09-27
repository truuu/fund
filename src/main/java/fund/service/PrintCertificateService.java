package fund.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class PrintCertificateService {

	public static String printDate(){
		
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyy년 MM월 dd일");
		String date1 =(today.format(date)).toString();
		
		return date1;
	}

}
