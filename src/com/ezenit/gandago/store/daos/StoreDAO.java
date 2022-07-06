package com.ezenit.gandago.store.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ezenit.gandago.store.models.MenuDTO;
import com.ezenit.gandago.store.models.StoreDTO;

public class StoreDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 싱글톤 패턴 시작
	private static StoreDAO instance;
	
	public static StoreDAO getInstance() {
		if(instance == null) {
			instance = new StoreDAO();
		}
		return instance;
	}
	
	private StoreDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("등록 실패");
			//e.printStackTrace();
		}
	}
	// 싱글톤 패턴 끝
	
	public Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "gandago";
		String password = "m1234";
		
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("접속 실패");
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

	// 매장 리스트 조회
	public StoreDTO getStore(int strNum) {
		String sql = "select * from store where strNum = ?";
		conn = getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "" + strNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String strName = rs.getString("strName");
				String strGroup = rs.getString("strGroup");
				String strAddr = rs.getString("strAddr");
				int strMinOrder = rs.getInt("strMinOrder");
				String strTime = rs.getString("strTime");
				String strPhone = rs.getString("strTel");
				String strImage = rs.getString("strImage");
				String strBigImage = rs.getString("strBigImage");

				StoreDTO dto = new StoreDTO(rs.getInt("strNum"), strName, strGroup, strAddr, strMinOrder, strTime,
						strPhone, strImage, strBigImage);
				return dto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}

	// 매장 리스트 조회
	public List<StoreDTO> selectAllStore(String input_strGroup) {
		String sql = "select * from store where strgroup = ?";
		List<StoreDTO> list = new ArrayList<StoreDTO>();
		conn = getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, input_strGroup);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int strNum = rs.getInt("strNum");
				String strName = rs.getString("strName");
				String strGroup = rs.getString("strGroup");
				String strAddr = rs.getString("strAddr");
				int strMinOrder = rs.getInt("strMinOrder");
				String strTime = rs.getString("strTime");
				String strPhone = rs.getString("strTel");
				String strImage = rs.getString("strImage");
				String strBigImage = rs.getString("strBigImage");

				StoreDTO dto = new StoreDTO(strNum, strName, strGroup, strAddr, strMinOrder, strTime, strPhone,
						strImage, strBigImage);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// 메뉴 리스트 조회
	public List<MenuDTO> selectAllMenu(int strNum) {
		String sql = "select * from menu where strNum = ?";
		List<MenuDTO> list = new ArrayList<MenuDTO>();
		conn = getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "" + strNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int menuNum = rs.getInt("menuNum");
				String menuName = rs.getString("menuName");
				String menuInfo = rs.getString("menuInfo");
				int menuPrice = rs.getInt("menuPrice");
				String menuImage = rs.getString("menuImage");

				MenuDTO mdto = new MenuDTO(menuNum, menuName, menuInfo, menuPrice, menuImage, rs.getInt("strNum"));
				list.add(mdto);
			}
		} catch (SQLException e) {
			//System.out.println("메뉴부분 오류발생");
			e.printStackTrace();
		} finally {
			close();
		}
		return list;

	}

}
