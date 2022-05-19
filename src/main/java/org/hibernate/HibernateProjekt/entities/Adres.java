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
@Table(name="Adres", 
uniqueConstraints={@UniqueConstraint(columnNames= {"ID"})})
public class Adres{
	
	private Integer id;
	private Integer nrMieszkania;
	private String miasto = new String();
	private String kodPocztowy = new String();
	private String ulica = new String();
	private String nrBudynku = new String();
	
	public Adres() {}
	
	public Adres(Integer id, String miasto, String kodPocztowy, String ulica, String nrBudynku, Integer nrMieszkania) {
		this.id=id;
		this.miasto=miasto;
		this.kodPocztowy=kodPocztowy;
		this.ulica=ulica;
		this.nrBudynku=nrBudynku;
		this.nrMieszkania=nrMieszkania;
	}
	@Id
	@Column(name="ID", updatable=false)
	public Integer getID() {
		return id;}
	public void setID(Integer id) {
		this.id=id;}
	
	@OneToOne(mappedBy = "Adres")
	private Pracownik emp;
	@OneToOne(mappedBy = "Adres")
	private Klient cli;
	@OneToOne(mappedBy = "Adres")
	private Zaopatrzenie supp;
	
	@Column(name="Miasto", nullable=false)
	public String getMiasto() {
		return miasto;}
	public void setMiasto(String miasto) {
		this.miasto=miasto;}
	
	@Column(name="KodPocztowy", nullable=false)
	public String getKodPocztowy() {
		return kodPocztowy;}
	public void setKodPocztowy(String kodPocztowy) {
		this.kodPocztowy=kodPocztowy;}
	
	@Column(name="Ulica", nullable=false)
	public String getUlica() {
		return ulica;}
	public void setUlica(String ulica) {
		this.ulica=ulica;}
	
	@Column(name="NrBudynku", nullable=false)
	public String getNrBudynku() {
		return nrBudynku;}
	public void setNrBudynku(String nrBudynku) {
		this.nrBudynku=nrBudynku;}
	
	@Column(name="NrMieszkania", nullable=true)
	public Integer getNrMieszkania() {
		return nrMieszkania;}
	public void setNrMieszkania(Integer nrMieszkania) {
		this.nrMieszkania=nrMieszkania;}
}
