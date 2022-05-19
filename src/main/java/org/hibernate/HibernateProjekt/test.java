package org.hibernate.HibernateProjekt;

import java.util.List;
 
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateProjekt.HibernateUtils;
import org.hibernate.HibernateProjekt.entities.Adres;
import org.hibernate.HibernateProjekt.entities.Klient;
import org.hibernate.HibernateProjekt.entities.Pracownik;
 
public class test {
 
   public static void main(String[] args) {
       SessionFactory factory = HibernateUtils.getSessionFactory();
 
       Session session = factory.getCurrentSession();
       Integer z = 1;
       Integer x = 3;
       try {
            
           // All the action with DB via Hibernate
           // must be located in one transaction.
           // Start Transaction.            
           session.getTransaction().begin();
            
           // Create an HQL statement, query the object.
           // Equivalent to the SQL statement:
           // Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO
           String sql = "Select a from " + Adres.class.getName() + " a "
	        		+ "where a.id = " + String.valueOf(2);
   
           // Create Query object.
           Query<Adres> query = session.createQuery(sql);

           // Execute query.
           List<Adres> adresy = query.getResultList();
 
           for (Adres ad : adresy) {
               System.out.println("Adres: " + ad.getID() + " : "
                       + ad.getKodPocztowy() + " : " + ad.getMiasto() + " : " + ad.getUlica()
                       + " : " + ad.getNrBudynku() + " : " + ad.getNrMieszkania());
           }
           
	        sql = "Select p from " + Pracownik.class.getName() + " p "
	        		+ "where p.idUzytkownika = " + String.valueOf(z);
  
	           Query<Pracownik> query2 = session.createQuery(sql);

	           // Execute query.
	           List<Pracownik> pracownicy = query2.getResultList();
	 
	           for (Pracownik ad : pracownicy) {
	               System.out.println("Pracownik: " + ad.getID() + " : "
	                       + ad.getImie() + " : " + ad.getNazwisko() + " : " + ad.getIdAdres()
	                       + " : " + ad.getIdUzytkownika());
	           }
	           
		        sql = "Select k from " + Klient.class.getName() + " k "
		        		+ "where k.idUzytkownika = " + String.valueOf(x);
	  
		           Query<Klient> query1 = session.createQuery(sql);

		           // Execute query.
		           List<Klient> klienci = query1.getResultList();
		 
		           for (Klient ad : klienci) {
		               System.out.println("Klient: " + ad.getID() + " : "
		                       + ad.getImie() + " : " + ad.getNazwisko() + " : " + ad.getIdAdres()
		                       + " : " + ad.getIdUzytkownika());
		           }
           // Commit data.
           session.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           // Rollback in case of an error occurred.
           session.getTransaction().rollback();
       }
   }
    
}