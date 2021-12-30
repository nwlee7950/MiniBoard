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
@ToString
@NoArgsConstructor
@ApiModel(value = "CommentDto : 댓글 정보", description = "댓글 정보를 나타낸다.")
public class CommentDto {
    @NotNull
    @ApiModelProperty(value = "댓글 번호")
    private int id;

    @NotNull
    @ApiModelProperty(value = "글 번호")
    private long boardId;

    @Size(min = 3, max = 50)
    @ApiModelProperty(value = "댓글 내용")
    private String content;

    @ApiModelProperty(value = "날짜")
    private String date;

    @Size(min = 3, max = 20)
    @ApiModelProperty(value = "댓글 작성자")
    private String userId;

    @ApiModelProperty(value = "부모의 댓글 번호")
    private Integer parentId;

    @ApiModelProperty(value = "댓글 그룹")
    private int commentGroup;

    @ApiModelProperty(value = "그룹 내 댓글 순서")
    private int sequence;
}
