package org.hibernate.HibernateProjekt.showTab;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateProjekt.HibernateUtils;
import org.hibernate.HibernateProjekt.entities.Specyfikacja_przedmiotu;
import org.hibernate.query.Query;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class ShowItemSpec extends VBox {
	public static Button ok = new Button("OK");
	SessionFactory factory;
	GridPane grid = new GridPane();
	ScrollPane pane = new ScrollPane();
	
	public ShowItemSpec(SessionFactory factory) {
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
	
	        String sql = "Select a from " + Specyfikacja_przedmiotu.class.getName() + " a "
	                + " order by a.id ";

	        Query<Specyfikacja_przedmiotu> query = session.createQuery(sql);
	        List<Specyfikacja_przedmiotu> itemSpecs = query.getResultList();
		    int a = itemSpecs.size() +1;
		    Label[][] labs = new Label[11][a];
		    labs[0][0] = new Label("ID");
		    labs[1][0] = new Label("materiałKorpusu");
		    labs[2][0] = new Label("materiałGryfu");
		    labs[3][0] = new Label("ilośćProgów");
		    labs[4][0] = new Label("rodzajMostka");
		    labs[5][0] = new Label("ilośćStrun");
		    labs[6][0] = new Label("srednicaPolaGryCM");
		    labs[7][0] = new Label("wysokoscCM");
		    labs[8][0] = new Label("materialNaciagu");
		    labs[9][0] = new Label("mocW");
		    labs[10][0] = new Label("idPrzedmiotu");

			//public Specyfikacja_przedmiotu(Integer id, String materialKorpusu, String materialGryfu, Integer iloscProgow, String rodzajMostka,
			///		Integer iloscStrun, Integer srednicaPolaGryCM, Integer wysokoscCM, String materialNaciagu, Integer mocW, Integer idPrzedmiotu)
			
		    for(Specyfikacja_przedmiotu is : itemSpecs) {
		    	labs[0][i] = new Label(String.valueOf(is.getID()));
		    	if(is.getMaterialKorpusu() == null)
		    		labs[1][i] = new Label("n/d");
		    	else
		    		labs[1][i] = new Label(is.getMaterialKorpusu());
		    	if(is.getMaterialGryfu() == null)
		    		labs[2][i] = new Label("n/d");
		    	else
		    		labs[2][i] = new Label(is.getMaterialGryfu());
		    	if(is.getIloscProgow() == null)
		    		labs[3][i] = new Label("n/d");
		    	else
		    		labs[3][i] = new Label(String.valueOf(is.getIloscProgow()));
		    	if(is.getRodzajMostka() == null)
		    		labs[4][i] = new Label("n/d");
		    	else
		    		labs[4][i] = new Label(is.getRodzajMostka());
		    	if(is.getIloscStrun() == null)
		    		labs[5][i] = new Label("n/d");
		    	else
		    		labs[5][i] = new Label(String.valueOf(is.getIloscStrun()));
		    	if(is.getSrednicaPolaGryCM() == null)
		    		labs[6][i] = new Label("n/d");
		    	else
		    		labs[6][i] = new Label(String.valueOf(is.getSrednicaPolaGryCM()));
		    	if(is.getWysokoscCM() == null)
		    		labs[7][i] = new Label("n/d");
		    	else
		    		labs[7][i] = new Label(String.valueOf(is.getWysokoscCM()));
		    	if(is.getMaterialNaciagu() == null)
		    		labs[8][i] = new Label("n/d");
		    	else
		    		labs[8][i] = new Label(is.getMaterialNaciagu());
		    	if(is.getMocW() == null)
		    		labs[9][i] = new Label("n/d");
		    	else
		    		labs[9][i] = new Label(String.valueOf(is.getMocW()));
		    	labs[10][i] = new Label(String.valueOf(is.getIdPrzedmiotu()));
		    	i++;
		    }
		    
		    for(int j=0; j<=10; j++) {
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