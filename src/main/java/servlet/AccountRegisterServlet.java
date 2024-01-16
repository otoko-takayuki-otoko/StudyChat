package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AccountDAO;
import model.Account;
import model.Login;
import model.LoginLogic;

/**
 * Servlet implementation class AccountRegisterServlet
 */
@WebServlet("/AccountRegisterServlet")
public class AccountRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/NewAccount.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		AccountDAO dao = new AccountDAO();
		String flag =  request.getParameter("new");
		
//		新規アカウントの追加
		if(flag!=null){
			System.out.println("new");
			String password = request.getParameter("password");
			String accountName = request.getParameter("accountName");
			Account account = new Account(password, accountName);
			dao.accountRegister(account);
			session.setAttribute("account", account);
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Home.jsp");
			dispatcher.forward(request, response);
		
//		既存アカウントのログイン
		}else {
			String password = request.getParameter("password");
			String accountName = request.getParameter("accountName");
			Login login = new Login(password, accountName);
			LoginLogic loginlogic = new LoginLogic();
			Account account = dao.accountSelect(login);
			boolean result = loginlogic.execute(login);
			
			if(result) {
				session.setAttribute("account", account);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Home.jsp");
				dispatcher.forward(request, response);	
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/False.jsp");
				dispatcher.forward(request, response);
			}
		}
			
	}

}
