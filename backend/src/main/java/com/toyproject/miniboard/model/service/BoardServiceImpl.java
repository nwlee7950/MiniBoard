package com.toyproject.miniboard.model.service;

import com.toyproject.miniboard.model.dto.BoardDto;
import com.toyproject.miniboard.model.dto.BoardParameterDto;
import com.toyproject.miniboard.model.mapper.BoardMapper;
import com.toyproject.miniboard.model.vo.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{
    private final BoardMapper boardMapper;

    @Override
    public List<Board> boardList(BoardParameterDto boardParameterDto) {

        return boardMapper.boardList(boardParameterDto);
    }

    @Override
    public void insertBoard(BoardDto boardDto) {
        Board board = Board.builder()
                .userId(boardDto.getUserId())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .isNotice(boardDto.isNotice())
                .build();

        boardMapper.insertBoard(board);
    }

    @Override
    public void updateBoard(BoardDto boardDto) {
        Board board = Board.builder()
                .id(boardDto.getId())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .isNotice(boardDto.isNotice())
                .build();

        boardMapper.updateBoard(board);
    }

    @Override
    public void deleteBoard(long id) {
        boardMapper.deleteBoard(id);
    }

    @Override
    public Board getBoard(long id) {
        boardMapper.updateHits(id);
        return boardMapper.getBoard(id);
    }
}
