package priv.zyz.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 工具类
 */
public class DBHelper {
    public Connection conn = null;
    public ResultSet rs = null;
    public PreparedStatement pst = null;

    //连接参数
    private static String driverName;
    private static String url;
    private static String userName;
    private static String password;

    static {
        init();
    }

    /**
     * 获取数据库连接的几个参数
     */
    public static void init(){
        Properties params = new Properties();
        //配置文件的路径
        String proFileName = "database.properties";
        //加载配置文件到输入流中
        InputStream is = DBHelper.class.getClassLoader().getResourceAsStream(proFileName);
        try {
            //从输入流中读取属性列表
            params.load(is);
        }catch (IOException e){
            e.printStackTrace();
        }
        driverName = params.getProperty("driver");
        url = params.getProperty("url");
        userName = params.getProperty("username");
        password = params.getProperty("password");
    }

    /**
     * 得到连接对象
     * @return  Connection
     */
    public Connection getConn(){
        try {
            //加载驱动
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, userName, password);   //获取连接对象
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 查询数据
     * @param sql   Sql语句
     * @param objs  Sql语句中占位符的参数
     * @return ResultSet 返回结果集
     * @throws Exception  声明异常
     */
    public ResultSet select(String sql,Object[] objs) throws Exception{
        conn = getConn();
        pst = conn.prepareStatement(sql);
        for (int i = 0; i < objs.length; i++) {
            pst.setObject(i+1 , objs[i]);
        }
        rs = pst.executeQuery();
        return rs;
    }

    /**
     * 增删改方法
     * @param sql   Sql语句
     * @param objs  Sql语句中占位符的参数
     * @return int  得到执行的SQL语句后影响的行数
     * @throws Exception    声明异常
     */
    public int update(String sql , Object[] objs) throws Exception{
        conn = getConn();
        pst = conn.prepareStatement(sql);
        for (int i = 0; i < objs.length; i++) {
            pst.setObject(i+1 , objs[i]);
        }
        return pst.executeUpdate();
    }

    /**
     * 关闭方法
     * @param conn  连接对象
     * @param st    执行对象
     * @param rs    结果集
     */
    public void closeAll(Connection conn, Statement st, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (conn != null) conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
