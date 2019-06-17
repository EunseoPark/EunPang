package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.SSLContext.SNICallBack;

import bean.Cart;
import bean.Forward;
import bean.Pay;
import bean.Product;
import dao.CartDao;
import dao.PayDao;
import dao.ProductDao;

public class PayMM {
	
	HttpServletRequest request;
	HttpServletResponse response;
	PayDao ppDao;
	
	public PayMM(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public Forward pay() {
		HttpSession session = request.getSession();
		String id = session.getAttribute("id").toString();
		
		ProductDao pDao = new ProductDao();
		String pcode = request.getParameter("pcode");
		String pc[]= request.getParameterValues("pcode");
		List<Product> pList=new ArrayList<>();
		pList= pDao.searchprice(pc);
		pDao.close();
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
		Date today = new Date();
		String statedate = date.format(today);
		
		ppDao = new PayDao();
		Pay pay = new Pay();
		pay.setId(id);
		pay.setPcode(pcode);
		pay.setPqty(Integer.valueOf(request.getParameter("pqty")));
		String[] i=request.getParameterValues("pqty");
		
		Forward fw = new Forward();
		if(ppDao.insertPay(pc, id, statedate, pList, pay, i)) {
			System.out.println("결제 페이지에 정보 넣기 성공");
			
			PayDao payDao = new PayDao();
			Pay p = payDao.searchpaycode(statedate);
			payDao.close();
			
			List<Pay> payList = ppDao.selectInfo(pay);

			session = request.getSession();
			session.setAttribute("id", id);
			request.setAttribute("pay", makeHtmlDelCart(id));
			request.setAttribute("payList", makeHtmlPay(payList));
			
			fw.setPath("payment.jsp");
			fw.setRedirect(false);
		} else {
			System.out.println("결제 페이지에 정보 넣기 실패");
			fw.setPath("cart");
			fw.setRedirect(true);
		}
		ppDao.close();
		
		return fw;
	}

	private Object makeHtmlPay(List<Pay> payList) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table border=1>");
		sb.append("<tr align=center> <th> PAYCODE </th> <th> ID </th> <th> PCODE </th> <th> PQTY </th> <th> PPRICE </th> <th> PDATE </th> <th> TOTAL </th> </tr>");
		
		for (int i=0; i<payList.size(); i++) {
			sb.append("<tr align=center>");
			sb.append("<td>"+payList.get(i).getPaycode()+"</td>");
			sb.append("<td>"+payList.get(i).getId()+"</td>");
			sb.append("<td>"+payList.get(i).getPcode()+"</td>");
			sb.append("<td>"+payList.get(i).getPqty()+"</td>");
			sb.append("<td>"+payList.get(i).getPprice()+"</td>");
			sb.append("<td>"+payList.get(i).getPdate()+"</td>");
			sb.append("<td>"+payList.get(i).getTotal()+"</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

	private Object makeHtmlDelCart(String id) {
		Cart c = new Cart();
		CartDao cDao = new CartDao();
		c.setId(id);
		boolean result = cDao.DelCart(id);
		
		if (result) {
			System.out.println("장바구니 삭제 성공");
		}
		return null;
	}

}
