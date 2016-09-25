package fund.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReadEB22Date {
	public static String readEB22Date(String fileName){
		
		BufferedReader br = null;        

		InputStreamReader isr = null;    

		FileInputStream fis = null;        

		File file = new File(fileName);

		String temp = "";

		String content = "";

		try {

			// 파일을 읽어들여 File Input 스트림 객체 생성
			fis = new FileInputStream(file);

			// File Input 스트림 객체를 이용해 Input 스트림 객체를 생서하는데 인코딩을 UTF-8로 지정
			isr = new InputStreamReader(fis, "UTF-8");

			// Input 스트림 객체를 이용하여 버퍼를 생성
			br = new BufferedReader(isr);

			// 버퍼를 한줄한줄 읽어들여 내용 추출
			while( (temp = br.readLine()) != null) {
				content += temp + "\n";
			}

			String eb22Date = content.substring(27,33);
			
			SimpleDateFormat format1 = new SimpleDateFormat("yyMMdd");
			Date result = format1.parse(eb22Date);
			
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
