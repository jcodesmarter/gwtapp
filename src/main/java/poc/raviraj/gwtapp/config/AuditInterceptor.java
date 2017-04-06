package poc.raviraj.gwtapp.config;

import java.io.Serializable;
import java.util.Date;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import poc.raviraj.gwtapp.shared.AuditableModel;

public class AuditInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;

	/**
	 * public void onDelete(Object entity, Serializable id, Object[] state,
	 * String[] propertyNames, Type[] types) { // do nothing }
	 */

	// This method is called when object gets updated.
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
		boolean performed = false;
		if (entity instanceof AuditableModel) {
			System.out.println("Update Operation");
			Date modifiedDate = new Date();
			for (int i = 0; i < propertyNames.length; i++) {
				if ("modifiedBy".equals(propertyNames[i]))
					currentState[i] = null;// set uid from session
				if ("modifiedDate".equals(propertyNames[i]))
					currentState[i] = modifiedDate;
			}
			performed = true;
		}
		return performed;
	}

	/**
	 * public boolean onLoad(Object entity, Serializable id, Object[] state,
	 * String[] propertyNames, Type[] types) { // do nothing return true; }
	 */

	// This method is called when object gets created.
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		boolean performed = false;
		if (entity instanceof AuditableModel) {
			System.out.println("Create Operation");
			Date createdDate = new Date();
			for (int i = 0; i < propertyNames.length; i++) {
				if ("createdBy".equals(propertyNames[i]))
					state[i] = null;
				if ("modifiedBy".equals(propertyNames[i]))
					state[i] = null;// set uid from session
				if ("createdDate".equals(propertyNames[i]))
					state[i] = createdDate;
				if ("modifiedDate".equals(propertyNames[i]))
					state[i] = createdDate;
			}
			performed = true;
		}
		return performed;
	}

	/**
	 * // called before commit into database public void preFlush(Iterator
	 * iterator) { System.out.println("preFlush"); }
	 */

	/**
	 * // called after committed into database public void postFlush(Iterator
	 * iterator) { System.out.println("postFlush"); }
	 */
}