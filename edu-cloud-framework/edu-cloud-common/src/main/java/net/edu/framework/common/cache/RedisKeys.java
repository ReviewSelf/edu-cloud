package net.edu.framework.common.cache;

/**
 * Redis Key管理
 *
 * @author 阿沐 babamu@126.com
 */
public class RedisKeys {

    /**
     * 验证码Key
     */
    public static String getCaptchaKey(String key) {
        return "sys:captcha:" + key;
    }

    /**
     * accessToken Key
     */
    public static String getAccessTokenKey(String accessToken) {
        return "sys:access:" + accessToken;
    }


    public static String getKnowledgePointKey() {
        return "problem:kp:" ;
    }

    public static String getChoiceOptions(Long problemId){
        return "problem:choice:"+problemId;
    }

    public static String getSample(Long problemId) {
        return "problem:sample:"+problemId;
    }

    public static String getLessonAttendLog(Long lessonId) {
        return "lesson:log:"+lessonId;
    }

    public static String getLessonIp(Long lessonId) {
        return "lesson:ip:"+lessonId;
    }

    public static String getLesson(Long classId) {
        return "lesson:"+classId;
    }
}
