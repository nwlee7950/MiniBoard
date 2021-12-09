package com.toyproject.miniboard.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Comment {
    private int id;
    private long boardId;
    private String content;
    private String date;
    private String userId;
    private int parentId;
    private int commentGroup;
    private int sequence;

    @Builder
    public Comment(int id, long boardId, String content, String date, String userId, int parentId, int commentGroup, int sequence){
        this.id = id;
        this.boardId = boardId;
        this.content = content;
        this.date = date;
        this.userId = userId;
        this.parentId = parentId;
        this.commentGroup = commentGroup;
        this.sequence = sequence;
    }
}
