package kurswork.remaster.KursWorkKpiApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "domact_position_calif")
public class DomactPositionCalif {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int DPC_id;
	
	@ManyToOne
	@JoinColumn(name = "range_id", nullable = false)
	private Calification calification;
	
	@ManyToOne
	@JoinColumn(name = "domact_id", nullable = false)
	private Domact domact;
	
	@ManyToOne
	@JoinColumn(name = "position_id", nullable = false)
	private Position position;

	public int getDPC_id() {
		return DPC_id;
	}

	public void setDPC_id(int dPC_id) {
		DPC_id = dPC_id;
	}

	public Calification getCalification() {
		return calification;
	}

	public void setCalification(Calification calification) {
		this.calification = calification;
	}

	public Domact getDomact() {
		return domact;
	}

	public void setDomact(Domact domact) {
		this.domact = domact;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public DomactPositionCalif(int dPC_id, Calification calification, Domact domact, Position position) {
		super();
		DPC_id = dPC_id;
		this.calification = calification;
		this.domact = domact;
		this.position = position;
	}

	public DomactPositionCalif() {
		super();
	}
	
}
