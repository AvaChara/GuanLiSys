package com.fangrongfu.chaxunguanli;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.fangrongfu.dengluzhuceFrame.LoginDialog;
import com.fangrongfu.gonggong.Dao;
import com.fangrongfu.jinhuoguanli.JinhuoFrame;
import com.fangrongfu.mainFrame.MainFrame;
import com.fangrongfu.renyuanxinxiguanli.CzryxxFrame;

public class ChaxunjhFrame extends JFrame{

	JLabel useridLabel;//“人员编号”标签
	JLabel shangpinidLabel;//“商品编号”标签
	JLabel dateLabel;//“操作日期”标签
	JLabel jlLabel;//选择列表标签

	JTextField useridField;//“人员编号”文本框
	JTextField shangpinidField;//“商品编号”文本框
	JTextField dateField;//“操作日期”文本框

	JButton querenButton;//“确认”按钮
	JButton quxiaoButton;//“取消”按钮

	JPanel p1;
	JPanel p2;
	JPanel p3;
	JPanel p4;

	/*String[] columnNames= {"编号","状态","名称","类别","生产日期","有效日期","供应商","单价","数量"};//定义表格列名数组
	String[][] tableValues= {{"A1","B1","C1","D1","E1","F1","G1","H1","I1"},
			{"A1","B1","C1","D1","E1","F1","G1","H1","I1"},
			{"A1","B1","C1","D1","E1","F1","G1","H1","I1"},
			{"A1","B1","C1","D1","E1","F1","G1","H1","I1"},
			{"A1","B1","C1","D1","E1","F1","G1","H1","I1"},
			{"A1","B1","C1","D1","E1","F1","G1","H1","I1"},
			{"A1","B1","C1","D1","E1","F1","G1","H1","I1"},
			{"A1","B1","C1","D1","E1","F1","G1","H1","I1"}};//定义表格数据数组*/




	JTable chrecordTable;//显示表格
	JScrollPane chrecordSrollPane;//滚动面板
	DefaultTableModel model;//表格的数据模型，操作数据

	JComboBox<String> jc;

	public ChaxunjhFrame() {
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

		useridLabel=new JLabel("人员编号");//“人员编号”标签
		shangpinidLabel=new JLabel("商品编号");//“商品编号”标签
		dateLabel=new JLabel("操作日期");//“操作日期”标签
		jlLabel=new JLabel("请选择：");//选择列表标签

		useridField=new JTextField(10);
		shangpinidField=new JTextField(10);
		dateField=new JTextField(10);

		jc=new JComboBox<>(new MyComboBoxch());
		jc.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					String s=(String)jc.getSelectedItem();
					if(s=="人员编号") {
						useridLabel.setText("人员编号");
						useridField.setText(null);
						p1.updateUI();
						p1.repaint();
						p1.setVisible(true);

					}else if(s=="商品编号") {
						useridLabel.setText("商品编号");
						useridField.setText(null);
						p1.updateUI();
						p1.repaint();
						p1.setVisible(true);

					}else {
						useridLabel.setText("操作日期");
						useridField.setText(null);
						p1.updateUI();
						p1.repaint();
						p1.setVisible(true);

					}
					querenButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {

							try {
								UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//？
								String Str=new String(useridField.getText());
								chrecordTable.setModel(Dao.cxjhrecord(Str));
								JOptionPane.showMessageDialog(ChaxunjhFrame.this, "查询进货历史记录成功","提示", JOptionPane.INFORMATION_MESSAGE);
								//}
							}catch(Exception e1) {
								e1.printStackTrace();
							}

						}
					});
				}

				Dao.chaxunjhrecord(LoginDialog.userid,"null");//查询进货历史记录操作的历史记录

			}

		});

		querenButton=new JButton("确认");//“确认”按钮
		querenButton.setBounds(30, 100, 60, 25);


		quxiaoButton=new JButton("取消");//“取消”按钮
		quxiaoButton.setBounds(100, 100, 60, 25);
		quxiaoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int result=JOptionPane.showConfirmDialog(ChaxunjhFrame.this, "是否确认退出？", "提示", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					ChaxunjhFrame.this.dispose();
				}
				else if(result==JOptionPane.NO_OPTION){
					ChaxunjhFrame.this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}

		});

		p1=new JPanel();
		p1.add(useridLabel);p1.add(useridField);
		p1.setLayout(new FlowLayout());
		p1.setBounds(0, 50, 180, 32);
		p1.setVisible(false);

		p4=new JPanel();
		p4.add(jlLabel);p4.add(jc);
		p4.setLayout(new FlowLayout());
		p4.setBounds(20, 10, 150, 32);

		//model = new DefaultTableModel(tableValues,columnNames);

		chrecordTable=new JTable();//创建指定列名和数据的表格
		//jhdTable.setModel(Dao.cxkcxx(Str));
		chrecordSrollPane=new JScrollPane(chrecordTable);//创建显示表格的滚动面板
		//jhdSrollPane.add(jhdTable);//这是错误的写法
		chrecordSrollPane.setBounds(180, 10, 560, 120);

		//设置鼠标监听事件，点击单据号单元格，弹出单据信息
		chrecordTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//事件处理代码
				//得到选中的行
				int row = chrecordTable.getSelectedRow();
				//得到table中有几列
				int column = chrecordTable.getSelectedColumn();
				//根据你想要的行和列去取值。方法如下：
				Object selectedValue = chrecordTable.getModel().getValueAt(row,column);
				//selectedValue就是你想要的值

				//ChuhuoFrame.outid=(String)selectedValue;
				if(column==2) {//选定人员编号
					//编写用于打开人员信息窗口的代码
					//使标题栏的风格也跟着一起改变...
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);

					//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {


							try {
								UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
								Renyuanxx renyuanxxf=new Renyuanxx(selectedValue.toString());
								renyuanxxf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								renyuanxxf.setVisible(true);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
							}
							//new MainFrame();

						}
					});

				}
				if(column==3) {//选定单据编号
					//编写用于打开进货单信息窗口的代码
					//使标题栏的风格也跟着一起改变...
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);

					//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {


							try {
								UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
								Jinhuodanxx jinhuoxxf=new Jinhuodanxx(selectedValue.toString());
								jinhuoxxf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								jinhuoxxf.setVisible(true);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
							}
							//new MainFrame();

						}
					});

				}

			}
		});

		this.setLayout(null);//绝对布局

		this.add(p1);this.add(p4);//this.add(p2);this.add(p3);
		this.add(querenButton);this.add(quxiaoButton);
		this.add(chrecordSrollPane);

		this.addWindowListener(new WindowAdapter(){//为了关闭窗口
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//子框关闭，父框不关闭
			}
		});

		this.setBounds(300, 100, 768, 165);
		this.setTitle("查询进货历史记录界面");
		this.setIconImage(getToolkit().getImage(getClass().getResource("/resources/chaxunjhls.PNG")));
		this.setResizable(false);
		this.setVisible(true);
	}

}
 