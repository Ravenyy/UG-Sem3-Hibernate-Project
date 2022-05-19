package org.hibernate.HibernateProjekt.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="zaopatrzenie_przedmioty",
uniqueConstraints={@UniqueConstraint(columnNames= {"ID"})})
public class zaopatrzenie_przedmioty {
	
	public zaopatrzenie_przedmioty(){}
		
	private Integer id, idDostawcy, idPrzedmiotu;
	
	
	public zaopatrzenie_przedmioty(Integer id, Integer idDostawcy, Integer idPrzedmiotu) {
		this.id=id;
		this.idDostawcy=idDostawcy;
		this.idPrzedmiotu=idPrzedmiotu;
	}
	
	@Id
	@Column(name="ID", updatable=false)
	public Integer getID() {
		return id;}
	public void setID(Integer id) {
		this.id=id;}
	
	@Column(name="IdDostawcy", nullable=false)
	public Integer getIdDostawcy() {
		return idDostawcy;}
	public void setIdDostawcy(Integer idDostawcy) {
		this.idDostawcy=idDostawcy;}
	
	@Column(name="IdPrzedmiotu", nullable=false)
	public Integer getIdPrzedmiotu() {
		return idPrzedmiotu;}
	public void setIdPrzedmiotu(Integer idPrzedmiotu) {
		this.idPrzedmiotu=idPrzedmiotu;}
}
