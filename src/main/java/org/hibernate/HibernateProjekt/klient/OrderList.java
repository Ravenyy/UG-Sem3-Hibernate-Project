package org.hibernate.HibernateProjekt.klient;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateProjekt.entities.Przedmiot;
import org.hibernate.HibernateProjekt.entities.Zamowienia_klient;
import org.hibernate.HibernateProjekt.scene.ApkScene;
import org.hibernate.query.Query;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class OrderList extends BorderPane{

	ActionEvent e = new ActionEvent();
	ListView<Hyperlink> orderList = new ListView<Hyperlink>();
	public static Button back = new Button("Cofnij");
	GridPane pane = new GridPane();
	VBox vbox0 = new VBox();
	VBox vbox1 = new VBox();
	HBox box = new HBox();
	
	public OrderList() {	
		setupList();
		setCenter(pane);
		pane.add(back, 0, 1);
	}
		
	private void setupList() {
		Hyperlink[] links = new Hyperlink[5];
		for(int i = 0; i<=4; i++) {
			links[i] = new Hyperlink("Test2"+i);
			links[i].setUnderline(false);
			links[i].setVisited(true);
			links[i].setOnAction(e->{
				Label order = new Label();
				order.setFont(Font.font(15));
				getSelectedOrder(order);
			});
		}
		for(int i =0; i<=4; i++)
			orderList.getItems().add(links[i]);
		HBox box = new HBox(orderList);
		box.setAlignment(Pos.CENTER);
		pane.add(box, 0, 0);
	}
	
	private void setupList(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		try {
	    	session.getTransaction().begin();
	    	
	    	/*String sql = "Select a from " + Zamowienia_klient.class.getName() + " a "
	                + " where  ";
	        
	        Query<Zamowienia_klient> query1 = session.createQuery(sql);
	        List<Zamowienia_klient> orders = query1.getResultList(); 
	        
	        Hyperlink[] links = new Hyperlink[orders.size()];
	        for(int i = 0; i<=orders.size()-1; i++) {
	        	int j = i;
	        	links[i] = new Hyperlink(items.get(i).getMarka() + " " + items.get(i).getModel() + " Sztuk: " + items.get(i).getIloscSztuk());
				links[i].setUnderline(false);
				links[i].setVisited(true);
				links[i].setOnAction(e->{
					getItemData(factory, items.get(j));
					placeOrderGUI(factory, items.get(j));
				});
				itemList.getItems().add(links[i]);
	        }
	        }
	        box.getChildren().addAll(itemList,vbox0, vbox1, vbox2, vbox3, vbox4);
			box.setAlignment(Pos.CENTER);
			add(box, 0, 0);*/
			session.getTransaction().commit();       
		}
		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
	private void getSelectedOrder(Label order) {
		pane.getChildren().remove(order);
		order.setText("JakiestamDane");
		pane.add(order, 1, 0);
	}
}
