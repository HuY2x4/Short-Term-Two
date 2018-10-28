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
import com.hyx.model.Room;

public class FrmUpdRoom extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确认");
	private Button btnCancel = new Button("返回");
	
	private JLabel labelId = new JLabel("房间编号:  ");
	private JLabel labelId1;
	private JLabel labelSort = new JLabel("房间类型:");
	private JLabel labelSort1;
	private JLabel labelArea = new JLabel("房间面积:");
	private JLabel labelRemark = new JLabel("房间备注:");
	String strSort="";
	int roomID=0;
	private JTextField edtArea = new JTextField(10);
	private JTextField edtRemark = new JTextField(10);

	public FrmUpdRoom(Dialog f, String s, boolean b,int roomId) {
		super(f, s, b);
		roomID=roomId;
		Room r=startUtil.roomContr.getRoom(roomId);
		labelId1 = new JLabel(Integer.toString(r.getRoomId()));
		int rs=r.getRoomSort();
		if(rs==1) {
			strSort="房间";
		}
		else if(rs==2) {
			strSort="客厅";
		}
		else if(rs==3) {
			strSort="厨房";
		}
		else if(rs==4) {
			strSort="卫生间";
		}
		labelSort1 = new JLabel(strSort);
		
		
		
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		
		workPane.add(labelId);
		workPane.add(labelId1);
		workPane.add(labelSort);
		workPane.add(labelSort1);
		workPane.add(labelArea);
		workPane.add(edtArea);
		workPane.add(labelRemark);
		workPane.add(edtRemark);
		workPane.add(btnCancel);
		workPane.add(btnOk);
		this.setSize(230, 200);
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
			Float area=Float.valueOf(this.edtArea.getText());
			String remark=this.edtRemark.getText();

			startUtil.roomContr.updRoom(roomID, area, remark);
			this.setVisible(false);
			
		}
			
		
	}


}

