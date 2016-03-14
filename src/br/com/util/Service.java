package br.com.util;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.configuration.HibernateConfiguration;

public class Service<T, ID extends Serializable> {
	Session session = null;
	Transaction transaction = null;
	
	public T findBy(int id, Class<T> objectClass) {
		try {
			session = HibernateConfiguration.getInstance().getSession();
			transaction = session.beginTransaction();
			T result = session.get(objectClass, id);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
}
