package com.info.sms.model;

import lombok.Data;

/**
 * Created by Luke 2020/4/9 13:17
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private Long gmtCreate;
    private Integer creater;
    private Long gmtModified;
    private Integer modifier;
}
