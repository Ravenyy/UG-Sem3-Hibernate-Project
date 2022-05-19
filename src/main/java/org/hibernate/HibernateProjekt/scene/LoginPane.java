package org.hibernate.HibernateProjekt.scene;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateProjekt.apk.Apk;
import org.hibernate.HibernateProjekt.entities.Adres;
import org.hibernate.HibernateProjekt.login.Login;
import org.hibernate.query.Query;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class LoginPane extends GridPane {
	public static Button login = new Button("Zaloguj");
	private static TextField loginField = new TextField();
	private static PasswordField passwordField = new PasswordField();
	
		public LoginPane(){
			setupPane();
			addLabels();
			add(loginField, 1, 0);
			add(passwordField, 1, 1);
			add(login, 1, 2);
			login.setPrefSize(80, 30);
			loginField.clear();
			passwordField.clear();
		}


		private void setupPane() {
			setAlignment(Pos.CENTER);
			setPrefWidth(200);
			setPadding(new Insets(0, 0, 100, 170));
			setHgap(10);
			setVgap(10);
		}
		
		private void addLabels() {
			Label user = new Label("Użytkownik");
			user.setFont(Font.font(20));
			
			Label password = new Label("Hasło");
			password.setFont(Font.font(20));
			
			add(user, 0, 0);
			add(password, 0, 1);
		}
		
		public static String getPane() {
			Label loginName = new Label();
			loginName.setText(loginField.getText());	
        	String usrName = loginName.getText();
	        return usrName;
		}
		
		public static String getPass() {
			Label loginPass = new Label();
			loginPass.setText(passwordField.getText());	
        	String password = loginPass.getText();
	        return password;
		}
}
