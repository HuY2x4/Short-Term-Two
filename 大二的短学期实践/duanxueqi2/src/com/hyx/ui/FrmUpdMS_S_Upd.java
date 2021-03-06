package com.hyx.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.hyx.startUtil;
import com.hyx.model.Material;
import com.hyx.model.Service;
import com.hyx.util.BaseException;

public class FrmUpdMS_S_Upd extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确认");
	private Button btnCancel = new Button("返回");
	
	private JLabel labelName = new JLabel("服务名:");
	private JLabel labelContent = new JLabel("服务内容:");
	private JLabel labelLevel = new JLabel("等级:");
	private JLabel labelPrice = new JLabel("价格:");
	private JLabel labelCount = new JLabel("数量:");
	private JLabel labelTime = new JLabel("时间:");

	private JTextField edtName = new JTextField(10);
	private JTextField edtContent = new JTextField(10);
	private JTextField edtLevel = new JTextField(5);
	private JTextField edtPrice = new JTextField(5);
	private JTextField edtCount = new JTextField(5);
	private JTextField edtTime = new JTextField(5);
	int sID=0;
	public FrmUpdMS_S_Upd(Dialog f, String s, boolean b,int sId) {
		super(f, s, b);
		sID=sId;
		Service ser=startUtil.serviceContr.getService(sId);
		edtName.setText(ser.getServiceName());
		edtContent.setText(ser.getServiceContent());
		edtLevel.setText(Integer.toString(ser.getServiceLevel()));
		edtPrice.setText(Integer.toString(ser.getPrice()));
		edtCount.setText(ser.getCount());
		edtTime.setText(Float.toString(ser.getTime()));
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		
		workPane.add(labelName);
		workPane.add(edtName);
		workPane.add(labelContent);
		workPane.add(edtContent);
		workPane.add(labelLevel);
		workPane.add(edtLevel);
		workPane.add(labelPrice);
		workPane.add(edtPrice);
		workPane.add(labelCount);
		workPane.add(edtCount);
		workPane.add(labelTime);
		workPane.add(edtTime);

		
		this.setSize(220, 250);
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
			Service s=new Service();
			s.setCount(this.edtContent.getText());
			s.setPrice(Integer.parseInt(this.edtPrice.getText()));
			s.setServiceContent(this.edtContent.getText());
			s.setServiceLevel(Integer.parseInt(this.edtLevel.getText()));
			s.setServiceName(this.edtName.getText());
			s.setTime(Float.parseFloat(this.edtTime.getText()));
			s.setServiceId(sID);
			startUtil.serviceContr.updService(s);
			JOptionPane.showMessageDialog(null, "修改成功"); 
			this.setVisible(false);
			
		}
			
		
	}

}
