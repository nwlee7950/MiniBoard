package com.toyproject.miniboard.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString
@ApiModel(value = "BoardDto : 게시글 정보", description = "게시글 정보를 나타낸다.")
public class BoardDto {
    @NotNull
    @ApiModelProperty(value = "글 번호")
    private long id;

    @NotNull
    @Size(min = 3, max = 20)
    @ApiModelProperty(value = "작성자")
    private String userId;

    @NotNull
    @Size(min = 3, max = 50)
    @ApiModelProperty(value = "제목")
    private String title;

    @NotNull
    @Size(min = 3, max = 50)
    @ApiModelProperty(value = "내용")
    private String content;

    @ApiModelProperty(value = "조회수")
    private long hits;

    @ApiModelProperty(value = "날짜")
    private String date;

    @ApiModelProperty(value = "공지사항 여부")
    private boolean isNotice;
}
