import com.demo.bean.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@WebServlet(name = "sqldemo4", urlPatterns = "/sqldemo4")
public class SqlDemo4 extends HttpServlet {
    Session session;
    private SessionFactory factory;
    private Transaction tx = null;

    @Override
    public void init() throws ServletException {
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h>第四关: 巧夺天工</h></br>");
        out.println("URL: /sqldemo4?name=Bob</br>");
        out.println("</br>");
        out.println("flag在sqldemo2.test表的flag字段</br>");
        out.println("</br>");


        try {
            tx = session.beginTransaction();
            String name = req.getParameter("name");
            if (name != null && containsInvalidKeyword(name)) {
                resp.getWriter().println("监测到危险关键字");
            }else {
                String sql = "from com.demo.bean.User where name ='" + name + "'";
                out.println("SQL: " + sql + "</br>");
                out.println("</br>");

                Query query = session.createQuery(sql, User.class);
                List<User> users = query.list();
                for (User user : users) {
                    out.println("Result:" + user);
                }
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            out.println("加油，给你点提示: ");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            out.println("<pre>" + exceptionAsString + "</pre></br>");
        } finally {
            //session.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    public void destroy() {
        session.close();
    }

    private boolean containsInvalidKeyword(String input) {
        String[] invalidKeywords = {"#","-","/*","*/","*","sql("};
        String lowerCaseInput = input.toLowerCase();
        for (String keyword : invalidKeywords) {
            if (lowerCaseInput.contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}
