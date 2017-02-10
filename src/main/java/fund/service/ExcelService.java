package fund.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import fund.dto.Sal;
import fund.dto.Xfer;

public class ExcelService {

    public static List<Xfer> get자동이체Result(InputStream input) throws Exception {
        List<Xfer> result = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(input);
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; ++i) {
            Sheet sheet = workbook.getSheetAt(i);
            for (int r = 1; r < sheet.getPhysicalNumberOfRows() ; ++r) {
                Row row = sheet.getRow(r);
                try {
                    String accountNo = row.getCell(5).getStringCellValue();
                    if ("159-22-01424-5(240-890012-16304)".equals(accountNo)) continue;
                    Date date = row.getCell(9).getDateCellValue();
                    int amount = (int)row.getCell(12).getNumericCellValue();
                    String etc1 = row.getCell(16).getStringCellValue();
                    String etc2 = row.getCell(17).getStringCellValue();
                    result.add(new Xfer(accountNo, date, amount, etc1, etc2));
                } catch (Exception e) {
                    System.out.println(e.getMessage()); // TODO: 에러처리
                }
            }
        }
        return result;
    }

    public static List<Sal> get급여공제Result(InputStream input) throws Exception {
        List<Sal> result = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(input);
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; ++i) {
            Sheet sheet = workbook.getSheetAt(i);
            for (int r = 1; r < sheet.getPhysicalNumberOfRows() ; ++r) {
                Row row = sheet.getRow(r);
                try {
                    String commitmentNo = null;
                    try { commitmentNo = row.getCell(0).getStringCellValue(); } catch (Exception e) {}
                    String name = row.getCell(2).getStringCellValue();
                    int amount = (int)row.getCell(6).getNumericCellValue();
                    Date date = row.getCell(8).getDateCellValue();
                    String etc = row.getCell(10).getStringCellValue();
                    result.add(new Sal(commitmentNo, name, amount, date, etc));
                } catch (Exception e) {
                    System.out.println(e.getMessage()); // TODO: 에러처리
                }
            }
        }
        return result;
    }
}
