package com.fangrongfu.renyuanxinxiguanli;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class CzryxxFrame1 extends JFrame{

	JTable jhdTable;
	JScrollPane jhdSrollPane;
	
	/*String[] columnNames= {"编号","名称","状态","类别","生产日期","有效日期","供应商","单价","数量"};//定义表格列名数组
	String[][] tableValues= {{"A1","B1","C1","D1","E1","F1","G1","H1","I1"},
			{"A1","B1","C1","D1","E1","F1","G1","H1","I1"},
			{"A1","B1","C1","D1","E1","F1","G1","H1","I1"},
			{"A1","B1","C1","D1","E1","F1","G1","H1","I1"},
			{"A1","B1","C1","D1","E1","F1","G1","H1","I1"},
			{"A1","B1","C1","D1","E1","F1","G1","H1","I1"},
			{"A1","B1","C1","D1","E1","F1","G1","H1","I1"},
			{"A1","B1","C1","D1","E1","F1","G1","H1","I1"}};//定义表格数据数组*/

	public CzryxxFrame1(String[] columnNames,String[][] tableValues) {

		jhdTable=new JTable(tableValues,columnNames);//创建指定列名和数据的表格
		jhdSrollPane=new JScrollPane(jhdTable);//创建显示表格的滚动面板
		//jhdSrollPane.add(jhdTable);//这是错误的写法
		jhdSrollPane.setBounds(0, 0, 600, 180);

		this.addWindowListener(new WindowAdapter(){//为了关闭窗口
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//子框关闭，父框不关闭
			}
		});

		this.setLayout(null);//绝对布局
		this.add(jhdSrollPane);
		this.setBounds(100, 100, 600, 185);
		this.setTitle("人员信息");
		this.setIconImage(getToolkit().getImage(getClass().getResource("/resources/seekuser.PNG")));
		this.setResizable(false);
		this.setVisible(true);
	}

}
