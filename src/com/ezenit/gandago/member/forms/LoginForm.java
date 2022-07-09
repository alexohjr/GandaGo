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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ezenit.gandago.common.CommonInfo;
import com.ezenit.gandago.member.daos.MemberDAO;
import com.ezenit.gandago.store.forms.StoreListForm;

public class LoginForm extends JFrame implements ActionListener {
	
	Container container = getContentPane();
	MemberDAO dao = MemberDAO.getInstance();
	
	// ���� 
	JButton button1 = new JButton(CommonInfo.userId);
	
	
	ImageIcon logoImage = new ImageIcon("resources/images/common/headerLogo.JPG");
	JLabel imageLb = new JLabel();
	
	
	JPanel totalAreaPanel = new JPanel();
	
	JPanel loginLbPn = new JPanel();
	
	JPanel inputPn = new JPanel();
	JPanel idPn = new JPanel();
	JPanel pwdPn = new JPanel();
	JPanel loginBtnPn = new JPanel();
	JPanel searchPn = new JPanel();
	
	JLabel loginLb = new JLabel("L O G I N");
	JLabel idLb = new JLabel("���̵�");
	JLabel pwdLb = new JLabel("��й�ȣ");
	
	JTextField idTf = new JTextField();
	JPasswordField pwdTf = new JPasswordField();
	
	JButton loginBtn = new JButton("�� �� ��");
	JButton searchIdBtn = new JButton("���̵�ã��");
	JButton searchPwdBtn = new JButton("��й�ȣã��");
	JButton joinBtn = new JButton("ȸ������");
	  
	public LoginForm() {
		setTitle("창화닝Go!");
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
		
		totalAreaPanel.add(imageLb);
		totalAreaPanel.setBackground(Color.white);
		totalAreaPanel.setPreferredSize(new Dimension(500, 800));
	
		imageLb.setIcon(logoImage);
		imageLb.setBorder(new EmptyBorder(30, 0, 0, 0));
		

		totalAreaPanel.add(loginLbPn);
		loginLbPn.setBackground(Color.white);
		loginLbPn.setPreferredSize(new Dimension(500, 140));
		loginLbPn.add(loginLb);
		loginLb.setFont(new Font("SansSerif", Font.BOLD, 50));
		loginLb.setBorder(new EmptyBorder(60, 0, 0, 0));
		
		totalAreaPanel.add(inputPn);
		inputPn.setBackground(Color.white);
		inputPn.setPreferredSize(new Dimension(350, 200));
		
		inputPn.setLayout(new GridLayout(4, 1, 0, 5));
		
		inputPn.add(idPn);
		idPn.setLayout(new BorderLayout());
		idPn.setBackground(Color.white);
		idPn.add("West", idLb);
		idPn.add("East", idTf);
		idLb.setFont(new Font("SansSerif", Font.BOLD, 20));
		idTf.setPreferredSize(new Dimension(260, 20));
		
		inputPn.add(pwdPn);
		pwdPn.setLayout(new BorderLayout());
		pwdPn.setBackground(Color.white);
		pwdPn.add("West", pwdLb);
		pwdPn.add("East",pwdTf);
		pwdLb.setFont(new Font("SansSerif", Font.BOLD, 20));
		pwdTf.setPreferredSize(new Dimension(260, 20));
		
		inputPn.add(loginBtn);
		loginBtn.setFont(new Font("SansSerif", Font.BOLD, 20));
		loginBtn.setBackground(new Color(255, 224, 140));
		loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		inputPn.add(searchPn);
		searchPn.setLayout(new GridLayout(1, 3, 2, 0));
		searchPn.add(searchIdBtn);
		searchPn.add(searchPwdBtn);
		searchPn.add(joinBtn);
		
		
		searchIdBtn.setBorderPainted(false);
		searchIdBtn.setFocusPainted(false);
		searchIdBtn.setBackground(Color.white);
		searchIdBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		searchPwdBtn.setBorderPainted(false);
		searchPwdBtn.setFocusPainted(false);
		searchPwdBtn.setBackground(Color.white);
		searchPwdBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		joinBtn.setBorderPainted(false);
		joinBtn.setFocusPainted(false);
		joinBtn.setBackground(Color.white);
		joinBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	private void start() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		loginBtn.addActionListener(this);
		searchIdBtn.addActionListener(this);
		searchPwdBtn.addActionListener(this);
		joinBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginBtn) {
			loginCheck();
		} else if(e.getSource() == searchIdBtn) {
			new SearchIdForm();
			setVisible(false);
		} else if(e.getSource() == searchPwdBtn) {
			new SearchPwdForm();
			setVisible(false);
		} else if(e.getSource() == joinBtn) {
			new JoinForm();
			setVisible(false);
		}
	}
	
	
	public void loginCheck() {
		String userId = idTf.getText().trim();
		String userPwd = pwdTf.getText().trim();
		
		if(userId.equals("")) {
			JOptionPane.showMessageDialog(this, "���̵� �Է����ּ���.", "",
					JOptionPane.INFORMATION_MESSAGE);
			idTf.requestFocus(true);
			return;
		}
		if(userPwd.equals("")) {
			JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է����ּ���.", "",
					JOptionPane.INFORMATION_MESSAGE);
			pwdTf.requestFocus(true);
			return;
		}
		
		int result = dao.loginCheck(userId, userPwd);
		if(result > 0) {
			CommonInfo.userId = userId;	// �α��� ������ ���̵� �Ѱ��ֱ�
			new StoreListForm();				
			setVisible(false);				// ���� â �����
		} else {
			JOptionPane.showMessageDialog(this, "�������� ���� ���̵��̰ų�, �߸��� ��й�ȣ�Դϴ�.", "�α��� ����",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
