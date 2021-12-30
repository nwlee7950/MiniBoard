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
    private Role role;
    private String name;
    private String profileImage;

    @Builder
    public User(String id, String password, Role role, String name, String profileImage){
        this.id = id;
        this.password = password;
        this.role = role;
        this.name = name;
        this.profileImage = profileImage;
    }
}
