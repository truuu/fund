package fund.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    static SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd");

    public static String toYMD() {
        return toYMD(new Date());
    }

    public static String toYMD(Date date) {
        return formatYMD.format(date);
    }

    public static Date parseYMD(String s) throws ParseException {
        return formatYMD.parse(s);
    }

}
