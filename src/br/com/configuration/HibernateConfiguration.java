package br.com.configuration;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfiguration {
	private static HibernateConfiguration cf;
	private Configuration cfg;
	private SessionFactory sessionFactory;
	
	private HibernateConfiguration() throws HibernateException {
		cfg = new Configuration();
		cfg.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
		cfg.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/watcher");
		cfg.setProperty("hibernate.connection.username", "postgres");
        cfg.setProperty("hibernate.connection.password", "admin");
        cfg.setProperty("hibernate.default_schema","watcher");
        cfg.setProperty("hibernate.show_sql", "true");
        
        cfg.addResource("br/com/hbm/Model.hbm.xml");
        
        sessionFactory = cfg.buildSessionFactory();
	}
	
	public static synchronized HibernateConfiguration getInstance() throws HibernateException {
        if (cf == null) {
            cf = new HibernateConfiguration();
        }

        return cf;
    }
	
    public Session getSession() throws HibernateException {
        Session session = sessionFactory.openSession();
        if (!session.isConnected()) {
            this.reconnect();
        }
        return session;
    }
    
    private void reconnect() throws HibernateException {
        this.sessionFactory = cfg.buildSessionFactory();
    }
}
