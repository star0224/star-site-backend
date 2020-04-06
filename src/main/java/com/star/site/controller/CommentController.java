package com.star.site.controller;

import com.alibaba.fastjson.JSON;
import com.star.site.common.StarResponse;
import com.star.site.common.StarResponseCode;
import com.star.site.entity.Comment;
import com.star.site.form.CommentForm;
import com.star.site.service.CommentService;
import com.star.site.utils.StarDateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CommentController {

    @Resource
    private CommentService commentService;

    @GetMapping("/comment/get/list")
    public String findByArticleId(Long articleId) {
        List<Comment> comments = commentService.findByArticleId(articleId);
        return comments != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取所有评论成功", comments))
                : JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取所有评论失败"));
    }

    @PostMapping("/comment/save")
    public String save(@RequestBody CommentForm commentForm) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentForm, comment);
        comment.setTime(StarDateUtils.getTime());
        Comment save = commentService.save(comment);
        return save != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "评论成功", save)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "评论失败"));
    }

    @GetMapping("/comment/delete")
    public String delete(Integer commentId, String ip) {
        // 先验证当前ip和存储ip是否相同，若不同直接取消
        if (!(commentService.findCommentById(commentId).getIp().equals(ip))) {
            return JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "当前ip不正确"));
        }
        try {
            commentService.delete(commentId);
            return JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "删除成功"));
        } catch (Exception e) {
            return JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "删除失败，数据库异常"));
        }
    }
}
