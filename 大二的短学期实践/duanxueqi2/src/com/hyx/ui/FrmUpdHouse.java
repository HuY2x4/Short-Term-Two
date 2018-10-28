package com.hyx.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
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
import com.hyx.model.Client;
import com.hyx.model.House;
import com.hyx.model.User;
import com.hyx.util.BaseException;

public class FrmUpdHouse extends JDialog implements ActionListener{
	private JPanel panShang = new JPanel();
	private JPanel panZhong = new JPanel();
	private JPanel panXia = new JPanel();

	private JButton btnSousuo = new JButton("搜索");
	private JButton btnConfirm = new JButton("选择该房子");
	private JButton btnCancel = new JButton("取消");
	
	private String[] str1={"搜索房子编号", "搜索客户名"};
	private JComboBox jcb=new JComboBox(str1);

	private JTable table;
	private DefaultTableModel model;
	
	private JTextField edtSousuo = new JTextField(5);
	private int number=1;
	
    String[] titles = { "编号", "地址","面积","房间数量","客厅数量","厨房数量","厕所数量","房主" };
	//private DefaultTableModel model = new DefaultTableModel(datas, titles);;
//	private JTable table = new JTable(model);;
	//private JScrollPane scroll = new JScrollPane(table);
	public FrmUpdHouse (Frame f, String s, boolean b) throws BaseException {
		super(f, s, b);
		
		this.setSize(500,500);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		//获取数据
		
		
		Object[][] newdatas=getDatas(number);
		 model = new DefaultTableModel(newdatas, titles);
		
		 table = new JTable(model);
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		
		
		//布局
		this.setLayout(new BorderLayout());
		panShang.setLayout(new FlowLayout(FlowLayout.LEFT));
		panXia.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panShang.add(jcb);
		panShang.add(edtSousuo);
		panShang.add(btnSousuo);
		panXia.add(btnCancel);
		panXia.add(btnConfirm);
		panZhong.add(scroll);
		btnSousuo.addActionListener(this);
		btnCancel.addActionListener(this);
		btnConfirm.addActionListener(this);
		this.add("North",panShang);
		this.add("Center",panZhong);
		this.add("South",panXia);	
		//添加数据
		
		
		
		
		this.validate();
		this.setVisible(true);
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btnSousuo) {
				Object[][] newdatas;
				System.out.println("选中的："+jcb.getSelectedIndex());
				if(jcb.getSelectedIndex()==0) {
					try {
						number=2;
						newdatas=getDatas(number);
						DefaultTableModel model = new DefaultTableModel(newdatas, titles);
						
						JTable table = new JTable(model);
						
						JScrollPane scroll = new JScrollPane(table);
						scroll.setVerticalScrollBarPolicy(
								JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
						panZhong.removeAll();
						panZhong.add(scroll);
						panZhong.repaint();
						panZhong.updateUI();
					} catch (BaseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(jcb.getSelectedIndex()==1) {
					try {
						number=3;
						newdatas=getDatas(number);
						DefaultTableModel model = new DefaultTableModel(newdatas, titles);
						
						JTable table = new JTable(model);
						
						JScrollPane scroll = new JScrollPane(table);
						scroll.setVerticalScrollBarPolicy(
								JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
						panZhong.removeAll();
						panZhong.add(scroll);
						panZhong.repaint();
						panZhong.updateUI();
					} catch (BaseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}
					
				
				
				
				
				}
			}
		else if(e.getSource()==this.btnCancel){
			this.setVisible(false);
		}
		else if(e.getSource()==this.btnConfirm){
			int selectRows=table.getSelectedRows().length;// 取得用户所选行的行数
			if(selectRows==1){ 
			    int selectedRowIndex = table.getSelectedRow(); // 取得用户所选单行
			    int data=(int)model.getValueAt(selectedRowIndex, 0);
			    System.out.println("房子编号:"+data);
			    try {
			    	this.setVisible(false);
					FrmUpdHouse_room updHouse_room=new FrmUpdHouse_room(this,"选择房间",true,data);
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
			
		}
		
	}
	
	public Object[][] getDatas(int methon) throws BaseException{
		Object[][] datas = new Object[25][10];
		List<House> list=null;
		if(methon==1) {
			 list=startUtil.houseContr.getAllHouseByUser();

		}
		else if(methon==2) {
			list=startUtil.houseContr.getHouseById(Integer.parseInt(this.edtSousuo.getText()));
		}
		else if(methon==3) {
			list=startUtil.houseContr.getHouseByClientName(this.edtSousuo.getText());

		}
		for(int i=0;i<list.size();i++) {
			for(int j=0;j<8;j++) {
				switch (j)  
                {  
                case 0:  
                	datas[i][j]=list.get(i).getHouseId();

                    break;  
                case 1:  
                	datas[i][j]=list.get(i).getHouseAddress();
                    break;  
                case 2:  
                	datas[i][j]=list.get(i).getHouseTotalArea();
                    break;  
                case 3:  
                	datas[i][j]=(int)((list.get(i).getRoom()/1000)%10);
                    break;  
                case 4:  
                	datas[i][j]=(int)((list.get(i).getRoom()/100)%10);
                    break;  
                case 5:  
                	datas[i][j]=(int)((list.get(i).getRoom()/10)%10);
                    break;  
                case 6:  
                	datas[i][j]=(int)(list.get(i).getRoom()%10);
                    break;  
                case 7:  
                	Client c=startUtil.clientContr.getClientNameById(list.get(i).getHouseId());
                	datas[i][j]=c.getClientName();
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
		Object[][] newdatas = new Object[nulllenth][8];
		for(int i=0;i<nulllenth;i++) {
			for(int j=0;j<8;j++) {
				newdatas[i][j]=datas[i][j];
			}
		}
		return newdatas;
	}
	

}
