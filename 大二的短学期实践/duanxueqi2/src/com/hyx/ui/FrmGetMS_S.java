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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.hyx.startUtil;
import com.hyx.model.Service;
import com.hyx.model.ServiceBudget;
import com.hyx.util.BaseException;

public class FrmGetMS_S extends JDialog implements ActionListener{
	private JPanel panShang = new JPanel();
	private JPanel panZhong = new JPanel();
	private JPanel panXia = new JPanel();


	private JButton btnCancel = new JButton("����");
	
	private JLabel label = new JLabel("����Ӧ������Ϣ��");



	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private int materialID;

	public FrmGetMS_S (FrmUpdHouse frmUpdHouse, String s, boolean b,int materialId ,String materialName) throws BaseException {
		
		super(frmUpdHouse, s, b);
		materialID=materialId;
		JLabel label1 = new JLabel(materialName);
		this.setSize(600,300);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		//��ȡ����
		
		//����
		this.setLayout(new BorderLayout());
		panShang.setLayout(new FlowLayout(FlowLayout.LEFT));
		panXia.setLayout(new FlowLayout(FlowLayout.CENTER));
		panShang.add(label1);
		panShang.add(label);
		panXia.add(btnCancel);
		
		btnCancel.addActionListener(this);
		this.add("North",panShang);
		this.add("Center",panZhong);
		this.add("South",panXia);	

		//�������
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
		
		
	
		
	}
	
	public void getDatas() throws BaseException{
		Object[][] datas = new Object[25][10];
		String[] titles = { "������", "������","��������","���񼶱�","�۸�","��λ","ʱ��"};
		List<Service> list=null;
		list=startUtil.serviceContr.getPartServiceByMa(materialID);
		
		
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


