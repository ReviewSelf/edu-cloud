package net.edu.module.vo;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/7 15:25
 * @Version: 1.0
 * @Description:
 * 判题请求常量
 */
public class JudgeFinalValue {
    public static final String SERVER_URL = "http://101.34.86.238:2358";

    public static final String X_EDU_TOKEN = "8MFLJ6Dh0KITeG7SOmSTo38KWg7HEpnq";

    public static final String X_EDU_ADMIN = "qghRoPKlJud7n1HWL0SnCVdW7tHO8kTj";

    public static final String SUBMIT_POST_URL = SERVER_URL + "/submissions?base64_encoded=true&wait=true&fields=time,memory,status_id";

    public static final String LANGUAGES_GET_URL = SERVER_URL + "/languages";


    public static final String RESULT_GET_URL = SERVER_URL + "/submissions/";


}
