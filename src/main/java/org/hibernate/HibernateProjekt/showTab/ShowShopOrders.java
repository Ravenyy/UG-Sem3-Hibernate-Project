package org.hibernate.HibernateProjekt.showTab;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateProjekt.HibernateUtils;
import org.hibernate.HibernateProjekt.entities.Zamowienia_sklep;
import org.hibernate.query.Query;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class ShowShopOrders extends VBox {
	public static Button ok = new Button("OK");
	SessionFactory factory;
	GridPane grid = new GridPane();
	ScrollPane pane = new ScrollPane();
	
	public ShowShopOrders(SessionFactory factory) {
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
	
	        String sql = "Select a from " + Zamowienia_sklep.class.getName() + " a "
	                + " order by a.id ";
	
	        Query<Zamowienia_sklep> query = session.createQuery(sql);
	        List<Zamowienia_sklep> zamowienia = query.getResultList();
		    int a = zamowienia.size() +1;
		    Label[][] labs = new Label[5][a];
		    labs[0][0] = new Label("ID");
		    labs[1][0] = new Label("ID Przedmiotu");
		    labs[2][0] = new Label("Ilość");
		    labs[3][0] = new Label("Do zapłaty");
		    labs[4][0] = new Label("Data zamówienia");

		    
		    for(Zamowienia_sklep kl : zamowienia) {
		    	labs[0][i] = new Label(String.valueOf(kl.getId()));
		    	labs[1][i] = new Label(String.valueOf(kl.getIdPrzedmiot()));
		    	labs[2][i] = new Label(String.valueOf(kl.getIlosc()));
		    	labs[3][i] = new Label(String.valueOf(kl.getDoSplaty()));
		    	labs[4][i] = new Label(String.valueOf(kl.getDataZamowienia()));
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