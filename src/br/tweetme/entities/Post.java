package br.tweetme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected double id;

	@Column(name = "textPost", nullable = false)
	protected String text;

	@Column(name = "datePost", nullable = false)
	protected Date date;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_user", referencedColumnName = "id")
	protected User owner;

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		String result = super.toString();

		result += "\n Post: " + getText() + "[" + getDate().toString() + "]\n";
		result += "Owner login: " + getOwner().getLogin() + "\n";

		return result;
	}
}
