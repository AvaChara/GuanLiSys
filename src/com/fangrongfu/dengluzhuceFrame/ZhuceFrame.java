package com.fangrongfu.dengluzhuceFrame;

import java.awt.FlowLayout;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import com.fangrongfu.gonggong.Chooser;
import com.fangrongfu.gonggong.Dao;
import com.fangrongfu.renyuanxinxiguanli.CzryxxFrame;
import com.fangrongfu.renyuanxinxiguanli.MyComboBoxSex;
import com.fangrongfu.renyuanxinxiguanli.MyComboBoxStatue;
import com.fangrongfu.renyuanxinxiguanli.MyComboBoxWorktype;
import com.fangrongfu.renyuanxinxiguanli.ZjryxxFrame;

public class ZhuceFrame extends JFrame{

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
	//JTextField sexField;
	JComboBox<String> sex;
	JTextField dateField;
	JTextField phonenumberField;
	JTextField developaddressField;
	//JTextField worktypeField;
	JComboBox<String> worktype;
	//JTextField statueField;
	JComboBox<String> statue;

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

	public static String idStr;//作为全局变量

	public ZhuceFrame() {
		idLabel=new JLabel("人员编号");//标签“人员编号”
		nameLabel=new JLabel("人员姓名");//标签“人员姓名”
		sexLabel=new JLabel("人员性别");//标签“人员性别”
		dateLabel=new JLabel("出生日期");//标签“出生日期”
		phonenumberLabel=new JLabel("手机号码");//标签“手机号码”
		developaddressLabel=new JLabel("邮箱地址");//标签“邮箱地址”
		worktypeLabel=new JLabel("工作岗位");//标签“工作岗位”
		statueLabel=new JLabel("在职状态");//标签“在职状态”

		idField=new JTextField(15);
		nameField=new JTextField(15);
		//sexField=new JTextField(15);
		sex=new JComboBox<>(new MyComboBoxSex());
		dateField=new JTextField(15);
		phonenumberField=new JTextField(15);
		developaddressField=new JTextField(15);
		//worktypeField=new JTextField(15);
		worktype=new JComboBox<>(new MyComboBoxWorktype());
		//statueField=new JTextField(15);
		statue=new JComboBox<>(new MyComboBoxStatue());


		p1=new JPanel();
		p1.add(idLabel);p1.add(idField);
		p1.setLayout(new FlowLayout());
		p1.setBounds(20, 10, 250, 32);

		p2=new JPanel();
		p2.add(nameLabel);p2.add(nameField);
		p2.setLayout(new FlowLayout());
		p2.setBounds(20, 60, 250, 32);

		p3=new JPanel();
		p3.add(sexLabel);p3.add(sex);
		p3.setLayout(new FlowLayout());
		p3.setBounds(20, 110, 250, 32);

		p4=new JPanel();
		p4.add(dateLabel);p4.add(dateField);
		p4.setLayout(new FlowLayout());
		p4.setBounds(0, 160, 238, 32);

		p5=new JPanel();
		p5.add(phonenumberLabel);p5.add(phonenumberField);
		p5.setLayout(new FlowLayout());
		p5.setBounds(20, 210, 250, 32);

		p6=new JPanel();
		p6.add(developaddressLabel);p6.add(developaddressField);
		p6.setLayout(new FlowLayout());
		p6.setBounds(20, 260, 250, 32);

		p7=new JPanel();
		p7.add(worktypeLabel);p7.add(worktype);
		p7.setLayout(new FlowLayout());
		p7.setBounds(20, 310, 250, 32);

		p8=new JPanel();
		p8.add(statueLabel);p8.add(statue);
		p8.setLayout(new FlowLayout());
		p8.setBounds(20, 360, 250, 32);

		querenButton=new JButton("确认");//“确认”按钮
		querenButton.setBounds(70, 420, 60, 25);
		querenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//？
					idStr=new String(idField.getText());
					String nameStr=new String(nameField.getText());
					String sexStr=new String(sex.getSelectedItem().toString());
					String dateStr=new String(dateField.getText());
					String phonenumberStr=new String(phonenumberField.getText());
					String addressStr=new String(developaddressField.getText());
					String worktypeStr=new String(worktype.getSelectedItem().toString());
					String statueStr=new String(statue.getSelectedItem().toString());

					if(strIsEnglish(nameStr)==true) {
						//if(idStr==""||nameStr==""||statueStr==""||typeStr==""||dateStr==""||deadtimeStr==""||produceStr==""||moneyStr==""||numberStr=="") {
						//JOptionPane.showMessageDialog(ZjspxxFrame.this, "商品信息填写不完整","提示", JOptionPane.WARNING_MESSAGE);
						//}
						//if(idStr!=null&&nameStr!=null&&statueStr!=null&&typeStr!=null&&dateStr!=null&&deadtimeStr!=null&&produceStr!=null&&moneyStr!=null&&numberStr!=null) {

						if(Dao.zhuce1(nameStr)==true) {//重名，弹出错误框
							//ZhuceFrame.this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							JOptionPane.showMessageDialog(ZhuceFrame.this, "注册信息发现重名","错误", JOptionPane.ERROR_MESSAGE);

						}else{//信息合格，进行数据库增加人员信息操作，同时弹出设置用户名密码的操作窗口
							Dao.zjryxx(idStr, nameStr, sexStr, dateStr, phonenumberStr, addressStr, worktypeStr, statueStr,null,null);//验证人员信息通过后，增加人员信息
							JOptionPane.showMessageDialog(ZhuceFrame.this, "填写人员信息成功","提示", JOptionPane.INFORMATION_MESSAGE);
							setVisible(false);
							//使标题栏的风格也跟着一起改变...
							JFrame.setDefaultLookAndFeelDecorated(true);
							JDialog.setDefaultLookAndFeelDecorated(true);

							//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
							SwingUtilities.invokeLater(new Runnable() {
								public void run() {


									try {
										UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
										ZhuceFrame1 zf=new ZhuceFrame1();
									} catch (Exception e) {
										JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
									}
									//new MainFrame();

								}
							});

						}

						//}

					}else {
						JOptionPane.showMessageDialog(ZhuceFrame.this,"输入名字不规范，请输入英文","错误",JOptionPane.ERROR_MESSAGE);
					}


				}catch(Exception e1) {
					e1.printStackTrace();
				}
				//setVisible(false);
			}
		});

		quxiaoButton=new JButton("取消");//“取消”按钮
		quxiaoButton.setBounds(170, 420, 60, 25);
		quxiaoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int result=JOptionPane.showConfirmDialog(ZhuceFrame.this, "是否确认退出？", "提示", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					ZhuceFrame.this.dispose();
				}
				else if(result==JOptionPane.NO_OPTION){
					ZhuceFrame.this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}

		});

		xuanzeriqiButton=new JButton("选择");
		xuanzeriqiButton.setBounds(238, 162, 60, 25);
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
		statue.addKeyListener(new java.awt.event.KeyAdapter() {//按键“Enter”等于“确认”按钮
			public void keyTyped(java.awt.event.KeyEvent e) {
				if(e.getKeyChar()=='\n') {//如果按键字符是换行符"\n"
					querenButton.doClick();//执行“确认”按钮的doClick（）方法
				}
			}
		});
		this.setBounds(580, 100, 310, 500);
		this.setTitle("注册界面");
		this.setIconImage(getToolkit().getImage(getClass().getResource("/resources/user.PNG")));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.dispose();
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

}
