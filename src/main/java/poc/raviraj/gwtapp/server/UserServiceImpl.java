package poc.raviraj.gwtapp.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import poc.raviraj.gwtapp.client.UserService;
import poc.raviraj.gwtapp.server.dao.UserDAO;
import poc.raviraj.gwtapp.shared.User;

public class UserServiceImpl extends RemoteServiceServlet implements UserService {

	private static final long serialVersionUID = 1L;
	
	private UserDAO userDao = new UserDAO();

	@Override
	public User save(User user) {
		return userDao.save(user);
	}

	@Override
	public User delete(User user) {
		return userDao.delete(user);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User find(Long userId) {
		return userDao.find(userId);
	}

	@Override
	public List<User> paginated(int pageNumber, int perPageLimit) {
		return userDao.paginated(pageNumber, perPageLimit);
	}
	
	@Override
	public List<User> findAllByFirstNameOrLastNameOrUsername(String searchText){
		if(searchText.length() == 0){
			return userDao.findAll();
		}
		searchText = searchText + "%";
		return userDao.findAllByFirstNameOrLastNameOrUsername(searchText);
	}

}
