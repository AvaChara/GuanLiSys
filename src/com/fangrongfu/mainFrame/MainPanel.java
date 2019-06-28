package com.fangrongfu.mainFrame;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 设置主窗口的背景图
 * @author frf
 *
 */

public class MainPanel extends JPanel{

	public int width, height;
	private Image img;

	public MainPanel() {
		super();
		URL url = null;
		try {
			url = new URL("file:///F:/eclipse-workspace/超市库存管理系统/res/main.jpg");
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
