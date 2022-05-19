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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
//id, materialkorpusu, materialgryfu, iloscprogow, rodzajmostka, iloscstrun, srednicapolagrycm, wysokosccm, materialnaciagu, mocw, idprzedmiotu

@Entity
@Table(name="Specyfikacja_przedmiotu",
uniqueConstraints= {@UniqueConstraint(columnNames= {"ID"})})
public class Specyfikacja_przedmiotu {
	
	private Integer id, iloscProgow, iloscStrun, srednicaPolaGryCM, wysokoscCM, mocW, idPrzedmiotu;
	private String materialKorpusu, materialGryfu, rodzajMostka, materialNaciagu;
	
	public Specyfikacja_przedmiotu() {}
	public Specyfikacja_przedmiotu(Integer id, String materialKorpusu, String materialGryfu, Integer iloscProgow, String rodzajMostka,
			Integer iloscStrun, Integer srednicaPolaGryCM, Integer wysokoscCM, String materialNaciagu, Integer mocW, Integer idPrzedmiotu) {
		this.id=id;
		this.materialKorpusu=materialKorpusu;
		this.materialGryfu=materialGryfu;
		this.iloscProgow=iloscProgow;
		this.rodzajMostka=rodzajMostka;
		this.iloscStrun=iloscStrun;
		this.srednicaPolaGryCM=srednicaPolaGryCM;
		this.wysokoscCM=wysokoscCM;
		this.materialNaciagu=materialNaciagu;
		this.mocW=mocW;
		this.idPrzedmiotu=idPrzedmiotu;
	}
	@Id
	@Column(name="ID", updatable=false)
	public Integer getID() {
		return id;}
	public void setID(Integer id) {
		this.id = id;}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idPrzedmiotu", referencedColumnName = "ID")
	private Przedmiot item;
	
	@Column(name="MaterialKorpusu")
	public String getMaterialKorpusu() {
		return materialKorpusu;}
	public void setMaterialKorpusu(String materialKorpusu) {
		this.materialKorpusu = materialKorpusu;}
	
	@Column(name="MaterialGryfu")
	public String getMaterialGryfu() {
		return materialGryfu;}
	public void setMaterialGryfu(String materialGryfu) {
		this.materialGryfu = materialGryfu;}
	
	@Column(name="iloscProgow")
	public Integer getIloscProgow() {
		return iloscProgow;}
	public void setIloscProgow(Integer iloscProgow) {
		this.iloscProgow = iloscProgow;}
	
	@Column(name="RodzajMostka")
	public String getRodzajMostka() {
		return rodzajMostka;}
	public void setRodzajMostka(String rodzajMostka) {
		this.rodzajMostka = rodzajMostka;}
	
	@Column(name="iloscStrun")
	public Integer getIloscStrun() {
		return iloscStrun;}
	public void setIloscStrun(Integer iloscStrun) {
		this.iloscStrun = iloscStrun;}
	
	@Column(name="SrednicaPolaGryCM")
	public Integer getSrednicaPolaGryCM() {
		return srednicaPolaGryCM;}
	public void setSrednicaPolaGryCM(Integer srednicaPolaGryCM) {
		this.srednicaPolaGryCM = srednicaPolaGryCM;}
	
	@Column(name="WysokoscCM")
	public Integer getWysokoscCM() {
		return wysokoscCM;}
	public void setWysokoscCM(Integer wysokoscCM) {
		this.wysokoscCM = wysokoscCM;}
	
	@Column(name="MaterialNaciagu")
	public String getMaterialNaciagu() {
		return materialNaciagu;}
	public void setMaterialNaciagu(String materialNaciagu) {
		this.materialNaciagu = materialNaciagu;}

	@Column(name="MocW")
	public Integer getMocW() {
		return mocW;}
	public void setMocW(Integer mocW) {
		this.mocW = mocW;}
	
	@Column(name="IdPrzedmiotu")
	public Integer getIdPrzedmiotu() {
		return idPrzedmiotu;}
	public void setIdPrzedmiotu(Integer idPrzedmiotu) {
		this.idPrzedmiotu = idPrzedmiotu;}
}
