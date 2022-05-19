package org.hibernate.HibernateProjekt.login;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateProjekt.entities.Adres;
import org.hibernate.HibernateProjekt.entities.Klient;
import org.hibernate.HibernateProjekt.entities.Pracownik;
import org.hibernate.HibernateProjekt.entities.Uzytkownicy;
import org.hibernate.HibernateProjekt.scene.LoggedData;
import org.hibernate.HibernateProjekt.scene.LoginPane;
import org.hibernate.query.Query;


public class Login {
	
	
	Login(){}
	
	public static LoggedData getRole(SessionFactory factory){
		Session session = factory.getCurrentSession();
		Uzytkownicy userData = new Uzytkownicy();
		LoggedData loggedUser = new LoggedData("e", "r", "r", "o", "r", "0", "0", 0, 0, 0, 0);
    
		try {
	    	session.getTransaction().begin();
	        String sql = "Select a from " + Uzytkownicy.class.getName() + " a "
	                + " order by a.id ";
	
	        Query<Uzytkownicy> query = session.createQuery(sql);
	        List<Uzytkownicy> users = query.getResultList();
	        
	        for(int i=0; i<=users.size()-1; i++) {
	        	if(LoginPane.getPane().equals(users.get(i).getLogin())) {
	        		
	        		userData.setLogin(users.get(i).getLogin());
	        		userData.setHaslo(users.get(i).getHaslo());
	        		userData.setID(users.get(i).getID());
	        		if(LoginPane.getPass().equals(users.get(i).getHaslo()))
	        			userData.setRola(users.get(i).getRola());
	        	}
	        }
	        /*System.out.println(userData.getLogin());
    		System.out.println(userData.getRola());
    		System.out.println(userData.getHaslo());
    		System.out.println(userData.getID());*/
	       
	        if(userData.getRola().equals("klient")) {
		        sql = "Select k from " + Klient.class.getName() + " k "
		        		+ "where k.idUzytkownika = " + String.valueOf(userData.getID());
		        Query<Klient> query1 = session.createQuery(sql);
		        List<Klient> clients = query1.getResultList();
		        
		        Klient client = new Klient();
		        client.setID(clients.get(0).getID());
		        client.setImie(clients.get(0).getImie());
		        client.setNazwisko(clients.get(0).getNazwisko());
		        client.setIdAdres(clients.get(0).getIdAdres());
		        client.setIdUzytkownika(clients.get(0).getIdUzytkownika());
		        
		        sql = "Select a from " + Adres.class.getName() + " a "
		        		+ "where a.id = " + String.valueOf(client.getIdAdres());
		        Query<Adres> query2 = session.createQuery(sql);
		        List<Adres> address = query2.getResultList();
		        
		        Adres addres = new Adres();
		        addres.setID(address.get(0).getID());
		        addres.setMiasto(address.get(0).getMiasto());
		        addres.setKodPocztowy(address.get(0).getKodPocztowy());
		        addres.setUlica(address.get(0).getUlica());
		        addres.setNrBudynku(address.get(0).getNrBudynku());
		        addres.setNrMieszkania(address.get(0).getNrMieszkania());
		        
		        loggedUser = new LoggedData(userData.getRola(), client.getImie(), client.getNazwisko(), addres.getKodPocztowy(),
		        		addres.getMiasto(), addres.getUlica(), addres.getNrBudynku(), addres.getNrMieszkania(), client.getIdAdres()
		        		, client.getIdUzytkownika(), client.getID());
	        }
	        
	        if(userData.getRola().equals("pracownik") || userData.getRola().equals("administrator")) {
		        sql = "Select p from " + Pracownik.class.getName() + " p "
		        		+ "where p.idUzytkownika = " + String.valueOf(userData.getID());
		        Query<Pracownik> query1 = session.createQuery(sql);
		        List<Pracownik> employees = query1.getResultList();
		        
		        Pracownik employee = new Pracownik();
		        employee.setID(employees.get(0).getID());
		        employee.setImie(employees.get(0).getImie());
		        employee.setNazwisko(employees.get(0).getNazwisko());
		        employee.setIdAdres(employees.get(0).getIdAdres());
		        employee.setIdUzytkownika(employees.get(0).getIdUzytkownika());
		        
		        sql = "Select a from " + Adres.class.getName() + " a "
		        		+ "where a.id = " + String.valueOf(employee.getIdAdres());
		        Query<Adres> query2 = session.createQuery(sql);
		        List<Adres> address = query2.getResultList();
		        
		        Adres addres = new Adres();
		        addres.setID(address.get(0).getID());
		        addres.setMiasto(address.get(0).getMiasto());
		        addres.setKodPocztowy(address.get(0).getKodPocztowy());
		        addres.setUlica(address.get(0).getUlica());
		        addres.setNrBudynku(address.get(0).getNrBudynku());
		        addres.setNrMieszkania(address.get(0).getNrMieszkania());
		        
		        loggedUser = new LoggedData(userData.getRola(), employee.getImie(), employee.getNazwisko(), addres.getKodPocztowy(),
		        		addres.getMiasto(), addres.getUlica(), addres.getNrBudynku(), addres.getNrMieszkania(), employee.getIdAdres()
		        		, employee.getIdUzytkownika(), employee.getID());
	        }
	        		
	        session.getTransaction().commit();
	    }
		
		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return loggedUser;
	}

}

