package com.korit.servlet_study.ch05;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Servlet {
    private String url;

    protected Servlet() {
    }

    public void doGet(Request req, Response resp) {

    }

    public void doPost(Request req, Response resp) {

    }

    public abstract void dopost(Request req, Response resp);
}
