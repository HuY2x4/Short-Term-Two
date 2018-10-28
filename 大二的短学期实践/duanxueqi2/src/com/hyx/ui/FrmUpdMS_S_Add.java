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

public class FrmUpdMS_S_Add extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("����");
	
	private JLabel labelName = new JLabel("������:");
	private JLabel labelContent = new JLabel("��������:");
	private JLabel labelLevel = new JLabel("�ȼ�:");
	private JLabel labelPrice = new JLabel("�۸�:");
	private JLabel labelCount = new JLabel("��λ:");
	private JLabel labelTime = new JLabel("ʱ��:");

	private JTextField edtName = new JTextField(10);
	private JTextField edtContent = new JTextField(10);
	private JTextField edtLevel = new JTextField(3);
	private JTextField edtPrice = new JTextField(3);
	private JTextField edtCount = new JTextField(3);
	private JTextField edtTime = new JTextField(3);
	int mID=0;
	public FrmUpdMS_S_Add(Dialog f, String s, boolean b,int mId) {
		super(f, s, b);
		mID=mId;
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

		
		this.setSize(200, 220);
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
			s.setCount(this.edtCount.getText());
			s.setPrice(Integer.parseInt(this.edtPrice.getText()));
			s.setServiceContent(this.edtContent.getText());
			s.setServiceLevel(Integer.parseInt(this.edtLevel.getText()));
			s.setServiceName(this.edtName.getText());
			s.setTime(Float.parseFloat(this.edtTime.getText()));
			try {
				startUtil.serviceContr.addService(s,mID);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "��ӳɹ�"); 
			this.setVisible(false);
			
		}
			
		
	}


}