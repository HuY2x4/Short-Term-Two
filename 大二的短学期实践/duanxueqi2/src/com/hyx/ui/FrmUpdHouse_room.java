package com.hyx.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.hyx.startUtil;
import com.hyx.model.House;
import com.hyx.model.Room;
import com.hyx.util.BaseException;

public class FrmUpdHouse_room extends JDialog implements ActionListener{
	private JPanel panShang = new JPanel();
	private JPanel panZhong = new JPanel();
	private JPanel panXia = new JPanel();
	private JButton btnUpd = new JButton("修改房间");
	private JButton btnConfirm = new JButton("选择该房间");
	private JButton btnRe = new JButton("刷新");
	private JButton btnCancel = new JButton("取消");
	
	private JLabel label = new JLabel("请选择房间");
	JScrollPane scroll;
	private JTable table;
	private DefaultTableModel model;
	int houseID=0;
	
    String[] titles = { "房间编号", "房间面积","房子编号","房间结构","备注"};
	//private DefaultTableModel model = new DefaultTableModel(datas, titles);;
//	private JTable table = new JTable(model);;
	//private JScrollPane scroll = new JScrollPane(table);
	public FrmUpdHouse_room (FrmUpdHouse frmUpdHouse, String s, boolean b,int houseId) throws BaseException {
		super(frmUpdHouse, s, b);
		houseID=houseId;
		this.setSize(500,250);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		//获取数据
		
		
		Object[][] newdatas=getDatas(houseId);
		 model = new DefaultTableModel(newdatas, titles);
		
		 table = new JTable(model);
		
		 scroll = new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		
		
		//布局
		this.setLayout(new BorderLayout());
		panShang.setLayout(new FlowLayout(FlowLayout.LEFT));
		panXia.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panShang.add(label);
		panXia.add(btnCancel);
		panXia.add(btnUpd);
		panXia.add(btnRe);
		panXia.add(btnConfirm);
		
		panZhong.add(scroll);
		btnCancel.addActionListener(this);
		btnRe.addActionListener(this);
		btnUpd.addActionListener(this);
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
		
		if(e.getSource()==this.btnCancel){
			this.setVisible(false);
		}
		else if(e.getSource()==this.btnConfirm){
			int selectRows=table.getSelectedRows().length;// 取得用户所选行的行数
			if(selectRows==1){ 
			    int selectedRowIndex = table.getSelectedRow(); // 取得用户所选单行
			    int data=(int)model.getValueAt(selectedRowIndex, 0);
			    this.setVisible(false);
			    try {
					FrmUpdHouse_main f=new FrmUpdHouse_main(null,"房间信息",true,data);
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
			
		}
		else if(e.getSource()==this.btnUpd){
			int selectRows=table.getSelectedRows().length;// 取得用户所选行的行数
			if(selectRows==1){ 
			    int selectedRowIndex = table.getSelectedRow(); // 取得用户所选单行
			    int data=(int)model.getValueAt(selectedRowIndex, 0);
			    FrmUpdRoom f=new FrmUpdRoom(null,"房间信息",true,data);
			} 
			
		}
		else if(e.getSource()==this.btnRe){
			Object[][] newdatas=new Object[10][10];
			try {
				newdatas= getDatas(houseID);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
	
	public Object[][] getDatas(int houseId) throws BaseException{
		Object[][] datas = new Object[25][10];
		List<Room> list=null;
		 list=startUtil.roomContr.getRoomByHouseId(houseId);
		 String strSort="";
			int rs=0;
		
		for(int i=0;i<list.size();i++) {
			for(int j=0;j<5;j++) {
				switch (j)  
                {  
                case 0:  
                	datas[i][j]=list.get(i).getRoomId();

                    break;  
                case 1:  
                	datas[i][j]=list.get(i).getRoomArea();
                    break;  
                case 2:  
                	datas[i][j]=list.get(i).getHouseId();
                    break;  
                case 3:  
                	rs=list.get(i).getRoomSort();
                	if(rs==1) {
            			strSort="房间";
            		}
            		else if(rs==2) {
            			strSort="客厅";
            		}
            		else if(rs==3) {
            			strSort="厨房";
            		}
            		else if(rs==4) {
            			strSort="卫生间";
            		}
                	datas[i][j]=strSort;
                	
                    break;  
                case 4:  
                	datas[i][j]=list.get(i).getRoomRemark();
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
			for(int j=0;j<5;j++) {
				newdatas[i][j]=datas[i][j];
			}
		}
		return newdatas;
	}
	

}
