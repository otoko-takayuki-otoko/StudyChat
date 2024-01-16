package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Login;

public class AccountDAO {
	private final String DB_URL = "jdbc:mariadb://localhost/web_app?serverTimezone=JST";
	private final String DB_USER = "root";
	private final String DB_PASS = "mysql";
	
//	アカウントの確認
	public Account accountSelect(Login login) {
		Account account = null;
		try{
			Class.forName("org.mariadb.jdbc.Driver");
			
		}catch(ClassNotFoundException e) {
			System.out.println(e);
			System.exit(0);
		}
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)){
			String sql = "SELECT userId, accountName, password From account WHERE accountName = ? AND password = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, login.getAccountName());
			pStmt.setString(2, login.getPassword());
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				String userId = rs.getString("userId");
				String accountName = rs.getString("accountName");
				String password = rs.getString("password");
				account = new Account(userId,password,accountName);
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		} 
		return account;
	}
	
//	アカウントの登録
	public Account accountRegister(Account account) {
		try{
			Class.forName("org.mariadb.jdbc.Driver");
			
		}catch(ClassNotFoundException e) {
			System.out.println(e);
			System.exit(0);
		}
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)){
			String sql = "INSERT INTO account (accountName, password) VALUES(?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, account.getAccountName());
			pStmt.setString(2, account.getPassword());
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				String accountName = rs.getString("accountName");
				String password = rs.getString("password");
				System.out.print(accountName);
				account = new Account(accountName, password);
			}
			}catch(SQLException e){
				e.printStackTrace();
				return null;
			}
			return account;

	}
}
