package com.star.site.form;

import lombok.Data;

@Data
public class CommentForm {
    private Long articleId;
    private String content;
    private Integer parentCommentId;
    private String time;
    private String name;
    private String replyName;
    private String mail;
    private String ip;
    private String country;
    private String city;
    private String province;
    private String district;
}
