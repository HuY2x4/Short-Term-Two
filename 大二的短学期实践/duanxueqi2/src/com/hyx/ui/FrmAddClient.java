package com.hyx.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.hyx.startUtil;
import com.hyx.model.House;
import com.hyx.model.User;
import com.hyx.util.BaseException;

public class FrmAddClient extends JDialog implements ActionListener{

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnConfirm = new JButton("确认");
	private JButton btnCancel = new JButton("取消");
	private JLabel labelUserName = new JLabel("房主：");
	private JLabel labelHouseAddress = new JLabel("地址：");
	private JLabel labelHouseTotalArea = new JLabel("面积：");
	private JLabel labelHouseTotalAreaUnit = new JLabel("单位：平方米");
	private JLabel labelShi = new JLabel("房间的数量：");
	private JLabel labelTing = new JLabel("客厅的数量：");
	private JLabel labelChu = new JLabel("厨房的数量：");
	private JLabel labelWei = new JLabel("厕所的数量：");

	private JTextField edtUserName = new JTextField(15);
	private JTextField edtHouseAddress = new JTextField(15);
	private JTextField edtHouseTotalArea = new JTextField(8);
	private JTextField edtShi = new JTextField(1);
	private JTextField edtTing = new JTextField(1);
	private JTextField edtChu = new JTextField(1);
	private JTextField edtWei = new JTextField(1);
	
	
	public FrmAddClient (Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnCancel.addActionListener(this);
		toolBar.add(btnCancel);
		btnConfirm.addActionListener(this);
		toolBar.add(btnConfirm);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUserName);
		workPane.add(edtUserName);
		workPane.add(labelHouseAddress);
		workPane.add(edtHouseAddress);
		workPane.add(labelHouseTotalArea);
		workPane.add(edtHouseTotalArea);
		workPane.add(labelHouseTotalAreaUnit);
		workPane.add(labelShi);
		workPane.add(edtShi);
		workPane.add(labelTing);
		workPane.add(edtTing);
		workPane.add(labelChu);
		workPane.add(edtChu);
		workPane.add(labelWei);
		workPane.add(edtWei);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(260,250);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.validate();
		this.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btnConfirm) {
			String userName=this.edtUserName.getText();
			String houseAddress=this.edtHouseAddress.getText();
			String houseTotalArea=this.edtHouseTotalArea.getText();
			int shi=Integer.parseInt(this.edtShi.getText());
			int ting=Integer.parseInt(this.edtTing.getText());
			int chu=Integer.parseInt(this.edtChu.getText());
			int wei=Integer.parseInt(this.edtWei.getText());
			int room=10000+shi*1000+ting*100+chu*10+wei;
			House house=new House();
			house.setHouseAddress(houseAddress);
			house.setHouseTotalArea(Integer.parseInt(houseTotalArea));
			house.setRoom(room);
			house.setUserId(User.currentLoginUser.getUserId());
			try {
				if(startUtil.clientContr.addClientHouse(userName, house)) {
					JOptionPane.showMessageDialog(null, "添加成功", "提醒",JOptionPane.WARNING_MESSAGE);  
				}
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), "账号或密码错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
			}
		else if(e.getSource()==this.btnCancel){
			this.setVisible(false);
		}
	}

	
}
