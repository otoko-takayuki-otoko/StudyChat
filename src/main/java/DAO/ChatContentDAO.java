package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.ChatContent;

public class ChatContentDAO {
	private final String DB_URL = "jdbc:mariadb://localhost/web_app?serverTimezone=JST";
	private final String DB_USER = "root";
	private final String DB_PASS = "mysql";
	
//	掲示板一覧から選択されたスレッドの中身を選択する
	public ArrayList<ChatContent> ChatContentSelect(String boardId) {
		List<ChatContent> chatContentList = new ArrayList<>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
			System.exit(0);
		}
		try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)){
			String sql = "SELECT * From chatcontent WHERE boardId = ? ;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,  boardId);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				String userId = rs.getString("userId");
				String sentdatetime = rs.getString("sentdatetime");
				String comment = rs.getString("comment");
			    ChatContent chatContent = new ChatContent(boardId, userId, sentdatetime, comment);
			    chatContentList.add(chatContent);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (ArrayList<ChatContent>) chatContentList;
	}
	
//	新しいコメントを作成（スレッドのタイトルではない）
	public ArrayList<ChatContent> ContentNew(String boardId, String userId, String comment){
	    List<ChatContent> chatContentList = new ArrayList<>();
	    try {
	        Class.forName("org.mariadb.jdbc.Driver");
	    } catch(ClassNotFoundException e) {
	        System.out.println(e);
	        System.exit(0);
	    }
	    try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)){
	        String sql = "INSERT INTO chatcontent (boardId, userId, sentdatetime, comment) VALUES(?, ?, ? ,? );";
	        PreparedStatement pStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        
//	        投稿した時間を取得
	        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	        
	        pStmt.setString(1,  boardId);
	        pStmt.setString(2,  userId);
	        pStmt.setTimestamp(3,  currentTimestamp);
	        pStmt.setString(4,  comment);
	        int affectedRows = pStmt.executeUpdate();

	        if (affectedRows > 0) {
	            // 挿入された行のキーを取得
	            ResultSet generatedKeys = pStmt.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                String sentdatetime = generatedKeys.getTimestamp(1).toString();
	                ChatContent chatContent = new ChatContent(boardId, userId, sentdatetime, comment);
	                chatContentList.add(chatContent);
	            }
	        }
	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
	    return (ArrayList<ChatContent>) chatContentList;
	}



}
