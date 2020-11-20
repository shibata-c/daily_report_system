package controllers.clients;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Client;
import models.validators.ClientValidator;
import utils.DBUtil;

/**
 * Servlet implementation class ClientCreateServlet
 */
@WebServlet("/clients/create")
public class ClientCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Client c = new Client();

            c.setCode(request.getParameter("code"));
            c.setName(request.getParameter("name"));
            c.setAge(Integer.parseInt(request.getParameter("age")));
            c.setDelete_flag(1);

            List<String> errors = ClientValidator.validate(c, true);
            if(errors.size() > 0){
                em.close();


                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("Client",c);
                request.setAttribute("error", errors);

                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/clients/new.jsp");
                rd.forward(request, response);
            }else{
                em.getTransaction().begin();
                em.persist(c);
                em.getTransaction().commit();
                request.getSession().setAttribute("flush", "登録が完了しました。");
                em.close();

                response.sendRedirect(request.getContextPath()+"/clients/index");
            }
        }
    }

}
