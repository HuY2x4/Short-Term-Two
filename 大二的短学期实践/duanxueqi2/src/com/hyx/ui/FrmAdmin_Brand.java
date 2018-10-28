package com.hyx.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hyx.startUtil;
import com.hyx.model.Brand;
import com.hyx.model.Sort;
import com.hyx.util.BaseException;

public class FrmAdmin_Brand extends JDialog implements ActionListener{
	private JPanel panShang = new JPanel();
	private JPanel panZhong = new JPanel();
	private JPanel panXia = new JPanel();


	private JButton btnCancel = new JButton("返回");
	private JButton btnRe = new JButton("刷新");
	private JButton btnUpd = new JButton("修改该品牌");
	private JButton btnDelete = new JButton("删除该品牌");
	private JButton btnAdd = new JButton("添加新品牌");
	
	private JLabel label = new JLabel("品牌信息：");


	
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	

	public FrmAdmin_Brand (FrmUpdHouse frmUpdHouse, String s, boolean b) throws BaseException {
		super(frmUpdHouse, s, b);
		this.setSize(600,300);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		//获取数据
		
		//布局
		this.setLayout(new BorderLayout());
		panShang.setLayout(new FlowLayout(FlowLayout.CENTER));
		panXia.setLayout(new FlowLayout(FlowLayout.CENTER));
		

		
		panXia.add(btnCancel);
		panXia.add(btnDelete);
		panXia.add(btnUpd);
		panXia.add(btnAdd);
		panXia.add(btnRe);
	//	panZhong.add(scroll);
		btnRe.addActionListener(this);
		btnCancel.addActionListener(this);
		btnUpd.addActionListener(this);
		btnDelete.addActionListener(this);
		btnAdd.addActionListener(this);
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
		else if(e.getSource()==this.btnRe){
			try {
				getDatas();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==this.btnDelete){
			
			int selectRows=table.getSelectedRows().length;// 取得用户所选行的行数
			if(selectRows==1){ 
			    int selectedRowIndex = table.getSelectedRow(); // 取得用户所选单行
			    String data=String.valueOf(model.getValueAt(selectedRowIndex, 0));
			    try {
			    	if(startUtil.brandContr.deleteBrand(data)) {
			    		JOptionPane.showMessageDialog(null, "删除成功"); 
			        	getDatas();
			    	}
			    	else {
			    		JOptionPane.showMessageDialog(null, "删除失败，请先删除该品牌下的所有材料"); 
			    	}
			    	
			    	
			    	
					
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
			
		}
		
		else if(e.getSource()==this.btnAdd){
			FrmAdmin_Brand_Add addS=new FrmAdmin_Brand_Add(null,"添加品牌",true);
			
		}
		else if(e.getSource()==this.btnUpd){
			int selectRows=table.getSelectedRows().length;// 取得用户所选行的行数
			if(selectRows==1){ 
			    int selectedRowIndex = table.getSelectedRow(); // 取得用户所选单行
			    String data=String.valueOf(model.getValueAt(selectedRowIndex, 0));
			    System.out.println("data:"+data);
			    FrmAdmin_Brand_Upd addS=new FrmAdmin_Brand_Upd(null,"添加品牌",true,data);
			} 
			
		}
		
	}
	
	public void getDatas() throws BaseException{
		Object[][] datas = new Object[25][15];
		
		String[] titles = { "品牌名称", "品牌备注"};
			List<Brand> list=null;
			list=startUtil.brandContr.getAllBrand();
		
			
			for(int i=0;i<list.size();i++) {
			for(int j=0;j<2;j++) {
				switch (j)  
                {  
                case 0:  
                	datas[i][j]=list.get(i).getBrandName();

                    break;  
                case 1:  
                	datas[i][j]=list.get(i).getBrandRemark();
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
			Object[][] newdatas = new Object[nulllenth][2];
			for(int i=0;i<nulllenth;i++) {
				for(int j=0;j<2;j++) {
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



