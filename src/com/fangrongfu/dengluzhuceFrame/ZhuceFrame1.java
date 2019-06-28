package com.fangrongfu.dengluzhuceFrame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.fangrongfu.gonggong.Dao;
import com.fangrongfu.renyuanxinxiguanli.ScryxxFrame;

public class ZhuceFrame1 extends JFrame{

	JLabel nameLabel;//标签“用户名”
	JLabel passwordLabel;//标签“密码”

	JTextField nameField;
	JTextField passwordField;

	JPanel p1;
	JPanel p2;

	JButton querenButton;//“确认”按钮
	JButton quxiaoButton;//“取消”按钮

	public ZhuceFrame1() {
		nameLabel=new JLabel("用户名");//标签“用户名”
		passwordLabel=new JLabel("密码");//标签“密码”

		nameField=new JTextField(10);
		passwordField=new JTextField(10);

		p1=new JPanel();
		p1.add(nameLabel);p1.add(nameField);
		p1.setLayout(new FlowLayout());
		p1.setBounds(20, 10, 200, 32);

		p2=new JPanel();
		p2.add(passwordLabel);p2.add(passwordField);
		p2.setLayout(new FlowLayout());
		p2.setBounds(20, 60, 200, 32);

		querenButton=new JButton("确认");//“确认”按钮
		querenButton.setBounds(40, 100, 60, 25);
		querenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//？
					String nameStr=new String(nameField.getText());
					String passwordStr=new String(passwordField.getText());


					//if(idStr==""||nameStr==""||statueStr==""||typeStr==""||dateStr==""||deadtimeStr==""||produceStr==""||moneyStr==""||numberStr=="") {
					//JOptionPane.showMessageDialog(ZjspxxFrame.this, "商品信息填写不完整","提示", JOptionPane.WARNING_MESSAGE);
					//}
					//if(idStr!=null&&nameStr!=null&&statueStr!=null&&typeStr!=null&&dateStr!=null&&deadtimeStr!=null&&produceStr!=null&&moneyStr!=null&&numberStr!=null) {
					if(Dao.zhuce2(nameStr)==true) {//用户名验证不通过
						JOptionPane.showMessageDialog(ZhuceFrame1.this, "用户名重复","提示", JOptionPane.ERROR_MESSAGE);

					}else {//用户名密码验证通过
						Dao.zhuce3(ZhuceFrame.idStr,nameStr,passwordStr);//函数功能：添加验证成功的信用的用户名和密码信息
						JOptionPane.showMessageDialog(ZhuceFrame1.this, "注册成功","提示", JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						Dao.zhucerecord(nameStr);
					}

					//}
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				//setVisible(false);
			}
		});

		quxiaoButton=new JButton("取消");//“取消”按钮
		quxiaoButton.setBounds(130, 100, 60, 25);
		quxiaoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int result=JOptionPane.showConfirmDialog(ZhuceFrame1.this, "是否确认退出？", "提示", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					ZhuceFrame1.this.dispose();
				}
				else if(result==JOptionPane.NO_OPTION){
					ZhuceFrame1.this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}

		});

		this.setLayout(null);//绝对布局
		this.add(p1);this.add(p2);
		this.add(querenButton);this.add(quxiaoButton);
		nameField.addKeyListener(new java.awt.event.KeyAdapter() {//按键“Enter”等于“确认”按钮
			public void keyTyped(java.awt.event.KeyEvent e) {
				if(e.getKeyChar()=='\n') {//如果按键字符是换行符"\n"
					querenButton.doClick();//执行“确认”按钮的doClick（）方法
				}
			}
		});
		this.setBounds(580, 100, 245, 180);
		this.setTitle("填写用户名密码界面");
		this.setIconImage(getToolkit().getImage(getClass().getResource("/resources/user.PNG")));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}

}
