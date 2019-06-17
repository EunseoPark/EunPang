package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Customer;
import bean.Forward;
import dao.CustomerDao;

public class CustomerMM {

	HttpServletRequest request;
	HttpServletResponse response;
	
	private CustomerDao cDao;
	
	public CustomerMM(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	public Forward insertInfo() {
		cDao = new CustomerDao();
		Customer cs = new Customer();
		cs.setId(request.getParameter("id"));
		cs.setPw(request.getParameter("pw"));
		cs.setEmail(request.getParameter("email"));
		cs.setName(request.getParameter("name"));
		cs.setPhonenum(request.getParameter("phonenum"));
		cs.setAddress(request.getParameter("address"));
		
		boolean result = cDao.insertInfo(cs);
		cDao.close();
			
		Forward fw = new Forward();
		if (result) {
			fw.setPath("index.jsp");
			fw.setRedirect(true);
		} else {
			fw.setPath("joinFrm.jsp");
			fw.setRedirect(false);
		}
		
		return fw;
	}

	public Forward access() {
		String id = request.getParameter("id"); // 로그인 화면에서 내가 입력했던 ID 가져오기
		String pw = request.getParameter("pw"); // 로그인 화면에서 내가 입력했던 PW 가져오기
		System.out.println("아이디는 "+id);
		System.out.println("비밀번호는 "+pw);
		
		cDao = new CustomerDao();
		Customer cs = new Customer(); 
		
		cs.setId(id);
		cs.setPw(pw);
		
		Customer result = cDao.access(cs);
		cDao.close();
		
		Forward fw = new Forward();
		if (result!=null) { // 로그인 성공
			HttpSession session = request.getSession();
			session.setAttribute("id", id); // getParameter로 받아와서 setAttribute로 뿌려주기
			request.setAttribute("msg", "<h3> LOGIN SUCCESS! </h3> <br>");
			request.setAttribute("customer", cs);
			
			fw.setPath("index.jsp");
			fw.setRedirect(false);
		} else {
			fw.setPath("index.jsp");
			fw.setRedirect(true);
		}
		return fw;
	}

	public Forward logout() {
		Forward fw = new Forward();
		HttpSession session = request.getSession();
		session.invalidate();
		
		fw.setPath("./");
		fw.setRedirect(true);
		return fw;
	}

}
