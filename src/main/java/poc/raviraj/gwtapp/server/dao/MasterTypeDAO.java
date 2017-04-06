package poc.raviraj.gwtapp.server.dao;

import java.util.List;

import org.hibernate.Criteria;

import poc.raviraj.gwtapp.shared.Department;
import poc.raviraj.gwtapp.shared.Designation;
import poc.raviraj.gwtapp.shared.Gender;
import poc.raviraj.gwtapp.shared.Role;

public class MasterTypeDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public List<Department> getAllDepartments() {
		Criteria criteria = getSession().createCriteria(Department.class);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Designation> getAllDesignations() {
		Criteria criteria = getSession().createCriteria(Designation.class);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Role> getAllRoles() {
		Criteria criteria = getSession().createCriteria(Role.class);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Gender> getAllGenders() {
		Criteria criteria = getSession().createCriteria(Gender.class);
		return criteria.list();
	}

}
