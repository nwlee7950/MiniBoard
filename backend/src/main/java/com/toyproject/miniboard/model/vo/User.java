package com.toyproject.miniboard.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class User {
    private String id;
    private String password;
    private String role;
    private String name;
    private String oauthType;
    private String profileImage;

    @Builder
    public User(String id, String password, String role, String name, String oauthType, String profileImage){
        this.id = id;
        this.password = password;
        this.role = role;
        this.name = name;
        this.oauthType = oauthType;
        this.profileImage = profileImage;
    }
}
