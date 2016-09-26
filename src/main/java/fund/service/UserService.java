package fund.service;

import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
 
 
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import fund.dto.Sponsor;

public class UserService {
	
	 public static void writeNoticeListToFile(String fileName,List<Sponsor> list) throws Exception{
		 Workbook workbook = null;
			
			if(fileName.endsWith("xlsx")){
				workbook = new XSSFWorkbook();
			}else if(fileName.endsWith("xls")){
				workbook = new HSSFWorkbook();
			}else{
				throw new Exception("invalid file name, should be xls or xlsx");
			}
			
			Sheet sheet = workbook.createSheet("DM발송주소록");
			
			Iterator<Sponsor> iterator = list.iterator();
			
			int rowIndex = 0;
			Row row = sheet.createRow(rowIndex++);
			Cell cell0 = row.createCell(0);
			cell0.setCellValue("회원번호");
			
			Cell cell1 = row.createCell(1);
			cell1.setCellValue("이름");
			
			Cell cell2 = row.createCell(2);
			cell2.setCellValue("후원인구분2");
			
			Cell cell3 = row.createCell(3);
			cell3.setCellValue("소속교회");
			
			Cell cell4 = row.createCell(4);
			cell4.setCellValue("우편번호");
			
			Cell cell5 = row.createCell(5);
			cell5.setCellValue("주소");
			while(iterator.hasNext()){
				Sponsor sponsor = iterator.next();
				row = sheet.createRow(rowIndex++);
				cell0 = row.createCell(0);
				cell0.setCellValue(sponsor.getId());
				
				cell1 = row.createCell(1);
				cell1.setCellValue(sponsor.getName());
				
				cell2 = row.createCell(2);
				cell2.setCellValue(sponsor.getSponsorType2());
				
				cell3 = row.createCell(3);
				cell3.setCellValue(sponsor.getChurch());
				
				cell4 = row.createCell(4);
				cell4.setCellValue(sponsor.getPostCode());
				
				cell5 = row.createCell(5);
				cell5.setCellValue(sponsor.getAddress());
				
			}
			
			//lets write the excel data to file now
			FileOutputStream fos = new FileOutputStream(fileName);
			workbook.write(fos);
			fos.close();
			System.out.println(fileName + " written successfully");
	    }

}