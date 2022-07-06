package com.ezenit.gandago.order.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.ezenit.gandago.common.CommonInfo;
import com.ezenit.gandago.common.CommonUtil;
import com.ezenit.gandago.order.daos.OrderDAO;
import com.ezenit.gandago.order.models.BasketDTO;
import com.ezenit.gandago.order.models.OrderDTO;
import com.ezenit.gandago.store.forms.StoreInfoForm;
import com.ezenit.gandago.store.forms.StoreListForm;
import com.ezenit.gandago.order.models.OrderDTO;

public class BasketListForm extends JFrame implements ActionListener {
	
	Container container = getContentPane();
	OrderDAO dao = OrderDAO.getInstance();
	
	/* 헤더 선언 시작 */
	JPanel headerPanel = new JPanel();
	JButton prevBtn = new JButton("<");
	JLabel titleLb = new JLabel("장바구니");
	JButton deleteAllBtn = new JButton("전체 삭제");
	/* 헤더 선언 끝 */
	
	/* 바디 선언 시작 */
	JPanel bodyAreaPanel = new JPanel();
	JPanel storeNamePanel = new JPanel();
	JLabel storeNameLb = new JLabel();
	JLabel storeMinOrderLb = new JLabel();
	
	JScrollPane scrollPane = new JScrollPane();
	JPanel menuAreaPanel = new JPanel();
	
	JPanel orderAreaPanel = new JPanel();
	JPanel orderPricePanel = new JPanel();
	JPanel orderBtnPanel = new JPanel();
	
	JLabel orderPriceLb = new JLabel("총 주문금액");
	JLabel orderPriceTotalLb = new JLabel();
	
	JButton orderBtn = new JButton("주문하기");
	
	/* 바디 선언 끝 */
	CommonUtil cu = new CommonUtil();
	List<BasketDTO> list = null;
	int totalAmount = 0;
	int totalPrice = 0;
	OrderDTO orderDTO = new OrderDTO();
	int listSize = 0;
	int minOrderPrice = 0;

	public BasketListForm() {
		setTitle("간다Go");
		setSize(500, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
		init();
		start();
	}
	
	private void init() {
		
		list = dao.selectAllBasket();

		orderDTO.setStrNum(list.get(0).getStrNum());
		orderDTO.setMenuNum(list.get(0).getMenuNum());
		minOrderPrice = list.get(0).getStrMinOrder();
		listSize = list.size();
		
		container.setLayout(new BorderLayout());
		container.add("North", headerPanel);
	
		headerPanel.setLayout(new GridLayout(1, 3));
		headerPanel.add(prevBtn);
		headerPanel.add(titleLb);
		titleLb.setHorizontalAlignment(JLabel.CENTER);
		headerPanel.add(deleteAllBtn);
		
		prevBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		deleteAllBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		prevBtn.setBorder(new EmptyBorder(0, 0, 10, 40));
		
		prevBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		titleLb.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		deleteAllBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		prevBtn.setBorderPainted(false);
		prevBtn.setFocusPainted(false);
		prevBtn.setContentAreaFilled(false);
		
		deleteAllBtn.setBorder(new EmptyBorder(0, 50, 0, 0));
		deleteAllBtn.setBorderPainted(false);
		deleteAllBtn.setFocusPainted(false);
		deleteAllBtn.setContentAreaFilled(false);
		
		container.add("Center", bodyAreaPanel);
		bodyAreaPanel.setPreferredSize(new Dimension(450, 800));
		bodyAreaPanel.setBackground(Color.white);
		
		bodyAreaPanel.setLayout(new FlowLayout());
		
		bodyAreaPanel.add(storeNamePanel);
		storeNamePanel.setBackground(Color.white);
		storeNamePanel.setPreferredSize(new Dimension(470, 70));
		storeNamePanel.setLayout(new BorderLayout());
		
		storeNamePanel.add("West", storeNameLb);
		storeNamePanel.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));
		storeNameLb.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		storeNameLb.setBorder(new EmptyBorder(20, 0, 0, 0));
		
		storeNameLb.setText(list.get(0).getStrName());
		storeNameLb.setHorizontalAlignment(JLabel.LEFT);
		
		storeNamePanel.add("East", storeMinOrderLb);
		String strMinOrder = "최소 주문 금액 " + cu.intToStringPrice(list.get(0).getStrMinOrder())+"원";
		storeMinOrderLb.setText(strMinOrder);
		storeMinOrderLb.setBorder(new EmptyBorder(20, 0, 0, 0));
		storeMinOrderLb.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		storeMinOrderLb.setHorizontalAlignment(JLabel.RIGHT);
		
		bodyAreaPanel.add(scrollPane);
		scrollPane.setViewportView(menuAreaPanel);
		menuAreaPanel.setBackground(Color.white);
		menuAreaPanel.setLayout(new FlowLayout());
		menuAreaPanel.setPreferredSize(new Dimension(470, 470));
		
		for(int i=0; i<list.size(); i++) {
			
			totalAmount += list.get(i).getMenuAmount();
			JPanel menuPanel = new JPanel();
			menuPanel.setBackground(Color.white);
			menuPanel.setPreferredSize(new Dimension(470, 80));
			menuPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
			menuAreaPanel.add(menuPanel);
			menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			JPanel menuInfoPanel = new JPanel();
			JLabel menuNameLb = new JLabel(list.get(i).getMenuName()+"("+list.get(i).getMenuDetail()+")");
			
			String price = cu.intToStringPrice(list.get(i).getMenuPrice());
			JLabel menuPriceLb = new JLabel(price+"원");
			
			
			
			menuPanel.add(menuInfoPanel);
			menuInfoPanel.setLayout(new GridLayout(2, 1));
			menuInfoPanel.add(menuNameLb);
			menuInfoPanel.add(menuPriceLb);
			menuInfoPanel.setPreferredSize(new Dimension(290, 60));
			menuInfoPanel.setBackground(Color.white);
			
			menuNameLb.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			menuPriceLb.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			
			JPanel menuBtnPanel = new JPanel();
			
			menuPanel.add(menuBtnPanel);
			
			menuBtnPanel.setLayout(new GridLayout(1,3));
			menuBtnPanel.setPreferredSize(new Dimension(120, 40));
			
			JButton minusBtn = new JButton(new ImageIcon("resources/images/order/minus.png"));
			JButton plusBtn = new JButton(new ImageIcon("resources/images/order/plus.png"));
			JLabel amountLb = new JLabel(list.get(i).getMenuAmount()+"개");
			amountLb.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			amountLb.setHorizontalAlignment(JLabel.CENTER);
			
			menuBtnPanel.add(minusBtn);
			menuBtnPanel.add(amountLb);
			menuBtnPanel.add(plusBtn);
			
			menuBtnPanel.setBackground(Color.white);
			minusBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			plusBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
			minusBtn.setBorderPainted(false);
			minusBtn.setFocusPainted(false);
			minusBtn.setContentAreaFilled(false);
			plusBtn.setBorderPainted(false);
			plusBtn.setFocusPainted(false);
			plusBtn.setContentAreaFilled(false);
			
			JLabel menuAmountHidden = new JLabel(list.get(i).getMenuAmount()+"");
			JLabel menuPriceHidden = new JLabel(list.get(i).getMenuPrice()+"");
			JLabel menuNumHidden = new JLabel(list.get(i).getMenuNum()+"");
			
			totalPrice += list.get(i).getMenuAmount() * list.get(i).getMenuPrice();
			
			if(amountLb.getText().equals("1개")) {
				minusBtn.setEnabled(false);
			} else {
				minusBtn.setEnabled(true);
			}
			
			
			plusBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int menuAmount = Integer.parseInt(menuAmountHidden.getText());
					menuAmount++;
					menuAmountHidden.setText(menuAmount+"");;
					amountLb.setText((menuAmount)+"개");
					
					int menuPrice = Integer.parseInt(menuPriceHidden.getText());
					
					totalPrice+= menuPrice;
					
					orderPriceTotalLb.setText(cu.intToStringPrice(totalPrice)+"원");
					
					totalAmount++;
					
					int menuNum = Integer.parseInt(menuNumHidden.getText());
					
					int result = dao.updateBasket(1, menuNum);
					
					if(menuAmount > 1) {
						minusBtn.setEnabled(true);
					} else {
						minusBtn.setEnabled(false);
					}
				}
			});
			
			minusBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int menuAmount = Integer.parseInt(menuAmountHidden.getText());
					menuAmount--;
					menuAmountHidden.setText(menuAmount+"");;
					amountLb.setText((menuAmount)+"개");
					
					int menuPrice = Integer.parseInt(menuPriceHidden.getText());
					
					totalPrice-= menuPrice;
					
					orderPriceTotalLb.setText(cu.intToStringPrice(totalPrice)+"원");
					
					totalAmount--;
					
					int menuNum = Integer.parseInt(menuNumHidden.getText());
					
					int result = dao.updateBasket(0, menuNum);
					
					if(menuAmount == 1) {
						minusBtn.setEnabled(false);
					} else {
						minusBtn.setEnabled(true);
					}
				}
			});
			
			JButton deleteBtn = new JButton(new ImageIcon("resources/images/order/trash.png"));
			menuPanel.add(deleteBtn);

			deleteBtn.setPreferredSize(new Dimension(35,60));
			deleteBtn.setBackground(new Color(224, 79, 95));
			
			deleteBtn.setBorderPainted(false);
			deleteBtn.setFocusPainted(false);
			deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
			deleteBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int menuNum = Integer.parseInt(menuNumHidden.getText());
					
					int result = dao.deleteSelectedBasket(menuNum);
					if(result > 0) {
						if(listSize == 1) {
							new StoreListForm();
							setVisible(false);
						} else {
							new BasketListForm();
							setVisible(false);
							
						}
					}
					
				}
			});
		}
		String totalPrice_str = cu.intToStringPrice(totalPrice);
		orderPriceTotalLb.setText(totalPrice_str + "원");

		
		container.add("South", orderAreaPanel);
		
		orderAreaPanel.setLayout(new FlowLayout());
		orderAreaPanel.setPreferredSize(new Dimension(470, 120));
		orderAreaPanel.setBackground(Color.gray);
		
		orderAreaPanel.add(orderPricePanel);
		orderPricePanel.setPreferredSize(new Dimension(470, 40));
		orderPricePanel.setBackground(Color.white);
		orderPricePanel.setLayout(new BorderLayout());
		orderPricePanel.add("West", orderPriceLb);
		orderPricePanel.add("East", orderPriceTotalLb);
		
		orderPriceLb.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		orderPriceTotalLb.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		orderPriceLb.setBorder(new EmptyBorder(0, 10, 0, 0));
		orderPriceTotalLb.setBorder(new EmptyBorder(0, 0, 0, 10));
		
		orderAreaPanel.add(orderBtnPanel);
		orderBtnPanel.setLayout(new GridLayout(1,1));
		orderBtnPanel.setPreferredSize(new Dimension(470, 60));
		orderBtnPanel.setBackground(Color.white);
		orderBtnPanel.add(orderBtn);
		orderBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		orderBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	private void start() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		orderBtn.addActionListener(this);
		deleteAllBtn.addActionListener(this);
		prevBtn.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == orderBtn) {
			if(totalPrice < minOrderPrice) {
				JOptionPane.showMessageDialog(this, "최소 주문 금액을 맞춰주시기 바랍니다.", "",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			orderDTO.setMenuAmount(totalAmount);
			orderDTO.setOrderPrice(totalPrice);
			
			int result = JOptionPane.showConfirmDialog(this, "주문하시겠습니까?",
					"주문확인", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null);
			
			if(result == 0) {
				int flag1 = dao.insertOrder(orderDTO);
				int flag2 = dao.deleteBasket();
				
				if(flag1 > 0 && flag2 > 0) {
					JOptionPane.showMessageDialog(this, "주문이 완료됐습니다.", "주문완료",
							JOptionPane.INFORMATION_MESSAGE);
					
					new StoreListForm();
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(this, "오류가 발생했습니다.", "오류",
							JOptionPane.ERROR_MESSAGE);	
				}
			} 
		}
		
		if(e.getSource() == deleteAllBtn) {
			int result = JOptionPane.showConfirmDialog(this, "전체 삭제하시겠습니까?",
					"전체삭제", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null);
			if(result == 0) {
				int flag = dao.deleteBasket();
				if(flag > 0) {
					JOptionPane.showMessageDialog(this, "장바구니가 전체 삭제되었습니다.", "삭제완료",
							JOptionPane.INFORMATION_MESSAGE);
					new StoreListForm();
					setVisible(false);
				}
			}
		}
		
		if(e.getSource() == prevBtn) {
			if(CommonInfo.fromFlag.equals("list")) {
				new StoreListForm();
				setVisible(false);
			} else if(CommonInfo.fromFlag.equals("info")) {
				new StoreInfoForm(CommonInfo.strNum);
				setVisible(false);
			}
			
		}
		
	}

}

