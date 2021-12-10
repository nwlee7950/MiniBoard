package com.toyproject.miniboard.model.mapper;

import com.toyproject.miniboard.model.dto.CommentDto;
import com.toyproject.miniboard.model.vo.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    public List<Comment> commentList(long boardId);
    public void insertComment(Comment comment);
    public void updateComment(Comment comment);
    public void deleteComment(int id);
    public int getCommentGroupMax(long boardId);
    public Integer getParentGroup(Integer parentId);
    public int getSequence(long boardId, int commentGroup);
}
