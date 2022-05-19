package org.hibernate.HibernateProjekt.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Uzytkownicy",
uniqueConstraints={@UniqueConstraint(columnNames= {"ID"})})
public class Uzytkownicy {
	
	public Uzytkownicy(){}
		
	private Integer id;
	private String login, haslo, rola;
	
	
	public Uzytkownicy(Integer id, String login, String haslo, String rola) {
		this.id=id;
		this.login=login;
		this.haslo=haslo;
		this.rola=rola;
	}
	
	@Id
	@Column(name="ID", updatable=false)
	public Integer getID() {
		return id;}
	public void setID(Integer id) {
		this.id=id;}
	
	@OneToOne(mappedBy = "Uzytkownicy")
	private Pracownik emp;
	@OneToOne(mappedBy = "Uzytkownicy")
	private Klient cli;
	
	@Column(name="Login", nullable=false)
	public String getLogin() {
		return login;}
	public void setLogin(String login) {
		this.login = login;}
	
	@Column(name="Haslo")
	public String getHaslo() {
		return haslo;}
	public void setHaslo(String haslo) {
		this.haslo = haslo;}

	@Column(name="Rola", nullable=false)
	public String getRola() {
		return rola;}
	public void setRola(String rola) {
		this.rola = rola;}
}
	

