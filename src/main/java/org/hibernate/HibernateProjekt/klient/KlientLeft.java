package org.hibernate.HibernateProjekt.klient;

import org.hibernate.HibernateProjekt.apk.Apk;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class KlientLeft extends VBox {
	public static Button b1 = new Button("Zmien dane");
	public static Button b2 = new Button("Dostepne przedmioty");
	//public static Button b3 = new Button("Lista zamowień"); 
	public static Button b4 = new Button("Wyloguj");
	private Apk apk;
	
	public KlientLeft(Apk apk){
		this.apk = apk;
		setupPane();
		getChildren().addAll(b1, b2, b4);
	}
	
	private void setupPane() {
		b1.setPrefSize(150, 20);
		b2.setPrefSize(150, 20);
		//b3.setPrefSize(150, 20);
		b4.setPrefSize(150, 20);
		setAlignment(Pos.CENTER_LEFT);
		setPrefWidth(400);
		setPadding(new Insets(0, 0, 200, 50));
		setSpacing(20);
		
	}
}