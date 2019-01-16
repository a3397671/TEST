import org.jxls.common.Context;
import org.jxls.jdbc.JdbcHelper;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2019/1/14
 * @time 16:36
 */
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args){
        logger.info("Running SQL demo");
        Connection conn = DBManage.getConnection();
        System.out.println("connection mysql----------");
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY_MM_dd"));
        try {
            JdbcHelper jdbcHelper = new JdbcHelper(conn);
            //模板文件
            String fileName = "simpleSql2";
            InputStream is = Application.class.getClass().getResourceAsStream("/../resources/simpleSql2.xls");

            List<Student> stu = new ArrayList<>();
            Student stu1 = new Student(1,"zhangsan",'M',15);
            Student stu2 = new Student(2,"xioayu",'F',16);
            stu.add(stu1);
            stu.add(stu2);
            Context context = new Context();
            //设置参数变量
            //context.putVar("conn", conn);
            //context.putVar("jdbc", jdbcHelper);
            context.putVar("stu", stu);
            OutputStream os = new FileOutputStream(new File("f:\\data\\out_"+fileName+"_"+today+".xls"));
            //载入模板、处理导出
            JxlsHelper.getInstance().processTemplate(is, os, context);
            //释放资源
            os.flush();
            os.close();
            is.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        DBManage.close(conn);
    }
}
