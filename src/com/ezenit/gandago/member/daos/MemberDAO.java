package com.ezenit.gandago.member.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ezenit.gandago.member.models.MemberDTO;

public class MemberDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// ΩÃ±€≈Ê ∆–≈œ Ω√¿€
	private static MemberDAO instance;
	
	public static MemberDAO getInstance() {
		if(instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}
	
	private MemberDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("µÓ∑œ Ω«∆–");
			//e.printStackTrace();
		}
	}
	// ΩÃ±€≈Ê ∆–≈œ ≥°
	
	public Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "gandago";
		String password = "m1234";
		
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("¡¢º” Ω«∆–");
			//e.printStackTrace();
		}
		return conn;
	}
	
	public void close() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int loginCheck(String userId, String userPwd) {
		String sql = "select count(*) from member where userId = ? and userPwd = ?";
		int result = 0;
		conn = getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(); 
		}
		return result;
	}
	
	public int idCheck(String userId) {
		String sql = "select count(*) from member where userId = ?";
		int result = 0;
		conn = getConnection(); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	public int insertMember(MemberDTO dto) {
		String sql = "insert into member values (?, ?, ?, ?, ?, ?)";
		int result = 0;
		conn = getConnection();		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getUserPwd());
			pstmt.setString(3, dto.getUserName());
			pstmt.setString(4, dto.getUserTel());
			pstmt.setString(5, dto.getUserBtd());
			pstmt.setString(6, dto.getUserAddr());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	public String searchId(String userName, String userTel) {
		String sql = "select userId from member where userName = ? and userTel = ?";
		String userId = "";
		conn = getConnection(); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, userTel);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				userId = rs.getString("userId");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return userId;
	}
	
	public int searchPwd(String userId, String userName, String userTel) {
		String sql = "select count(*) from member where userId = ? and userName = ? and userTel = ?";
		int result = 0;
		conn = getConnection(); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userName);
			pstmt.setString(3, userTel);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	public int modifyPwd(String userId, String userPwd) {
		String sql = "update member set userPwd = ? where userId = ?";
		int result = 0;
		conn = getConnection(); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userPwd);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
}
