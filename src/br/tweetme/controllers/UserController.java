package br.tweetme.controllers;

import java.util.List;

import br.tweetme.entities.User;
import br.tweetme.factories.FactoryDAOJdbc;
import br.tweetme.persistenceDAO.FactoryDAO;
import br.tweetme.persistenceDAO.UserDAO;

public class UserController {

	protected static final String msgLogin = "User not exists!";

	protected FactoryDAO factory = null;

	public UserController() {
		// this.factory = FactoryDAOHibernate.getInstance();
		this.factory = FactoryDAOJdbc.getInstance();
	}

	public void signup(User user) throws Exception {
		UserDAO ud = factory.createUserPersistence();

		ud.insert(user);

	}

	public User signin(String login, String pass) throws Exception {
		User user = null;

		user = retrieve(login);

		if (!user.getPassword().matches("^" + pass + "$")) {
			user = null;
		}

		return user;
	}

	public User retrieve(String login) throws Exception {
		UserDAO ud = factory.createUserPersistence();

		User user = null;

		user = ud.retrieve(login);

		return user;
	}

	public boolean checkEmail(String email) {

		UserDAO ud = factory.createUserPersistence();

		User user = null;

		try {
			user = ud.retrieveByEmail(email);
			System.out.println("Email a ser checado = " + email);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (user != null) {
			return true;
		}

		return false;

	}

	public boolean checkLogin(String login) {

		UserDAO ud = factory.createUserPersistence();

		User user = null;

		try {
			user = ud.retrieve(login);
			System.out.println("Login a ser checado = " + login);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (user != null) {
			return true;
		}

		return false;

	}

	public void edit(User user) {

	}

	public void follow(User follower, User followed) {

	}

	public List<User> listFollowers() {

		return null;
	}

	public List<User> listFolloweds() {

		return null;
	}

	public User search(String login) {

		return null;
	}

}
