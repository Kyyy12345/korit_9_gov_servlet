package com.korit.servlet_study.ch09;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@WebServlet("/study/students")
public class StudentServlet extends HttpServlet {
    private StudentRepository studentRepository;
    private ObjectMapper om = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = new StudentRepository();
        config.getServletContext().setAttribute("sr", studentRepository);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
       /* BufferedReader bufferedReader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String str = "";

        while (true) {
            str = bufferedReader.readLine();
            if (str == null) {
                break;
            }
            stringBuilder.append(str);
        }*/



        Student student = om.readValue(req.getReader(), Student.class); // Json -> Java Instance / noargu로 생성
        studentRepository.insert(student);
        System.out.println(studentRepository);


        /*Response response = new Response("학생 정보 등록 완료");*/
        /*resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");*/
        om.writeValue(resp.getWriter(), Map.of("message" , "학생 추가 완료")); // Java Instance(Student) -> Response



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");*/

        String searchNameValue = req.getParameter("searchName");
        om.writeValue(resp.getWriter(), studentRepository.findAllBySearchNameValue(searchNameValue));




    }
}
