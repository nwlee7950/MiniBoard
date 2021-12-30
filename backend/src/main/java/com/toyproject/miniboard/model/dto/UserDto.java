package com.toyproject.miniboard.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@ApiModel(value = "UserDto : 회원정보", description = "회원의 상세 정보를 나타낸다.")
public class UserDto {
    @NotNull
    @Size(min = 3, max = 20)
    @ApiModelProperty(value = "회원 아이디")
    private String id;

    @NotNull
    @Size(min = 3, max = 100)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ApiModelProperty(value = "회원 비밀번호")
    private String password;

    @Size(min = 3, max = 15)
    @ApiModelProperty(value = "회원 권한")
    private String role;

    @NotNull
    @Size(min = 3, max = 15)
    @ApiModelProperty(value = "회원 이름")
    private String name;

    @ApiModelProperty(value = "회원 프로필 사진")
    private String profileImage;
}
