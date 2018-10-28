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
import com.hyx.model.MSInfo;
import com.hyx.model.Material;
import com.hyx.model.MaterialBudget;
import com.hyx.model.Service;
import com.hyx.model.ServiceBudget;
import com.hyx.model.Sort;
import com.hyx.util.BaseException;

public class FrmAdmin_Sort extends JDialog implements ActionListener{
	private JPanel panShang = new JPanel();
	private JPanel panZhong = new JPanel();
	private JPanel panXia = new JPanel();


	private JButton btnCancel = new JButton("返回");
	private JButton btnRe = new JButton("刷新");
	private JButton btnUpd = new JButton("修改该分类");
	private JButton btnDelete = new JButton("删除该分类");
	private JButton btnAdd = new JButton("添加新分类");
	
	private JLabel label = new JLabel("分类信息：");


	
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	

	public FrmAdmin_Sort (FrmUpdHouse frmUpdHouse, String s, boolean b) throws BaseException {
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
			    	if(startUtil.sortContr.deleteSort(data)) {
			    		JOptionPane.showMessageDialog(null, "删除成功"); 
			        	getDatas();
			    	}
			    	else {
			    		JOptionPane.showMessageDialog(null, "删除失败，请先删除该分类下的所有材料"); 
			    	}
			    	
			    	
			    	
					
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
			
		}
		
		else if(e.getSource()==this.btnAdd){
			FrmAdmin_Sort_Add addS=new FrmAdmin_Sort_Add(null,"添加分类",true);
			
		}
		else if(e.getSource()==this.btnUpd){
			int selectRows=table.getSelectedRows().length;// 取得用户所选行的行数
			if(selectRows==1){ 
			    int selectedRowIndex = table.getSelectedRow(); // 取得用户所选单行
			    String data=String.valueOf(model.getValueAt(selectedRowIndex, 0));
			    System.out.println("data:"+data);
			    FrmAdmin_Sort_Upd addS=new FrmAdmin_Sort_Upd(null,"添加分类",true,data);
			} 
			
		}
		
	}
	
	public void getDatas() throws BaseException{
		Object[][] datas = new Object[25][15];
		
		String[] titles = { "分类名称", "分类备注"};
			List<Sort> list=null;
			list=startUtil.sortContr.getAllSort();
			
			for(int i=0;i<list.size();i++) {
			for(int j=0;j<2;j++) {
				switch (j)  
                {  
                case 0:  
                	datas[i][j]=list.get(i).getSortName();

                    break;  
                case 1:  
                	datas[i][j]=list.get(i).getSortRemark();
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


