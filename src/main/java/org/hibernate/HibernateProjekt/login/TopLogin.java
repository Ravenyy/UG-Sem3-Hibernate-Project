package org.hibernate.HibernateProjekt.login;

import org.hibernate.HibernateProjekt.apk.Apk;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TopLogin extends VBox {
	Label topLogin = new Label("Zaloguj się");
	
	public TopLogin(Apk apk){
		setAlignment(Pos.CENTER);
		topLogin.setFont(Font.font(25));
		getChildren().add(topLogin);
	}
}
