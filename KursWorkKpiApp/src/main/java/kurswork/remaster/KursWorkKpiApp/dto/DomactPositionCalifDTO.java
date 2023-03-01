package kurswork.remaster.KursWorkKpiApp.dto;


import kurswork.remaster.KursWorkKpiApp.model.Calification;
import kurswork.remaster.KursWorkKpiApp.model.Domact;
import kurswork.remaster.KursWorkKpiApp.model.Position;

public class DomactPositionCalifDTO {
	
	private Calification calification;
	private Domact domact;
	private Position position;
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
	public DomactPositionCalifDTO(Calification calification, Domact domact, Position position) {
		super();
		this.calification = calification;
		this.domact = domact;
		this.position = position;
	}
	public DomactPositionCalifDTO() {
		super();
	}
	

}
