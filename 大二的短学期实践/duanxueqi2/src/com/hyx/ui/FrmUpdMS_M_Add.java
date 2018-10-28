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

public class FrmUpdMS_M_Add extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确认");
	private Button btnCancel = new Button("返回");
	
	private JLabel labelName = new JLabel("材料名:");
	private JLabel labelSort = new JLabel("所属分类:");
	private JLabel labelBrand = new JLabel("所属品牌:");
	private JLabel labelSp = new JLabel("描述信息:");
	private JLabel labelVersion = new JLabel("型号:");
	private JLabel labelColor = new JLabel("颜色:");
	private JLabel labelPrice = new JLabel("价格:");
	private JLabel labelUnit = new JLabel("单位:");
	private JLabel labelKong1 = new JLabel("      ");
	private JLabel labelKong2 = new JLabel("      ");

	private String[] str1={"-全部类别-"};
	private JComboBox JBSort;;
	private String[] str2={"-全部品牌-"};
	private JComboBox JBBrand;

	private JTextField edtName = new JTextField(10);
	private JTextField edtSp = new JTextField(10);
	private JTextField edtVersion = new JTextField(5);
	private JTextField edtColor = new JTextField(5);
	private JTextField edtPrice = new JTextField(5);
	private JTextField edtUnit = new JTextField(5);

	public FrmUpdMS_M_Add(Dialog f, String s, boolean b) {
		super(f, s, b);
		str1=startUtil.sortContr.getAllSortName(2);
		str2=startUtil.brandContr.getAllBrandName(2);
		JBSort=new JComboBox(str1);
		JBBrand=new JComboBox(str2);
		
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		
		workPane.add(labelName);
		workPane.add(edtName);
		workPane.add(labelSort);
		workPane.add(JBSort);
		workPane.add(labelKong1);
		workPane.add(labelBrand);
		workPane.add(JBBrand);
		workPane.add(labelKong2);
		workPane.add(labelSp);
		workPane.add(edtSp);
		workPane.add(labelVersion);
		workPane.add(edtVersion);
		workPane.add(labelColor);
		workPane.add(edtColor);
		workPane.add(labelPrice);
		workPane.add(edtPrice);
		workPane.add(labelUnit);
		workPane.add(edtUnit);
		
		this.setSize(214, 300);
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
			Material m=new Material();
			m.setBrandName(String.valueOf(this.JBBrand.getSelectedItem()));
			m.setColor(this.edtColor.getText());
			m.setMaterialName(this.edtName.getText());
			m.setPrice(Integer.parseInt(this.edtPrice.getText()));
			m.setSortName(String.valueOf(this.JBSort.getSelectedItem()));
			m.setSpecification(this.edtSp.getText());
			m.setUnit(this.edtUnit.getText());
			m.setVersion(this.edtVersion.getText());
			startUtil.materialContr.addMaterial(m);
			JOptionPane.showMessageDialog(null, "添加成功"); 
			this.setVisible(false);
			
		}
			
		
	}


}



