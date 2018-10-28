package com.hyx.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.hyx.startUtil;
import com.hyx.model.Material;
import com.hyx.util.BaseException;

public class FrmUpdMS_M extends JDialog implements ActionListener{
	private JPanel panShang = new JPanel();
	private JPanel panZhong = new JPanel();
	private JPanel panXia = new JPanel();

	private JButton btnConfirm = new JButton("查询对应服务");
	private JButton btnCancel = new JButton("返回");
	private JButton btnChaxun = new JButton("查询");
	private JButton btnSousuo = new JButton("搜索");
	private JButton btnDelete = new JButton("删除");
	private JButton btnUpd = new JButton("修改");
	private JButton btnAdd = new JButton("增加");
	private JButton btnRe = new JButton("刷新列表");
	
	private JLabel labelSousuo = new JLabel("按照材料名查询:");

	private JTextField edtSousuo = new JTextField(5);
	
	private String[] str1={"-全部类别-"};
	private JComboBox JBSort;;
	private String[] str2={"-全部品牌-"};
	private JComboBox JBBrand;
	
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private int state=0;
	
 //   String[] titles = { "房间编号", "房间面积","房子编号","房间结构","备注"};
	//private DefaultTableModel model = new DefaultTableModel(datas, titles);;
//	private JTable table = new JTable(model);;
	//private JScrollPane scroll = new JScrollPane(table);
	public FrmUpdMS_M (FrmUpdHouse frmUpdHouse, String s, boolean b) throws BaseException {
		
		super(frmUpdHouse, s, b);
		this.setSize(600,300);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		//获取数据
		str1=startUtil.sortContr.getAllSortName(1);
		str2=startUtil.brandContr.getAllBrandName(1);
		JBSort=new JComboBox(str1);
		JBBrand=new JComboBox(str2);
		//布局
		this.setLayout(new BorderLayout());
		panShang.setLayout(new FlowLayout(FlowLayout.CENTER));
		panXia.setLayout(new FlowLayout(FlowLayout.CENTER));

	
		panShang.add(JBSort);
		panShang.add(JBBrand);
		panShang.add(btnChaxun);
		panShang.add(labelSousuo);
		panShang.add(edtSousuo);
		panShang.add(btnSousuo);
		
		panXia.add(btnCancel);
		panXia.add(btnDelete);
		panXia.add(btnUpd);
		panXia.add(btnAdd);
		panXia.add(btnRe);
		panXia.add(btnConfirm);
	//	panZhong.add(scroll);
		btnCancel.addActionListener(this);
		btnConfirm.addActionListener(this);
		btnChaxun.addActionListener(this);
		btnSousuo.addActionListener(this);
		btnUpd.addActionListener(this);
		btnDelete.addActionListener(this);
		btnAdd.addActionListener(this);
		btnRe.addActionListener(this);
		this.add("North",panShang);
		this.add("Center",panZhong);
		this.add("South",panXia);	

		//添加数据
		getDatas(1);

		
		
		this.validate();
		this.setVisible(true);
		
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==this.btnCancel){
			this.setVisible(false);
		}else if(e.getSource()==this.btnConfirm){
			try {
				int selectedRowIndex = table.getSelectedRow();
				FrmUpdMS_S addS=new FrmUpdMS_S(null,"服务信息",true,(int)model.getValueAt(selectedRowIndex, 0),String.valueOf(model.getValueAt(selectedRowIndex, 1)));

			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource()==this.btnChaxun){
			try {
				getDatas(1);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource()==this.btnSousuo){
			try {
				getDatas(2);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==this.btnDelete){
			int selectedRowIndex = table.getSelectedRow();
			if(startUtil.materialContr.delMaterial((int)model.getValueAt(selectedRowIndex, 0))) {
				JOptionPane.showMessageDialog(null, "删除成功"); 
				try {
					getDatas(1);
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "删除失败，请先删除对应的服务！"); 
			}
		}
		else if(e.getSource()==this.btnAdd){
			FrmUpdMS_M_Add addS=new FrmUpdMS_M_Add(null,"添加材料",true);
		}
		else if(e.getSource()==this.btnUpd){
			int selectedRowIndex = table.getSelectedRow();
			FrmUpdMS_M_Upd upd=new FrmUpdMS_M_Upd(null,"修改材料",true,(int)model.getValueAt(selectedRowIndex, 0));
		}
		else if(e.getSource()==this.btnRe){
			try {
				getDatas(1);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
		
	}
	
	public void getDatas(int methon) throws BaseException{
		Object[][] datas = new Object[25][10];
		String[] titles = { "材料编号", "材料名","分类","品牌","信息","型号","颜色","价格","单位"};
		List<Material> list=null;
		if(methon==1) {
			String sortName=String.valueOf(this.JBSort.getSelectedItem());
			String brandName=String.valueOf(this.JBBrand.getSelectedItem());
			if(this.JBSort.getSelectedIndex()==0&&this.JBBrand.getSelectedIndex()==0) {
				list=startUtil.materialContr.getAllMaterial();
				}
			else if(this.JBSort.getSelectedIndex()!=0&&this.JBBrand.getSelectedIndex()==0){
				list=startUtil.materialContr.getPartMaterialByS(sortName);
			}
			else if(this.JBSort.getSelectedIndex()==0&&this.JBBrand.getSelectedIndex()!=0){
				list=startUtil.materialContr.getPartMaterialByB(brandName);
			}
			else {
				list=startUtil.materialContr.getPartMaterialBySB(sortName, brandName);
			}
		}
		else if(methon==2) {
			list=startUtil.materialContr.getMaterialByName(this.edtSousuo.getText());
			
		}
		
		
		
		
		
		for(int i=0;i<list.size();i++) {
		for(int j=0;j<9;j++) {
				switch (j)  
                {  
                case 0:  
                	datas[i][j]=list.get(i).getMaterialId();

                    break;  
                case 1:  
                	datas[i][j]=list.get(i).getMaterialName();
                    break;  
                case 2:  
                	datas[i][j]=list.get(i).getSortName();
                    break;  
                case 3:  
                	datas[i][j]=list.get(i).getBrandName();
                    break;  
                case 4:  
                	datas[i][j]=list.get(i).getSpecification();
                    break;  
                case 5:  
                	datas[i][j]=list.get(i).getVersion();
                    break;  
                case 6:  
                	datas[i][j]=list.get(i).getColor();
                    break;  
                case 7:  
                	datas[i][j]=list.get(i).getPrice();
                    break;  
                case 8:  
                	datas[i][j]=list.get(i).getUnit();
                    break;  
				
                }

			}
		}
			
			int nulllenth=0;
			for(int i=0;i<datas.length;i++) {
				if(datas[i][0]==null) {
					nulllenth=i;
					break;
				}
			
			}
			Object[][] newdatas = new Object[nulllenth][9];
			for(int i=0;i<nulllenth;i++) {
				for(int j=0;j<9;j++) {
					newdatas[i][j]=datas[i][j];
				}
			}
			
			model = new DefaultTableModel(newdatas, titles);
			
			 table = new JTable(model);
			
			scroll = new JScrollPane(table);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			
			panZhong.removeAll();
			panZhong.add(scroll);
			panZhong.repaint();
			panZhong.updateUI();
			
			
		
		
		 
		
		
		
		
	}
	

}
