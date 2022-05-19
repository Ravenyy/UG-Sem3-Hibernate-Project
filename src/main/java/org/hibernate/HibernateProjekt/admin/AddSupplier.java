package org.hibernate.HibernateProjekt.admin;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateProjekt.entities.Adres;
import org.hibernate.HibernateProjekt.entities.Zaopatrzenie;
import org.hibernate.query.Query;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class AddSupplier extends GridPane {
	
	public static Button ok = new Button("Akceptuj");
	public static Button back = new Button("Cofnij");
	public static TextField[] fields = new TextField[7];
	SessionFactory factory;
	
	public AddSupplier(SessionFactory factory) {
		this.factory=factory;
		addLabels();
		setupPane();
		addFields();

		add(back, 0, 7);
		add(ok, 1, 7);
	}

	private void setupPane(){
		
		setAlignment(Pos.CENTER);
		setPrefWidth(200);
		//setPadding(new Insets(0, 0, 100, 170));
		setHgap(10);
		setVgap(10);
	}
	
	private void addLabels() {
		Label[] labels = new Label[7];
		labels[0] = new Label("Nazwa: ");
		labels[1] = new Label("NIP: ");
		labels[2] = new Label("Miasto: ");
		labels[3] = new Label("Kod pocztowy: ");
		labels[4] = new Label("Ulica");
		labels[5] = new Label("Numer budynku: ");
		labels[6] = new Label("Numer lokalu: ");
		for(int i = 0; i <= 6; i++) {
			labels[i].setFont(Font.font(15));
			add(labels[i], 0, i);
		}
	}
	
	private void addFields() {
		for(int i = 0; i<=6; i++)
			fields[i] = new TextField();
		for (int i = 0; i<=6; i++)
			add(fields[i], 1, i);
	}
	
	public static void addSupplier(TextField[] fields, SessionFactory factory) {
		Session session = factory.getCurrentSession();
		int max = 0;
		
		Zaopatrzenie supp = new Zaopatrzenie();
		Adres ad = new Adres();

		Label name = new Label();
		Label nip = new Label();
		Label city = new Label();
		Label postalCode = new Label();
		Label street = new Label();
		Label building = new Label();
		Label flat = new Label();
		
		name.setText(fields[0].getText());	
    	String na = name.getText();
    	nip.setText(fields[1].getText());	
    	String ni = nip.getText();
    	city.setText(fields[2].getText());	
    	String ci = city.getText();
    	postalCode.setText(fields[3].getText());	
    	String pCode = postalCode.getText();
    	street.setText(fields[4].getText());	
    	String st = street.getText();
    	building.setText(fields[5].getText());	
    	String bu = building.getText();
    	flat.setText(fields[6].getText());	
    	String fl = flat.getText();
    	
		try {
			session.getTransaction().begin();
			
	        String sql = "Select a from " + Adres.class.getName() + " a "
	        		+ "order by a.id ";
	        Query<Adres> query2 = session.createQuery(sql);
	        List<Adres> address = query2.getResultList();
	        for(int i=0; i<=address.size()-1; i++)
	        	if(address.get(i).getID() >= max)
	        		max=address.get(i).getID();
	        
	        ad.setID(max+1);
	        ad.setMiasto(ci);
	        ad.setKodPocztowy(pCode);
	        ad.setUlica(st);
	        ad.setNrBudynku(bu);
	        if (fl.contentEquals(""))
	        	ad.setNrMieszkania(null);
	        else
	        	ad.setNrMieszkania(Integer.valueOf(fl));
	        
	        max = 0;
	        
	        sql = "Select p from " + Zaopatrzenie.class.getName() + " p "
	        		+ "order by p.id ";
	        Query<Zaopatrzenie> query1 = session.createQuery(sql);
	        List<Zaopatrzenie> supps = query1.getResultList();
	        for(int i=0; i<=supps.size()-1; i++)
	        	if(supps.get(i).getId() >= max)
	        		max=supps.get(i).getId();
	        
	        supp.setId(max+1);
	        supp.setNazwa(na);
	        supp.setNIP(ni);
	        supp.setIdAdres(ad.getID());	        

	        session.persist(ad);
	        session.flush();
	        session.persist(supp);
	        session.flush();
	    	session.getTransaction().commit();
	}
		
		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
}