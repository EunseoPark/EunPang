package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.naming.spi.DirStateFactory.Result;

import bean.Cart;
import bean.Pay;
import bean.Product;

public class PayDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public PayDao() {
		con = JdbcUtil.getConnection();
	}

	public void close() {
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		JdbcUtil.close(con);
	}

	public boolean insertPay(Pay p, Product pr, String statedate) {
		String sql = "INSERT INTO PAY VALUES (LPAD(PPSEQ.NEXTVAL, 3, 0), ?, ?, ?, ?, ?, ?)";

		int result;
		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, p.getId());
			pstmt.setNString(2, p.getPcode());
			pstmt.setInt(3, p.getPqty());
			pstmt.setInt(4, pr.getPprice());
			pstmt.setNString(5, statedate);
			pstmt.setInt(6, p.getPqty() * pr.getPprice());

			result = pstmt.executeUpdate();

			System.out.println(result);
			if (result != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public Pay searchpaycode(String statement) {
		String sql = "SELECT PAYCODE FROM PAY WHERE PDATE=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, statement);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Pay p = new Pay();
				p.setPaycode(rs.getNString("PAYCODE"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Pay> selectInfo(Pay pay) {
		List<Pay> payList = null;
		String sql = "SELECT * FROM PAY WHERE ID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, pay.getId());
			rs = pstmt.executeQuery();
			payList = new ArrayList<>();

			while (rs.next()) {
				pay = new Pay();
				pay.setPaycode(rs.getNString("PAYCODE"));
				pay.setId(rs.getNString("ID"));
				pay.setPcode(rs.getNString("PCODE"));
				pay.setPqty(rs.getInt("PQTY"));
				pay.setPprice(rs.getInt("PPRICE"));
				pay.setPdate(rs.getNString("PDATE"));
				pay.setTotal(rs.getInt("TOTAL"));

				payList.add(pay);
			}
			return payList;
		} catch (SQLException e) {
			System.out.println("결제 리스트 구현 실패");
			e.printStackTrace();
		}

		return null;
	}

	public boolean insertPay(String[] pc, String id, String statement, List<Product> pList, Pay p, String[] w) {
		String sql = "INSERT INTO PAY VALUES (LPAD(PPSEQ.NEXTVAL, 3, 0), ?, ?, ?, ?, ?, ?)";
		int result = 0;
		try {
			for (int i = 0; i < pc.length; i++) {
				pstmt = con.prepareStatement(sql);
				pstmt.setNString(1, id);
				pstmt.setNString(2, pc[i]);
				pstmt.setInt(3, Integer.parseInt(w[i]));
				pstmt.setInt(4, pList.get(i).getPprice());
				pstmt.setNString(5, statement);
				pstmt.setInt(6, p.getPqty() * pList.get(i).getPprice());
				result = pstmt.executeUpdate();
			}
			if (result != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
