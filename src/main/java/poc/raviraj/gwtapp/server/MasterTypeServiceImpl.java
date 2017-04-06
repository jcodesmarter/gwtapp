package poc.raviraj.gwtapp.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import poc.raviraj.gwtapp.client.MasterTypeService;
import poc.raviraj.gwtapp.server.dao.MasterTypeDAO;
import poc.raviraj.gwtapp.shared.Department;
import poc.raviraj.gwtapp.shared.Designation;
import poc.raviraj.gwtapp.shared.Gender;
import poc.raviraj.gwtapp.shared.Role;

public class MasterTypeServiceImpl extends RemoteServiceServlet implements MasterTypeService {

	private static final long serialVersionUID = 1L;
	
	private MasterTypeDAO masterTypeDAO = new MasterTypeDAO();

	@Override
	public List<Department> getAllDepartments() {
		return masterTypeDAO.getAllDepartments();
	}

	@Override
	public List<Designation> getAllDesignations() {
		return masterTypeDAO.getAllDesignations();
	}

	@Override
	public List<Role> getAllRoles() {
		return masterTypeDAO.getAllRoles();
	}

	@Override
	public List<Gender> getAllGenders() {
		return masterTypeDAO.getAllGenders();
	}

}
