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

import fund.BaseController;
import fund.dto.Salary;

public class ReadExcelSalaryToList extends BaseController{
	public static List<Salary> readExcelData(String fileName) {
		List<Salary> countriesList = new ArrayList<Salary>();

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
				//every sheet has rows, iterate over them
				for(int rowIndex=1; rowIndex < sheet.getPhysicalNumberOfRows() ; rowIndex++ ){
					//row 0은 헤더정보라서 무시
					//System.out.println(sheet.getRow(rowIndex).getCell(0).getStringCellValue());
					if(sheet.getRow(rowIndex).getCell(0) != null && !isNullOrEmpty(sheet.getRow(rowIndex).getCell(1).getStringCellValue()) ){
						//현재 row 반환
						row = sheet.getRow(rowIndex);
						if(!"".equals(row.getCell(0)) && !" ".equals(row.getCell(0)) && row.getCell(0)!= null ) {
							Salary resultRow = null;
							String sponsorNo = "";
							String sponsorName = "";
							String amount = "";
							String paymentDate = "";

							for(int cellIndex=0;cellIndex<row.getPhysicalNumberOfCells();cellIndex++){
								if(cellIndex==0 || cellIndex==1 || cellIndex==5 || cellIndex==7){
									cell = row.getCell(cellIndex);
									//check the cell type and process accordingly
									switch(cell.getCellType()){
									case Cell.CELL_TYPE_STRING:
										if(sponsorNo.equalsIgnoreCase("")){
											sponsorNo = cell.getStringCellValue().trim();
										}else if(sponsorName.equalsIgnoreCase("")){
											sponsorName = cell.getStringCellValue().trim();
										}
										break;
									case Cell.CELL_TYPE_NUMERIC:
										if( DateUtil.isCellDateFormatted(cell)) {
											Date date = cell.getDateCellValue();
											paymentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
										}/**
										if(sponsorNo.equalsIgnoreCase("")){			
											Double sponsor = cell.getNumericCellValue();
											sponsorNo = sponsor.longValue()+"";
										}**/else{
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
							resultRow = new Salary(sponsorNo,sponsorName,amount,paymentDate);
							countriesList.add(resultRow);
						}//if
					}//if
				}//for
			}//for
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (IllegalStateException e){
			System.out.println("error");
		}
		return countriesList;
	}
	static boolean isNullOrEmpty(String s) {
		if (s == null) return true;
		return s.trim().isEmpty();
	}
}