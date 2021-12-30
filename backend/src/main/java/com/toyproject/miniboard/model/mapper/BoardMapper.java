package com.toyproject.miniboard.model.mapper;
import com.toyproject.miniboard.model.dto.BoardParameterDto;
import com.toyproject.miniboard.model.vo.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    public List<Board> boardList(BoardParameterDto boardParameterDto);
    public void insertBoard(Board board);
    public void updateBoard(Board board);
    public void deleteBoard(long id);
    public Board getBoard(long id);
    public void updateHits(long id);
}
