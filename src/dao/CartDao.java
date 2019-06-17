package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.Set;

import bean.Cart;
import bean.Product;

public class CartDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public CartDao() {
		con = JdbcUtil.getConnection();
	}
	
	public void close() {
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		JdbcUtil.close(con);
	}

	public boolean insertCart(Cart cart) {
		String sql = "INSERT INTO CART VALUES (?, ?, DEFAULT)";
		int result;
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, cart.getId());
			pstmt.setNString(2, cart.getPcode());
			
			result = pstmt.executeUpdate();
			
			System.out.println(result);
			if (result!=0) {
				return true;
			}
		}
		catch (SQLException e) {
			System.out.println("장바구니 넣기 실패");
			e.printStackTrace();
		}
		return false;
	}

	public List<Cart> cartInfo(Cart c) {
		List<Cart> cList = null;
		String sql = "SELECT * FROM CART WHERE ID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, c.getId());
			rs = pstmt.executeQuery();
			cList = new ArrayList<Cart>();
			
			while (rs.next()) {
				c = new Cart();
				c.setId(rs.getNString("ID"));
				c.setPcode(rs.getNString("PCODE"));
				c.setQty(rs.getInt("PQTY"));
				
				cList.add(c);
			}
			return cList;
		} catch (SQLException e) {
			System.out.println("개인 장바구니 구현 실패");
			e.printStackTrace();
		}
		
		return null;
	}

	public Boolean DelCart(String id) {
		String sql = "DELETE FROM CART WHERE ID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			int result = pstmt.executeUpdate();
			
			if (result!=0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("장바구니 삭제 실패");
			e.printStackTrace();
		}
		return false;
	}

}
