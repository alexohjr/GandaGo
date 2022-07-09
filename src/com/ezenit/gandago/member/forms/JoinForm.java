package com.ezenit.gandago.member.forms;

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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.ezenit.gandago.member.daos.MemberDAO;
import com.ezenit.gandago.member.models.MemberDTO;
import com.ezenit.gandago.order.forms.OrderListForm;

public class JoinForm extends JFrame implements ActionListener, KeyListener {
	
	Container container = getContentPane();
	MemberDAO dao = MemberDAO.getInstance();
	MemberDTO dto = new MemberDTO();
	   
	// ����
	JPanel totalAreaPanel = new JPanel();
	JPanel joinTitlePn = new JPanel();
	JLabel joinTitleLb = new JLabel("ȸ������");
	
	JPanel inputAreaPanel = new JPanel();
	JPanel lbPn = new JPanel();
	JPanel inputPn = new JPanel();
	JPanel msgPn = new JPanel();
	JPanel btdPn = new JPanel();

	JPanel btnPn = new JPanel();
	
	JLabel idLb = new JLabel("���̵�");
	JLabel pwdLb1 = new JLabel("��й�ȣ");
	JLabel pwdLb2 = new JLabel("��й�ȣ Ȯ��");
	JLabel nameLb = new JLabel("�̸�");
	JLabel telLb = new JLabel("����ó");
	JLabel addrLb = new JLabel("�ּ�");
	JLabel btdLb = new JLabel("�������");
	
	JLabel hiddenIdLb = new JLabel();
	JLabel hiddenPwdLb1 = new JLabel();
	JLabel hiddenPwdLb2 = new JLabel();
	JLabel hiddenNameLb = new JLabel();
	JLabel hiddenTelLb = new JLabel();
	JLabel hiddenAddrLb = new JLabel();
	
	JTextField idTf = new JTextField();
	JPasswordField pwdTf1 = new JPasswordField();
	JPasswordField pwdTf2 = new JPasswordField();
	JTextField nameTf = new JTextField();
	JTextField telTf = new JTextField();
	JTextField addrTf = new JTextField();
	JComboBox<Integer> yearCb = new JComboBox<Integer>();
	JComboBox<Integer> monthCb = new JComboBox<Integer>();
	JComboBox<Integer> dateCb = new JComboBox<Integer>();
	

	ImageIcon checkImg = new ImageIcon("resources/images/member/check.png");
	ImageIcon xImg = new ImageIcon("resources/images/member/delete.png");
	JLabel idVali = new JLabel();
	JLabel pwdVali1 = new JLabel();
	JLabel pwdVali2 = new JLabel();
	JLabel nameVali = new JLabel();
	JLabel telVali = new JLabel();
	JLabel addrVali = new JLabel();
	
	JPanel inputValiPn = new JPanel();
	JLabel inputValiLb = new JLabel("");
	
	JButton joinBtn = new JButton("ȸ������");
	JButton cancelBtn = new JButton("���");
	
	boolean idFlag, pwdFlag1, pwdFlag2, nameFlag, telFlag, addrFlag;
	
	public JoinForm() {
		setTitle("간다오고고");  
		setSize(500, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
		init();
		start();
	}
	
	private void init() {
		container.setLayout(new FlowLayout());
		container.add(totalAreaPanel);
		
		totalAreaPanel.setLayout(new FlowLayout());
		totalAreaPanel.setPreferredSize(new Dimension(500, 800));
		totalAreaPanel.setBackground(Color.white);
		totalAreaPanel.add(joinTitlePn);
		joinTitlePn.setLayout(new FlowLayout(FlowLayout.LEFT));
		joinTitlePn.setPreferredSize(new Dimension(500, 120));
		joinTitlePn.setBackground(Color.white);
		joinTitlePn.add(joinTitleLb);
		joinTitlePn.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		
		joinTitleLb.setBorder(new EmptyBorder(70, 15, 0 ,0));
		joinTitleLb.setFont(new Font("SansSerif", Font.BOLD, 30));
		
		totalAreaPanel.add(inputAreaPanel);
		inputAreaPanel.setBackground(Color.black);
		inputAreaPanel.setPreferredSize(new Dimension(500, 550));
		inputAreaPanel.setLayout(new BorderLayout());
		inputAreaPanel.add("West", lbPn);
		inputAreaPanel.add("Center", inputPn);
		inputAreaPanel.add("South", btnPn);
		inputAreaPanel.add("East", msgPn);
		inputAreaPanel.setBackground(Color.white);
		inputAreaPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
		
		lbPn.setBackground(Color.white);
		inputPn.setBackground(Color.white);
		lbPn.setBorder(new EmptyBorder(0, 20, 0 ,10));
		lbPn.setLayout(new GridLayout(7, 1, 0, 0));
		lbPn.add(idLb);
		lbPn.add(pwdLb1);
		lbPn.add(pwdLb2);
		lbPn.add(nameLb);
		lbPn.add(telLb);
		lbPn.add(addrLb);
		lbPn.add(btdLb);
		
		idLb.setFont(new Font("SansSerif", Font.BOLD, 15));
		pwdLb1.setFont(new Font("SansSerif", Font.BOLD, 15));
		pwdLb2.setFont(new Font("SansSerif", Font.BOLD, 15));
		nameLb.setFont(new Font("SansSerif", Font.BOLD, 15));
		telLb.setFont(new Font("SansSerif", Font.BOLD, 15));
		addrLb.setFont(new Font("SansSerif", Font.BOLD, 15));
		btdLb.setFont(new Font("SansSerif", Font.BOLD, 15));
		
		inputPn.setLayout(new GridLayout(7, 1, 0, 0));
		inputPn.add(idTf);
		inputPn.add(pwdTf1);
		inputPn.add(pwdTf2);
		inputPn.add(nameTf);
		inputPn.add(telTf);
		inputPn.add(addrTf);
		inputPn.add(btdPn);
		
		
		btdPn.setBackground(Color.white);
		btdPn.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		btdPn.setBorder(new EmptyBorder(0, -5, 0, 0));
		btdPn.add(yearCb);
		btdPn.add(monthCb);
		btdPn.add(dateCb);
		
		yearCb.setPreferredSize(new Dimension(100, 45));
		monthCb.setPreferredSize(new Dimension(80, 45));
		dateCb.setPreferredSize(new Dimension(80, 45));
		
		yearCb.setFont(new Font("SansSerif", Font.BOLD, 15));
		monthCb.setFont(new Font("SansSerif", Font.BOLD, 15));
		dateCb.setFont(new Font("SansSerif", Font.BOLD, 15));
		
		yearCb.setBackground(Color.white);
		monthCb.setBackground(Color.white);
		dateCb.setBackground(Color.white);
		
		
		for(int i=2022; i>=1960; i--) {
			yearCb.addItem(i);
		}
		
		for(int i=1; i<=12; i++) {
			monthCb.addItem(i);
		}
		
		for(int i=1; i<=31; i++) {
			dateCb.addItem(i);
		}
		
		msgPn.setBackground(Color.white);
		msgPn.setLayout(new GridLayout(7, 1, 0, 0));
		msgPn.setPreferredSize(new Dimension(70, 350));
		msgPn.setBorder(new EmptyBorder(0, 15, 0, 0));
		
		msgPn.add(idVali);
		msgPn.add(pwdVali1);
		msgPn.add(pwdVali2);
		msgPn.add(nameVali);
		msgPn.add(telVali);
		msgPn.add(addrVali);
		
		btnPn.setBackground(Color.white);
		btnPn.setBorder(new EmptyBorder(30, 0, 0, 0));
		btnPn.setPreferredSize(new Dimension(500, 150));
		btnPn.add(inputValiPn);
		inputValiPn.setPreferredSize(new Dimension(500, 30));
		inputValiPn.add(inputValiLb);
		btnPn.add(joinBtn);
		btnPn.add(cancelBtn);
		
		inputValiLb.setFont(new Font("SansSerif", Font.BOLD, 15));
		
		joinBtn.setPreferredSize(new Dimension(200, 50));
		cancelBtn.setPreferredSize(new Dimension(200, 50));
		
		joinBtn.setFont(new Font("SansSerif", Font.BOLD, 15));
		cancelBtn.setFont(new Font("SansSerif", Font.BOLD, 15));
		
		joinBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
	}
	
	private void start() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		joinBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		
		idTf.addKeyListener(this);
		pwdTf1.addKeyListener(this);
		pwdTf2.addKeyListener(this);
		nameTf.addKeyListener(this);
		telTf.addKeyListener(this);
		addrTf.addKeyListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == joinBtn) {
			validateTf();
			if(idFlag && pwdFlag1 && pwdFlag2 && nameFlag && telFlag && addrFlag ) {
				String userId = idTf.getText().trim();
				String userPwd = pwdTf2.getText().trim();
				String userName = nameTf.getText().trim();
				String userTel = telTf.getText().trim();
				String userAddr = addrTf.getText().trim();

				String userBtd = yearCb.getSelectedItem()+"-"+monthCb.getSelectedItem()+"-"+dateCb.getSelectedItem();
				MemberDTO dto = new MemberDTO(userId, userPwd, userName, userTel, userBtd, userAddr);
				
				JOptionPane.showMessageDialog(this, "ȸ�������� �Ϸ�ƽ��ϴ�.", "",
						JOptionPane.INFORMATION_MESSAGE);
				
				int result = dao.insertMember(dto);
				if(result > 0) {
					setVisible(false);
					new LoginForm();
				} else {
					showDialog("�����߻�");
				}
				
				
			}
		} else if(e.getSource() == cancelBtn) {
			setVisible(false);
			new LoginForm();
		}
	}
	
	public void validateTf() {
		String userId = idTf.getText().trim();
		String userPwd1 = pwdTf1.getText().trim();
		String userPwd2 = pwdTf2.getText().trim();
		String userName = nameTf.getText().trim();
		String userTel = telTf.getText().trim();
		String userAddr = addrTf.getText().trim();
		
		if(userId.equals("")) {
			idVali.setIcon(xImg);
			showDialog("���̵� �Է����ּ���.");
			idTf.requestFocus(true);
			idFlag = false;
			inputValiLb.setText("���̵� �Է����ּ���.");
			return;
		} else if(userId.length() < 4) {
			idVali.setIcon(xImg);
			showDialog("���̵�� 4���� �̻� �Է����ּ���.");
			idTf.requestFocus(true);
			idFlag = false;
			inputValiLb.setText("���̵�� 4���� �̻� �Է����ּ���.");
			return;
		} else {
			int result = dao.idCheck(userId);
			if(result > 0) {
				idVali.setIcon(xImg);
				showDialog("�ߺ��� ���̵��Դϴ�.");
				idTf.requestFocus(true);
				idFlag = false;
				inputValiLb.setText("�ߺ��� ���̵��Դϴ�.");
				return;
			} else {
				inputValiLb.setText("");
			}
		}
		
		if(userPwd1.equals("")) {
			pwdVali1.setIcon(xImg);
			showDialog("��й�ȣ�� �Է����ּ���.");
			inputValiLb.setText("��й�ȣ�� �Է����ּ���.");
			pwdTf1.requestFocus(true);
			pwdFlag1 = false;
			return;
		} else if(userPwd1.length() < 4) {
			pwdVali1.setIcon(xImg);
			showDialog("��й�ȣ�� 4���� �̻� �Է����ּ���.");
			inputValiLb.setText("��й�ȣ�� 4���� �̻� �Է����ּ���.");
			pwdTf1.requestFocus(true);
			pwdFlag1 = false;
			return;
		} else {
			inputValiLb.setText("");
		}
		
		if(userPwd2.equals("")) {
			pwdVali2.setIcon(xImg);
			showDialog("��й�ȣ Ȯ���� �Է����ּ���.");
			inputValiLb.setText("��й�ȣ Ȯ���� �Է����ּ���.");
			pwdTf2.requestFocus(true);
			pwdFlag2 = false;
			return;
		} else if(!userPwd2.equals(userPwd1)) {
			pwdVali2.setIcon(xImg);
			showDialog("��й�ȣ�� �����ϰ� �Է����ּ���.");
			inputValiLb.setText("��й�ȣ�� �����ϰ� �Է����ּ���.");
			pwdTf2.requestFocus(true);
			pwdFlag2 = false;
			return;
		} else {
			inputValiLb.setText("");
		}
		
		if(userName.equals("")) {
			nameVali.setIcon(xImg);
			showDialog("�̸��� �Է����ּ���.");
			inputValiLb.setText("�̸��� �Է����ּ���.");
			nameTf.requestFocus(true);
			nameFlag = false;
			return;
		} else if(userName.length() < 2) {
			nameVali.setIcon(xImg);
			showDialog("�̸��� ��Ȯ�� �Է����ּ���.");
			inputValiLb.setText("�̸��� ��Ȯ�� �Է����ּ���.");
			nameTf.requestFocus(true);
			nameFlag = false;
			return;
		} else {
			inputValiLb.setText("");
		}
		
		if(userTel.equals("")) {
			telVali.setIcon(xImg);
			showDialog("����ó�� �Է����ּ���.");
			inputValiLb.setText("����ó�� �Է����ּ���.");
			telTf.requestFocus(true);
			telFlag = false;
			return;
		} else if(userTel.length() < 11) {
			telVali.setIcon(xImg);
			showDialog("����ó�� ��Ȯ�� �Է����ּ���.");
			inputValiLb.setText("����ó�� ��Ȯ�� �Է����ּ���.");
			telTf.requestFocus(true);
			telFlag = false;
			return;
		} else {
			boolean flag = true;
			
			for(int i=0; i<userTel.length(); i++){
				char c = userTel.charAt(i);
				if(c<48 || c> 57) {	//���ڰ� �ƴ� ���
					flag = false;
					break;
				}
			}
			if(!flag) {
				telVali.setIcon(xImg);
				showDialog("����ó�� ���ڸ� �Է����ּ���.");
				inputValiLb.setText("����ó�� ���ڸ� �Է����ּ���.");
				telTf.requestFocus(true);
				telFlag = false;
				return;
			} else {
				inputValiLb.setText("");
			}
		} 
		
		if(userAddr.equals("")) {
			addrVali.setIcon(xImg);
			showDialog("�ּҸ� �Է����ּ���.");
			inputValiLb.setText("�ּҸ� �Է����ּ���.");
			addrTf.requestFocus(true);
			addrFlag = false;
			return;
		} else if(userAddr.length() < 5) {
			addrVali.setIcon(xImg);
			showDialog("�ּҸ� ��Ȯ�� �Է����ּ���.");
			inputValiLb.setText("�ּҸ� ��Ȯ�� �Է����ּ���.");
			addrTf.requestFocus(true);
			addrFlag = false;
			return;
		} else {
			inputValiLb.setText("");
		} 
	}
	
	public void labelSetText(JLabel label, String message) {
		label.setText(message);
	}
	
	public void showDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "����",
				JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource() == idTf) {
			String userId = idTf.getText().trim();
			int result = 0;
			if(userId.length() < 4) {
				idVali.setIcon(xImg);
				idFlag = false;
				inputValiLb.setText("���̵�� 4���� �̻� �Է����ּ���.");
			} else {
				result = dao.idCheck(userId);
				if(result > 0) {
					idVali.setIcon(xImg);
					idFlag = false;
					inputValiLb.setText("�ߺ��� ���̵��Դϴ�.");
				} else {
					idVali.setIcon(checkImg);
					idFlag = true;
					inputValiLb.setText("");
				}
			}
		}
		
		if(e.getSource() == pwdTf1) {
			String userPwd1 = pwdTf1.getText().trim();
			String userPwd2 = pwdTf2.getText().trim();
			if(userPwd1.length() < 4) {
				pwdVali1.setIcon(xImg);
				pwdFlag1 = false;
				pwdFlag2 = false;
				inputValiLb.setText("��й�ȣ�� 4���� �̻� �Է����ּ���.");
			}else if(userPwd2.length() >0 && !userPwd1.equals(userPwd2)) {
				pwdVali2.setIcon(xImg);
				pwdFlag1 = false;
				pwdFlag2 = false;
				inputValiLb.setText("��й�ȣ�� �����ϰ� �Է����ּ���.");
			} else {
				if(userPwd2.length() > 0) {
					pwdVali2.setIcon(checkImg);
				}
				pwdVali1.setIcon(checkImg);
				inputValiLb.setText("");
				pwdFlag1 = true;
				pwdFlag2 = true;
			}
		}
		
		if(e.getSource() == pwdTf2) {
			String userPwd1 = pwdTf1.getText().trim();
			String userPwd2 = pwdTf2.getText().trim();
			if(userPwd2.length() < 4) {
				pwdVali2.setIcon(xImg);
				pwdFlag1 = false;
				pwdFlag2 = false;
				inputValiLb.setText("��й�ȣ Ȯ���� ��Ȯ�� �Է����ּ���.");
			} else if(!userPwd1.equals(userPwd2)) {
				pwdVali2.setIcon(xImg);
				pwdFlag1 = false;
				pwdFlag2 = false;
				inputValiLb.setText("��й�ȣ�� �����ϰ� �Է����ּ���.");
			} else {
				pwdVali2.setIcon(checkImg);
				pwdFlag1 = true;
				pwdFlag2 = true;
				inputValiLb.setText("");
			}
		}
		
		if(e.getSource() == nameTf) {
			String userName = nameTf.getText().trim();
			if(userName.length() < 2) {
				nameVali.setIcon(xImg);
				nameFlag = false;
				inputValiLb.setText("�̸��� ��Ȯ�� �Է����ּ���.");
			} else {
				nameVali.setIcon(checkImg);
				nameFlag = true;
				inputValiLb.setText("");
			}
		}
		
		if(e.getSource() == telTf) {
			String userTel = telTf.getText().trim();
			boolean flag = true;
			
			if(userTel.length() < 11) {
				telVali.setIcon(xImg);
				telFlag = false;
				inputValiLb.setText("����ó�� ���ڸ� �Է����ּ���.");
			} else {
				for(int i=0; i<userTel.length(); i++){
					char c = userTel.charAt(i);
					if(c<48 || c> 57) {	//���ڰ� �ƴ� ���
						flag = false;
						break;
					}
				}
				if(!flag) {
					telVali.setIcon(xImg);
					telFlag = false;
					inputValiLb.setText("����ó�� ���ڸ� �Է����ּ���.");
				} else {
					telVali.setIcon(checkImg);
					telFlag = true;
					inputValiLb.setText("");
				}
			} 
		}
		
		if(e.getSource() == addrTf) {
			String userAddr = addrTf.getText().trim();
			if(userAddr.length() < 5) {
				addrVali.setIcon(xImg);
				addrFlag = false;
				inputValiLb.setText("�ּҸ� ��Ȯ�� �Է����ּ���.");
			} else {
				addrVali.setIcon(checkImg);
				addrFlag = true;
				inputValiLb.setText("");
			}
		}
	}

}
