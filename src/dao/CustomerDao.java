package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Customer;

// 회원관련 DB 서비스
public class CustomerDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public CustomerDao() {
		con = JdbcUtil.getConnection();
	}
	
	public void close() {
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
		JdbcUtil.close(con);
	}

	public Customer access(Customer cs) {
		String sql = "SELECT * FROM CUSTOMER WHERE ID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, cs.getId());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (rs.getNString("pw").equals(cs.getPw())) {
					cs.setId(rs.getNString("ID"));
					cs.setPw(rs.getNString("PW"));
					cs.setEmail(rs.getNString("EMAIL"));
					cs.setName(rs.getNString("NAME"));
					cs.setPhonenum(rs.getNString("PHONENUM"));
					cs.setAddress(rs.getNString("ADDRESS"));
					
					return cs;
				}
			}
		} catch (SQLException e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
		}
		return null;
	}


	public boolean insertInfo(Customer cs) {
		String sql = "INSERT INTO CUSTOMER VALUES(?, ?, ?, ?, ?, ?)";
		int result;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, cs.getId());
			pstmt.setNString(2, cs.getPw());
			pstmt.setNString(3, cs.getEmail());
			pstmt.setNString(4, cs.getName());
			pstmt.setNString(5, cs.getPhonenum());
			pstmt.setNString(6, cs.getAddress());
			
			result = pstmt.executeUpdate();
			
			if (result!=0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("회원가입 실패");
			e.printStackTrace();
		}
		
		return false;
	}
}
