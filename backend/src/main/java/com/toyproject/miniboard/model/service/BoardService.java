package com.toyproject.miniboard.model.service;
import com.toyproject.miniboard.model.dto.BoardDto;
import com.toyproject.miniboard.model.dto.BoardParameterDto;
import com.toyproject.miniboard.model.vo.Board;

import java.util.List;

public interface BoardService {
    public List<Board> boardList(BoardParameterDto boardParameterDto);
    public void insertBoard(BoardDto boardDto);
    public void updateBoard(BoardDto boardDto);
    public void deleteBoard(long id);
    public Board getBoard(long id);
}
