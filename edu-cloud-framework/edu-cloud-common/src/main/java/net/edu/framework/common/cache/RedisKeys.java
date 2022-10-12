package net.edu.framework.common.cache;

/**
 * Redis Key管理
 *
 * @author 阿沐 babamu@126.com
 */
public class RedisKeys {




    //*********************system***********************************************************
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


    //***********************************lesson***********************************************************
    public static String getLessonAttendLog(Long lessonId) {
        if(lessonId!=null){
            return "lesson:log:" + lessonId;
        }
        else {
            return "lesson:log:";
        }

    }
    public static String getExamAttendLog(Long examId) {
        if(examId!=null){
            return "exam:log:" + examId;
        }
        else {
            return "lesson:log:";
        }

    }


    public static String getLessonIp(Long lessonId) {
        return "lesson:ip:" + lessonId;
    }


    public static String getExamIp(Long examId) {
        return "exam:ip:" + examId;
    }

    public static String getLessonResources(Long lessonId) {
        return "lesson:resources:" + lessonId;
    }
    public static String getLessonProblem(Long lessonId,Integer type) {
        if(type!=null){
            return   "lesson:problem:" + lessonId+":"+type;
        }
        else {
            return   "lesson:problem:" + lessonId;
        }

    }

    public static String getClassLesson(Long classId) {
        return "lesson:class:" + classId;
    }

    public static String getLessonPage(String key) {
        return "lesson:" + key;
    }
    
    
    
    
    //***********************************problem***********************************************************
    public static String getProblemInfo(Long problemId, String type) {
        return "problem:" + type + ":" + problemId;
    }

    public static String getKnowledgePointKey() {
        return "problem:kp:";
    }
    public static String getOrgKey() {
        return "problem:org:";
    }

    public static String getChoiceOptions(Long problemId) {
        return "problem:choice:option" + problemId;
    }

    public static String getSample(Long problemId) {
        return "problem:sample:" + problemId;
    }


    public static String getSampleFile(String path) {
        return "sample:" + path;
    }




//***********************************teach***********************************************************

    public static String getTeachStatistics() {
        return "teach:statistics" ;
    }

    public static String getActivityClass() {
        return "teach:activityclass" ;
    }











    //***********************************judge***********************************************************
    public static String getJudgeRecordFlag(Long userId, Long problemId, Integer problemType, Integer source, Long sourceId) {
        return "judge:record:"+userId+":" +problemId+":"+problemType+":"+source+":"+sourceId;
    }


}
