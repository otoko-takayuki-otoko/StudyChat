package model;

public class Login {
	private String password;
	private String accountName;
	
	
	public Login(String password, String accountName) {
		this.accountName = accountName;
		this.password = password;
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
	
	
}
