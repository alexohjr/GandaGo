package com.ezenit.gandago.order.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ezenit.gandago.common.CommonInfo;
import com.ezenit.gandago.order.models.BasketDTO;
import com.ezenit.gandago.order.models.OrderDTO;
import com.ezenit.gandago.order.models.OrderListDTO;

public class OrderDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 싱글톤 패턴 시작
	private static OrderDAO instance;
	
	public static OrderDAO getInstance() {
		if(instance == null) {
			instance = new OrderDAO();
		}
		return instance;
	}
	
	private OrderDAO() {
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
	
	// 장바구니 리스트 조회
	public List<BasketDTO> selectAllBasket() {
		String sql = "select b.userId, b.strNum, b.menuNum, b.menuAmount, m.menuName, m.menuInfo, m.menuPrice, s.strName, s.strMinOrder "
				+ "from basket b, menu m, store s "
				+ "where b.userId = ? and b.strNum = s.strNum and b.menuNum = m.menuNum";
		List<BasketDTO> list = new ArrayList<BasketDTO>();
		conn = getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, CommonInfo.userId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int strNum = rs.getInt("strNum");
				String strName = rs.getString("strName");
				int strMinOrder = rs.getInt("strMinOrder");
				int menuNum = rs.getInt("menuNum");
				String menuName = rs.getString("menuName");
				String menuDetail = rs.getString("menuInfo");
				int menuAmount = rs.getInt("menuAmount");
				int menuPrice = rs.getInt("menuPrice");
				
				BasketDTO dto = new BasketDTO(strNum, strName, strMinOrder, menuNum, menuName, menuDetail, menuAmount, menuPrice);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	// 장바구니 확인하기
	public int basketCheck() {
		String sql = "select count(*) from basket where userId = ?";
		int result = 0;
		conn = getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, CommonInfo.userId);
			
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
	
	// 장바구니 메뉴 개수 플러스 마이너스
	public int updateBasket(int flag, int menuNum) {
		String sql = "";
	
		if(flag > 0) {
			sql = "update basket set menuAmount = menuAmount+1 where userid = ? and menuNum = ?";
		} else {
			sql = "update basket set menuAmount = menuAmount-1 where userid = ? and menuNum = ?";
		}
		
		int result = 0;
		conn = getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, CommonInfo.userId);
			pstmt.setInt(2, menuNum);
		
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	// 장바구니 개별 삭제 비워주기
	public int deleteSelectedBasket(int menuNum) {
		String sql = "delete basket where userId = ? and menuNum = ?";
		int result = 0;
		conn = getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, CommonInfo.userId);
			pstmt.setInt(2, menuNum);
		
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	  
	// 장바구니 주문하기
	public int insertOrder(OrderDTO dto) {
		String sql = "insert into orders values (?, ?, ?, ?, ?, to_char(sysdate, 'YYYY-MM-DD HH24:MI'))";
		int result = 0;
		conn = getConnection();		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, CommonInfo.userId);
			pstmt.setInt(2, dto.getStrNum());
			pstmt.setInt(3, dto.getMenuNum());
			pstmt.setInt(4, dto.getMenuAmount());
			pstmt.setInt(5, dto.getOrderPrice());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	// 장바구니 비워주기
	public int deleteBasket() {
		String sql = "delete basket where userId = ?";
		int result = 0;
		conn = getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, CommonInfo.userId);
		
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	// 주문내역 확인하기
	public int orderCheck() {
		String sql = "select count(*) from orders where userId = ?";
		int result = 0;
		conn = getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, CommonInfo.userId);
			
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
	
	// 주문내역 리스트 조회
	public List<OrderListDTO> selectAllOrders() {
		String sql = "select o.menuAmount, o.orderPrice, o.orderDate, s.strName, s.strImage, m.menuName "
				+ "from orders o, store s, menu m "
				+ "where o.userId = ? and o.strNum = s.strNum and o.menuNum = m.menuNum "
				+ "order by o.orderdate desc";
		List<OrderListDTO> list = new ArrayList<OrderListDTO>();
		conn = getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, CommonInfo.userId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String strName = rs.getString("strName");
				String strImage = rs.getString("strImage");
				String menuName = rs.getString("menuName");
				int menuAmount = rs.getInt("menuAmount");
				int orderPrice = rs.getInt("orderPrice");
				String orderDate = rs.getString("orderDate");
				
				OrderListDTO dto = new OrderListDTO(strName, strImage, menuName, menuAmount, orderPrice, orderDate);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	// 장바구니 담기
	public int insertBasket(int strNum, int menuNum) {
		String sql = "insert into basket values (?, ?, ?, 1)";
		int result = 0;
		conn = getConnection();		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, CommonInfo.userId);
			pstmt.setInt(2, strNum);
			pstmt.setInt(3, menuNum);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
}
