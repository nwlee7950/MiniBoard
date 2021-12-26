package com.toyproject.miniboard.model.service;

import com.toyproject.miniboard.model.mapper.UserMapper;
import com.toyproject.miniboard.model.vo.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {
        User user = userMapper.selectByUserId(username);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getId())
                .password(user.getPassword())
                .roles(user.getRole().getKey())
                .build();
    }
}
