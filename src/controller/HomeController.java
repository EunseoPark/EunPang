package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Forward;
import service.CartMM;
import service.CustomerMM;
import service.PayMM;
import service.ProductMM;

@WebServlet({"/access", "/joinFrm", "/insertInfo", "/logout", "/food", "/ns", "/ap", "/insertProduct", "/proUpFrm", "/cart", "/pay"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String cmd = uri.substring(conPath.length());
		System.out.println("cmd=" + cmd);
		
		Forward fw = null;
		CustomerMM cs = new CustomerMM(request, response);
		ProductMM pm = new ProductMM(request, response);
		CartMM cm = new CartMM(request, response);
		PayMM pmm = new PayMM(request, response);
		
		switch(cmd) {
		case "/access":
			fw = cs.access();
			break;
		case "/joinFrm":
			fw = new Forward();
			fw.setPath("joinFrm.jsp");
			fw.setRedirect(false);
			break;
		case "/insertInfo":
			fw = cs.insertInfo();
			break;
		case "/logout":
			fw = cs.logout();
			break;
		case "/food":
			fw = pm.getItemList("F");
			break;
		case "/ns":
			fw = pm.getItemList("N");
			break;
		case "/ap":
			fw = pm.getItemList("A");
			break;
		case "/insertProduct":
			fw = pm.insertProduct();
			break;
		case "/proUpFrm":
			fw = new Forward();
			fw.setPath("product/proUpFrm.jsp");
			fw.setRedirect(false);
			break;
		case "/cart":
			 fw = cm.cartInfo();
			break;
		case "/pay":
			fw = pmm.pay();
			break;
		}
		
		if (fw != null) {
			response.setContentType("text/html; charset=UTF-8");
			if (fw.isRedirect()) { // true
				response.sendRedirect(fw.getPath());
			} else {
				request.getRequestDispatcher(fw.getPath()).forward(request, response);
			}
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
