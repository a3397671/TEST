import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Administrator
 * @date 2019/1/15
 * @time 10:10
 */
public class DBManage {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    /** private static final String DRIVER = "com.microsoft.jdbc.sqlserver.SQLServerDriver";*/
    private static final String URL = "jdbc:mysql://localhost:3306/jxls_export";
    /**private static final String URL = "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=master";*/
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }

        return conn;
    }

    public static void close(Connection conn){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
    }

    /*    public static void closeAll(ResultSet rs, Statement state, Connection conn) {

        try {
            if (rs != null)
                rs.close();

            if (state != null)
                state.close();

            if (conn != null)
                conn.close();

        } catch (SQLException e) {

        }
    }*/
}
