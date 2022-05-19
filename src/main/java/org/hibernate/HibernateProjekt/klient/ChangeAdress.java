package org.hibernate.HibernateProjekt.klient;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateProjekt.entities.Adres;
import org.hibernate.HibernateProjekt.scene.LoggedData;
import org.hibernate.query.Query;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class ChangeAdress extends GridPane {	
	public static Button ok = new Button("Akceptuj");
	public static Button back = new Button("Cofnij");
	SessionFactory factory;
	LoggedData loggedUser;
	
	public static TextField[] fields = new TextField[5];

	
	public ChangeAdress() {
		addLabels();
		setupPane();
		addFields();
		add(back, 0, 5);
		add(ok, 1, 5);
		ok.setPrefWidth(200);
	}
	
	private void setupPane(){
		
		setAlignment(Pos.CENTER);
		setPrefWidth(200);
		//setPadding(new Insets(0, 0, 100, 170));
		setHgap(10);
		setVgap(10);
	}
	
	private void addLabels() {
		Label[] labels = new Label[5];;
		labels[0] = new Label("Miasto: ");
		labels[1] = new Label("Kod pocztowy: ");
		labels[2] = new Label("Ulica");
		labels[3] = new Label("Numer budynku: ");
		labels[4] = new Label("Numer mieszkania(opcjonalne): ");
		for(int i = 0; i <= 4; i++) {
			labels[i].setFont(Font.font(15));
			add(labels[i], 0, i);
		}
	}
	
	private void addFields() {
		for(int i = 0; i<=4; i++)
			fields[i] = new TextField();
		for (int i = 0; i<=4; i++)
			add(fields[i], 1, i);
	}
	
	public static void changeAddress(TextField[] fields, SessionFactory factory, LoggedData loggedUser) {
		Session session = factory.getCurrentSession();
		Integer id = loggedUser.idAdres;
		Adres a;
		
		Label city = new Label();
		Label postalCode = new Label();
		Label street = new Label();
		Label building = new Label();
		Label flat = new Label();
		
		city.setText(fields[0].getText());	
    	String ci = city.getText();
    	postalCode.setText(fields[1].getText());	
    	String pCode = postalCode.getText();
    	street.setText(fields[2].getText());	
    	String st = street.getText();
    	building.setText(fields[3].getText());	
    	String bu = building.getText();
    	flat.setText(fields[4].getText());	
    	String fl = flat.getText();

        if(fl.equals("")) 
        	a = new Adres(id, ci, pCode, st, bu, null);
        else 
        	a = new Adres(id, ci, pCode, st, bu, Integer.valueOf(fl));
        
        loggedUser.miasto = ci;
        loggedUser.kodpocztowy = pCode;
        loggedUser.ulica = st;
        loggedUser.budynek = bu;
        loggedUser.mieszkanie = Integer.valueOf(fl);
		session.beginTransaction();
		session.update(a);
		session.getTransaction().commit();
		session.close();
	}
}
