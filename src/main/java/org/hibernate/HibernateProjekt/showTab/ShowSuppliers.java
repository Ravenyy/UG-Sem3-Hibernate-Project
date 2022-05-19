package org.hibernate.HibernateProjekt.showTab;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateProjekt.HibernateUtils;
import org.hibernate.HibernateProjekt.entities.Zaopatrzenie;
import org.hibernate.query.Query;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class ShowSuppliers extends VBox {
	public static Button ok = new Button("OK");
	SessionFactory factory;
	GridPane grid = new GridPane();
	ScrollPane pane = new ScrollPane();
	
	public ShowSuppliers(SessionFactory factory) {
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
	
	        String sql = "Select a from " + Zaopatrzenie.class.getName() + " a "
	                + " order by a.id ";
	
	        Query<Zaopatrzenie> query = session.createQuery(sql);
	        List<Zaopatrzenie> dostawcy = query.getResultList();
		    int a = dostawcy.size() +1;
		    Label[][] labs = new Label[4][a];
		    labs[0][0] = new Label("ID");
		    labs[1][0] = new Label("Nazwa");
		    labs[2][0] = new Label("IdAdresu");
		    labs[3][0] = new Label("NIP");
		    
		    for(Zaopatrzenie su : dostawcy) {
		    	labs[0][i] = new Label(String.valueOf(su.getId()));
		    	labs[1][i] = new Label(su.getNazwa());
		    	labs[2][i] = new Label(String.valueOf(su.getIdAdres()));
		    	labs[3][i] = new Label(su.getNIP());
		    	i++;
		    }
		    
		    for(int j=0; j<=3; j++) {
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