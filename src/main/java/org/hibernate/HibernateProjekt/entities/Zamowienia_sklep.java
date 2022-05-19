package org.hibernate.HibernateProjekt.entities;

import java.math.BigDecimal;
import java.util.Date;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Zamowienia_sklep")
public class Zamowienia_sklep {
	
	private Integer id, IdPrzedmiot, ilosc;
	private BigDecimal doSplaty;
	private Date dataZamowienia;
	
	public Zamowienia_sklep() {}
	
	public Zamowienia_sklep(Integer id, Integer IdPrzedmiot, Integer ilosc, BigDecimal doSplaty, Date dataZamowienia) {
		this.id=id;
		this.IdPrzedmiot=IdPrzedmiot;
		this.ilosc=ilosc;
		this.doSplaty=doSplaty;
		this.dataZamowienia=dataZamowienia;
	}

	@Id
	@Column(name="ID", updatable=false)
	public Integer getId() {
		return id;}
	public void setId(Integer id) {
		this.id = id;}

	@OneToOne(mappedBy = "Zamowienia_sklep")
	private Przedmiot item;
	
	@Column(name="IdPrzedmiot")
	public Integer getIdPrzedmiot() {
		return IdPrzedmiot;}
	public void setIdPrzedmiot(Integer idPrzedmiot) {
		IdPrzedmiot = idPrzedmiot;}

	@Column(name="Ilosc")
	public Integer getIlosc() {
		return ilosc;}
	public void setIlosc(Integer ilosc) {
		this.ilosc = ilosc;}

	@Column(name="DoSplaty")
	public BigDecimal getDoSplaty() {
		return doSplaty;}
	public void setDoSplaty(BigDecimal doSplaty) {
		this.doSplaty = doSplaty;}

	@Column(name="DataZamowienia")
	public Date getDataZamowienia() {
		return dataZamowienia;}
	public void setDataZamowienia(Date dataZamowienia) {
		this.dataZamowienia = dataZamowienia;}
	
	
}
