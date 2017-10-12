package MyUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by magicjian
 */
public class DateHelper {
    public static Date getMinuteAgoDate(Date date, Integer offset) {
        Calendar canlendar = Calendar.getInstance(); //java.util包
        canlendar.setTime(date);
        canlendar.add(Calendar.MINUTE, offset); //分钟减 如果不够减会将时变动
        date = canlendar.getTime();
        return date;
    }

    public static Date getHourAgoDate(Date date, Integer offset) {
        Calendar canlendar = Calendar.getInstance(); //java.util包
        canlendar.setTime(date);
        canlendar.add(Calendar.HOUR, offset); //小时减 如果不够减会将日期变动
        date = canlendar.getTime();
        return date;
    }

    public static Date getDayAgoDate(Date date, Integer offset) {
        Calendar canlendar = Calendar.getInstance(); //java.util包
        canlendar.setTime(date);
        canlendar.add(Calendar.DATE, offset); //日期减 如果不够减会将月份变动
        date = canlendar.getTime();
        return date;
    }

    public static String formatDate(String dateString, String formater) {
        String formatResult = "";
        try {
            SimpleDateFormat dateFormater = new SimpleDateFormat(formater);
            Date date = dateFormater.parse(dateString);
            formatResult = dateFormater.format(date);
        } catch (Exception e) {
            formatResult = dateString;
        }
        return formatResult;
    }

    public static String formatDate(Date date, String formater) {
        String formatResult = "";
        try {
            SimpleDateFormat dateFormater = new SimpleDateFormat(formater);
            formatResult = dateFormater.format(date);
        } catch (Exception e) {
            formatResult = "";
        }
        return formatResult;
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }


    public static String getDateDistanceBeforNow(Date date) {
        StringBuffer sb = new StringBuffer("");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        try {
            long l = now.getTime() - date.getTime();
            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);


            sb.append("发表于：");
            if (day > 0)
                sb.append(day + "天");
            else if (hour > 0)
                sb.append(hour + "小时");
            else if (min > 0)
                sb.append(min + "分");
            else sb.append(s + "秒");
            sb.append("前");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 时间日期
     *
     * @param calendar    原始时间
     * @param dayOffset   日期偏移量
     * @param monthOffset 月份偏移量
     * @param day         日期固定值
     * @param month       月份固定值
     * @return
     */
    public static Date calTime(Calendar calendar, Integer dayOffset, Integer monthOffset, Integer yearOffset, Integer day, Integer month, Integer year, Integer hourOffset,Integer hour,Integer minute ,Integer second,Integer milliSecond) {

        if (year != null) {
            calendar.set(Calendar.YEAR, year);
        }
        if (month != null) {
            calendar.set(Calendar.MONTH, month);
        }
        if (day != null) {
            calendar.set(Calendar.DAY_OF_MONTH, day);
        }
        if (hour != null) {
            calendar.set(Calendar.HOUR_OF_DAY, hour);
        }
        if (yearOffset != null) {
            calendar.set(Calendar.YEAR, calendar.YEAR + yearOffset);

        }
        if (monthOffset != null) {
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + monthOffset);
        }
        if (dayOffset != null) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(calendar.DATE) + dayOffset);
        }
        if (hourOffset != null) {
            calendar.set(Calendar.HOUR_OF_DAY, calendar.get(calendar.HOUR) + hourOffset);
        }
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND,milliSecond);
        return calendar.getTime();
    }
}
