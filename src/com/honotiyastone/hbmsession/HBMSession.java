/**
 * 
 */
package com.honotiyastone.hbmsession;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * @author Toshiba
 *
 */
public class HBMSession implements Serializable {

	private static final SessionFactory SESSION_FACTORY = buildSessionFactory();
	private static final long serialVersionUID = 1L;
	private final Session SESSION = SESSION_FACTORY.openSession();
	private static HBMSession obj = null;

	/**
	 * Create session from sessionFactory using configuration.
	 * 
	 * @throws HibernateException
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		throw new CloneNotSupportedException();
	}

	private HBMSession() {

	}

	private static class IODH {
		private static final HBMSession obj = new HBMSession();
	}

	public static HBMSession getSingleton() {
		if (obj == null)
			obj = IODH.obj;
		return obj;
	}

	@SuppressWarnings("unused")
	private HBMSession readResolve() {
		return IODH.obj;
	}

	// Hibernate 5:
	private static SessionFactory buildSessionFactory() {
		try {
			// Create the ServiceRegistry from hibernate.cfg.xml
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
					.build();

			// Create a metadata sources using the specified service registry.
			Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

			return metadata.getSessionFactoryBuilder().build();
		} catch (Throwable ex) {

			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Retrieve opened session.
	 * 
	 * @return session
	 * @throws HibernateException
	 */
	public Session getSession() throws HibernateException {
		return SESSION;
	}

	/**
	 * Close the session.
	 * 
	 * @throws HibernateException
	 */
	public void releaseSession() throws HibernateException {
		if (SESSION != null) {
			SESSION.flush();
			SESSION.close();
		}
	}

	/**
	 * Close the whole session factory when all the requests have been processed
	 */
	public static void releaseFactory() {
		if (SESSION_FACTORY != null) {
			SESSION_FACTORY.close();
		}
	}

}
