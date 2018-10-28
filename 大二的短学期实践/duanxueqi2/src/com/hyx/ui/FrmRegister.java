package com.hyx.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.hyx.startUtil;
import com.hyx.util.BaseException;


public class FrmRegister extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确认");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelUser = new JLabel("用户名:");
	private JLabel labelUserName = new JLabel("姓名:");
	private JLabel labelPwd = new JLabel("密码:");
	private JLabel labelPwd2 = new JLabel("确认密码:");
	private JTextField edtUser = new JTextField(20);
	private JTextField edtUserName = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	public FrmRegister(Dialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		
		workPane.add(labelUser);
		workPane.add(edtUser);
		workPane.add(labelUserName);
		workPane.add(edtUserName);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		workPane.add(labelPwd2);
		workPane.add(edtPwd2);
		
		this.setSize(310, 200);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		//this.setVisible(true);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String user=this.edtUser.getText();
			String userName=this.edtUserName.getText();
			String pwd1=new String(this.edtPwd.getPassword());
			String pwd2=new String(this.edtPwd2.getPassword());
			if(user.length()<6||user.length()>13) {
				JOptionPane.showMessageDialog(null, "账号长度应在6到13位"); 
			}
			else if(pwd1.length()<6||pwd1.length()>13){
				JOptionPane.showMessageDialog(null, "密码长度应在6到13位"); 
			}
			else if(!pwd1.equals(pwd2)){
				JOptionPane.showMessageDialog(null, "两次输入密码应相同"); 
			}
			try {
				startUtil.userContr.register(userName, user, pwd1, pwd2);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"注册失败",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
			
		
	}


}

