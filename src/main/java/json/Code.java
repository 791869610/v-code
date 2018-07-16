package json;


/**
 * Created by sy on 2017/4/5.
 */
public class Code {

    public final static int UNKNOWN_ERROR = 0;
    public final static int NOTFiND_ENTITY = 1;
    public final static int Missing_Parameters = 2;//缺少参数
    public final static int NETWORK_ERROR = 3;
    public final static int BIGSCREEN_ID_IsNot_EMPTY = 4;//大屏ID不能为空

    public static String[] Msg = new String[]{
            "未知错误",
            "没有找到对应模型信息",
            "缺少参数",
            "网络错误",
            "大屏ID不能为空"
    };


}
