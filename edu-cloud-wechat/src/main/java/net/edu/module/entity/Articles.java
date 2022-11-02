package net.edu.module.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author weng
 * @date 2022/10/28 - 12:19
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class Articles {
    /**
     * 图文消息标题
     */
    @XmlElement(name="Title")
    private String title;

    /**
     * 图文消息描述
     */
    @XmlElement(name="Description")
    private String description;

    /**
     * 图片链接
     */
    @XmlElement(name="PicUrl")
    private String picUrl;

    /**
     * 点击图文消息跳转链接
     */
    @XmlElement(name="Url")
    private String url;
}