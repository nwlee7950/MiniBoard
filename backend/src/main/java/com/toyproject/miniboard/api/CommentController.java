package com.toyproject.miniboard.api;

import com.toyproject.miniboard.model.dto.CommentDto;
import com.toyproject.miniboard.model.service.CommentService;
import com.toyproject.miniboard.model.vo.Comment;
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
@Api(tags = {"Comment Controller"})
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    @ApiOperation(value = "댓글을 등록한다.", response = String.class)
    @PostMapping()
    public ResponseEntity<CommentDto> insertComment(@RequestBody @ApiParam(value = "댓글 정보", required = true) CommentDto commentDto) throws Exception {
        log.info("insert Comment : {}", commentDto);
        commentService.insertComment(commentDto);
        return new ResponseEntity<CommentDto>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "댓글을 수정한다.", response = String.class)
    @PutMapping
    public ResponseEntity<String> updateComment(@RequestBody @ApiParam(value = "수정할 댓글 정보", required = true) CommentDto commentDto) throws Exception {
        log.debug("update Comment : {}",commentDto);
        commentService.updateComment(commentDto);
        return new ResponseEntity<String>(HttpStatus.OK);
    }


    @ApiOperation(value = "댓글을 삭제한다.", response = String.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<CommentDto> deleteComment(@PathVariable @ApiParam(value = "삭제할 댓글 번호", required = true) int id) throws Exception {
        log.info("delete Comment");
        commentService.deleteComment(id);
        return new ResponseEntity<CommentDto>(HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 목록을 반환한다.", response = List.class)
    @GetMapping("/{boardId}")
    public ResponseEntity<List<Comment>> commentList(@PathVariable @ApiParam(value = "글 번호", required = true) long boardId) throws Exception {
        log.info("comment List called");
        return new ResponseEntity<List<Comment>>(commentService.commentList(boardId), HttpStatus.OK);
    }
}
