package com.ezenit.gandago.order.forms;

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
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.ezenit.gandago.common.CommonUtil;
import com.ezenit.gandago.common.CommonInfo;
import com.ezenit.gandago.order.daos.OrderDAO;
import com.ezenit.gandago.order.models.OrderListDTO;
import com.ezenit.gandago.store.forms.StoreListForm;

public class OrderListForm extends JFrame implements ActionListener {
	
	Container container = getContentPane();
	OrderDAO dao = OrderDAO.getInstance();
	
	// 선언 
	
	/* 헤더 선언 시작*/
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
	
	JLabel basketCountLb = new JLabel("gd");
	
	/* 바디 선언 시작 */
	JPanel bodyAreaPanel = new JPanel();
	JPanel orderListTitlePn = new JPanel();
	JLabel orderListTitleLb = new JLabel(CommonInfo.userId + "님의 주문 내역");
	
	JScrollPane scrollPane = new JScrollPane();
	JPanel orderListAreaPanel = new JPanel();
	/* 바디 선언 끝 */
	
	List<OrderListDTO> list = null;
	CommonUtil cu = new CommonUtil();
	
	public OrderListForm() {
		setTitle("간다Go");
		setSize(500, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
		init();
		start();
	}
	
	private void init() {
		
		list = dao.selectAllOrders();
		
		container.setLayout(new BorderLayout());
		container.add("North", headerAreaPanel);
		
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
		
		/* 바디 구성 시작 */
		container.add("Center", bodyAreaPanel);
		bodyAreaPanel.setLayout(new BorderLayout());
		bodyAreaPanel.add("North", orderListTitlePn);
		
		orderListTitlePn.setBackground(Color.white);
		orderListTitlePn.setBorder(new MatteBorder(0, 0, 3, 0, Color.black));
		orderListTitlePn.add(orderListTitleLb);
		orderListTitleLb.setFont(new Font("SansSerif", Font.BOLD, 25));
		
		bodyAreaPanel.add("Center", scrollPane);
		
		scrollPane.setViewportView(orderListAreaPanel);
		int size = list.size();
		
		orderListAreaPanel.setPreferredSize(new Dimension(450, size*100 + 30));
		
		for(int i=0; i<list.size(); i++) {
			JPanel orderListPanel = new JPanel();
			orderListPanel.setPreferredSize(new Dimension(460, 100));
			orderListAreaPanel.add(orderListPanel);
			
			ImageIcon image = new ImageIcon(list.get(i).getStrImage());
			Image storeImg = image.getImage();
			Image storeUpdate = storeImg.getScaledInstance(100, 95, Image.SCALE_SMOOTH);
			ImageIcon storeUpdateIcon = new ImageIcon(storeUpdate);
			
			JLabel imageLabel = new JLabel();
			imageLabel.setIcon(storeUpdateIcon);
			
			orderListPanel.setLayout(new BorderLayout());
			orderListPanel.add("West", imageLabel);
			orderListPanel.setBackground(Color.white);
			orderListPanel.setBorder(new MatteBorder(0, 5, 0, 0, new Color(255, 224, 140)));
			
			JPanel labelPanel = new JPanel();
			labelPanel.setBackground(Color.green);
			
			
			labelPanel.setLayout(new GridLayout(2, 1, 3, 3));
			labelPanel.setBorder(new EmptyBorder(0, 15, 0, 0));
			
			JPanel namePanel = new JPanel();
			JPanel datePanel = new JPanel();
			
			JLabel nameLb = new JLabel(list.get(i).getStrName());
			
			String menuStr = "";
			int menuAmount = list.get(i).getMenuAmount();
			String orderPrice = cu.intToStringPrice(list.get(i).getOrderPrice());
			if(menuAmount == 1) {
				menuStr = list.get(i).getMenuName() + " - " + orderPrice + "원";
			} else {
				menuStr = list.get(i).getMenuName() + " 외 " + (menuAmount-1) + "개 - " + orderPrice + "원";
			}
			JLabel menuLb = new JLabel(menuStr);

			JLabel dateLb = new JLabel(list.get(i).getOrderDate());
			
			
			orderListPanel.add("Center", namePanel);
			orderListPanel.add("East", datePanel);
			
			namePanel.setLayout(new GridLayout(2, 1, 0, 0));
			namePanel.setBorder(new EmptyBorder(15, 15, 0, 0));

			
			namePanel.setBackground(Color.white);
			namePanel.add(nameLb);
			
			namePanel.add(menuLb);
			nameLb.setFont(new Font("SansSerif", Font.BOLD, 20));

			menuLb.setBorder(new EmptyBorder(0, 3, 25, 0));
			menuLb.setFont(new Font("SansSerif", Font.BOLD, 13));
			
			datePanel.setBackground(Color.white);
			datePanel.add(dateLb);
			dateLb.setBorder(new EmptyBorder(5, 0, 0, 20));
			dateLb.setFont(new Font("SansSerif", Font.BOLD, 13));
			

			dateLb.setBorder(new EmptyBorder(0, 0, 20, 0));
		}
				
		/* 바디 구성 끝 */
		
	}
	
	private void start() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		headerLogoBtn.addActionListener(this);
		headerBasketBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == headerLogoBtn) {
			new StoreListForm();
			setVisible(false);
		} else if(e.getSource() == headerBasketBtn) {
			int result = dao.basketCheck();
			if(result > 0) {
				new BasketListForm();
				setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "장바구니에 담긴 메뉴가 없습니다.", "",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
	}
	
}
