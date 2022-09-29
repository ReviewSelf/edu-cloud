package net.edu.module.vo;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/7 15:43
 * @Version: 1.0
 * @Description:
 */
public class JudgeStatusCode {
    public static final int OJ=0;
    public static final int AC=3;
    public static final int WA=4;
    public static String getStatus(String code){
        switch (code){
            case "1":
                return "In Queue";
            case "2":
                return "Processing";
            case "3":
                return "Accepted";
            case "4":
                return "Wrong Answer";
            case "5":
                return "Time Limit Exceeded";
            case "6":
                return "Compilation Error";
            case "7":
                return "Runtime Error (SIGSEGV)";
            case "8":
                return "Runtime Error (SIGXFSZ)";
            case "9":
                return "Runtime Error (SIGFPE)";
            case "10":
                return "Runtime Error (SIGABRT)";
             case "11":
                return "Runtime Error (NZEC)";
            case "12":
                return "Runtime Error (Other)";
            case "13":
                return "Internal Error";
            case "14":
                return "Exec Format Error";
            default:
                return "Unknown Code";
        }
    }




}
