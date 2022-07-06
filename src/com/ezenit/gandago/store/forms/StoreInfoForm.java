package com.ezenit.gandago.store.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.ezenit.gandago.common.CommonUtil;
import com.ezenit.gandago.common.CommonInfo;
import com.ezenit.gandago.order.daos.OrderDAO;
import com.ezenit.gandago.order.forms.BasketListForm;
import com.ezenit.gandago.order.forms.OrderListForm;
import com.ezenit.gandago.order.models.BasketDTO;
import com.ezenit.gandago.store.daos.StoreDAO;
import com.ezenit.gandago.store.models.MenuDTO;
import com.ezenit.gandago.store.models.StoreDTO;

public class StoreInfoForm extends JFrame implements ActionListener {
	Container container = getContentPane();
	StoreDAO storeDao = StoreDAO.getInstance();
	OrderDAO orderDao = OrderDAO.getInstance();
	CommonUtil cu = new CommonUtil();
	
/* 헤더 선언 시작 */
	ImageIcon headerImageIcon = new ImageIcon("resources/images/common/headerLogo.JPG");
	Image headerImg = headerImageIcon.getImage();
	Image headerImgCopied = headerImg.getScaledInstance(200, 50, Image.SCALE_SMOOTH);
	ImageIcon changeIcon = new ImageIcon(headerImgCopied);

	JButton headerLogoBtn = new JButton(changeIcon);
	JButton headerUserIdBtn = new JButton("주문내역");
	JButton headerBasketBtn = new JButton(new ImageIcon("resources/images/common/basketImg.png"));
	JPanel headerAreaPanel = new JPanel();
	JPanel headerRightPanel = new JPanel();
/* 헤더 선언 끝 */
	
	// 패널
	JPanel panel_main = new JPanel(); // 전체패널
	JPanel pl_menu = new JPanel(); // 메뉴패널
	
	//DB
	StoreDTO store = null;
	List<MenuDTO> menus = null;
	List<BasketDTO> basketList = null;
	
	int strNum;
	

	public StoreInfoForm(int strNum) {
		
		this.strNum = strNum;
		// Jframe 기본설정
		setTitle("간다Go");
		setSize(500, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		this.store = storeDao.getStore(strNum);
		this.menus = storeDao.selectAllMenu(strNum);
		this.basketList = orderDao.selectAllBasket();
		
		init();
		start();
	}

	// 화면구성
	private void init() {
		container.setLayout(new BorderLayout());
		container.setBackground(Color.white);
		container.add("North", headerAreaPanel);
		container.add("Center", panel_main);

		/* 헤더 구성 시작 */
		headerAreaPanel.setBackground(Color.white);

		headerAreaPanel.setLayout(new BorderLayout());
		headerAreaPanel.add("West", headerLogoBtn);
		headerAreaPanel.add("East", headerRightPanel);

		headerLogoBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		headerLogoBtn.setBorderPainted(false);
		headerLogoBtn.setFocusPainted(false);
		headerLogoBtn.setContentAreaFilled(false);

		headerRightPanel.setBackground(Color.white);
		headerRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		headerRightPanel.add(headerUserIdBtn);
		headerRightPanel.add(headerBasketBtn);
		
		headerUserIdBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		headerUserIdBtn.setBorderPainted(false);
		headerUserIdBtn.setFocusPainted(false);
		headerUserIdBtn.setContentAreaFilled(false);
		headerUserIdBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		headerBasketBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		headerBasketBtn.setBorderPainted(false);
		headerBasketBtn.setFocusPainted(false);
		headerBasketBtn.setContentAreaFilled(false);
		/* 헤더 구성 끝 */

		// 패널색상
		panel_main.setBackground(Color.white); // 전체패널색상

	/* 가게상세: 사진, 정보, 메뉴 */
		// 가게사진
		ImageIcon storepic = new ImageIcon(this.store.getStrBigImage());
		Image storepicImg = storepic.getImage();
		Image storepicUpdate = storepicImg.getScaledInstance(600, 220, Image.SCALE_SMOOTH);
		ImageIcon storepicUpdateUpdateIcon = new ImageIcon(storepicUpdate);
		JButton bt_storepic = new JButton(storepicUpdateUpdateIcon);
		
		
		// 가게정보 : 상호명, 위치, 번호
		JButton bt_store = new JButton("    "+this.store.getStrName()+"    ");
		JButton bt_place = new JButton(this.store.getStrAddr());
		JButton bt_phone = new JButton("  "+this.store.getStrPhone()+"  ");
	/* 가게상세 끝 */  
		
		// 전체패널 부분별 위치설정
		panel_main.setLayout(new BorderLayout());
		panel_main.add("North", bt_storepic); 	// 사진패널
		panel_main.add("West", bt_store);		// 상호명버튼
		panel_main.add("Center", bt_place);	 	// 장소버튼
		panel_main.add("East", bt_phone); 		// 전화번호버튼
		panel_main.add("South", pl_menu); 		// 메뉴패널

		// 메뉴패널 크키설정
		pl_menu.setPreferredSize(new Dimension(0, 400));

		// 버튼 테두리없애기
			// 사진테두리
		bt_storepic.setBorderPainted(false);	
		bt_storepic.setFocusPainted(false);
		bt_storepic.setContentAreaFilled(false);
			// 상호명테두리
		bt_store.setFocusPainted(false);		
		bt_store.setContentAreaFilled(false);
			// 장소테두리
		bt_place.setFocusPainted(false);		
		bt_place.setContentAreaFilled(false);
			// 전화번호테두리
		bt_phone.setFocusPainted(false);		
		bt_phone.setContentAreaFilled(false);

		// 버튼 폰트, 위치설정
			// 상호
		bt_store.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), " 상호명 ", TitledBorder.CENTER, 0));		
		bt_store.setFont(new Font("맑은 고딕", Font.BOLD, 12));
			// 장소
		bt_place.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), " 가게위치 ", TitledBorder.CENTER, 0));	
		bt_place.setFont(new Font("맑은 고딕", Font.BOLD, 12));
			// 전화번호
		bt_phone.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), " 전화번호 ", TitledBorder.CENTER, 0));	
		bt_phone.setFont(new Font("맑은 고딕", Font.BOLD, 12));

		// 메뉴패널 5행1열설정
		pl_menu.setLayout(new GridLayout(5, 1, 2, 2));
		
		
		for (int i = 0; i < this.menus.size(); i++) {
			// 각 메뉴패널선언
			JPanel obj = new JPanel();
			pl_menu.add(obj);

			
//			// 패널 선언
//			JPanel menu_panel = new JPanel();
//			JPanel menu_panelWest = new JPanel();
	
			
			//test
			//StoreDAO dao = new StoreDAO();
			
			// 이미지->버튼으로변환
				// 음식메뉴사진버튼
			ImageIcon img_store = new ImageIcon(this.menus.get(i).getMenuImage());
			Image storeImg = img_store.getImage();
			Image storeUpdate = storeImg.getScaledInstance(100, 60, Image.SCALE_SMOOTH);
			ImageIcon storeUpdateIcon = new ImageIcon(storeUpdate);
			JButton store = new JButton(storeUpdateIcon);
				// 장바구니추가버튼
			ImageIcon img_plus = new ImageIcon("resources/images/store/bagplus.png");
			Image plusImg = img_plus.getImage();
			Image plusUpdate = plusImg.getScaledInstance(100, 30, Image.SCALE_SMOOTH);
			ImageIcon plusUpdateIcon = new ImageIcon(plusUpdate);
			JButton plus = new JButton(plusUpdateIcon);
		
			String menuName = this.menus.get(i).getMenuName();
			String menuInfo = this.menus.get(i).getMenuInfo();
			int menuPrice = this.menus.get(i).getMenuPrice();
			String menuPrice_str = cu.intToStringPrice(menuPrice)+"원";
			
			// 메뉴상세설명용 라벨
			JLabel lb_storeInfo = new JLabel("<html>"+menuName  +"<br>"+menuInfo+"<br>" +  menuPrice_str + "</html>", JLabel.LEFT);
//			JLabel lb_storeInfo = new JLabel("<html>메뉴이름<br>메뉴설명<br>메뉴가격 </html>", JLabel.LEFT);
			
			lb_storeInfo.setBackground(Color.pink);
			lb_storeInfo.setPreferredSize(new Dimension(200, 50));
			
			
			// 메뉴패널설정
//			obj.setLayout(new FlowLayout(FlowLayout.LEFT));
//			obj.add(store); // 음식메뉴사진 : 버튼
//			obj.add(lb_storeInfo); // 메뉴상세설명 : 라벨
//			obj.add(plus); // 장바구니담기 : 버튼
			
			obj.setLayout(new BorderLayout());
			obj.add("West",store); // 음식메뉴사진 : 버튼
			obj.add("Center",lb_storeInfo); // 메뉴상세설명 : 라벨
			obj.add("East",plus); // 장바구니담기 : 버튼

			// 메뉴패널 색상
			obj.setBackground(Color.white);

			// 버튼, 라벨설정
			// 버튼: 음식메뉴사진테두리
			store.setBorderPainted(false);
			store.setFocusPainted(false);
			store.setContentAreaFilled(false);

			// 버튼: 장바구니담기 테두리
			plus.setCursor(new Cursor(Cursor.HAND_CURSOR));
			plus.setBorderPainted(false);
			plus.setFocusPainted(false);
			plus.setContentAreaFilled(false);
			plus.setToolTipText("<html><h5 style='color:blue; font=맑은 고딕'>장바구니에 추가</h5></html>");
			
			int strNum = this.menus.get(i).getStrNum();
			int menuNum = this.menus.get(i).getMenuNum();
			
			plus.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					basketList = orderDao.selectAllBasket();
					
					// 장바구니에 담긴 메뉴가 있을 경우
					if(basketList.size() > 0) {
						int basketStrNum = basketList.get(0).getStrNum();
						// 장바구니에 담긴 매장과 새로 선택한 매장이 다를경우
						if(strNum != basketStrNum) {
							int result = showStoreDialog();
							if(result == 0) {
								showMenuDialog("선택하신 메뉴를 장바구니에 담았습니다");
								orderDao.deleteBasket();
								orderDao.insertBasket(strNum, menuNum);
								return;
							} 
							return;
						
						// 장바구니에 담긴 매장과 새로 선택한 매장이 같은경우
						} else {
							boolean flag = true;
							for(int i=0; i<basketList.size(); i++) {
								if(menuNum == basketList.get(i).getMenuNum()) {
									showMenuDialog("이미 담긴 메뉴입니다.");
									flag = false;
									break;
								}
							}
							if(flag) {
								showMenuDialog("선택하신 메뉴를 장바구니에 담았습니다");
								orderDao.insertBasket(strNum, menuNum);
							}
						}
					
					// 장바구니에 담긴 메뉴가 없을 경우
					} else {
						showMenuDialog("선택하신 메뉴를 장바구니에 담았습니다");
						orderDao.insertBasket(strNum, menuNum);
					}
					
				}
			});
			
			// 라벨 : 폰트설정
			lb_storeInfo.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		}

	}

	private void start() {
		// x버튼
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		headerLogoBtn.addActionListener(this);
		headerBasketBtn.addActionListener(this);
		headerUserIdBtn.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == headerLogoBtn) {
			new StoreListForm();
			setVisible(false);
		} else if(e.getSource() == headerBasketBtn) {
			int result = orderDao.basketCheck();
			if(result > 0) {
				CommonInfo.strNum = strNum;
				CommonInfo.fromFlag = "info";
				new BasketListForm();
				setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "장바구니에 담긴 메뉴가 없습니다.", "",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		} else if(e.getSource() == headerUserIdBtn) {
			int result = orderDao.orderCheck();
			if(result > 0) {
				new OrderListForm();
				setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "주문내역이 없습니다.", "",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
	}
	
	public int showStoreDialog() {
		int result = JOptionPane.showConfirmDialog(this, "장바구니에는 같은 가게의 메뉴만 담을 수 있습니다.\n기존 장바구니를 비우고 새로운 메뉴를 담으시겠습니까?",
				"장바구니 담기", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null);
		return result;
	}
	
	public void showMenuDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
