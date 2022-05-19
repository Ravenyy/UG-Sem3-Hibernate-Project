package org.hibernate.HibernateProjekt.employee;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateProjekt.entities.Przedmiot;
import org.hibernate.HibernateProjekt.entities.Specyfikacja_przedmiotu;
import org.hibernate.query.Query;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EmpItemList extends GridPane{

	ActionEvent e = new ActionEvent();
	ListView<Hyperlink> itemList = new ListView<Hyperlink>();
	public static Button back = new Button("Cofnij");
	SessionFactory factory;
	VBox vbox0 = new VBox();
	VBox vbox1 = new VBox();
	VBox vbox2 = new VBox();
	VBox vbox3 = new VBox();
	HBox box = new HBox();

	public EmpItemList(SessionFactory factory) {	
		this.factory=factory;
		setupList(factory);
		add(back, 0, 1);
	}
	
	private void setupList(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		try {
	    	session.getTransaction().begin();
	    	
	    	String sql = "Select a from " + Przedmiot.class.getName() + " a "
	                + " order by a.id ";
	        
	        Query<Przedmiot> query1 = session.createQuery(sql);
	        List<Przedmiot> items = query1.getResultList(); 
	        
	        Hyperlink[] links = new Hyperlink[items.size()];
	        for(int i = 0; i<=items.size()-1; i++) {
	        	int j = i;
				links[i] = new Hyperlink(items.get(i).getMarka() + " " + items.get(i).getModel() + " Sztuk: " + items.get(i).getIloscSztuk());
				links[i].setUnderline(false);
				links[i].setVisited(true);
				links[i].setOnAction(e->{
					getItemData(factory, items.get(j));
				});
				itemList.getItems().add(links[i]);
	        }
	        box.getChildren().addAll(itemList,vbox0, vbox1, vbox2, vbox3);
			box.setAlignment(Pos.CENTER);
			add(box, 0, 0);
			session.getTransaction().commit();       
		}
		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
	private void getItemData(SessionFactory factory, Przedmiot item) {
		Session session = factory.getCurrentSession();
		Specyfikacja_przedmiotu spec;
	    MathContext mc = new MathContext(4);
	    BigDecimal b = new BigDecimal("0.23");
	    
	    Label pr = new Label("Cena sztuki: ");
	    Label tax = new Label("Podatek: ");
	    Label typ = new Label("Rodzaj przedmiotu: ");
	    Label de = new Label("Opis: ");
		Label cena = new Label(String.valueOf(item.getCenaSztuki()));
		int l = (String.valueOf(item.getCenaSztuki())).length();
    	if(l>6)
    		mc = new MathContext(l-2);
		Label podatek = new Label(String.valueOf(item.getCenaSztuki().multiply(b,mc)));
		Label rodzaj = new Label(item.getRodzaj());
		Label opis = new Label(item.getOpis());
		
		try {
	    	session.getTransaction().begin();
		
			String sql = "Select a from " + Specyfikacja_przedmiotu.class.getName() + " a "
	                + " where a.idPrzedmiotu = " + item.getID();
			
	        Query<Specyfikacja_przedmiotu> query = session.createQuery(sql);
	        spec = query.getSingleResult();
	        session.getTransaction().commit();  
	
			
		    Label[] labs = new Label[9];
		    Label[] data = new Label[9];
		    labs[0] = new Label("materiałKorpusu: ");
		    labs[1] = new Label("materiałGryfu: ");
		    labs[2] = new Label("ilośćProgów: ");
		    labs[3] = new Label("rodzajMostka: ");
		    labs[4] = new Label("ilośćStrun: ");
		    labs[5] = new Label("srednicaPolaGryCM: ");
		    labs[6] = new Label("wysokoscCM: ");
		    labs[7] = new Label("materialNaciagu: ");
		    labs[8] = new Label("mocW: ");
		    
	    	if(spec.getMaterialKorpusu() == null)
	    		data[0] = new Label("n/d");
	    	else
	    		data[0] = new Label(spec.getMaterialKorpusu());
	    	if(spec.getMaterialGryfu() == null)
	    		data[1] = new Label("n/d");
	    	else
	    		data[1] = new Label(spec.getMaterialGryfu());
	    	if(spec.getIloscProgow() == null)
	    		data[2] = new Label("n/d");
	    	else
	    		data[2]= new Label(String.valueOf(spec.getIloscProgow()));
	    	if(spec.getRodzajMostka() == null)
	    		data[3] = new Label("n/d");
	    	else
	    		data[3] = new Label(spec.getRodzajMostka());
	    	if(spec.getIloscStrun() == null)
	    		data[4] = new Label("n/d");
	    	else
	    		data[4] = new Label(String.valueOf(spec.getIloscStrun()));
	    	if(spec.getSrednicaPolaGryCM() == null)
	    		data[5] = new Label("n/d");
	    	else
	    		data[5] = new Label(String.valueOf(spec.getSrednicaPolaGryCM()));
	    	if(spec.getWysokoscCM() == null)
	    		data[6] = new Label("n/d");
	    	else
	    		data[6] = new Label(String.valueOf(spec.getWysokoscCM()));
	    	if(spec.getMaterialNaciagu() == null)
	    		data[7] = new Label("n/d");
	    	else
	    		data[7] = new Label(spec.getMaterialNaciagu());
	    	if(spec.getMocW() == null)
	    		data[8] = new Label("n/d");
	    	else
	    		data[8] = new Label(String.valueOf(spec.getMocW()));  
	    	
	    	vbox0.getChildren().clear();
	    	vbox1.getChildren().clear();
	    	vbox2.getChildren().clear();
	    	vbox3.getChildren().clear();
	    	
	    	vbox0.getChildren().addAll(pr, tax, typ, de);
	    	vbox1.getChildren().addAll(cena, podatek, rodzaj, opis);
	    	for(int i = 0; i<=8; i++) {
	    		vbox2.getChildren().add(labs[i]);
	    		vbox3.getChildren().add(data[i]);
	    	}
		}
		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
}
