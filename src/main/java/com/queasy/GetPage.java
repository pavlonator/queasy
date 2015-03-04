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

        obj.put("guid", guid);
        obj.put("caption", "Caption Page "+page);
        obj.put("hasNext", page <= 10);
        obj.put("hasPrev", page > 1);
        obj.put("page", page);

        if(page==1) {
            obj.put("type", "learn/text");
            obj.put("content", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        } else if(page==2) {
            obj.put("type", "learn/html");
            obj.put("content", "<i>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</i>");
        } else if(page==3) {
            obj.put("type", "learn/youtube");
            obj.put("content", "hon8cwF33F0");
        } else if(page==4) {
            obj.put("type", "learn/soundcloud");
            obj.put("content", "191965777");
        } else if(page==20) {
            obj.put("type", "test/radio");
            obj.put("options", options );
        }
        resp.setContentType("application/json");
        resp.getWriter().write(obj.toJSONString());
    }
}