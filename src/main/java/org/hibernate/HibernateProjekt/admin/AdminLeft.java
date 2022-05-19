package org.hibernate.HibernateProjekt.admin;

import org.hibernate.HibernateProjekt.apk.Apk;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class AdminLeft extends VBox {
	public static Button b1 = new Button("Dodaj pracownika");
	public static Button b2 = new Button("Dodaj przedmiot");
	public static Button b3 = new Button("Dodaj dostawce");
	public static Button b4 = new Button("Interfejs pracownika"); 
	public static Button b5 = new Button("Wyloguj");
	private Apk apk;
	
	public AdminLeft(Apk apk){
		this.apk = apk;
		setupPane();
	}
	
	private void setupPane() {
		b1.setPrefSize(150, 20);
		b2.setPrefSize(150, 20);
		b3.setPrefSize(150, 20);
		b4.setPrefSize(150, 20);
		b5.setPrefSize(150, 20);
		getChildren().addAll(b1, b2, b3, b4, b5);
		setAlignment(Pos.CENTER_LEFT);
		setPrefWidth(200);
		setPadding(new Insets(0, 0, 200, 50));
		setSpacing(20);
	}
}
