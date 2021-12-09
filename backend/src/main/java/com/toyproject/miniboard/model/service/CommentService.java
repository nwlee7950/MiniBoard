package com.toyproject.miniboard.model.service;

import com.toyproject.miniboard.model.dto.CommentDto;

public interface CommentService {
    public void insertComment(CommentDto commentDto);
    public void updateComment(CommentDto commentDto);
    public void deleteComment(int id);
}
