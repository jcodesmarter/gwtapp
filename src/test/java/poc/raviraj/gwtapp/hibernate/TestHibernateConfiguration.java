package poc.raviraj.gwtapp.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import poc.raviraj.gwtapp.config.HibernateUtil;
import poc.raviraj.gwtapp.shared.Department;
import poc.raviraj.gwtapp.shared.DummyTable;

public class TestHibernateConfiguration {
	
	private static Long tempId = 0L;

	@Test
	public void testOnSaveWithInterceptor(){
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		//do something
		
		DummyTable dt = new DummyTable();
		dt.setDummyText("Testing " + System.currentTimeMillis());
		session.save(dt);
		System.out.println(dt.toString());
		
		tempId = dt.getDummyId();
		
		tx.commit();
		session.flush();
		session.close();
		dt = null;
	}
	
	@Test
	public void testOnUpdateWithInterceptor(){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		//do something
		
		DummyTable dt = (DummyTable) session.get(DummyTable.class, tempId);
		dt.setDummyText("U-Testing " + System.currentTimeMillis());
		
		tx.commit();
		session.flush();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAllDepartmentsFromMasterTypes(){
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Department.class);
		List<Department> list = criteria.list();
		System.out.println(list.toString());
	}
}
