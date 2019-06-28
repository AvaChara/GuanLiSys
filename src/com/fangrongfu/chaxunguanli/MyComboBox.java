package com.fangrongfu.chaxunguanli;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 * 设置下拉列表属性的控制类
 * @author frf
 *
 */

public class MyComboBox extends AbstractListModel<String> implements ComboBoxModel<String>{

	String selecteditem=null;
	String[] test= {"商品编号","商品名称","商品状态","商品类型","供应商"};

	public MyComboBox() {

	}

	@Override
	public int getSize() {//返回下拉列表框中项目的数目
		// TODO Auto-generated method stub
		return test.length;
	}

	@Override
	public String getElementAt(int index) {//根据索引返回值
		// TODO Auto-generated method stub
		return test[index];
	}

	@Override
	public void setSelectedItem(Object anItem) {//获取下拉列表框中的项目
		// TODO Auto-generated method stub
		selecteditem=(String)anItem;
	}

	@Override
	public Object getSelectedItem() {//设置下拉列表框项目
		// TODO Auto-generated method stub
		return selecteditem;
	}

}
