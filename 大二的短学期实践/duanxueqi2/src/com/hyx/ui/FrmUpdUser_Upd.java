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
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.hyx.startUtil;
import com.hyx.model.User;

public class FrmUpdUser_Upd extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确认");
	private Button btnCancel = new Button("返回");
	private JLabel labelid = new JLabel("用户编号:  ");
	private JLabel labelid1;
	private JLabel labelName = new JLabel("用户名字:  ");
	private JLabel labelName1;
	private JLabel labelAccount = new JLabel("用户账号:");
	private JLabel labelAccount1;
	private JLabel labelPassword = new JLabel("用户密码:");
	private JLabel labelLevel = new JLabel("用户权限:");

	int ID=0;
	User user=new User();
	private JTextField edtPassword = new JTextField(10);
	private JTextField edtLevel = new JTextField(10);
	public FrmUpdUser_Upd(Dialog f, String s, boolean b,int id) {
		super(f, s, b);
		ID=id;
	    user=startUtil.userContr.getUserById(id);
		labelid1 = new JLabel(Integer.toString(user.getUserId()));
		labelName1 = new JLabel(user.getUserName());
		labelAccount1 = new JLabel(user.getUserAccount());
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);

		workPane.add(labelid);
		workPane.add(labelid1);
		workPane.add(labelName);
		workPane.add(labelName1);
		workPane.add(labelAccount);
		workPane.add(labelAccount1);
		workPane.add(labelPassword);
		workPane.add(edtPassword);
		workPane.add(labelLevel);
		workPane.add(edtLevel);
		workPane.add(btnCancel);
		workPane.add(btnOk);
		this.setSize(150, 300);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			
			User u=new User();
			u.setUserId(ID);
			u.setUserAccount(user.getUserAccount());
			u.setUserLevel(Integer.parseInt(this.edtLevel.getText()));
			u.setUserName(user.getUserName());
			u.setUserPassword(this.edtPassword.getText());

			startUtil.userContr.updUser(u);
			
			this.setVisible(false);
			
		}
			
		
	}


}
