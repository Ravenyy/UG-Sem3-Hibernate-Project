package org.hibernate.HibernateProjekt.employee;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateProjekt.entities.Klient;
import org.hibernate.HibernateProjekt.entities.Przedmiot;
import org.hibernate.HibernateProjekt.entities.Zamowienia_klient;
import org.hibernate.HibernateProjekt.entities.Zamowienia_sklep;
import org.hibernate.HibernateProjekt.entities.id_zamowienia_klienta;
import org.hibernate.HibernateProjekt.entities.zamowienie_klienta;
import org.hibernate.HibernateProjekt.scene.LoggedData;
import org.hibernate.query.Query;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EmpOrder extends GridPane{

	ActionEvent e = new ActionEvent();
	ListView<Hyperlink> itemList = new ListView<Hyperlink>();
	public static Button ok = new Button("Złóż zamówienie");
	public static Button back = new Button("Cofnij");
	Label capnPlaceholder = new Label("   ");
	public static Przedmiot it = new Przedmiot();
	SessionFactory factory;
	HBox box = new HBox();
	VBox vbox = new VBox();
	public static TextField tf = new TextField();
	
	public EmpOrder(SessionFactory factory) {	
		this.factory=factory;
		setupList(factory);
		add(back, 0, 1);
		add(capnPlaceholder, 1, 0);
		//pane.add(capnPlaceholder, 2, 0);
	}
		
	private void setupList(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		try {
	    	session.getTransaction().begin();
	    	
	    	String sql = "Select a from " + Przedmiot.class.getName() + " a "
	                + " order by a.id ";
	        
	        Query<Przedmiot> query1 = session.createQuery(sql);
	        List<Przedmiot> items = query1.getResultList(); 
	        
	        Hyperlink[] links = new Hyperlink[items.size()];
	        for(int i = 0; i<=items.size()-1; i++) {
	        	int j = i;
	        	links[i] = new Hyperlink(items.get(i).getMarka() + " " + items.get(i).getModel());
				links[i].setUnderline(false);
				links[i].setVisited(true);
				links[i].setOnAction(e->{
					placeOrderGUI(factory, items.get(j));
					it = items.get(j);
					});
				itemList.getItems().add(links[i]);
	        }
	        box.getChildren().addAll(itemList, vbox);
			box.setAlignment(Pos.CENTER);
			add(box, 0, 0);
			session.getTransaction().commit();       
		}
		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
	private void placeOrderGUI(SessionFactory factory, Przedmiot item) {
		Label ile = new Label("Podaj ilość zamawianych sztuk: ");
		vbox.getChildren().addAll(ile, tf, ok);
	}
	
	public static void placeOrder(TextField tf, SessionFactory factory, Przedmiot item) {
		Session session = factory.getCurrentSession();
		int max = 0;
		Zamowienia_sklep order;
		Date date = new Date();
		MathContext mc = new MathContext(4);
		int l = (String.valueOf(item.getCenaSztuki())).length();
    	if(l>6)
    		mc = new MathContext(l-2);
    	BigDecimal b = new BigDecimal(tf.getText());
		BigDecimal price = item.getCenaSztuki().multiply(b,mc);
		
		try {
	    	session.getTransaction().begin();
	        String sql = "Select a from " + Zamowienia_sklep.class.getName() + " a "
	                + " order by a.id ";
	        Query<Zamowienia_sklep> query = session.createQuery(sql);
	        List<Zamowienia_sklep> shopOrders = query.getResultList();  
	        for(int i=0; i<=shopOrders.size()-1; i++) 
	        	if(shopOrders.get(i).getId() >= max)
	        		max=shopOrders.get(i).getId();
	        
	        order = new Zamowienia_sklep(max+1, item.getID(), Integer.valueOf(tf.getText()), price, date);
	        
	        
	        item.setIloscSztuk(item.getIloscSztuk()+Integer.valueOf(tf.getText()));
	        
		    session.persist(order);
		    session.flush();
		    session.update(item);
		    session.getTransaction().commit();  	
		}
		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
}
