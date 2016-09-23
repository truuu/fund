package fund.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import fund.dto.XferResult;

public class ReadExcelFileToList {

	public static List<XferResult> readExcelData(String fileName) {
		List<XferResult> countriesList = new ArrayList<XferResult>();

		try {
			//Create the input stream from the xlsx/xls file
			FileInputStream fis = new FileInputStream(fileName);

			//Create Workbook instance for xlsx/xls file input stream
			Workbook workbook = null;
			if(fileName.toLowerCase().endsWith("xlsx")){
				workbook = new XSSFWorkbook(fis);
			}else if(fileName.toLowerCase().endsWith("xls")){
				workbook = new HSSFWorkbook(fis);
			}

			//Get the number of sheets in the xlsx file
			int numberOfSheets = workbook.getNumberOfSheets();
			Row row;
			Cell cell;
			//loop through each of the sheets
			for(int i=0; i < numberOfSheets; i++){

				//Get the nth sheet from the workbook
				Sheet sheet = workbook.getSheetAt(i);

				for(int rowIndex=1; rowIndex < sheet.getPhysicalNumberOfRows() ; rowIndex++ ){
					//row 0은 헤더정보라서 무시

					if(sheet.getRow(rowIndex).getCell(0) != null && !isNullOrEmpty(sheet.getRow(rowIndex).getCell(0).getStringCellValue()) && "159-22-01424-5(240-890012-16304)".equalsIgnoreCase(sheet.getRow(rowIndex).getCell(5).getStringCellValue()) == false){
						//현재 row 반환
						row = sheet.getRow(rowIndex);
						if(!"".equals(row.getCell(0)) && !" ".equals(row.getCell(0)) && row.getCell(0)!= null ) {
							XferResult resultRow = null;

							String accountNo="";
							String sponsorName="";
							String amount = "";
							String paymentDate = "";
							String paymentWay="";

							//row의 첫번째 cell값이 비어있지 않은 경우 만 cell탐색
							//cell탐색 for문
							for(int cellIndex=0;cellIndex<row.getPhysicalNumberOfCells();cellIndex++){
								if(cellIndex==5 || cellIndex==9 || cellIndex==12 || cellIndex==16 || cellIndex==17){
									cell = row.getCell(cellIndex);
									if(true){
										switch(cell.getCellType()){
										case Cell.CELL_TYPE_STRING:
											if(accountNo.equalsIgnoreCase("")){
												accountNo = cell.getStringCellValue().trim();
											}else if(sponsorName.equalsIgnoreCase("")){
												sponsorName = cell.getStringCellValue().trim();
											}else if(paymentWay.equalsIgnoreCase("")){
												paymentWay = cell.getStringCellValue().trim();
											}
											break;
										case Cell.CELL_TYPE_BLANK:
											if(sponsorName.equalsIgnoreCase("")){
												sponsorName="  ";
											}
										case Cell.CELL_TYPE_NUMERIC:
											if( DateUtil.isCellDateFormatted(cell)) {
												Date date = cell.getDateCellValue();
												paymentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
											}else{
												Double money = cell.getNumericCellValue();
												if(Math.floor(money) == money){
													amount = money.intValue()+"";
												}else{
													amount = money+"";
												}
											}
											break;
										}
									}
								}
							}

							resultRow = new XferResult(accountNo,sponsorName,amount,paymentDate,paymentWay);
							countriesList.add(resultRow);						
						}//if		
					}//if
				}//for

			}//for
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return countriesList;
	}

	static boolean isNullOrEmpty(String s) {
		if (s == null) return true;
		return s.trim().isEmpty();
	}

}