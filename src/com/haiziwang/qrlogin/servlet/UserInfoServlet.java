package com.haiziwang.qrlogin.servlet;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/ui")
public class UserInfoServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		Map<String, Object> loginInfo = (Map<String, Object>) req.getSession().getAttribute("loginInfo");
		if(loginInfo!=null){
			req.getRequestDispatcher("usercenter.jsp").forward(req, resp);
		}else{
			resp.getWriter().println("neeed login");
		}
		
	}
}
