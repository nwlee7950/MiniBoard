package com.toyproject.miniboard.model.service;

import com.toyproject.miniboard.model.dto.CommentDto;
import com.toyproject.miniboard.model.mapper.CommentMapper;
import com.toyproject.miniboard.model.vo.Comment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentMapper commentMapper;

    @Override
    public List<Comment> commentList(long boardId) {
        return commentMapper.commentList(boardId);
    }

    @Override
    public void insertComment(CommentDto commentDto) {
        if(commentDto.getParentId() == null){
            commentDto.setParentId(0);
            commentDto.setCommentGroup(commentMapper.getCommentGroupMax(commentDto.getBoardId()));
        } else{
            commentDto.setCommentGroup(commentMapper.getParentGroup(commentDto.getParentId()));
        }
        commentDto.setSequence(commentMapper.getSequence(commentDto.getBoardId(), commentDto.getCommentGroup()));

        Comment comment = Comment.builder()
                .boardId(commentDto.getBoardId())
                .content(commentDto.getContent())
                .userId(commentDto.getUserId())
                .parentId(commentDto.getParentId())
                .commentGroup(commentDto.getCommentGroup())
                .sequence(commentDto.getSequence())
                .build();

        commentMapper.insertComment(comment);
    }

    @Override
    public void updateComment(CommentDto commentDto) {
        Comment comment = Comment.builder()
                .id(commentDto.getId())
                .content(commentDto.getContent())
                .build();

        commentMapper.updateComment(comment);
    }

    @Override
    public void deleteComment(int id) {
        if(commentMapper.getParentId(id) == 0){
            commentMapper.deleteChildren(id);
        }
        commentMapper.deleteComment(id);
    }
}
