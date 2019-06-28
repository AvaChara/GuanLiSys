package com.fangrongfu.chuhuoguanli;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import com.fangrongfu.gonggong.Dao;

/**
 * 添加商品到出货单的窗口
 * @author frf
 *
 */

public class ChuhuoFrame1 extends JFrame{

	JLabel numberLabel;//“出货数量”标签

	JTextField numberField;//“出货数量”文本框

	JButton tianjiaButton;//“添加”按钮

	JPanel p1;

	Vector tableValues;

	public ChuhuoFrame1(String id,String num,JTable table,String Str,Vector tableValues) {

		numberLabel=new JLabel("出货数量");//“出货数量”标签

		numberField=new JTextField(10);

		tianjiaButton=new JButton("添加");//“添加”按钮
		tianjiaButton.setBounds(70, 60, 60, 25);

		p1=new JPanel();
		p1.add(numberLabel);p1.add(numberField);
		p1.setLayout(new FlowLayout());
		p1.setBounds(10, 20, 180, 32);

		this.setLayout(null);//绝对布局

		this.add(p1);
		this.add(tianjiaButton);
		tianjiaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				try {
					String tianjiaNum = null;
					tianjiaNum=numberField.getText();

					if(isNumeric(tianjiaNum)==true) {
						int n;

						n=Integer.parseInt(num)-Integer.parseInt(tianjiaNum);

						if(n<0) {
							JOptionPane.showMessageDialog(ChuhuoFrame1.this,"出货数量大于库存数量","错误",JOptionPane.ERROR_MESSAGE);
						}else {
							String newNum = null;

							newNum=String.valueOf(n);

							Dao.cxkcxx2(id,newNum);//对数据库的商品数量进行修改

							tableValues.addElement(Dao.scchdxx(id, tianjiaNum));//将出货的商品信息添加到出货单

							table.getModel().setValueAt(newNum, table.getSelectedRow(), 8);//修改表格
							table.updateUI();//更新表格
							JOptionPane.showMessageDialog(ChuhuoFrame1.this, "已成功添加该商品到出货单","提示", JOptionPane.INFORMATION_MESSAGE);
							setVisible(false);

						}
					}else {
						JOptionPane.showMessageDialog(ChuhuoFrame1.this,"输入的不是数字，请输入数字","错误",JOptionPane.ERROR_MESSAGE);
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


			}
		});

		this.addWindowListener(new WindowAdapter(){//为了关闭窗口
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//子框关闭，父框不关闭
			}
		});

		this.setBounds(300, 100, 200, 130);
		this.setTitle("添加商品界面");
		this.setIconImage(getToolkit().getImage(getClass().getResource("/resources/blue.PNG")));
		this.setResizable(false);
		this.setVisible(true);

	}

	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	
	/*public Vector tjchspxx() {
		return this.tableValues;
	}*/

}
