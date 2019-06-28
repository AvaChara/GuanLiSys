package com.fangrongfu.mainFrame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import com.fangrongfu.chuhuoguanli.ChuhuoFrame;
import com.fangrongfu.dengluzhuceFrame.LoginDialog;
import com.fangrongfu.dengluzhuceFrame.LoginPanel;
import com.fangrongfu.dengluzhuceFrame.ZhuceFrame;

/**
 * 主界面
 * @author frf
 *
 */

public class MainFrame extends JFrame{

	MainPanel mp;//背景面板
	MenuBar mb;//菜单栏
	ToolBar tb;//（常用功能）工具栏
	JToolBar tb1;//状态栏=工具栏加标签

	JDesktopPane desktopPanel;
	JLabel label;
	JLabel label1;//状态栏显示用户名称
	JLabel label2;//状态栏显示用户职位

	JButton zhuxiao;//按钮“注销”

	private Font font;

	public MainFrame() {
		mp=new MainPanel();
		mp.setLayout(null);

		desktopPanel=new JDesktopPane();
		label=new JLabel();

		mb=new MenuBar(desktopPanel,label);

		tb=new ToolBar();

		tb1=new JToolBar();
		tb1.setBounds(0, 710, 1366, 30);
		label1=new JLabel("用户账号："+LoginDialog.userid);
		tb1.add(label1);
		label2=new JLabel("    用户职位："+LoginDialog.usertype);
		tb1.add(label2);

		//设置Timer 1000ms实现一次动作 实际是一个线程
		final JLabel varTime=new JLabel();//状态栏显示日期时间
		Timer timeAction = new Timer(1000, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				long timemillis = System.currentTimeMillis();
				//转换日期显示格式
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				varTime.setText("    日期时间："+df.format(new Date(timemillis)));
			}
		});
		timeAction.start();
		tb1.add(varTime);

		zhuxiao = new JButton("注销");
		zhuxiao.setBounds(1290, 670, 60, 32);
		zhuxiao.addActionListener(new ActionListener() {

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
							int result=JOptionPane.showConfirmDialog(MainFrame.this, "是否确认注销当前账号？", "提示", JOptionPane.YES_NO_OPTION);
							if(result==JOptionPane.YES_OPTION) {
								MainFrame.this.dispose();//注销账户后，关闭当前的主界面窗口，跳转到登陆界面
								LoginDialog f=new LoginDialog();
							}
							else if(result==JOptionPane.NO_OPTION){
								MainFrame.this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							}
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
				int result=JOptionPane.showConfirmDialog(MainFrame.this, "是否确认退出库存管理系统？", "提示", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {
					MainFrame.this.dispose();//注销账户后，关闭当前的主界面窗口，跳转到登陆界面
				}
				else if(result==JOptionPane.NO_OPTION){
					MainFrame.this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});

		this.add(BorderLayout.CENTER,mp);
		mp.add(mb);mp.add(tb);mp.add(tb1);mp.add(zhuxiao);
		this.setBounds(0, 0, 1366, 768);
		this.setTitle("主界面");
		this.setIconImage(getToolkit().getImage(getClass().getResource("/resources/cangku.jpg")));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//为了后面打开别的窗口，点击“x”，不关闭窗口
		this.setVisible(true);
		//this.dispose();
	}

	public void getCzyStateLabel(){

	}

}
