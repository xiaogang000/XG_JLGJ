
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;

@WebServlet(name = "sqldemo1", urlPatterns = "/sqldemo1")
public class SqlDemo1 extends HttpServlet {

    private String dbuser = "root";
    private String dbpasswd = "root";
    private String dbhost = "127.0.0.1";
    private Connection conn;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://"+dbhost+":3306/sqldemo", dbuser,dbpasswd );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h>第一关: 小试身手</h></br>");
        out.println("URL: /sqldemo1?name=Bob</br>");
        out.println("</br>");
        out.println("flag在sqldemo2.test表的flag字段</br>");

        String sql = "select * from user where name = '" + req.getParameter("name")+"'";
        out.println("SQL: " + sql+"</br>");
        out.println("</br>");

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                out.println("Result: Id=> " + rs.getObject("id") + " Name=> " + rs.getObject("name") + " Passwd=> " + rs.getObject("passwd")+"</br>");
            }
        } catch (Exception e) {
            out.println("加油，给你点提示: ");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            out.println("<pre>" + exceptionAsString + "</pre></br>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    public void destroy() {
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
