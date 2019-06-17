package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Cart;
import service.CartMM;
import service.ProductMM;

@WebServlet({"/ajaxDetail", "/packincart"})
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String cmd = uri.substring(conPath.length());
		System.out.println("cmd=" + cmd);

		ProductMM pm = new ProductMM(request, response);
		CartMM cm = new CartMM(request, response);
		String jsonObj = null;

		switch (cmd) {
		case "/ajaxDetail":
			jsonObj = pm.AjaxDetail();
			break; 
		case "/packincart":
			jsonObj = cm.packCart();
			break;
		}

		if (jsonObj != null) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter(); // out은 응답객체로 생성
			out.write(jsonObj);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

}
