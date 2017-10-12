package MyUtils;

import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

public class DownloadUtils {

    void downLoad(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ServletContext servletContext = request.getServletContext();

        //读取要下载的文件
        String filePath = servletContext.getRealPath("/WEB-INF/downLoad/美女.jpg");
        FileInputStream fis = new FileInputStream(filePath);

        //下载名称 设置编码
        String msgName = "美女.jpg";
        msgName = fileNameEncoding(msgName, request);

        //设置两个响应体
        response.setHeader("Content-Type", servletContext.getMimeType(filePath));
        response.setHeader("Content-Disposition", "attachment;filename=" + msgName);

        //将读取流的数据 转到输出流中
        IOUtils.copy(fis, response.getOutputStream());
        fis.close();
    }

    // 用来对下载的文件名称进行编码的！
    public static String fileNameEncoding(String filename, HttpServletRequest request) throws IOException {
        String agent = request.getHeader("User-Agent"); //获取浏览器
        if (agent.contains("Firefox")) {
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filename = "=?utf-8?B?"
                    + base64Encoder.encode(filename.getBytes("utf-8"))
                    + "?=";
        } else if (agent.contains("MSIE")) {
            filename = URLEncoder.encode(filename, "utf-8");
        } else {
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }
}
