package com.haiziwang.qrlogin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haiziwang.qrlogin.context.keyContext;

@WebServlet(urlPatterns = "/monitor")
public class MonitorServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/event-stream");
		resp.getWriter().print("data:"+" "+keyContext.ksDetail()+"\n\n");
		resp.getWriter().flush();
		try {
			//其实也可以用注册订阅来时时监控都有谁扫了二维码以及扫码得到的信息，不过我觉得有点麻烦，你们自己实现啊。。。
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
