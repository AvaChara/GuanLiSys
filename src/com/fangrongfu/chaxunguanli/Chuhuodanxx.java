package com.fangrongfu.chaxunguanli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.fangrongfu.chuhuoguanli.ChuhuoFrame;
import com.fangrongfu.dengluzhuceFrame.LoginDialog;
import com.fangrongfu.gonggong.Dao;

/**
 * 出货单信息窗口
 * @author frf
 *
 */

public class Chuhuodanxx extends JFrame{

	JLabel wenziLabel;//“文字”标签

	JButton dayinButton;//“打印”按钮
	JButton quxiaoButton;//“取消”按钮

	JTable chdxxTable;//显示表格
	JScrollPane chdxxSrollPane;//滚动面板
	DefaultTableModel chdxxModel;//表格的数据模型，操作数据

	Vector columnNames;


	public Chuhuodanxx(String outid){

		wenziLabel = new JLabel("出货单信息");
		wenziLabel.setBounds(365, 10, 60, 25);

		dayinButton = new JButton("打印");
		dayinButton.setBounds(370, 360, 60, 25);
		dayinButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(Chuhuodanxx.this, "已加入打印队列，请稍后...","提示", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		columnNames=new Vector();
		//Vector rowValue1=new Vector();
		//tableValues=new Vector();

		columnNames.add("编号");
		columnNames.add("名称");
		columnNames.add("状态");
		columnNames.add("类别");
		columnNames.add("生产日期");
		columnNames.add("有效日期");
		columnNames.add("供应商");
		columnNames.add("单价");
		columnNames.add("数量");

		try {
			chdxxModel = Dao.chdcxx(outid);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//表格的数据模型，操作数据

		chdxxTable=new JTable();//创建指定列名和数据的表格
		chdxxTable.setRowHeight(20);//设置标体的高度
		chdxxTable.setModel(chdxxModel);
		chdxxSrollPane=new JScrollPane(chdxxTable);//创建显示表格的滚动面板
		chdxxSrollPane.setBounds(20, 35, 763, 320);

		this.add(wenziLabel);
		this.add(dayinButton);
		this.add(chdxxSrollPane);

		this.setLayout(null);//绝对布局

		this.addWindowListener(new WindowAdapter(){//为了关闭窗口
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//子框关闭，父框不关闭
			}
		});

		this.setBounds(300, 100, 808, 420);
		this.setTitle("查询出货单信息界面");
		this.setIconImage(getToolkit().getImage(getClass().getResource("/resources/chaxunchls.PNG")));
		this.setResizable(false);
		this.setVisible(true);
	}

}
