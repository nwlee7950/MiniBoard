package com.toyproject.miniboard.model.service;
import com.toyproject.miniboard.model.dto.BoardDto;
import java.util.List;

public interface BoardService {
    public List<BoardDto> boardList();
    public void insertBoard(BoardDto boardDto);
    public void updateBoard(BoardDto boardDto);
    public void deleteBoard(long id);
}
