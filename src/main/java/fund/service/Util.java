package fund.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    static SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd");

    public static String toYMD(Date date) {
        return formatYMD.format(date);
    }

}
