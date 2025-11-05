package com.korit.servlet_study.ch03;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@WebServlet("/ch03/users")
public class UserServlet extends HttpServlet {
    private UserRepository userRepository;
    private ObjectMapper objectMapper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userRepository = UserRepository.getInstance();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("/application/json");
        List<User> users = userRepository.findAll();

        objectMapper.writeValue(resp.getWriter(), users);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
       /* BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        StringBuilder builder = new StringBuilder()
        while (true) {
            String line = bufferedReader.readLine();
            if(Objects.isNull(line)) {
                break;
            }
            builder.append(line);

        }
        UserDto userDto = objectMapper.readValue(builder.toString(), UserDto.class);*/

        UserDto userDto = objectMapper.readValue(req.getReader(), UserDto.class);
        System.out.println(userDto);

        User foundUser = userRepository.findByUsername(userDto.getUsername());


        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");

        if (!Objects.isNull(foundUser)) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(400)
                    .message("이미 존재하는 username입니다.")
                    .build();
            resp.setStatus(400); //http 프로토콜에서 보여주는 status

            objectMapper.writeValue(resp.getWriter(), errorResponse); // errorResponse를 json으로 변환하자마자 출력한다
            return;
        }
        User userEntity = userDto.toUser();
        userRepository.insert(userEntity);

        SuccessResponse<User> successResponse = SuccessResponse.<User>builder()
                .status(200)
                .message("사용자 등록을 완료하였습니다.")
                .body(userEntity)
                .build();

        objectMapper.writeValue(resp.getWriter(), successResponse);
        /*User user = new User();
        Long id = user.getId();
        String username = user.getUsername();
        String password = user.getPassword();
        String name = user.getName();
        String email = user.getEmail();

        user = User.builder()
                .id(id)
                .username(username)
                .password(password)
                .name(name)
                .email(email)
                .build();*/


    }


}
