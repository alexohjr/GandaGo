package com.ezenit.gandago.store.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.ezenit.gandago.common.CommonUtil;
import com.ezenit.gandago.common.CommonInfo;
import com.ezenit.gandago.order.daos.OrderDAO;
import com.ezenit.gandago.order.forms.BasketListForm;
import com.ezenit.gandago.order.forms.OrderListForm;
import com.ezenit.gandago.store.daos.StoreDAO;
import com.ezenit.gandago.store.models.StoreDTO;

public class StoreListForm extends JFrame implements ActionListener {
	Container container = getContentPane();
	StoreDAO dao = StoreDAO.getInstance();
	OrderDAO orderDao = OrderDAO.getInstance();
	CommonUtil cu = new CommonUtil();

	/* 헤더 선언 시작 */
	ImageIcon headerImageIcon = new ImageIcon("resources/images/common/headerLogo.JPG");
	Image headerImg = headerImageIcon.getImage();
	Image headerImgCopied = headerImg.getScaledInstance(200, 50, Image.SCALE_SMOOTH);
	ImageIcon changeIcon = new ImageIcon(headerImgCopied);

	JButton headerLogoBtn = new JButton(changeIcon);
	JButton headerUserIdBtn = new JButton("주문내역");
	//JLabel headerUserIdLb = new JLabel(LoginInfo.userId);
	JButton headerBasketBtn = new JButton(new ImageIcon("resources/images/common/basketImg.png"));
	JPanel headerAreaPanel = new JPanel();
	JPanel headerRightPanel = new JPanel();
	/* 헤더 선언 끝 */
	
	/* 탭패널 선언 */
	// 탭패널선언
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
	// 탭패널에 들어가는 이미지
	ImageIcon icon_wes = new ImageIcon("resources/images/store/category.png");
	ImageIcon icon_kor = new ImageIcon("resources/images/store/bibimbap.png");
	ImageIcon icon_chi = new ImageIcon("resources/images/store/dimsum.png");
	ImageIcon icon_jap = new ImageIcon("resources/images/store/salmon.png");
	// 각 탭패널 패널창
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	/* 탭패널 선언 끝 */

	// 버튼클릭이벤트 테스트용
	// dialog
	JDialog dialog = new JDialog(this);
	
	// 매장 리스트
	List<StoreDTO> list = null;
	List<StoreDTO> listChi = null;
	
	int selectIndex = 0;

	public StoreListForm() {
		setTitle("간다Go");
		setSize(500, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);

		init();
		start();
	}

	private void init() {
		
		container.setLayout(new BorderLayout());
		container.setBackground(Color.white);
		container.add("North", headerAreaPanel);
		container.add("Center", tabbedPane);
		
		

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

		/* 탭부분 설정 */
		tabbedPane.add(panel1, icon_kor);
		tabbedPane.add(panel2, icon_chi);
		tabbedPane.add(panel3, icon_wes);
		tabbedPane.add(panel4, icon_jap);
		// 첫번째 TabbedPane 설정
		tabbedPane.setTitleAt(0, "한식");
		// 두번째 TabbedPane 설정
		tabbedPane.setTitleAt(1, "중식");
		// 세번째 TabbedPane 설정
		tabbedPane.setTitleAt(2, "양식");
		// 네번째 TabbedPane 설정
		tabbedPane.setTitleAt(3, "일식");
		// 패널 색상
		tabbedPane.setBackground(Color.white);
		panel1.setBackground(Color.gray);
		panel2.setBackground(Color.gray);
		panel3.setBackground(Color.gray);
		panel4.setBackground(Color.gray);
		/* 탭부분 설정 끝 */
		
		// 탭패널에서 각 패널
		panel1.setLayout(new GridLayout(5, 1, 1, 1));
		panel2.setLayout(new GridLayout(5, 1, 1, 1));
		panel3.setLayout(new GridLayout(5, 1, 1, 1));
		panel4.setLayout(new GridLayout(5, 1, 1, 1));
		
		if(CommonInfo.tabIndex == 0) {
			list = dao.selectAllStore("kor");
			makeTab(list);
			tabbedPane.setSelectedIndex(0);
		} else if(CommonInfo.tabIndex == 1) {
			list = dao.selectAllStore("chi");
			makeTab(list);
			tabbedPane.setSelectedIndex(1);
		} else if(CommonInfo.tabIndex == 2) {
			list = dao.selectAllStore("wes");
			makeTab(list);
			tabbedPane.setSelectedIndex(2);
		} else if(CommonInfo.tabIndex == 3) {
			list = dao.selectAllStore("jap");
			makeTab(list);
			tabbedPane.setSelectedIndex(3);
		}
		
	}
	
	private void makeTab(List<StoreDTO> list) {
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 5; i++) {
				
				// 패널생성
				JPanel obj = new JPanel();

				switch (j) {
				case 0:
					panel1.add(obj);
					break;
				case 1:
					panel2.add(obj);
					break;
				case 2:
					panel3.add(obj);
					break;
				case 3:
					panel4.add(obj);
					break;
				}

				// 이미지->버튼으로변환
				// 가게사진버튼
				ImageIcon img_store = new ImageIcon(list.get(i).getStrImage());
				Image storeImg = img_store.getImage();
				Image storeUpdate = storeImg.getScaledInstance(100, 95, Image.SCALE_SMOOTH);
				ImageIcon storeUpdateIcon = new ImageIcon(storeUpdate);
				JButton store = new JButton(storeUpdateIcon);
				// 가게로이동버튼
				ImageIcon img_gostore = new ImageIcon("resources/images/store/goStore.jpg");
				Image gostoreImg = img_gostore.getImage();
				Image gostoreUpdate = gostoreImg.getScaledInstance(130, 80, Image.SCALE_SMOOTH);
				ImageIcon gostoreUpdateIcon = new ImageIcon(gostoreUpdate);
				JButton gostore = new JButton(gostoreUpdateIcon);

				JLabel hiddenData = new JLabel(list.get(i).getStrNum()+"");

				gostore.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String result = hiddenData.getText();
						new StoreInfoForm(Integer.parseInt(result));
						setVisible(false);
					}
				});

				String strName = list.get(i).getStrName();
				String strTime = list.get(i).getStrTime();
				int strMinOrder = list.get(i).getStrMinOrder();
				String strMinOrder_str = cu.intToStringPrice(strMinOrder)+"원";
				
				// 메뉴상세설명용 라벨
				JLabel lb_Info = new JLabel("<html>"+strName+"<br>배달소요시간 : "+strTime+"<br>최소주문금액 : "+strMinOrder_str+" </html>", JLabel.LEFT);

				// 상점패널 설정
				obj.setLayout(new FlowLayout(FlowLayout.LEFT));
				obj.add("West", store);
				obj.add("Center", lb_Info);
				obj.add("East", gostore);

				// 패널색상설정
				obj.setBackground(Color.white);

				// 버튼, 라벨설정
				// 버튼: 가게사진테두리
				store.setBorderPainted(false);
				store.setFocusPainted(false);
				store.setContentAreaFilled(false);
				// 버튼: 가게로이동테두리
				gostore.setCursor(new Cursor(Cursor.HAND_CURSOR));
				gostore.setBorderPainted(false);
				gostore.setFocusPainted(false);
				gostore.setContentAreaFilled(false);

				// 라벨 : 폰트설정
				lb_Info.setFont(new Font("맑은 고딕", Font.BOLD, 14));
			}
		}
	}

	private void start() {
		// x버튼
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// button 이벤트 설정
		headerBasketBtn.addActionListener(this);
		headerUserIdBtn.addActionListener(this);
			
		// 탭 이벤트 등록
		tabbedPane.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	        	int index = tabbedPane.getSelectedIndex();
	        	if(index == 0) {
	        		list = dao.selectAllStore("kor");
	        		panel1.removeAll();
	        		makeTab(list);
	        		CommonInfo.tabIndex = 0;
	        	} else if(index ==1) {
	        		list = dao.selectAllStore("chi");
	        		panel2.removeAll();
	        		makeTab(list);
	        		CommonInfo.tabIndex = 1;
	        	} else if(index ==2) {
	        		list = dao.selectAllStore("wes");
	        		panel3.removeAll();
	        		makeTab(list);
	        		CommonInfo.tabIndex = 2;
	        	} else if(index ==3) {
	        		list = dao.selectAllStore("jap");
	        		panel4.removeAll();
	        		makeTab(list);
	        		CommonInfo.tabIndex = 3;
	        	}
	        }
	    });
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == headerBasketBtn) {
			int result = orderDao.basketCheck();
			if(result > 0) {
				CommonInfo.fromFlag = "list";
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

}
