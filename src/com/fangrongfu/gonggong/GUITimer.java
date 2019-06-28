package com.fangrongfu.gonggong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUITimer extends JFrame{

    //添加 显示时间的JTextField
    private void addComponent(){
        JPanel time = new JPanel();
        this.getContentPane().add(time);
        this.setTimer(time);
    }
    //显示窗口
    private void showTime(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.pack();//自动适应窗口大小
        this.setSize(160, 80);
        this.setVisible(true);
    }

    //设置Timer 1000ms实现一次动作 实际是一个线程
    private void setTimer(JPanel time){
        final JPanel varTime = time;
        Timer timeAction = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                long timemillis = System.currentTimeMillis();
                //转换日期显示格式
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                varTime.setToolTipText(df.format(new Date(timemillis)));
            }
        });
        timeAction.start();
    }

    public static void main(String[] args) {
        GUITimer timeFrame = new GUITimer();
        timeFrame.addComponent();
        timeFrame.showTime();
    }

}  