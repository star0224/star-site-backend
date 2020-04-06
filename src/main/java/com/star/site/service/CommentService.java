package com.star.site.service;

import com.star.site.entity.Comment;
import com.star.site.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findByArticleId(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public void delete(Integer commentId) {
        commentRepository.deleteById(commentId);
    }

    public Comment findCommentById(Integer commentId) {
        return commentRepository.findById(commentId).get();
    }
}
