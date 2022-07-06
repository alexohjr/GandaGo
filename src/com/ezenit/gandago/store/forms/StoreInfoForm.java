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
	
/* ��� ���� ���� */
	ImageIcon headerImageIcon = new ImageIcon("resources/images/common/headerLogo.JPG");
	Image headerImg = headerImageIcon.getImage();
	Image headerImgCopied = headerImg.getScaledInstance(200, 50, Image.SCALE_SMOOTH);
	ImageIcon changeIcon = new ImageIcon(headerImgCopied);

	JButton headerLogoBtn = new JButton(changeIcon);
	JButton headerUserIdBtn = new JButton("�ֹ�����");
	JButton headerBasketBtn = new JButton(new ImageIcon("resources/images/common/basketImg.png"));
	JPanel headerAreaPanel = new JPanel();
	JPanel headerRightPanel = new JPanel();
/* ��� ���� �� */
	
	// �г�
	JPanel panel_main = new JPanel(); // ��ü�г�
	JPanel pl_menu = new JPanel(); // �޴��г�
	
	//DB
	StoreDTO store = null;
	List<MenuDTO> menus = null;
	List<BasketDTO> basketList = null;
	
	int strNum;
	

	public StoreInfoForm(int strNum) {
		
		this.strNum = strNum;
		// Jframe �⺻����
		setTitle("����Go");
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

	// ȭ�鱸��
	private void init() {
		container.setLayout(new BorderLayout());
		container.setBackground(Color.white);
		container.add("North", headerAreaPanel);
		container.add("Center", panel_main);

		/* ��� ���� ���� */
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
		
		headerUserIdBtn.setFont(new Font("���� ���", Font.BOLD, 15));
		headerUserIdBtn.setBorderPainted(false);
		headerUserIdBtn.setFocusPainted(false);
		headerUserIdBtn.setContentAreaFilled(false);
		headerUserIdBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		headerBasketBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		headerBasketBtn.setBorderPainted(false);
		headerBasketBtn.setFocusPainted(false);
		headerBasketBtn.setContentAreaFilled(false);
		/* ��� ���� �� */

		// �гλ���
		panel_main.setBackground(Color.white); // ��ü�гλ���

	/* ���Ի�: ����, ����, �޴� */
		// ���Ի���
		ImageIcon storepic = new ImageIcon(this.store.getStrBigImage());
		Image storepicImg = storepic.getImage();
		Image storepicUpdate = storepicImg.getScaledInstance(600, 220, Image.SCALE_SMOOTH);
		ImageIcon storepicUpdateUpdateIcon = new ImageIcon(storepicUpdate);
		JButton bt_storepic = new JButton(storepicUpdateUpdateIcon);
		
		
		// �������� : ��ȣ��, ��ġ, ��ȣ
		JButton bt_store = new JButton("    "+this.store.getStrName()+"    ");
		JButton bt_place = new JButton(this.store.getStrAddr());
		JButton bt_phone = new JButton("  "+this.store.getStrPhone()+"  ");
	/* ���Ի� �� */  
		
		// ��ü�г� �κк� ��ġ����
		panel_main.setLayout(new BorderLayout());
		panel_main.add("North", bt_storepic); 	// �����г�
		panel_main.add("West", bt_store);		// ��ȣ���ư
		panel_main.add("Center", bt_place);	 	// ��ҹ�ư
		panel_main.add("East", bt_phone); 		// ��ȭ��ȣ��ư
		panel_main.add("South", pl_menu); 		// �޴��г�

		// �޴��г� ũŰ����
		pl_menu.setPreferredSize(new Dimension(0, 400));

		// ��ư �׵θ����ֱ�
			// �����׵θ�
		bt_storepic.setBorderPainted(false);	
		bt_storepic.setFocusPainted(false);
		bt_storepic.setContentAreaFilled(false);
			// ��ȣ���׵θ�
		bt_store.setFocusPainted(false);		
		bt_store.setContentAreaFilled(false);
			// ����׵θ�
		bt_place.setFocusPainted(false);		
		bt_place.setContentAreaFilled(false);
			// ��ȭ��ȣ�׵θ�
		bt_phone.setFocusPainted(false);		
		bt_phone.setContentAreaFilled(false);

		// ��ư ��Ʈ, ��ġ����
			// ��ȣ
		bt_store.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), " ��ȣ�� ", TitledBorder.CENTER, 0));		
		bt_store.setFont(new Font("���� ���", Font.BOLD, 12));
			// ���
		bt_place.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), " ������ġ ", TitledBorder.CENTER, 0));	
		bt_place.setFont(new Font("���� ���", Font.BOLD, 12));
			// ��ȭ��ȣ
		bt_phone.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), " ��ȭ��ȣ ", TitledBorder.CENTER, 0));	
		bt_phone.setFont(new Font("���� ���", Font.BOLD, 12));

		// �޴��г� 5��1������
		pl_menu.setLayout(new GridLayout(5, 1, 2, 2));
		
		
		for (int i = 0; i < this.menus.size(); i++) {
			// �� �޴��гμ���
			JPanel obj = new JPanel();
			pl_menu.add(obj);

			
//			// �г� ����
//			JPanel menu_panel = new JPanel();
//			JPanel menu_panelWest = new JPanel();
	
			
			//test
			//StoreDAO dao = new StoreDAO();
			
			// �̹���->��ư���κ�ȯ
				// ���ĸ޴�������ư
			ImageIcon img_store = new ImageIcon(this.menus.get(i).getMenuImage());
			Image storeImg = img_store.getImage();
			Image storeUpdate = storeImg.getScaledInstance(100, 60, Image.SCALE_SMOOTH);
			ImageIcon storeUpdateIcon = new ImageIcon(storeUpdate);
			JButton store = new JButton(storeUpdateIcon);
				// ��ٱ����߰���ư
			ImageIcon img_plus = new ImageIcon("resources/images/store/bagplus.png");
			Image plusImg = img_plus.getImage();
			Image plusUpdate = plusImg.getScaledInstance(100, 30, Image.SCALE_SMOOTH);
			ImageIcon plusUpdateIcon = new ImageIcon(plusUpdate);
			JButton plus = new JButton(plusUpdateIcon);
		
			String menuName = this.menus.get(i).getMenuName();
			String menuInfo = this.menus.get(i).getMenuInfo();
			int menuPrice = this.menus.get(i).getMenuPrice();
			String menuPrice_str = cu.intToStringPrice(menuPrice)+"��";
			
			// �޴��󼼼���� ��
			JLabel lb_storeInfo = new JLabel("<html>"+menuName  +"<br>"+menuInfo+"<br>" +  menuPrice_str + "</html>", JLabel.LEFT);
//			JLabel lb_storeInfo = new JLabel("<html>�޴��̸�<br>�޴�����<br>�޴����� </html>", JLabel.LEFT);
			
			lb_storeInfo.setBackground(Color.pink);
			lb_storeInfo.setPreferredSize(new Dimension(200, 50));
			
			
			// �޴��гμ���
//			obj.setLayout(new FlowLayout(FlowLayout.LEFT));
//			obj.add(store); // ���ĸ޴����� : ��ư
//			obj.add(lb_storeInfo); // �޴��󼼼��� : ��
//			obj.add(plus); // ��ٱ��ϴ�� : ��ư
			
			obj.setLayout(new BorderLayout());
			obj.add("West",store); // ���ĸ޴����� : ��ư
			obj.add("Center",lb_storeInfo); // �޴��󼼼��� : ��
			obj.add("East",plus); // ��ٱ��ϴ�� : ��ư

			// �޴��г� ����
			obj.setBackground(Color.white);

			// ��ư, �󺧼���
			// ��ư: ���ĸ޴������׵θ�
			store.setBorderPainted(false);
			store.setFocusPainted(false);
			store.setContentAreaFilled(false);

			// ��ư: ��ٱ��ϴ�� �׵θ�
			plus.setCursor(new Cursor(Cursor.HAND_CURSOR));
			plus.setBorderPainted(false);
			plus.setFocusPainted(false);
			plus.setContentAreaFilled(false);
			plus.setToolTipText("<html><h5 style='color:blue; font=���� ���'>��ٱ��Ͽ� �߰�</h5></html>");
			
			int strNum = this.menus.get(i).getStrNum();
			int menuNum = this.menus.get(i).getMenuNum();
			
			plus.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					basketList = orderDao.selectAllBasket();
					
					// ��ٱ��Ͽ� ��� �޴��� ���� ���
					if(basketList.size() > 0) {
						int basketStrNum = basketList.get(0).getStrNum();
						// ��ٱ��Ͽ� ��� ����� ���� ������ ������ �ٸ����
						if(strNum != basketStrNum) {
							int result = showStoreDialog();
							if(result == 0) {
								showMenuDialog("�����Ͻ� �޴��� ��ٱ��Ͽ� ��ҽ��ϴ�");
								orderDao.deleteBasket();
								orderDao.insertBasket(strNum, menuNum);
								return;
							} 
							return;
						
						// ��ٱ��Ͽ� ��� ����� ���� ������ ������ �������
						} else {
							boolean flag = true;
							for(int i=0; i<basketList.size(); i++) {
								if(menuNum == basketList.get(i).getMenuNum()) {
									showMenuDialog("�̹� ��� �޴��Դϴ�.");
									flag = false;
									break;
								}
							}
							if(flag) {
								showMenuDialog("�����Ͻ� �޴��� ��ٱ��Ͽ� ��ҽ��ϴ�");
								orderDao.insertBasket(strNum, menuNum);
							}
						}
					
					// ��ٱ��Ͽ� ��� �޴��� ���� ���
					} else {
						showMenuDialog("�����Ͻ� �޴��� ��ٱ��Ͽ� ��ҽ��ϴ�");
						orderDao.insertBasket(strNum, menuNum);
					}
					
				}
			});
			
			// �� : ��Ʈ����
			lb_storeInfo.setFont(new Font("���� ���", Font.BOLD, 13));
		}

	}

	private void start() {
		// x��ư
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
				JOptionPane.showMessageDialog(this, "��ٱ��Ͽ� ��� �޴��� �����ϴ�.", "",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		} else if(e.getSource() == headerUserIdBtn) {
			int result = orderDao.orderCheck();
			if(result > 0) {
				new OrderListForm();
				setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "�ֹ������� �����ϴ�.", "",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
	}
	
	public int showStoreDialog() {
		int result = JOptionPane.showConfirmDialog(this, "��ٱ��Ͽ��� ���� ������ �޴��� ���� �� �ֽ��ϴ�.\n���� ��ٱ��ϸ� ���� ���ο� �޴��� �����ðڽ��ϱ�?",
				"��ٱ��� ���", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null);
		return result;
	}
	
	public void showMenuDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
