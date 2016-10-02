package fund.controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ReadEB14Date {
	public static String readEB14Date(String fileName){
		BufferedReader br = null;        

		InputStreamReader isr = null;    

		FileInputStream fis = null;        

		File file = new File(fileName);

		String temp = "";

		String content = "";

		try {

			fis = new FileInputStream(file);

			isr = new InputStreamReader(fis, "UTF-8");

			br = new BufferedReader(isr);

			while( (temp = br.readLine()) != null) {
				content += temp + "\n";
			}

			String eb14Date = content.substring(27,33);
			
			SimpleDateFormat format1 = new SimpleDateFormat("yyMMdd");
			Date result = format1.parse(eb14Date);
			
			format1.applyPattern("yyyy-MM-dd");

			return format1.format(result);

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return null;
	}
}
