package com.toyproject.miniboard.model.service;

import com.toyproject.miniboard.model.dto.CommentDto;
import com.toyproject.miniboard.model.vo.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> commentList(long boardId);
    public void insertComment(CommentDto commentDto);
    public void updateComment(CommentDto commentDto);
    public void deleteComment(int id);
}
