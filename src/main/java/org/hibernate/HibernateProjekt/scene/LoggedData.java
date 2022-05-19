package org.hibernate.HibernateProjekt.scene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoggedData extends VBox {
	private HBox h0 = new HBox();
	private HBox h1 = new HBox();
	private HBox h2 = new HBox();
	private HBox h3 = new HBox();
	private HBox h4 = new HBox();
	public String rola, imie, nazwisko, kodpocztowy, miasto, ulica, budynek;
	public Integer mieszkanie, idAdres, idUzytkownika, id;

	public LoggedData(String rola, String imie, String nazwisko, String kodpocztowy, String miasto, String ulica, String budynek, Integer mieszkanie
			,Integer idAdres, Integer idUzytkownika, Integer id) {
		this.rola=rola;
		this.imie=imie;
		this.nazwisko=nazwisko;
		this.kodpocztowy=kodpocztowy;
		this.miasto=miasto;
		this.ulica=ulica;
		this.budynek=budynek;
		this.mieszkanie=mieszkanie;
		this.idAdres=idAdres;
		this.idUzytkownika=idUzytkownika;
		this.id=id;
		setupPane();
		addLabels(rola, imie, nazwisko, kodpocztowy, miasto, ulica, budynek, mieszkanie);
	
		getChildren().addAll(h0, h1, h2, h3, h4);
	}
	private void setupPane() {
		setAlignment(Pos.CENTER_LEFT);
		setPrefWidth(400);
		setPadding(new Insets(0, 0, 200, 50));
		setSpacing(20);
		
	}

	private void addLabels(String rola, String imie, String nazwisko, String kodpocztowy, String miasto, String ulica, String budynek, Integer mieszkanie){
		Label adres2 = new Label();
		Label L0 = new Label("Dane zalogowanego " +rola+"a: ");
		Label L1 = new Label("Imię: ");
		Label L2 = new Label("Nazwisko: ");
		Label L3 = new Label("Adres: ");
		Label capnPlaceholder = new Label("     ");
		Label imie1 = new Label(imie);
		Label nazwisko1 = new Label(nazwisko);
		Label adres1 = new Label(kodpocztowy +", " + miasto);
		if(mieszkanie != null)
			adres2.setText(ulica + ", "+budynek+", "+mieszkanie);
		else 
			adres2.setText(ulica+", "+budynek);
		
		h0.getChildren().add(L0);
		h1.getChildren().addAll(L1, imie1);
		h2.getChildren().addAll(L2, nazwisko1);
		h3.getChildren().addAll(L3, adres1);
		h4.getChildren().addAll(capnPlaceholder, adres2);
	}
}
