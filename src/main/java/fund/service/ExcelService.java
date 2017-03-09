package fund.service;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
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
                    if (StringUtils.isBlank(accountNo)) continue;
                    if ("159-22-01424-5(240-890012-16304)".equals(accountNo)) continue;
                    Date date = getDateValue(row.getCell(9));
                    int amount = getIntValue(row.getCell(12));
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
                    int amount = getIntValue(row.getCell(6));
                    Date date = getDateValue(row.getCell(8));
                    String etc = row.getCell(10).getStringCellValue();
                    result.add(new Sal(commitmentNo, name, amount, date, etc));
                } catch (Exception e) {
                    System.out.println(e.getMessage()); // TODO: 에러처리
                }
            }
        }
        return result;
    }

    static int getIntValue(Cell cell) {
        if (cell.getCellType() == Cell.CELL_TYPE_STRING)
            return (int)(double)Double.valueOf(cell.getStringCellValue().replaceAll(",", ""));
        return (int)cell.getNumericCellValue();
    }

    static Date getDateValue(Cell cell) throws ParseException {
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            String s = cell.getStringCellValue().replaceAll("/", "-");
            return Util.parseYMD(s);
        }
        return cell.getDateCellValue();
    }
}
