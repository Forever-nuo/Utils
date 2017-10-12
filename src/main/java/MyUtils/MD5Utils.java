package MyUtils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 * @类名: MD5Utils
 * @描述: 32位的md5加密方法
 * @作者 gujiannn@qq.com
 * @日期 2015年11月15日 上午11:29:57
 */
public class MD5Utils {
    // 全局数组
    private final static String[] strDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    static BiMap<Character, Character> biMap = HashBiMap.create();
    static {
        biMap.put('0', '9');
        biMap.put('1', '8');
        biMap.put('2', '7');
        biMap.put('3', '6');
        biMap.put('4', '5');
        biMap.put('5', '4');
        biMap.put('6', '3');
        biMap.put('7', '2');
        biMap.put('8', '1');
        biMap.put('9', '0');
        biMap.put('a', 'q');
        biMap.put('b', 'w');
        biMap.put('c', 'e');
        biMap.put('d', 'r');
        biMap.put('e', 't');
        biMap.put('f', 'y');
        biMap.put('g', 'u');
        biMap.put('h', 'i');
        biMap.put('i', 'o');
        biMap.put('g', 'p');
        biMap.put('k', 'a');
        biMap.put('l', 's');
        biMap.put('m', 'd');
        biMap.put('n', 'f');
        biMap.put('o', 'g');
        biMap.put('p', 'h');
        biMap.put('q', 'j');
        biMap.put('r', 'k');
        biMap.put('s', 'l');
        biMap.put('t', 'z');
        biMap.put('u', 'x');
        biMap.put('v', 'c');
        biMap.put('w', 'v');
        biMap.put('x', 'b');
        biMap.put('y', 'n');
        biMap.put('z', 'm');
    }

    public MD5Utils() {
    }

    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 返回形式只为数字
    @SuppressWarnings("unused")
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    /**
     * MD5加密
     * @param strObj
     * @return
     */
    public static String GetMD5Code(String strObj) {
        String resuoestring = null;
        try {
            resuoestring = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resuoestring = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resuoestring;
    }

    /**
     * 凯撒密码
     * @param password
     * @param isInverse
     * @return
     */
    public static String keyboardRelpace(String password,boolean isInverse) {
        String encodedPassword = "";
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            Pattern pattern = Pattern.compile("[a-z0-9]");
            if(pattern.matcher(String.valueOf(ch)).matches()){
                encodedPassword = encodedPassword + (isInverse?biMap.get(ch):biMap.inverse().get(ch));
            }
        }
        return encodedPassword;
    }

    /**
     * 加密
     * @param password
     * @return
     */
    public static String encode(String password){
        MD5Utils getMD5 = new MD5Utils();
        password = getMD5.GetMD5Code(password);
        password = getMD5.keyboardRelpace(password,false);
        return password;
    }

    /**
     * 一层解密
     * @param password
     * @return
     */
    public static String decode(String password) {
        MD5Utils getMD5 = new MD5Utils();
        password = getMD5.keyboardRelpace(password, true);
        password = getMD5.GetMD5Code(password);
        return password;
    }

    public static void main(String[] args) {
        System.out.println(encode("123456"));
        System.out.println(encode(1+encode("123456")));
    }
}
