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
import javax.swing.table.TableColumn;

import com.hyx.startUtil;
import com.hyx.model.MSInfo;
import com.hyx.model.Material;
import com.hyx.model.MaterialBudget;
import com.hyx.model.Room;
import com.hyx.model.Service;
import com.hyx.model.ServiceBudget;
import com.hyx.util.BaseException;

public class FrmUpdHouse_main extends JDialog implements ActionListener{
	private JPanel panShang = new JPanel();
	private JPanel panZhong = new JPanel();
	private JPanel panXia = new JPanel();
	private JPanel panZuo = new JPanel();


	private JButton btnConfirm = new JButton("�޸����");
	private JButton btnCancel = new JButton("ȡ��");
	private JButton btnCailiao = new JButton("������Ϣ");
	private JButton btnFuwu = new JButton("������Ϣ");
	private JButton btnDelete = new JButton("ɾ����ǰѡ����");
	private JButton btnAdd = new JButton("����²���");
	
	private JLabel label = new JLabel("��ǰ�������Ϣ��                                      ");
	private JLabel labelZhu = new JLabel("ע��ɾ������ʱ�Ὣ��Ӧ����һ��ɾ��");
	private JLabel labelKong1 = new JLabel("                        ");
	private JLabel labelKong2 = new JLabel("                        ");

	
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private int roomID;
	private int state=0;
	
 //   String[] titles = { "������", "�������","���ӱ��","����ṹ","��ע"};
	//private DefaultTableModel model = new DefaultTableModel(datas, titles);;
//	private JTable table = new JTable(model);;
	//private JScrollPane scroll = new JScrollPane(table);
	public FrmUpdHouse_main (FrmUpdHouse frmUpdHouse, String s, boolean b,int roomId) throws BaseException {
		super(frmUpdHouse, s, b);
		roomID=roomId;
		this.setSize(600,300);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		//��ȡ����
		
		//����
		this.setLayout(new BorderLayout());
		panShang.setLayout(new FlowLayout(FlowLayout.CENTER));
		panXia.setLayout(new FlowLayout(FlowLayout.CENTER));
		
	//	panShang.add(label);
	//	panShang.add(labelZhu);
		panShang.add(btnCailiao);
		panShang.add(btnFuwu);
		
		panXia.add(btnCancel);
		panXia.add(btnDelete);
		panXia.add(btnAdd);
		panXia.add(btnConfirm);
	//	panZhong.add(scroll);
		btnCancel.addActionListener(this);
		btnConfirm.addActionListener(this);
		btnCailiao.addActionListener(this);
		btnFuwu.addActionListener(this);
		btnDelete.addActionListener(this);
		btnAdd.addActionListener(this);
		this.add("North",panShang);
		this.add("Center",panZhong);
		this.add("South",panXia);	
		this.add("West",panZuo);	

		//�������
		getDatas(1,roomId);
		
		
		
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
			this.setVisible(false);
		}
		else if(e.getSource()==this.btnCailiao){
			try {
				getDatas(1,roomID);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if(e.getSource()==this.btnFuwu){
			try {
				getDatas(2,roomID);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if(e.getSource()==this.btnDelete){
			
			int selectRows=table.getSelectedRows().length;// ȡ���û���ѡ�е�����
			if(selectRows==1){ 
			    int selectedRowIndex = table.getSelectedRow(); // ȡ���û���ѡ����
			    int data=(int)model.getValueAt(selectedRowIndex, 0);
			    try {
			    	if(state==1) {
			    		if(startUtil.serviceBudgetContr.hasSBOfRoom(data)) {
			    			JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�����ɾ����Ӧ�ķ���"); 
			    		}
			    		else {
			    			startUtil.materialBudgetContr.deleteMBOfRoom(data);
			    			getDatas(1,roomID);
			    			JOptionPane.showMessageDialog(null, "ɾ���ɹ�"); 
			    		}
			    		
			    	}
			    	else if(state==2) {
			    		startUtil.serviceBudgetContr.deleteSBOfRoom(data,roomID);
			    		getDatas(2,roomID);
			    		JOptionPane.showMessageDialog(null, "ɾ���ɹ�"); 
			    	}
					
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
			
		}
		
		else if(e.getSource()==this.btnAdd){
			try {

				FrmUpdHouse_addM addS=new FrmUpdHouse_addM(null,"������Ϣ",true,roomID);

			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	
	public void getDatas(int methon,int roomId) throws BaseException{
		Object[][] datas = new Object[25][10];
		if(methon==1) {
			state=1;
			String[] titles = { "ID", "����ID","������","����ID","����","����","�ܼ�"};
			List<MaterialBudget> list=null;
			list=startUtil.materialBudgetContr.getAllMBOfRoom(roomId);
			
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
			String[] titles = { "ID", "����ID","������","����ID","������","����","ʱ��","�ܼ۸�","����ID","��ע"};
			List<ServiceBudget> list=null;
			list=startUtil.serviceBudgetContr.getAllSBOfRoom(roomId);
			
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

