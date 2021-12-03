package com.toyproject.miniboard.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Comment {
    int id;
    long boardId;
    String content;
    String date;
    String userId;
    int parentId;
    int group;
    int sequence;

    @Builder
    public Comment(int id, long boardId, String content, String date, String userId, int parentId, int group, int sequence){
        this.id = id;
        this.boardId = boardId;
        this.content = content;
        this.date = date;
        this.userId = userId;
        this.parentId = parentId;
        this.group = group;
        this.sequence = sequence;
    }
}
