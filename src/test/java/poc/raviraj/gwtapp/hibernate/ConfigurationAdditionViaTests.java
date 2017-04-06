package poc.raviraj.gwtapp.hibernate;

import javax.validation.ConstraintViolationException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import poc.raviraj.gwtapp.config.HibernateUtil;
import poc.raviraj.gwtapp.shared.Department;
import poc.raviraj.gwtapp.shared.Designation;
import poc.raviraj.gwtapp.shared.Gender;
import poc.raviraj.gwtapp.shared.Role;

public class ConfigurationAdditionViaTests {

	@Test
	public void addDepartmentsToMasterTypes() {
				
		Department department[] = new Department[4];
		department[0] = new Department();
		department[0].setKeyId(1L);
		department[0].setKeyName("Admin");
		department[1] = new Department();
		department[1].setKeyId(2L);
		department[1].setKeyName("HR");
		department[2] = new Department();
		department[2].setKeyId(3L);
		department[2].setKeyName("IT");
		department[3] = new Department();
		department[3].setKeyId(4L);
		department[3].setKeyName("Accounts");

		Session session = HibernateUtil.getSession();
		Transaction tx = session.getTransaction();
		tx.begin();

		for (Department d : department) {
			try {
				session.save(d);
			} catch (ConstraintViolationException cve) {
				System.out.println(cve.getMessage());
			}
		}

		tx.commit();
		session.close();
	}
	
	@Test
	public void addDesignationsToMasterTypes() {
		Designation designation[] = new Designation[4];
		designation[0] = new Designation();
		designation[0].setKeyId(5L);
		designation[0].setKeyName("Executive");
		designation[1] = new Designation();
		designation[1].setKeyId(6L);
		designation[1].setKeyName("Team Lead");
		designation[2] = new Designation();
		designation[2].setKeyId(7L);
		designation[2].setKeyName("Manager");
		designation[3] = new Designation();
		designation[3].setKeyId(8L);
		designation[3].setKeyName("VP");

		Session session = HibernateUtil.getSession();
		Transaction tx = session.getTransaction();
		tx.begin();

		for (Designation d : designation) {
			try {
				session.save(d);
			} catch (ConstraintViolationException cve) {
				System.out.println(cve.getMessage());
			}
		}

		tx.commit();
		session.close();
	}
	
	@Test
	public void addRolesToMasterTypes() {
		Role role[] = new Role[4];
		role[0] = new Role();
		role[0].setKeyId(9L);
		role[0].setKeyName("Level 1");
		role[1] = new Role();
		role[1].setKeyId(10L);
		role[1].setKeyName("Level 2");
		role[2] = new Role();
		role[2].setKeyId(11L);
		role[2].setKeyName("Level 3");
		role[3] = new Role();
		role[3].setKeyId(12L);
		role[3].setKeyName("Level 4");

		Session session = HibernateUtil.getSession();
		Transaction tx = session.getTransaction();
		tx.begin();

		for (Role r : role) {
			try {
				session.save(r);
			} catch (ConstraintViolationException cve) {
				System.out.println(cve.getMessage());
			}
		}

		tx.commit();
		session.close();
	}
	
	@Test
	public void addGenderToMasterTypes() {
		Gender gender[] = new Gender[3];
		gender[0] = new Gender();
		gender[0].setKeyId(13L);
		gender[0].setKeyName("Male");
		gender[1] = new Gender();
		gender[1].setKeyId(14L);
		gender[1].setKeyName("Female");
		gender[2] = new Gender();
		gender[2].setKeyId(15L);
		gender[2].setKeyName("Unknown");

		Session session = HibernateUtil.getSession();
		Transaction tx = session.getTransaction();
		tx.begin();

		for (Gender g : gender) {
			try {
				session.save(g);
			} catch (ConstraintViolationException cve) {
				System.out.println(cve.getMessage());
			}
		}

		tx.commit();
		session.close();
	}
}
