package com.toyproject.miniboard.model.service;

import com.toyproject.miniboard.model.dto.UserDto;
import com.toyproject.miniboard.model.dto.UserRegisterDto;
import com.toyproject.miniboard.model.mapper.UserMapper;
import com.toyproject.miniboard.model.vo.Role;
import com.toyproject.miniboard.model.vo.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(UserRegisterDto userRegisterDto) throws Exception{
        if(userMapper.findUserById(userRegisterDto.getId()) != 0){
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        User user = User.builder()
                .id(userRegisterDto.getId())
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .role(Role.USER)
                .name(userRegisterDto.getName())
                .oauthType(userRegisterDto.getOauthType())
                .profileImage(userRegisterDto.getProfileImage())
                .build();

        userMapper.register(user);
    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = User.builder()
                .id(userDto.getId())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .name(userDto.getName())
                .profileImage(userDto.getProfileImage())
                .build();

        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(String id) {
        userMapper.deleteUser(id);
    }

    @Override
    public User getUser(String id) {
        return userMapper.getUser(id);
    }

    @Override
    public List<User> userList() {
        return userMapper.userList();
    }
}
