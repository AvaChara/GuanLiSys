package com.fangrongfu.renyuanxinxiguanli;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.fangrongfu.gonggong.Dao;
import com.fangrongfu.shangpinxinxiguanli.XgspxxFrame;

public class XgryxxFrame extends JFrame{

	JLabel idLabel;//标签“商品编号”
	JLabel nameLabel;//标签“商品名称”

	JTextField idField;
	JTextField nameField;

	JPanel p1;
	JPanel p2;

	JButton querenButton;//“确认”按钮
	JButton quxiaoButton;//“取消”按钮

	public XgryxxFrame() {
		idLabel=new JLabel("人员编号");//标签“人员编号”
		nameLabel=new JLabel("人员姓名");//标签“人员姓名”

		idField=new JTextField(10);
		nameField=new JTextField(10);

		p1=new JPanel();
		p1.add(idLabel);p1.add(idField);
		p1.setLayout(new FlowLayout());
		p1.setBounds(20, 10, 200, 32);

		p2=new JPanel();
		p2.add(nameLabel);p2.add(nameField);
		p2.setLayout(new FlowLayout());
		p2.setBounds(20, 60, 200, 32);

		querenButton=new JButton("确认");//“确认”按钮
		querenButton.setBounds(40, 100, 60, 25);
		querenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//？
					String idStr=new String(idField.getText());
					String nameStr=new String(nameField.getText());
					Dao.xgryxx(idStr,nameStr);//函数功能：查找人员信息
					JOptionPane.showMessageDialog(XgryxxFrame.this, "查找人员信息成功","提示", JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception e1) {
					e1.printStackTrace();
				}

				setVisible(false);
			}
		});

		quxiaoButton=new JButton("取消");//“取消”按钮
		quxiaoButton.setBounds(130, 100, 60, 25);
		quxiaoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int result=JOptionPane.showConfirmDialog(XgryxxFrame.this, "是否确认退出？", "提示", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					XgryxxFrame.this.dispose();
				}
				else if(result==JOptionPane.NO_OPTION){
					XgryxxFrame.this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

		this.addWindowListener(new WindowAdapter(){//为了关闭窗口
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//子框关闭，父框不关闭
			}
		});

		this.setBounds(580, 100, 245, 180);
		this.setTitle("修改人员信息界面");
		this.setIconImage(getToolkit().getImage(getClass().getResource("/resources/amenduser.PNG")));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}


}
