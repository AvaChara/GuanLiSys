
package com.fangrongfu.dengluzhuceFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import com.fangrongfu.gonggong.Chooser;
import com.fangrongfu.gonggong.Dao;
import com.fangrongfu.mainFrame.MainFrame;

/**
 * 登陆界面窗口
 * @author frf
 *
 */

public class LoginDialog extends JFrame implements ActionListener{

	LoginPanel lp;//内容面板（含有背景图片）
	//CanvasPanel cp;//显示文字
	JPanel p1;
	JPanel p2;
	JLabel lb1;
	JLabel lb2;
	JTextField userField;//用户名文本框
	JPasswordField passwordField;//密码文本框
	JButton loginButton;//登陆按钮
	JButton zhuceButton;//退出按钮

	JPanel p3;
	JLabel lb3;//显示文字

	private Font font;
	private Font font1;

	public static String userid;//将人员ID设置为全局变量，贯穿整个系统
	public static String usertype;//将人员职位设置为全局变量，方便设置系统权限

	public LoginDialog() {

		lp=new LoginPanel();
		lp.setLayout(null);

		//cp=new CanvasPanel();

		//cp.setBounds(370, 250, 640, 100);

		font1=new Font("宋体",Font.BOLD,100);

		lb3=new JLabel("库存管理系统");
		lb3.setFont(font1);
		lb3.setForeground(Color.white);

		p3=new JPanel();
		p3.setLayout(new FlowLayout());
		p3.setBounds(370, 230, 640, 115);
		p3.setOpaque(false);//设置面板为透明

		p1=new JPanel();
		p1.setLayout(new FlowLayout());
		p1.setBounds(554, 400, 250, 32);
		p1.setOpaque(false);//设置面板为透明

		p2=new JPanel();
		p2.setLayout(new FlowLayout());
		p2.setBounds(554, 450, 250, 32);
		p2.setOpaque(false);//设置面板为透明

		font=new Font("宋体",Font.BOLD,20);

		lb1=new JLabel("账号");
		lb1.setFont(font);
		lb1.setForeground(Color.white);

		lb2=new JLabel("密码");
		lb2.setFont(font);
		lb2.setForeground(Color.white);

		userField=new JTextField(15);
		userField.setBorder(BorderFactory.createRaisedBevelBorder()); // 凸边框


		passwordField=new JPasswordField(15);
		passwordField.setBorder(BorderFactory.createRaisedBevelBorder()); // 凸边框
		passwordField.addKeyListener(
				new java.awt.event.KeyAdapter() {//按键“Enter”等于“登陆”按钮
			public void keyTyped(java.awt.event.KeyEvent e) {
				if(e.getKeyChar()=='\n') {//如果按键字符是换行符"\n"
					loginButton.doClick();//执行“登陆”按钮的doClick（）方法
				}
			}
		});

		loginButton=new JButton("登陆");
		loginButton.setBounds(580, 500, 80, 35);
		loginButton.setFont(font);
		loginButton.addActionListener(this);

		zhuceButton=new JButton("注册");
		zhuceButton.setBounds(700, 500, 80, 35);
		zhuceButton.setFont(font);
		zhuceButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//使标题栏的风格也跟着一起改变...
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);

				//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {


						try {
							UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
							ZhuceFrame zf=new ZhuceFrame();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
						}
						//new MainFrame();

					}
				});
			}

		});

		this.addWindowListener(new WindowAdapter(){//为了关闭窗口
			public void windowClosing(WindowEvent e) {
				int result=JOptionPane.showConfirmDialog(LoginDialog.this, "是否确认退出库存管理系统？", "提示", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					LoginDialog.this.dispose();//注销账户后，关闭当前的主界面窗口，跳转到登陆界面
				}
				else if(result==JOptionPane.NO_OPTION){
					LoginDialog.this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});

		this.add(BorderLayout.CENTER,lp);
		p3.add(lb3);
		p1.add(lb1);p1.add(userField);
		p2.add(lb2);p2.add(passwordField);

		lp.add(p3);lp.add(p1);lp.add(p2);lp.add(loginButton);lp.add(zhuceButton);//lp.add(cp);
		this.setBounds(0, 0, 1366, 768);
		this.setTitle("系统登陆");
		this.setIconImage(getToolkit().getImage(getClass().getResource("/resources/cangku.jpg")));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		try {
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			String userStr=new String(userField.getText());
			String passStr=new String(passwordField.getPassword());

			//容错处理：输入框不能为空
			if(userStr==null||passStr==null) {
				JOptionPane.showMessageDialog(LoginDialog.this, "用户名或密码为空","登陆失败", JOptionPane.ERROR_MESSAGE);
				return;
			}

			userid = userField.getText();//全局变量

			if(!Dao.checkLogin(userStr,passStr)) {
				JOptionPane.showMessageDialog(LoginDialog.this, "用户名或密码错误","登陆失败", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if(Dao.checkLogin(userStr,passStr)) {

				usertype = Dao.getusertype(userid);//全局变量

				//使标题栏的风格也跟着一起改变...
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);

				//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {


						try {
							UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
							MainFrame mf=new MainFrame();
							mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							mf.setVisible(true);
							//mf.getCzyStateLabel().setText(userStr);
							Dao.loginrecord(userField.getText());
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
						}
						//new MainFrame();

					}
				});

			}

		}catch(Exception e1) {
			e1.printStackTrace();
		}

		setVisible(false);

	}

	public static void main(String[] args) {

		//使标题栏的风格也跟着一起改变...
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);

		//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {


				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
					LoginDialog f=new LoginDialog();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
				}
				//new MainFrame();

			}
		});

	}

}
