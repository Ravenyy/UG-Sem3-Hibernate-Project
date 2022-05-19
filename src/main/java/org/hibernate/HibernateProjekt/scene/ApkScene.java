package org.hibernate.HibernateProjekt.scene;

import org.hibernate.SessionFactory;
import org.hibernate.HibernateProjekt.HibernateUtils;
import org.hibernate.HibernateProjekt.Runner;
import org.hibernate.HibernateProjekt.admin.AddEmp;
import org.hibernate.HibernateProjekt.admin.AddItem;
import org.hibernate.HibernateProjekt.admin.AddItemSpec;
import org.hibernate.HibernateProjekt.admin.AddSupplier;
import org.hibernate.HibernateProjekt.admin.AdminLeft;
import org.hibernate.HibernateProjekt.admin.AdminRight;
import org.hibernate.HibernateProjekt.admin.EmpLeftForAdmin;
import org.hibernate.HibernateProjekt.employee.AddClient;
import org.hibernate.HibernateProjekt.employee.EmpClientOrderList;
import org.hibernate.HibernateProjekt.employee.EmpClientSearch;
import org.hibernate.HibernateProjekt.employee.EmpItemList;
import org.hibernate.HibernateProjekt.employee.EmpLeft;
import org.hibernate.HibernateProjekt.employee.EmpOrder;
import org.hibernate.HibernateProjekt.klient.KlientLeft;
import org.hibernate.HibernateProjekt.klient.OrderList;
import org.hibernate.HibernateProjekt.klient.ChangeAdress;
import org.hibernate.HibernateProjekt.klient.ChangeData;
import org.hibernate.HibernateProjekt.klient.ChangePass;
import org.hibernate.HibernateProjekt.klient.ItemList;
import org.hibernate.HibernateProjekt.login.Login;
import org.hibernate.HibernateProjekt.login.TopLogin;
import org.hibernate.HibernateProjekt.showTab.ShowAddress;
import org.hibernate.HibernateProjekt.showTab.ShowClient;
import org.hibernate.HibernateProjekt.showTab.ShowClientOrders;
import org.hibernate.HibernateProjekt.showTab.ShowEmployee;
import org.hibernate.HibernateProjekt.showTab.ShowIdStuff;
import org.hibernate.HibernateProjekt.showTab.ShowItem;
import org.hibernate.HibernateProjekt.showTab.ShowItemSpec;
import org.hibernate.HibernateProjekt.showTab.ShowShopOrders;
import org.hibernate.HibernateProjekt.showTab.ShowSuppliers;
import org.hibernate.HibernateProjekt.showTab.ShowUsers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class ApkScene extends Scene{
	VBox capnPlaceholder = new VBox();
	private Runner runner;
	Label loginError = new Label("Błędne dane logowania");
	static LoggedData loggedUser;
	//new LoggedData(rola, imie, nazwisko, miasto, kodpocztowy, ulica, budynek, mieszkanie)Login.getRole(factory)
	
    public ApkScene(Runner runner) {
        super(new BorderPane(), 800, 600);
        SessionFactory factory = HibernateUtils.getSessionFactory();
        
        this.runner = runner;
        loginSetup(factory);
        next(factory);
        ClientChangeData(factory);
        ClientShowItems(factory);
        //ClientOrderList(factory);
        EmployeeChangeData(factory);
        EmployeeItemList(factory);
        EmployeeOrder(factory);
        //EmployeeClient(factory);
        EmployeeClientRegister(factory);
        AdminIsAnEmployeeToo(factory);
        AdminAddEmp(factory);
        AdminAddItem(factory);
        AdminAddSupplier(factory);
        ShowStuff(factory);
        logOut(factory);
        capnPlaceholder.setMinWidth(250);
    }
    
    private final void loginSetup(SessionFactory factory) {
    	capnPlaceholder.getChildren().remove(loginError);
        BorderPane items = ((BorderPane) getRoot());
        items.setTop(new TopLogin(runner.getApk()));
        items.setCenter(new LoginPane());
        items.setRight(capnPlaceholder);
        items.setLeft(null);
        items.setBottom(new Exit());
        items.setId("background");
        items.getStylesheets().addAll(this.getClass().getResource("background.css").toExternalForm());
    }
    
    private void next(SessionFactory factory) {
    	LoginPane.login.setDefaultButton(true);
    	LoginPane.login.setOnAction(e->
    	{
    		loggedUser = Login.getRole(factory);
    		switch(loggedUser.rola) {
    		case "klient":
        		BackToClientPane(factory);
    			break;
    		case "pracownik":
        		BackToEmpPane(factory);
    			break;
    		case "administrator":
        		BackToAdminPane(factory);
    			break;
    		default:
    			capnPlaceholder.setAlignment(Pos.TOP_CENTER);
    			loginError.setFont(Font.font(15));
    			capnPlaceholder.setPadding(new Insets(145, 50, 0, 0));
    			capnPlaceholder.getChildren().add(loginError);
    			break;
    		}
    	});
		
    }
    
    private void BackToChangeData(SessionFactory factory) {
		BorderPane items = ((BorderPane) getRoot());
		items.setTop(null);
		items.setCenter(null);
		items.setLeft(new ChangeData(runner.getApk()));
		items.setRight(null);
    }
    private void BackToClientPane(SessionFactory factory) {
    	BorderPane items = ((BorderPane) getRoot());
		items.setTop(null);
		items.setCenter(loggedUser);
		items.setLeft(new KlientLeft(runner.getApk()));
		items.setRight(null);
    }
    
    private void BackToEmpPane(SessionFactory factory) {
    	BorderPane items = ((BorderPane) getRoot());
		items.setTop(null);
		items.setCenter(loggedUser);
		items.setLeft(new EmpLeft(runner.getApk()));
		items.setRight(null);
    }
    
    private void BackToAdminPane(SessionFactory factory) {
    	BorderPane items = ((BorderPane) getRoot());
		items.setTop(null);
		items.setCenter(new AdminRight());
		items.setLeft(new AdminLeft(runner.getApk()));
		items.setRight(null);
    }
    private void BackToAdminEmpPane(SessionFactory factory) {
    	BorderPane items = ((BorderPane) getRoot());
		items.setTop(null);
		items.setCenter(loggedUser);
		items.setRight(null);
		items.setLeft(new EmpLeftForAdmin(runner.getApk()));
    }
    	
    	private void ClientChangeData(SessionFactory factory) {
    		BorderPane items = ((BorderPane) getRoot());		
        	KlientLeft.b1.setOnAction(e->{
        		items.setCenter(null);
        		items.setLeft(new ChangeData(runner.getApk()));
        		items.setRight(null);
        	});
        	ChangeData.haslo.setOnAction(e->{
        		items.setCenter(new ChangePass(factory));
        		items.setLeft(null);
        		items.setRight(null);
        	});
        	ChangeData.adres.setOnAction(e->{
        		items.setCenter(new ChangeAdress());
        		items.setLeft(null);
        		items.setRight(null);
        	});
        	ChangePass.back.setOnAction(e->
        		BackToChangeData(factory)
        	);
        	ChangePass.ok.setDefaultButton(true);
        	ChangePass.ok.setOnAction(e->{
        		ChangePass.changePassword(ChangePass.fields, factory, loggedUser);
        		BackToChangeData(factory);
    		});
        	ChangeAdress.back.setOnAction(e->
        		BackToChangeData(factory)
        	);
        	ChangeAdress.ok.setDefaultButton(true);
        	ChangeAdress.ok.setOnAction(e->
        		BackToChangeData(factory)
        	);
        }
        
        private void ClientShowItems(SessionFactory factory) {
        	BorderPane items = ((BorderPane) getRoot());
        	KlientLeft.b2.setOnAction(e->
        	{
        		items.setCenter(new ItemList(factory));
        		items.setLeft(null);
        		items.setRight(null);
        	});
        	ItemList.back.setOnAction(e->
        		BackToClientPane(factory)
        	);
        	ItemList.ok.setOnAction(e->{
        		ItemList.placeOrder(ItemList.tf, factory, ItemList.it, loggedUser);
        		BackToClientPane(factory);
        	});
        }
        
        /*private void ClientOrderList(SessionFactory factory) {
        	BorderPane items = ((BorderPane) getRoot());
        	KlientLeft.b3.setOnAction(e->
        	{
        		items.setCenter(new OrderList());
        		items.setLeft(null);
        		items.setRight(null);
        	});
        	OrderList.back.setOnAction(e->
    			BackToClientPane(factory)
        	);
        }*/
        
        private void EmployeeChangeData(SessionFactory factory) {
    		BorderPane items = ((BorderPane) getRoot());		
        	EmpLeft.b1.setOnAction(e->{
        		items.setCenter(null);
        		items.setLeft(new ChangeData(runner.getApk()));
        		items.setRight(null);
        	});
        	ChangeData.haslo.setOnAction(e->{
        		items.setCenter(new ChangePass(factory));
        		items.setLeft(null);
        		items.setRight(null);
        	});
        	ChangeData.adres.setOnAction(e->{
        		items.setCenter(new ChangeAdress());
        		items.setLeft(null);
        		items.setRight(null);
        	});
        	ChangeData.back.setOnAction(e->{
        		switch(loggedUser.rola) {
        		case "klient":BackToClientPane(factory); break;
        		case "pracownik": BackToEmpPane(factory); break;
        		case "administrator": BackToAdminEmpPane(factory); break;
        		}
        	});
        	ChangePass.back.setOnAction(e->
        		BackToChangeData(factory)
        	);
        	ChangePass.ok.setDefaultButton(true);
        	ChangePass.ok.setOnAction(e->{
        		ChangePass.changePassword(ChangePass.fields, factory, loggedUser);
        		BackToChangeData(factory);
        	});
        	ChangeAdress.back.setOnAction(e->
        		BackToChangeData(factory)
        	);
        	ChangeAdress.ok.setDefaultButton(true);
        	ChangeAdress.ok.setOnAction(e->{
        		ChangeAdress.changeAddress(ChangeAdress.fields, factory, loggedUser);
        		BackToChangeData(factory);
        	});
        }
        
        private void EmployeeItemList(SessionFactory factory) {
    		BorderPane items = ((BorderPane) getRoot());	
    		EmpLeft.b2.setOnAction(e->{
    			items.setCenter(new EmpItemList(factory));
    			items.setLeft(null);
    			items.setRight(null);
    		});
        	EmpItemList.back.setOnAction(e->{
        		switch(loggedUser.rola) {
        		case "pracownik": BackToEmpPane(factory); break;
        		case "administrator": BackToAdminEmpPane(factory); break;
        		}
        	});
        }
        
        private void EmployeeOrder(SessionFactory factory) {
    		BorderPane items = ((BorderPane) getRoot());
    		EmpLeft.b3.setOnAction(e->{
    			items.setCenter(new EmpOrder(factory));
    			items.setLeft(null);
    			items.setRight(null);
    		});
        	EmpOrder.back.setOnAction(e->{
        		switch(loggedUser.rola) {
        		case "pracownik": BackToEmpPane(factory); break;
        		case "administrator": BackToAdminEmpPane(factory); break;
        		}
        	});
        	EmpOrder.ok.setOnAction(e->{
        		EmpOrder.placeOrder(EmpOrder.tf, factory, EmpOrder.it);
        		switch(loggedUser.rola) {
        		case "pracownik": BackToEmpPane(factory); break;
        		case "administrator": BackToAdminEmpPane(factory); break;
        		}
        	});
        }
        
        /*private void EmployeeClient(SessionFactory factory) {
    		BorderPane items = ((BorderPane) getRoot());
    		EmpLeft.b4.setOnAction(e->{
    			items.setCenter(new EmpClientSearch(factory));
    			items.setLeft(null);
    			items.setRight(null);
    		});
        	EmpClientSearch.back.setOnAction(e->{
        		switch(loggedUser.rola) {
        		case "pracownik": BackToEmpPane(factory); break;
        		case "administrator": BackToAdminEmpPane(factory); break;
        		}
        	});
        	EmpClientSearch.ok.setOnAction(e->{
        		items.setCenter(new EmpClientOrderList(1));
        		items.setLeft(null);
        		items.setRight(null);
        	});
        	EmpClientOrderList.ok.setOnAction(e->{
        		switch(loggedUser.rola) {
        		case "pracownik": BackToEmpPane(factory); break;
        		case "administrator": BackToAdminEmpPane(factory); break;
        		}
        	});
        	EmpClientOrderList.back.setOnAction(e->{
        		items.setCenter(new EmpClientSearch(factory));
    			items.setLeft(null);
    			items.setRight(null);
    		});
        }*/
        
        private void EmployeeClientRegister(SessionFactory factory) {
    		BorderPane items = ((BorderPane) getRoot());
    		EmpLeft.b5.setOnAction(e->{
    			items.setCenter(new AddClient(factory));
    			items.setLeft(null);
    			items.setRight(null);
    		});
        	AddClient.back.setOnAction(e->{
        		switch(loggedUser.rola) {
        		case "pracownik": BackToEmpPane(factory); break;
        		case "administrator": BackToAdminEmpPane(factory); break;
        		}
        	});
        	AddClient.ok.setOnAction(e->{
        		AddClient.addClient(AddClient.fields, factory);
    			switch(loggedUser.rola) {
    			case "pracownik": BackToEmpPane(factory); break;
    			case "administrator": BackToAdminEmpPane(factory); break;
    			}
    		});
        }
        
        private void AdminAddEmp(SessionFactory factory) {
        	BorderPane items = ((BorderPane) getRoot());
    		AdminLeft.b1.setOnAction(e->{
    			items.setCenter(new AddEmp(factory));
    			items.setLeft(null);
    			items.setRight(null);
    		});
        	AddEmp.ok.setOnAction(e->{
        		AddEmp.addEmployee(AddEmp.fields, factory);
        		BackToAdminPane(factory);
        	});
        	AddEmp.back.setOnAction(e->
    			BackToAdminPane(factory)
        	);
        }
        
        private void AdminAddItem(SessionFactory factory) {
        	BorderPane items = ((BorderPane) getRoot());
    		AdminLeft.b2.setOnAction(e->{
    			items.setCenter(new AddItem(factory));
    			items.setLeft(null);
    			items.setRight(null);
    		});
        	AddItem.ok.setOnAction(e->{
    			items.setCenter(new AddItemSpec(factory, AddItem.fields));
    			items.setLeft(null);
    			items.setRight(null);
        	});
        	AddItem.back.setOnAction(e->
    			BackToAdminPane(factory)
        	);
        	AddItemSpec.ok.setOnAction(e->{
        		AddItemSpec.selectSpec(factory, AddItem.fields);
        		BackToAdminPane(factory);
        	});
        	AddItemSpec.back.setOnAction(e->{
    			items.setCenter(new AddItem(factory));
    			items.setLeft(null);
    			items.setRight(null);
        	});
        }
        
        private void AdminAddSupplier(SessionFactory factory) {
        	BorderPane items = ((BorderPane) getRoot());
    		AdminLeft.b3.setOnAction(e->{
    			items.setCenter(new AddSupplier(factory));
    			items.setLeft(null);
    			items.setRight(null);
    		});
        	AddSupplier.ok.setOnAction(e->{
        		AddSupplier.addSupplier(AddSupplier.fields, factory);
        		BackToAdminPane(factory);
        	});
        	AddSupplier.back.setOnAction(e->
    			BackToAdminPane(factory)
        	);
        }
        
        private void AdminIsAnEmployeeToo(SessionFactory factory) {
        	BorderPane items = ((BorderPane) getRoot());
        	AdminLeft.b4.setOnAction(e->{
        		items.setTop(null);
        		items.setCenter(loggedUser);
    			items.setRight(null);
    			items.setLeft(new EmpLeftForAdmin(runner.getApk()));
        	});
        	EmpLeftForAdmin.b1.setOnAction(e->{
    			items.setCenter(null);
    			items.setLeft(new ChangeData(runner.getApk()));
    			items.setRight(null);
    		});
        	EmpLeftForAdmin.b2.setOnAction(e->{
    			items.setCenter(new EmpItemList(factory));
    			items.setLeft(null);
    			items.setRight(null);
    		});
        	EmpLeftForAdmin.b3.setOnAction(e->{
    			items.setCenter(new EmpOrder(factory));
    			items.setLeft(null);
    			items.setRight(null);
    		});
        	/*EmpLeftForAdmin.b4.setOnAction(e->{
    			items.setCenter(new EmpClientSearch(factory));
    			items.setLeft(null);
    			items.setRight(null);
    		});*/
        	EmpLeftForAdmin.b5.setOnAction(e->{
    			items.setCenter(new AddClient(factory));
    			items.setLeft(null);
    			items.setRight(null);
    		});
        	EmpLeftForAdmin.b6.setOnAction(e->
        		BackToAdminPane(factory)
        	);
        }
        
        private void ShowStuff(SessionFactory factory) {
        	BorderPane items = ((BorderPane) getRoot());
        	AdminRight.b1.setOnAction(e->{
    			items.setCenter(new ShowAddress(factory));
    			items.setLeft(null);
    			items.setRight(null);
        	});        	
        	AdminRight.b2.setOnAction(e->{
    			items.setCenter(new ShowEmployee(factory));
    			items.setLeft(null);
    			items.setRight(null);
        	});
        	AdminRight.b3.setOnAction(e->{
    			items.setCenter(new ShowClient(factory));
    			items.setLeft(null);
    			items.setRight(null);
        	});
        	AdminRight.b4.setOnAction(e->{
    			items.setCenter(new ShowItem(factory));
    			items.setLeft(null);
    			items.setRight(null);
        	});
        	AdminRight.b5.setOnAction(e->{
    			items.setCenter(new ShowItemSpec(factory));
    			items.setLeft(null);
    			items.setRight(null);
        	});
        	AdminRight.b6.setOnAction(e->{
    			items.setCenter(new ShowClientOrders(factory));
    			items.setLeft(null);
    			items.setRight(null);
        	});        	
        	AdminRight.b7.setOnAction(e->{
    			items.setCenter(new ShowUsers(factory));
    			items.setLeft(null);
    			items.setRight(null);
        	});        	
        	AdminRight.b8.setOnAction(e->{
    			items.setCenter(new ShowIdStuff(factory));
    			items.setLeft(null);
    			items.setRight(null);
        	});        	
        	AdminRight.b9.setOnAction(e->{
    			items.setCenter(new ShowSuppliers(factory));
    			items.setLeft(null);
    			items.setRight(null);
        	});
        	AdminRight.b10.setOnAction(e->{
    			items.setCenter(new ShowShopOrders(factory));
    			items.setLeft(null);
    			items.setRight(null);
        	});
        	ShowIdStuff.ok.setOnAction(e->
    			BackToAdminPane(factory)
    		);
        	ShowUsers.ok.setOnAction(e->
        		BackToAdminPane(factory)
    		);
        	ShowSuppliers.ok.setOnAction(e->
    		BackToAdminPane(factory)
    		);
        	ShowShopOrders.ok.setOnAction(e->
    		BackToAdminPane(factory)
    		);
        	ShowClientOrders.ok.setOnAction(e->
    		BackToAdminPane(factory)
        	);
        	ShowItemSpec.ok.setOnAction(e->
    		BackToAdminPane(factory)
        	);
        	ShowEmployee.ok.setOnAction(e->
    		BackToAdminPane(factory)
        	);
        	ShowItem.ok.setOnAction(e->
    		BackToAdminPane(factory)
        	);
        	ShowClient.ok.setOnAction(e->
    		BackToAdminPane(factory)
        	);
        	ShowAddress.ok.setOnAction(e->
    		BackToAdminPane(factory)
        	);
        }
        
        private void logOut(SessionFactory factory) {
        	KlientLeft.b4.setOnAction(e->
        		loginSetup(factory)
        	);
        	EmpLeft.b6.setOnAction(e->
        		loginSetup(factory)
        	);
        	AdminLeft.b5.setOnAction(e->
        		loginSetup(factory)
        	);
    }

}
