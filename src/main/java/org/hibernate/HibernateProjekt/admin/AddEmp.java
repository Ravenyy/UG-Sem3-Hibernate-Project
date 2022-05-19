package org.hibernate.HibernateProjekt.admin;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateProjekt.entities.Adres;
import org.hibernate.HibernateProjekt.entities.Pracownik;
import org.hibernate.HibernateProjekt.entities.Uzytkownicy;
import org.hibernate.query.Query;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class AddEmp extends GridPane {
	
	public static Button ok = new Button("Akceptuj");
	public static Button back = new Button("Cofnij");
	public static TextField[] fields = new TextField[8];
	SessionFactory factory;
	
	public AddEmp(SessionFactory factory) {
		this.factory=factory;
		addLabels();
		setupPane();
		addFields();
		add(back, 0, 8);
		add(ok, 1, 8);
	}

	private void setupPane(){
		
		setAlignment(Pos.CENTER);
		setPrefWidth(200);
		//setPadding(new Insets(0, 0, 100, 170));
		setHgap(10);
		setVgap(10);
	}
	
	private void addLabels() {
		Label[] labels = new Label[8];
		labels[0] = new Label("Imię: ");
		labels[1] = new Label("Nazwisko: ");
		labels[2] = new Label("Miasto: ");
		labels[3] = new Label("Kod pocztowy: ");
		labels[4] = new Label("Ulica");
		labels[5] = new Label("Numer budynku: ");
		labels[6] = new Label("Numer mieszkania: ");
		labels[7] = new Label("Login: ");
		for(int i = 0; i <= 7; i++) {
			labels[i].setFont(Font.font(15));
			add(labels[i], 0, i);
		}
	}
	
	private void addFields() {
		for(int i = 0; i<=7; i++)
			fields[i] = new TextField();
		for (int i = 0; i<=7; i++)
			add(fields[i], 1, i);
	}
	
	public static void addEmployee(TextField[] fields, SessionFactory factory) {
		Session session = factory.getCurrentSession();
		int max = 0;
		//int max1 = 0;
		//String prac = new String("pracownik");
		
		Pracownik emp = new Pracownik();
		Adres ad = new Adres();
		Uzytkownicy usr = new Uzytkownicy();
		
		Label firstName = new Label();
		Label lastName = new Label();
		Label city = new Label();
		Label postalCode = new Label();
		Label street = new Label();
		Label building = new Label();
		Label flat = new Label();
		Label login = new Label();
		
		firstName.setText(fields[0].getText());	
    	String fName = firstName.getText();
    	lastName.setText(fields[1].getText());	
    	String lName = lastName.getText();
    	city.setText(fields[2].getText());	
    	String ci = city.getText();
    	postalCode.setText(fields[3].getText());	
    	String pCode = postalCode.getText();
    	street.setText(fields[4].getText());	
    	String st = street.getText();
    	building.setText(fields[5].getText());	
    	String bu = building.getText();
    	flat.setText(fields[6].getText());	
    	String fl = flat.getText();
    	login.setText(fields[7].getText());	
    	String lo = login.getText();
    	
		try {
	    	session.getTransaction().begin();
	        String sql = "Select a from " + Uzytkownicy.class.getName() + " a "
	                + " order by a.id ";
	        
	        Query<Uzytkownicy> query = session.createQuery(sql);
	        List<Uzytkownicy> users = query.getResultList();  
	        for(int i=0; i<=users.size()-1; i++) 
	        	if(users.get(i).getID() >= max)
	        		max=users.get(i).getID();
	
	        
	        usr.setID(max+1);
	        usr.setLogin(lo);
	        usr.setHaslo("haslo");
	        usr.setRola("pracownik");
	        
	        max=0;
	        
	        sql = "Select a from " + Adres.class.getName() + " a "
	        		+ "order by a.id ";
	        Query<Adres> query2 = session.createQuery(sql);
	        List<Adres> address = query2.getResultList();
	        for(int i=0; i<=address.size()-1; i++)
	        	if(address.get(i).getID() >= max)
	        		max=address.get(i).getID();
	        
	        ad.setID(max+1);
	        ad.setMiasto(ci);
	        ad.setKodPocztowy(pCode);
	        ad.setUlica(st);
	        ad.setNrBudynku(bu);
	        if (fl.contentEquals(""))
	        	ad.setNrMieszkania(null);
	        else
	        	ad.setNrMieszkania(Integer.valueOf(fl));
	        
	        max = 0;
	        
	        sql = "Select p from " + Pracownik.class.getName() + " p "
	        		+ "order by p.id ";
	        Query<Pracownik> query1 = session.createQuery(sql);
	        List<Pracownik> employees = query1.getResultList();
	        for(int i=0; i<=employees.size()-1; i++)
	        	if(users.get(i).getID() >= max)
	        		max=users.get(i).getID();
	        
	        emp.setID(max+1);
	        emp.setImie(fName);
	        emp.setNazwisko(lName);
	        emp.setIdAdres(ad.getID());
	        emp.setIdUzytkownika(usr.getID());
	        
	        System.out.println(usr.getID() + usr.getLogin() + usr.getHaslo() + usr.getRola() + ad.getID() + ad.getMiasto() + ad.getKodPocztowy()
	        			+ad.getUlica() + ad.getNrBudynku() + ad.getNrMieszkania() + emp.getID() + emp.getImie() + emp.getNazwisko() + emp.getIdAdres()
	        			+emp.getIdUzytkownika());

	        session.persist(usr);
	        session.flush();
	        session.persist(ad);
	        session.flush();
	        session.persist(emp);
	        session.flush();
	    	session.getTransaction().commit();
	}
		
		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
}
