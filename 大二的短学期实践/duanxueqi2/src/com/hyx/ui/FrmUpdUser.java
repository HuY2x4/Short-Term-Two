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
import com.hyx.model.Sort;
import com.hyx.model.User;
import com.hyx.util.BaseException;

public class FrmUpdUser extends JDialog implements ActionListener{
	private JPanel panShang = new JPanel();
	private JPanel panZhong = new JPanel();
	private JPanel panXia = new JPanel();


	private JButton btnCancel = new JButton("返回");
	private JButton btnRe = new JButton("刷新");
	private JButton btnUpd = new JButton("修改");

	



	
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	

	public FrmUpdUser (FrmUpdHouse frmUpdHouse, String s, boolean b) throws BaseException {
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
	
		panXia.add(btnUpd);
		panXia.add(btnRe);
	//	panZhong.add(scroll);
		btnRe.addActionListener(this);
		btnCancel.addActionListener(this);
		btnUpd.addActionListener(this);

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
	
		
		else if(e.getSource()==this.btnUpd){
			int selectRows=table.getSelectedRows().length;// 取得用户所选行的行数
			if(selectRows==1){ 
			    int selectedRowIndex = table.getSelectedRow(); // 取得用户所选单行
			    int data=Integer.parseInt(String.valueOf(model.getValueAt(selectedRowIndex, 0)));
			    System.out.println("data:"+data);
			    FrmUpdUser_Upd addS=new FrmUpdUser_Upd(null,"修改信息",true,data);
			} 
			
		}
		
	}
	
	public void getDatas() throws BaseException{
		Object[][] datas = new Object[25][15];
		
		String[] titles = { "用户编号", "名字","账号","权限"};
			List<User> list=null;
			list=startUtil.userContr.getAllUser();
			
			for(int i=0;i<list.size();i++) {
			for(int j=0;j<4;j++) {
				switch (j)  
                {  
                case 0:  
                	datas[i][j]=list.get(i).getUserId();

                    break;  
                case 1:  
                	datas[i][j]=list.get(i).getUserName();
                    break;  
                case 2:  
                	datas[i][j]=list.get(i).getUserAccount();

                    break;  
                case 3:  
                	datas[i][j]=list.get(i).getUserLevel();
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
			Object[][] newdatas = new Object[nulllenth][4];
			for(int i=0;i<nulllenth;i++) {
				for(int j=0;j<4;j++) {
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
