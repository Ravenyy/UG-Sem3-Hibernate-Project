package org.hibernate.HibernateProjekt.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Zaopatrzenie",
uniqueConstraints= {@UniqueConstraint(columnNames= {"ID"})})
public class Zaopatrzenie {
	
	private Integer id, idAdres;
	private String nazwa, NIP;
	
	public Zaopatrzenie() {}
	public Zaopatrzenie(Integer id, String nazwa, Integer idAdres, String NIP) {
		this.id=id;
		this.nazwa=nazwa;
		this.idAdres=idAdres;
		this.NIP=NIP;
	}
	@Id
	@Column(name="ID", updatable=false)
	public Integer getId() {
		return id;}
	public void setId(Integer id) {
		this.id = id;}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idAdres", referencedColumnName = "ID")
	private Adres adres;
	
	@OneToMany(mappedBy = "Zaopatrzenie")
	private Set<Przedmiot> Przedmiot;
	
		
	@Column(name="Nazwa")
	public String getNazwa() {
		return nazwa;}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;}
	
	@Column(name="idAdres")
	public Integer getIdAdres() {
		return idAdres;}
	public void setIdAdres(Integer idAdres) {
		this.idAdres = idAdres;}
	
	@Column(name="NIP")
	public String getNIP() {
		return NIP;}
	public void setNIP(String nIP) {
		NIP = nIP;}

}
