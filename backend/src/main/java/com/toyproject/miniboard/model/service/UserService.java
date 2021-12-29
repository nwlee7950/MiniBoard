package com.toyproject.miniboard.model.service;

import com.toyproject.miniboard.model.dto.UserDto;
import com.toyproject.miniboard.model.dto.UserRegisterDto;
import com.toyproject.miniboard.model.vo.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    public void register(UserRegisterDto userRegisterDto, MultipartFile multipartFile) throws Exception;
    public void updateUser(UserDto userDto, MultipartFile multipartFile) throws Exception;
    public void deleteUser(String id);
    public User selectByUserId(String id);
    public List<User> userList();
}
