package br.tweetme.persistenceDAO;

import java.util.List;

import br.tweetme.entities.User;


public interface UserDAO {

	public void insert(User user) throws Exception;

	public User retrieve(String login) throws Exception;
	
	public User retrieveByEmail(String email) throws Exception;
	
	public void update(User user) throws Exception;

	public void delete(User user) throws Exception;

	public List<User> list() throws Exception;

}
