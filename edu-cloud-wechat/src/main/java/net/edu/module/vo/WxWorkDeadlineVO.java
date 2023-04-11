package net.edu.module.vo;

import lombok.Data;

/**
 * @author weng
 * @date 2022/10/10 - 15:36
 **/
@Data
public class WxWorkDeadlineVO extends TemplateBaseVo{

    /**
     * 截止时间
     */
    private String deadline;

    /**
     * 提交方式
     */
    private String submitMethod;


    /**
     * 备注
     */
    private String remark;


    public String toJsonString() {
        return "{\"deadline\":\""+deadline
                +"\",\"submitMethod\":\""+submitMethod
                +"\",\"remark\":\""+remark
                +"\"}";
    }
}
