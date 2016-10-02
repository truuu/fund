package fund.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadEB14File {
	public static ArrayList<String> readEB14File(String fileName){
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

			int t = content.indexOf("T");
			String result = content.substring(120,t);

			int i=0;
			ArrayList<String> list = new ArrayList<String>();
			while(true){
				int r = result.indexOf("R",i);
				if(r<0) break;
				list.add(result.substring(r,r+120));
				i=r+96;
			}
			return list;

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