package com.toyproject.miniboard.api;

import com.toyproject.miniboard.model.dto.BoardDto;
import com.toyproject.miniboard.model.dto.BoardParameterDto;
import com.toyproject.miniboard.model.service.BoardService;
import com.toyproject.miniboard.model.vo.Board;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = {"Board Controller"})
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    @ApiOperation(value = "게시글을 추가한다.", response = String.class)
    @PostMapping
    public ResponseEntity<Board> insertBoard(@RequestBody @ApiParam(value = "게시글 정보", required = true) BoardDto boardDto) throws Exception {
        log.info("insert Board : {}", boardDto);
        boardService.insertBoard(boardDto);
        return new ResponseEntity<Board>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "게시글을 삭제한다.", response = String.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<Board> deleteBoard(@PathVariable @ApiParam(value = "삭제할 글 번호", required = true) long id) throws Exception {
        log.info("delete board");
        boardService.deleteBoard(id);
        return new ResponseEntity<Board>(HttpStatus.OK);
    }

    @ApiOperation(value = "게시글을 수정한다.", response = String.class)
    @PutMapping
    public ResponseEntity<String> updateBoard(@RequestBody @ApiParam(value = "수정할 글 정보", required = true) BoardDto boardDto) throws Exception{
        log.debug("update Board:{}",boardDto);
        boardService.updateBoard(boardDto);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 목록을 반환한다.", response = List.class)
    @GetMapping
    public ResponseEntity<List<Board>> boardList(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = false) BoardParameterDto boardParameterDto) throws  Exception{
        log.info("board List:{}", boardParameterDto);
        return new ResponseEntity<List<Board>>(boardService.boardList(boardParameterDto), HttpStatus.OK);
    }

    @ApiOperation(value="게시글번호에 맞는 게시글을 반환한다.",response= List.class)
    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoard(@PathVariable @ApiParam(value = "조회할 글 번호", required = true) long id){
        return new ResponseEntity<Board>(boardService.getBoard(id),HttpStatus.OK);
    }
}
