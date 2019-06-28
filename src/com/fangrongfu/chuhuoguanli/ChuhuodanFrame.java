package com.fangrongfu.chuhuoguanli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.fangrongfu.dengluzhuceFrame.LoginDialog;
import com.fangrongfu.gonggong.Dao;

/**
 * 出货单窗口
 * @author frf
 *
 */

public class ChuhuodanFrame extends JFrame{

	JLabel wenziLabel;//“文字”标签

	JButton dayinButton;//“打印”按钮
	JButton quxiaoButton;//“取消”按钮

	JTable jhdxxTable;//显示表格
	JScrollPane jhdxxSrollPane;//滚动面板
	DefaultTableModel jhdxxModel;//表格的数据模型，操作数据

	Vector columnNames;


	public ChuhuodanFrame(Vector tableValues,String outid) {

		wenziLabel = new JLabel("出货单信息");
		wenziLabel.setBounds(365, 10, 60, 25);

		dayinButton = new JButton("打印");
		dayinButton.setBounds(285, 360, 60, 25);
		dayinButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Dao.zjdanjuxx(outid,"out",tableValues);//增加出货单信息，将出货单信息写进出货单信息表里
				Dao.chuhuorecord(LoginDialog.userid,ChuhuoFrame.outid);//增加出货操作历史记录，要有出货单号
				JOptionPane.showMessageDialog(ChuhuodanFrame.this, "已加入打印队列，请稍后...","提示", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		quxiaoButton = new JButton("取消");
		quxiaoButton.setBounds(440, 360, 60, 25);
		quxiaoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				tableValues.clear();//清空出货单数据
				jhdxxTable.updateUI();//刷新表格
				JOptionPane.showMessageDialog(ChuhuodanFrame.this, "该出货单已取消，请重新选择出货商品","提示", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
			}
		});

		columnNames=new Vector();
		columnNames.add("编号");
		columnNames.add("名称");
		columnNames.add("状态");
		columnNames.add("类别");
		columnNames.add("生产日期");
		columnNames.add("有效日期");
		columnNames.add("供应商");
		columnNames.add("单价");
		columnNames.add("数量");

		jhdxxModel = new DefaultTableModel(tableValues,columnNames);//表格的数据模型，操作数据

		jhdxxTable=new JTable();//创建指定列名和数据的表格
		jhdxxTable.setRowHeight(20);//设置标体的高度
		jhdxxTable.setModel(jhdxxModel);
		jhdxxSrollPane=new JScrollPane(jhdxxTable);//创建显示表格的滚动面板
		jhdxxSrollPane.setBounds(20, 35, 763, 320);

		this.add(wenziLabel);
		this.add(dayinButton);this.add(quxiaoButton);
		this.add(jhdxxSrollPane);

		this.setLayout(null);//绝对布局

		this.addWindowListener(new WindowAdapter(){//为了关闭窗口
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//子框关闭，父框不关闭
			}
		});

		this.setBounds(300, 100, 808, 420);
		this.setTitle("出货单界面");
		this.setIconImage(getToolkit().getImage(getClass().getResource("/resources/blue.PNG")));
		this.setResizable(false);
		this.setVisible(true);
	}

}
