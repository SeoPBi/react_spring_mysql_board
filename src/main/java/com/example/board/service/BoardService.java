package com.example.board.service;

import com.example.board.Domain.Board;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> findBoards() {
        return boardRepository.findAll();
    }

    // id에 해당하는 board가 repository에 존재하지 않을 경우 NullPointerException 에러 핸들링
    // (** 서버 죽지 않게 하기 위함 **)
    public Board findOne(Long id){
        return boardRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    @Transactional
    public void create(Board board) {
        boardRepository.save(board);
    }

    @Transactional
    public void delete(Board board){
        boardRepository.delete(board);
    }
}
