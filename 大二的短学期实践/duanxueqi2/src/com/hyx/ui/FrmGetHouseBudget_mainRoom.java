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
import com.hyx.util.BaseException;

public class FrmGetHouseBudget_mainRoom extends JDialog implements ActionListener{
	private JPanel panShang = new JPanel();
	private JPanel panZhong = new JPanel();
	private JPanel panXia = new JPanel();
	private JPanel panZuo = new JPanel();


	private JButton btnCancel = new JButton("返回");
	private JButton btnCailiao = new JButton("材料信息");
	private JButton btnFuwu = new JButton("服务信息");
	
	private JLabel label = new JLabel("当前房间的信息：");
	private JLabel labelB = new JLabel("材料预算：");
	private JLabel labelBP ;
	private JLabel labelS = new JLabel("服务预算：");
	private JLabel labelSP ;
	private JLabel labelSB = new JLabel("总预算：");
	private JLabel labelSBP ;

	
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private int roomID;
	private int state=0;
	
 //   String[] titles = { "房间编号", "房间面积","房子编号","房间结构","备注"};
	//private DefaultTableModel model = new DefaultTableModel(datas, titles);;
//	private JTable table = new JTable(model);;
	//private JScrollPane scroll = new JScrollPane(table);
	public FrmGetHouseBudget_mainRoom (FrmUpdHouse frmUpdHouse, String s, boolean b,int roomId) throws BaseException {
		super(frmUpdHouse, s, b);
		roomID=roomId;
		this.setSize(600,300);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		//获取数据
		int[] price=startUtil.materialBudgetContr.getAllBudgetOfRoom(roomID);
		labelBP = new JLabel(Integer.toString(price[0]));
		labelSP = new JLabel(Integer.toString(price[1]));
		labelSBP = new JLabel(Integer.toString(price[2]));
		//布局
		this.setLayout(new BorderLayout());
		panShang.setLayout(new FlowLayout(FlowLayout.CENTER));
		panXia.setLayout(new FlowLayout(FlowLayout.CENTER));
		
	//	panShang.add(label);
	//	panShang.add(labelZhu);
		panShang.add(btnCailiao);
		panShang.add(btnFuwu);
		
		panXia.add(btnCancel);
		panXia.add(labelB);
		panXia.add(labelBP);
		panXia.add(labelS);
		panXia.add(labelSP);
		panXia.add(labelSB);
		panXia.add(labelSBP);
	//	panZhong.add(scroll);
		btnCancel.addActionListener(this);
		btnCailiao.addActionListener(this);
		btnFuwu.addActionListener(this);

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
		}
		else if(e.getSource()==this.btnCailiao){
			try {
				getDatas(1);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if(e.getSource()==this.btnFuwu){
			try {
				getDatas(2);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		
	}
	
	public void getDatas(int methon) throws BaseException{
		Object[][] datas = new Object[25][10];
		if(methon==1) {
			state=1;
			String[] titles = { "ID", "材料ID","材料名","房间ID","数量","单价","总价"};
			List<MaterialBudget> list=null;
			list=startUtil.materialBudgetContr.getAllMBOfRoom(roomID);
			
			for(int i=0;i<list.size();i++) {
			for(int j=0;j<7;j++) {
				switch (j)  
                {  
                case 0:  
                	datas[i][j]=list.get(i).getMbId();

                    break;  
                case 1:  
                	datas[i][j]=list.get(i).getMaterialId();
                    break;  
                case 2:  
                	Material ma=startUtil.materialContr.getMaterial(list.get(i).getMaterialId());
                	datas[i][j]=ma.getMaterialName();
                    break;  
                case 3:  
                	datas[i][j]=list.get(i).getRoomId();
                    break;  
                case 4:  
                	datas[i][j]=list.get(i).getCount();
                    break;  
                case 5:  
                	datas[i][j]=list.get(i).getPrice();
                    break;  
                case 6:  
                	datas[i][j]=list.get(i).getTotalPrice();
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
				for(int j=0;j<7;j++) {
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
		else if(methon==2) {
			state=2;
			String[] titles = { "ID", "服务ID","服务名","材料ID","材料名","数量","时间","总价格","房间ID","备注"};
			List<ServiceBudget> list=null;
			list=startUtil.serviceBudgetContr.getAllSBOfRoom(roomID);
			
			for(int i=0;i<list.size();i++) {
			for(int j=0;j<10;j++) {
				switch (j)  
                {  
                case 0:  
                	datas[i][j]=list.get(i).getSbId();

                    break;  
                case 1:  
                	datas[i][j]=list.get(i).getServiceId();
                    break;  
                case 2:  
                	Service ma=startUtil.serviceContr.getService(list.get(i).getServiceId());
                	datas[i][j]=ma.getServiceName();
                    break;  
                case 3:  
                	MSInfo ms=startUtil.msInfoContr.getMSByServiceId(list.get(i).getServiceId());
                	datas[i][j]=ms.getMaterialId();
                    break;  
                case 4:  
                	MSInfo ms1=startUtil.msInfoContr.getMSByServiceId(list.get(i).getServiceId());
                	Material ma1=startUtil.materialContr.getMaterial(ms1.getMaterialId());
                	datas[i][j]=ma1.getMaterialName();
                    break;  
                case 5:  
                	datas[i][j]=list.get(i).getCount();
                    break;  
                case 6:  
                	datas[i][j]=list.get(i).getTime();
                    break;  
                case 7:  
                	datas[i][j]=list.get(i).getTotalPrice();
                    break;  
                case 8:  
                	datas[i][j]=list.get(i).getRoomId();
                    break;  
                case 9:  
                	datas[i][j]=list.get(i).getRemark();
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
			Object[][] newdatas = new Object[nulllenth][10];
			for(int i=0;i<nulllenth;i++) {
				for(int j=0;j<10;j++) {
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
	

}


