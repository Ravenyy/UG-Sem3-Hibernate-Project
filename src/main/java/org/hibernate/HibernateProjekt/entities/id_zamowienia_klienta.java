package org.hibernate.HibernateProjekt.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="id_zamowienia_klienta",
uniqueConstraints={@UniqueConstraint(columnNames= {"ID"})})
public class id_zamowienia_klienta {
	
	public id_zamowienia_klienta(){}
		
	private Integer id, idKlient, idZamowienie;
	
	
	public id_zamowienia_klienta(Integer id, Integer idKlient, Integer idZamowienie) {
		this.id=id;
		this.idKlient=idKlient;
		this.idZamowienie=idZamowienie;
	}
	
	@Id
	@Column(name="ID", updatable=false)
	public Integer getID() {
		return id;}
	public void setID(Integer id) {
		this.id=id;}
	
	@OneToOne(mappedBy = "id_zamowienia_klienta")
	private Klient klient;
	@OneToOne(mappedBy = "id_zamowienia_klienta")
	private Zamowienia_klient orders;
	
	@Column(name="IdKlient", nullable=false)
	public Integer getIdKlient() {
		return idKlient;}
	public void setIdKlient(Integer idKlient) {
		this.idKlient=idKlient;}
	
	@Column(name="IdZamowienie", nullable=false)
	public Integer getIdZamowienie() {
		return idZamowienie;}
	public void setIdZamowienie(Integer idZamowienie) {
		this.idZamowienie=idZamowienie;}
}
