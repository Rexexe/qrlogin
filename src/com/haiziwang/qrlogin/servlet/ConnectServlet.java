package com.haiziwang.qrlogin.servlet;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.haiziwang.qrlogin.context.keyContext;
import com.haiziwang.qrlogin.utils.DbUtil;

@WebServlet(urlPatterns = "/conn")
public class ConnectServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	/**
	 * 不一定要通过Interrupted方式来实现，也可以用回调来实现，订阅的时候传一个回调接口
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("openConn:"+req.getRequestURL());
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/event-stream");
		String k = req.getParameter("k");
		//注册订阅
		keyContext.registerSub(k);
		try {
			//1分钟重连一次
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			System.out.println("用户已扫描");
			//e.printStackTrace();
			//去数据库查询
			Map<String, Object> loginInfo = DbUtil.queryRecode(k);
			req.getSession().setAttribute("loginInfo", loginInfo);
			resp.getWriter().print("event:ready\n");
			resp.getWriter().print("data: end\n\n");
			resp.getWriter().flush();
		}finally{
			//释放订阅
			keyContext.releaseSub(k);
		}
		System.out.println("releaseConn:"+req.getRequestURL());
	}
}
