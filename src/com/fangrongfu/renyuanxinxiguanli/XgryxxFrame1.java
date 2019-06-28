package com.fangrongfu.renyuanxinxiguanli;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.fangrongfu.dengluzhuceFrame.LoginDialog;
import com.fangrongfu.gonggong.Chooser;
import com.fangrongfu.gonggong.Dao;
import com.fangrongfu.shangpinxinxiguanli.XgspxxFrame1;

public class XgryxxFrame1 extends JFrame{

	JLabel idLabel;//标签“人员编号”
	JLabel nameLabel;//标签“人员姓名”
	JLabel sexLabel;//标签“人员性别”
	JLabel dateLabel;//标签“出生日期”
	JLabel phonenumberLabel;//标签“手机号码”
	JLabel developaddressLabel;//标签“邮箱地址”
	JLabel worktypeLabel;//标签“工作岗位”
	JLabel statueLabel;//标签“在职状态”

	JTextField idField;
	JTextField nameField;
	JTextField sexField;
	JTextField dateField;
	JTextField phonenumberField;
	JTextField developaddressField;
	JTextField worktypeField;
	JTextField statueField;

	JPanel p1;
	JPanel p2;
	JPanel p3;
	JPanel p4;
	JPanel p5;
	JPanel p6;
	JPanel p7;
	JPanel p8;

	JButton querenButton;//“确认”按钮
	JButton quxiaoButton;//“取消”按钮

	JButton xuanzeriqiButton;//选择日期按钮

	public XgryxxFrame1(String id,String name,String sex,String date,String phonenumber,String developaddress,String worktype,String statue) {

		idLabel=new JLabel("人员编号");//标签“人员编号”
		nameLabel=new JLabel("人员姓名");//标签“人员姓名”
		sexLabel=new JLabel("人员性别");//标签“人员性别”
		dateLabel=new JLabel("出生日期");//标签“出生日期”
		phonenumberLabel=new JLabel("手机号码");//标签“手机号码”
		developaddressLabel=new JLabel("邮箱地址");//标签“邮箱地址”
		worktypeLabel=new JLabel("工作岗位");//标签“工作岗位”
		statueLabel=new JLabel("在职状态");//标签“在职状态”

		idField=new JTextField(10);
		idField.setText(id);
		idField.setEditable(false);//设置为不可编辑
		nameField=new JTextField(10);
		nameField.setText(name);
		nameField.setEditable(false);//设置为不可编辑
		sexField=new JTextField(10);
		sexField.setText(sex);
		dateField=new JTextField(8);
		dateField.setText(date);
		phonenumberField=new JTextField(10);
		phonenumberField.setText(phonenumber);
		developaddressField=new JTextField(10);
		developaddressField.setText(developaddress);
		worktypeField=new JTextField(10);
		worktypeField.setText(worktype);
		statueField=new JTextField(10);
		statueField.setText(statue);

		p1=new JPanel();
		p1.add(idLabel);p1.add(idField);
		p1.setLayout(new FlowLayout());
		p1.setBounds(20, 10, 200, 32);

		p2=new JPanel();
		p2.add(nameLabel);p2.add(nameField);
		p2.setLayout(new FlowLayout());
		p2.setBounds(20, 60, 200, 32);

		p3=new JPanel();
		p3.add(sexLabel);p3.add(sexField);
		p3.setLayout(new FlowLayout());
		p3.setBounds(20, 110, 200, 32);

		p4=new JPanel();
		p4.add(dateLabel);p4.add(dateField);
		p4.setLayout(new FlowLayout());
		p4.setBounds(5, 160, 160, 32);

		p5=new JPanel();
		p5.add(phonenumberLabel);p5.add(phonenumberField);
		p5.setLayout(new FlowLayout());
		p5.setBounds(20, 210, 200, 32);

		p6=new JPanel();
		p6.add(developaddressLabel);p6.add(developaddressField);
		p6.setLayout(new FlowLayout());
		p6.setBounds(20, 260, 200, 32);

		p7=new JPanel();
		p7.add(worktypeLabel);p7.add(worktypeField);
		p7.setLayout(new FlowLayout());
		p7.setBounds(20, 310, 200, 32);

		p8=new JPanel();
		p8.add(statueLabel);p8.add(statueField);
		p8.setLayout(new FlowLayout());
		p8.setBounds(20, 360, 200, 32);

		querenButton=new JButton("确认");//“确认”按钮
		querenButton.setBounds(40, 420, 60, 25);
		querenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//？
					String idStr=new String(idField.getText());
					String nameStr=new String(nameField.getText());
					String sexStr=new String(sexField.getText());
					String dateStr=new String(dateField.getText());
					String phonenumberStr=new String(phonenumberField.getText());
					String developaddressStr=new String(developaddressField.getText());
					String worktypeStr=new String(worktypeField.getText());
					String statueStr=new String(statueField.getText());

					//if(strIsEnglish(nameStr)==true) {
					if(isNumeric(phonenumberStr)==true) {
						Dao.xgryxx1(idStr,nameStr,sexStr,dateStr,phonenumberStr,developaddressStr,worktypeStr,statueStr);//函数功能：增加商品信息
						JOptionPane.showMessageDialog(XgryxxFrame1.this, "修改人员信息成功","提示", JOptionPane.INFORMATION_MESSAGE);
						Dao.xgryrecord(LoginDialog.userid,"null");//增加修改人员信息操作的历史记录，不用商品id

						setVisible(false);
					}else {
						JOptionPane.showMessageDialog(XgryxxFrame1.this,"输入手机号码不是数字","错误",JOptionPane.ERROR_MESSAGE);
					}


				}catch(Exception e1) {
					e1.printStackTrace();
				}


			}
		});

		quxiaoButton=new JButton("取消");//“取消”按钮
		quxiaoButton.setBounds(130, 420, 60, 25);
		quxiaoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int result=JOptionPane.showConfirmDialog(XgryxxFrame1.this, "是否确认退出？", "提示", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					XgryxxFrame1.this.dispose();
				}
				else if(result==JOptionPane.NO_OPTION){
					XgryxxFrame1.this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}

		});

		xuanzeriqiButton=new JButton("选择");
		xuanzeriqiButton.setBounds(170, 162, 60, 25);
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

		this.setLayout(null);//绝对布局
		this.add(p1);this.add(p2);this.add(p3);this.add(p4);this.add(p5);this.add(p6);this.add(p7);this.add(p8);this.add(xuanzeriqiButton);
		this.add(querenButton);this.add(quxiaoButton);
		statueField.addKeyListener(new java.awt.event.KeyAdapter() {//按键“Enter”等于“确认”按钮
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

		this.setBounds(580, 100, 245, 500);
		this.setTitle("修改人员信息界面");
		this.setIconImage(getToolkit().getImage(getClass().getResource("/resources/amenduser.PNG")));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}

	//判断表示是否全为英文
	private  static boolean strIsEnglish(String word) {
		boolean sign = true; // 初始化标志为为'true'
		for (int i = 0; i < word.length(); i++) {
			if (!(word.charAt(i) >= 'A' && word.charAt(i) <= 'Z')
					&& !(word.charAt(i) >= 'a' && word.charAt(i) <= 'z')) {
				return false;
			}
		}
		return true;
	}

	//判断表示是否全为数字
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

}
