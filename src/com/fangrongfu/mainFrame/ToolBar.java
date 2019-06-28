package com.fangrongfu.mainFrame;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;

public class ToolBar extends JToolBar{

	JDesktopPane desktopPanel=new JDesktopPane();
	JLabel label=new JLabel();

	MenuBar menuBar;

	private void initialize() {//初始化工具栏界面的方法
		menuBar=new MenuBar(desktopPanel,label);
		this.setSize(new Dimension(1366,40));
		this.setLocation(0, 51);
		//添加指定的工具栏按钮
		add(createToolButton(menuBar.getJinhuoItem()));//添加"进货单"
		add(createToolButton(menuBar.getChuhuoItem()));//添加"出货单"
		add(createToolButton(menuBar.getQuehuoItem()));//添加"缺货单"
		add(createToolButton(menuBar.getJihuoItem()));//添加"积货单"

	}

	private JButton createToolButton(final JMenuItem item) {
		JButton button=new JButton();//创建按钮
		button.setText(item.getText());//设置按钮名称
		button.setToolTipText(item.getText());//设置按钮提示文本
		button.setIcon(item.getIcon());//设置按钮图标
		button.setFocusable(false);
		//添加按钮动作监听器
		button.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				item.doClick();//执行按钮的单击动作
			}
		});
		return button;
	}

	public ToolBar() {
		super();
		initialize();
	}

}
