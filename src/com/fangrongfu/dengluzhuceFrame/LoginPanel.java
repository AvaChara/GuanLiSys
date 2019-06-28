package com.fangrongfu.dengluzhuceFrame;

import java.awt.Graphics;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;

/**
 * 设置登陆窗口的背景图
 * @author frf
 *
 */

public class LoginPanel extends JPanel{
	public int width, height;
	private Image img;
	public LoginPanel() {
		super();
		URL url = null;
		try {
			url = new URL("file:///Users/vampire/Desktop/GuanLiSys/res/denglu.jpg");//登陆界面背景图片路径
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		img=new ImageIcon(url).getImage();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img,0,0,this);
	}
}
