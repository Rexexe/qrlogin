package com.haiziwang.qrlogin.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haiziwang.qrlogin.context.keyContext;
import com.haiziwang.qrlogin.utils.DbUtil;

@WebServlet(urlPatterns = "/wlogin")
public class WloginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String k = req.getParameter("k");
		String location = req.getParameter("location");
		String account = new URLDecoder().decode(req.getParameter("account"));
		String password = new URLDecoder().decode(req.getParameter("password"));
		DbUtil.addRecord(k, account, password,location);
		keyContext.pubToClient(k);
		resp.sendRedirect("loginsuccess.html");
	}
}
