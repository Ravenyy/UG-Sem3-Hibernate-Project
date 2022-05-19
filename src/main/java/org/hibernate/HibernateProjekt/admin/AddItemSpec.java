package org.hibernate.HibernateProjekt.admin;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateProjekt.entities.Przedmiot;
import org.hibernate.HibernateProjekt.entities.Specyfikacja_przedmiotu;
import org.hibernate.HibernateProjekt.entities.Zaopatrzenie;
import org.hibernate.HibernateProjekt.entities.zaopatrzenie_przedmioty;
import org.hibernate.query.Query;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class AddItemSpec extends GridPane {
	
	public static Button ok = new Button("OK");
	public static Button back = new Button("Cofnij");
	static TextField[] fields = new TextField[5];
	SessionFactory factory;
	
	public AddItemSpec(SessionFactory factory, TextField[] f) {
		this.factory=factory;
		setupPane();
		add(back, 0, 5);
		add(ok, 1, 5);
		String s = getSuppName(factory, f);
		
		if(s.equals(f[5].getText())){
			switch(f[3].getText()) {
			case "Strunowe":
				addGUIString();
				break;
			case "Perkusyjne":
				addGUIDrum();
				break;
			case "Elektroniczne":
				addEmptySpec(factory, f);
				break;
			case "Nagłośnienie":
				addGUISound();
				break;
			case "Dęte":
				addGUIWind();
				break;
			case "Element":
				addEmptySpec(factory, f);
				break;
			default:
				wrongData();
				break;
			}
		}
		else
			wrongData();
	}
	
	String getSuppName(SessionFactory factory, TextField[] f){
		Session session = factory.getCurrentSession();
		String suppName = f[5].getText();
		String toReturn = "error";
		
		try {
	    	session.getTransaction().begin();
	        String sql = "Select a from " + Zaopatrzenie.class.getName() + " a "
	                + " order by a.id ";
	        
	        Query<Zaopatrzenie> query = session.createQuery(sql);
	        List<Zaopatrzenie> supps = query.getResultList(); 
	        for(int i=0; i<=supps.size()-1; i++) {
	        	if(supps.get(i).getNazwa().equals(suppName))
	        		toReturn = suppName; 
	        }
	        session.getTransaction().commit();	
		}
		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return toReturn;
	}
 
	private void addGUIWind() {
		Label[] labels = new Label[1];
		labels[0] = new Label("Materiał korpusu: ");
		for(int i = 0; i <= 0; i++) {
			labels[i].setFont(Font.font(15));
			add(labels[i], 0, i);
		}
		for(int i = 0; i<=0; i++)
			fields[i] = new TextField();
		for (int i = 0; i<=0; i++)
			add(fields[i], 1, i);
	}

	private void addGUISound() {
		Label[] labels = new Label[1];
		labels[0] = new Label("Moc[W]: ");
		for(int i = 0; i <= 0; i++) {
			labels[i].setFont(Font.font(15));
			add(labels[i], 0, i);
		}
		for(int i = 0; i<=0; i++)
			fields[i] = new TextField();
		for (int i = 0; i<=0; i++)
			add(fields[i], 1, i);
	}

	private void addGUIDrum() {
		Label[] labels = new Label[4];
		labels[0] = new Label("Materiał korpusu: ");
		labels[1] = new Label("Średnica pola gry[CM]: ");
		labels[2] = new Label("Wysokość[CM]: ");
		labels[3] = new Label("Materiał naciągu: ");
		for(int i = 0; i <= 3; i++) {
			labels[i].setFont(Font.font(15));
			add(labels[i], 0, i);
		}
		
		for(int i = 0; i<=3; i++)
			fields[i] = new TextField();
		for (int i = 0; i<=3; i++)
			add(fields[i], 1, i);
	}

	private void addGUIString() {
		Label[] labels = new Label[5];
		labels[0] = new Label("Materiał korpusu: ");
		labels[1] = new Label("Materiał gryfu: ");
		labels[2] = new Label("Ilość progów: ");
		labels[3] = new Label("Rodzaj mostka: ");
		labels[4] = new Label("Ilosc strun: ");
		for(int i = 0; i <= 4; i++) {
			labels[i].setFont(Font.font(15));
			add(labels[i], 0, i);
		}
		
		for(int i = 0; i<=4; i++)
			fields[i] = new TextField();
		for (int i = 0; i<=4; i++)
			add(fields[i], 1, i);
	}

	private void wrongData() {
		Label l = new Label("Podano złe dane.");
		add(l, 0, 0);
		getChildren().remove(ok);
	}

	public static void selectSpec(SessionFactory factory, TextField[] f) {
		switch(f[3].getText()) {
		case "Strunowe":
			addSpecString(factory, f);
			break;
		case "Perkusyjne":;
			addSpecDrum(factory, f);
			break;
		case "Nagłośnienie":
			addSpecSound(factory, f);
			break;
		case "Dęte":
			addSpecWind(factory, f);
			break;
		}
	}
	
	private void addEmptySpec(SessionFactory factory, TextField[] f) {
		Session session = factory.getCurrentSession();
		Przedmiot item;
		Specyfikacja_przedmiotu spec = new Specyfikacja_przedmiotu();
		zaopatrzenie_przedmioty connect;
        int max = 0, idDostawcy = 0, idPrzedmiotu = 0;
		
		String price = f[0].getText();
		String brand = f[1].getText();
		String model = f[2].getText();
		String type = f[3].getText();
		String desc = f[4].getText();
		String suppName = f[5].getText();
		
		try {
	    	session.getTransaction().begin();
	        String sql = "Select a from " + Zaopatrzenie.class.getName() + " a "
	                + " order by a.id ";
	        
	        Query<Zaopatrzenie> query = session.createQuery(sql);
	        List<Zaopatrzenie> supps = query.getResultList(); 
	        for(int i=0; i<=supps.size()-1; i++)
	        	if(supps.get(i).getNazwa().equals(suppName)) 
	        		idDostawcy = supps.get(i).getId();
	        		//supp = new Zaopatrzenie(supps.get(i).getId(), supps.get(i).getNazwa(), supps.get(i).getIdAdres(), supps.get(i).getNIP());
	        
	        sql = "Select a from " + Przedmiot.class.getName() + " a "
	                + " order by a.id ";
	        
	        Query<Przedmiot> query1 = session.createQuery(sql);
	        List<Przedmiot> items = query1.getResultList(); 
			for(int i=0; i<=items.size()-1; i++)
	        	if(items.get(i).getID() >= max)
	        		max=idPrzedmiotu=items.get(i).getID();
			
			item = new Przedmiot(max+1, 0, BigDecimal.valueOf(Double.valueOf(price)), BigDecimal.valueOf(Double.valueOf(price)*0.23),
					brand, model, type, desc);
			
			max = 0;
			
			sql = "Select a from " + Specyfikacja_przedmiotu.class.getName() + " a "
	                + " order by a.id ";
			
	        Query<Specyfikacja_przedmiotu> query2 = session.createQuery(sql);
	        List<Specyfikacja_przedmiotu> specs = query2.getResultList(); 
			for(int i=0; i<=specs.size()-1; i++)
	        	if(specs.get(i).getID() >= max)
	        		max=items.get(i).getID();
	        
			spec.setID(max+1);
			spec.setIdPrzedmiotu(item.getID());
			
			max = 0;
			
			sql = "Select a from " + zaopatrzenie_przedmioty.class.getName() + " a "
	                + " order by a.id ";
			
	        Query<zaopatrzenie_przedmioty> query3 = session.createQuery(sql);
	        List<zaopatrzenie_przedmioty> con = query3.getResultList(); 
			for(int i=0; i<=con.size()-1; i++)
	        	if(con.get(i).getID() >= max)
	        		max=items.get(i).getID();
			
			connect = new zaopatrzenie_przedmioty(max+1, idDostawcy, idPrzedmiotu);

	        session.persist(item);
	        session.flush();
			session.persist(connect);
			session.flush();
	        session.persist(spec);
	        session.flush();
	    	session.getTransaction().commit();
	    	
	    	add(new Label("Dodano element"), 0, 0);
	    	getChildren().remove(back);
	        
		}
		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	private static void addSpecWind(SessionFactory factory, TextField[] f) {
		Session session = factory.getCurrentSession();
		Przedmiot item;
		Specyfikacja_przedmiotu spec = new Specyfikacja_przedmiotu();
		zaopatrzenie_przedmioty connect;
        int max = 0, idDostawcy = 0, idPrzedmiotu = 0;
		
		String price = f[0].getText();
		String brand = f[1].getText();
		String model = f[2].getText();
		String type = f[3].getText();
		String desc = f[4].getText();
		String suppName = f[5].getText();
		
		String body = fields[0].getText();
		
		try {
	    	session.getTransaction().begin();
	        String sql = "Select a from " + Zaopatrzenie.class.getName() + " a "
	                + " order by a.id ";
	        
	        Query<Zaopatrzenie> query = session.createQuery(sql);
	        List<Zaopatrzenie> supps = query.getResultList(); 
	        for(int i=0; i<=supps.size()-1; i++)
	        	if(supps.get(i).getNazwa().equals(suppName))
	        		idDostawcy = supps.get(i).getId();
	        		//supp = new Zaopatrzenie(supps.get(i).getId(), supps.get(i).getNazwa(), supps.get(i).getIdAdres(), supps.get(i).getNIP());
	        
	        
	        sql = "Select a from " + Przedmiot.class.getName() + " a "
	                + " order by a.id ";
	        
	        Query<Przedmiot> query1 = session.createQuery(sql);
	        List<Przedmiot> items = query1.getResultList(); 
			for(int i=0; i<=items.size()-1; i++)
	        	if(items.get(i).getID() >= max)
	        		max=idPrzedmiotu=items.get(i).getID();
			
			item = new Przedmiot(max+1, 0, BigDecimal.valueOf(Double.valueOf(price)), BigDecimal.valueOf(Double.valueOf(price)*0.23),
					brand, model, type, desc);
			
			max = 0;
			
			sql = "Select a from " + Specyfikacja_przedmiotu.class.getName() + " a "
	                + " order by a.id ";
			
	        Query<Specyfikacja_przedmiotu> query2 = session.createQuery(sql);
	        List<Specyfikacja_przedmiotu> specs = query2.getResultList(); 
			for(int i=0; i<=specs.size()-1; i++)
	        	if(specs.get(i).getID() >= max)
	        		max=items.get(i).getID();
	        
			spec.setID(max+1);
			spec.setIdPrzedmiotu(item.getID());
			spec.setMaterialKorpusu(body);
			
			max = 0;
			
			sql = "Select a from " + zaopatrzenie_przedmioty.class.getName() + " a "
	                + " order by a.id ";
			
	        Query<zaopatrzenie_przedmioty> query3 = session.createQuery(sql);
	        List<zaopatrzenie_przedmioty> con = query3.getResultList(); 
			for(int i=0; i<=con.size()-1; i++)
	        	if(con.get(i).getID() >= max)
	        		max=items.get(i).getID();
			
			connect = new zaopatrzenie_przedmioty(max+1, idDostawcy, idPrzedmiotu);

	        session.persist(item);
	        session.flush();
			session.persist(connect);
			session.flush();
	        session.persist(spec);
	        session.flush();
	    	session.getTransaction().commit();
	    	
		}
		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	private static void addSpecSound(SessionFactory factory, TextField[] f) {
		Session session = factory.getCurrentSession();
		Przedmiot item;
		Specyfikacja_przedmiotu spec = new Specyfikacja_przedmiotu();
		zaopatrzenie_przedmioty connect;
        int max = 0, idDostawcy = 0, idPrzedmiotu = 0;
		
		String price = f[0].getText();
		String brand = f[1].getText();
		String model = f[2].getText();
		String type = f[3].getText();
		String desc = f[4].getText();
		String suppName = f[5].getText();
		
		String pow = fields[0].getText();
		
		try {
	    	session.getTransaction().begin();
	        String sql = "Select a from " + Zaopatrzenie.class.getName() + " a "
	                + " order by a.id ";
	        
	        Query<Zaopatrzenie> query = session.createQuery(sql);
	        List<Zaopatrzenie> supps = query.getResultList(); 
	        for(int i=0; i<=supps.size()-1; i++)
	        	if(supps.get(i).getNazwa().equals(suppName))
	        		idDostawcy = supps.get(i).getId();
	        		//supp = new Zaopatrzenie(supps.get(i).getId(), supps.get(i).getNazwa(), supps.get(i).getIdAdres(), supps.get(i).getNIP());
	        
	        
	        sql = "Select a from " + Przedmiot.class.getName() + " a "
	                + " order by a.id ";
	        
	        Query<Przedmiot> query1 = session.createQuery(sql);
	        List<Przedmiot> items = query1.getResultList(); 
			for(int i=0; i<=items.size()-1; i++)
	        	if(items.get(i).getID() >= max)
	        		max=idPrzedmiotu=items.get(i).getID();
			
			item = new Przedmiot(max+1, 0, BigDecimal.valueOf(Double.valueOf(price)), BigDecimal.valueOf(Double.valueOf(price)*0.23),
					brand, model, type, desc);
			
			max = 0;
			
			sql = "Select a from " + Specyfikacja_przedmiotu.class.getName() + " a "
	                + " order by a.id ";
			
	        Query<Specyfikacja_przedmiotu> query2 = session.createQuery(sql);
	        List<Specyfikacja_przedmiotu> specs = query2.getResultList(); 
			for(int i=0; i<=specs.size()-1; i++)
	        	if(specs.get(i).getID() >= max)
	        		max=items.get(i).getID();
	        
			spec.setID(max+1);
			spec.setIdPrzedmiotu(item.getID());
			spec.setMocW(Integer.valueOf(pow));
			
			max = 0;
			
			sql = "Select a from " + zaopatrzenie_przedmioty.class.getName() + " a "
	                + " order by a.id ";
			
	        Query<zaopatrzenie_przedmioty> query3 = session.createQuery(sql);
	        List<zaopatrzenie_przedmioty> con = query3.getResultList(); 
			for(int i=0; i<=con.size()-1; i++)
	        	if(con.get(i).getID() >= max)
	        		max=items.get(i).getID();
			
			connect = new zaopatrzenie_przedmioty(max+1, idDostawcy, idPrzedmiotu);

	        session.persist(item);
	        session.flush();
			session.persist(connect);
			session.flush();
	        session.persist(spec);
	        session.flush();
	    	session.getTransaction().commit();
	    	
		}
		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}


	private static void addSpecDrum(SessionFactory factory, TextField[] f) {
		Session session = factory.getCurrentSession();
		Przedmiot item;
		Specyfikacja_przedmiotu spec = new Specyfikacja_przedmiotu();
		zaopatrzenie_przedmioty connect;
        int max = 0, idDostawcy = 0, idPrzedmiotu = 0;
		
		String price = f[0].getText();
		String brand = f[1].getText();
		String model = f[2].getText();
		String type = f[3].getText();
		String desc = f[4].getText();
		String suppName = f[5].getText();
		
		String body = fields[0].getText();
		String diameter = fields[1].getText();
		String height = fields[2].getText();
		String string = fields[3].getText();
		
		try {
	    	session.getTransaction().begin();
	        String sql = "Select a from " + Zaopatrzenie.class.getName() + " a "
	                + " order by a.id ";
	        
	        Query<Zaopatrzenie> query = session.createQuery(sql);
	        List<Zaopatrzenie> supps = query.getResultList(); 
	        for(int i=0; i<=supps.size()-1; i++)
	        	if(supps.get(i).getNazwa().equals(suppName))
	        		idDostawcy = supps.get(i).getId();
	        		//supp = new Zaopatrzenie(supps.get(i).getId(), supps.get(i).getNazwa(), supps.get(i).getIdAdres(), supps.get(i).getNIP());
	        
	        
	        sql = "Select a from " + Przedmiot.class.getName() + " a "
	                + " order by a.id ";
	        
	        Query<Przedmiot> query1 = session.createQuery(sql);
	        List<Przedmiot> items = query1.getResultList(); 
			for(int i=0; i<=items.size()-1; i++)
	        	if(items.get(i).getID() >= max)
	        		max=idPrzedmiotu=items.get(i).getID();
			
			item = new Przedmiot(max+1, 0, BigDecimal.valueOf(Double.valueOf(price)), BigDecimal.valueOf(Double.valueOf(price)*0.23),
					brand, model, type, desc);
			
			max = 0;
			
			sql = "Select a from " + Specyfikacja_przedmiotu.class.getName() + " a "
	                + " order by a.id ";
			
	        Query<Specyfikacja_przedmiotu> query2 = session.createQuery(sql);
	        List<Specyfikacja_przedmiotu> specs = query2.getResultList(); 
			for(int i=0; i<=specs.size()-1; i++)
	        	if(specs.get(i).getID() >= max)
	        		max=items.get(i).getID();
	        
			spec.setID(max+1);
			spec.setIdPrzedmiotu(item.getID());
			spec.setMaterialKorpusu(body);
			spec.setSrednicaPolaGryCM(Integer.valueOf(diameter));
			spec.setWysokoscCM(Integer.valueOf(height));
			spec.setMaterialNaciagu(string);
			
			max = 0;
			
			sql = "Select a from " + zaopatrzenie_przedmioty.class.getName() + " a "
	                + " order by a.id ";
			
	        Query<zaopatrzenie_przedmioty> query3 = session.createQuery(sql);
	        List<zaopatrzenie_przedmioty> con = query3.getResultList(); 
			for(int i=0; i<=con.size()-1; i++)
	        	if(con.get(i).getID() >= max)
	        		max=items.get(i).getID();
			
			connect = new zaopatrzenie_przedmioty(max+1, idDostawcy, idPrzedmiotu);

	        session.persist(item);
	        session.flush();
			session.persist(connect);
			session.flush();
	        session.persist(spec);
	        session.flush();
	    	session.getTransaction().commit();
	    	
		}
		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	private static void addSpecString(SessionFactory factory, TextField[] f) {
		Session session = factory.getCurrentSession();
		Przedmiot item;
		Specyfikacja_przedmiotu spec = new Specyfikacja_przedmiotu();
		zaopatrzenie_przedmioty connect;
        int max = 0, idDostawcy = 0, idPrzedmiotu = 0;
		
		String price = f[0].getText();
		String brand = f[1].getText();
		String model = f[2].getText();
		String type = f[3].getText();
		String desc = f[4].getText();
		String suppName = f[5].getText();
		
		String body = fields[0].getText();
		String fretboard = fields[1].getText();
		String frets = fields[2].getText();
		String bridge = fields[3].getText();
		String strings = fields[4].getText();
		
		try {
	    	session.getTransaction().begin();
	        String sql = "Select a from " + Zaopatrzenie.class.getName() + " a "
	                + " order by a.id ";
	        
	        Query<Zaopatrzenie> query = session.createQuery(sql);
	        List<Zaopatrzenie> supps = query.getResultList(); 
	        for(int i=0; i<=supps.size()-1; i++)
	        	if(supps.get(i).getNazwa().equals(suppName))
	        		idDostawcy = supps.get(i).getId();
	        		//supp = new Zaopatrzenie(supps.get(i).getId(), supps.get(i).getNazwa(), supps.get(i).getIdAdres(), supps.get(i).getNIP());
	        
	        
	        sql = "Select a from " + Przedmiot.class.getName() + " a "
	                + " order by a.id ";
	        
	        Query<Przedmiot> query1 = session.createQuery(sql);
	        List<Przedmiot> items = query1.getResultList(); 
			for(int i=0; i<=items.size()-1; i++)
	        	if(items.get(i).getID() >= max)
	        		max=idPrzedmiotu=items.get(i).getID();
			
			item = new Przedmiot(max+1, 0, BigDecimal.valueOf(Double.valueOf(price)), BigDecimal.valueOf(Double.valueOf(price)*0.23),
					brand, model, type, desc);
			
			max = 0;
			
			sql = "Select a from " + Specyfikacja_przedmiotu.class.getName() + " a "
	                + " order by a.id ";
			
	        Query<Specyfikacja_przedmiotu> query2 = session.createQuery(sql);
	        List<Specyfikacja_przedmiotu> specs = query2.getResultList(); 
			for(int i=0; i<=specs.size()-1; i++)
	        	if(specs.get(i).getID() >= max)
	        		max=items.get(i).getID();
	        
			spec.setID(max+1);
			spec.setIdPrzedmiotu(item.getID());
			spec.setMaterialKorpusu(body);
			spec.setMaterialGryfu(fretboard);
			spec.setIloscProgow(Integer.valueOf(frets));
			spec.setRodzajMostka(bridge);
			spec.setIloscStrun(Integer.valueOf(strings));
			
			max = 0;
			
			sql = "Select a from " + zaopatrzenie_przedmioty.class.getName() + " a "
	                + " order by a.id ";
			
	        Query<zaopatrzenie_przedmioty> query3 = session.createQuery(sql);
	        List<zaopatrzenie_przedmioty> con = query3.getResultList(); 
			for(int i=0; i<=con.size()-1; i++)
	        	if(con.get(i).getID() >= max)
	        		max=items.get(i).getID();
			
			connect = new zaopatrzenie_przedmioty(max+1, idDostawcy, idPrzedmiotu);

	        session.persist(item);
	        session.flush();
			session.persist(connect);
			session.flush();
	        session.persist(spec);
	        session.flush();
	    	session.getTransaction().commit();
	    	
		}
		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	private void setupPane(){
		
		setAlignment(Pos.CENTER);
		setPrefWidth(200);
		//setPadding(new Insets(0, 0, 100, 170));
		setHgap(10);
		setVgap(10);
	}
	
}