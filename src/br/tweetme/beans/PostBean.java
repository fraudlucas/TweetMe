package br.tweetme.beans;

import java.sql.Timestamp;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.tweetme.controllers.PostController;
import br.tweetme.controllers.UserController;
import br.tweetme.entities.Post;
import br.tweetme.entities.User;

public class PostBean {

	private String post;

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String toPost() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);
		User user = (User) session.getAttribute("user");

		if (user == null) return null;
		
		System.out.println(getPost());

		Date date = new Date();
		System.out.println(new Timestamp(date.getTime()));

		Post p = new Post();
		p.setDate(date);
		p.setText(getPost());
		p.setOwner(user);

		PostController pc = new PostController();

		try {
			pc.newPost(p);
			
			UserController uc = new UserController();
			user = uc.retrieve(user.getLogin());
			session.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "continue";
	}

}
