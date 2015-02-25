package com.queasy;

import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetMeta extends HttpServlet {
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

        JSONObject obj=new JSONObject();
        obj.put("guid", guid);
        obj.put("numPages", new Integer(10));
        obj.put("score", new Double(-1));
        obj.put("isComplete", new Boolean(false));
        obj.put("null", null);
        resp.setContentType("application/json");
        resp.getWriter().write(obj.toJSONString());
    }
}
