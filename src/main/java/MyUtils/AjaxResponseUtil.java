package MyUtils;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author magic jian
 */
public class AjaxResponseUtil {

    /**
     * @param text
     */
    public static void response(String text, HttpServletResponse response) {
        response.setContentType("text/html");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(text);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void response(Object object, HttpServletResponse response) {
        Gson gson = new Gson();
        String text = gson.toJson(object);
        response(text, response);
    }
}
