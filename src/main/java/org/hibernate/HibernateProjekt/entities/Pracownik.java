package org.hibernate.HibernateProjekt.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Pracownik",
uniqueConstraints= {@UniqueConstraint(columnNames= {"ID"})})
public class Pracownik {
	
	private Integer id, idAdres, idUzytkownika;
	private String imie, nazwisko;
	
	public Pracownik() {}
	
	public Pracownik(Integer id, String imie, String nazwisko, Integer idAdres, Integer idUzytkownika) {
		this.id=id;
		this.imie=imie;
		this.nazwisko=nazwisko;
		this.idAdres=idAdres;
		this.idUzytkownika=idUzytkownika;
	}
	@Id
	@Column(name="ID", updatable=false)
	public Integer getID() {
		return id;}
	public void setID(Integer id) {
		this.id = id;}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idAdres", referencedColumnName = "ID")
	private Adres adres;
			
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idUzytkownika", referencedColumnName = "ID")
	private Uzytkownicy uzytkownik;
	
	@Column(name="imie", nullable=false)
	public String getImie() {
		return imie;}
	public void setImie(String imie) {
		this.imie=imie;}
	
	@Column(name="Nazwisko", nullable=false)
	public String getNazwisko() {
		return nazwisko;}
	public void setNazwisko(String nazwisko) {
		this.nazwisko=nazwisko;}
	
	@Column(name="idAdres", nullable=false)
	public Integer getIdAdres() {
		return idAdres;}
	public void setIdAdres(Integer idAdres) {
		this.idAdres=idAdres;}
	
	@Column(name="idUzytkownika", nullable = false)
	public Integer getIdUzytkownika() {
		return idUzytkownika;}
	public void setIdUzytkownika(Integer idUzytkownika) {
		this.idUzytkownika = idUzytkownika;}

}