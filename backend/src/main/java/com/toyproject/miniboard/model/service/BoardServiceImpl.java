package com.toyproject.miniboard.model.service;

import com.toyproject.miniboard.model.dto.BoardDto;
import com.toyproject.miniboard.model.mapper.BoardMapper;
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
    public List<BoardDto> boardList() {
        return null;
    }

    @Override
    public void insertBoard(BoardDto boardDto) {
        boardMapper.insertBoard(boardDto);
    }

    @Override
    public void updateBoard(BoardDto boardDto) {

    }

    @Override
    public void deleteBoard(long id) {
        boardMapper.deleteBoard(id);
    }
}
