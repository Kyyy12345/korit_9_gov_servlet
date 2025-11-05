package com.korit.servlet_study.ch07;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Data
@Builder
public class BoardRepository {
    private static BoardRepository instance;
    private List<Board> boards;
    private int autoId;

    public BoardRepository() {
        boards = new ArrayList<>();
    }

    public static BoardRepository getInstance() {
        if (Objects.isNull(instance)) {
            return new BoardRepository();
        }
        return instance;
    }

    public void add(Board board) {
        if (Objects.isNull(board)) {
            System.out.println("비어있습니다.");
        }
        board.setId(autoId++);
        boards.add(board);
    }




}
