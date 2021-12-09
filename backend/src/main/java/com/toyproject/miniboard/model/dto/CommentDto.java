package com.toyproject.miniboard.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(value = "댓글 정보", description = "댓글 정보를 가진 Dto")
public class CommentDto {
    @ApiModelProperty(value = "댓글 번호")
    private int id;
    @ApiModelProperty(value = "글 번호")
    private long boardId;
    @ApiModelProperty(value = "댓글 내용")
    private String content;
    @ApiModelProperty(value = "날짜")
    private String date;
    @ApiModelProperty(value = "댓글 작성자")
    private String userId;
    @ApiModelProperty(value = "부모의 댓글 번호")
    private Integer parentId;
    @ApiModelProperty(value = "댓글 그룹")
    private int commentGroup;
    @ApiModelProperty(value = "그룹 내 댓글 순서")
    private int sequence;
}
