package com.fangrongfu.dengluzhuceFrame;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.xml.ws.Service.Mode;

/**
 * 用一个画板，写上“库存管理系统”
 * @author frf
 *
 */

public class CanvasPanel extends Canvas{

	private Font font;

	public CanvasPanel() {
		super();
		font=new Font("宋体",Font.BOLD,100);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2=(Graphics2D)g;
		g2.setFont(font);
		g2.setColor(Color.BLACK);
		//this.setBackground(null);//设置画板背景色
		//this.isOpaque();//检测是否支持透明。
		this.setIgnoreRepaint(false);//绘制透明色
		g2.drawString("库存管理系统", 0, 87);
	}

}
