package com.info.sms.dto;

import com.info.sms.model.User;
import lombok.Data;

/**
 * Created by Luke 2020/4/13 17:24
 */
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private Long gmtCreate;
    private Long creator;
    private Long gmtModified;
    private Long modifier;
    private User user;

}
