package com.toyproject.miniboard.api;

import com.toyproject.miniboard.model.dto.BoardDto;
import com.toyproject.miniboard.model.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = {"Board Controller"})
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;

    @ApiOperation(value = "게시글을 추가한다.", response = String.class)
    @PostMapping
    public ResponseEntity<BoardDto> insertBoard(@RequestBody @ApiParam(value = "게시글 정보", required = true) BoardDto boardDto) throws Exception {
        log.info("call insertBoard : {}", boardDto);
        boardService.insertBoard(boardDto);
        return new ResponseEntity<BoardDto>(HttpStatus.CREATED);
    }
}
