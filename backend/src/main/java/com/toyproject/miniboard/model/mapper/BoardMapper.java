package com.toyproject.miniboard.model.mapper;
import com.toyproject.miniboard.model.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    public List<BoardDto> boardList();
    public void insertBoard(BoardDto boardDto);
    public void updateBoard(BoardDto boardDto);
    public void deleteBoard(long id);
}
