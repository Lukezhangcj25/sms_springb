package com.info.sms.exception;

/**
 * Created by Luke 2020/5/8 17:03
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    NOT_LOGIN(2000,"用户未登录!"),
    QUESTION_NOT_FOUND(2001,"问题不存在!"),
    TARGET_PARAM_NOT_FOUND(2002,"评论/问题不存在或已被删除,无法回复!"),
    SYS_ERROR(2003,"系统异常，请联系管理员！"),
    TYPE_NOT_FOUND(2004,"评论类型不能为空！"),
    TYPE_PARAM_WRONG(2004,"评论类型不存在！"),
    COMMENT_NOT_FOUND(2005,"您操作的评论不存在!"),
    COMMENT_IS_EMPTY(2006,"回复内容不能为空!"),
    READ_NOTIFICATION_FAIL(2007,"消息推送错误，不可读取!"),
    NOTIFICATION_NOT_FOUND(2008,"推送消息不存在!"),
    FILE_UPLOAD_FAIL(2009,"图片上传失败!"),
    ;

    private Integer code;
    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
