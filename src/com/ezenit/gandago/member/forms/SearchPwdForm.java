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

import com.ezenit.gandago.common.CommonInfo;
import com.ezenit.gandago.member.daos.MemberDAO;
import com.ezenit.gandago.store.forms.StoreListForm;

public class SearchPwdForm extends JFrame implements ActionListener {
	
	Container container = getContentPane();
	MemberDAO dao = MemberDAO.getInstance();
	 
	// 선언 
	JPanel totalAreaPanel = new JPanel();
	JPanel bodyAreaPanel = new JPanel();

	JPanel titlePn = new JPanel();
	JPanel labelPn = new JPanel();
	JPanel inputPn = new JPanel();
	JPanel btnPn = new JPanel();
	
	
	JLabel titleLb = new JLabel("비밀번호 찾기");
	JLabel idLb = new JLabel("아이디");
	JLabel nameLb = new JLabel("이    름");
	JLabel phoneLb = new JLabel("연락처");
	
	JTextField idTf = new JTextField();
	JTextField nameTf = new JTextField();
	JTextField phoneTf = new JTextField();
	
	JButton searchBtn = new JButton("찾기");
	JButton cancelBtn = new JButton("취소");
	// 선언 
	
	String userId = "";
	
	public SearchPwdForm() {
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
		labelPn.setLayout(new GridLayout(3, 1, 5, 5));
		labelPn.setBorder(new EmptyBorder(0, 15, 0, 0));
		idLb.setFont(new Font("SansSerif", Font.BOLD, 15));
		nameLb.setFont(new Font("SansSerif", Font.BOLD, 15));
		phoneLb.setFont(new Font("SansSerif", Font.BOLD, 15));
		labelPn.add(idLb);
		labelPn.add(nameLb);
		labelPn.add(phoneLb);
		

		bodyAreaPanel.add("Center", inputPn);
		inputPn.setPreferredSize(new Dimension(100, 50));
		inputPn.setBackground(Color.white);
		inputPn.setLayout(new GridLayout(3, 1, 5, 5));
		inputPn.add(idTf);
		inputPn.add(nameTf);
		inputPn.add(phoneTf);


		bodyAreaPanel.add("South", btnPn);
		btnPn.setPreferredSize(new Dimension(100, 350));
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
			String id = idTf.getText().trim();
			
			if(id.equals("")) {
				JOptionPane.showMessageDialog(this, "아이디를 입력해주세요.", "",
						JOptionPane.INFORMATION_MESSAGE);
				idTf.requestFocus();
				return;
			} else if(name.equals("")) {
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
				int result = dao.searchPwd(id, name, phone);
				if(result < 1) {
					JOptionPane.showMessageDialog(this, "찾으시는 회원정보가 없습니다.", "",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					userId = id;
					JOptionPane.showMessageDialog(this, "비밀번호 재설정페이지로 이동합니다.", "",
							JOptionPane.INFORMATION_MESSAGE);
					new ModifyPwdForm(id);
					setVisible(false);
				}
			}
		} else if(e.getSource() == cancelBtn) {
			new LoginForm();
			setVisible(false);
		}
	}
}
