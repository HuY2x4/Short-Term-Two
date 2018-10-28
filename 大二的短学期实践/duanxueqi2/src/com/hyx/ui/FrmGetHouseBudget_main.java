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

public class FrmGetHouseBudget_main extends JDialog implements ActionListener{
	private JPanel panShang = new JPanel();
	private JPanel panZhong = new JPanel();
	private JPanel panXia = new JPanel();


	private JButton btnCancel = new JButton("返回");
	private JButton btnCailiao = new JButton("材料信息");
	private JButton btnFuwu = new JButton("服务信息");
	private JButton btnGetRoom = new JButton("查询各个房间的装修预算");
	
	private JLabel labelMB = new JLabel("材料总预算：");
	private JLabel labelSB = new JLabel("服务总预算");
	private JLabel labelAllB = new JLabel("装修总预算");
	private JLabel labelAllB1 = new JLabel(" ");
	private JLabel labelMB1 = new JLabel(" ");
	private JLabel labelSB1 = new JLabel(" ");

	
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private int houseID;
	private int state=0;
	
 //   String[] titles = { "房间编号", "房间面积","房子编号","房间结构","备注"};
	//private DefaultTableModel model = new DefaultTableModel(datas, titles);;
//	private JTable table = new JTable(model);;
	//private JScrollPane scroll = new JScrollPane(table);
	public FrmGetHouseBudget_main (FrmGetHouseBudget_getHouse frmGetHouseBudget_getHouse, String s, boolean b,int houseId) throws BaseException {
		super(frmGetHouseBudget_getHouse, s, b);
		houseID=houseId;
		this.setSize(600,600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		//获取数据
		int[] price=startUtil.materialBudgetContr.getAllBudgetOfHouse(houseID);

		labelAllB1 = new JLabel(Integer.toString(price[2]));
		labelMB1 = new JLabel(Integer.toString(price[0]));
		labelSB1 = new JLabel(Integer.toString(price[1]));

		//布局
		this.setLayout(new BorderLayout());
		panShang.setLayout(new FlowLayout(FlowLayout.CENTER));
		panXia.setLayout(new FlowLayout(FlowLayout.CENTER));
		
	//	panShang.add(label);
		panShang.add(btnGetRoom);
		panShang.add(btnCailiao);
		panShang.add(btnFuwu);
		
		panXia.add(btnCancel);
		panXia.add(labelMB);
		panXia.add(labelMB1);
		panXia.add(labelSB);
		panXia.add(labelSB1);
		panXia.add(labelAllB);
		panXia.add(labelAllB1);
	//	panZhong.add(scroll);
		btnCancel.addActionListener(this);
		btnCailiao.addActionListener(this);
		btnFuwu.addActionListener(this);
		btnGetRoom.addActionListener(this);
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
		else if(e.getSource()==this.btnGetRoom){
			try {
				FrmGetHouseBudget_getRoom getbudget=new FrmGetHouseBudget_getRoom(null,"选择房间",true,houseID);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
		Object[][] datas = new Object[25][15];
		
		if(methon==1) {
			state=1;
			String[] titles = { "ID", "材料ID","材料名","数量","单价","总价","房间ID","类型","备注"};
			List<Object[]> list=null;
			list=startUtil.materialBudgetContr.getAllMBOfHouse(houseID);
			
			for(int i=0;i<list.size();i++) {
				for(int j=0;j<9;j++) {
					datas[i][j]=list.get(i)[j];
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
		else if(methon==2) {
			state=2;
			String[] titles = { "ID", "服务ID","服务名","材料ID","材料名","数量","时间","总价格","房间ID","类型","备注"};
			List<Object[]> list=null;
			list=startUtil.serviceBudgetContr.getAllSBOfHouse(houseID);
			System.out.println("3");
			for(int i=0;i<list.size();i++) {
			for(int j=0;j<11;j++) {
				
				datas[i][j]=list.get(i)[j];
				
				}

			}
			

			
			int nulllenth=0;
			for(int i=0;i<datas.length;i++) {
				if(datas[i][0]==null) {
					nulllenth=i;
					break;
				}
				

			}
			
			Object[][] newdatas = new Object[nulllenth][11];
			for(int i=0;i<nulllenth;i++) {
				for(int j=0;j<11;j++) {
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


