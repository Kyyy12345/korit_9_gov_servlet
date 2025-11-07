package com.korit.servlet_study.ch09_practice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.ch09.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/결승/우승")
public class KTServlet extends HttpServlet {
    List<KT> kts = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());


        KT rolster = objectMapper.readValue(req.getReader(), KT.class);
        kts.add(rolster);
        System.out.println(kts);


        Response response = new Response("우승!");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");

        objectMapper.writeValue(resp.getWriter(), response);




    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");



        objectMapper.writeValue(resp.getWriter(), kts);
    }
}
