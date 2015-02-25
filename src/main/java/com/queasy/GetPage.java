package com.queasy;

import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class GetPage extends HttpServlet {
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

        JSONObject obj=new JSONObject();

        Map<Integer, String> options = new LinkedHashMap<Integer, String>();
        options.put(1, "option one");
        options.put(2, "option <i>two</i>");
        options.put(3, "option {three}");

        //if(page==0) {
            obj.put("guid", guid);
            obj.put("caption", "Caption Page "+page);
            obj.put("hasNext", page <= 10);
            obj.put("hasPrev", page > 1);
            obj.put("page", page);
            obj.put("type", "test/radio");
            obj.put("options", options );
            obj.put("null", null);
        //}
        resp.setContentType("application/json");
        resp.getWriter().write(obj.toJSONString());
    }
}