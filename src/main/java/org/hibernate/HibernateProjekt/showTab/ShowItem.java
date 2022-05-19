package org.hibernate.HibernateProjekt.showTab;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateProjekt.HibernateUtils;
import org.hibernate.HibernateProjekt.entities.Przedmiot;
import org.hibernate.query.Query;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class ShowItem extends VBox {
	public static Button ok = new Button("OK");
	SessionFactory factory;
	GridPane grid = new GridPane();
	ScrollPane pane = new ScrollPane();
	
	public ShowItem(SessionFactory factory) {
		this.factory=factory;
		setup();
		hibFactory();
	}

	private void setup(){
		pane.setContent(grid);
		pane.setFitToHeight(true);
		pane.setFitToWidth(true);
		getChildren().addAll(pane, ok);
		setAlignment(Pos.TOP_CENTER);
		pane.setPrefHeight(500);
	}

	private void hibFactory() {
	    Session session = factory.getCurrentSession();
	    int i = 1;
	    MathContext mc = new MathContext(4);
	    BigDecimal b = new BigDecimal("0.23");

	    
	    try {
	    	session.getTransaction().begin();
	
	        String sql = "Select a from " + Przedmiot.class.getName() + " a "
	                + " order by a.id ";
	
	        Query<Przedmiot> query = session.createQuery(sql);
	        List<Przedmiot> items = query.getResultList();
		    int a = items.size() +1;
		    Label[][] labs = new Label[8][a];
		    labs[0][0] = new Label("ID");
		    labs[1][0] = new Label("iloscSztuk");
		    labs[2][0] = new Label("cenaSztuki");
		    labs[3][0] = new Label("podatek");
		    labs[4][0] = new Label("marka");
		    labs[5][0] = new Label("model");
		    labs[6][0] = new Label("rodzaj");
		    labs[7][0] = new Label("opis");
		    
		    for(Przedmiot it : items) {
		    	labs[0][i] = new Label(String.valueOf(it.getID()));
		    	labs[1][i] = new Label(String.valueOf(it.getIloscSztuk()));
		    	labs[2][i] = new Label(String.valueOf(it.getCenaSztuki()));
		    	int l = (String.valueOf(it.getCenaSztuki())).length();
		    	if(l>6)
		    		mc = new MathContext(l-2);
		    	labs[3][i] = new Label(String.valueOf(it.getCenaSztuki().multiply(b,mc)));
		    	labs[4][i] = new Label(it.getMarka());
		    	labs[5][i] = new Label(it.getModel());
		    	labs[6][i] = new Label(it.getRodzaj());
		    	labs[7][i] = new Label(it.getOpis());
		    	i++;
		    }
		    
		    for(int j=0; j<=7; j++) {
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