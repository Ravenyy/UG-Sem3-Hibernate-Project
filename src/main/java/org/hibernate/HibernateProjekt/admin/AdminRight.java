package org.hibernate.HibernateProjekt.admin;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AdminRight extends VBox {
	public static Button b1 = new Button("Wyświetl tab adres");
	public static Button b2 = new Button("Wyświetl tab pracownik");
	public static Button b3 = new Button("Wyświetl tab klient");
	public static Button b4 = new Button("Wyświetl tab przedmiot");
	public static Button b5 = new Button("Wyświetl tab specPrzedmiotu");
	public static Button b6 = new Button("Wyświetl tab zamowieniaKlient");
	public static Button b7 = new Button("Wyświetl tab uzytkownicy");
	public static Button b8 = new Button("Wyświetl tab dostawcyPrzedmioty");
	public static Button b9 = new Button("Wyświetl tab dostawcy");
	public static Button b10 = new Button("Wyświetl tab zamowieniaSklep");
	
	public AdminRight(){
		setupPane();
	}
	
	private void setupPane() {
		b1.setPrefSize(220, 20);
		b2.setPrefSize(220, 20);
		b3.setPrefSize(220, 20);
		b4.setPrefSize(220, 20);
		b5.setPrefSize(220, 20);
		b6.setPrefSize(220, 20);
		b7.setPrefSize(220, 20);
		b8.setPrefSize(220, 20);
		b9.setPrefSize(220, 20);
		b10.setPrefSize(220, 20);
		setAlignment(Pos.CENTER_LEFT);
		setPrefWidth(500);
		setPadding(new Insets(0, 0, 200, 50));
		setSpacing(20);
		HBox box1 = new HBox();
		HBox box2 = new HBox();
		HBox box3 = new HBox();
		HBox box4 = new HBox();
		HBox box5 = new HBox();
		box1.getChildren().addAll(b1, b6);
		box1.setSpacing(10);
		box2.getChildren().addAll(b2, b7);
		box2.setSpacing(10);
		box3.getChildren().addAll(b3, b8);
		box3.setSpacing(10);
		box4.getChildren().addAll(b4, b9);
		box4.setSpacing(10);
		box5.getChildren().addAll(b5, b10);
		box5.setSpacing(10);
		getChildren().addAll(box1, box2, box3, box4, box5);
		
	}
}
