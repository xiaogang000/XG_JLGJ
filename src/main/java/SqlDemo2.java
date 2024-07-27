import com.demo.bean.User;
import com.demo.dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "sqldemo2", urlPatterns = "/sqldemo2")
public class SqlDemo2 extends HttpServlet {

    private SqlSession session;
    private InputStream in;

    @Override
    public void init() throws ServletException {
        try {
            in = Resources.getResourceAsStream("SqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        session = factory.openSession();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h>第二关: 狸猫换太子</h></br>");
        out.println("URL: /sqldemo2?tablename=name&name=Bob</br>");
        out.println("</br>");
        out.println("flag在sqldemo2.test表的flag字段</br>");
        out.println("</br>");

        String tablename = req.getParameter("tablename");
        if (tablename != null && containsInvalidKeyword(tablename)) {
            resp.getWriter().println("监测到危险关键字");
        } else {
            Map<String, Object> params = new HashMap<>();
            params.put("tablename", req.getParameter("tablename"));
            params.put("name", req.getParameter("name"));
            UserDao userDao = session.getMapper(UserDao.class);
            try {
                List<User> users = userDao.selectB(params);
                for (User user : users) {
                    out.println("Result: " + user.toString());
                }
            } catch (Exception e) {
                out.println("加油，给你点提示: ");
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();
                out.println("<pre>" + exceptionAsString + "</pre></br>");
            }
        }
    }

    @Override
    public void destroy() {
        session.close();
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean containsInvalidKeyword(String input) {
        String[] invalidKeywords = {"union", "select", "from","@", "<", ">","&","|","#","--", "(", ")", "_","!",";"};
        String lowerCaseInput = input.toLowerCase();
        for (String keyword : invalidKeywords) {
            if (lowerCaseInput.contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}
