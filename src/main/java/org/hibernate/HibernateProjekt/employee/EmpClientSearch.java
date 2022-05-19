package org.hibernate.HibernateProjekt.employee;

import org.hibernate.SessionFactory;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class EmpClientSearch extends GridPane {
	Label L1 = new Label("Podaj imię klienta");
	Label L2 = new Label("Podaj nazwisko klienta");
	Label or = new Label("LUB");
	Label L3 = new Label("Podaj ID klienta");
	TextField tf1 = new TextField();
	TextField tf2 = new TextField();
	TextField tf3 = new TextField();
	public static Button ok = new Button("Dalej");
	public static Button back = new Button("Cofnij");
	private static int ID;
	SessionFactory factory;
	
	public EmpClientSearch(SessionFactory factory) {
		this.factory=factory;
		setup();
		}
	
	private int searchForClient(){
		String temp1 = new String();
		String temp2 = new String();
		int id = 1;
		if((tf1.getText() == null || tf1.getText().trim().isEmpty()) && (tf2.getText() == null || tf2.getText().trim().isEmpty()))
			temp1 = tf3.getText();
		
		if((tf3.getText() == null || tf3.getText().trim().isEmpty())) {
			temp1 = tf1.getText();
			temp2 = tf2.getText();
		}
		return id;
	}
	
	
	private void setup() {
		add(L1, 0, 0);
		add(L2, 0, 1);
		add(or, 0, 2);
		add(L3, 0, 3);
		add(tf1, 1, 0);
		add(tf2, 1, 1);
		add(new Label("    "), 1, 2);
		add(tf3, 1, 3);
		add(back, 0, 4);
		add(ok, 1, 4);
		
		setAlignment(Pos.CENTER);
		setPrefWidth(200);
		setHgap(10);
		setVgap(10);
	}

	public static int getID() {
		return ID;
	}

	public static void setID(int iD) {
		ID = iD;
	}

}
