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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.ezenit.gandago.member.daos.MemberDAO;

public class SearchIdForm extends JFrame implements ActionListener {
	
	Container container = getContentPane();
	MemberDAO dao = MemberDAO.getInstance();
	 
	// 선언 
	JPanel totalAreaPanel = new JPanel();
	JPanel bodyAreaPanel = new JPanel();

	JPanel titlePn = new JPanel();
	JPanel labelPn = new JPanel();
	JPanel inputPn = new JPanel();
	JPanel btnPn = new JPanel();
	
	
	JLabel titleLb = new JLabel("아이디 찾기");
	JLabel nameLb = new JLabel("이    름");
	JLabel phoneLb = new JLabel("연락처");
	
	JTextField nameTf = new JTextField();
	JTextField phoneTf = new JTextField();
	
	JButton searchBtn = new JButton("찾기");
	JButton cancelBtn = new JButton("취소");
	// 선언 
	
	public SearchIdForm() {
		setTitle("간다Go");
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
		nameLb.setFont(new Font("SansSerif", Font.BOLD, 15));
		phoneLb.setFont(new Font("SansSerif", Font.BOLD, 15));
		labelPn.add(nameLb);
		labelPn.add(phoneLb);
		

		bodyAreaPanel.add("Center", inputPn);
		inputPn.setPreferredSize(new Dimension(100, 50));
		inputPn.setBackground(Color.white);
		inputPn.setLayout(new GridLayout(2, 1, 5, 5));
		inputPn.add(nameTf);
		inputPn.add(phoneTf);


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
			String name = nameTf.getText().trim();
			String phone = phoneTf.getText().trim();
			
			if(name.equals("")) {
				JOptionPane.showMessageDialog(this, "이름을 입력해주세요.", "",
						JOptionPane.INFORMATION_MESSAGE);
				nameTf.requestFocus();
				return;
			} else if(phone.equals("")) {
				JOptionPane.showMessageDialog(this, "연락처를 입력해주세요.", "",
						JOptionPane.INFORMATION_MESSAGE);
				phoneTf.requestFocus();
				return;
			} else {
				String userId = dao.searchId(name, phone);
				if(userId.equals("")) {
					JOptionPane.showMessageDialog(this, "찾으시는 회원정보가 없습니다.", "",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					JOptionPane.showMessageDialog(this, "회원님의 아이디는 [" + userId + "] 입니다.", "",
							JOptionPane.INFORMATION_MESSAGE);
					new LoginForm();
					setVisible(false);
				}
			}
		} else if(e.getSource() == cancelBtn) {
			new LoginForm();
			setVisible(false);
		}
	}

}
