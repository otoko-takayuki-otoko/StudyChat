package model;

import DAO.AccountDAO;

public class LoginLogic {
	public boolean execute(Login login) {
		AccountDAO dao = new AccountDAO();
		Account account = dao.accountSelect(login);
		return account != null;
	}

}
