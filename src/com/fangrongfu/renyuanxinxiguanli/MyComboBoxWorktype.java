package com.fangrongfu.renyuanxinxiguanli;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 * 设置下拉列表属性的控制类
 * @author frf
 *
 */

public class MyComboBoxWorktype extends AbstractListModel<String> implements ComboBoxModel<String>{

	String selecteditem=null;
	String[] test= {"xitongguanluyuan","cangkuguanliyuan","xiaoshouburenyuan","caigouburenyuan"};

	public MyComboBoxWorktype() {

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

