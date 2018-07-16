package json;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public class Msg {

    private final static  String Msg_SUCCESS = "success";
    private final static String Msg_ERROR = "error";

    public static JSONObject OK(Object o){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",200);
        map.put("msg",Msg_SUCCESS);
        map.put("data",o);
        return  new JSONObject(map);
    }

    public static JSONObject OK(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",200);
        map.put("msg",Msg_SUCCESS);
        return  new JSONObject(map);
    }

    public static JSONObject Error(int i){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("code",-1);
        map.put("msg",Code.Msg[i]);
        map.put("data"," ");
        return new JSONObject(map);
    }

    public static JSONObject Error(String str,int i){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("code",i);
        map.put("msg",str);
        map.put("data"," ");
        return new JSONObject(map);
    }

    public static JSONObject OK(Object data,String str){
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put(str,data);
        map.put("code",200);
        map.put("msg",Msg_SUCCESS);
        map.put("data",dataMap);
        return  new JSONObject(map);
    }
}
