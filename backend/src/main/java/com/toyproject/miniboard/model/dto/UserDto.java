package com.toyproject.miniboard.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@ApiModel(value = "UserDto : 회원정보", description = "회원의 상세 정보를 나타낸다")
public class UserDto {
    @ApiModelProperty(value = "회원 아이디")
    private String id;
    @ApiModelProperty(value = "회원 비밀번호")
    private String password;
    @ApiModelProperty(value = "회원 권한")
    private String role;
    @ApiModelProperty(value = "회원 이름")
    private String name;
    @ApiModelProperty(value = "회원 로그인 구분")
    private String oauthType;
    @ApiModelProperty(value = "회원 프로필 사진")
    private String profileImage;
}
