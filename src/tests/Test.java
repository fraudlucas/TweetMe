package tests;

import br.tweetme.entities.User;
import br.tweetme.factories.FactoryDAOHibernate;
import br.tweetme.persistenceDAO.FactoryDAO;
import br.tweetme.persistenceDAO.UserDAO;

public class Test {

	private static FactoryDAO factory = FactoryDAOHibernate.getInstance();

	public static void main(String[] args) {
		// insertUser();
		 retrieveUser();
		// deleteUser();

		FactoryDAOHibernate.destroyInstance();
	}

	public static void insertUser() {
		UserDAO ud = factory.createUserPersistence();

		User user = new User();

		user.setLogin("asjajs");
		user.setPassword("123456");
		user.setName("ASJ AJS");
		user.setSurname("Falcão");
		user.setEmail("asj_ajs@outlook.com");

		try {
			ud.insert(user);
			System.out.println("User inserido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao inserir user - " + e.getMessage());
		}

	}

	public static User retrieveUser() {
		UserDAO ud = factory.createUserPersistence();

		User user = null;

		try {
			user = ud.retrieve("fraudlucas");
			System.out.println("User " + user.getLogin() + " "
					+ user.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	private static void deleteUser() {
		UserDAO ud = factory.createUserPersistence();

		User user = retrieveUser();

		try {
			ud.delete(user);
			System.out.println("User deletado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
