package fund.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import fund.dto.EB14;

public class ReadEB14File {
	public static ArrayList<String> readEB14File(){
		BufferedReader br = null;        
        
        InputStreamReader isr = null;    
         
        FileInputStream fis = null;        
 
        File file = new File("/Users/parkeunsun/Documents/cms_sample/EB140610.txt");
 
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
             
            System.out.println("================== 파일 내용 출력 ==================");
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
