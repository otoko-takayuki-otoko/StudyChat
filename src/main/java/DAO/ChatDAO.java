package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Chat;

public class ChatDAO {
	private final String DB_URL = "jdbc:mariadb://localhost/web_app?serverTimezone=JST";
	private final String DB_USER = "root";
	private final String DB_PASS = "mysql";
	
//	チャット（スレッド）の取得、一覧を表示するため
	public ArrayList<Chat> ChatSelect() {
		List<Chat> chatList = new ArrayList<>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
			System.exit(0);
		}
		try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)){
			String sql = "SELECT * From chat ;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				String boardId = rs.getString("boardId");
				String userId = rs.getString("userId");
				String title = rs.getString("title");
				String categoryId = rs.getString("categoryId");
			    Chat chat = new Chat(boardId, userId, title, categoryId);
			    chatList.add(chat);    
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (ArrayList<Chat>) chatList;
	}
	
//	カテゴリーに該当するchatを選択する
	public ArrayList<Chat> ChatSelectTable(String categoryId) {
		List<Chat> chatList = new ArrayList<>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
			System.exit(0);
		}
		try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)){
			String sql = "SELECT * From chat WHERE categoryId = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, categoryId);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				String boardId = rs.getString("boardId");
				String userId = rs.getString("userId");
				String title = rs.getString("title");
			    Chat chat = new Chat(boardId, userId, title, categoryId);
			    chatList.add(chat);    
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (ArrayList<Chat>) chatList;
	}
	
//	userIdに該当する者が作ったchatを選択
	public ArrayList<Chat> ChatUserSelect(String userId) {
		List<Chat> chatList = new ArrayList<>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
			System.exit(0);
		}
		try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)){
			String sql = "SELECT * From chat WHERE userId = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userId);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {	
				String boardId = rs.getString("boardId");
				String title = rs.getString("title");
				String categoryId = rs.getString("categoryId");
			    Chat chat = new Chat(boardId, userId, title, categoryId);
			    chatList.add(chat);    
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (ArrayList<Chat>) chatList;
	}
	
//	boardIdを基にchatを選択する
	public Chat ChatBoardSelect(String boardId){
		Chat chat = new Chat();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
			System.exit(0);
		}
		try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)){
			String sql = "SELECT * From chat WHERE boardId = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, boardId);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {	
				String userId = rs.getString("userId");
				String title = rs.getString("title");
				String categoryId = rs.getString("categoryId");
			    chat = new Chat(boardId, userId, title, categoryId);   
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return chat;
	}
	
//	新しいchatの作成
	public ArrayList<Chat> ChatNew(String title, String categoryId, String userId, String comment) {
	    List<Chat> chatList = new ArrayList<>();
	    String boardId = null;
	    try {
	        Class.forName("org.mariadb.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        System.out.println(e);
	        System.exit(0);
	    }
	    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
	        String sql = "INSERT INTO chat (userId, title, categoryId) VALUES (?, ?, ?);";
	        try (PreparedStatement pStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	            pStmt.setString(1, userId);
	            pStmt.setString(2, title);
	            pStmt.setString(3, categoryId);
	            
//	            boardIdをStringに変更するため
	            int affectedRows = pStmt.executeUpdate();
	            if (affectedRows > 0) {
	                ResultSet generatedKeys = pStmt.getGeneratedKeys();
	                if (generatedKeys.next()) {
	                    boardId = generatedKeys.getString(1);
	                }
	            }
	            Chat chat = new Chat(boardId, userId, title, categoryId);
	            chatList.add(chat);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    ChatContentDAO daoCon = new ChatContentDAO();
	    daoCon.ContentNew(boardId, userId, comment);
	    return (ArrayList<Chat>) chatList;
	}
	
//	chatの削除
	public void ChatDelete(String boardId) {
	    try {
	        Class.forName("org.mariadb.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        System.out.println(e);
	        System.exit(0);
	    }
	    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
//	        二つのtableを操作するため
	        conn.setAutoCommit(false);
	        try {
	            // chatcontentテーブルから削除
	            String sqlChatContent = "DELETE FROM chatcontent WHERE boardId = ?";
	            try (PreparedStatement pStmtChatContent = conn.prepareStatement(sqlChatContent)) {
	                pStmtChatContent.setString(1, boardId);
	                pStmtChatContent.executeUpdate();
	            }
	            // chatテーブルから削除
	            String sqlChat = "DELETE FROM chat WHERE boardId = ?";
	            try (PreparedStatement pStmtChat = conn.prepareStatement(sqlChat)) {
	                pStmtChat.setString(1, boardId);
	                pStmtChat.executeUpdate();
	            }
	            conn.commit();
	        } catch (SQLException e) {
	            conn.rollback();
	            e.printStackTrace();
	        } finally {
	            conn.setAutoCommit(true);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
//	chatのタイトルの変更
	public void ChatChange(String boardId, String title) {
		try {
		    Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		    System.out.println(e);
		    System.exit(0);
		}
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			System.out.println("dao update");
			String sql = "UPDATE chat SET title = ? WHERE boardId = ?;";

		    try (PreparedStatement pStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
		        pStmt.setString(1, title);
		        pStmt.setString(2, boardId);
		        
		        int affectedRows = pStmt.executeUpdate();
		        if (affectedRows > 0) {
		            ResultSet generatedKeys = pStmt.getGeneratedKeys();
		            if (generatedKeys.next()) {
		                boardId = generatedKeys.getString(1);
		            }
		        }
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}

				
	}

}


