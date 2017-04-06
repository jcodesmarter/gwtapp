package poc.raviraj.gwtapp.server.dao;

import org.hibernate.Session;

import poc.raviraj.gwtapp.config.HibernateUtil;

public abstract class AbstractDAO {

	public Session getSession(){
		return HibernateUtil.getSession();
	}
}
