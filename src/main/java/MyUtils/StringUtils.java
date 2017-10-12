package MyUtils;

import org.apache.commons.lang.text.StrBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gujiannn@qq.com
 * @project: wbl
 * @description: TODO(the role of this class)
 * @date 2015-11-19 22:19
 * @singature When it has is lost, brave to give up.
 */
public class StringUtils {


    /**
     * 检查字符串是否为空
     * @param str 字符串
     * @return
     */
    public static boolean isNotEmpty(String str) {
        if (str == null) {
            return false;
        } else if (str.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *去空格、回车、换行符、制表符
     */
    public static String replaceBlank(String str) {
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            str = m.replaceAll("");
        }
        return str;
    }

    /**
     * 将字符串数组转化为Integer数组
     * @param strs
     * @return
     */
    public static Integer[] parseStringArrayToIntegerArray(String [] strs){
        Integer integers[] = new Integer[strs.length];
        for(int i=0;i<strs.length;i++){
            integers[i] = Integer.parseInt(strs[i]);
        }
        return integers;
    }

    /**
     * 将Integer数组转化为字符串数组
     * @param integers
     * @return
     */
    public static String[] parseIntegerArrayToStringArray(Integer [] integers){
        String strs[] = new String[integers.length];
        for(int i=0;i<strs.length;i++){
            strs[i] = String.valueOf(integers[i]);
        }
        return strs;
    }

    /**
     * 将字符串分割并转化为Integer数组
     * @param str
     * @param splitRegx
     * @return
     */
    public static Integer[] parseStringToIntegerArray(String str,String splitRegx){
        String strs[] = replaceBlank(str).split(splitRegx);
        return parseStringArrayToIntegerArray(strs);
    }

    /**
     * 判断是否为空串
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }


    /**
     * 锁进字符串，包含"..."
     * @param str
     * @param maxWidth
     * @return
     */
    public static String abbreviate(String str, int maxWidth) {
        return abbreviate(str, 0, maxWidth);
    }


    public static String abbreviate(String str, int offset, int maxWidth) {
        if (str == null) {
            return null;
        }
        if (maxWidth < 4) {
            throw new IllegalArgumentException("Minimum abbreviation width is 4");
        }
        if (str.length() <= maxWidth) {
            return str;
        }
        if (offset > str.length()) {
            offset = str.length();
        }
        if ((str.length() - offset) < (maxWidth - 3)) {
            offset = str.length() - (maxWidth - 3);
        }
        if (offset <= 4) {
            return str.substring(0, maxWidth - 3) + "...";
        }
        if (maxWidth < 7) {
            throw new IllegalArgumentException("Minimum abbreviation width with offset is 7");
        }
        if ((offset + (maxWidth - 3)) < str.length()) {
            return "..." + abbreviate(str.substring(offset), maxWidth - 3);
        }
        return "..." + str.substring(str.length() - (maxWidth - 3));
    }


    /**
     * 首字母大写
     * @param str
     * @return
     */
    public static String capitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        return new StrBuilder(strLen)
                .append(Character.toTitleCase(str.charAt(0)))
                .append(str.substring(1))
                .toString();
    }


    /**
     * 首字母小写
     * @param str
     * @return
     */
    public static String uncapitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        return new StrBuilder(strLen)
                .append(Character.toLowerCase(str.charAt(0)))
                .append(str.substring(1))
                .toString();
    }
}
