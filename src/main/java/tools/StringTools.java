package tools;

public class StringTools {
    public static boolean isNull(String str){
        if(str==null||"".equals(str)){
            return true;
        }else{
            return false;
        }
    }
}
