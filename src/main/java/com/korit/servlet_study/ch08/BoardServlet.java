package com.korit.servlet_study.ch08;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.dynalink.StandardNamespace;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/KT우승기원")
public class BoardServlet extends HttpServlet {
    private List<Board> boardList = new ArrayList<>();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        System.out.println("POST 잘갔노");

        BufferedReader bufferedReader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String json = "";
        while (true) {
            json = bufferedReader.readLine();
            if (json == null) {
                break;
            }
            stringBuilder.append(json);
        }
        json = stringBuilder.toString();

        System.out.println(stringBuilder);


        ObjectMapper objectMapper = new ObjectMapper();
        Board board = objectMapper.readValue(json, Board.class); //json 대신 req.getReader() ????????
        System.out.println(board);



        boardList.add(board);
        System.out.println(boardList);

        Response response = new Response();
        response.setMessage("게시글 작성 완료 OK");

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json"); //writeValue 하기 전에
        objectMapper.writeValue(resp.getWriter(), response);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(), boardList);
    }
}
