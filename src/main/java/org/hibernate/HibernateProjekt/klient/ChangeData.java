package org.hibernate.HibernateProjekt.klient;

import org.hibernate.HibernateProjekt.apk.Apk;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ChangeData extends VBox {
	public static Button haslo = new Button("Zmien hasło");
	public static Button adres = new Button("Edytuj adres");
	public static Button back = new Button("Cofnij");
	private Apk apk;
	
	public ChangeData(Apk apk) {
		this.apk = apk;
		setupPane();
		
		haslo.setPrefSize(150, 20);
		adres.setPrefSize(150, 20);
		back.setPrefSize(150, 20);
	}
	
	private void setupPane() {
		setAlignment(Pos.CENTER_LEFT);
		getChildren().addAll(haslo, adres, back);
		setSpacing(20);
		setPadding(new Insets(0, 0, 200, 50));
	}

}
