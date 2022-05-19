package org.hibernate.HibernateProjekt.entities;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Zamowienia_klient",
uniqueConstraints= {@UniqueConstraint(columnNames= {"ID"})})
public class Zamowienia_klient {

	private Integer id, ilosc;
	private BigDecimal cena;
	private Date dataZamowienia;
	
	public Zamowienia_klient() {}
	
	public Zamowienia_klient(Integer id, Date dataZamowienia, BigDecimal cena, Integer ilosc) {
		this.id=id;
		this.dataZamowienia=dataZamowienia;
		this.cena=cena;
		this.ilosc=ilosc;
	}

	@Id
	@Column(name="ID", updatable=false)
	public Integer getId() {
		return id;}
	public void setId(Integer id) {
		this.id = id;}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="IdZamowienie", referencedColumnName= "ID")
	private id_zamowienia_klienta orderID;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="IdZamowienia", referencedColumnName= "ID")
	private zamowienie_klienta clientOrder;

	@Column(name="Ilosc", nullable=false)
	public Integer getIlosc() {
		return ilosc;}
	public void setIlosc(Integer ilosc) {
		this.ilosc = ilosc;}
	
	@Column(name="Cena")
	public BigDecimal getCena() {
		return cena;}
	public void setCena(BigDecimal cena) {
		this.cena = cena;}

	@Column(name="DataZamowienia", nullable=false)
	public Date getDataZamowienia() {
		return dataZamowienia;}
	public void setDataZamowienia(Date dataZamowienia) {
		this.dataZamowienia = dataZamowienia;}
	
}
