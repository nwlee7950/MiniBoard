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
@ApiModel(value = "BoardParameterDto : 게시판 추가적인 정보", description = "부가적인 파라미터정보")
public class BoardParameterDto {
    @ApiModelProperty(value = "검색 조건")
    private String key;
    @ApiModelProperty(value = "검색어")
    private String word;
}
