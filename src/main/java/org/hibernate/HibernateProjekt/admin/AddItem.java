package org.hibernate.HibernateProjekt.admin;

import org.hibernate.SessionFactory;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class AddItem extends GridPane {
	
	public static Button ok = new Button("Dalej");
	public static Button back = new Button("Cofnij");
	public static TextField[] fields = new TextField[6];
	SessionFactory factory;
	
	public AddItem(SessionFactory factory) {
		this.factory=factory;
		addLabels();
		setupPane();
		addFields();

		add(back, 0, 6);
		add(ok, 1, 6);
		Label line1 = new Label("Dopuszczalne wartosci dla rodzaju:");
		Label line2 = new Label("Strunowe, Perkusyjne, Elektroniczne, Nagłośnienie, Dęte, Element");
		add(line1, 0, 7, 8, 1);
		add(line2, 0, 8, 8, 1);
	}

	private void setupPane(){
		
		setAlignment(Pos.CENTER);
		setPrefWidth(200);
		//setPadding(new Insets(0, 0, 100, 170));
		setHgap(10);
		setVgap(10);
	}
	
	private void addLabels() {
		Label[] labels = new Label[6];
		labels[0] = new Label("Cena: ");
		labels[1] = new Label("Marka: ");
		labels[2] = new Label("Model: ");
		labels[3] = new Label("Rodzaj: ");
		labels[4] = new Label("Opis: ");
		labels[5] = new Label("Nazwa dostawcy: ");
		for(int i = 0; i <= 5; i++) {
			labels[i].setFont(Font.font(15));
			add(labels[i], 0, i);
		}
	}
	
	private void addFields() {
		for(int i = 0; i<=5; i++)
			fields[i] = new TextField();
		for (int i = 0; i<=5; i++)
			add(fields[i], 1, i);
	}

}