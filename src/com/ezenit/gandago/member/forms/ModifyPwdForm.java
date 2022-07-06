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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.ezenit.gandago.member.daos.MemberDAO;

public class ModifyPwdForm extends JFrame implements ActionListener {
	
	Container container = getContentPane();
	MemberDAO dao = MemberDAO.getInstance();
	 
	// ���� 
	JPanel totalAreaPanel = new JPanel();
	JPanel bodyAreaPanel = new JPanel();

	JPanel titlePn = new JPanel();
	JPanel labelPn = new JPanel();
	JPanel inputPn = new JPanel();
	JPanel btnPn = new JPanel();
	
	
	JLabel titleLb = new JLabel("��й�ȣ �缳��");
	JLabel pwdLb1 = new JLabel("��й�ȣ");
	JLabel pwdLb2 = new JLabel("��й�ȣ Ȯ��");
	
	JPasswordField pwdTf1 = new JPasswordField();
	JPasswordField pwdTf2 = new JPasswordField();
	
	JButton searchBtn = new JButton("�缳��");
	JButton cancelBtn = new JButton("���");
	// ���� 
	
	String userId = "";
	
	public ModifyPwdForm(String userId) {
		this.userId = userId;
		setTitle("����Go");
		setSize(500, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
		init();
		start();
	}
	
	private void init() {
		container.add(bodyAreaPanel);
		
		bodyAreaPanel.setLayout(new BorderLayout());
		bodyAreaPanel.setPreferredSize(new Dimension(550, 500));
		
		bodyAreaPanel.add("North", titlePn);
		
		
		titlePn.setPreferredSize(new Dimension(500, 250));
		titlePn.setBackground(Color.white);
		titlePn.setLayout(new FlowLayout(FlowLayout.LEFT));
		titlePn.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		titlePn.add(titleLb);
		titleLb.setBorder(new EmptyBorder(190, 10, 0, 0));
		titleLb.setFont(new Font("SansSerif", Font.BOLD, 30));
		
		
		bodyAreaPanel.add("West", labelPn);
		labelPn.setPreferredSize(new Dimension(120, 50));
		labelPn.setBackground(Color.white);
		labelPn.setLayout(new GridLayout(2, 1, 5, 5));
		labelPn.setBorder(new EmptyBorder(0, 15, 0, 0));
		pwdLb1.setFont(new Font("SansSerif", Font.BOLD, 15));
		pwdLb2.setFont(new Font("SansSerif", Font.BOLD, 15));
		labelPn.add(pwdLb1);
		labelPn.add(pwdLb2);
		

		bodyAreaPanel.add("Center", inputPn);
		inputPn.setPreferredSize(new Dimension(100, 50));
		inputPn.setBackground(Color.white);
		inputPn.setLayout(new GridLayout(2, 1, 5, 5));
		inputPn.add(pwdTf1);
		inputPn.add(pwdTf2);


		bodyAreaPanel.add("South", btnPn);
		btnPn.setPreferredSize(new Dimension(100, 400));
		btnPn.setBorder(new MatteBorder(1, 0, 0, 0, Color.black));
		btnPn.setBackground(Color.white);
		btnPn.add(searchBtn);
		btnPn.add(cancelBtn);
		
		searchBtn.setPreferredSize(new Dimension(120, 35));
		cancelBtn.setPreferredSize(new Dimension(120, 35));
		
		searchBtn.setFont(new Font("SansSerif", Font.BOLD, 15));
		cancelBtn.setFont(new Font("SansSerif", Font.BOLD, 15));
		
		searchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	private void start() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		searchBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == searchBtn) {
			String pwd1 = pwdTf1.getText().trim();
			String pwd2 = pwdTf2.getText().trim();
			
			if(pwd1.equals("")) {
				JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է����ּ���.", "",
						JOptionPane.INFORMATION_MESSAGE);
				pwdTf1.requestFocus();
				return;
			} else if(pwd1.length() < 4) {
				JOptionPane.showMessageDialog(this, "��й�ȣ�� 4���� �̻� �Է����ּ���.", "",
						JOptionPane.INFORMATION_MESSAGE);
				pwdTf1.requestFocus();
				return;
			} else if(pwd2.equals("")) {
				JOptionPane.showMessageDialog(this, "��й�ȣ Ȯ���� �Է����ּ���.", "",
						JOptionPane.INFORMATION_MESSAGE);
				pwdTf2.requestFocus();
				return;
			} else if(!pwd1.equals(pwd2)) {
				JOptionPane.showMessageDialog(this, "��й�ȣ�� �����ϰ� �Է����ּ���.", "",
						JOptionPane.INFORMATION_MESSAGE);
				pwdTf2.requestFocus();
				return;
			} else {
				int result = dao.modifyPwd(userId, pwd1);
				
				if(result > 0) {
					JOptionPane.showMessageDialog(this, "ȸ������ ��й�ȣ�� �缳���Ǿ����ϴ�.\n�α����������� �̵��մϴ�.", "",
							JOptionPane.INFORMATION_MESSAGE);
					new LoginForm();
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(this, "������ �߻��߽��ϴ�.", "����",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		} else if(e.getSource() == cancelBtn) {
			new LoginForm();
			setVisible(false);
		}
	}
}
