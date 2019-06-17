package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Cart;
import bean.Forward;

import dao.CartDao;


public class CartMM {

	HttpServletRequest request;
	HttpServletResponse response;
	CartDao cDao;
	
	public CartMM(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public String packCart() {
		String pcode = request.getParameter("pcode"); // 피코드 가져오기
		
		HttpSession session = request.getSession();
		
		Cart cart = new Cart();
		cart.setId(session.getAttribute("id").toString());
		cart.setPcode(pcode);
		
		cDao = new CartDao();
		if (cDao.insertCart(cart)) {
			System.out.println("카트에 물건 넣기 성공");
		} else {
			System.out.println("카트에 물건 넣기 실패");
		}
		
		cDao.close();
		
		Forward fw = new Forward();
		fw.setPath("cart.jsp");
		fw.setRedirect(true);
		
		return pcode;
	}

	public Forward cartInfo() {
		cDao = new CartDao();
		Cart c = new Cart();
		HttpSession session = request.getSession();
		String id = session.getAttribute("id").toString(); // 세션 아이디
		c.setId(id);
		
		List<Cart> cList = cDao.cartInfo(c);
		cDao.close();
	
		Forward fw = new Forward();
		if (cList != null) {
			session.setAttribute("id", id);
			request.setAttribute("cList", makecListHtml(cList));
			
			fw.setPath("cart.jsp");
			fw.setRedirect(false);
		} else {
			fw.setPath("./");
			fw.setRedirect(true);
		}
		
		return fw;
	}

	private Object makecListHtml(List<Cart> cList) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table border=1>");
		sb.append("<tr align=center> <th> ID </th> <th> PRODUCT CODE </th> <th> QTY </th> </tr>");
		
		for (int i=0; i<cList.size(); i++) {
			sb.append("<tr align=center>");
			sb.append("<td>"+cList.get(i).getId()+"</td>");
			sb.append("<td> <input type='hidden' name='pcode' value='"+cList.get(i).getPcode()+"'>"+cList.get(i).getPcode()+"</td>");
			sb.append("<td> <input type='text' name='pqty' size=3 value="+cList.get(i).getQty()+"> </td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}


}
