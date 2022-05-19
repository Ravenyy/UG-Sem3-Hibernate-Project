package org.hibernate.HibernateProjekt.employee;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class EmpClientOrderList extends BorderPane{

	ActionEvent e = new ActionEvent();
	ListView<Hyperlink> orderList = new ListView<Hyperlink>();
	public static Button ok = new Button("OK");
	public static Button back = new Button("Cofnij");
	GridPane pane = new GridPane();
	int ID;
	
	public EmpClientOrderList(int ID) {	
		this.ID=ID;
		setupList();
		setCenter(pane);
		pane.add(back, 0, 1);
		pane.add(ok, 1, 1);
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
	
	private void getSelectedOrder(Label order) {
		pane.getChildren().remove(order);
		order.setText("JakiestamDane");
		pane.add(order, 1, 0);
	}
}