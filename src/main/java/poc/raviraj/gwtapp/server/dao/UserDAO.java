package poc.raviraj.gwtapp.server.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import poc.raviraj.gwtapp.shared.User;

public class UserDAO extends AbstractDAO {

	public User save(User user) {
		Session session = getSession();
		Transaction tx = session.getTransaction();
		tx.begin();

		if (user.getUserId() == 0) {
			session.save(user);
		} else {
			session.update(user);
		}

		tx.commit();		
		session.close();

		return user;
	}

	public User delete(User user) {
		Session session = getSession();
		Transaction tx = session.getTransaction();
		tx.begin();

		System.out.println("Delete user called for ID: " + user.getUserId());
		session.delete(user);
		System.out.println("After Delete user called for ID: " + user.getUserId());
		System.out.println("Before delete commit");
		tx.commit();
		System.out.println("After delete commit");
		session.close();

		return user;
	}

	public List<User> findAll() {
		Session session = getSession();
		Transaction tx = session.getTransaction();
		tx.begin();

		Criteria criteria = session.createCriteria(User.class);
		@SuppressWarnings("unchecked")
		List<User> userList = criteria.list();

		tx.commit();
		session.close();

		return userList;

	}

	public User find(Long userId) {
		Session session = getSession();
		Transaction tx = session.getTransaction();
		tx.begin();

		User user = (User) session.get(User.class, userId);

		tx.commit();
		session.close();

		System.out.println("User ID after save: " + user.getUserId());

		return user;
	}

	public List<User> paginated(int pageNumber, int perPageLimit) {
		Session session = getSession();
		Transaction tx = session.getTransaction();
		tx.begin();

		Criteria criteria = session.createCriteria(User.class);
		criteria.setFirstResult(pageNumber * perPageLimit - pageNumber);
		criteria.setFetchSize(perPageLimit);
		@SuppressWarnings("unchecked")
		List<User> userList = criteria.list();

		tx.commit();
		session.close();

		return userList;
	}
}
