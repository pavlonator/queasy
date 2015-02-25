package com.queasy;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendAnswer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String guid = req.getParameter("guid");
        Integer page = Integer.parseInt(req.getParameter("page"));
        String answer = req.getParameter("answer");
        JSONObject answerObj= (JSONObject) JSONValue.parse(answer);

        String ans = (String) answerObj.get("ans");
        if(answerObj.get("ans").equals("1")){
            resp.sendRedirect("page?guid="+guid+"&page="+(page+1));
        }
    }
}
