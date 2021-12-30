package com.toyproject.miniboard.api;

import com.toyproject.miniboard.jwt.JwtFilter;
import com.toyproject.miniboard.jwt.TokenProvider;
import com.toyproject.miniboard.model.dto.UserDto;
import com.toyproject.miniboard.model.dto.UserLoginDto;
import com.toyproject.miniboard.model.dto.UserRegisterDto;
import com.toyproject.miniboard.model.service.CustomUserDetailsService;
import com.toyproject.miniboard.model.service.UserService;
import com.toyproject.miniboard.model.vo.Token;
import com.toyproject.miniboard.model.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(tags = {"User Controller"})
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @ApiOperation(value = "회원 가입을 한다.", response = String.class)
    @PostMapping()
    public ResponseEntity<String> register(@Valid @RequestPart(value = "userRegisterDto") UserRegisterDto userRegisterDto,
                                           @RequestPart(value = "file", required = false) MultipartFile multipartFile) throws Exception {
        log.info("register user : {}", userRegisterDto);
        userService.register(userRegisterDto, multipartFile);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @ApiOperation(value = "로그인", response = Token.class)
    @PostMapping("/login")
    public ResponseEntity<Token> login(@Valid @ApiParam(value = "id와 password", required = true) @RequestBody UserLoginDto userLoginDto) throws Exception{
        log.info("call login : {}", userLoginDto);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginDto.getId(), userLoginDto.getPassword());

        //유저 정보를 조회하여 인증 정보를 생성
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        //해당 인증 정보를 현재 실행중인 스레드(Security Context)에 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //해당 인증 정보를 기반으로 jwt 토큰을 생성
        String jwt = tokenProvider.createToken(authentication);
        log.debug("로그인 토큰정보 : {}", jwt);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        //생성된 Token을 Response Header에 넣고, Token vo 객체를 이용해 Response Body에도 넣어서 리턴
        return new ResponseEntity<>(new Token(jwt), httpHeaders, HttpStatus.OK);
    }

    @ApiOperation(value = "회원 정보를 수정한다.", response = String.class)
    @PutMapping()
    public ResponseEntity<String> updateUser(@Valid @RequestPart(value = "userDto") @ApiParam(value = "수정할 회원 정보", required = true)UserDto userDto,
                                             @RequestPart(value = "file", required = false) @ApiParam(value = "프로필 사진") MultipartFile multipartFile) throws Exception{
        log.debug("update user : {}", userDto);
        userService.updateUser(userDto, multipartFile);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @ApiOperation(value = "회원 탈퇴", response = String.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable @ApiParam(value = "삭제할 유저 ID", required = true) String id) throws Exception {
        log.info("delete user");
        userService.deleteUser(id);
        return new ResponseEntity<User>(HttpStatus.OK);
    }

    @ApiOperation(value="회원 한 명의 정보를 반환한다.",response= User.class)
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable @ApiParam(value = "조회할 회원의 ID", required = true) String id){
        return new ResponseEntity<User>(userService.selectByUserId(id),HttpStatus.OK);
    }

    @ApiOperation(value = "회원 목록을 반환한다.", response = List.class)
    @GetMapping
    public ResponseEntity<List<User>> userList() throws  Exception{
        log.info("user List");
        return new ResponseEntity<List<User>>(userService.userList(), HttpStatus.OK);
    }
}
