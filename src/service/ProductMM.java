package service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bean.Forward;
import bean.Product;
import dao.ProductDao;

public class ProductMM {

	HttpServletRequest request;
	HttpServletResponse response;
	ProductDao pDao;

	public ProductMM(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public Forward insertProduct() {
		System.out.println(request.getParameter("pkind"));
		String uploadPath = request.getSession().getServletContext().getRealPath("upload"); // 물리적 주소
		System.out.println("path=" + uploadPath);

		File dir = new File(uploadPath);
		if (!dir.isDirectory()) {
			dir.mkdir();
		}

		int size = 10 * 1024 * 1024; // 10MB까지 업로드 가능
		Forward fw = null;

		try {
			MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "utf-8",
					new DefaultFileRenamePolicy());
			String kind = multi.getParameter("pkind");
			String name = multi.getParameter("pname");
			int price = Integer.valueOf(multi.getParameter("pprice"));
			String contents = multi.getParameter("pcontents");
			String oriFileName = multi.getOriginalFileName("pfile");
			String sysFileName = multi.getFilesystemName("pfile");

			HttpSession session = request.getSession();

			Product product = new Product();
			product.setPkind(kind);
			product.setPname(name);
			product.setPprice(price);
			product.setPcontents(contents);
			product.setPorifilename(oriFileName);
			product.setPsysfilename(sysFileName);

			pDao = new ProductDao();
			if (pDao.insertProduct(product)) {
				System.out.println("상품 등록 완료");
			} else {
				System.out.println("상품 등록 실패");
			}

			if (product.getPkind().equals("F")) {
				session.setAttribute("page", "food");
			} else if (product.getPkind().equals("N")) {
				session.setAttribute("page", "necessaries");
			} else {
				session.setAttribute("page", "appliances");
			}
			pDao.close();

			fw = new Forward();
			fw.setPath("index.jsp");
			fw.setRedirect(true);

		} catch (IOException e) {
			System.out.println("상품 업로드 실패");
			e.printStackTrace();
		}

		return fw;
	}

	public Forward getItemList(String kind) {
		// 리스트 보기
		Forward fw = new Forward();

		pDao = new ProductDao();
		List<Product> pList = null;
		pList = pDao.getItemList(kind);
		pDao.close();

		if (pList != null && pList.size() != 0) {
			String html = makeHtml_pList(pList);
			request.setAttribute("pList", html);
		}
		fw.setPath("main.jsp"); // 음식, 생필품, 가전제품 모두 출력
		fw.setRedirect(false);

		return fw;
	}

	private String makeHtml_pList(List<Product> pList) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < pList.size(); i++) {
			Product p = pList.get(i);
			sb.append("<div style= 'width: 15%; height: 20%; border: white; display: inline-flex;' onclick=\"detail('"
					+ p.getPcode() + "')\">");
			sb.append("<img src='upload/" + p.getPsysfilename() + "' width='100'>");
			sb.append("</div>");

		}
		return sb.toString();
	}

	public String AjaxDetail() {
		String pcode = request.getParameter("pcode");
		pDao = new ProductDao();
		Product p = null;
		p = pDao.AjaxDetail(pcode);
		pDao.close();
		String jsonObj = null;
		
		if (p!=null) {
			jsonObj = new Gson().toJson(p);
		}
		return jsonObj;
	}


}
