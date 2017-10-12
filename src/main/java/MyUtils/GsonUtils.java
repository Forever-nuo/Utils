package MyUtils;

import com.google.gson.Gson;

/**
 * @author gujiannn@qq.com
 * @project: wbl
 * @description: TODO(the role of this class)
 * @date 2015-11-20 23:27
 * @singature When it has is lost, brave to give up.
 */
public class GsonUtils {
    private static Gson gson = new Gson();
    public static String convertObjectToJsonStr(Object object){
        return gson.toJson(object);
    }
}
