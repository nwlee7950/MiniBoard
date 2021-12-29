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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void register(UserRegisterDto userRegisterDto, MultipartFile multipartFile) throws Exception{
        if(userMapper.selectCountByUserId(userRegisterDto.getId()) != 0){
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        String separ = File.separator;

        File file = new File("");
        String rootPath = file.getAbsolutePath().split("src")[0];

        String savePath = rootPath + separ + "src" + separ + "main" + separ + "resources" + separ + "profileImg";
        if(!new File(savePath).exists()){
            try{
                new File(savePath).mkdirs();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        String originFileName = multipartFile.getOriginalFilename();
        String saveFileName = UUID.randomUUID().toString() + originFileName.substring(originFileName.lastIndexOf("."));

        String filePath = savePath + separ + saveFileName;
        multipartFile.transferTo(new File(filePath));

        User user = User.builder()
                .id(userRegisterDto.getId())
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .role(Role.USER)
                .name(userRegisterDto.getName())
                .oauthType(userRegisterDto.getOauthType())
                .profileImage(saveFileName)
                .build();

        userMapper.register(user);
    }

    @Override
    @Transactional
    public void updateUser(UserDto userDto, MultipartFile multipartFile) throws Exception{
        String separ = File.separator;

        File file = new File("");
        String rootPath = file.getAbsolutePath().split("src")[0];
        String savePath = rootPath + separ + "src" + separ + "main" + separ + "resources" + separ + "profileImg";

        File originFile = new File(savePath + separ + userDto.getProfileImage());

        if(originFile.exists()){
            originFile.delete();
            log.info("이전 프로필 사진 삭제");
        }

        String originFileName = multipartFile.getOriginalFilename();
        String saveFileName = UUID.randomUUID().toString() + originFileName.substring(originFileName.lastIndexOf("."));

        String filePath = savePath + separ + saveFileName;
        multipartFile.transferTo(new File(filePath));

        userDto.setProfileImage(saveFileName);

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
    public User selectByUserId(String id) {
        return userMapper.selectByUserId(id);
    }

    @Override
    public List<User> userList() {
        return userMapper.userList();
    }
}
