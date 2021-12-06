package com.toyproject.miniboard.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ToString
@ApiModel(value = "BoardDto : 게시글 정보", description = "게시글 정보를 나타낸다")
public class BoardDto {
    private long id;

    @NotNull
    private String userId;

    @NotNull
    private String title;

    @NotNull
    private String content;
    private long hits;
    private String date;
    private boolean isNotice;
}
