package com.info.sms.dto;


import lombok.Data;

/**
 * Created by Luke 2020/6/29 16:42
 */
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private Long outerid;
    private String outerTitle;
    private String typeName;
    private Integer type;
}
