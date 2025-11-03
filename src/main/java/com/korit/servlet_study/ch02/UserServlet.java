package com.korit.servlet_study.ch02;

import lombok.AllArgsConstructor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

@WebServlet("/ch02/users")
public class UserServlet extends HttpServlet {


    private List<User> users;

    @Override
    public void init() throws ServletException {
        users = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // username == "test"
        // 찾으면 User객체 응답(200), 못 찾으면 해당 username은 존재하지 않습니다.(404)
        /*  req.getMethod();
        boolean isUsername = false;
        if (!req.getParameter("username").equals("test")) {
            System.out.println("username은 존재하지 않습니다.");
        }
        resp.setStatus(200);*/
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        List<User> foundUsers = users.stream().filter(user -> user.getUsername().equals(req.getParameter("username")))
                .toList();

        User foundUser = foundUsers.isEmpty() ? null : foundUsers.get(0);
        if (Objects.isNull(foundUser)) {
            resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println("해당 username은 존재하지 않습니다.");
            return;
        }

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.getWriter().println(foundUser);
        //System.out.println(isUsername);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        User user1 = new User();
        user1.setUsername("username");
        user1.setPassword("password");
        user1.setName("name");
        user1.setEmail("email");

        user1 = User.builder()
                .username(username)
                        .password(password)
                                .name(name)
                                        .email(email)
                                                .build();

        User user2 = new User();
        user2.setName(req.getParameter("name"));
        user2.setPassword(req.getParameter("password"));
        user2.setName(req.getParameter("name"));
        user2.setEmail(req.getParameter("email"));

        users.add(new User(req.getParameter("name"), req.getParameter("password"), req.getParameter("name")
                , req.getParameter("email")));



        users.add(user1);
        System.out.println(users);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("사용자 등록 완료");



        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println(users.add(user1));
        System.out.println(users);


        @AllArgsConstructor
        class ValidException extends RuntimeException {
            Map<String, String> error;
        }





    }

    private boolean isValid(String str) {
        if (str == null) return false;
        return !str.isBlank();
    }
}
