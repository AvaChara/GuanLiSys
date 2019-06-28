package com.fangrongfu.chaxunguanli;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.fangrongfu.dengluzhuceFrame.LoginDialog;
import com.fangrongfu.gonggong.Dao;
import com.fangrongfu.renyuanxinxiguanli.CzryxxFrame;

public class CxkcxxFrame extends JFrame{

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

	JPanel p1;
	JPanel p2;
	JPanel p3;
	JPanel p4;
	JPanel p5;
	JPanel p6;

	JTable jhdTable;//显示表格
	JScrollPane jhdSrollPane;//滚动面板
	DefaultTableModel model;//表格的数据模型，操作数据

	JComboBox<String> jc;

	public CxkcxxFrame() {

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
								String Str=new String(idField.getText());
								jhdTable.setModel(Dao.cxkcxx(Str));
								JOptionPane.showMessageDialog(CxkcxxFrame.this, "查询信息成功","提示", JOptionPane.INFORMATION_MESSAGE);
								//}
							}catch(Exception e1) {
								e1.printStackTrace();
							}

							//setVisible(false);
						}
					});
				}

				Dao.chaxunkcrecord(LoginDialog.userid,"null");//增加查询库存信息操作的历史记录

			}

		});

		querenButton=new JButton("确认");//“确认”按钮
		querenButton.setBounds(30, 100, 60, 25);


		quxiaoButton=new JButton("取消");//“取消”按钮
		quxiaoButton.setBounds(100, 100, 60, 25);
		quxiaoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int result=JOptionPane.showConfirmDialog(CxkcxxFrame.this, "是否确认退出？", "提示", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					CxkcxxFrame.this.dispose();
				}
				else if(result==JOptionPane.NO_OPTION){
					CxkcxxFrame.this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}

		});

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

		//model = new DefaultTableModel(tableValues,columnNames);

		jhdTable=new JTable();//创建指定列名和数据的表格
		//jhdTable.setModel(Dao.cxkcxx(Str));
		jhdSrollPane=new JScrollPane(jhdTable);//创建显示表格的滚动面板
		//jhdSrollPane.add(jhdTable);//这是错误的写法
		jhdSrollPane.setBounds(180, 10, 560, 120);

		this.setLayout(null);//绝对布局

		this.add(p1);this.add(p6);//this.add(p2);this.add(p3);this.add(p4);this.add(p5);
		this.add(querenButton);this.add(quxiaoButton);
		this.add(jhdSrollPane);

		this.addWindowListener(new WindowAdapter(){//为了关闭窗口
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//子框关闭，父框不关闭
			}
		});

		this.setBounds(300, 100, 768, 165);
		this.setTitle("查询库存信息界面");
		this.setIconImage(getToolkit().getImage(getClass().getResource("/resources/chaxunkc.PNG")));
		this.setResizable(false);
		this.setVisible(true);
	}

}
