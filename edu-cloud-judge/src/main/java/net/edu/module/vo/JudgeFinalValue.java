package net.edu.module.vo;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/7 15:25
 * @Version: 1.0
 * @Description:
 */
public class JudgeFinalValue {
    public static final String SERVER_URL = "http://124.70.217.106:2358";

    public static final String X_Edu_Token = "8MFLJ6Dh0KITeG7SOmSTo38KWg7HEpnq";

    public static final String X_Edu_Admin = "qghRoPKlJud7n1HWL0SnCVdW7tHO8kTj";

    public static final String SUBMIT_POST_URL = SERVER_URL + "/submissions?base64_encoded=true&wait=true&fields=time,memory,status_id";

    public static final String LANGUAGES_GET_URL = SERVER_URL + "/languages";

    //    public static final String RESULT_GET_URL="http://47.97.36.151:2358/submissions/batch?base64_encoded=false&fields=time,memory,expected_output,status_id,stdin&tokens=";
    public static final String RESULT_GET_URL = SERVER_URL + "/submissions/";


    public static void main(String[] args) {
        String cpu_time_limit="1000";
        String memory_limit="10000";
        String stdin="MSAy";
        String language_id="13";
        String expected_output="3";
        String source_code="I2luY2x1ZGUgPGlvc3RyZWFtPiAKdXNpbmcgbmFtZXNwYWNlIHN0ZDsKaW50IG1haW4oKSB7CgkgaW50IEEsIEI7ICAgIAoJIGNpbiA+PiBBID4+IEI7ICAgIAoJIGNvdXQgPDwgQSArIEI7ICAgIAoJIHJldHVybiAwOyAKfQ==";
        String result="{\"cpu_time_limit\":\""+cpu_time_limit
                +"\",\"memory_limit\":\""+memory_limit
                +"\",\"stdin\":\""+stdin
                +"\",\"language_id\":\""+language_id
                +"\",\"expected_output\":\""+expected_output
                +"\",\"source_code\":\""+source_code
                +"\"}";
        System.out.println(result);
    }
}
