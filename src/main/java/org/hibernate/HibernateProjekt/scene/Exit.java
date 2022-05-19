package org.hibernate.HibernateProjekt.scene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Exit extends VBox {
	
	public static Button exit = new Button("Wyjdz");
	
	
	public Exit() {
		getChildren().add(exit);
		exit.setMinSize(100, 50);
		setAlignment(Pos.TOP_RIGHT);
		setPadding(new Insets(30));
		exit.setOnAction(e->{
			System.exit(0);
		});
	}
}
