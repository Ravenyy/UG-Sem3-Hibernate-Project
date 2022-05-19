package org.hibernate.HibernateProjekt.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="zamowienie_klienta",
uniqueConstraints={@UniqueConstraint(columnNames= {"ID"})})
public class zamowienie_klienta{
	
	public zamowienie_klienta(){}
		
	private Integer id, idZamowienia, idPrzedmiot;
	
	
	public zamowienie_klienta(Integer id, Integer idZamowienia, Integer idPrzedmiot) {
		this.id=id;
		this.idZamowienia=idZamowienia;
		this.idPrzedmiot=idPrzedmiot;
	}
	
	@Id
	@Column(name="ID", updatable=false)
	public Integer getID() {
		return id;}
	public void setID(Integer id) {
		this.id=id;}
	
	@OneToOne(mappedBy = "zamowienie_klienta")
	private Zamowienia_klient orders;
	@OneToOne(mappedBy = "zamowienie_klienta")
	private Przedmiot item;
		
	@Column(name="IdZamowienia", nullable=false)
	public Integer getIdZamowienia() {
		return idZamowienia;}
	public void setIdZamowienia(Integer idZamowienia) {
		this.idZamowienia=idZamowienia;}
	
	@Column(name="IdPrzedmiot", nullable=false)
	public Integer getIdPrzedmiot() {
		return idPrzedmiot;}
	public void setIdPrzedmiot(Integer idPrzedmiot) {
		this.idPrzedmiot=idPrzedmiot;}
}
