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
             
            // ������ �о�鿩 File Input ��Ʈ�� ��ü ����
            fis = new FileInputStream(file);
             
            // File Input ��Ʈ�� ��ü�� �̿��� Input ��Ʈ�� ��ü�� �����ϴµ� ���ڵ��� UTF-8�� ����
            isr = new InputStreamReader(fis, "UTF-8");
             
            // Input ��Ʈ�� ��ü�� �̿��Ͽ� ���۸� ����
            br = new BufferedReader(isr);
         
            // ���۸� �������� �о�鿩 ���� ����
            while( (temp = br.readLine()) != null) {
                content += temp + "\n";
            }
             
            System.out.println("================== ���� ���� ��� ==================");
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
