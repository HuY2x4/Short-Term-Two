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

public class FrmAdmin_Brand_Upd extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确认");
	private Button btnCancel = new Button("返回");
	
	private JLabel labelName = new JLabel("品牌名:  ");
	private JLabel labelName1;
	private JLabel labelRemark = new JLabel("品牌备注:");

	String data1="";
	private JTextField edtRemark = new JTextField(20);

	public FrmAdmin_Brand_Upd(Dialog f, String s, boolean b,String data) {
		super(f, s, b);
		data1=data;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		labelName1 = new JLabel(data1);
		workPane.add(labelName);
		workPane.add(labelName1);
		workPane.add(labelRemark);
		workPane.add(edtRemark);
		workPane.add(btnCancel);
		workPane.add(btnOk);
		this.setSize(250, 120);
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
			
			String remark=this.edtRemark.getText();

			startUtil.brandContr.updBrand(data1, remark);
			this.setVisible(false);
			
		}
			
		
	}


}



