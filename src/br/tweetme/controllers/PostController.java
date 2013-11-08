package br.tweetme.controllers;

import br.tweetme.entities.Post;
import br.tweetme.factories.FactoryDAOJdbc;
import br.tweetme.persistenceDAO.FactoryDAO;
import br.tweetme.persistenceDAO.PostDAO;

public class PostController {
	protected FactoryDAO factory = null;

	public PostController() {
//		this.factory = new FactoryDAOHibernate();
		this.factory = new FactoryDAOJdbc();
	}
	
	public void newPost(Post post) throws Exception {
		PostDAO pd = factory.createPostPersistence();
		
		pd.insert(post);
	}
}
