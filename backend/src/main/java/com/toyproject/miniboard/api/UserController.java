package com.toyproject.miniboard.api;

import com.toyproject.miniboard.model.dto.BoardParameterDto;
import com.toyproject.miniboard.model.dto.UserDto;
import com.toyproject.miniboard.model.dto.UserRegisterDto;
import com.toyproject.miniboard.model.service.UserService;
import com.toyproject.miniboard.model.vo.Board;
import com.toyproject.miniboard.model.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "회원 가입을 한다.", response = String.class)
    @PostMapping()
    public ResponseEntity<String> register(@Valid @RequestBody UserRegisterDto userRegisterDto) throws Exception {
        log.info("register user : {}", userRegisterDto);
        userService.register(userRegisterDto);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @ApiOperation(value = "회원 정보를 수정한다.", response = String.class)
    @PutMapping()
    public ResponseEntity<String> updateUser(@RequestBody @ApiParam(value = "수정할 회원 정보", required = true)UserDto userDto) throws Exception{
        log.debug("update user : {}", userDto);
        userService.updateUser(userDto);
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
        return new ResponseEntity<User>(userService.getUser(id),HttpStatus.OK);
    }

    @ApiOperation(value = "회원 목록을 반환한다.", response = List.class)
    @GetMapping
    public ResponseEntity<List<User>> userList() throws  Exception{
        log.info("user List");
        return new ResponseEntity<List<User>>(userService.userList(), HttpStatus.OK);
    }
}
