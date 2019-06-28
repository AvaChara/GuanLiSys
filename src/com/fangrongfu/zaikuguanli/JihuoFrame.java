package com.fangrongfu.zaikuguanli;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.fangrongfu.chaxunguanli.MyComboBox;
import com.fangrongfu.chuhuoguanli.CreateId;
import com.fangrongfu.chuhuoguanli.SelectionListener;
import com.fangrongfu.gonggong.Dao;

/**
 * 积货管理窗口
 * @author frf
 *
 */

public class JihuoFrame extends JFrame{

	JLabel idLabel;//“商品编号”标签
	JLabel nameLabel;//“商品名称”标签
	JLabel statueLabel;//“商品状态”标签
	JLabel typeLabel;//“商品状态”标签
	JLabel produceLabel;//“供应商”标签
	JLabel jlLabel;//选择列表标签
	//JLabel numberLabel;//“出货数量”标签

	JTextField idField;//“商品编号”文本框
	JTextField nameField;//“商品名称”文本框
	JTextField statueField;//“商品状态”文本框
	JTextField typeField;//“商品类型”文本框
	JTextField produceField;//“供应商”文本框
	//JTextField numberField;//“出货数量”文本框

	JButton querenButton;//“确认”按钮
	JButton quxiaoButton;//“取消”按钮
	JButton wanchengButton;//“完成”按钮
	JButton shuaxinButton;//“刷新”按钮

	JPanel p1;
	JPanel p2;
	JPanel p3;
	JPanel p4;
	JPanel p5;
	JPanel p6;
	JPanel p7;

	/*String[] columnNames= {"编号","状态","名称","类别","生产日期","有效日期","供应商","单价","数量"};//定义表格列名数组
	String[][] tableValues= {{"A1","B1","C1","D1","E1","F1","G1","H1","I1"},
			{"A1","B1","C1","D1","E1","F1","G1","H1","I1"},
			{"A1","B1","C1","D1","E1","F1","G1","H1","I1"},
			{"A1","B1","C1","D1","E1","F1","G1","H1","I1"},
			{"A1","B1","C1","D1","E1","F1","G1","H1","I1"},
			{"A1","B1","C1","D1","E1","F1","G1","H1","I1"},
			{"A1","B1","C1","D1","E1","F1","G1","H1","I1"},
			{"A1","B1","C1","D1","E1","F1","G1","H1","I1"}};//定义表格数据数组*/




	JTable jhdTable;//显示表格
	JScrollPane jhdSrollPane;//滚动面板
	DefaultTableModel model;//表格的数据模型，操作数据

	JComboBox<String> jc;//下拉列表

	SelectionListener listener;

	String Str;

	public static String surplusid;//全局变量，积货单号

	Vector tableValues = new Vector();//出货单信息，应用在出货单信息窗口

	public JihuoFrame() {
		/*Vector columnNames=new Vector();
		Vector rowValue1=new Vector();
		Vector tableValues=new Vector();

		columnNames.add("编号");
		columnNames.add("状态");
		columnNames.add("名称");
		columnNames.add("类别");
		columnNames.add("生产日期");
		columnNames.add("有效日期");
		columnNames.add("供应商");
		columnNames.add("单价");
		columnNames.add("数量");

		rowValue1.add("A1");
		rowValue1.add("B1");
		rowValue1.add("C1");
		rowValue1.add("D1");
		rowValue1.add("E1");
		rowValue1.add("F1");
		rowValue1.add("G1");
		rowValue1.add("H1");
		rowValue1.add("I1");

		Vector rowValue2=new Vector();

		rowValue2.add("A1");
		rowValue2.add("B1");
		rowValue2.add("C1");
		rowValue2.add("D1");
		rowValue2.add("E1");
		rowValue2.add("F1");
		rowValue2.add("G1");
		rowValue2.add("H1");
		rowValue2.add("I1");

		tableValues.add(rowValue1);
		tableValues.add(rowValue2);*/

		idLabel=new JLabel("商品编号");//“进货单编号”标签
		nameLabel=new JLabel("商品名称");//“商品信息”标签
		statueLabel=new JLabel("商品状态");//“商品状态”标签
		typeLabel=new JLabel("商品类型");//“商品类型”标签
		produceLabel=new JLabel("供应商");//“供应商”标签
		jlLabel=new JLabel("请选择：");//选择列表标签
		//numberLabel=new JLabel("出货数量");//“出货数量”标签

		idField=new JTextField(10);
		nameField=new JTextField(10);
		statueField=new JTextField(10);
		typeField=new JTextField(10);
		produceField=new JTextField(10);
		//numberField=new JTextField(15);

		jc=new JComboBox<>(new MyComboBox());
		jc.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					String s=(String)jc.getSelectedItem();
					if(s=="商品编号") {
						idLabel.setText("商品编号");
						idField.setText(null);
						p1.updateUI();
						p1.repaint();
						p1.setVisible(true);

					}else if(s=="商品名称") {
						idLabel.setText("商品名称");
						idField.setText(null);
						p1.updateUI();
						p1.repaint();
						p1.setVisible(true);

					}else if(s=="商品状态") {
						idLabel.setText("商品状态");
						idField.setText(null);
						p1.updateUI();
						p1.repaint();
						p1.setVisible(true);

					}else if(s=="商品类型") {
						idLabel.setText("商品类型");
						idField.setText(null);
						p1.updateUI();
						p1.repaint();
						p1.setVisible(true);

					}else {
						idLabel.setText("供应商");
						idField.setText(null);
						p1.updateUI();
						p1.repaint();
						p1.setVisible(true);

					}
					querenButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {

							try {
								UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//？
								Str=new String(idField.getText());

								//if(idStr==""||nameStr==""||statueStr==""||typeStr==""||dateStr==""||deadtimeStr==""||produceStr==""||moneyStr==""||numberStr=="") {
								//JOptionPane.showMessageDialog(ZjspxxFrame.this, "商品信息填写不完整","提示", JOptionPane.WARNING_MESSAGE);
								//}
								//if(idStr!=null&&nameStr!=null&&statueStr!=null&&typeStr!=null&&dateStr!=null&&deadtimeStr!=null&&produceStr!=null&&moneyStr!=null&&numberStr!=null) {
								//Dao.cxkcxx(Str);//函数功能：根据下拉列表选择的关键字来查询信息
								model=Dao.cxkcxx4(Str);
								jhdTable.setModel(model);

								//jhdTable.setRowSelectionAllowed(false);//设置禁止行选中



								jhdTable.getColumnModel().getColumn(9).setCellRenderer(new MyButtonRender2());//只有获取到表格的列后才能添加渲染器。
								//jhdTable.getColumnModel().getColumn(9).setCellEditor(new MyButtonEditor(jhdTable));//修改表格的默认编辑器

								//设置只能选中单行
								jhdTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

								ListSelectionModel selectionModel = jhdTable.getSelectionModel();// 取得jhdTable的ListSelectionModel.
								//给表格添加监听事件
								selectionModel.addListSelectionListener(new ListSelectionListener() {
									@Override
									public void valueChanged(ListSelectionEvent e) {
										//事件处理代码

										String id = null;
										String num = null;

										// 已取表格cell的index Array数据：获得选中行的行号，以及该行的列数
										int rows = jhdTable.getSelectedRow();//行号
										int columns = jhdTable.getSelectedColumn();//列数

										id=(String) jhdTable.getValueAt(rows, 0);//商品编号
										num=(String) jhdTable.getValueAt(rows, 8);//商品数量

										jhdTable.getColumnModel().getColumn(9).setCellEditor(new MyButtonEditor2(id,num,jhdTable,Str,tableValues));//修改表格的默认编辑器


	             				 				/*try {
													jhdTable.setModel(Dao.cxkcxx1(Str));//重新重数据库中获得商品表格信息
													jhdTable.getColumnModel().getColumn(9).setCellRenderer(new MyButtonRender());//只有获取到表格的列后才能添加渲染器。
													jhdTable.updateUI();//更新显示
												} catch (SQLException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}*/


										//编写用于打开进货单窗口的代码
	             								/*ChuhuoFrame1 chuhuof=new ChuhuoFrame1(jhdTable);
	             								chuhuof.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	             							    chuhuof.setVisible(true);*/
									}
								});

								//listener = new SelectionListener(jhdTable);//对表格的选定行设置监听事件
								//jhdTable.getSelectionModel().addListSelectionListener(listener);


								JOptionPane.showMessageDialog(JihuoFrame.this, "查询商品信息成功","提示", JOptionPane.INFORMATION_MESSAGE);
								//}

							}catch(Exception e1) {
								e1.printStackTrace();
							}

							//setVisible(false);
						}
					});

				}

			}

		});

		querenButton=new JButton("确认");//“确认”按钮
		querenButton.setBounds(30, 100, 60, 25);


		quxiaoButton=new JButton("取消");//“取消”按钮
		quxiaoButton.setBounds(100, 100, 60, 25);
		quxiaoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int result=JOptionPane.showConfirmDialog(JihuoFrame.this, "是否确认退出？", "提示", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					JihuoFrame.this.dispose();
				}
				else if(result==JOptionPane.NO_OPTION){
					JihuoFrame.this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}

		});

		wanchengButton=new JButton("完成");//“完成”按钮
		wanchengButton.setBounds(440, 140, 60, 25);
		wanchengButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(JihuoFrame.this, "生成积货单成功","提示", JOptionPane.INFORMATION_MESSAGE);

				//此时自动生成一个出货单号
				Random rand = new Random();
				surplusid=CreateId.test(rand.nextInt(9999));

				//编写用于打开积货单窗口的代码
				JihuodanFrame jihuodanf=new JihuodanFrame(tableValues,surplusid);
				jihuodanf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jihuodanf.setVisible(true);

			}
		});

		/*shuaxinButton=new JButton("刷新");//“刷新”按钮
		shuaxinButton.setBounds(400,140,60,25);
		shuaxinButton.addActionListener(new ActionListener() {

 			@Override
 			public void actionPerformed(ActionEvent e) {

 				try {
 					((DefaultTableModel) jhdTable.getModel()).getDataVector().clear(); //清空table的数据，重新写入；

 					DefaultTableModel newModel = new DefaultTableModel();

 					newModel =Dao.cxkcxx1(Str);

 					jhdTable.setModel(newModel);//重新在数据库中获得商品表格信息
 					jhdTable.getColumnModel().getColumn(9).setCellRenderer(new MyButtonRender());//只有获取到表格的列后才能添加渲染器。
 					jhdTable.validate();
 					jhdTable.updateUI();//更新显示
 				} catch (SQLException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				}



 				JOptionPane.showMessageDialog(ChuhuoFrame.this, "刷新成功","提示", JOptionPane.INFORMATION_MESSAGE);

 			}
 		});*/

		p1=new JPanel();
		p1.add(idLabel);p1.add(idField);
		p1.setLayout(new FlowLayout());
		p1.setBounds(0, 50, 180, 32);
		p1.setVisible(false);

		/*p2=new JPanel();
		p2.add(nameLabel);p2.add(nameField);
		p2.setLayout(new FlowLayout());
		p2.setBounds(0, 50, 180, 32);
		p2.setVisible(false);

		p3=new JPanel();
		p3.add(statueLabel);p3.add(statueField);
		p3.setLayout(new FlowLayout());
		p3.setBounds(0, 50, 180, 32);
		p3.setVisible(false);

		p4=new JPanel();
		p4.add(typeLabel);p4.add(typeField);
		p4.setLayout(new FlowLayout());
		p4.setBounds(0, 50, 180, 32);
		p4.setVisible(false);

		p5=new JPanel();
		p5.add(produceLabel);p5.add(produceField);
		p5.setLayout(new FlowLayout());
		p5.setBounds(0, 50, 180, 32);
		p5.setVisible(false);*/

		p6=new JPanel();
		p6.add(jlLabel);p6.add(jc);
		p6.setLayout(new FlowLayout());
		p6.setBounds(20, 10, 150, 32);

		//p7=new JPanel();
		//p7.add(numberLabel);p7.add(numberField);
		//p7.setLayout(new FlowLayout());
		//p7.setBounds(164, 138, 180, 32);

		//model = new DefaultTableModel(tableValues,columnNames);

		jhdTable=new JTable();//创建指定列名和数据的表格
		jhdTable.setRowHeight(32);//设置标体的高度
		//jhdTable.getColumnModel().getColumn(9).setCellRenderer(new MyButtonRender());//只有获取到表格的列后才能添加渲染器。
		//jhdTable.setModel(Dao.cxkcxx(Str));
		jhdSrollPane=new JScrollPane(jhdTable);//创建显示表格的滚动面板
		//jhdSrollPane.add(jhdTable);//这是错误的写法
		jhdSrollPane.setBounds(180, 10, 600, 120);

		this.setLayout(null);//绝对布局

		this.add(p1);this.add(p6);//this.add(p2);this.add(p3);this.add(p4);this.add(p5);//this.add(p7);
		this.add(querenButton);this.add(quxiaoButton);this.add(wanchengButton);//this.add(shuaxinButton);
		this.add(jhdSrollPane);

		this.addWindowListener(new WindowAdapter(){//为了关闭窗口
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//子框关闭，父框不关闭
			}
		});

		this.setBounds(300, 100, 808, 200);
		this.setTitle("积货界面");
		this.setIconImage(getToolkit().getImage(getClass().getResource("/resources/red.PNG")));
		this.setResizable(false);
		this.setVisible(true);
	}



}

