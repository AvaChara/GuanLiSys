package com.fangrongfu.shangpinxinxiguanli;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Pattern;

import javax.swing.*;

import com.fangrongfu.dengluzhuceFrame.LoginDialog;
import com.fangrongfu.gonggong.Chooser;
import com.fangrongfu.gonggong.Dao;
import com.fangrongfu.mainFrame.MainFrame;
import com.fangrongfu.renyuanxinxiguanli.MyComboBoxStatue;
import com.fangrongfu.zaikuguanli.JihuoFrame1;

/**
 * 增加商品信息窗口
 * @author frf
 *
 */

public class ZjspxxFrame extends JFrame{

	JLabel idLabel;//标签“商品编号”
	JLabel nameLabel;//标签“商品名称”
	JLabel statueLabel;//标签“商品状态”
	JLabel typeLabel;//标签“商品类型”
	JLabel dateLabel;//标签“生产日期”
	JLabel deadtimeLabel;//标签“有效日期”
	JLabel produceLabel;//标签“供应商”
	JLabel moneyLabel;//标签“商品价格”
	JLabel numberLabel;//标签“商品数量”

	JTextField idField;
	JTextField nameField;
	//JTextField statueField;
	JComboBox<String> statue;
	JTextField typeField;
	JTextField dateField;
	JTextField deadtimeField;
	JTextField produceField;
	JTextField moneyField;
	JTextField numberField;

	JPanel p1;
	JPanel p2;
	JPanel p3;
	JPanel p4;
	JPanel p5;
	JPanel p6;
	JPanel p7;
	JPanel p8;
	JPanel p9;

	JButton querenButton;//“确认”按钮
	JButton quxiaoButton;//“取消”按钮

	JButton xuanzeriqiButton;//选择日期按钮
	JButton xuanzeriqiButton2;//选择日期按钮

	public ZjspxxFrame() {
		idLabel=new JLabel("商品编号");//标签“商品编号”
		nameLabel=new JLabel("商品名称");//标签“商品名称”
		statueLabel=new JLabel("商品状态");//标签“商品状态”
		typeLabel=new JLabel("商品类型");//标签“商品类型”
		dateLabel=new JLabel("生产日期");//标签“生产日期”
		deadtimeLabel=new JLabel("有效日期");//标签“有效日期”
		produceLabel=new JLabel("供应商");//标签“供应商”
		moneyLabel=new JLabel("商品价格");//标签“商品价格”
		numberLabel=new JLabel("商品数量");//标签“商品数量”

		idField=new JTextField(10);
		nameField=new JTextField(10);
		//statueField=new JTextField(15);
		statue=new JComboBox<>(new MyComboBoxStatue());
		typeField=new JTextField(10);
		dateField=new JTextField(8);
		deadtimeField=new JTextField(8);
		produceField=new JTextField(10);
		moneyField=new JTextField(10);
		numberField=new JTextField(10);

		p1=new JPanel();
		p1.add(idLabel);p1.add(idField);
		p1.setLayout(new FlowLayout());
		p1.setBounds(20, 10, 200, 32);

		p2=new JPanel();
		p2.add(nameLabel);p2.add(nameField);
		p2.setLayout(new FlowLayout());
		p2.setBounds(20, 60, 200, 32);

		p3=new JPanel();
		p3.add(statueLabel);p3.add(statue);
		p3.setLayout(new FlowLayout());
		p3.setBounds(20, 110, 200, 32);

		p4=new JPanel();
		p4.add(typeLabel);p4.add(typeField);
		p4.setLayout(new FlowLayout());
		p4.setBounds(20, 160, 200, 32);

		p5=new JPanel();
		p5.add(dateLabel);p5.add(dateField);
		p5.setLayout(new FlowLayout());
		p5.setBounds(5, 210, 160, 32);

		p6=new JPanel();
		p6.add(deadtimeLabel);p6.add(deadtimeField);
		p6.setLayout(new FlowLayout());
		p6.setBounds(5, 260, 160, 32);

		p7=new JPanel();
		p7.add(produceLabel);p7.add(produceField);
		p7.setLayout(new FlowLayout());
		p7.setBounds(20, 310, 200, 32);

		p8=new JPanel();
		p8.add(moneyLabel);p8.add(moneyField);
		p8.setLayout(new FlowLayout());
		p8.setBounds(20, 360, 200, 32);

		p9=new JPanel();
		p9.add(numberLabel);p9.add(numberField);
		p9.setLayout(new FlowLayout());
		p9.setBounds(20, 410, 200, 32);

		querenButton=new JButton("确认");//“确认”按钮
		querenButton.setBounds(40, 460, 60, 25);
		querenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//？
					String idStr=new String(idField.getText());
					String nameStr=new String(nameField.getText());
					String statueStr=new String(statue.getSelectedItem().toString());
					String typeStr=new String(typeField.getText());
					String dateStr=new String(dateField.getText());
					String deadtimeStr=new String(deadtimeField.getText());
					String produceStr=new String(produceField.getText());

					String moneyStr=new String(moneyField.getText());
					String numberStr=numberField.getText();
					if(isNumeric(moneyStr)!=true||isNumeric(numberStr)!=true) {
						JOptionPane.showMessageDialog(ZjspxxFrame.this,"价格框或数量框输入的不是数字，请输入数字","错误",JOptionPane.ERROR_MESSAGE);
					}else {
						//if(idStr==""||nameStr==""||statueStr==""||typeStr==""||dateStr==""||deadtimeStr==""||produceStr==""||moneyStr==""||numberStr=="") {
						//JOptionPane.showMessageDialog(ZjspxxFrame.this, "商品信息填写不完整","提示", JOptionPane.WARNING_MESSAGE);
						//}
						//if(idStr!=null&&nameStr!=null&&statueStr!=null&&typeStr!=null&&dateStr!=null&&deadtimeStr!=null&&produceStr!=null&&moneyStr!=null&&numberStr!=null) {
						Dao.zjspxx(idStr,nameStr,statueStr,typeStr,dateStr,deadtimeStr,produceStr,moneyStr,numberStr);//函数功能：增加商品信息
						JOptionPane.showMessageDialog(ZjspxxFrame.this, "增加商品信息成功","提示", JOptionPane.INFORMATION_MESSAGE);
						Dao.zjsprecord(LoginDialog.userid,idStr);//增加添加商品信息操作的历史记录，要有所添加的商品id
						//}
						setVisible(false);
					}


				}catch(Exception e1) {
					e1.printStackTrace();
				}


			}
		});

		quxiaoButton=new JButton("取消");//“取消”按钮
		quxiaoButton.setBounds(130, 460, 60, 25);
		quxiaoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int result=JOptionPane.showConfirmDialog(ZjspxxFrame.this, "是否确认退出？", "提示", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					ZjspxxFrame.this.dispose();
				}
				else if(result==JOptionPane.NO_OPTION){
					ZjspxxFrame.this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}

		});

		xuanzeriqiButton=new JButton("选择");
		xuanzeriqiButton.setBounds(170, 212, 60, 25);
		xuanzeriqiButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame("时间选择");
				jf.setLayout(null);
				jf.setBounds(580, 160, 250, 300);

		        /*Chooser ser = Chooser.getInstance();
		        javax.swing.JTextField text = new JTextField();
		        text.setBounds(10, 10, 200, 30);
		        text.setText("2013-10-11");
		        ser.register(text);*/

				Chooser ser2 = Chooser.getInstance("yyyy-MM-dd");
				JLabel label = new JLabel("please click me.");
				label.setBounds(10, 10, 200, 30);
				ser2.register(label);

				JButton quedingButton = new JButton("确定");
				quedingButton.setBounds(87, 230, 60, 25);

				quedingButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						dateField.setText(label.getText());
						jf.setVisible(false);

					}

				});

				//jf.add(text);
				jf.add(label);
				jf.add(quedingButton);
				jf.setVisible(true);

				jf.addWindowListener(new WindowAdapter(){//为了关闭窗口
					public void windowClosing(WindowEvent e) {
						setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//子框关闭，父框不关闭
						//jf.setVisible(false);
					}
				});

			}

		});

		xuanzeriqiButton2=new JButton("选择");
		xuanzeriqiButton2.setBounds(170, 262, 60, 25);
		xuanzeriqiButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame("时间选择");
				jf.setLayout(null);
				jf.setBounds(580, 160, 250, 300);

		        /*Chooser ser = Chooser.getInstance();
		        javax.swing.JTextField text = new JTextField();
		        text.setBounds(10, 10, 200, 30);
		        text.setText("2013-10-11");
		        ser.register(text);*/

				Chooser ser2 = Chooser.getInstance("yyyy-MM-dd");
				JLabel label = new JLabel("please click me.");
				label.setBounds(10, 10, 200, 30);
				ser2.register(label);

				JButton quedingButton = new JButton("确定");
				quedingButton.setBounds(87, 230, 60, 25);

				quedingButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						deadtimeField.setText(label.getText());
						jf.setVisible(false);

					}

				});

				//jf.add(text);
				jf.add(label);
				jf.add(quedingButton);
				jf.setVisible(true);

				jf.addWindowListener(new WindowAdapter(){//为了关闭窗口
					public void windowClosing(WindowEvent e) {
						setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//子框关闭，父框不关闭
						//jf.setVisible(false);
					}
				});

			}

		});

		this.setLayout(null);//绝对布局
		this.add(p1);this.add(p2);this.add(p3);this.add(p4);this.add(p5);this.add(p6);this.add(p7);this.add(p8);this.add(p9);this.add(xuanzeriqiButton);this.add(xuanzeriqiButton2);
		this.add(querenButton);this.add(quxiaoButton);
		numberField.addKeyListener(new java.awt.event.KeyAdapter() {//按键“Enter”等于“确认”按钮
			public void keyTyped(java.awt.event.KeyEvent e) {
				if(e.getKeyChar()=='\n') {//如果按键字符是换行符"\n"
					querenButton.doClick();//执行“确认”按钮的doClick（）方法
				}
			}
		});

		this.addWindowListener(new WindowAdapter(){//为了关闭窗口
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//子框关闭，父框不关闭
			}
		});

		this.setBounds(580, 100, 245, 540);
		this.setTitle("增加商品信息界面");
		this.setIconImage(getToolkit().getImage(getClass().getResource("/resources/addgoods.PNG")));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}

	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

}
