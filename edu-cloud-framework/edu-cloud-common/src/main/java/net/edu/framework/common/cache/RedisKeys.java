package net.edu.framework.common.cache;

/**
 * Redis Key管理
 *
 * @author 阿沐 babamu@126.com
 */
public class RedisKeys {




    //*********************system******************************************-----------------
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


    public static String getPdf(String path) {
        return "file:pdf:" + path;
    }


    //*********************lesson******************************************-----------------
    public static String getLessonAttendLog(Long lessonId) {
        return "lesson:log:" + lessonId;
    }

    public static String getLessonIp(Long lessonId) {
        return "lesson:ip:" + lessonId;
    }

    public static String getLessonResources(Long lessonId) {
        return "lesson:resources:" + lessonId;
    }

    public static String getClassLesson(Long classId) {
        return "lesson:class:" + classId;
    }


    
    
    
    
    //*********************problem******************************************-----------------
    public static String getProblemInfo(Long problemId, String type) {
        return "problem:" + type + ":" + problemId;
    }

    public static String getKnowledgePointKey() {
        return "problem:kp:";
    }

    public static String getChoiceOptions(Long problemId) {
        return "problem:choice:" + problemId;
    }

    public static String getSample(Long problemId) {
        return "problem:sample:" + problemId;
    }
}
