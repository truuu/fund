package fund.service;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fund.dto.Sal;
import fund.dto.Xfer;

@Service
public class ExcelService {

    static Date d1900_01_01 = new GregorianCalendar(1900, 0, 1).getTime();
    @Autowired LogService logService;

    public List<Xfer> get자동이체Result(InputStream input) throws Exception {
        List<Xfer> result = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(input);
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; ++i) {
            Sheet sheet = workbook.getSheetAt(i);
            for (int r = 1; r < sheet.getPhysicalNumberOfRows() ; ++r) {
                Row row = sheet.getRow(r);
                String accountNo = "에러";
                Date date = d1900_01_01;
                int amount = -9;
                String etc1 = "에러";
                String etc2 = "에러";
                boolean valid = false;
                try {
                    accountNo = row.getCell(5).getStringCellValue();
                    if (StringUtils.isBlank(accountNo)) continue;
                    if ("159-22-01424-5(240-890012-16304)".equals(accountNo)) continue;
                    date = getDateValue(row.getCell(9));
                    amount = getIntValue(row.getCell(12));
                    etc1 = row.getCell(16).getStringCellValue();
                    etc2 = row.getCell(17).getStringCellValue();
                    valid = true;
                } catch (Exception e) {
                    logService.insert(e);
                }
                result.add(new Xfer(accountNo, date, amount, etc1, etc2, valid));
            }
        }
        return result;
    }

    public List<Sal> get급여공제Result(InputStream input) throws Exception {
        List<Sal> result = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(input);
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; ++i) {
            Sheet sheet = workbook.getSheetAt(i);
            for (int r = 1; r < sheet.getPhysicalNumberOfRows() ; ++r) {
                Row row = sheet.getRow(r);
                String commitmentNo = "에러";
                String name = "에러";
                int amount = -9;
                Date date = d1900_01_01;
                String etc = "에러";
                boolean valid = false;
                try {
                    commitmentNo = row.getCell(0).getStringCellValue();
                    name = row.getCell(2).getStringCellValue();
                    amount = getIntValue(row.getCell(6));
                    date = getDateValue(row.getCell(8));
                    etc = row.getCell(10).getStringCellValue();
                    valid = true;
                } catch (Exception e) {
                    logService.insert(e);
                }
                result.add(new Sal(commitmentNo, name, amount, date, etc, valid));
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
