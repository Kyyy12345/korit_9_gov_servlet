package com.korit.servlet_study.ch07;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/ch07/boards")
public class BoardServlet extends HttpServlet {
    private BoardRepository boardRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        BoardRepository boardRepository = BoardRepository.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        /*BoardRepository boards = BoardRepository.getInstance();*/
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String writer = req.getParameter("writer");


        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setWriter(writer);






    }
}
