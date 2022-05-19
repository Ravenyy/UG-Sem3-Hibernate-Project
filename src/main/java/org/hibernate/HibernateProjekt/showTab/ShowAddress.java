package org.hibernate.HibernateProjekt.showTab;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateProjekt.HibernateUtils;
import org.hibernate.HibernateProjekt.entities.Adres;
import org.hibernate.query.Query;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ShowAddress extends VBox {
	public static Button ok = new Button("OK");
	SessionFactory factory;
	GridPane grid = new GridPane();
	ScrollPane pane = new ScrollPane();
	
	public ShowAddress(SessionFactory factory) {
		this.factory=factory;
		grid.setId("tabBG");
		setup();
		hibFactory();
		pane.setFitToHeight(true);
		pane.setFitToWidth(true);
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
	
	        String sql = "Select a from " + Adres.class.getName() + " a "
	                + " order by a.id ";
	
	        Query<Adres> query = session.createQuery(sql);
	        List<Adres> adresy = query.getResultList();
		    int a = adresy.size() +1;
		    Label[][] labs = new Label[6][a];
		    labs[0][0] = new Label("ID");
		    labs[1][0] = new Label("Miasto");
		    labs[2][0] = new Label("KodPocztowy");
		    labs[3][0] = new Label("Ulica");
		    labs[4][0] = new Label("NrMieszkania");
		    labs[5][0] = new Label("NrBudynku");
		    
		    for(Adres ad : adresy) {
		    	labs[0][i] = new Label(String.valueOf(ad.getID()));
		    	labs[1][i] = new Label(ad.getMiasto());
		    	labs[2][i] = new Label(ad.getKodPocztowy());
		    	labs[3][i] = new Label(ad.getUlica());
		    	labs[4][i] = new Label(ad.getNrBudynku());
		    	if(ad.getNrMieszkania() == null)
		    		labs[5][i] = new Label(" ");
		    	else
		    		labs[5][i] = new Label(String.valueOf(ad.getNrMieszkania()));
		    	i++;
		    }
		    
		    for(int j=0; j<=5; j++) {
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