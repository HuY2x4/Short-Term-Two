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

public class FrmAdmin_Sort_Add extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确认");
	private Button btnCancel = new Button("返回");
	
	private JLabel labelName = new JLabel("分类名:");
	private JLabel labelRemark = new JLabel("分类备注:");

	private JTextField edtName = new JTextField(10);
	private JTextField edtRemark = new JTextField(10);

	public FrmAdmin_Sort_Add(Dialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		
		workPane.add(labelName);
		workPane.add(edtName);
		workPane.add(labelRemark);
		workPane.add(edtRemark);
		workPane.add(btnCancel);
		workPane.add(btnOk);

		
		this.setSize(200, 150);
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
			String name=this.edtName.getText();
			String remark=this.edtRemark.getText();

			startUtil.sortContr.addSort(name, remark);
			this.setVisible(false);
			
		}
			
		
	}


}


