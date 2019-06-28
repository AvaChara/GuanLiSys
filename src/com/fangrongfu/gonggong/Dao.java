package com.fangrongfu.gonggong;

import java.sql.*;
import java.util.Date;
import java.util.UUID;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.fangrongfu.chuhuoguanli.ChuhuoFrame;
import com.fangrongfu.chuhuoguanli.ChuhuodanFrame;
import com.fangrongfu.dengluzhuceFrame.LoginDialog;
import com.fangrongfu.renyuanxinxiguanli.CzryxxFrame1;
import com.fangrongfu.renyuanxinxiguanli.XgryxxFrame1;
import com.fangrongfu.shangpinxinxiguanli.CzspxxFrame;
import com.fangrongfu.shangpinxinxiguanli.CzspxxFrame1;
import com.fangrongfu.shangpinxinxiguanli.XgspxxFrame1;

/**
 * 在Dao类中实现数据的驱动、连接、关闭和多个曹所数据库的方法这些方法包括不同数据表的操作方法
 * @author frf
 *
 */

public class Dao {

	//连接数据库，端口号：8889，数据库名：GuanLi，用户名：root，密码：root
	protected static String dbClassName="com.mysql.jdbc.Driver";//定义数据库驱动类的名称
	protected static String dbUrl="jdbc:mysql://localhost:8889/GuanLi?useUnicode=true&characterEncoding=utf8";//定义访问数据的URL，并设置可识别汉字
	protected static String dbUser="root";//定义访问数据库的用户名
	protected static String dbPwd="root";//定义访问数据库的密码

	public static Connection conn=null;//声明数据库的连接对象

	static {//在静态代码段中初始化Dao类，实现数据库的驱动和连接
		try {
			if(conn==null) {
				Class.forName(dbClassName).newInstance();//加载驱动程序
				conn=DriverManager.getConnection(dbUrl,dbUser,dbPwd);//1.getConnection()方法，连接MySQL数据库！！
			}
		}catch(Exception ee) {
			ee.printStackTrace();
		}
	}

	public Dao() {

	}

	/**
	 * 登陆管理，检查用户名和密码是否匹配
	 * @param name
	 * @param password
	 * @return
	 * @throws SQLException
	 */

	public static boolean checkLogin(String name,String password) throws SQLException {
		//2.创建statement类对象，用来执行SQL语句！！
		Statement statement = conn.createStatement();
		//要执行的SQL语句
		String sql = "select * from tb_renyuanlist where account='" + name + "' and password='" + password + "'";//查询语句和排序后的查询语句是放一起的

		System.out.println("select * from tb_renyuanlist where account='" + name + "' and password='" + password + "'");

		//3.ResultSet类，用来存放获取的结果集！！执行SQL查询
		ResultSet rs =statement.executeQuery(sql);
		return rs.next();
	}

	/**
	 * 获得人员职位，方便设置系统功能使用权限
	 * @param name
	 * @return
	 * @throws SQLException
	 */

	public static String getusertype(String name) throws SQLException {
		//2.创建statement类对象，用来执行SQL语句！！
		Statement statement = conn.createStatement();
		//要执行的SQL语句
		String sql = "select * from tb_renyuanlist where account='" + name + "'";//查询语句和排序后的查询语句是放一起的

		System.out.println("select * from tb_renyuanlist where account='" + name + "'");

		//3.ResultSet类，用来存放获取的结果集！！执行SQL查询
		ResultSet rs =statement.executeQuery(sql);
		rs.next();//很重要不要漏了
		return rs.getString("worktype");
	}

	/**
	 * 验证注册的人员信息（只检测是否重名）
	 * @param nameStr
	 * @return
	 * @throws SQLException
	 */

	public static boolean zhuce1(String nameStr) throws SQLException {

		Statement statement;
		//2.创建statement类对象，用来执行SQL语句！！
		statement = conn.createStatement();
		//要执行的SQL语句
		String sql = "select * from tb_renyuanlist where name='" + nameStr + "'";//查询语句和排序后的查询语句是放一起的

		System.out.println("select * from tb_renyuanlist where name='" + nameStr + "'");

		//3.ResultSet类，用来存放获取的结果集！！执行SQL查询
		ResultSet rs =statement.executeQuery(sql);
		return rs.next();

	}

	/**
	 * 验证注册的用户名密码
	 * @param nameStr
	 * @return
	 * @throws SQLException
	 */

	public static boolean zhuce2(String nameStr) throws SQLException {

		Statement statement;
		//2.创建statement类对象，用来执行SQL语句！！
		statement = conn.createStatement();
		//要执行的SQL语句
		String sql = "select * from tb_renyuanlist where account='" + nameStr + "'";//查询语句和排序后的查询语句是放一起的

		System.out.println( "select * from tb_renyuanlist where account='" + nameStr + "'");

		//3.ResultSet类，用来存放获取的结果集！！执行SQL查询
		ResultSet rs =statement.executeQuery(sql);
		return rs.next();

	}

	/**
	 * 增加验证合格新用户的用户名密码信息
	 * @param nameStr
	 * @param passwordStr
	 */

	public static void zhuce3(String id,String nameStr,String passwordStr){
		//添加一条记录
		PreparedStatement psql;
		ResultSet res;
		//预处理添加数据，其中有两个参数--“？”

		try {
			//将信息插入到指定记录的某一字段，这个就是对数据库中已有的数据进行更新。使用update 语句。标准语法：update 表名 set 字段名 = 要更改的信息，字段名2 = 要更改的信息where (如果指定记录，即配合 where 子句)
			psql = conn.prepareStatement("update tb_renyuanlist set account='" + nameStr + "' ,password='" + passwordStr + "' where id='" + id + "'");

			System.out.println("update tb_renyuanlist set account='" + nameStr + "' ,password='" + passwordStr + "' where id='" + id + "'");

			psql.executeUpdate();//执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 增加商品信息
	 * @param idStr
	 * @param nameStr
	 * @param statueStr
	 * @param typeStr
	 * @param dateStr
	 * @param deadtimeStr
	 * @param produceStr
	 * @param moneyStr
	 * @param numberStr
	 */

	public static void zjspxx(String idStr,String nameStr,String statueStr,String typeStr,String dateStr,String deadtimeStr,String produceStr,String moneyStr,String numberStr) {
		//添加一条记录
		PreparedStatement psql;
		ResultSet res;
		//预处理添加数据，其中有两个参数--“？”

		try {
			psql = conn.prepareStatement("insert into tb_shangpinlist (id,name,statue,type,date,deadtime,produce,money,number) "+"values(?,?,?,?,?,?,?,?,?)");


			psql.setString(1,idStr);//设置参数1，创建id为idStr的数据
			psql.setString(2,nameStr);//设置参数2，创建name为nameStr的数据
			psql.setString(3,statueStr);
			psql.setString(4,typeStr);
			psql.setString(5,dateStr);
			psql.setString(6,deadtimeStr);
			psql.setString(7,produceStr);
			psql.setString(8,moneyStr);
			psql.setString(9,numberStr);
			psql.executeUpdate();//执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 删除商品信息
	 * @param idStr
	 * @param nameStr
	 */

	public static void scspxx(String idStr,String nameStr){
		PreparedStatement psql2;
		try {
			psql2 = conn.prepareStatement("delete from tb_shangpinlist where id= ? or name= ?");//根据商品编号或者商品名称删除一条记录

			System.out.println("delete from tb_shangpinlist where id= "+idStr+" or name= "+nameStr+";");

			psql2.setString(1,idStr);//设置参数1，创建id为idStr的数据
			psql2.setString(2,nameStr);//设置参数2，创建name为nameStr的数据
			psql2.executeUpdate();
			psql2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 查找商品信息
	 * @param idStr
	 * @param nameStr
	 */

	public static void czspxx(String idStr,String nameStr) {

		//PreparedStatement psql3;
		try {
			//2.创建statement类对象，用来执行SQL语句！！
			Statement statement = conn.createStatement();
			//要执行的SQL语句
			String sql = "select *from tb_shangpinlist where id= '" +idStr+"' or name= '"+nameStr+"'";//查询语句和排序后的查询语句是放一起的

			System.out.println("select *from tb_shangpinlist where id= '" +idStr+"' or name= '"+nameStr+"'");

			//3.ResultSet类，用来存放获取的结果集！！
			ResultSet rs = statement.executeQuery(sql);

			String[] columnNames= {"编号","名称","状态","类别","生产日期","有效日期","供应商","单价","数量"};//定义表格列名数组

			String id;
			String name;
			String statue;
			String type;
			String date;
			String deadtime;
			String produce;
			String money;
			String number;

			rs.next();//很重要，不要忘了这一句
			id=rs.getString("id");
			name=rs.getString("name");
			statue=rs.getString("statue");
			type=rs.getString("type");
			date=rs.getString("date");
			deadtime=rs.getString("deadtime");
			produce=rs.getString("produce");
			money=rs.getString("money");
			number=rs.getString("number");

			String[][] tableValues= {{id,name,statue,type,date,deadtime,produce,money,number}};

			rs.close();
			//conn.close();
			//编写用于打开显示商品信息窗口的代码
			//使标题栏的风格也跟着一起改变...
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);

			//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {


					try {
						UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
						CzspxxFrame1 czspf1=new CzspxxFrame1(columnNames,tableValues);
						czspf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						czspf1.setVisible(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
					}
					//new MainFrame();

				}
			});

		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	/**
	 * 显示修改商品信息的界面
	 * @param idStr
	 * @param nameStr
	 */

	public static void xgspxx(String idStr,String nameStr) {

		try {
			//2.创建statement类对象，用来执行SQL语句！！
			Statement statement = conn.createStatement();
			//要执行的SQL语句
			String sql = "select *from tb_shangpinlist where id= '" +idStr+"' or name= '"+nameStr+"'";//查询语句和排序后的查询语句是放一起的

			System.out.println("select *from tb_shangpinlist where id= '" +idStr+"' or name= '"+nameStr+"'");

			//3.ResultSet类，用来存放获取的结果集！！
			ResultSet rs = statement.executeQuery(sql);

			String id;
			String name;
			String statue;
			String type;
			String date;
			String deadtime;
			String produce;
			String money;
			String number;

			rs.next();//很重要，不要忘了这一句
			id=rs.getString("id");
			name=rs.getString("name");
			statue=rs.getString("statue");
			type=rs.getString("type");
			date=rs.getString("date");
			deadtime=rs.getString("deadtime");
			produce=rs.getString("produce");
			money=rs.getString("money");
			number=rs.getString("number");

			rs.close();
			//conn.close();
			//编写用于打开显示商品信息窗口的代码
			//使标题栏的风格也跟着一起改变...
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);

			//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {


					try {
						UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
						XgspxxFrame1 xgspf1=new XgspxxFrame1(id,name,statue,type,date,deadtime,produce,money,number);
						xgspf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						xgspf1.setVisible(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
					}
					//new MainFrame();

				}
			});

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 修改商品信息
	 * @param idStr
	 * @param nameStr
	 */

	public static void xgspxx1(String idStr,String nameStr,String statueStr,String typeStr,String dateStr,String deadtimeStr,String produceStr,String moneyStr,String numberStr) {

		PreparedStatement psql4;
		try {
			psql4 = conn.prepareStatement("update tb_shangpinlist set statue = ? , type = ? , date = ? , deadtime = ? , produce = ? , money = ? , number = ? where id = ? or name = ?");

			psql4.setString(1,statueStr);
			psql4.setString(2,typeStr);
			psql4.setString(3,dateStr);
			psql4.setString(4,deadtimeStr);
			psql4.setString(5,produceStr);
			psql4.setString(6,moneyStr);
			psql4.setString(7,numberStr);
			psql4.setString(8,idStr);
			psql4.setString(9,nameStr);
			psql4.executeUpdate();
			//conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 增加人员信息
	 * @param idStr
	 * @param nameStr
	 * @param sexStr
	 * @param dateStr
	 * @param phonenumberStr
	 * @param addressStr
	 * @param worktypeStr
	 * @param statueStr
	 */

	public static void zjryxx(String idStr,String nameStr,String sexStr,String dateStr,String phonenumberStr,String addressStr,String worktypeStr,String statueStr,String accountStr,String passwordStr) {
		//添加一条记录
		PreparedStatement psql;
		ResultSet res;
		//预处理添加数据，其中有两个参数--“？”

		try {
			psql = conn.prepareStatement("insert into tb_renyuanlist (id,name,sex,date,phonenumber,address,worktype,statue,account,password) "+"values(?,?,?,?,?,?,?,?,?,?)");

			System.out.println("insert into tb_renyuanlist (id,name,sex,date,phonenumber,address,worktype,statue,account,password) "+
					"values("+idStr+","+nameStr+","+sexStr+","+dateStr+","+phonenumberStr+","+addressStr+","+worktypeStr+","+statueStr+","+accountStr+","+passwordStr+")");

			psql.setString(1,idStr);//设置参数1，创建id为idStr的数据
			psql.setString(2,nameStr);//设置参数2，创建name为nameStr的数据
			psql.setString(3,sexStr);
			psql.setString(4,dateStr);
			psql.setString(5,phonenumberStr);
			psql.setString(6,addressStr);
			psql.setString(7,worktypeStr);
			psql.setString(8,statueStr);
			psql.setString(9,accountStr);
			psql.setString(10,passwordStr);
			psql.executeUpdate();//执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 删除人员信息
	 * @param idStr
	 * @param nameStr
	 */

	public static void scryxx(String idStr,String nameStr){
		PreparedStatement psql2;
		try {
			psql2 = conn.prepareStatement("delete from tb_renyuanlist where id= ? or name= ?");//根据人员编号或者人员姓名删除一条记录
			psql2.setString(1,idStr);//设置参数1，创建id为idStr的数据
			psql2.setString(2,nameStr);//设置参数2，创建name为nameStr的数据
			psql2.executeUpdate();
			psql2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 查找人员信息
	 */

	public static void czryxx(String idStr,String nameStr) {

		//PreparedStatement psql3;
		try {
			//2.创建statement类对象，用来执行SQL语句！！
			Statement statement = conn.createStatement();
			//要执行的SQL语句
			String sql = "select *from tb_renyuanlist where id= '" +idStr+"' or name= '"+nameStr+"'";//查询语句和排序后的查询语句是放一起的

			System.out.println("select *from tb_renyuanlist where id= '" +idStr+"' or name= '"+nameStr+"'");

			//3.ResultSet类，用来存放获取的结果集！！
			ResultSet rs = statement.executeQuery(sql);

			String[] columnNames= {"编号","姓名","性别","出生日期","手机号码","邮箱地址","工作岗位","在职状态"};//定义表格列名数组
			String id;
			String name;
			String sex;
			String date;
			String phonenumber;
			String developaddress;
			String worktype;
			String statue;

			rs.next();//很重要，不要忘了这一句
			id=rs.getString("id");
			name=rs.getString("name");
			sex=rs.getString("sex");
			date=rs.getString("date");
			phonenumber=rs.getString("phonenumber");
			developaddress=rs.getString("developaddress");
			worktype=rs.getString("worktype");
			statue=rs.getString("statue");

			String[][] tableValues= {{id,name,sex,date,phonenumber,developaddress,worktype,statue}};

			rs.close();
			//conn.close();
			//编写用于打开显示商品信息窗口的代码
			//使标题栏的风格也跟着一起改变...
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);

			//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {


					try {
						UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
						CzryxxFrame1 czryf1=new CzryxxFrame1(columnNames,tableValues);
						czryf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						czryf1.setVisible(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
					}
					//new MainFrame();

				}
			});

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 显示修改人员信息的界面
	 * @param idStr
	 * @param nameStr
	 */

	public static void xgryxx(String idStr,String nameStr) {

		try {
			//2.创建statement类对象，用来执行SQL语句！！
			Statement statement = conn.createStatement();
			//要执行的SQL语句
			String sql = "select *from tb_renyuanlist where id= '" +idStr+"' or name= '"+nameStr+"'";//查询语句和排序后的查询语句是放一起的

			System.out.println("select *from tb_renyuanlist where id= '" +idStr+"' or name= '"+nameStr+"'");

			//3.ResultSet类，用来存放获取的结果集！！
			ResultSet rs = statement.executeQuery(sql);

			String id;
			String name;
			String sex;
			String date;
			String phonenumber;
			String developaddress;
			String worktype;
			String statue;

			rs.next();//很重要，不要忘了这一句
			id=rs.getString("id");
			name=rs.getString("name");
			sex=rs.getString("sex");
			date=rs.getString("date");
			phonenumber=rs.getString("phonenumber");
			developaddress=rs.getString("developaddress");
			worktype=rs.getString("worktype");
			statue=rs.getString("statue");

			rs.close();
			//conn.close();
			//编写用于打开显示商品信息窗口的代码
			//使标题栏的风格也跟着一起改变...
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);

			//必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {


					try {
						UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
						XgryxxFrame1 xgryf1=new XgryxxFrame1(id,name,sex,date,phonenumber,developaddress,worktype,statue);
						xgryf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						xgryf1.setVisible(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Substance Graphite failed to initialize");
					}
					//new MainFrame();

				}
			});

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 修改人员信息
	 * @param idStr
	 * @param nameStr
	 * @param sexStr
	 * @param dateStr
	 * @param phonenumberStr
	 * @param developaddressStr
	 * @param worktypeStr
	 * @param statueStr
	 */

	public static void xgryxx1(String idStr,String nameStr,String sexStr,String dateStr,String phonenumberStr,String developaddressStr,String worktypeStr,String statueStr) {

		PreparedStatement psql4;
		try {
			psql4 = conn.prepareStatement("update tb_renyuanlist set sex = ? , date = ? , phonenumber = ? , developaddress = ? , worktype = ? , statue = ? where id = ? or name = ?");
			psql4.setString(1,sexStr);
			psql4.setString(2,dateStr);
			psql4.setString(3,phonenumberStr);
			psql4.setString(4,developaddressStr);
			psql4.setString(5,worktypeStr);
			psql4.setString(6,statueStr);
			psql4.setString(7,idStr);
			psql4.setString(8,nameStr);
			psql4.executeUpdate();
			//conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 查看库存信息
	 * @param Str
	 * @return
	 * @throws SQLException
	 */

	public static DefaultTableModel cxkcxx(String Str) throws SQLException {
		//PreparedStatement psql3;
		//2.创建statement类对象，用来执行SQL语句！！
		Statement statement = conn.createStatement();
		//要执行的SQL语句
		String sql = "select *from tb_shangpinlist where id= '" + Str + "' or name='" +Str + "' or statue='" + Str + "' or type='" + Str + "' or produce='" + Str +"'";//查询语句和排序后的查询语句是放一起的
		//3.ResultSet类，用来存放获取的结果集！！
		ResultSet rs = statement.executeQuery(sql);
		Vector columnNames=new Vector();
		Vector tableValues=new Vector();

		DefaultTableModel model = new DefaultTableModel(tableValues,columnNames);//表格的数据模型，操作数据

		columnNames.add("编号");
		columnNames.add("名称");
		columnNames.add("状态");
		columnNames.add("类别");
		columnNames.add("生产日期");
		columnNames.add("有效日期");
		columnNames.add("供应商");
		columnNames.add("单价");
		columnNames.add("数量");

		String id;
		String name;
		String statue;
		String type;
		String date;
		String deadtime;
		String produce;
		String money;
		String number;


		while(rs.next()) {//很重要，不要忘了这一句

			Vector rowValue=new Vector();
			id=rs.getString("id");
			name=rs.getString("name");
			statue=rs.getString("statue");
			type=rs.getString("type");
			date=rs.getString("date");
			deadtime=rs.getString("deadtime");
			produce=rs.getString("produce");
			money=rs.getString("money");
			number=rs.getString("number");

			rowValue.add(id);
			rowValue.add(name);
			rowValue.add(statue);
			rowValue.add(type);
			rowValue.add(date);
			rowValue.add(deadtime);
			rowValue.add(produce);
			rowValue.add(money);
			rowValue.add(number);
			//rowValue.add(tianjiaButton);

			tableValues.add(rowValue);
		}

		rs.close();
		//conn.close();
		return model;

	}

	/**
	 * 出货管理查看库存信息
	 * @param Str
	 * @return
	 * @throws SQLException
	 */

	public static DefaultTableModel cxkcxx1(String Str) throws SQLException {
		//PreparedStatement psql3;
		//2.创建statement类对象，用来执行SQL语句！！
		Statement statement = conn.createStatement();
		//要执行的SQL语句
		String sql = "select *from tb_shangpinlist where id= '" + Str + "' or name='" +Str + "' or statue='" + Str + "' or type='" + Str + "' or produce='" + Str +"'";//查询语句和排序后的查询语句是放一起的
		//3.ResultSet类，用来存放获取的结果集！！
		ResultSet rs = statement.executeQuery(sql);
		Vector columnNames=new Vector();
		//Vector rowValue1=new Vector();
		Vector tableValues=new Vector();

		DefaultTableModel model = new DefaultTableModel(tableValues,columnNames);//表格的数据模型，操作数据


		columnNames.addElement("编号");
		columnNames.addElement("名称");
		columnNames.addElement("状态");
		columnNames.addElement("类别");
		columnNames.addElement("生产日期");
		columnNames.addElement("有效日期");
		columnNames.addElement("供应商");
		columnNames.addElement("单价");
		columnNames.addElement("数量");
		columnNames.addElement("添加");

		String id;
		String name;
		String statue;
		String type;
		String date;
		String deadtime;
		String produce;
		String money;
		String number;


		while(rs.next()) {//很重要，不要忘了这一句

			Vector rowValue=new Vector();
			id=rs.getString("id");
			name=rs.getString("name");
			statue=rs.getString("statue");
			type=rs.getString("type");
			date=rs.getString("date");
			deadtime=rs.getString("deadtime");
			produce=rs.getString("produce");
			money=rs.getString("money");
			number=rs.getString("number");

			//JButton tianjiaButton=new JButton("添加");

			rowValue.addElement(id);
			rowValue.addElement(name);
			rowValue.addElement(statue);
			rowValue.addElement(type);
			rowValue.addElement(date);
			rowValue.addElement(deadtime);
			rowValue.addElement(produce);
			rowValue.addElement(money);
			rowValue.addElement(number);
			//rowValue.addElement(tianjiaButton);
			rowValue.addElement("添加");

			tableValues.addElement(rowValue);
		}

		rs.close();
		//conn.close();
		return model;

	}

	/**
	 * 出货后，修改数据库的商品信息
	 * @param Str
	 * @param num
	 * @return
	 * @throws SQLException
	 */

	public static void cxkcxx2(String Str,String num) throws SQLException {

		//预处理更新（修改）数据
		PreparedStatement psql3;
		psql3 = conn.prepareStatement("update tb_shangpinlist set number = ? where id = ?");
		psql3.setString(1, num);
		psql3.setString(2,Str);
		psql3.executeUpdate();



	}

	/**
	 * 生成出货单：生成出货单表格的表格模型的数据
	 * 生成缺货单：生成缺货单表格的表格模型的数据
	 * 生成积货单：生成积货单表格的表格模型的数据
	 * @param Str
	 * @param tianjiaNum
	 * @return
	 * @throws SQLException
	 */

	public static Vector scchdxx(String Str,String tianjiaNum) throws SQLException {
		//PreparedStatement psql3;
		//2.创建statement类对象，用来执行SQL语句！！
		Statement statement = conn.createStatement();
		//要执行的SQL语句
		String sql = "select *from tb_shangpinlist where id= '" + Str +"'";//查询语句和排序后的查询语句是放一起的
		//3.ResultSet类，用来存放获取的结果集！！
		ResultSet rs = statement.executeQuery(sql);

		String id;
		String name;
		String statue;
		String type;
		String date;
		String deadtime;
		String produce;
		String money;
		String number;

		rs.next();

		Vector rowValue=new Vector();
		id=rs.getString("id");
		name=rs.getString("name");
		statue=rs.getString("statue");
		type=rs.getString("type");
		date=rs.getString("date");
		deadtime=rs.getString("deadtime");
		produce=rs.getString("produce");
		money=rs.getString("money");
		number=tianjiaNum;

		rowValue.add(id);
		rowValue.add(name);
		rowValue.add(statue);
		rowValue.add(type);
		rowValue.add(date);
		rowValue.add(deadtime);
		rowValue.add(produce);
		rowValue.add(money);
		rowValue.add(number);

		//tableValues.add(rowValue);

		rs.close();
		return rowValue;
	}

	/*
	 * 缺货管理查看库存信息，检索条件和出货管理是不一样的
	 */

	public static DefaultTableModel cxkcxx3(String Str) throws SQLException {
		//PreparedStatement psql3;
		//2.创建statement类对象，用来执行SQL语句！！
		Statement statement = conn.createStatement();
		//要执行的SQL语句
		String sql = "select *from tb_shangpinlist where (id= '" + Str + "' and number<=10) or (name='" +Str + "' and number<=10) or (statue='" + Str + "' and number<=10) or (type='" + Str + "' and number<=10) or (produce='" + Str +"' and number<=10)";//查询语句和排序后的查询语句是放一起的
		//3.ResultSet类，用来存放获取的结果集！！
		ResultSet rs = statement.executeQuery(sql);
		Vector columnNames=new Vector();
		Vector tableValues=new Vector();

		DefaultTableModel model = new DefaultTableModel(tableValues,columnNames);//表格的数据模型，操作数据


		columnNames.addElement("编号");
		columnNames.addElement("名称");
		columnNames.addElement("状态");
		columnNames.addElement("类别");
		columnNames.addElement("生产日期");
		columnNames.addElement("有效日期");
		columnNames.addElement("供应商");
		columnNames.addElement("单价");
		columnNames.addElement("数量");
		columnNames.addElement("添加");

		String id;
		String name;
		String statue;
		String type;
		String date;
		String deadtime;
		String produce;
		String money;
		String number;


		while(rs.next()) {//很重要，不要忘了这一句

			Vector rowValue=new Vector();
			id=rs.getString("id");
			name=rs.getString("name");
			statue=rs.getString("statue");
			type=rs.getString("type");
			date=rs.getString("date");
			deadtime=rs.getString("deadtime");
			produce=rs.getString("produce");
			money=rs.getString("money");
			number=rs.getString("number");

			//JButton tianjiaButton=new JButton("添加");

			rowValue.addElement(id);
			rowValue.addElement(name);
			rowValue.addElement(statue);
			rowValue.addElement(type);
			rowValue.addElement(date);
			rowValue.addElement(deadtime);
			rowValue.addElement(produce);
			rowValue.addElement(money);
			rowValue.addElement(number);
			//rowValue.addElement(tianjiaButton);
			rowValue.addElement("添加");

			tableValues.addElement(rowValue);
		}

		rs.close();
		//conn.close();
		return model;

	}

	/*
	 * 积货管理查看库存信息，检索条件和出货管理和缺货管理是不一样的
	 */

	public static DefaultTableModel cxkcxx4(String Str) throws SQLException {
		//PreparedStatement psql3;
		//2.创建statement类对象，用来执行SQL语句！！
		Statement statement = conn.createStatement();
		//要执行的SQL语句
		String sql = "select *from tb_shangpinlist where (id= '" + Str + "' and number>=100) or (name='" +Str + "' and number>=100) or (statue='" + Str + "' and number>=100) or (type='" + Str + "' and number>=100) or (produce='" + Str +"' and number>=100)";//查询语句和排序后的查询语句是放一起的
		//3.ResultSet类，用来存放获取的结果集！！
		ResultSet rs = statement.executeQuery(sql);
		Vector columnNames=new Vector();
		Vector tableValues=new Vector();

		DefaultTableModel model = new DefaultTableModel(tableValues,columnNames);//表格的数据模型，操作数据

		columnNames.addElement("编号");
		columnNames.addElement("名称");
		columnNames.addElement("状态");
		columnNames.addElement("类别");
		columnNames.addElement("生产日期");
		columnNames.addElement("有效日期");
		columnNames.addElement("供应商");
		columnNames.addElement("单价");
		columnNames.addElement("数量");
		columnNames.addElement("添加");

		String id;
		String name;
		String statue;
		String type;
		String date;
		String deadtime;
		String produce;
		String money;
		String number;


		while(rs.next()) {//很重要，不要忘了这一句

			Vector rowValue=new Vector();
			id=rs.getString("id");
			name=rs.getString("name");
			statue=rs.getString("statue");
			type=rs.getString("type");
			date=rs.getString("date");
			deadtime=rs.getString("deadtime");
			produce=rs.getString("produce");
			money=rs.getString("money");
			number=rs.getString("number");

			//JButton tianjiaButton=new JButton("添加");

			rowValue.addElement(id);
			rowValue.addElement(name);
			rowValue.addElement(statue);
			rowValue.addElement(type);
			rowValue.addElement(date);
			rowValue.addElement(deadtime);
			rowValue.addElement(produce);
			rowValue.addElement(money);
			rowValue.addElement(number);
			//rowValue.addElement(tianjiaButton);
			rowValue.addElement("添加");

			tableValues.addElement(rowValue);
		}

		rs.close();
		//conn.close();
		return model;

	}

	/**
	 * 查询出货历史记录
	 * @param Str
	 * @return
	 * @throws SQLException
	 */

	public static DefaultTableModel cxchrecord(String Str) throws SQLException {
		//PreparedStatement psql3;
		//2.创建statement类对象，用来执行SQL语句！！
		Statement statement = conn.createStatement();
		//要执行的SQL语句
		String sql = "select *from tb_recordlist where (userid= '" + Str + "' and optype='out') or (shangpinid='" + Str + "' and optype='out') or (date='" + Str + "' and optype='out')";//查询语句和排序后的查询语句是放一起的
		//3.ResultSet类，用来存放获取的结果集！！
		ResultSet rs = statement.executeQuery(sql);
		Vector columnNames=new Vector();
		//Vector rowValue1=new Vector();
		Vector tableValues=new Vector();

		DefaultTableModel model = new DefaultTableModel(tableValues,columnNames);//表格的数据模型，操作数据

		columnNames.add("记录编号");
		columnNames.add("操作类型");
		columnNames.add("人员账号");
		columnNames.add("出货单号");
		columnNames.add("操作日期");
		//columnNames.add("按钮");

		String recordid;
		String optype;
		String userid;
		String shangpinid;
		String date;

		while(rs.next()) {//很重要，不要忘了这一句

			Vector rowValue=new Vector();
			recordid=rs.getString("recordid");
			optype=rs.getString("optype");
			userid=rs.getString("userid");
			shangpinid=rs.getString("shangpinid");
			date=rs.getString("date");
			//JButton tianjiaButton=new JButton("添加");

			rowValue.add(recordid);
			rowValue.add(optype);
			rowValue.add(userid);
			rowValue.add(shangpinid);
			rowValue.add(date);
			//rowValue.add(tianjiaButton);

			tableValues.add(rowValue);
		}

		rs.close();
		//conn.close();
		return model;

	}

	/**
	 * 查询进货历史记录
	 * @param Str
	 * @return
	 * @throws SQLException
	 */

	public static DefaultTableModel cxjhrecord(String Str) throws SQLException {
		//PreparedStatement psql3;
		//2.创建statement类对象，用来执行SQL语句！！
		Statement statement = conn.createStatement();
		//要执行的SQL语句
		String sql = "select *from tb_recordlist where (userid= '" + Str + "' and optype='in') or (shangpinid='" + Str + "' and optype='in') or (date='" + Str + "' and optype='in')";//查询语句和排序后的查询语句是放一起的

		System.out.println("select *from tb_recordlist where (userid= '" + Str + "' and optype='in') or (shangpinid='" + Str + "' and optype='in') or (date='" + Str + "' and optype='in')");

		//3.ResultSet类，用来存放获取的结果集！！
		ResultSet rs = statement.executeQuery(sql);
		Vector columnNames=new Vector();
		//Vector rowValue1=new Vector();
		Vector tableValues=new Vector();

		DefaultTableModel model = new DefaultTableModel(tableValues,columnNames);//表格的数据模型，操作数据

		columnNames.add("记录编号");
		columnNames.add("操作类型");
		columnNames.add("人员账号");
		columnNames.add("进货单号");
		columnNames.add("操作日期");
		//columnNames.add("按钮");

		String recordid;
		String optype;
		String userid;
		String shangpinid;
		String date;

		while(rs.next()) {//很重要，不要忘了这一句

			Vector rowValue=new Vector();
			recordid=rs.getString("recordid");
			optype=rs.getString("optype");
			userid=rs.getString("userid");
			shangpinid=rs.getString("shangpinid");
			date=rs.getString("date");
			//JButton tianjiaButton=new JButton("添加");

			rowValue.add(recordid);
			rowValue.add(optype);
			rowValue.add(userid);
			rowValue.add(shangpinid);
			rowValue.add(date);
			//rowValue.add(tianjiaButton);

			tableValues.add(rowValue);
		}

		rs.close();
		//conn.close();
		return model;

	}

	/**
	 * 查询其他历史记录
	 * @param Str
	 * @return
	 * @throws SQLException
	 */

	public static DefaultTableModel cxqtrecord(String Str) throws SQLException {
		//PreparedStatement psql3;
		//2.创建statement类对象，用来执行SQL语句！！
		Statement statement = conn.createStatement();
		//要执行的SQL语句
		String sql = "select *from tb_recordlist where (userid= '" + Str + "' and optype!='out' and optype!='in') or (shangpinid='" + Str + "' and optype!='out' and optype!='in') or (date='" + Str + "' and optype!='out' and optype!='in')";//查询语句和排序后的查询语句是放一起的
		//3.ResultSet类，用来存放获取的结果集！！
		ResultSet rs = statement.executeQuery(sql);
		//psql3 = conn.prepareStatement("select *from tb_shangpinlist where id= ? or name= ?");//根据商品编号或者商品名称查找一条记录
		//psql3.setString(1,idStr);//设置参数1，创建id为idStr的数据
		//psql3.setString(2,nameStr);//设置参数2，创建name为nameStr的数据
		//ResultSet rs = psql3.executeQuery();
		//psql3.executeUpdate();
		//psql3.close();
		Vector columnNames=new Vector();
		//Vector rowValue1=new Vector();
		Vector tableValues=new Vector();

		DefaultTableModel model = new DefaultTableModel(tableValues,columnNames);//表格的数据模型，操作数据

		columnNames.add("记录编号");
		columnNames.add("操作类型");
		columnNames.add("人员账号");
		columnNames.add("商品编号");
		columnNames.add("操作日期");
		//columnNames.add("按钮");

		String recordid;
		String optype;
		String userid;
		String shangpinid;
		String date;

		while(rs.next()) {//很重要，不要忘了这一句

			Vector rowValue=new Vector();
			recordid=rs.getString("recordid");
			optype=rs.getString("optype");
			userid=rs.getString("userid");
			shangpinid=rs.getString("shangpinid");
			date=rs.getString("date");
			//JButton tianjiaButton=new JButton("添加");

			rowValue.add(recordid);
			rowValue.add(optype);
			rowValue.add(userid);
			rowValue.add(shangpinid);
			rowValue.add(date);
			//rowValue.add(tianjiaButton);

			tableValues.add(rowValue);
		}

		rs.close();
		//conn.close();
		return model;

	}

	/**
	 * 添加登陆历史记录
	 * @param userid
	 */

	public static void loginrecord(String userid) {
		//添加一条记录
		PreparedStatement psql;
		ResultSet res;
		//预处理添加数据，其中有两个参数--“？”

		try {
			psql = conn.prepareStatement("insert into tb_recordlist (optype,userid,shangpinid,date) "+"values(?,?,?,?)");
			//recordid在数据库里自动生成
			//psql.setString(1,UUID.randomUUID().toString().replace("-",""));//设置参数1，创建id为idStr的数据
			psql.setString(1,"login");//设置参数2，创建name为nameStr的数据
			psql.setString(2,userid);
			psql.setString(3,"null");

			Date day=new Date();//这里用java.util.Date，不要用java.sql.Date
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			String createdate = dateFormat2.format(day);
			java.util.Date myDate2;
			try {
				myDate2 = dateFormat2.parse(createdate);
				psql.setDate(4,new java.sql.Date(myDate2.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//psql.setString(5,dateStr);
			psql.executeUpdate();//执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加注册历史记录
	 * @param userid
	 */

	public static void zhucerecord(String userid) {
		//添加一条记录
		PreparedStatement psql;
		ResultSet res;
		//预处理添加数据，其中有两个参数--“？”

		try {
			psql = conn.prepareStatement("insert into tb_recordlist (optype,userid,shangpinid,date) "+"values(?,?,?,?)");
			//recordid在数据库里自动生成
			//psql.setString(1,UUID.randomUUID().toString().replace("-",""));//设置参数1，创建id为idStr的数据
			psql.setString(1,"register");//设置参数2，创建name为nameStr的数据
			psql.setString(2,userid);
			psql.setString(3,"null");

			Date day=new Date();//这里用java.util.Date，不要用java.sql.Date
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			String createdate = dateFormat2.format(day);
			java.util.Date myDate2;
			try {
				myDate2 = dateFormat2.parse(createdate);
				psql.setDate(4,new java.sql.Date(myDate2.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//psql.setString(5,dateStr);
			psql.executeUpdate();//执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加出货历史记录
	 * @param userid
	 */

	public static void chuhuorecord(String userid,String shangpinid) {
		//添加一条记录
		PreparedStatement psql;
		ResultSet res;
		//预处理添加数据，其中有两个参数--“？”

		try {
			psql = conn.prepareStatement("insert into tb_recordlist (optype,userid,shangpinid,date) "+"values(?,?,?,?)");
			//recordid在数据库里自动生成
			//psql.setString(1,UUID.randomUUID().toString().replace("-",""));//设置参数1，创建id为idStr的数据
			psql.setString(1,"out");//设置参数2，创建name为nameStr的数据
			psql.setString(2,userid);
			psql.setString(3,shangpinid);

			Date day=new Date();//这里用java.util.Date，不要用java.sql.Date
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			String createdate = dateFormat2.format(day);
			java.util.Date myDate2;
			try {
				myDate2 = dateFormat2.parse(createdate);
				psql.setDate(4,new java.sql.Date(myDate2.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//psql.setString(5,dateStr);
			psql.executeUpdate();//执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加进货历史记录
	 * @param userid
	 */

	public static void jinhuorecord(String userid,String shangpinid) {
		//添加一条记录
		PreparedStatement psql;
		ResultSet res;
		//预处理添加数据，其中有两个参数--“？”

		try {
			psql = conn.prepareStatement("insert into tb_recordlist (optype,userid,shangpinid,date) "+"values(?,?,?,?)");
			//recordid在数据库里自动生成
			//psql.setString(1,UUID.randomUUID().toString().replace("-",""));//设置参数1，创建id为idStr的数据
			psql.setString(1,"in");//设置参数2，创建name为nameStr的数据
			psql.setString(2,userid);
			psql.setString(3,shangpinid);

			Date day=new Date();//这里用java.util.Date，不要用java.sql.Date
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			String createdate = dateFormat2.format(day);
			java.util.Date myDate2;
			try {
				myDate2 = dateFormat2.parse(createdate);
				psql.setDate(4,new java.sql.Date(myDate2.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//psql.setString(5,dateStr);
			psql.executeUpdate();//执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加查询库存信息历史记录
	 * @param userid
	 */

	public static void chaxunkcrecord(String userid,String shangpinid) {
		//添加一条记录
		PreparedStatement psql;
		ResultSet res;
		//预处理添加数据，其中有两个参数--“？”

		try {
			psql = conn.prepareStatement("insert into tb_recordlist (optype,userid,shangpinid,date) "+"values(?,?,?,?)");
			//recordid在数据库里自动生成
			//psql.setString(1,UUID.randomUUID().toString().replace("-",""));//设置参数1，创建id为idStr的数据
			psql.setString(1,"seekstock");//设置参数2，创建name为nameStr的数据
			psql.setString(2,userid);
			psql.setString(3,shangpinid);

			Date day=new Date();//这里用java.util.Date，不要用java.sql.Date
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			String createdate = dateFormat2.format(day);
			java.util.Date myDate2;
			try {
				myDate2 = dateFormat2.parse(createdate);
				psql.setDate(4,new java.sql.Date(myDate2.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//psql.setString(5,dateStr);
			psql.executeUpdate();//执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加查询出货历史记录
	 * @param userid
	 */

	public static void chaxunchrecord(String userid,String shangpinid) {
		//添加一条记录
		PreparedStatement psql;
		ResultSet res;
		//预处理添加数据，其中有两个参数--“？”

		try {
			psql = conn.prepareStatement("insert into tb_recordlist (optype,userid,shangpinid,date) "+"values(?,?,?,?)");
			//recordid在数据库里自动生成
			//psql.setString(1,UUID.randomUUID().toString().replace("-",""));//设置参数1，创建id为idStr的数据
			psql.setString(1,"seekout");//设置参数2，创建name为nameStr的数据
			psql.setString(2,userid);
			psql.setString(3,shangpinid);

			Date day=new Date();//这里用java.util.Date，不要用java.sql.Date
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			String createdate = dateFormat2.format(day);
			java.util.Date myDate2;
			try {
				myDate2 = dateFormat2.parse(createdate);
				psql.setDate(4,new java.sql.Date(myDate2.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//psql.setString(5,dateStr);
			psql.executeUpdate();//执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加查询进货历史记录
	 * @param userid
	 */

	public static void chaxunjhrecord(String userid,String shangpinid) {
		//添加一条记录
		PreparedStatement psql;
		ResultSet res;
		//预处理添加数据，其中有两个参数--“？”

		try {
			psql = conn.prepareStatement("insert into tb_recordlist (optype,userid,shangpinid,date) "+"values(?,?,?,?)");
			//recordid在数据库里自动生成
			//psql.setString(1,UUID.randomUUID().toString().replace("-",""));//设置参数1，创建id为idStr的数据
			psql.setString(1,"seekin");//设置参数2，创建name为nameStr的数据
			psql.setString(2,userid);
			psql.setString(3,shangpinid);

			Date day=new Date();//这里用java.util.Date，不要用java.sql.Date
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			String createdate = dateFormat2.format(day);
			java.util.Date myDate2;
			try {
				myDate2 = dateFormat2.parse(createdate);
				psql.setDate(4,new java.sql.Date(myDate2.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//psql.setString(5,dateStr);
			psql.executeUpdate();//执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加查询其他历史记录
	 * @param userid
	 */

	public static void chaxunqtrecord(String userid,String shangpinid) {
		//添加一条记录
		PreparedStatement psql;
		ResultSet res;
		//预处理添加数据，其中有两个参数--“？”

		try {
			psql = conn.prepareStatement("insert into tb_recordlist (optype,userid,shangpinid,date) "+"values(?,?,?,?)");
			//recordid在数据库里自动生成
			//psql.setString(1,UUID.randomUUID().toString().replace("-",""));//设置参数1，创建id为idStr的数据
			psql.setString(1,"seekother");//设置参数2，创建name为nameStr的数据
			psql.setString(2,userid);
			psql.setString(3,shangpinid);

			Date day=new Date();//这里用java.util.Date，不要用java.sql.Date
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			String createdate = dateFormat2.format(day);
			java.util.Date myDate2;
			try {
				myDate2 = dateFormat2.parse(createdate);
				psql.setDate(4,new java.sql.Date(myDate2.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//psql.setString(5,dateStr);
			psql.executeUpdate();//执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加缺货管理历史记录
	 * @param userid
	 */

	public static void quehuorecord(String userid,String shangpinid) {
		//添加一条记录
		PreparedStatement psql;
		ResultSet res;
		//预处理添加数据，其中有两个参数--“？”

		try {
			psql = conn.prepareStatement("insert into tb_recordlist (optype,userid,shangpinid,date) "+"values(?,?,?,?)");
			//recordid在数据库里自动生成
			//psql.setString(1,UUID.randomUUID().toString().replace("-",""));//设置参数1，创建id为idStr的数据
			psql.setString(1,"lack");//设置参数2，创建name为nameStr的数据
			psql.setString(2,userid);
			psql.setString(3,shangpinid);

			Date day=new Date();//这里用java.util.Date，不要用java.sql.Date
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			String createdate = dateFormat2.format(day);
			java.util.Date myDate2;
			try {
				myDate2 = dateFormat2.parse(createdate);
				psql.setDate(4,new java.sql.Date(myDate2.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//psql.setString(5,dateStr);
			psql.executeUpdate();//执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加积货管理历史记录
	 * @param userid
	 */

	public static void jihuorecord(String userid,String shangpinid) {
		//添加一条记录
		PreparedStatement psql;
		ResultSet res;
		//预处理添加数据，其中有两个参数--“？”

		try {
			psql = conn.prepareStatement("insert into tb_recordlist (optype,userid,shangpinid,date) "+"values(?,?,?,?)");
			//recordid在数据库里自动生成
			//psql.setString(1,UUID.randomUUID().toString().replace("-",""));//设置参数1，创建id为idStr的数据
			psql.setString(1,"surplus");//设置参数2，创建name为nameStr的数据
			psql.setString(2,userid);
			psql.setString(3,shangpinid);

			Date day=new Date();//这里用java.util.Date，不要用java.sql.Date
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			String createdate = dateFormat2.format(day);
			java.util.Date myDate2;
			try {
				myDate2 = dateFormat2.parse(createdate);
				psql.setDate(4,new java.sql.Date(myDate2.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//psql.setString(5,dateStr);
			psql.executeUpdate();//执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加增加人员历史记录
	 * @param userid
	 */

	public static void zjryrecord(String userid,String shangpinid) {
		//添加一条记录
		PreparedStatement psql;
		ResultSet res;
		//预处理添加数据，其中有两个参数--“？”

		try {
			psql = conn.prepareStatement("insert into tb_recordlist (optype,userid,shangpinid,date) "+"values(?,?,?,?)");
			//recordid在数据库里自动生成
			//psql.setString(1,UUID.randomUUID().toString().replace("-",""));//设置参数1，创建id为idStr的数据
			psql.setString(1,"adduser");//设置参数2，创建name为nameStr的数据
			psql.setString(2,userid);
			psql.setString(3,shangpinid);

			Date day=new Date();//这里用java.util.Date，不要用java.sql.Date
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			String createdate = dateFormat2.format(day);
			java.util.Date myDate2;
			try {
				myDate2 = dateFormat2.parse(createdate);
				psql.setDate(4,new java.sql.Date(myDate2.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//psql.setString(5,dateStr);
			psql.executeUpdate();//执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加删除人员历史记录
	 * @param userid
	 */

	public static void scryrecord(String userid,String shangpinid) {
		//添加一条记录
		PreparedStatement psql;
		ResultSet res;
		//预处理添加数据，其中有两个参数--“？”

		try {
			psql = conn.prepareStatement("insert into tb_recordlist (optype,userid,shangpinid,date) "+"values(?,?,?,?)");
			//recordid在数据库里自动生成
			//psql.setString(1,UUID.randomUUID().toString().replace("-",""));//设置参数1，创建id为idStr的数据
			psql.setString(1,"deleteuser");//设置参数2，创建name为nameStr的数据
			psql.setString(2,userid);
			psql.setString(3,shangpinid);

			Date day=new Date();//这里用java.util.Date，不要用java.sql.Date
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			String createdate = dateFormat2.format(day);
			java.util.Date myDate2;
			try {
				myDate2 = dateFormat2.parse(createdate);
				psql.setDate(4,new java.sql.Date(myDate2.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//psql.setString(5,dateStr);
			psql.executeUpdate();//执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加查找人员历史记录
	 * @param userid
	 */

	public static void czryrecord(String userid,String shangpinid) {
		//添加一条记录
		PreparedStatement psql;
		ResultSet res;
		//预处理添加数据，其中有两个参数--“？”

		try {
			psql = conn.prepareStatement("insert into tb_recordlist (optype,userid,shangpinid,date) "+"values(?,?,?,?)");
			//recordid在数据库里自动生成
			//psql.setString(1,UUID.randomUUID().toString().replace("-",""));//设置参数1，创建id为idStr的数据
			psql.setString(1,"seekuser");//设置参数2，创建name为nameStr的数据
			psql.setString(2,userid);
			psql.setString(3,shangpinid);

			Date day=new Date();//这里用java.util.Date，不要用java.sql.Date
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			String createdate = dateFormat2.format(day);
			java.util.Date myDate2;
			try {
				myDate2 = dateFormat2.parse(createdate);
				psql.setDate(4,new java.sql.Date(myDate2.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//psql.setString(5,dateStr);
			psql.executeUpdate();//执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加修改人员历史记录
	 * @param userid
	 */

	public static void xgryrecord(String userid,String shangpinid) {
		//添加一条记录
		PreparedStatement psql;
		ResultSet res;
		//预处理添加数据，其中有两个参数--“？”

		try {
			psql = conn.prepareStatement("insert into tb_recordlist (optype,userid,shangpinid,date) "+"values(?,?,?,?)");
			//recordid在数据库里自动生成
			//psql.setString(1,UUID.randomUUID().toString().replace("-",""));//设置参数1，创建id为idStr的数据
			psql.setString(1,"amenduser");//设置参数2，创建name为nameStr的数据
			psql.setString(2,userid);
			psql.setString(3,shangpinid);

			Date day=new Date();//这里用java.util.Date，不要用java.sql.Date
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			String createdate = dateFormat2.format(day);
			java.util.Date myDate2;
			try {
				myDate2 = dateFormat2.parse(createdate);
				psql.setDate(4,new java.sql.Date(myDate2.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//psql.setString(5,dateStr);
			psql.executeUpdate();//执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加增加商品历史记录
	 * @param userid
	 */

	public static void zjsprecord(String userid,String shangpinid) {
		//添加一条记录
		PreparedStatement psql;
		ResultSet res;
		//预处理添加数据，其中有两个参数--“？”

		try {
			psql = conn.prepareStatement("insert into tb_recordlist (optype,userid,shangpinid,date) "+"values(?,?,?,?)");
			//recordid在数据库里自动生成
			//psql.setString(1,UUID.randomUUID().toString().replace("-",""));//设置参数1，创建id为idStr的数据
			psql.setString(1,"addgoods");//设置参数2，创建name为nameStr的数据
			psql.setString(2,userid);
			psql.setString(3,shangpinid);

			Date day=new Date();//这里用java.util.Date，不要用java.sql.Date
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			String createdate = dateFormat2.format(day);
			java.util.Date myDate2;
			try {
				myDate2 = dateFormat2.parse(createdate);
				psql.setDate(4,new java.sql.Date(myDate2.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//psql.setString(5,dateStr);
			psql.executeUpdate();//执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加删除人员历史记录
	 * @param userid
	 */

	public static void scsprecord(String userid,String shangpinid) {
		//添加一条记录
		PreparedStatement psql;
		ResultSet res;
		//预处理添加数据，其中有两个参数--“？”

		try {
			psql = conn.prepareStatement("insert into tb_recordlist (optype,userid,shangpinid,date) "+"values(?,?,?,?)");
			//recordid在数据库里自动生成
			//psql.setString(1,UUID.randomUUID().toString().replace("-",""));//设置参数1，创建id为idStr的数据
			psql.setString(1,"deletegoods");//设置参数2，创建name为nameStr的数据
			psql.setString(2,userid);
			psql.setString(3,shangpinid);

			Date day=new Date();//这里用java.util.Date，不要用java.sql.Date
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			String createdate = dateFormat2.format(day);
			java.util.Date myDate2;
			try {
				myDate2 = dateFormat2.parse(createdate);
				psql.setDate(4,new java.sql.Date(myDate2.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//psql.setString(5,dateStr);
			psql.executeUpdate();//执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加查找人员历史记录
	 * @param userid
	 */

	public static void czsprecord(String userid,String shangpinid) {
		//添加一条记录
		PreparedStatement psql;
		ResultSet res;
		//预处理添加数据，其中有两个参数--“？”

		try {
			psql = conn.prepareStatement("insert into tb_recordlist (optype,userid,shangpinid,date) "+"values(?,?,?,?)");
			//recordid在数据库里自动生成
			//psql.setString(1,UUID.randomUUID().toString().replace("-",""));//设置参数1，创建id为idStr的数据
			psql.setString(1,"seekgoods");//设置参数2，创建name为nameStr的数据
			psql.setString(2,userid);
			psql.setString(3,shangpinid);

			Date day=new Date();//这里用java.util.Date，不要用java.sql.Date
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			String createdate = dateFormat2.format(day);
			java.util.Date myDate2;
			try {
				myDate2 = dateFormat2.parse(createdate);
				psql.setDate(4,new java.sql.Date(myDate2.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//psql.setString(5,dateStr);
			psql.executeUpdate();//执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加修改人员历史记录
	 * @param userid
	 */

	public static void xgsprecord(String userid,String shangpinid) {
		//添加一条记录
		PreparedStatement psql;
		ResultSet res;
		//预处理添加数据，其中有两个参数--“？”

		try {
			psql = conn.prepareStatement("insert into tb_recordlist (optype,userid,shangpinid,date) "+"values(?,?,?,?)");
			//recordid在数据库里自动生成
			//psql.setString(1,UUID.randomUUID().toString().replace("-",""));//设置参数1，创建id为idStr的数据
			psql.setString(1,"amendgoods");//设置参数2，创建name为nameStr的数据
			psql.setString(2,userid);
			psql.setString(3,shangpinid);

			Date day=new Date();//这里用java.util.Date，不要用java.sql.Date
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			String createdate = dateFormat2.format(day);
			java.util.Date myDate2;
			try {
				myDate2 = dateFormat2.parse(createdate);
				psql.setDate(4,new java.sql.Date(myDate2.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//psql.setString(5,dateStr);
			psql.executeUpdate();//执行更新
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 增加出货单信息，方便出货历史记录的查询
	 */

	public static void zjchdxx(String outid,Vector tableValues) {

		String id;
		String number;

		Vector rowValues;

		//先从表格数据里读取出货商品信息
		for(int i=0;i<tableValues.size();i++) {//size()获取向量的个数
			rowValues=(Vector)tableValues.get(i);
			//while(true) {

			id=(String) rowValues.get(0);
			number=(String)  rowValues.get(8);

			//再将这些出货商品数据连同outid一起写入tb_outlist里

			//添加一条记录
			PreparedStatement psql;
			ResultSet res;
			//预处理添加数据，其中有两个参数--“？”

			try {
				psql = conn.prepareStatement("insert into tb_outlist (outid,shangpinid,number) "+"values(?,?,?)");
				psql.setString(1,outid);
				psql.setString(2,id);
				psql.setString(3,number);

				//psql.setString(5,dateStr);
				psql.executeUpdate();//执行更新
			} catch (SQLException e) {
				e.printStackTrace();
			}

			//}
		}

	}

	/*
	 * 增加进货单信息，方便进货历史记录的查询
	 */

	public static void zjjhdxx(String inid,Vector tableValues) {
		String id;
		String number;

		Vector rowValues;

		//先从表格数据里读取进货商品信息
		for(int i=0;i<tableValues.size();i++) {//size()获取向量的个数
			rowValues=(Vector)tableValues.get(i);
			//while(true) {

			id=(String) rowValues.get(0);
			number=(String)  rowValues.get(8);

			//再将这些进货商品数据连同inid一起写入tb_inlist里

			//添加一条记录
			PreparedStatement psql;
			ResultSet res;
			//预处理添加数据，其中有两个参数--“？”

			try {
				psql = conn.prepareStatement("insert into tb_inlist (inid,shangpinid,number) "+"values(?,?,?)");
				psql.setString(1,inid);
				psql.setString(2,id);
				psql.setString(3,number);

				//psql.setString(5,dateStr);
				psql.executeUpdate();//执行更新
			} catch (SQLException e) {
				e.printStackTrace();
			}

			//}
		}

	}

	/*
	 * 增加缺货单信息，方便缺货历史记录的查询
	 */

	public static void zjqhdxx(String lackid,Vector tableValues) {
		String id;
		String number;

		Vector rowValues;

		//先从表格数据里读取进货商品信息
		for(int i=0;i<tableValues.size();i++) {//size()获取向量的个数
			rowValues=(Vector)tableValues.get(i);
			//while(true) {

			id=(String) rowValues.get(0);
			number=(String)  rowValues.get(8);

			//再将这些进货商品数据连同lackid一起写入tb_lacklist里

			//添加一条记录
			PreparedStatement psql;
			ResultSet res;
			//预处理添加数据，其中有两个参数--“？”

			try {
				psql = conn.prepareStatement("insert into tb_lacklist (lackid,shangpinid,number) "+"values(?,?,?)");
				psql.setString(1,lackid);
				psql.setString(2,id);
				psql.setString(3,number);

				//psql.setString(5,dateStr);
				psql.executeUpdate();//执行更新
			} catch (SQLException e) {
				e.printStackTrace();
			}

			//}
		}

	}

	/*
	 * 增加积货单信息，方便积货历史记录的查询
	 */

	public static void zjjihdxx(String surplusid,Vector tableValues) {
		String id;
		String number;

		Vector rowValues;

		//先从表格数据里读取进货商品信息
		for(int i=0;i<tableValues.size();i++) {//size()获取向量的个数
			rowValues=(Vector)tableValues.get(i);
			//while(true) {

			id=(String) rowValues.get(0);
			number=(String)  rowValues.get(8);

			//再将这些进货商品数据连同surplusid一起写入tb_surpluslist里

			//添加一条记录
			PreparedStatement psql;
			ResultSet res;
			//预处理添加数据，其中有两个参数--“？”

			try {
				psql = conn.prepareStatement("insert into tb_surpluslist (surplusid,shangpinid,number) "+"values(?,?,?)");
				psql.setString(1,surplusid);
				psql.setString(2,id);
				psql.setString(3,number);

				//psql.setString(5,dateStr);
				psql.executeUpdate();//执行更新
			} catch (SQLException e) {
				e.printStackTrace();
			}

			//}
		}

	}

	/*
	 * 增加单据信息（出货单，进货单，缺货单，积货单），方便单据信息的历史记录查询
	 */

	public static void zjdanjuxx(String listid,String listtype,Vector tableValues) {
		String id;
		String number;

		Vector rowValues;

		//先从表格数据里读取单据商品信息
		for(int i=0;i<tableValues.size();i++) {//size()获取向量的个数
			rowValues=(Vector)tableValues.get(i);
			//while(true) {

			id=(String) rowValues.get(0);
			number=(String)  rowValues.get(8);

			//再将这些单据商品数据连同listid一起写入tb_listlist里

			//添加一条记录
			PreparedStatement psql;
			ResultSet res;
			//预处理添加数据，其中有两个参数--“？”

			try {
				psql = conn.prepareStatement("insert into tb_listlist (listid,listtype,shangpinid,number,date) "+"values(?,?,?,?,?)");
				psql.setString(1,listid);
				psql.setString(2,listtype);
				psql.setString(3,id);
				psql.setString(4,number);

				Date day=new Date();//这里用java.util.Date，不要用java.sql.Date
				DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
				String createdate = dateFormat2.format(day);
				java.util.Date myDate2;
				try {
					myDate2 = dateFormat2.parse(createdate);
					psql.setDate(5,new java.sql.Date(myDate2.getTime()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//psql.setString(5,dateStr);
				psql.executeUpdate();//执行更新
			} catch (SQLException e) {
				e.printStackTrace();
			}

			//}
		}

	}

	/**
	 * 查询出货历史记录时，获取根据出货单号获取出货单信息
	 * @param Str
	 * @return
	 * @throws SQLException
	 */

	public static DefaultTableModel chdcxx(String Str) throws SQLException {
		//PreparedStatement psql3;
		//2.创建statement类对象，用来执行SQL语句！！
		Statement statement = conn.createStatement();
		//要执行的SQL语句
		String sql = "select *from tb_listlist where listid= '" + Str +"' and listtype='out'";//查询语句和排序后的查询语句是放一起的
		//3.ResultSet类，用来存放获取的结果集！！
		ResultSet rs = statement.executeQuery(sql);

		Vector columnNames=new Vector();
		//Vector rowValue1=new Vector();
		Vector tableValues=new Vector();

		DefaultTableModel model = new DefaultTableModel(tableValues,columnNames);//表格的数据模型，操作数据


		columnNames.addElement("编号");
		columnNames.addElement("名称");
		columnNames.addElement("状态");
		columnNames.addElement("类别");
		columnNames.addElement("生产日期");
		columnNames.addElement("有效日期");
		columnNames.addElement("供应商");
		columnNames.addElement("单价");
		columnNames.addElement("数量");

		String id;
		String name;
		String statue;
		String type;
		String date;
		String deadtime;
		String produce;
		String money;
		String number;

		while(rs.next()) {//很重要，不要忘了这一句

			number=rs.getString("number");

			Statement statement2 = conn.createStatement();

			String sql2 = "select *from tb_shangpinlist where id= '" + rs.getString("shangpinid") +"'";//查询语句和排序后的查询语句是放一起的

			ResultSet rs2 = statement2.executeQuery(sql2);

			rs2.next();//很重要，不要忘了这一句

			Vector rowValue=new Vector();
			id=rs2.getString("id");
			name=rs2.getString("name");
			statue=rs2.getString("statue");
			type=rs2.getString("type");
			date=rs2.getString("date");
			deadtime=rs2.getString("deadtime");
			produce=rs2.getString("produce");
			money=rs2.getString("money");


			rowValue.addElement(id);
			rowValue.addElement(name);
			rowValue.addElement(statue);
			rowValue.addElement(type);
			rowValue.addElement(date);
			rowValue.addElement(deadtime);
			rowValue.addElement(produce);
			rowValue.addElement(money);
			rowValue.addElement(number);

			tableValues.addElement(rowValue);
		}

		rs.close();
		//conn.close();
		return model;

	}

	/**
	 * 获取根据人员编号获取人员信息
	 * @param Str
	 * @return
	 * @throws SQLException
	 */

	public static DefaultTableModel renyuanxx(String Str) throws SQLException {
		//PreparedStatement psql3;
		//2.创建statement类对象，用来执行SQL语句！！
		Statement statement = conn.createStatement();
		//要执行的SQL语句
		String sql = "select *from tb_renyuanlist where account= '" + Str +"'";//查询语句和排序后的查询语句是放一起的
		//3.ResultSet类，用来存放获取的结果集！！
		ResultSet rs = statement.executeQuery(sql);

		Vector columnNames=new Vector();
		//Vector rowValue1=new Vector();
		Vector tableValues=new Vector();

		DefaultTableModel model = new DefaultTableModel(tableValues,columnNames);//表格的数据模型，操作数据


		columnNames.addElement("编号");
		columnNames.addElement("姓名");
		columnNames.addElement("性别");
		columnNames.addElement("出生日期");
		columnNames.addElement("电话号码");
		columnNames.addElement("邮箱地址");
		columnNames.addElement("工作类型");
		columnNames.addElement("在职状态");

		String id;
		String name;
		String sex;
		String date;
		String phonenumber;
		String developaddress;
		String type;
		String statue;

		while(rs.next()) {//很重要，不要忘了这一句

			Vector rowValue=new Vector();
			id=rs.getString("id");
			name=rs.getString("name");
			sex=rs.getString("sex");
			date=rs.getString("date");
			phonenumber=rs.getString("phonenumber");
			developaddress=rs.getString("developaddress");
			type=rs.getString("worktype");
			statue=rs.getString("statue");


			rowValue.addElement(id);
			rowValue.addElement(name);
			rowValue.addElement(sex);
			rowValue.addElement(date);
			rowValue.addElement(phonenumber);
			rowValue.addElement(developaddress);
			rowValue.addElement(type);
			rowValue.addElement(statue);

			tableValues.addElement(rowValue);
		}

		rs.close();
		//conn.close();
		return model;

	}

	/**
	 * 查询进货历史记录时，获取根据进货单号获取出货单信息
	 * @param Str
	 * @return
	 * @throws SQLException
	 */

	public static DefaultTableModel jhdcxx(String Str) throws SQLException {
		//PreparedStatement psql3;
		//2.创建statement类对象，用来执行SQL语句！！
		Statement statement = conn.createStatement();
		//要执行的SQL语句
		String sql = "select *from tb_listlist where listid= '" + Str +"' and listtype='in'";//查询语句和排序后的查询语句是放一起的
		System.out.println("select *from tb_listlist where listid= '" + Str +"' and listtype='in'");
		//3.ResultSet类，用来存放获取的结果集！！
		ResultSet rs = statement.executeQuery(sql);
		Vector columnNames=new Vector();
		Vector tableValues=new Vector();

		DefaultTableModel model = new DefaultTableModel(tableValues,columnNames);//表格的数据模型，操作数据


		columnNames.addElement("编号");
		columnNames.addElement("名称");
		columnNames.addElement("状态");
		columnNames.addElement("类别");
		columnNames.addElement("生产日期");
		columnNames.addElement("有效日期");
		columnNames.addElement("供应商");
		columnNames.addElement("单价");
		columnNames.addElement("数量");

		String id;
		String name;
		String statue;
		String type;
		String date;
		String deadtime;
		String produce;
		String money;
		String number;

		while(rs.next()) {//很重要，不要忘了这一句

			number=rs.getString("number");

			Statement statement2 = conn.createStatement();

			String sql2 = "select *from tb_shangpinlist where id= '" + rs.getString("shangpinid") +"'";//查询语句和排序后的查询语句是放一起的

			ResultSet rs2 = statement2.executeQuery(sql2);

			rs2.next();//很重要，不要忘了这一句

			Vector rowValue=new Vector();
			id=rs2.getString("id");
			name=rs2.getString("name");
			statue=rs2.getString("statue");
			type=rs2.getString("type");
			date=rs2.getString("date");
			deadtime=rs2.getString("deadtime");
			produce=rs2.getString("produce");
			money=rs2.getString("money");


			rowValue.addElement(id);
			rowValue.addElement(name);
			rowValue.addElement(statue);
			rowValue.addElement(type);
			rowValue.addElement(date);
			rowValue.addElement(deadtime);
			rowValue.addElement(produce);
			rowValue.addElement(money);
			rowValue.addElement(number);

			tableValues.addElement(rowValue);
		}

		rs.close();
		//conn.close();
		return model;

	}

}
