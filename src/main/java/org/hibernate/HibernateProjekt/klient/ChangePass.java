package org.hibernate.HibernateProjekt.klient;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateProjekt.entities.Uzytkownicy;
import org.hibernate.HibernateProjekt.scene.LoggedData;
import org.hibernate.query.Query;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class ChangePass extends GridPane {
	
	public static Button ok = new Button("Akceptuj");
	public static Button back = new Button("Cofnij");
	public static PasswordField[] fields = new PasswordField[3];
	SessionFactory factory;
	
	public ChangePass(SessionFactory factory){
		this.factory=factory;
		addLabels();
		setupPane();
		addFields();
		
		add(back, 0, 3);
		add(ok, 1, 3);
		ok.setPrefWidth(200);
	}
	

	private void setupPane(){
		
		setAlignment(Pos.CENTER);
		setPrefWidth(200);
		//setPadding(new Insets(0, 0, 100, 170));
		setHgap(10);
		setVgap(10);
	}
	
	private void addFields() {
		for(int i = 0; i<=2; i++)
			fields[i] = new PasswordField();
		for (int i = 0; i<=2; i++)
			add(fields[i], 1, i);
	}
	
	private void addLabels() {
		Label olPass = new Label("Wpisz stare hasło:");
		olPass.setFont(Font.font(15));
		
		Label nuPass = new Label("Podaj nowe hasło:");
		nuPass.setFont(Font.font(15));
		
		Label nuPass2 = new Label("Ponownie wpisz nowe hasło: ");
		nuPass2.setFont(Font.font(15));
		add(olPass, 0, 0);
		add(nuPass, 0, 1);
		add(nuPass2, 0, 2);
	}
	
	public static void changePassword(PasswordField[] fields, SessionFactory factory, LoggedData loggedUser) {
		Session session = factory.getCurrentSession();
		session.getTransaction().begin();
		Uzytkownicy u = new Uzytkownicy();
		Integer id = loggedUser.idUzytkownika;
		String login = new String();
		String haslo = new String();
		
        String sql = "Select a from " + Uzytkownicy.class.getName() + " a "
                + " order by a.id ";
        
        Query<Uzytkownicy> query = session.createQuery(sql);
        List<Uzytkownicy> users = query.getResultList();  
        for(int i=0; i<=users.size()-1; i++) 
        	if(id == users.get(i).getID()) {
        		login = users.get(i).getLogin();
        		haslo = users.get(i).getHaslo();
        		System.out.print(login + haslo);
        	}
        session.getTransaction().commit();
		
		Label oldPass = new Label();
		Label newPass = new Label();
		Label newPass2 = new Label();
		
		String pass = new String(haslo);
		
		oldPass.setText(fields[0].getText());	
    	String oPass = oldPass.getText();
    	newPass.setText(fields[1].getText());	
    	String nPass = newPass.getText();
    	newPass2.setText(fields[2].getText());	
    	String nPass2 = newPass2.getText();
    	
    	if(oPass.equals(pass) && nPass.equals(nPass2)) {
    		u = new Uzytkownicy(id, login, nPass, loggedUser.rola);
    		System.out.print(u.getID() + u.getLogin() + u.getHaslo() + u.getLogin());
    	}
    	else {	
    		System.out.println("Cos nie tak");
    		session.getTransaction().commit();
    		}
    	
    	session = factory.getCurrentSession();
		session.beginTransaction();
		session.update(u);
		session.getTransaction().commit();
		session.close();
	}
}