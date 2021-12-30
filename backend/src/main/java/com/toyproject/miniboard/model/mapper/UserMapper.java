package com.toyproject.miniboard.model.mapper;

import com.toyproject.miniboard.model.vo.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {
    public void register(User user) ;
    public int selectCountByUserId(String id);
    public void updateUser(User user);
    public void deleteUser(String id);
    public User selectByUserId(String id);
    public List<User> userList();
}
