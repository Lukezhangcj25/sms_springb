package com.info.sms.controller;

import com.info.sms.dto.CommentCreateDTO;
import com.info.sms.dto.CommentDTO;
import com.info.sms.dto.ResultDTO;
import com.info.sms.enums.CommentTypeEnum;
import com.info.sms.exception.CustomizeErrorCode;
import com.info.sms.model.Comment;
import com.info.sms.model.User;
import com.info.sms.service.CommentService;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Luke 2020/5/13 16:31
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request) {

        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_LOGIN);
        }

//        if(commentCreateDTO == null || commentCreateDTO.getContent() == null || commentCreateDTO.getContent() == ""){
        if(commentCreateDTO == null || StringUtils.isNullOrEmpty(commentCreateDTO.getContent())){
        return ResultDTO.errorOf(CustomizeErrorCode.COMMENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        comment.setParantId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setCreator(user.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLikeCount(0L);
        comment.setCommentCount(0);
        commentService.insert(comment,user);

        return ResultDTO.successOf();
    }



    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO<List> comments(@PathVariable(name="id") Long id, Model model){
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.successOf(commentDTOS);
    }
}
