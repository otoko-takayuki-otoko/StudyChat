package servlet;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/ChatControlServlet")
public class ChatControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account"); 
		String flag  = request.getParameter("flag");
		List<ChatContent> chatContentList =(List<ChatContent>) session.getAttribute("chatContentList");
		List<Chat> chatList = (List<Chat>) session.getAttribute("chatList");
		ChatDAO daoChat = new ChatDAO();
		ChatContentDAO daoCon = new ChatContentDAO();
		
		//		一覧表示		
		if(flag == null){			
			if(chatList == null) {
				chatList = new ArrayList<>();
				session.setAttribute("chatList", chatList);
			}
			chatList = daoChat.ChatSelect();
			session.setAttribute("chatList", chatList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ChatView.jsp");
			dispatcher.forward(request, response);
			
		//		新規投稿画面に遷移
		}else if(flag.equals("createChat")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/NewChatCreate.jsp");
			dispatcher.forward(request, response);
		
		//		新規投稿が作成され投稿一覧に遷移
		}else if(flag.equals("newChat")) {
			String title = request.getParameter("title");
			String categoryId = request.getParameter("categoryId");
			String comment = request.getParameter("comment");
			String userId = account.getUserId();
			chatList = daoChat.ChatNew(title, categoryId, userId, comment);
			session.setAttribute("chatList", chatList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ChatView.jsp");
			dispatcher.forward(request, response);
		
		//		投稿編集画面に遷移
		}else if(flag.equals("chatEdit")){
			String userId = account.getUserId();
			chatList = daoChat.ChatUserSelect(userId);
			session.setAttribute("chatList", chatList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ChatEdit.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");	
		Account account = (Account)session.getAttribute("account"); 
		List<Chat> chatList = (List<Chat>) session.getAttribute("chatList");
		String boardId  = request.getParameter("boardId");
		ChatDAO daoChat = new ChatDAO();
		
//		カテゴリーを選択したとき
		if(boardId == null) {
			String categoryId = request.getParameter("categoryId");
			chatList = daoChat.ChatSelectTable(categoryId);
			StringBuilder htmlBuilder = new StringBuilder();
			htmlBuilder.append("<div id=\"table-container\">");
			
			// 該当する投稿がない場合の処理
			if (chatList.isEmpty()) {	
				htmlBuilder.append("<p>該当する投稿はありません</p>");
			// 該当する投稿がある場合の処理	
			} else {
				for (Chat chat : chatList) {
					htmlBuilder.append("<form action=\"ChatControlServlet\" method=\"post\">")
					.append("<input type=\"hidden\" name=\"boardId\" value=\"").append(chat.getBoardId()).append("\">")
					.append("<div class='d-grid gap-2 col-6 mx-auto'><button class=' btn btn-light' type='submit' style='width: 100%; margin-bottom: 10px;' class='title-button'>").append(chat.getTitle()).append("</button></div>")
					.append("</form>");
				}
			}
			
			htmlBuilder.append("</div>");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(htmlBuilder.toString());	
			
//		chatが選択され、commentの一覧表示
		}else {
			List<ChatContent> chatContentList =(List<ChatContent>) session.getAttribute("chatContentList");
		    boardId = request.getParameter("boardId");
			String userId = request.getParameter("userId");
			String title = request.getParameter("title");
			String categoryId = request.getParameter("categoryId");
			ChatContentDAO ccdao = new ChatContentDAO();
			chatContentList = ccdao.ChatContentSelect(boardId);
			session.setAttribute("chatContentList", chatContentList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Content.jsp");
			dispatcher.forward(request, response);
		}

	}
}

