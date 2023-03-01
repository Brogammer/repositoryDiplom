package kurswork.remaster.KursWorkKpiApp.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "califications")
public class Calification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int range_id;

	@Column(name = "left_bound", nullable = false)
	private double left_bound;

	@Column(name = "right_bound", nullable = false)
	private double right_bound;

	@Column(name = "calificat_coef", nullable = false)
	private double calificat_coef;

	@Column(name = "calificat_symbol", nullable = false)
	private String calificat_symbol;
	
	@OneToMany(mappedBy = "calification",fetch = FetchType.EAGER)
	private Set<DomactPositionCalif> DPCs;

	public int getRange_id() {
		return range_id;
	}

	public void setRange_id(int range_id) {
		this.range_id = range_id;
	}

	public double getLeft_bound() {
		return left_bound;
	}

	public void setLeft_bound(double left_bound) {
		this.left_bound = left_bound;
	}

	public double getRight_bound() {
		return right_bound;
	}

	public void setRight_bound(double right_bound) {
		this.right_bound = right_bound;
	}

	public double getCalificat_coef() {
		return calificat_coef;
	}

	public void setCalificat_coef(double calificat_coef) {
		this.calificat_coef = calificat_coef;
	}

	public String getCalificat_symbol() {
		return calificat_symbol;
	}

	public void setCalificat_symbol(String calificat_symbol) {
		this.calificat_symbol = calificat_symbol;
	}

	public Set<DomactPositionCalif> getDPCs() {
		return DPCs;
	}

	public void setDPCs(Set<DomactPositionCalif> dPCs) {
		DPCs = dPCs;
	}

	public Calification(int range_id, double left_bound, double right_bound, double calificat_coef,
			String calificat_symbol, Set<DomactPositionCalif> dPCs) {
		super();
		this.range_id = range_id;
		this.left_bound = left_bound;
		this.right_bound = right_bound;
		this.calificat_coef = calificat_coef;
		this.calificat_symbol = calificat_symbol;
		DPCs = dPCs;
	}

	public Calification() {
		super();
	}
	
	
	
}
