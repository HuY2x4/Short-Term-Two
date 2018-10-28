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
import com.hyx.model.MSBInfo;
import com.hyx.model.Material;
import com.hyx.model.MaterialBudget;
import com.hyx.model.Service;
import com.hyx.model.ServiceBudget;
import com.hyx.util.BaseException;

public class FrmUpdHouse_addS extends JDialog implements ActionListener{
	private JPanel panShang = new JPanel();
	private JPanel panZhong = new JPanel();
	private JPanel panXia = new JPanel();


	private JButton btnConfirm = new JButton("添加");
	private JButton btnCancel = new JButton("返回");
	
	private JLabel labelCount = new JLabel("数量:");
	private JLabel label = new JLabel("请选择服务：");


	private JTextField edtCount = new JTextField(3);

	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private int roomID;
	int mbID=0;
	private int state=0;
	

	public FrmUpdHouse_addS (FrmUpdHouse frmUpdHouse, String s, boolean b,int roomId,int materialId,int mbId) throws BaseException {
		
		super(frmUpdHouse, s, b);
	
		mbID=mbId;
		roomID=roomId;
		this.setSize(600,300);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		//获取数据
		edtCount.setText("1");
		//布局
		this.setLayout(new BorderLayout());
		panShang.setLayout(new FlowLayout(FlowLayout.CENTER));
		panXia.setLayout(new FlowLayout(FlowLayout.CENTER));
		panShang.add(label);
		panXia.add(btnCancel);
		panXia.add(labelCount);
		panXia.add(edtCount);
		panXia.add(btnConfirm);
		btnCancel.addActionListener(this);
		btnConfirm.addActionListener(this);
		this.add("North",panShang);
		this.add("Center",panZhong);
		this.add("South",panXia);	

		//添加数据
		getDatas(materialId);

		
		
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
			ServiceBudget sb=new ServiceBudget();
			int selectedRowIndex = table.getSelectedRow();
			
			sb.setCount(Integer.parseInt(this.edtCount.getText()));
			sb.setRemark("");
			sb.setRoomId(roomID);
			sb.setServiceId((int)model.getValueAt(selectedRowIndex, 0));
			
			sb.setTime((Float)((Float.valueOf(String.valueOf(model.getValueAt(selectedRowIndex, 6))))*Integer.parseInt(this.edtCount.getText())));

			int price1=(int)model.getValueAt(selectedRowIndex, 4);

			int count1=Integer.parseInt(this.edtCount.getText());

			sb.setTotalPrice(price1*count1);

			try {
				startUtil.serviceBudgetContr.addSBOfRoom(sb,mbID);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "添加成功"); 
		}
		
	
		
	}
	
	public void getDatas(int materialId) throws BaseException{
		Object[][] datas = new Object[25][10];
		String[] titles = { "服务编号", "服务名","服务内容","服务级别","价格","单位","时间"};
		List<Service> list=null;
		list=startUtil.serviceContr.getPartServiceByMa(materialId);
		
		
		for(int i=0;i<list.size();i++) {
		for(int j=0;j<7;j++) {
				switch (j)  
                {  
                case 0:  
                	datas[i][j]=list.get(i).getServiceId();

                    break;  
                case 1:  
                	datas[i][j]=list.get(i).getServiceName();
                    break;  
                case 2:  
                	datas[i][j]=list.get(i).getServiceContent();
                    break;  
                case 3:  
                	datas[i][j]=list.get(i).getServiceLevel();
                    break;  
                case 4:  
                	datas[i][j]=list.get(i).getPrice();
                    break;  
                case 5:  
                	datas[i][j]=list.get(i).getCount();
                    break;  
                case 6:  
                	datas[i][j]=list.get(i).getTime();
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
			Object[][] newdatas = new Object[nulllenth][7];
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
	

}

