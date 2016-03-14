package br.com.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.configuration.HibernateConfiguration;
import br.com.model.Model;

public class Main {

	public static void main(String[] args) {
		 
		 Session session = null;
		 Transaction transaction = null;
		 
		 try {
			 session = HibernateConfiguration.getInstance().getSession();
			 transaction = session.beginTransaction();
			 
			 Model model = new Model();
			 model.setName("Teste");
			 
			 session.save(model);
			 model.setName(model.name + model.id);
			 
			 session.saveOrUpdate(model);

			 System.out.println(model.name);
			 System.out.println(model.id);
			 
			 session.delete(model);
			 transaction.commit();
			 
		 } catch(Exception e) {
			 e.printStackTrace();
		 } 
	}

}