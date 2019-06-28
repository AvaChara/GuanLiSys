package com.fangrongfu.chuhuoguanli;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.fangrongfu.chaxunguanli.CxkcxxFrame;
import com.fangrongfu.chaxunguanli.MyComboBox;
import com.fangrongfu.gonggong.Dao;
import com.fangrongfu.jinhuoguanli.JinhuodanFrame;
import com.fangrongfu.chuhuoguanli.MyButtonRender;

public class ChuhuoFrame extends JFrame{

	JLabel idLabel;//“商品编号”标签
	JLabel nameLabel;//“商品名称”标签
	JLabel statueLabel;//“商品状态”标签
	JLabel typeLabel;//“商品状态”标签
	JLabel produceLabel;//“供应商”标签
	JLabel jlLabel;//选择列表标签

	JTextField idField;//“商品编号”文本框
	JTextField nameField;//“商品名称”文本框
	JTextField statueField;//“商品状态”文本框
	JTextField typeField;//“商品类型”文本框
	JTextField produceField;//“供应商”文本框

	JButton querenButton;//“确认”按钮
	JButton quxiaoButton;//“取消”按钮
	JButton wanchengButton;//“完成”按钮

	JPanel p1;
	JPanel p6;


	JTable jhdTable;//显示表格
	JScrollPane jhdSrollPane;//滚动面板
	DefaultTableModel model;//表格的数据模型，操作数据

	JComboBox<String> jc;//下拉列表

	SelectionListener listener;

	String Str;

	Vector tableValues = new Vector();//出货单信息，应用在出货单信息窗口

	public static String outid;//全局变量，出货单ID

	public ChuhuoFrame() {

		idLabel=new JLabel("商品编号");//“进货单编号”标签
		nameLabel=new JLabel("商品名称");//“商品信息”标签
		statueLabel=new JLabel("商品状态");//“商品状态”标签
		typeLabel=new JLabel("商品类型");//“商品类型”标签
		produceLabel=new JLabel("供应商");//“供应商”标签
		jlLabel=new JLabel("请选择：");//选择列表标签

		idField=new JTextField(10);
		nameField=new JTextField(10);
		statueField=new JTextField(10);
		typeField=new JTextField(10);
		produceField=new JTextField(10);

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
								model=Dao.cxkcxx1(Str);
								jhdTable.setModel(model);
								jhdTable.getColumnModel().getColumn(9).setCellRenderer(new MyButtonRender());//只有获取到表格的列后才能添加渲染器。

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

										jhdTable.getColumnModel().getColumn(9).setCellEditor(new MyButtonEditor(id,num,jhdTable,Str,tableValues));//修改表格的默认编辑器

									}
								});


								JOptionPane.showMessageDialog(ChuhuoFrame.this, "查询商品信息成功","提示", JOptionPane.INFORMATION_MESSAGE);

							}catch(Exception e1) {
								e1.printStackTrace();
							}

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

				int result=JOptionPane.showConfirmDialog(ChuhuoFrame.this, "是否确认退出？", "提示", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					ChuhuoFrame.this.dispose();
				}
				else if(result==JOptionPane.NO_OPTION){
					ChuhuoFrame.this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}

		});

		wanchengButton=new JButton("完成");//“完成”按钮
		wanchengButton.setBounds(440, 140, 60, 25);
		wanchengButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(ChuhuoFrame.this, "生成出货单成功","提示", JOptionPane.INFORMATION_MESSAGE);

				//此时自动生成一个出货单号
				Random rand = new Random();
				outid=CreateId.test(rand.nextInt(9999));

				//编写用于打开出货单窗口的代码
				ChuhuodanFrame chuhuodanf=new ChuhuodanFrame(tableValues,outid);
				chuhuodanf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				chuhuodanf.setVisible(true);


			}
		});



		p1=new JPanel();
		p1.add(idLabel);p1.add(idField);
		p1.setLayout(new FlowLayout());
		p1.setBounds(0, 50, 180, 32);
		p1.setVisible(false);


		p6=new JPanel();
		p6.add(jlLabel);p6.add(jc);
		p6.setLayout(new FlowLayout());
		p6.setBounds(20, 10, 150, 32);


		jhdTable=new JTable();//创建指定列名和数据的表格
		jhdTable.setRowHeight(32);//设置标体的高度

		jhdSrollPane=new JScrollPane(jhdTable);//创建显示表格的滚动面板
		jhdSrollPane.setBounds(180, 10, 600, 120);

		this.setLayout(null);//绝对布局

		this.add(p6);this.add(p1);//this.add(p2);this.add(p3);this.add(p4);this.add(p5);//this.add(p7);
		this.add(querenButton);this.add(quxiaoButton);this.add(wanchengButton);//this.add(shuaxinButton);
		this.add(jhdSrollPane);

		this.addWindowListener(new WindowAdapter(){//为了关闭窗口
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//子框关闭，父框不关闭
			}
		});

		this.setBounds(300, 100, 808, 200);
		this.setTitle("出货界面");
		this.setIconImage(getToolkit().getImage(getClass().getResource("/resources/blue.PNG")));
		this.setResizable(false);
		this.setVisible(true);
	}

}
