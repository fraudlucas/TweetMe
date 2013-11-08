package br.tweetme.persistenceDAOJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import br.tweetme.entities.Post;
import br.tweetme.entities.User;
import br.tweetme.persistenceDAO.UserDAO;

public class UserDAOJdbc implements UserDAO {

	@Override
	public void insert(User user) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ConnectionDAOJdbc.getConnection(false);

			pstmt = con
					.prepareStatement(
							"INSERT INTO users (name, email, pass, login) VALUES (?, ?, ?, ?)",
							Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getLogin());

			System.out.println("TESTE = " + user.getLogin());

			pstmt.executeUpdate();
			con.commit();

//			ResultSet rs = pstmt.getGeneratedKeys();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public User retrieve(String login) throws Exception {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;

		try {
			con = ConnectionDAOJdbc.getConnection(false);
			String select = "SELECT id, email, pass, name, login FROM users WHERE login = ?";
			pstmt = con.prepareStatement(select);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getDouble("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("pass"));
				user.setName(rs.getString("name"));
				user.setLogin(rs.getString("login"));

				select = "SELECT id, name, email, pass, login FROM users, follow "
						+ "WHERE id = id_follower AND id_followed = ?";

				pstmt = con.prepareStatement(select);
				pstmt.setDouble(1, user.getId());
				rs = pstmt.executeQuery();

				while (rs.next()) {
					User follower = new User();
					follower.setId(rs.getDouble("id"));
					follower.setEmail(rs.getString("email"));
					follower.setPassword(rs.getString("pass"));
					follower.setName(rs.getString("name"));
					follower.setLogin(rs.getString("login"));

					user.addFollower(follower);
				}

				select = "SELECT id, name, email, pass, login FROM users, follow "
						+ "WHERE id = id_followed AND id_follower = ?";

				pstmt = con.prepareStatement(select);
				pstmt.setDouble(1, user.getId());
				rs = pstmt.executeQuery();

				while (rs.next()) {
					User following = new User();
					following.setId(rs.getDouble("id"));
					following.setEmail(rs.getString("email"));
					following.setPassword(rs.getString("pass"));
					following.setName(rs.getString("name"));
					following.setLogin(rs.getString("login"));

					user.addFollowing(following);
				}

				select = "SELECT id, textPost, datePost FROM posts "
						+ "WHERE id_user = ?";
				pstmt = con.prepareStatement(select);
				pstmt.setDouble(1, user.getId());
				rs = pstmt.executeQuery();

				while (rs.next()) {
					Post post = new Post();

					post.setId(rs.getDouble("id"));
					post.setText(rs.getString("textpost"));
					post.setOwner(user);
					post.setDate(new Date(rs.getTimestamp("datePost").getTime()));
					
					System.out.println(post);
					
					user.addPost(post);
				}

				System.out.println(user);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return user;
	}

	@Override
	public User retrieveByEmail(String email) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;

		try {
			con = ConnectionDAOJdbc.getConnection(false);
			String select = "SELECT id, email, pass, name, login FROM users WHERE email = ?";
			pstmt = con.prepareStatement(select);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getDouble("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("pass"));
				user.setName(rs.getString("name"));
				user.setLogin(rs.getString("login"));

				select = "SELECT id, name, email, pass, login FROM users, follow WHERE id = id_follower AND id_followed = ?";

				pstmt = con.prepareStatement(select);
				pstmt.setDouble(1, user.getId());
				rs = pstmt.executeQuery();

				while (rs.next()) {
					User follower = new User();
					follower.setId(rs.getDouble("id"));
					follower.setEmail(rs.getString("email"));
					follower.setPassword(rs.getString("pass"));
					follower.setName(rs.getString("name"));
					follower.setLogin(rs.getString("login"));

					user.addFollower(follower);
				}

				select = "SELECT id, name, email, pass, login FROM users, follow WHERE id = id_followed AND id_follower = ?";

				pstmt = con.prepareStatement(select);
				pstmt.setDouble(1, user.getId());
				rs = pstmt.executeQuery();

				while (rs.next()) {
					User following = new User();
					following.setId(rs.getDouble("id"));
					following.setEmail(rs.getString("email"));
					following.setPassword(rs.getString("pass"));
					following.setName(rs.getString("name"));
					following.setLogin(rs.getString("login"));

					user.addFollowing(following);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return user;
	}

	@Override
	public void update(User user) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User user) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
