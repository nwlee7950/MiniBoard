package com.toyproject.miniboard.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Board {
    private long id;
    private String userId;
    private String title;
    private String content;
    private long hits;
    private String date;
    private boolean isNotice;

    @Builder
    Board(long id, String userId, String title, String content, long hits, String date, boolean isNotice){
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.date = date;
        this.isNotice = isNotice;
    }
}
