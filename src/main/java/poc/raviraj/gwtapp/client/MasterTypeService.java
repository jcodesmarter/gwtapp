package poc.raviraj.gwtapp.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import poc.raviraj.gwtapp.shared.Department;
import poc.raviraj.gwtapp.shared.Designation;
import poc.raviraj.gwtapp.shared.Gender;
import poc.raviraj.gwtapp.shared.Role;

@RemoteServiceRelativePath("mastertypes")
public interface MasterTypeService extends RemoteService {

	public List<Department> getAllDepartments();
	
	public List<Designation> getAllDesignations();
	
	public List<Role> getAllRoles();
	
	public List<Gender> getAllGenders();
}
