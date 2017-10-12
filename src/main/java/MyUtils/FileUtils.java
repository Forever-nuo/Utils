package MyUtils;


import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */
public class FileUtils {
    /**
     * 根据文件名 获得文件的字节
     *
     * @param name
     * @return
     */
    public static byte[] getByteByFileName(String name) {
        File file = new File(name); //创建一个文件
        byte[] b = new byte[(int) file.length()]; //创建缓存区

        try {
            InputStream in = new FileInputStream(file);//创建文件读取流
            try {
                in.read(b);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                in.close();
            }
        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }

        return b;
    }

    /***
     * 对字节进行md5加密
     *
     * @param s
     * @return
     */
    public static String md5(byte[] s) {
        //16进制字符
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] strTemp = s;
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {      //移位 输出字符串
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据文件名
     * 对文件进行md5加密
     *
     * @param fileName
     * @return
     */
    public static String md5(String fileName) {
        return md5(getByteByFileName(fileName));
    }

    /**
     * 判断2个文件是否相同
     */
    public static boolean fileIsSame(String fileName, String otherFileName) {
        return md5(fileName).equals(md5(otherFileName));
    }

    /**
     * 多个图片地址,得到图片地址的md5值,并且拼接字符串并且是排好序的
     *
     * @param imgContentUrl 相对路径字符串
     * @return
     */
    public static String getFileMd5s(String imgContentUrl) {
        if (null == imgContentUrl)
            return null;
        String[] imgContentUrlSplit = imgContentUrl.split(",");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < imgContentUrlSplit.length; i++) {
            builder.append(FileUtils.md5(getImgFullPath(imgContentUrlSplit[i])));
            if (i < imgContentUrlSplit.length - 1)
                builder.append(",");
        }
        return sortFileMd5s(builder.toString());
    }


    /***
     * 对多个md5值进行排序
     */
    public static String sortFileMd5s(String fileMd5s) {
        if (null == fileMd5s)
            return null;
        String[] split = fileMd5s.split(",");
        Arrays.sort(split);
        return StringUtils.join(split, ",");
    }


    /***
     * 根据相对路径得到上传图片的绝对路径
     *
     * @param imgUrl
     * @return
     */
    public static String getImgFullPath(String imgUrl) {
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        path = path.substring(0, path.lastIndexOf("/WEB-INF")); //截取掉classes
        return path + imgUrl.substring(5); //再拼接成真正的绝对路径   这儿的5 是截取的图片路径的 项目不同可能值不同
    }

    public static void main(String[] args) {
        String fileName1 = "C:\\Users\\Administrator\\Desktop\\测试资源\\迷你书.pdf";
        String fileName2 = "C:\\Users\\Administrator\\Desktop\\测试资源\\第二个.pdf";
        String fileName3 = "C:\\Users\\Administrator\\Desktop\\测试资源\\第二个 - 副本.pdf";
        boolean b = fileIsSame(fileName3, fileName2);
        if (b) {
            System.out.println("两个文件相同");
        } else {
            System.out.println("两个文件不相同");
        }
    }

    /**
     * rm 删除文件
     *
     * @param file
     */
    public static void rm(File file) {
        file.length();
        try {
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
            if (file.isFile()) {
                file.delete();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 删除多个文件根据urls
     *
     * @param url
     */
    public static void rmFileByUrls(String url) {
        String[] urlSplit = url.split(",");
        rmFileByUrls(Arrays.asList(urlSplit));
    }

    /**
     * 删除多个文件 根据 urls 集合
     *
     * @param urls
     */
    public static void rmFileByUrls(List<String> urls) {
        for (String url : urls)
            rm(new File(getImgFullPath(url)));
    }
}
