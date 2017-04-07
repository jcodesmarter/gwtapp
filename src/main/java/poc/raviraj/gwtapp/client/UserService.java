package poc.raviraj.gwtapp.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import poc.raviraj.gwtapp.shared.User;

@RemoteServiceRelativePath("users")
public interface UserService extends RemoteService {

	public User save(User user);
	
	public User delete(User user);
	
	public List<User> findAll();
	
	public User find(Long userId);
	
	public List<User> paginated(int pageNumber, int perPageLimit);
	
	public List<User> findAllByFirstNameOrLastNameOrUsername(String searchText);
	
}
