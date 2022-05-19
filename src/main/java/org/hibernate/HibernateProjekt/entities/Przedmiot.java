package org.hibernate.HibernateProjekt.entities;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

//id, iloscsztuk, cenasztuki, podatek, marka, model, rodzaj, opis
@Entity
@Table(name="Przedmiot", 
uniqueConstraints= {@UniqueConstraint(columnNames= {"ID"})})
public class Przedmiot {
	
	private Integer id, iloscSztuk;
	private BigDecimal cenaSztuki, podatek;
	private String marka, model, rodzaj, opis;
	
	public Przedmiot() {}
	public Przedmiot(Integer id, Integer iloscSztuk, BigDecimal cenaSztuki, BigDecimal podatek, String marka, String model, String rodzaj, String opis) {
		this.id=id;
		this.iloscSztuk=iloscSztuk;
		this.cenaSztuki=cenaSztuki;
		this.podatek=podatek;
		this.marka=marka;
		this.model=model;
		this.rodzaj=rodzaj;
		this.opis=opis;
	}
	
	@Id
	@Column(name="ID", updatable=false)
	public Integer getID() {
		return id;}
	public void setID(Integer id) {
		this.id = id;}
	
	@ManyToOne
	@JoinColumn(name="ID", nullable=false)
	private Zaopatrzenie Zaopatrzenie;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idPrzedmiot", referencedColumnName= "ID")
	private zamowienie_klienta clientOrder;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idPrzedmiot", referencedColumnName= "ID")
	private Zamowienia_sklep shopOrder;
	
	@Column(name="IloscSztuk", nullable=false)
	public Integer getIloscSztuk() {
		return iloscSztuk;}
	public void setIloscSztuk(Integer iloscSztuk) {
		this.iloscSztuk=iloscSztuk;}

	@Column(name="CenaSztuki", nullable=false)
	public BigDecimal getCenaSztuki() {
		return cenaSztuki;}
	public void setCenaSztuki(BigDecimal cenaSztuki) {
		this.cenaSztuki=cenaSztuki;}
	
	@Column(name="Podatek", nullable=false)
	public BigDecimal getPodatek() {
		return podatek;}
	public void setPodatek(BigDecimal podatek) {
		this.podatek=podatek;}
	
	@Column(name="Marka")
	public String getMarka() {
		return marka;}
	public void setMarka(String marka) {
		this.marka=marka;}
	
	@Column(name="Model", nullable=false)
	public String getModel() {
		return model;}
	public void setModel(String model) {
		this.model=model;}
	
	@Column(name="Rodzaj", nullable=false)
	public String getRodzaj() {
		return rodzaj;}
	public void setRodzaj(String rodzaj) {
		this.rodzaj=rodzaj;}
	
	@Column(name="Opis")
	public String getOpis() {
		return opis;}
	public void setOpis(String opis) {
		this.opis=opis;}
}
