package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Pay;
import bean.Product;

public class ProductDao {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public ProductDao() {
		con = JdbcUtil.getConnection();
	}

	public void close() {
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		JdbcUtil.close(con);
	}

	public boolean insertProduct(Product product) {
		String sql = "INSERT INTO PRODUCT VALUES (?||LPAD(PSEQ.NEXTVAL, 3, 0), ?, ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, product.getPkind());
			pstmt.setNString(2, product.getPname());
			pstmt.setInt(3, product.getPprice());
			pstmt.setNString(4, product.getPcontents());
			pstmt.setNString(5, product.getPorifilename());
			pstmt.setNString(6, product.getPsysfilename());

			int result = pstmt.executeUpdate();
			System.out.println(result);
			if (result != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<Product> getItemList(String kind) {
		String sql = "SELECT * FROM PRODUCT WHERE" + " PCODE LIKE '%' || ? || '%'";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, kind);
			rs = pstmt.executeQuery();
			List<Product> pList = new ArrayList<>();

			while (rs.next()) {
				Product p = new Product();
				p.setPcode(rs.getNString("PCODE"));
				p.setPname(rs.getNString("PNAME"));
				p.setPprice(rs.getInt("PPRICE"));
				p.setPcontents(rs.getNString("PCONTENTS"));
				p.setPsysfilename(rs.getNString("PSYSFILENAME"));

				pList.add(p);
			}
			return pList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Product AjaxDetail(String pcode) {
		String sql = "SELECT * FROM PRODUCT WHERE PCODE=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, pcode);
			rs = pstmt.executeQuery();
			Product p = null;

			if (rs.next()) {
				p = new Product();
				p.setPcode(rs.getNString("PCODE"));
				p.setPname(rs.getNString("PNAME"));
				p.setPprice(rs.getInt("PPRICE"));
				p.setPcontents(rs.getNString("PCONTENTS"));
			}
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Product> searchprice(String[] pcode) {
		List<Product> pList = new ArrayList<>();
		String sql = "SELECT PPRICE FROM PRODUCT WHERE PCODE=?";
		try {
			for (int i = 0; i < pcode.length; i++) {
				pstmt = con.prepareStatement(sql);
				pstmt.setNString(1, pcode[i]);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					Product p = new Product();
					p.setPprice(rs.getInt("PPRICE"));
					pList.add(p);

				}
			}
			return pList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
