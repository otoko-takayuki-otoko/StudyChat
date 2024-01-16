package model;

public class Account {
	private String userId;
	private String password;
	private String accountName;
	
	public String getUserId() {
		return userId;
	}
	public void setId(String userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public Account(String password, String accountName) {
		this.password = password;
		this.accountName = accountName; 
	}
	
	public Account(String userId, String password, String accountName) {
		this.userId  = userId;
		this.password = password;
		this.accountName = accountName;
	}

}