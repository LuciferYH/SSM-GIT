package ssm_maven.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date和String互相转换的工具类
 */
public class DateUtils {

    /**
     * Date转字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String date2String(Date date,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String format = sdf.format(date);
        return format;
    }

    public static Date string2Date(String str,String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date parse = sdf.parse(str);
        return parse;
    }
}
