package br.tweetme.persistenceDAOJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import br.tweetme.entities.Post;
import br.tweetme.persistenceDAO.PostDAO;

public class PostDAOJdbc implements PostDAO {

	@Override
	public void insert(Post post) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ConnectionDAOJdbc.getConnection(false);

			/**
			 * Tem que ver timestamp com java
			 */
			pstmt = con
					.prepareStatement(
							"INSERT INTO posts (id_user, textPost, datePost) VALUES (?, ?, ?)",
							Statement.RETURN_GENERATED_KEYS);
			// pstmt.setString(1, user.getName());
			// pstmt.setString(2, user.getEmail());
			// pstmt.setString(3, user.getPassword());
			// pstmt.setString(4, user.getLogin());

			pstmt.setDouble(1, post.getOwner().getId());
			pstmt.setString(2, post.getText());
			pstmt.setTimestamp(3, new Timestamp(post.getDate().getTime()));

			// System.out.println("TESTE = " + user.getLogin());

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
	public Post retrieve(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Post post) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Post post) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Post> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
