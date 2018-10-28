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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.hyx.startUtil;
import com.hyx.model.Material;
import com.hyx.model.User;
import com.hyx.util.BaseException;


public class FrmMain extends JFrame implements ActionListener{
	
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_client=new JMenu("客户业务");
    private JMenu menu_select=new JMenu("查询业务");
    private JMenu menu_admin=new JMenu("管理员业务");
    private JMenu menu_other=new JMenu("其他业务");
    private JMenuItem  menuItem_addClient=new JMenuItem("添加客户信息");
    private JMenuItem  menuItem_updRoom=new JMenuItem("添加装修信息");
    private JMenuItem  menuItem_getMaterial=new JMenuItem("查询装修材料及服务");
    private JMenuItem  menuItem_getBudget=new JMenuItem("查询客户相关信息");
  //  private JMenuItem  menuItem_getClient=new JMenuItem("查询客户信息");
    private JMenuItem  menuItem_addMaterial=new JMenuItem("修改材料");
    private JMenuItem  menuItem_addSort=new JMenuItem("修改分类");
    private JMenuItem  menuItem_addBrand=new JMenuItem("修改品牌");
    private JMenuItem  menuItem_updUser=new JMenuItem("修改员工信息");
    private JMenuItem  menuItem_updPassword=new JMenuItem("修改密码");
    private JMenuItem  menuItem_logout=new JMenuItem("注销");
	FrmLogin flogin=null;
	private JPanel statusBar = new JPanel();
	private JPanel mainBar = new JPanel();
	
	
	
	private JPanel panShang = new JPanel();
	private JPanel panZhong = new JPanel();
	private JPanel panXia = new JPanel();

	private JButton btnConfirm = new JButton("查询对应服务");
	private JButton btnCancel = new JButton("返回");
	private JButton btnChaxun = new JButton("查询");
	private JButton btnSousuo = new JButton("搜索");
	private JButton btnRe = new JButton("刷新");
	private JLabel labelSousuo = new JLabel("按照材料名查询:");

	private JTextField edtSousuo = new JTextField(5);
	
	private String[] str1={"-全部类别-"};
	private JComboBox JBSort;
	private String[] str2={"-全部品牌-"};
	private JComboBox JBBrand;
	
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private int state=0;
	
	
	public FrmMain() throws BaseException {
		this.setSize(800, 600);
		this.setTitle("装修预算管理系统");
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
	    flogin =new FrmLogin(this, "登录", true);
	    flogin.setVisible(true);
	    this.menu_client.add(this.menuItem_addClient); this.menuItem_addClient.addActionListener(this);
	    this.menu_client.add(this.menuItem_updRoom); this.menuItem_updRoom.addActionListener(this);
	    this.menu_select.add(this.menuItem_getMaterial); this.menuItem_getMaterial.addActionListener(this);
	    this.menu_select.add(this.menuItem_getBudget); this.menuItem_getBudget.addActionListener(this);
	  //  this.menu_select.add(this.menuItem_getClient); this.menuItem_getClient.addActionListener(this);
	    this.menu_admin.add(this.menuItem_addMaterial); this.menuItem_addMaterial.addActionListener(this);
	    this.menu_admin.add(this.menuItem_updUser); this.menuItem_updUser.addActionListener(this);
	    this.menu_admin.add(this.menuItem_addSort); this.menuItem_addSort.addActionListener(this);
	    this.menu_admin.add(this.menuItem_addBrand); this.menuItem_addBrand.addActionListener(this);
	    this.menu_other.add(this.menuItem_updPassword); this.menuItem_updPassword.addActionListener(this);
	    this.menu_other.add(this.menuItem_logout); this.menuItem_logout.addActionListener(this);
		//
	    menubar.add(menu_client);
	    menubar.add(menu_select);
	    menubar.add(menu_admin);
	    menubar.add(menu_other);
	    this.setJMenuBar(menubar);
	   
	    str1=startUtil.sortContr.getAllSortName(1);
		str2=startUtil.brandContr.getAllBrandName(1);
		JBSort=new JComboBox(str1);
		JBBrand=new JComboBox(str2);
		//布局
		this.setLayout(new BorderLayout());
		panShang.setLayout(new FlowLayout(FlowLayout.CENTER));
		panXia.setLayout(new FlowLayout(FlowLayout.CENTER));

	
		panShang.add(JBSort);
		panShang.add(JBBrand);
		panShang.add(btnChaxun);
		panShang.add(labelSousuo);
		panShang.add(edtSousuo);
		panShang.add(btnSousuo);
		
	//	panXia.add(btnCancel);
		panXia.add(btnConfirm);
		panXia.add(btnRe);
	//	panZhong.add(scroll);
		btnCancel.addActionListener(this);
		btnConfirm.addActionListener(this);
		btnChaxun.addActionListener(this);
		btnSousuo.addActionListener(this);
		btnRe.addActionListener(this);
		this.add("North",panShang);
		this.add("Center",panZhong);
		this.add("South",panXia);	

		//添加数据
		getDatas(1);
	    
	    
	    
	    this.addWindowListener(new WindowAdapter(){   
	    	public void windowClosing(WindowEvent e){ 
	    		System.exit(0);
             }
        });
	    this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.menuItem_addClient){
			FrmAddClient addClient=new FrmAddClient(this,"添加客户信息",true);
			//dlg.setVisible(true);
			
		}
		else if(e.getSource()==this.menuItem_updRoom){
			try {
				FrmUpdHouse updHouse=new FrmUpdHouse(null,"添装修信息",true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//dlg.setVisible(true);
			
		}
		else if(e.getSource()==this.menuItem_getMaterial){
			try {
				FrmGetMS_M getmd_m=new FrmGetMS_M(null,"材料信息",true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//dlg.setVisible(true);
			
		}
		else if(e.getSource()==this.menuItem_getBudget){
			try {
				FrmGetHouseBudget_getHouse getbudget=new FrmGetHouseBudget_getHouse(null,"选择房子",true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//dlg.setVisible(true);
			
		}
		else if(e.getSource()==this.menuItem_updPassword){
			FrmUpdPassword upd=new FrmUpdPassword(null,"选择房子",true);
			
		}
		else if(e.getSource()==this.menuItem_addSort){
			if(User.currentLoginUser.getUserLevel()>2) {
				try {
				FrmAdmin_Sort upd=new FrmAdmin_Sort(null,"修改分类",true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
			else {
				JOptionPane.showMessageDialog(null, "当前用户无权限使用该功能"); 
			}
			
			
		}
		else if(e.getSource()==this.menuItem_addBrand){
			if(User.currentLoginUser.getUserLevel()>2) {
			try {
				FrmAdmin_Brand upd=new FrmAdmin_Brand(null,"修改品牌",true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}}
			else {
				JOptionPane.showMessageDialog(null, "当前用户无权限使用该功能"); 
			}
			
			
		}
		else if(e.getSource()==this.menuItem_addMaterial){
			if(User.currentLoginUser.getUserLevel()>1) {
			try {
				FrmUpdMS_M upd=new FrmUpdMS_M(null,"修改材料",true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}}
			else {
				JOptionPane.showMessageDialog(null, "当前用户无权限使用该功能"); 
			}
			
			
		}
		else if(e.getSource()==this.menuItem_updUser){
			if(User.currentLoginUser.getUserLevel()>2) {
			try {
				FrmUpdUser upd=new FrmUpdUser(null,"员工信息",true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}}
			else {
				JOptionPane.showMessageDialog(null, "当前用户无权限使用该功能"); 
			}
			
		}
		else if(e.getSource()==this.menuItem_logout){
			
			FrmLogin upd=new FrmLogin(this,"登录",true);
			upd.setVisible(true);
		}
		else if(e.getSource()==this.btnConfirm){
			try {
				int selectedRowIndex = table.getSelectedRow();
				FrmGetMS_S addS=new FrmGetMS_S(null,"服务信息",true,(int)model.getValueAt(selectedRowIndex, 0),String.valueOf(model.getValueAt(selectedRowIndex, 1)));

			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource()==this.btnChaxun){
			try {
				getDatas(1);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource()==this.btnSousuo){
			try {
				getDatas(2);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==this.btnRe){
			try {
				str1=startUtil.sortContr.getAllSortName(1);
				str2=startUtil.brandContr.getAllBrandName(1);
				JBSort=new JComboBox(str1);
				JBBrand=new JComboBox(str2);
				panShang.removeAll();
				panShang.add(JBSort);
				panShang.add(JBBrand);
				panShang.add(btnChaxun);
				panShang.add(labelSousuo);
				panShang.add(edtSousuo);
				panShang.add(btnSousuo);
				panShang.repaint();
				panShang.updateUI();
				getDatas(2);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}





public void getDatas(int methon) throws BaseException{
	Object[][] datas = new Object[25][10];
	String[] titles = { "材料编号", "材料名","分类","品牌","信息","型号","颜色","价格","单位"};
	List<Material> list=null;
	if(methon==1) {
		String sortName=String.valueOf(this.JBSort.getSelectedItem());
		String brandName=String.valueOf(this.JBBrand.getSelectedItem());
		if(this.JBSort.getSelectedIndex()==0&&this.JBBrand.getSelectedIndex()==0) {
			list=startUtil.materialContr.getAllMaterial();
			}
		else if(this.JBSort.getSelectedIndex()!=0&&this.JBBrand.getSelectedIndex()==0){
			list=startUtil.materialContr.getPartMaterialByS(sortName);
		}
		else if(this.JBSort.getSelectedIndex()==0&&this.JBBrand.getSelectedIndex()!=0){
			list=startUtil.materialContr.getPartMaterialByB(brandName);
		}
		else {
			list=startUtil.materialContr.getPartMaterialBySB(sortName, brandName);
		}
	}
	else if(methon==2) {
		list=startUtil.materialContr.getMaterialByName(this.edtSousuo.getText());
		
	}
	
	
	
	
	
	for(int i=0;i<list.size();i++) {
	for(int j=0;j<9;j++) {
			switch (j)  
            {  
            case 0:  
            	datas[i][j]=list.get(i).getMaterialId();

                break;  
            case 1:  
            	datas[i][j]=list.get(i).getMaterialName();
                break;  
            case 2:  
            	datas[i][j]=list.get(i).getSortName();
                break;  
            case 3:  
            	datas[i][j]=list.get(i).getBrandName();
                break;  
            case 4:  
            	datas[i][j]=list.get(i).getSpecification();
                break;  
            case 5:  
            	datas[i][j]=list.get(i).getVersion();
                break;  
            case 6:  
            	datas[i][j]=list.get(i).getColor();
                break;  
            case 7:  
            	datas[i][j]=list.get(i).getPrice();
                break;  
            case 8:  
            	datas[i][j]=list.get(i).getUnit();
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
		Object[][] newdatas = new Object[nulllenth][9];
		for(int i=0;i<nulllenth;i++) {
			for(int j=0;j<9;j++) {
				newdatas[i][j]=datas[i][j];
			}
		}
		
		model = new DefaultTableModel(newdatas, titles);
		
		table = new JTable(model);
		
		table.setSize(800, 400);
		table.setRowHeight(30);
		TableColumn column=table.getColumnModel().getColumn(2);
		column.setPreferredWidth(100);
		
		scroll = new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		panZhong.removeAll();
		panZhong.add(scroll);
		panZhong.repaint();
		panZhong.updateUI();
		
		
	
	
	 
	
	
	
	
}
}

