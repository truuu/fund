package fund.controller;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import fund.dto.Salary;

public class ReadExcelSalaryToList {
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

			//loop through each of the sheets
			for(int i=0; i < numberOfSheets; i++){

				//Get the nth sheet from the workbook
				Sheet sheet = workbook.getSheetAt(i);

				//every sheet has rows, iterate over them
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) 
				{
					String sponsorNo = "";
					String sponsorName = "";
					String amount = "";
					String paymentDate = "";


					//Get the row object
					Row row = rowIterator.next();
					//Every row has columns, get the column iterator and iterate over them
					Iterator<Cell> cellIterator = row.cellIterator();

					while (cellIterator.hasNext()) 
					{
						//Get the Cell object
						Cell cell = cellIterator.next();
						//check the cell type and process accordingly
						switch(cell.getCellType()){
						case Cell.CELL_TYPE_STRING:
							if(sponsorName.equalsIgnoreCase("")){
								//4nd column
								sponsorName = cell.getStringCellValue().trim();
							}else if(paymentDate.equalsIgnoreCase("")){
								paymentDate = cell.getStringCellValue().trim();
							}
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if(sponsorNo.equalsIgnoreCase("")){			
								Double sponsor = cell.getNumericCellValue();
								sponsorNo = sponsor.longValue()+"";
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
					} //end of cell iterator
					Salary c = new Salary(sponsorNo,sponsorName,amount,paymentDate);

					countriesList.add(c);


				} //end of rows iterator


			} //end of sheets for loop

			//close file input stream
			fis.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return countriesList;
	}
}
