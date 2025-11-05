package com.korit.servlet_study.ch08;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BoardRepository {
    private List<Board> boards;
    private static BoardRepository instance;

    private BoardRepository() {
        boards = new ArrayList<>();
    }


}
