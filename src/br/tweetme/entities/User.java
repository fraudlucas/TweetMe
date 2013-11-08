package br.tweetme.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "users")
public class User {

	public User() {
		this.setFollowers(new ArrayList<User>());
		this.setFollowing(new ArrayList<User>());
		this.setPosts(new ArrayList<Post>());
	}

	@Id
	@Column(name = "id")
	protected double id;

	@Column(name = "login", nullable = false)
	protected String login;

	@Column(name = "pass", nullable = false)
	protected String password;

	@Column(name = "name", nullable = true)
	protected String name;

	@Column(name = "surname", nullable = true)
	protected String surname;

	@Column(name = "email", nullable = false)
	protected String email;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "follow", joinColumns = @JoinColumn(name = "id_followed"), inverseJoinColumns = @JoinColumn(name = "id_follower"))
	protected List<User> followers;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "follow", joinColumns = @JoinColumn(name = "id_follower"), inverseJoinColumns = @JoinColumn(name = "id_followed"))
	protected List<User> following;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	protected List<Post> posts;

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<User> getFollowers() {
		return followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	public List<User> getFollowing() {
		return following;
	}

	public void setFollowing(List<User> following) {
		this.following = following;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public void addFollower(User follower) {
		this.followers.add(follower);
	}

	public void addFollowing(User following) {
		this.following.add(following);
	}
	
	public void addPost(Post post) {
		this.posts.add(post);
	}

	@Override
	public String toString() {
		
		String result = super.toString();
		
		result += "\n" + getName() + " - " + getLogin() + "\n";
		result += "Followers: " + getFollowers().size() + " - Following: " + getFollowing().size() + "\n";
		result += "Posts: " + getPosts().size();
			
		return result;
	}
}
