package com.fangrongfu.jinhuoguanli;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.fangrongfu.chuhuoguanli.CreateId;
import com.fangrongfu.gonggong.Dao;
import com.fangrongfu.shangpinxinxiguanli.ZjspxxFrame;
import com.fangrongfu.zaikuguanli.JihuoFrame;
import com.fangrongfu.zaikuguanli.JihuodanFrame;
import com.fangrongfu.zaikuguanli.MyButtonEditor2;
import com.fangrongfu.zaikuguanli.MyButtonRender2;

public class JinhuoFrame extends JFrame {

	JLabel idLabel;//“商品编号”标签
	//JLabel nameLabel;//“商品名称”标签
	//JLabel statueLabel;//“商品状态”标签
	//JLabel typeLabel;//“商品状态”标签
	//JLabel produceLabel;//“供应商”标签
	//JLabel jlLabel;//选择列表标签
	//JLabel numberLabel;//“出货数量”标签

	JTextField idField;//“商品编号”文本框
	//JTextField nameField;//“商品名称”文本框
	//JTextField statueField;//“商品状态”文本框
	//JTextField typeField;//“商品类型”文本框
	//JTextField produceField;//“供应商”文本框
	//JTextField numberField;//“出货数量”文本框

	JButton querenButton;//“确认”按钮
	JButton quxiaoButton;//“取消”按钮
	JButton wanchengButton;//“完成”按钮
	JButton tianjiaButton;//“添加新商品”按钮

	JPanel p1;

	JTable jhdTable;//显示表格
	JScrollPane jhdSrollPane;//滚动面板
	DefaultTableModel model;//表格的数据模型，操作数据

	Vector tableValues = new Vector();//出货单信息，应用在出货单信息窗口

	String Str;

	public static String inid;//全局变量，进货单号

	public JinhuoFrame() {

		idLabel=new JLabel("商品编号");//“进货单编号”标签

		idField=new JTextField(10);

		querenButton=new JButton("确认");//“确认”按钮
		querenButton.setBounds(30, 80, 60, 25);
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
					model=Dao.cxkcxx1(Str);
					if(model.getRowCount()!=0) {
						jhdTable.setModel(model);

						//jhdTable.setRowSelectionAllowed(false);//设置禁止行选中



						jhdTable.getColumnModel().getColumn(9).setCellRenderer(new MyButtonRender3());//只有获取到表格的列后才能添加渲染器。
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

								jhdTable.getColumnModel().getColumn(9).setCellEditor(new MyButtonEditor3(id,num,jhdTable,Str,tableValues));//修改表格的默认编辑器


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


						JOptionPane.showMessageDialog(JinhuoFrame.this, "查询商品信息成功","提示", JOptionPane.INFORMATION_MESSAGE);
						//}
					}else {

						JOptionPane.showMessageDialog(JinhuoFrame.this, "查询商品信息失败，库存没有该类商品信息，请完善该类商品信息","提示", JOptionPane.INFORMATION_MESSAGE);

					}


				}catch(Exception e1) {
					e1.printStackTrace();
				}

				//setVisible(false);
			}
		});


		quxiaoButton=new JButton("取消");//“取消”按钮
		quxiaoButton.setBounds(100, 80, 60, 25);
		quxiaoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int result=JOptionPane.showConfirmDialog(JinhuoFrame.this, "是否确认退出？", "提示", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					JinhuoFrame.this.dispose();
				}
				else if(result==JOptionPane.NO_OPTION){
					JinhuoFrame.this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}

		});

		tianjiaButton=new JButton("添加新商品");//“添加新商品”按钮
		tianjiaButton.setBounds(45, 110, 100, 25);
		tianjiaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				//编写用于打开添加新商品窗口的代码
				ZjspxxFrame zf = new ZjspxxFrame();
				zf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				zf.setVisible(true);
			}

		});

		wanchengButton=new JButton("完成");//“完成”按钮
		wanchengButton.setBounds(440, 140, 60, 25);
		wanchengButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(JinhuoFrame.this, "生成进货信息核对表成功","提示", JOptionPane.INFORMATION_MESSAGE);

				//此时自动生成一个出货单号
				Random rand = new Random();
				inid=CreateId.test(rand.nextInt(9999));

				//编写用于打开进货信息核对窗口的代码
				JinhuodanFrame jinhuodanf=new JinhuodanFrame(tableValues,inid);
				jinhuodanf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jinhuodanf.setVisible(true);

			}
		});

		p1=new JPanel();
		p1.add(idLabel);p1.add(idField);
		p1.setLayout(new FlowLayout());
		p1.setBounds(0, 30, 180, 32);
		//p1.setVisible(false);

		jhdTable=new JTable();//创建指定列名和数据的表格
		jhdTable.setRowHeight(32);//设置标体的高度
		//jhdTable.getColumnModel().getColumn(9).setCellRenderer(new MyButtonRender());//只有获取到表格的列后才能添加渲染器。
		//jhdTable.setModel(Dao.cxkcxx(Str));
		jhdSrollPane=new JScrollPane(jhdTable);//创建显示表格的滚动面板
		//jhdSrollPane.add(jhdTable);//这是错误的写法
		jhdSrollPane.setBounds(180, 10, 600, 120);

		this.setLayout(null);//绝对布局

		this.add(p1);
		this.add(querenButton);this.add(quxiaoButton);this.add(wanchengButton);this.add(tianjiaButton);
		this.add(jhdSrollPane);

		this.addWindowListener(new WindowAdapter(){//为了关闭窗口
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//子框关闭，父框不关闭
			}
		});

		this.setBounds(300, 100, 808, 200);
		this.setTitle("进货界面");
		this.setIconImage(getToolkit().getImage(getClass().getResource("/resources/yellow.PNG")));
		this.setResizable(false);
		this.setVisible(true);

	}

}
