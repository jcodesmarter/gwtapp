package poc.raviraj.gwtapp.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import poc.raviraj.gwtapp.shared.AuditableModel;
import poc.raviraj.gwtapp.shared.Department;
import poc.raviraj.gwtapp.shared.Designation;
import poc.raviraj.gwtapp.shared.DummyTable;
import poc.raviraj.gwtapp.shared.Gender;
import poc.raviraj.gwtapp.shared.MasterType;
import poc.raviraj.gwtapp.shared.Role;
import poc.raviraj.gwtapp.shared.User;

public class HibernateUtil {

	private static SessionFactory sessionFactory = new Configuration().addAnnotatedClass(AuditableModel.class).addAnnotatedClass(MasterType.class).addAnnotatedClass(DummyTable.class).addAnnotatedClass(Department.class).addAnnotatedClass(Designation.class).addAnnotatedClass(Role.class).addAnnotatedClass(Gender.class).addAnnotatedClass(User.class).setInterceptor(new AuditInterceptor()).configure().buildSessionFactory();

	static {
		//added this static block just to load the hibernate during class loading itself.
		HibernateUtil.getSession().close();
	}

	public static Session getSession() {
		return sessionFactory
				// .withOptions().interceptor(new AuditInterceptor())
				.openSession();
	}

}
