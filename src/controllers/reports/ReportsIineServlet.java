package controllers.reports;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsIineServlet
 */
@WebServlet("/reports/iine")
public class ReportsIineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsIineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");

            int page;
            try{
                page = Integer.parseInt(request.getParameter("page"));
            } catch(Exception e) {
                page = 1;
            }
            List<Report> reports = em.createNamedQuery("getMyAllReports", Report.class)
                                      .setParameter("employee", login_employee)
                                      .setFirstResult(15 * (page - 1))
                                      .setMaxResults(15)
                                      .getResultList();

            long reports_count = (long)em.createNamedQuery("getMyReportsCount", Long.class)
                                         .setParameter("employee", login_employee)
                                         .getSingleResult();

            // セッションスコープからレポートのIDを取得して
            // 該当のIDの1件のレポートをデータベースから取得
            Report m = em.find(Report.class, Integer.parseInt(request.getParameter("id")));

            em.getTransaction().begin();

            int count = m.getIine_count();
            count++;
            m.setIine_count(count);

            em.getTransaction().commit();
            em.close();

            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("iine_count", m);
            request.setAttribute("reports", reports);
            request.setAttribute("reports_count", reports_count);
            request.setAttribute("page", page);

            if(request.getSession().getAttribute("flush") != null) {
                request.setAttribute("flush", request.getSession().getAttribute("flush"));
                request.getSession().removeAttribute("flush");
            }

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
            rd.forward(request, response);
        }
    }

}
