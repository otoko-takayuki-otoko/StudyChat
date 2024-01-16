package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ChatContentDAO;
import DAO.ChatDAO;
import model.Account;
import model.Chat;
import model.ChatContent;

/**
 * Servlet implementation class ChatEditServlet
 */
@WebServlet("/ChatEditServlet")
public class ChatEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		List<ChatContent> chatContentList =(List<ChatContent>) session.getAttribute("chatContentList");
		List<Chat> chatList = (List<Chat>) session.getAttribute("chatList");
		Account account = (Account)session.getAttribute("account");
		Chat chat = new Chat();
		request.setCharacterEncoding("UTF-8");
		
		ChatDAO daoChat = new ChatDAO();
		ChatContentDAO daoCon = new ChatContentDAO();
		String boardId  = request.getParameter("boardId");
		String flag  = request.getParameter("flag");
		
		if(flag.equals("delete")) {
			System.out.println("Flag: " + flag);
			String userId = account.getUserId();
			daoChat.ChatDelete(boardId);
			daoChat.ChatUserSelect(userId);
			session.setAttribute("chat", chat);
			session.setAttribute("chatList", chatList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ChatEdit.jsp");
			dispatcher.forward(request, response);
			
		}else if(flag.equals("change")) {
			System.out.println("Flag: " + flag);
			chat = daoChat.ChatBoardSelect(boardId);
			session.setAttribute("chat", chat);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ChatChange.jsp");
			dispatcher.forward(request, response);
		
		}else if(flag.equals("update")) {
			System.out.println("Flag: " + flag);
			String title = request.getParameter("title");
			String userId = account.getUserId();
			daoChat.ChatChange(boardId, title);
			chatList = daoChat.ChatUserSelect(userId);
			session.setAttribute("chat", chat);
			session.setAttribute("chatList", chatList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ChatEdit.jsp");
			dispatcher.forward(request, response);
		
		}else if(flag.equals("newComment")){
			System.out.println("Flag: " + flag);
			String userId = request.getParameter("userId");
			String comment = request.getParameter("comment");
			System.out.println(comment);
			daoCon.ContentNew(boardId, userId, comment);
			chatContentList = daoCon.ChatContentSelect(boardId);
			session.setAttribute("chat", chat);
			session.setAttribute("chatContentList", chatContentList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Content.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
