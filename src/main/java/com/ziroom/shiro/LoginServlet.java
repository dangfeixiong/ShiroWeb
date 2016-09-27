package com.ziroom.shiro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Servlet
 */
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	//doGet
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do Get");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	//doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			//获取session
			Session session = subject.getSession();
			System.out.println("sessionId" + session.getId());
			System.out.println("session Host" + session.getHost());
			System.out.println("session Timeout" + session.getTimeout());
			response.sendRedirect("success.jsp");
		} catch (AuthenticationException e) {
			System.out.println("登录失败");
			e.printStackTrace();
			request.setAttribute("errerInfo", "用户名或密码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
