package org.hibernate.HibernateProjekt.showTab;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateProjekt.HibernateUtils;
import org.hibernate.HibernateProjekt.entities.Pracownik;
import org.hibernate.query.Query;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class ShowEmployee extends VBox {
	public static Button ok = new Button("OK");
	SessionFactory factory;
	GridPane grid = new GridPane();
	ScrollPane pane = new ScrollPane();
	
	public ShowEmployee(SessionFactory factory) {
		this.factory=factory;
		setup();
		hibFactory();
	}

	private void setup(){
		pane.setContent(grid);
		getChildren().addAll(pane, ok);
		setAlignment(Pos.TOP_CENTER);
		pane.setPrefHeight(500);

	}

	private void hibFactory() {
	    Session session = factory.getCurrentSession();
	    int i = 1;

	    
	    try {
	    	session.getTransaction().begin();
	
	        String sql = "Select a from " + Pracownik.class.getName() + " a "
	                + " order by a.id ";
	
	        Query<Pracownik> query = session.createQuery(sql);
	        List<Pracownik> pracownicy = query.getResultList();
		    int a = pracownicy.size() +1;
		    Label[][] labs = new Label[6][a];
		    labs[0][0] = new Label("ID");
		    labs[1][0] = new Label("Imie");
		    labs[2][0] = new Label("Nazwisko");
		    labs[3][0] = new Label("IdAdresu");
		    labs[4][0] = new Label("IdUzytkownika");
		    
		    for(Pracownik pr : pracownicy) {
		    	labs[0][i] = new Label(String.valueOf(pr.getID()));
		    	labs[1][i] = new Label(pr.getImie());
		    	labs[2][i] = new Label(pr.getNazwisko());
		    	labs[3][i] = new Label(String.valueOf(pr.getIdAdres()));
		    	labs[4][i] = new Label(String.valueOf(pr.getIdUzytkownika()));
		    	i++;
		    }
		    
		    for(int j=0; j<=4; j++) {
		    	grid.add(labs[j][0], j, 0);
		    	labs[j][0].setId("labs");
		    	for (i=1; i< a; i++) {
		    		grid.add(labs[j][i], j, i);
		    		labs[j][i].setId("labs");
		    	}
		    }
	        session.getTransaction().commit();
	    }
		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
}