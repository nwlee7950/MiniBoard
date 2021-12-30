package com.toyproject.miniboard.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "UserRegisterDto : 회원가입시 필요한 회원 정보", description = "회원가입시 필요한 회원 정보를 나타낸다.")
public class UserRegisterDto {
    @NotNull
    @Size(min = 3, max = 20)
    private String id;

    @NotNull
    @Size(min = 3, max = 100)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    private String profileImage;
}
