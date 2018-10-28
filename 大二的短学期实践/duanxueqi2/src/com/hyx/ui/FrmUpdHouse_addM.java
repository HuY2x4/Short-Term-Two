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
import com.hyx.model.MSInfo;
import com.hyx.model.Material;
import com.hyx.model.MaterialBudget;
import com.hyx.model.Service;
import com.hyx.model.ServiceBudget;
import com.hyx.util.BaseException;

public class FrmUpdHouse_addM extends JDialog implements ActionListener{
	private JPanel panShang = new JPanel();
	private JPanel panZhong = new JPanel();
	private JPanel panXia = new JPanel();


	private JButton btnConfirm = new JButton("添加");
	private JButton btnCancel = new JButton("返回");
	private JButton btnSousuo = new JButton("搜索");
	
	private JLabel labelCount = new JLabel("数量:");
	private JLabel labelSort = new JLabel("分类:");
	private JLabel labelBrand = new JLabel("品牌:");

	private JTextField edtCount = new JTextField(3);
	
	private String[] str1={"-全部-"};
	private JComboBox JBSort;
	private String[] str2={"-全部-"};
	private JComboBox JBBrand;
	
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private int roomID;
	private int mbID;
	private int state=0;
	
 //   String[] titles = { "房间编号", "房间面积","房子编号","房间结构","备注"};
	//private DefaultTableModel model = new DefaultTableModel(datas, titles);;
//	private JTable table = new JTable(model);;
	//private JScrollPane scroll = new JScrollPane(table);
	public FrmUpdHouse_addM (FrmUpdHouse frmUpdHouse, String s, boolean b,int roomId) throws BaseException {
		
		super(frmUpdHouse, s, b);
		roomID=roomId;
		this.setSize(600,600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		//获取数据
		str1=startUtil.sortContr.getAllSortName(1);
		str2=startUtil.brandContr.getAllBrandName(1);
		JBSort=new JComboBox(str1);
		JBBrand=new JComboBox(str2);
		edtCount.setText("1");
		//布局
		this.setLayout(new BorderLayout());
		panShang.setLayout(new FlowLayout(FlowLayout.CENTER));
		panXia.setLayout(new FlowLayout(FlowLayout.CENTER));

	//	panShang.add(label);
	//	panShang.add(labelZhu);
		panShang.add(labelSort);
		panShang.add(JBSort);
		panShang.add(labelBrand);
		panShang.add(JBBrand);
		panShang.add(btnSousuo);

		panXia.add(btnCancel);
		panXia.add(labelCount);
		panXia.add(edtCount);
		panXia.add(btnConfirm);
	//	panZhong.add(scroll);
		btnCancel.addActionListener(this);
		btnConfirm.addActionListener(this);
		btnSousuo.addActionListener(this);

		this.add("North",panShang);
		this.add("Center",panZhong);
		this.add("South",panXia);	

		//添加数据
		getDatas();

		
		
		this.validate();
		this.setVisible(true);
		
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==this.btnCancel){
			this.setVisible(false);
		}
		else if(e.getSource()==this.btnConfirm){
			int id=0;
			MaterialBudget mb=new MaterialBudget();
			int selectedRowIndex = table.getSelectedRow();
			mb.setMaterialId((int)model.getValueAt(selectedRowIndex, 0));
			mb.setRoomId(roomID);
			mb.setCount(Integer.parseInt(this.edtCount.getText()));
			mb.setPrice((int)model.getValueAt(selectedRowIndex, 7));
			mb.setTotalPrice((int)model.getValueAt(selectedRowIndex, 7)*Integer.parseInt(this.edtCount.getText()));
			try {
			id=startUtil.materialBudgetContr.addMBOfRoom(mb);
			} catch (BaseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			int n = JOptionPane.showConfirmDialog(null, "添加成功，是否添加相应服务", "标题",JOptionPane.YES_NO_OPTION);//i=0/1 

			if(n==0){
				try {
					
					FrmUpdHouse_addS addS=new FrmUpdHouse_addS(null,"服务信息",true,roomID,mb.getMaterialId(), id);

				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else if(e.getSource()==this.btnSousuo){
			try {
				getDatas();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	
		
	}
	
	public void getDatas() throws BaseException{
		Object[][] datas = new Object[25][10];
		String[] titles = { "材料编号", "材料名","分类","品牌","信息","型号","颜色","价格","单位"};
		List<Material> list=null;
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
