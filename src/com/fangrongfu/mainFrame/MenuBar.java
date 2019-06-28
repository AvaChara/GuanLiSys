package com.fangrongfu.mainFrame;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import com.fangrongfu.chaxunguanli.ChaxunchFrame;
import com.fangrongfu.chaxunguanli.ChaxunjhFrame;
import com.fangrongfu.chaxunguanli.ChaxunqtFrame;
import com.fangrongfu.chaxunguanli.CxkcxxFrame;
import com.fangrongfu.chuhuoguanli.ChuhuoFrame;
import com.fangrongfu.dengluzhuceFrame.LoginDialog;
import com.fangrongfu.dengluzhuceFrame.ZhuceFrame;
import com.fangrongfu.jinhuoguanli.JinhuoFrame;
import com.fangrongfu.jinhuoguanli.JinhuodanFrame;
import com.fangrongfu.renyuanxinxiguanli.CzryxxFrame;
import com.fangrongfu.renyuanxinxiguanli.ScryxxFrame;
import com.fangrongfu.renyuanxinxiguanli.XgryxxFrame;
import com.fangrongfu.renyuanxinxiguanli.ZjryxxFrame;
import com.fangrongfu.shangpinxinxiguanli.CzspxxFrame;
import com.fangrongfu.shangpinxinxiguanli.ScspxxFrame;
import com.fangrongfu.shangpinxinxiguanli.XgspxxFrame;
import com.fangrongfu.shangpinxinxiguanli.ZjspxxFrame;
import com.fangrongfu.zaikuguanli.JihuoFrame;
import com.fangrongfu.zaikuguanli.QuehuoFrame;
import com.fangrongfu.zaikuguanli.QuehuodanFrame;

/**
 * 主界面的菜单条（MenuBar）、菜单（Menu）、菜单项（MenuItem）
 * @author frf
 *
 */

public class MenuBar extends JMenuBar{

	private JMenu jinhuo_Menu=null;
	private JMenuItem jinhuoItem=null;

	private JMenu chuhuo_Menu=null;
	private JMenuItem chuhuoItem=null;

	private JMenu zaiku_Menu=null;
	private JMenuItem quehuoItem=null;
	private JMenuItem jihuoItem=null;

	private JMenu renyuan_Menu=null;
	private JMenuItem zjryItem=null;
	private JMenuItem scryItem=null;
	private JMenuItem czryItem=null;
	private JMenuItem xgryItem=null;

	private JMenu shangpin_Menu=null;
	private JMenuItem zjspItem=null;
	private JMenuItem scspItem=null;
	private JMenuItem czspItem=null;
	private JMenuItem xgspItem=null;

	private JMenu chaxun_Menu=null;
	private JMenuItem cxkcItem=null;
	private JMenu cxls_Menu=null;
	private JMenuItem cxchItem=null;
	private JMenuItem cxjhItem=null;
	private JMenuItem cxqtItem=null;


	/**
	 * 初始化进货管理菜单的方法
	 * @return
	 */

	public JMenu getJinhuo_Menu() {
		if(jinhuo_Menu==null) {
			jinhuo_Menu=new JMenu();//创建一个菜单对象
			jinhuo_Menu.setText("进货管理（J）");//设置菜单名称

			if(LoginDialog.usertype.equals("xiaoshouburenyuan")||LoginDialog.usertype.equals("cangkuguanliyuan")) {
				jinhuo_Menu.setEnabled(false);//设置为不可用（设置权限用）

			}

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/jinhuo.PNG"));
			image.setImage(image.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT ));
			jinhuo_Menu.setIcon(image);

			jinhuo_Menu.setMnemonic(KeyEvent.VK_J);//设置快捷键
			jinhuo_Menu.add(getJinhuoItem());//添加“进货单”菜单项
		}
		return jinhuo_Menu;
	}

	/**
	 * 初始化菜单项“进货单”
	 * @return
	 */

	public JMenuItem getJinhuoItem() {
		if(jinhuoItem==null) {
			jinhuoItem=new JMenuItem();
			jinhuoItem.setText("进货单");

			if(LoginDialog.usertype.equals("xiaoshouburenyuan")||LoginDialog.usertype.equals("cangkuguanliyuan")) {
				jinhuoItem.setEnabled(false);//设置为不可用（设置权限用）
			}

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/yellow.PNG"));
			image.setImage(image.getImage().getScaledInstance(24, 32,Image.SCALE_DEFAULT ));
			jinhuoItem.setIcon(image);

			jinhuoItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//编写用于打开进货单窗口的代码
					//使标题栏的风格也跟着一起改变...
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);

					//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {


							try {
								UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
								JinhuoFrame jf=new JinhuoFrame();
								jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								jf.setVisible(true);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
							}
							//new MainFrame();

						}
					});


				}
			});

		}
		return jinhuoItem;
	}


	/**
	 * 初始化出货管理菜单的方法
	 * @return
	 */

	public JMenu getChuhuo_Menu() {
		if(chuhuo_Menu==null) {
			chuhuo_Menu=new JMenu();//创建一个菜单对象
			chuhuo_Menu.setText("出货管理（C）");//设置菜单名称

			if(LoginDialog.usertype.equals("cangkuguanliyuan")||LoginDialog.usertype.equals("caigouburenyuan")) {
				chuhuo_Menu.setEnabled(false);//设置为不可用（设置权限用）
			}

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/chuhuo.PNG"));
			image.setImage(image.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT ));
			chuhuo_Menu.setIcon(image);

			chuhuo_Menu.setMnemonic(KeyEvent.VK_C);//设置快捷键
			chuhuo_Menu.add(getChuhuoItem());//添加“出货单”菜单项
		}
		return chuhuo_Menu;
	}

	/**
	 * 初始化菜单项“出货单”
	 * @return
	 */

	public JMenuItem getChuhuoItem() {
		if(chuhuoItem==null) {
			chuhuoItem=new JMenuItem();
			chuhuoItem.setText("出货单");

			if(LoginDialog.usertype.equals("cangkuguanliyuan")||LoginDialog.usertype.equals("caigouburenyuan")) {
				chuhuoItem.setEnabled(false);//设置为不可用（设置权限用）
			}

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/blue.PNG"));
			image.setImage(image.getImage().getScaledInstance(24, 32,Image.SCALE_DEFAULT ));
			chuhuoItem.setIcon(image);

			chuhuoItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					//编写用于打开出货单窗口的代码
					//使标题栏的风格也跟着一起改变...
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);

					//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {


							try {
								UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
								ChuhuoFrame chuhuof=new ChuhuoFrame();
								chuhuof.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								chuhuof.setVisible(true);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
							}
							//new MainFrame();

						}
					});


				}
			});

		}
		return chuhuoItem;
	}

	/**
	 * 初始化在库管理菜单的方法
	 * @return
	 */

	public JMenu getZaiku_Menu() {
		if(zaiku_Menu==null) {
			zaiku_Menu=new JMenu();//创建一个菜单对象
			zaiku_Menu.setText("在库管理（Z）");//设置菜单名称

			if(LoginDialog.usertype.equals("xiaoshouburenyuan")||LoginDialog.usertype.equals("caigouburenyuan")) {
				zaiku_Menu.setEnabled(false);//设置为不可用（设置权限用）
			}

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/zaiku.PNG"));
			image.setImage(image.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT ));
			zaiku_Menu.setIcon(image);

			zaiku_Menu.setMnemonic(KeyEvent.VK_Z);//设置快捷键
			zaiku_Menu.add(getQuehuoItem());//添加“缺货单”菜单项
			zaiku_Menu.add(getJihuoItem());//添加“积货单”菜单项
		}
		return zaiku_Menu;
	}

	/**
	 * 初始化菜单项“缺货单”
	 * @return
	 */

	public JMenuItem getQuehuoItem() {
		if(quehuoItem==null) {
			quehuoItem=new JMenuItem();
			quehuoItem.setText("缺货单");

			if(LoginDialog.usertype.equals("xiaoshouburenyuan")||LoginDialog.usertype.equals("caigouburenyuan")) {
				quehuoItem.setEnabled(false);//设置为不可用（设置权限用）
			}

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/black.PNG"));
			image.setImage(image.getImage().getScaledInstance(24, 32,Image.SCALE_DEFAULT ));
			quehuoItem.setIcon(image);

			quehuoItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					//编写用于打开缺货窗口的代码
					//使标题栏的风格也跟着一起改变...
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);

					//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {


							try {
								UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
								QuehuoFrame quehuof=new QuehuoFrame();
								quehuof.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								quehuof.setVisible(true);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
							}
							//new MainFrame();

						}
					});


				}
			});

		}
		return quehuoItem;
	}

	/**
	 * 初始化菜单项“积货单”
	 * @return
	 */

	public JMenuItem getJihuoItem() {
		if(jihuoItem==null) {
			jihuoItem=new JMenuItem();
			jihuoItem.setText("积货单");

			if(LoginDialog.usertype.equals("xiaoshouburenyuan")||LoginDialog.usertype.equals("caigouburenyuan")) {
				jihuoItem.setEnabled(false);//设置为不可用（设置权限用）
			}

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/red.PNG"));
			image.setImage(image.getImage().getScaledInstance(24, 32,Image.SCALE_DEFAULT ));
			jihuoItem.setIcon(image);

			jihuoItem.addActionListener(new ActionListener() {


				@Override
				public void actionPerformed(ActionEvent e) {
					//编写用于打开积货窗口的代码
					//使标题栏的风格也跟着一起改变...
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);

					//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {


							try {
								UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
								JihuoFrame jihuof=new JihuoFrame();
								jihuof.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								jihuof.setVisible(true);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
							}
							//new MainFrame();

						}
					});


				}
			});

		}
		return jihuoItem;
	}

	/**
	 * 初始化人员信息管理菜单的方法
	 * @return
	 */

	public JMenu getRenyuan_Menu() {
		if(renyuan_Menu==null) {
			renyuan_Menu=new JMenu();//创建一个菜单对象
			renyuan_Menu.setText("人员信息管理（R）");//设置菜单名称

			if(LoginDialog.usertype.equals("xiaoshouburenyuan")||LoginDialog.usertype.equals("cangkuguanliyuan")||LoginDialog.usertype.equals("caigouburenyuan")) {
				renyuan_Menu.setEnabled(false);//设置为不可用（设置权限用）
			}

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/renyuan.PNG"));
			image.setImage(image.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT ));
			renyuan_Menu.setIcon(image);

			renyuan_Menu.setMnemonic(KeyEvent.VK_R);//设置快捷键
			renyuan_Menu.add(getZjryItem());//添加“增加人员信息”菜单项
			renyuan_Menu.add(getScryItem());//添加“删除人员信息”菜单项
			renyuan_Menu.add(getCzryItem());//添加“查找人员信息”菜单项
			renyuan_Menu.add(getXgryItem());//添加“修改人员信息”菜单项
		}
		return renyuan_Menu;
	}

	/**
	 * 初始化菜单项“增加人员信息”
	 * @return
	 */

	public JMenuItem getZjryItem() {
		if(zjryItem==null) {
			zjryItem=new JMenuItem();
			zjryItem.setText("增加人员信息");

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/adduser.PNG"));
			image.setImage(image.getImage().getScaledInstance(32, 32,Image.SCALE_DEFAULT ));
			zjryItem.setIcon(image);

			zjryItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					//编写用于打开增加商品信息窗口的代码
					//使标题栏的风格也跟着一起改变...
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);

					//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {


							try {
								UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
								ZjryxxFrame zjryf=new ZjryxxFrame();
								zjryf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								zjryf.setVisible(true);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
							}
							//new MainFrame();

						}
					});


				}
			});

		}
		return zjryItem;
	}

	/**
	 * 初始化菜单项“删除人员信息”
	 * @return
	 */

	public JMenuItem getScryItem() {
		if(scryItem==null) {
			scryItem=new JMenuItem();
			scryItem.setText("删除人员信息");

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/deleteuser.PNG"));
			image.setImage(image.getImage().getScaledInstance(32, 32,Image.SCALE_DEFAULT ));
			scryItem.setIcon(image);

			scryItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					//编写用于打开删除人员信息窗口的代码
					//使标题栏的风格也跟着一起改变...
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);

					//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {


							try {
								UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
								ScryxxFrame scryf=new ScryxxFrame();
								scryf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								scryf.setVisible(true);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
							}
							//new MainFrame();

						}
					});

				}
			});

		}
		return scryItem;
	}

	/**
	 * 初始化菜单项“查找人员信息”
	 * @return
	 */

	public JMenuItem getCzryItem() {
		if(czryItem==null) {
			czryItem=new JMenuItem();
			czryItem.setText("查找人员信息");

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/seekuser.PNG"));
			image.setImage(image.getImage().getScaledInstance(32, 32,Image.SCALE_DEFAULT ));
			czryItem.setIcon(image);

			czryItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					//编写用于打开查找人员信息窗口的代码
					//使标题栏的风格也跟着一起改变...
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);

					//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {


							try {
								UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
								CzryxxFrame czryf=new CzryxxFrame();
								czryf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								czryf.setVisible(true);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
							}
							//new MainFrame();

						}
					});


				}
			});

		}
		return czryItem;
	}

	/**
	 * 初始化菜单项“修改人员信息”
	 * @return
	 */

	public JMenuItem getXgryItem() {
		if(xgryItem==null) {
			xgryItem=new JMenuItem();
			xgryItem.setText("修改人员信息");

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/amenduser.PNG"));
			image.setImage(image.getImage().getScaledInstance(32, 32,Image.SCALE_DEFAULT ));
			xgryItem.setIcon(image);

			xgryItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					//编写用于打开修改人员信息窗口的代码
					//使标题栏的风格也跟着一起改变...
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);

					//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {


							try {
								UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
								XgryxxFrame xgryf=new XgryxxFrame();
								xgryf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								xgryf.setVisible(true);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
							}
							//new MainFrame();

						}
					});


				}
			});

		}
		return xgryItem;
	}

	/**
	 * 初始化商品信息管理菜单的方法
	 * @return
	 */

	public JMenu getShangpin_Menu() {
		if(shangpin_Menu==null) {
			shangpin_Menu=new JMenu();//创建一个菜单对象
			shangpin_Menu.setText("商品信息管理（S）");//设置菜单名称

			if(LoginDialog.usertype.equals("xiaoshouburenyuan")||LoginDialog.usertype.equals("caigouburenyuan")) {
				shangpin_Menu.setEnabled(false);//设置为不可用（设置权限用）
			}

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/shangpin.PNG"));
			image.setImage(image.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT ));
			shangpin_Menu.setIcon(image);

			shangpin_Menu.setMnemonic(KeyEvent.VK_S);//设置快捷键
			shangpin_Menu.add(getZjspItem());//添加“增加商品信息”菜单项
			shangpin_Menu.add(getScspItem());//添加“删除商品信息”菜单项
			shangpin_Menu.add(getCzspItem());//添加“查找商品信息”菜单项
			shangpin_Menu.add(getXgspItem());//添加“修改商品信息”菜单项
		}
		return shangpin_Menu;
	}

	/**
	 * 初始化菜单项“增加商品信息”
	 * @return
	 */

	public JMenuItem getZjspItem() {
		if(zjspItem==null) {
			zjspItem=new JMenuItem();
			zjspItem.setText("增加商品信息");

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/addgoods.PNG"));
			image.setImage(image.getImage().getScaledInstance(32, 32,Image.SCALE_DEFAULT ));
			zjspItem.setIcon(image);

			zjspItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//编写用于打开增加商品信息窗口的代码
					//使标题栏的风格也跟着一起改变...
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);

					//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {


							try {
								UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
								ZjspxxFrame zjspf=new ZjspxxFrame();
								zjspf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								zjspf.setVisible(true);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
							}
							//new MainFrame();

						}
					});

				}
			});

		}
		return zjspItem;
	}

	/**
	 * 初始化菜单项“删除商品信息”
	 * @return
	 */

	public JMenuItem getScspItem() {
		if(scspItem==null) {
			scspItem=new JMenuItem();
			scspItem.setText("删除商品信息");

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/deletegoods.PNG"));
			image.setImage(image.getImage().getScaledInstance(32, 32,Image.SCALE_DEFAULT ));
			scspItem.setIcon(image);

			scspItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					//编写用于打开删除商品信息窗口的代码
					//使标题栏的风格也跟着一起改变...
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);

					//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {


							try {
								UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
								ScspxxFrame scspf=new ScspxxFrame();
								scspf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								scspf.setVisible(true);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
							}
							//new MainFrame();

						}
					});

				}
			});

		}
		return scspItem;
	}

	/**
	 * 初始化菜单项“查找商品信息”
	 * @return
	 */

	public JMenuItem getCzspItem() {
		if(czspItem==null) {
			czspItem=new JMenuItem();
			czspItem.setText("查找商品信息");

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/seekgoods.PNG"));
			image.setImage(image.getImage().getScaledInstance(32, 32,Image.SCALE_DEFAULT ));
			czspItem.setIcon(image);

			czspItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					//编写用于打开查找商品信息窗口的代码
					//使标题栏的风格也跟着一起改变...
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);

					//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {


							try {
								UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
								CzspxxFrame czspf=new CzspxxFrame();
								czspf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								czspf.setVisible(true);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
							}
							//new MainFrame();

						}
					});


				}
			});

		}
		return czspItem;
	}

	/**
	 * 初始化菜单项“修改商品信息”
	 * @return
	 */

	public JMenuItem getXgspItem() {
		if(xgspItem==null) {
			xgspItem=new JMenuItem();
			xgspItem.setText("修改商品信息");

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/amendgoods.PNG"));
			image.setImage(image.getImage().getScaledInstance(32, 32,Image.SCALE_DEFAULT ));
			xgspItem.setIcon(image);

			xgspItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					//编写用于打开修改商品信息窗口的代码
					//使标题栏的风格也跟着一起改变...
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);

					//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {


							try {
								UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
								XgspxxFrame xgspf=new XgspxxFrame();
								xgspf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								xgspf.setVisible(true);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
							}
							//new MainFrame();

						}
					});


				}
			});

		}
		return xgspItem;
	}

	/**
	 * 初始化查询管理菜单的方法
	 * @return
	 */

	public JMenu getChaxun_Menu() {
		if(chaxun_Menu==null) {
			chaxun_Menu=new JMenu();//创建一个菜单对象
			chaxun_Menu.setText("查询管理（F）");//设置菜单名称

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/seek.PNG"));
			image.setImage(image.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT ));
			chaxun_Menu.setIcon(image);

			chaxun_Menu.setMnemonic(KeyEvent.VK_F);//设置快捷键
			chaxun_Menu.add(getCxkcItem());//添加“查询库存信息”菜单项
			chaxun_Menu.add(getCxls_Menu());//添加“查询历史记录”子菜单
		}
		return chaxun_Menu;
	}

	/**
	 * 初始化菜单项“查询库存信息”
	 * @return
	 */

	public JMenuItem getCxkcItem() {
		if(cxkcItem==null) {
			cxkcItem=new JMenuItem();
			cxkcItem.setText("查询库存信息");

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/chaxunkc.PNG"));
			image.setImage(image.getImage().getScaledInstance(32, 32,Image.SCALE_DEFAULT ));
			cxkcItem.setIcon(image);

			cxkcItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					//编写用于打开查询库存信息窗口的代码
					//使标题栏的风格也跟着一起改变...
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);

					//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {


							try {
								UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
								CxkcxxFrame cxkcf=new CxkcxxFrame();
								cxkcf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								cxkcf.setVisible(true);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
							}
							//new MainFrame();

						}
					});


				}
			});

		}
		return cxkcItem;
	}

	/**
	 * 初始化子菜单“查询历史记录”的方法
	 * @return
	 */

	public JMenu getCxls_Menu() {
		if(cxls_Menu==null) {
			cxls_Menu=new JMenu();//创建一个子菜单对象
			//cxls_Menu.setMnemonic('O');
			cxls_Menu.setText("查询历史记录");//设置子菜单名称

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/chaxunls.PNG"));
			image.setImage(image.getImage().getScaledInstance(32, 32,Image.SCALE_DEFAULT ));
			cxls_Menu.setIcon(image);

			cxls_Menu.add(getCxchItem());//添加“查询出货历史记录”菜单项
			cxls_Menu.add(getCxjhItem());//添加“查询进货历史记录”菜单项
			cxls_Menu.add(getCxqtItem());//添加“查询其他历史记录”菜单项
		}
		return cxls_Menu;
	}

	/**
	 * 初始化菜单项子项“查询出货历史记录”
	 * @return
	 */

	public JMenuItem getCxchItem() {
		if(cxchItem==null) {
			cxchItem=new JMenuItem();
			cxchItem.setText("查询出货历史记录");

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/chaxunchls.PNG"));
			image.setImage(image.getImage().getScaledInstance(32, 32,Image.SCALE_DEFAULT ));
			cxchItem.setIcon(image);

			cxchItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					//编写用于打开查询出货历史记录窗口的代码
					//使标题栏的风格也跟着一起改变...
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);

					//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {

							try {
								UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
								ChaxunchFrame cxchf=new ChaxunchFrame();
								cxchf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								cxchf.setVisible(true);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
							}
							//new MainFrame();

						}
					});


				}
			});

		}
		return cxchItem;
	}

	/**
	 * 初始化菜单项子项“查询进货历史记录”
	 * @return
	 */

	public JMenuItem getCxjhItem() {
		if(cxjhItem==null) {
			cxjhItem=new JMenuItem();
			cxjhItem.setText("查询进货历史记录");

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/chaxunjhls.PNG"));
			image.setImage(image.getImage().getScaledInstance(32, 32,Image.SCALE_DEFAULT ));
			cxjhItem.setIcon(image);

			cxjhItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//编写用于打开查询进货历史记录窗口的代码
					//使标题栏的风格也跟着一起改变...
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);

					//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							try {
								UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
								ChaxunjhFrame cxjhf=new ChaxunjhFrame();
								cxjhf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								cxjhf.setVisible(true);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
							}
							//new MainFrame();

						}
					});

				}
			});

		}
		return cxjhItem;
	}

	/**
	 * 初始化菜单项子项“查询其他历史记录”
	 * @return
	 */

	public JMenuItem getCxqtItem() {
		if(cxqtItem==null) {
			cxqtItem=new JMenuItem();
			cxqtItem.setText("查询其他历史记录");

			ImageIcon image = new ImageIcon(getClass().getResource("/resources/chaxunqtls.PNG"));
			image.setImage(image.getImage().getScaledInstance(32, 32,Image.SCALE_DEFAULT ));
			cxqtItem.setIcon(image);

			cxqtItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//编写用于打开查询其他历史记录窗口的代码
					//使标题栏的风格也跟着一起改变...
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);

					//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {


							try {
								UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
								ChaxunqtFrame cxqtf=new ChaxunqtFrame();
								cxqtf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								cxqtf.setVisible(true);

							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
							}
							//new MainFrame();

						}
					});

				}
			});

		}
		return cxqtItem;
	}

	/**
	 * 初始化菜单条MenuBar
	 */

	private void initalize() {
		this.setSize(new Dimension(1366,50));//设置尺寸
		add(getJinhuo_Menu());
		add(getChuhuo_Menu());
		add(getZaiku_Menu());
		add(getRenyuan_Menu());
		add(getShangpin_Menu());
		add(getChaxun_Menu());
	}

	/**
	 * 构造方法
	 * @param desktopPanel
	 * @param label
	 */

	public MenuBar(JDesktopPane desktopPanel,JLabel label) {
		super();
		initalize();
	}

}  
